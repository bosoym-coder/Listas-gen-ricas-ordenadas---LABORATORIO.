import java.util.Scanner;

public class Ejercicio3 {

    // ── PILA (Stack) ───────────────────────────────────────────────────────────

    static class NodoPila {
        char info;
        NodoPila sig;
    }

    static NodoPila tope = null;

    // Apila un carácter
    static void push(char c) {
        NodoPila nuevo = new NodoPila();
        nuevo.info = c;
        nuevo.sig = tope;
        tope = nuevo;
    }

    // Desapila y devuelve el carácter del tope
    static char pop() {
        char valor = tope.info;
        tope = tope.sig;
        return valor;
    }

    static boolean pilaVacia() {
        return tope == null;
    }

    // ── LISTA ENLAZADA CIRCULAR ────────────────────────────────────────────────

    static class NodoCircular {
        char info;
        NodoCircular sig;
    }

    // Usamos un puntero al ÚLTIMO nodo; el primero es ultimo.sig
    static NodoCircular ultimo = null;
    static int tamLista = 0;

    // Inserta al final de la lista circular
    static void insertarCircular(char c) {
        NodoCircular nuevo = new NodoCircular();
        nuevo.info = c;

        if (ultimo == null) {
            // La lista está vacía: el nodo apunta a sí mismo
            nuevo.sig = nuevo;
            ultimo = nuevo;
        } else {
            // Insertamos después del último y actualizamos ultimo
            nuevo.sig = ultimo.sig;   // nuevo apunta al primero
            ultimo.sig = nuevo;       // el antiguo último apunta al nuevo
            ultimo = nuevo;           // ahora el nuevo es el último
        }
        tamLista++;
    }

    // Extrae y devuelve el PRIMER elemento de la lista circular
    static char extraerPrimero() {
        NodoCircular primero = ultimo.sig;  // el primero es ultimo.sig
        char valor = primero.info;

        if (primero == ultimo) {
            // Solo había un elemento
            ultimo = null;
        } else {
            ultimo.sig = primero.sig;       // el último ahora apunta al segundo
        }
        tamLista--;
        return valor;
    }

    // ── LÓGICA PRINCIPAL ──────────────────────────────────────────────────────

    // Reinicia la pila y la lista para analizar una nueva frase
    static void limpiarEstructuras() {
        tope = null;
        ultimo = null;
        tamLista = 0;
    }

    // Verifica si la frase es palíndromo ignorando espacios y mayúsculas
    static boolean esPalindromo(String frase) {
        limpiarEstructuras();

        // Procesamos solo letras (ignoramos espacios y signos)
        String limpia = frase.toLowerCase().replaceAll("[^a-záéíóúüñ]", "");

        // Añadimos cada carácter a la pila y a la lista circular
        for (int i = 0; i < limpia.length(); i++) {
            char c = limpia.charAt(i);
            push(c);
            insertarCircular(c);
        }

        // Comparamos carácter a carácter:
        // - de la pila sacamos desde el FINAL (último insertado)
        // - de la lista circular sacamos desde el INICIO (primero insertado)
        // Si en algún momento difieren, no es palíndromo
        while (!pilaVacia()) {
            char desdeFinal = pop();
            char desdeInicio = extraerPrimero();
            if (desdeFinal != desdeInicio) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Detector de palíndromos ===");
        System.out.println("(Escribe 'salir' para terminar)\n");

        while (true) {
            System.out.print("Ingresa una frase: ");
            String frase = sc.nextLine();

            if (frase.equalsIgnoreCase("salir")) break;

            if (frase.isEmpty()) {
                System.out.println("Por favor ingresa una frase válida.\n");
                continue;
            }

            if (esPalindromo(frase)) {
                System.out.println("\"" + frase + "\" → ES palíndromo ✓\n");
            } else {
                System.out.println("\"" + frase + "\" → NO es palíndromo ✗\n");
            }
        }

        System.out.println("Programa finalizado.");
        sc.close();
    }
}