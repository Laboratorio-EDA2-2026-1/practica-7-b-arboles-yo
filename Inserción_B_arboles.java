/*Implementa aquí todos los procesos necesarios para la operación de inserción. 
Pueden modificar la extensión del documento para que se ajuste al lenguaje de su elección y comentar estas instrucciones.
Mi código esta en java: */
public class ArbolBInsercion {

    // clase interna que representa cada nodo del árbol
    class Nodo {
        int[] key;     // las llaves que tiene este nodo
        Nodo[] hijos;     // los hijos del nodo
        int n;            // cuántas llaves hay aquí
        boolean leaf;     // true si es hola
        int t;            // grado mínimo

        // constructor del nodo
        Nodo(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.key = new int[2 * t - 1];
            this.hijos = new Nodo[2 * t];
            this.n = 0;
        }

        // insertar una llave en un nodo que no está lleno
        void insertarNoLleno(int k) {
            int i = n - 1;

            if (leaf) {
                while (i >= 0 && key[i] > k) {
                    key[i + 1] = key[i];
                    i--;
                }

                key[i + 1] = k;
                n = n + 1;
                System.out.println("Insertado " + k + " en leaf.");
            } else {
                // buscamos el hijo adecuado donde va la llave
                while (i >= 0 && key[i] > k) {
                    i--;
                }
                i++;

                // si el hijo donde va a ir está lleno, lo dividimos
                if (hijos[i].n == 2 * t - 1) {
                    dividirHijo(i, hijos[i]);

                    // después de dividir, vemos en cuál mitad insertar
                    if (key[i] < k) {
                        i++;
                    }
                }
                hijos[i].insertarNoLleno(k);
            }
        }

        // dividir un hijo lleno en dos
        void dividirHijo(int i, Nodo y) {
            Nodo z = new Nodo(y.t, y.leaf);
            z.n = t - 1;

            // copiamos la mitad de las llaves a z
            for (int j = 0; j < t - 1; j++) {
                z.key[j] = y.key[j + t];
            }

            // si no es hoja, copiamos también sus hijos
            if (!y.leaf) {
                for (int j = 0; j < t; j++) {
                    z.hijos[j] = y.hijos[j + t];
                }
            }

            y.n = t - 1; // actualizamos el número de llaves en y

            // movemos los hijos del nodo actual para hacer espacio al nuevo
            for (int j = n; j >= i + 1; j--) {
                hijos[j + 1] = hijos[j];
            }

            hijos[i + 1] = z;

            // movemos las llaves del nodo actual
            for (int j = n - 1; j >= i; j--) {
                key[j + 1] = key[j];
            }

            // subimos la llave del medio
            key[i] = y.key[t - 1];
            n = n + 1;

            System.out.println("Dividido un nodo, subiendo la llave " + y.key[t-1]);
        }
    }

    Nodo raiz; // raíz del árbol
    int t;     // grado mínimo

    // constructor
    public ArbolBInsercion(int t) {
        this.t = t;
        raiz = new Nodo(t, true);
    }

    public void insertar(int k) {
        Nodo r = raiz;

        // si la raíz está llena, se divide
        if (r.n == 2 * t - 1) {
            Nodo s = new Nodo(t, false);
            s.hijos[0] = r;
            s.dividirHijo(0, r);
            int i = 0;
            if (s.key[0] < k) {
                i++;
            }
            s.hijos[i].insertarNoLleno(k);
            raiz = s;
            System.out.println("La raíz estaba llena, se dividió antes de insertar " + k);
        } else {
            r.insertarNoLleno(k);
        }
    }
   
    public static void main(String[] args) {
        ArbolBInsercion arbol = new ArbolBInsercion(3); // grado 3

        // metemos algunas llaves
        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(6);
        arbol.insertar(12);
        arbol.insertar(30);
        arbol.insertar(7);
        arbol.insertar(17);
    }
}
