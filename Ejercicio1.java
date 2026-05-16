import java.util.Random;

public class Ejercicio1 {

    // Clase interna que representa cada nodo de la lista
    static class Nodo {
        int info;
        Nodo sig;
    }

    // Referencia al primer nodo de la lista
    static Nodo raiz = null;

    // Inserta un nuevo nodo AL FINAL de la lista
    static void insertarAlFinal(int x) {
        Nodo nuevo = new Nodo();
        nuevo.info = x;
        nuevo.sig = null;

        // Si la lista está vacía, el nuevo nodo es la raíz
        if (raiz == null) {
            raiz = nuevo;
        } else {
            // Recorremos hasta llegar al último nodo
            Nodo reco = raiz;
            while (reco.sig != null) {
                reco = reco.sig;
            }
            // Enlazamos el nuevo nodo al final
            reco.sig = nuevo;
        }
    }

    // Recorre e imprime todos los elementos de la lista
    static void imprimir() {
        Nodo reco = raiz;
        System.out.print("Lista: ");
        while (reco != null) {
            System.out.print(reco.info + " -> ");
            reco = reco.sig;
        }
        System.out.println("null");
    }

    // Elimina todos los nodos cuyo valor supere 'limite'
    static void eliminarMayores(int limite) {
        // Primero eliminamos nodos al inicio que superen el límite
        while (raiz != null && raiz.info > limite) {
            raiz = raiz.sig;
        }

        // Ahora recorremos el resto de la lista
        if (raiz != null) {
            Nodo reco = raiz;
            while (reco.sig != null) {
                if (reco.sig.info > limite) {
                    // Saltamos el nodo que supera el límite
                    reco.sig = reco.sig.sig;
                } else {
                    reco = reco.sig;
                }
            }
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();

        // Insertar 10 números enteros positivos aleatorios (entre 1 y 100)
        System.out.println("=== Insertando 10 números aleatorios al final ===");
        for (int i = 0; i < 10; i++) {
            int numero = rand.nextInt(100) + 1;
            System.out.println("Insertando: " + numero);
            insertarAlFinal(numero);
        }

        System.out.println();
        imprimir();

        // Eliminar todos los nodos que superen el valor 50
        int limite = 50;
        System.out.println("\n=== Eliminando nodos con valor mayor a " + limite + " ===");
        eliminarMayores(limite);
        imprimir();
    }
}