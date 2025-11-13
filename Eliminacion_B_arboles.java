/*Implementa aquí todos los procesos necesarios para la operación de eliminación. 
Pueden modificar la extensión del documento para que se ajuste al lenguaje de su elección y comentar estas instrucciones.
Mi código está en java use la combinación de clases para no estar escribiendo mucho codigo repetido (extends) : */
public class ArbolBEliminacion extends ArbolBInsercion {

    public ArbolBEliminacion(int t) {
        super(t);
    }

    // versión simplificada de eliminación
    public void eliminar(int k) {
        raiz = eliminarRec(raiz, k);
    }

    private Nodo eliminarRec(Nodo nodo, int k) {
        if (nodo == null) return null;

        int i = 0;
        while (i < nodo.n && k > nodo.key[i]) {
            i++;
        }

        if (i < nodo.n && nodo.key[i] == k) {
            if (nodo.leaf) {
                // caso simple: borrar de hoja
                for (int j = i; j < nodo.n - 1; j++) nodo.key[j] = nodo.key[j + 1];
                nodo.n--;
                System.out.println("Eliminado " + k + " de una hoja.");
            } else {
                // versión básica: sustituir por sucesor
                int sucesor = nodo.hijos[i + 1].key[0];
                nodo.key[i] = sucesor;
                nodo.hijos[i + 1] = eliminarRec(nodo.hijos[i + 1], sucesor);
            }
        } else if (!nodo.leaf) {
            nodo.hijos[i] = eliminarRec(nodo.hijos[i], k);
        } else {
            System.out.println("No se encontró " + k + " en el árbol.");
        }

        return nodo;
    }

    public static void main(String[] args) {
        ArbolBEliminacion arbol = new ArbolBEliminacion(3);

        // insertamos algunas llaves primero
        int[] datos = {10, 20, 5, 6, 12, 30, 7, 17};
        for (int x : datos) {
            arbol.insertar(x);
        }

        System.out.println("Buscando antes de borrar:");
        arbol.buscar(12);

        System.out.println("\nEliminando 6...");
        arbol.eliminar(6);

        System.out.println("Eliminando 17...");
        arbol.eliminar(17);

        System.out.println("Buscando 17 después del borrado:");
        arbol.buscar(17);
    }
}
