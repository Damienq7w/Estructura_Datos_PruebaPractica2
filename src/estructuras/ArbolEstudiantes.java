package estructuras;

import java.util.ArrayList;

import modelo.Estudiante;

public class ArbolEstudiantes {

    private NodoEstudiante raiz;
    private int ultimasComparaciones;

    public boolean estaVacio() {
        return raiz == null;
    }

    public NodoEstudiante getRaiz() {
        return raiz;
    }

    /** Inserta sin duplicados. Devuelve false si la matrícula ya existe. */
    public boolean insertar(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("No se puede insertar un estudiante nulo.");
        }
        if (buscarNodo(raiz, estudiante.getMatricula()) != null) {
            return false; // duplicado: el árbol no cambia

            return false; // duplicado: el arbol no cambia
        }
        raiz = insertar(raiz, estudiante);
        return true;
    }


    // Caso base: posicion vacia, ahi se crea la hoja nueva.
    private NodoEstudiante insertar(NodoEstudiante nodo, Estudiante estudiante) {
        if (nodo == null) {
            return new NodoEstudiante(estudiante);
        }
        if (estudiante.getMatricula() < nodo.getMatricula()) {
            nodo.setIzquierdo(insertar(nodo.getIzquierdo(), estudiante));
        } else {
            nodo.setDerecho(insertar(nodo.getDerecho(), estudiante));
        }
        return nodo;
    }


    /** Busca por matricula y registra cuantas comparaciones hizo. null si no existe. */
    public Estudiante buscar(int matricula) {
        ultimasComparaciones = 0;
        NodoEstudiante nodo = buscarContando(raiz, matricula);
        return nodo == null ? null : nodo.getDato();
    }


    // Igual que buscarNodo, pero cuenta una comparacion por cada nodo visitado.

    private NodoEstudiante buscarContando(NodoEstudiante nodo, int matricula) {
        if (nodo == null) {
            return null;
        }
        ultimasComparaciones++;
        if (matricula == nodo.getMatricula()) {
            return nodo;
        }
        if (matricula < nodo.getMatricula()) {
            return buscarContando(nodo.getIzquierdo(), matricula);
        }
        return buscarContando(nodo.getDerecho(), matricula);
    }

    // Caso base: nodo nulo (no esta) o matricula encontrada. Descarta un subarbol por paso.

    protected NodoEstudiante buscarNodo(NodoEstudiante nodo, int matricula) {
        if (nodo == null || matricula == nodo.getMatricula()) {
            return nodo;
        }
        if (matricula < nodo.getMatricula()) {
            return buscarNodo(nodo.getIzquierdo(), matricula);
        }
        return buscarNodo(nodo.getDerecho(), matricula);
    }


    /** Comparaciones de la ultima llamada a buscar(). */
    public int getUltimasComparaciones() {
        return ultimasComparaciones;
    }


    /** Total de nodos (estudiantes). Arbol vacio = 0. O(n). */

    public int contar() {
        return contar(raiz);
    }

    private int contar(NodoEstudiante nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contar(nodo.getIzquierdo()) + contar(nodo.getDerecho());
    }


    /** Recorrido indicado. Arbol vacio = lista vacia. O(n). */

    public ArrayList<Estudiante> recorrer(TipoRecorrido tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("Debe indicar un tipo de recorrido.");
        }
        ArrayList<Estudiante> resultado = new ArrayList<>();
        switch (tipo) {
            case PREORDEN:
                preorden(raiz, resultado);
                break;
            case INORDEN:
                inorden(raiz, resultado);
                break;
            default:
                posorden(raiz, resultado);
                break;
        }
        return resultado;
    }


    /** Solo las matriculas del recorrido (util para mostrar y para las pruebas). */

    public ArrayList<Integer> recorrerMatriculas(TipoRecorrido tipo) {
        ArrayList<Integer> matriculas = new ArrayList<>();
        for (Estudiante e : recorrer(tipo)) {
            matriculas.add(e.getMatricula());
        }
        return matriculas;
    }

    // Preorden: visita, izquierdo, derecho. Caso base: nodo nulo.
    private void preorden(NodoEstudiante nodo, ArrayList<Estudiante> lista) {
        if (nodo == null) {
            return;
        }
        lista.add(nodo.getDato());
        preorden(nodo.getIzquierdo(), lista);
        preorden(nodo.getDerecho(), lista);
    }

    // Inorden: izquierdo, visita, derecho. En un ABB sale ordenado.
    private void inorden(NodoEstudiante nodo, ArrayList<Estudiante> lista) {
        if (nodo == null) {
            return;
        }
        inorden(nodo.getIzquierdo(), lista);
        lista.add(nodo.getDato());
        inorden(nodo.getDerecho(), lista);
    }


    // Posorden: izquierdo, derecho, visita. La raiz sale al final.

    private void posorden(NodoEstudiante nodo, ArrayList<Estudiante> lista) {
        if (nodo == null) {
            return;
        }
        posorden(nodo.getIzquierdo(), lista);
        posorden(nodo.getDerecho(), lista);
        lista.add(nodo.getDato());
    }

    /**
     * Modifica nombre y promedio SIN tocar la matricula (es la clave del ABB).
     * Valida los dos datos antes de cambiar nada. false si la matricula no existe.
     */
    public boolean modificar(int matricula, String nuevoNombre, double nuevoPromedio) {
        NodoEstudiante nodo = buscarNodo(raiz, matricula);
        if (nodo == null) {
            return false;
        }
        Estudiante.validarNombre(nuevoNombre);     // lanza IllegalArgumentException
        Estudiante.validarPromedio(nuevoPromedio); // antes de modificar el objeto real
        nodo.getDato().setNombre(nuevoNombre);
        nodo.getDato().setPromedio(nuevoPromedio);
        return true;
    }

    /** Elimina por matricula (hoja, un hijo o dos hijos). false si no existe. O(h). */
    public boolean eliminar(int matricula) {
        if (buscarNodo(raiz, matricula) == null) {
            return false;
        }
        raiz = eliminar(raiz, matricula);
        return true;
    }

    // Devuelve la raiz del subarbol ya reconectada; el padre actualiza su enlace con ella.
    private NodoEstudiante eliminar(NodoEstudiante nodo, int matricula) {
        if (nodo == null) {
            return null; // caso base
        }
        if (matricula < nodo.getMatricula()) {
            nodo.setIzquierdo(eliminar(nodo.getIzquierdo(), matricula));
        } else if (matricula > nodo.getMatricula()) {
            nodo.setDerecho(eliminar(nodo.getDerecho(), matricula));
        } else {
            if (nodo.getIzquierdo() == null) {
                return nodo.getDerecho();   // hoja o solo hijo derecho
            }
            if (nodo.getDerecho() == null) {
                return nodo.getIzquierdo(); // solo hijo izquierdo
            }
            // Dos hijos: el sucesor inorden (menor del subarbol derecho) es mayor que
            // todo el lado izquierdo y menor que el resto del derecho, asi el orden se mantiene.
            NodoEstudiante sucesor = minimoNodo(nodo.getDerecho());
            nodo.setDato(sucesor.getDato());
            nodo.setDerecho(eliminar(nodo.getDerecho(), sucesor.getMatricula()));
        }
        return nodo;
    }

    // Baja siempre por la izquierda. Caso base: no hay hijo izquierdo.
    protected NodoEstudiante minimoNodo(NodoEstudiante nodo) {
        if (nodo.getIzquierdo() == null) {
            return nodo;
        }
        return minimoNodo(nodo.getIzquierdo());
    }

}
