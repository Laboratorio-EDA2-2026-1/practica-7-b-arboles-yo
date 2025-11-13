/*Implementa aquí todos los procesos necesarios para la operación de inserción. 
Pueden modificar la extensión del documento para que se ajuste al lenguaje de su elección y comentar estas instrucciones.
Mi código esta en java use la combinación de clases para no estar escribiendo mucho codigo repetido (extends) : */
public class ArbolBInsercion extends ArbolBBusqueda {

    public ArbolBInsercion(int t) {
        super(t);
    }

    // este método divide un hijo lleno
    void dividirHijo(Nodo x, int i, Nodo y) {
        Nodo z = new Nodo(t, y.leaf);
        z.n = t - 1;

        // movemos las llaves de y a z
        for (int j = 0; j < t - 1; j++) {
            z.key[j] = y.key[j + t];
        }

        // si no es hoja, movemos también los hijos
        if (!y.leaf) {
            for (int j = 0; j < t; j++) {
                z.hijos[j] = y.hijos[j + t];
            }
        }

        y.n = t - 1;

        for (int j = x.n; j >= i + 1; j--) {
            x.hijos[j + 1] = x.hijos[j];
        }
        x.hijos[i + 1] = z;

        for (int j = x.n - 1; j >= i; j--) {
            x.key[j + 1] = x.key[j];
        }

        x.key[i] = y.key[t - 1];
        x.n++;
    }

    // insertar cuando el nodo no está lleno
    void insertarNoLleno(Nodo x, int k) {
        int i = x.n - 1;

        if (x.leaf) {
            while (i >= 0 && k < x.key[i]) {
                x.key[i + 1] = x.key[i];
                i--;
            }
            x.key[i + 1] = k;
            x.n++;
        } else {
            while (i >= 0 && k < x.key[i]) {
                i--;
            }
            i++;
            if (x.hijos[i].n == 2 * t - 1) {
                dividirHijo(x, i, x.hijos[i]);
                if (k > x.key[i]) {
                    i++;
                }
            }
            insertarNoLleno(x.hijos[i], k);
        }
    }

    public void insertar(int k) {
        Nodo r = raiz;
        if (r.n == 2 * t - 1) {
            Nodo s = new Nodo(t, false);
            raiz = s;
            s.hijos[0] = r;
            dividirHijo(s, 0, r);
            int i = (s.key[0] < k) ? 1 : 0;
            insertarNoLleno(s.hijos[i], k);
        } else {
            insertarNoLleno(r, k);
        }
    }

    public static void main(String[] args) {
        ArbolBInsercion arbol = new ArbolBInsercion(3);

        arbol.insertar(10);
        arbol.insertar(20);
        arbol.insertar(5);
        arbol.insertar(6);
        arbol.insertar(12);

        System.out.println("Prueba de búsqueda tras inserciones:");
        arbol.buscar(6);
        arbol.buscar(15);
    }
}
