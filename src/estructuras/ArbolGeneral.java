package estructuras;

import java.util.List;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/**
 * Árbol general de la jerarquía académica (UTA → facultades → carreras → paralelos).
 * Todas las consultas son recursivas. Como el árbol general no está ordenado,
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

    /** Crea la raíz única. Se rechaza si ya existe una raíz o si no es de tipo UNIVERSIDAD. */
    public boolean crearRaiz(UnidadAcademica unidad) {
        if (raiz != null || unidad == null || unidad.getTipo() != TipoUnidad.UNIVERSIDAD) {
            return false;
        }
        raiz = new NodoUnidad(unidad);
        return true;
    }

    /**
     * Agrega una unidad bajo el padre indicado.
     * Rechaza: datos nulos, árbol sin raíz, código repetido, padre inexistente
     * o relación padre–hijo inválida. Como cada código es único y el nodo nuevo
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
        return padre.agregarHijo(new NodoUnidad(unidad)); // valida la jerarquía
    }

    /** Busca por código (sin importar espacios ni mayúsculas). Devuelve null si no existe. */
    public NodoUnidad buscar(String codigo) {
        String clave = UnidadAcademica.normalizar(codigo);
        if (clave.isEmpty()) {
            return null;
        }
        return buscar(raiz, clave);
    }

    // Caso base: nodo nulo (no está) o código encontrado. Si no, prueba con cada hijo.
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

    /** Nivel (profundidad en aristas) de una unidad; la raíz está en el nivel 0. -1 si no existe. */
    public int nivel(String codigo) {
        throw new UnsupportedOperationException("Pendiente: nivel - Joel Tisalema");
    }

    // Caso base: nodo nulo (-1) o código encontrado (devuelve la profundidad acumulada).
    private int nivel(NodoUnidad nodo, String clave, int profundidad) {
        throw new UnsupportedOperationException("Pendiente: nivel - Joel Tisalema");
    }

    /** Grado = cantidad de hijos directos. -1 si la unidad no existe. */
    public int grado(String codigo) {
        throw new UnsupportedOperationException("Pendiente: grado - Joel Tisalema");
    }

    /** Altura en aristas: árbol vacío = -1, solo la raíz = 0. O(N). */
    public int altura() {
        throw new UnsupportedOperationException("Pendiente: altura - Joel Tisalema");
    }

    // Caso base: nodo nulo vale -1, así una hoja vale 1 + (-1) = 0.
    private int altura(NodoUnidad nodo) {
        throw new UnsupportedOperationException("Pendiente: altura - Joel Tisalema");
    }

    /** Número total de unidades. O(N). */
    public int contarNodos() {
        throw new UnsupportedOperationException("Pendiente: contarNodos - Joel Tisalema");
    }

    private int contarNodos(NodoUnidad nodo) {
        throw new UnsupportedOperationException("Pendiente: contarNodos - Joel Tisalema");
    }

    /** Número de hojas (unidades sin hijos). O(N). */
    public int contarHojas() {
        throw new UnsupportedOperationException("Pendiente: contarHojas - Joel Tisalema");
    }

    private int contarHojas(NodoUnidad nodo) {
        throw new UnsupportedOperationException("Pendiente: contarHojas - Joel Tisalema");
    }

    /** Jerarquía en preorden con dos espacios de sangría por nivel. */
    public String imprimirJerarquia() {
        throw new UnsupportedOperationException("Pendiente: imprimirJerarquia - Joel Tisalema");
    }

    private void imprimir(NodoUnidad nodo, int nivel, StringBuilder sb) {
        throw new UnsupportedOperationException("Pendiente: imprimir - Joel Tisalema");
    }

    /**
     * Eliminación segura: solo se borra una unidad hoja que no sea la raíz.
     * Devuelve false si no existe, si es la raíz (UTA) o si tiene descendientes.
     */
    public boolean eliminar(String codigo) {
        throw new UnsupportedOperationException("Pendiente: eliminar - Joel Tisalema");
    }

    // Devuelve el padre del nodo con esa clave, o null si no está en este subárbol.
    private NodoUnidad buscarPadre(NodoUnidad nodo, String clave) {
        throw new UnsupportedOperationException("Pendiente: buscarPadre - Joel Tisalema");
    }

    /**
     * Construye el árbol binario equivalente (hijo izquierdo / hermano derecho).
     * izquierdo = primer hijo; derecho = siguiente hermano. No modifica el árbol general.
     */
    public NodoBinarioUnidad transformarABinario() {
        throw new UnsupportedOperationException("Pendiente: transformarABinario - Joel Tisalema");
    }

    // Caso base: no quedan hermanos en la lista. O(N): cada unidad se convierte una vez.
    private NodoBinarioUnidad convertir(List<NodoUnidad> hermanos, int indice) {
        throw new UnsupportedOperationException("Pendiente: convertir - Joel Tisalema");
    }

    /** Texto del árbol binario obtenido, para mostrarlo en consola. */
    public String representarBinario() {
        throw new UnsupportedOperationException("Pendiente: representarBinario - Joel Tisalema");
    }

    private void representar(NodoBinarioUnidad nodo, int nivel, String etiqueta, StringBuilder sb) {
        throw new UnsupportedOperationException("Pendiente: representar - Joel Tisalema");
    }
}
