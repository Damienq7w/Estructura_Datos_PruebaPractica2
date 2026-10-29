package estructuras;

import java.util.List;

import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/**
 * Arbol general de la jerarquia academica (UTA → facultades → carreras → paralelos).
 * Todas las consultas son recursivas. Como el arbol general no esta ordenado,
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
     * Rechaza: datos nulos, arbol sin raiz, codigo repetido, padre inexistente
     * o relacion padre–hijo invalida. Como cada codigo es unico y el nodo nuevo
     * siempre se cuelga de uno existente, no se pueden formar ciclos.
     */
    public boolean agregar(String codigoPadre, UnidadAcademica unidad) {
        if (unidad == null || raiz == null) {
            return false;
        }
        if (existe(unidad.getCodigo())) {
            return false; // codigo unico
        }
        NodoUnidad padre = buscar(codigoPadre);
        if (padre == null) {
            return false; // padre inexistente
        }
        return padre.agregarHijo(new NodoUnidad(unidad)); // valida la jerarquia
    }

    /** Busca por codigo (sin importar espacios ni mayusculas). Devuelve null si no existe. */
    public NodoUnidad buscar(String codigo) {
        String clave = UnidadAcademica.normalizar(codigo);
        if (clave.isEmpty()) {
            return null;
        }
        return buscar(raiz, clave);
    }

    // Caso base: nodo nulo (no esta) o codigo encontrado. Si no, prueba con cada hijo.
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

    // Caso base: nodo nulo vale -1, asi una hoja vale 1 + (-1) = 0.
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

    /** Numero de hojas (unidades sin hijos). O(N). */
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
            return "(arbol general vacio)";
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

    /**
     * Eliminacion segura: solo se borra una unidad hoja que no sea la raiz.
     * Devuelve false si no existe, si es la raiz (UTA) o si tiene descendientes.
     */
    public boolean eliminar(String codigo) {
        String clave = UnidadAcademica.normalizar(codigo);
        if (raiz == null || clave.isEmpty() || raiz.getCodigo().equals(clave)) {
            return false; // la raiz no se elimina
        }
        NodoUnidad padre = buscarPadre(raiz, clave);
        if (padre == null) {
            return false; // no existe
        }
        for (NodoUnidad hijo : padre.getHijos()) {
            if (hijo.getCodigo().equals(clave)) {
                if (!hijo.esHoja()) {
                    return false; // tiene descendientes: no se borra
                }
                return padre.eliminarHijo(hijo);
            }
        }
        return false;
    }

    // Devuelve el padre del nodo con esa clave, o null si no esta en este subarbol.
    private NodoUnidad buscarPadre(NodoUnidad nodo, String clave) {
        for (NodoUnidad hijo : nodo.getHijos()) {
            if (hijo.getCodigo().equals(clave)) {
                return nodo;
            }
            NodoUnidad padre = buscarPadre(hijo, clave);
            if (padre != null) {
                return padre;
            }
        }
        return null;
    }

    /**
     * Construye el arbol binario equivalente (hijo izquierdo / hermano derecho).
     * izquierdo = primer hijo; derecho = siguiente hermano. No modifica el arbol general.
     */
    public NodoBinarioUnidad transformarABinario() {
        if (raiz == null) {
            return null;
        }
        NodoBinarioUnidad binaria = new NodoBinarioUnidad(raiz.getDato());
        binaria.setIzquierdo(convertir(raiz.getHijos(), 0));
        return binaria; // la raiz no tiene hermanos
    }

    // Caso base: no quedan hermanos en la lista. O(N): cada unidad se convierte una vez.
    private NodoBinarioUnidad convertir(List<NodoUnidad> hermanos, int indice) {
        if (indice >= hermanos.size()) {
            return null;
        }
        NodoUnidad actual = hermanos.get(indice);
        NodoBinarioUnidad nodo = new NodoBinarioUnidad(actual.getDato());
        nodo.setIzquierdo(convertir(actual.getHijos(), 0));   // primer hijo
        nodo.setDerecho(convertir(hermanos, indice + 1));       // siguiente hermano
        return nodo;
    }

    /** Texto del arbol binario obtenido, para mostrarlo en consola. */
    public String representarBinario() {
        NodoBinarioUnidad binaria = transformarABinario();
        if (binaria == null) {
            return "(arbol general vacio)";
        }
        StringBuilder sb = new StringBuilder();
        representar(binaria, 0, "Raiz", sb);
        return sb.toString();
    }

    private void representar(NodoBinarioUnidad nodo, int nivel, String etiqueta, StringBuilder sb) {
        if (nodo == null) {
            return;
        }
        sb.append("  ".repeat(nivel)).append(etiqueta).append(": ")
          .append(nodo.getDato().getCodigo()).append('\n');
        representar(nodo.getIzquierdo(), nivel + 1, "Izq (primer hijo)", sb);
        representar(nodo.getDerecho(), nivel + 1, "Der (hermano)", sb);
    }
}