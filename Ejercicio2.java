import java.util.Scanner;

public class Ejercicio2 {

    // Cada nodo guarda el coeficiente y el exponente de un término
    static class Nodo {
        double coeficiente;
        int exponente;
        Nodo sig;
    }

    static Nodo raiz = null;

    // Inserta un nuevo término al final de la lista
    static void insertarTermino(double coef, int exp) {
        Nodo nuevo = new Nodo();
        nuevo.coeficiente = coef;
        nuevo.exponente = exp;
        nuevo.sig = null;

        if (raiz == null) {
            raiz = nuevo;
        } else {
            Nodo reco = raiz;
            while (reco.sig != null) {
                reco = reco.sig;
            }
            reco.sig = nuevo;
        }
    }

    // Muestra el polinomio en formato legible
    static void mostrarPolinomio() {
        Nodo reco = raiz;
        System.out.print("Polinomio: ");
        boolean primero = true;
        while (reco != null) {
            if (!primero && reco.coeficiente >= 0) System.out.print(" + ");
            if (reco.exponente == 0) {
                System.out.print(reco.coeficiente);
            } else if (reco.exponente == 1) {
                System.out.print(reco.coeficiente + "x");
            } else {
                System.out.print(reco.coeficiente + "x^" + reco.exponente);
            }
            reco = reco.sig;
            primero = false;
        }
        System.out.println();
    }

    // Evalúa el polinomio para un valor dado de x
    static double evaluar(double x) {
        Nodo reco = raiz;
        double resultado = 0;
        while (reco != null) {
            // Cada término = coeficiente * x^exponente
            resultado += reco.coeficiente * Math.pow(x, reco.exponente);
            reco = reco.sig;
        }
        return resultado;
    }

    // Muestra la tabla de valores de x=0.0 hasta x=5.0 con paso 0.5
    static void mostrarTabla() {
        System.out.println("\n=== Tabla de valores ===");
        System.out.printf("%-10s %-15s%n", "x", "f(x)");
        System.out.println("-------------------------");
        for (double x = 0.0; x <= 5.0; x += 0.5) {
            System.out.printf("%-10.1f %-15.4f%n", x, evaluar(x));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Ingreso de polinomio en x ===");
        System.out.print("¿Cuántos términos tiene el polinomio? ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {
            System.out.println("\nTérmino " + i + ":");
            System.out.print("  Coeficiente: ");
            double coef = sc.nextDouble();
            System.out.print("  Exponente:   ");
            int exp = sc.nextInt();
            insertarTermino(coef, exp);
        }

        System.out.println();
        mostrarPolinomio();
        mostrarTabla();

        sc.close();
    }
}