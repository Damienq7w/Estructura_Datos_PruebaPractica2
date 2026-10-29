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

    /** Recorrido indicado. Árbol vacío = lista vacía. O(n). */
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

    /** Solo las matrículas del recorrido (útil para mostrar y para las pruebas). */
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

    // Posorden: izquierdo, derecho, visita. La raíz sale al final.
    private void posorden(NodoEstudiante nodo, ArrayList<Estudiante> lista) {
        if (nodo == null) {
            return;
        }
        posorden(nodo.getIzquierdo(), lista);
        posorden(nodo.getDerecho(), lista);
        lista.add(nodo.getDato());
    }
}
