/*Implementa aquí la operación de búsqueda. 
Pueden modificar la extensión del documento para que se ajuste al lenguaje de su elección y comentar estas instrucciones.
Mi código esta en java: */
public class ArbolBBusqueda {
    class Nodo {
        int[] key;      // aquí se guardan las llaves
        Nodo[] hijos;   // los hijos del nodo
        int n;          // cuántas llaves tiene el nodo
        boolean leaf;   // si es hoja o no
        int t;          // grado mínimo del árbol

        Nodo(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.key = new int[2 * t - 1];
            this.hijos = new Nodo[2 * t];
            this.n = 0;
        }

        // función para buscar una llave (key)
        Nodo buscar(int k) {
            int i = 0;
            while (i < n && k > key[i]) {
                i++;
            }
            if (i < n && key[i] == k) {
                System.out.println("Encontrado " + k + " en este nodo.");
                return this;
            }
            if (leaf) {
                System.out.println("No se encontró la llave " + k + " (llegamos a una hoja).");
                return null;
            }
            System.out.println("Bajando al hijo número " + i + " para seguir buscando...");
            return hijos[i].buscar(k);
        }
    }

    Nodo raiz;
    int t;

    public ArbolBBusqueda(int t) {
        this.t = t;
        raiz = new Nodo(t, true);
    }

    public void buscar(int k) {
        if (raiz == null) {
            System.out.println("El árbol está vacío :(");
        } else {
            Nodo resultado = raiz.buscar(k);
            if (resultado != null) {
                System.out.println("La llave " + k + " sí está en el árbol.");
            } else {
                System.out.println("La llave " + k + " no existe en el árbol.");
            }
        }
    }

    public static void main(String[] args) {
        ArbolBBusqueda arbol = new ArbolBBusqueda(3);

        // metemos algunas key a mano para probar
        arbol.raiz.key[0] = 10;
        arbol.raiz.key[1] = 20;
        arbol.raiz.key[2] = 30;
        arbol.raiz.n = 3;

        // probamos buscar algunas llaves (key)
        arbol.buscar(20);
        arbol.buscar(15);
    }
}
