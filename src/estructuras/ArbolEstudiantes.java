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

    /**
     * Modifica nombre y promedio SIN tocar la matrícula (es la clave del ABB).
     * Valida los dos datos antes de cambiar nada. false si la matrícula no existe.
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

    /** Elimina por matrícula (hoja, un hijo o dos hijos). false si no existe. O(h). */
    public boolean eliminar(int matricula) {
        if (buscarNodo(raiz, matricula) == null) {
            return false;
        }
        raiz = eliminar(raiz, matricula);
        return true;
    }

    // Devuelve la raíz del subárbol ya reconectada; el padre actualiza su enlace con ella.
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
            // Dos hijos: el sucesor inorden (menor del subárbol derecho) es mayor que
            // todo el lado izquierdo y menor que el resto del derecho, así el orden se mantiene.
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

    /** Altura en aristas: vacío = -1, un solo nodo = 0. O(n). */
    public int altura() {
        return altura(raiz);
    }

    private int altura(NodoEstudiante nodo) {
        if (nodo == null) {
            return -1;
        }
        return 1 + Math.max(altura(nodo.getIzquierdo()), altura(nodo.getDerecho()));
    }

    /** Cantidad de hojas. Vacío = 0. O(n). */
    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(NodoEstudiante nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) {
            return 1;
        }
        return contarHojas(nodo.getIzquierdo()) + contarHojas(nodo.getDerecho());
    }

    /** Estudiante con la menor matrícula; null si está vacío. O(h). */
    public Estudiante minimo() {
        return raiz == null ? null : minimoNodo(raiz).getDato();
    }

    /** Estudiante con la mayor matrícula; null si está vacío. O(h). */
    public Estudiante maximo() {
        return raiz == null ? null : maximoNodo(raiz).getDato();
    }

    private NodoEstudiante maximoNodo(NodoEstudiante nodo) {
        if (nodo.getDerecho() == null) {
            return nodo;
        }
        return maximoNodo(nodo.getDerecho());
    }

    /** Promedio aritmético de los promedios registrados. Vacío = 0.0. O(n). */
    public double promedioGeneral() {
        int total = contar();
        return total == 0 ? 0.0 : sumarPromedios(raiz) / total;
    }

    private double sumarPromedios(NodoEstudiante nodo) {
        if (nodo == null) {
            return 0.0;
        }
        return nodo.getDato().getPromedio()
                + sumarPromedios(nodo.getIzquierdo())
                + sumarPromedios(nodo.getDerecho());
    }

    /** Verifica la invariante del ABB con límites (min, max) en cada subárbol. O(n). */
    public boolean esABBValido() {
        return esABBValido(raiz, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Cada nodo debe estar estrictamente entre los límites heredados de sus ancestros.
    private boolean esABBValido(NodoEstudiante nodo, long minimo, long maximo) {
        if (nodo == null) {
            return true;
        }
        int m = nodo.getMatricula();
        if (m <= minimo || m >= maximo) {
            return false;
        }
        return esABBValido(nodo.getIzquierdo(), minimo, m)
                && esABBValido(nodo.getDerecho(), m, maximo);
    }
}
