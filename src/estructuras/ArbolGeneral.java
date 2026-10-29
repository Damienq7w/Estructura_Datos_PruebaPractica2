package estructuras;

import java.util.List;

import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/**
 * Arbol general de la jerarquia academica (UTA → facultades → carreras → paralelos).
 * Todas las consultas son recursivas. Como el árbol general no esta ordenado,
 * buscar puede visitar los N nodos: O(N).
 */
public class ArbolGeneral {

    private NodoUnidad raiz;

    public boolean estaVacio() {
        return raiz == null;
    }

    public NodoUnidad getRaiz() {
        return raiz;
    }

    /** Crea la raiz unica. Se rechaza si ya existe una raiz o si no es de tipo UNIVERSIDAD. */
    public boolean crearRaiz(UnidadAcademica unidad) {
        if (raiz != null || unidad == null || unidad.getTipo() != TipoUnidad.UNIVERSIDAD) {
            return false;
        }
        raiz = new NodoUnidad(unidad);
        return true;
    }

    /**
     * Agrega una unidad bajo el padre indicado.
     * Rechaza: datos nulos, arbol sin raiz, código repetido, padre inexistente
     * o relacion padre–hijo inválida. Como cada codigo es unico y el nodo nuevo
     * siempre se cuelga de uno existente, no se pueden formar ciclos.
     */
    public boolean agregar(String codigoPadre, UnidadAcademica unidad) {
        if (unidad == null || raiz == null) {
            return false;
        }
        if (existe(unidad.getCodigo())) {
            return false; // código único
        }
        NodoUnidad padre = buscar(codigoPadre);
        if (padre == null) {
            return false; // padre inexistente
        }
        return padre.agregarHijo(new NodoUnidad(unidad)); // valida la jerarquia
    }

    /** Busca por código (sin importar espacios ni mayusculas). Devuelve null si no existe. */
    public NodoUnidad buscar(String codigo) {
        String clave = UnidadAcademica.normalizar(codigo);
        if (clave.isEmpty()) {
            return null;
        }
        return buscar(raiz, clave);
    }

    // Caso base: nodo nulo (no esta) o código encontrado. Si no, prueba con cada hijo.
    private NodoUnidad buscar(NodoUnidad nodo, String clave) {
        if (nodo == null) {
            return null;
        }
        if (nodo.getCodigo().equals(clave)) {
            return nodo;
        }
        for (NodoUnidad hijo : nodo.getHijos()) {
            NodoUnidad encontrado = buscar(hijo, clave);
            if (encontrado != null) {
                return encontrado;
            }
        }
        return null;
    }

    public boolean existe(String codigo) {
        return buscar(codigo) != null;
    }

    /** Nivel (profundidad en aristas) de una unidad; la raiz esta en el nivel 0. -1 si no existe. */
    public int nivel(String codigo) {
        String clave = UnidadAcademica.normalizar(codigo);
        if (clave.isEmpty()) {
            return -1;
        }
        return nivel(raiz, clave, 0);
    }

    // Caso base: nodo nulo (-1) o codigo encontrado (devuelve la profundidad acumulada).
    private int nivel(NodoUnidad nodo, String clave, int profundidad) {
        if (nodo == null) {
            return -1;
        }
        if (nodo.getCodigo().equals(clave)) {
            return profundidad;
        }
        for (NodoUnidad hijo : nodo.getHijos()) {
            int n = nivel(hijo, clave, profundidad + 1);
            if (n != -1) {
                return n;
            }
        }
        return -1;
    }

    /** Grado = cantidad de hijos directos. -1 si la unidad no existe. */
    public int grado(String codigo) {
        NodoUnidad nodo = buscar(codigo);
        return nodo == null ? -1 : nodo.getHijos().size();
    }

    /** Altura en aristas: arbol vacio = -1, solo la raiz = 0. O(N). */
    public int altura() {
        return altura(raiz);
    }

    // Caso base: nodo nulo vale -1, así una hoja vale 1 + (-1) = 0.
    private int altura(NodoUnidad nodo) {
        if (nodo == null) {
            return -1;
        }
        int mayor = -1;
        for (NodoUnidad hijo : nodo.getHijos()) {
            mayor = Math.max(mayor, altura(hijo));
        }
        return 1 + mayor;
    }

    /** Numero total de unidades. O(N). */
    public int contarNodos() {
        return contarNodos(raiz);
    }

    private int contarNodos(NodoUnidad nodo) {
        if (nodo == null) {
            return 0;
        }
        int total = 1;
        for (NodoUnidad hijo : nodo.getHijos()) {
            total += contarNodos(hijo);
        }
        return total;
    }

    /** NNumero de hojas (unidades sin hijos). O(N). */
    public int contarHojas() {
        return contarHojas(raiz);
    }

    private int contarHojas(NodoUnidad nodo) {
        if (nodo == null) {
            return 0;
        }
        if (nodo.esHoja()) {
            return 1;
        }
        int total = 0;
        for (NodoUnidad hijo : nodo.getHijos()) {
            total += contarHojas(hijo);
        }
        return total;
    }

    /** Jerarquia en preorden con dos espacios de sangria por nivel. */
    public String imprimirJerarquia() {
        if (raiz == null) {
            return "(árbol general vacío)";
        }
        StringBuilder sb = new StringBuilder();
        imprimir(raiz, 0, sb);
        return sb.toString();
    }

    private void imprimir(NodoUnidad nodo, int nivel, StringBuilder sb) {
        sb.append("  ".repeat(nivel)).append(nodo.getDato()).append('\n');
        for (NodoUnidad hijo : nodo.getHijos()) {
            imprimir(hijo, nivel + 1, sb);
        }
    }
}
