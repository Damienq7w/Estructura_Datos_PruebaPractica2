package estructuras;

import java.util.ArrayList;

import modelo.Estudiante;

/**
 * Árbol binario de búsqueda de estudiantes ordenado por matrícula.
 * Invariante: izquierda < nodo < derecha y sin matrículas repetidas.
 * Insertar, buscar y eliminar siguen un solo camino: O(h), con h la altura
 * (O(log n) si el árbol está equilibrado, O(n) si degeneró en cadena).
 */
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
        }
        raiz = insertar(raiz, estudiante);
        return true;
    }

    // Caso base: posición vacía, ahí se crea la hoja nueva.
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

    /** Busca por matrícula y registra cuántas comparaciones hizo. null si no existe. */
    public Estudiante buscar(int matricula) {
        ultimasComparaciones = 0;
        NodoEstudiante nodo = buscarContando(raiz, matricula);
        return nodo == null ? null : nodo.getDato();
    }

    // Igual que buscarNodo, pero cuenta una comparación por cada nodo visitado.
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

    // Caso base: nodo nulo (no está) o matrícula encontrada. Descarta un subárbol por paso.
    protected NodoEstudiante buscarNodo(NodoEstudiante nodo, int matricula) {
        if (nodo == null || matricula == nodo.getMatricula()) {
            return nodo;
        }
        if (matricula < nodo.getMatricula()) {
            return buscarNodo(nodo.getIzquierdo(), matricula);
        }
        return buscarNodo(nodo.getDerecho(), matricula);
    }

    /** Comparaciones de la última llamada a buscar(). */
    public int getUltimasComparaciones() {
        return ultimasComparaciones;
    }

    /** Total de nodos (estudiantes). Árbol vacío = 0. O(n). */
    public int contar() {
        return contar(raiz);
    }

    private int contar(NodoEstudiante nodo) {
        if (nodo == null) {
            return 0;
        }
        return 1 + contar(nodo.getIzquierdo()) + contar(nodo.getDerecho());
    }
}
