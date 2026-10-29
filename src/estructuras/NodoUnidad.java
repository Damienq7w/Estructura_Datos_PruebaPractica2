package estructuras;

import java.util.ArrayList;
import java.util.List;
import modelo.UnidadAcademica;

/** Nodo del árbol general: un dato y una lista con cualquier cantidad de hijos. */
public class NodoUnidad {

    private final UnidadAcademica dato;
    private final ArrayList<NodoUnidad> hijos;

    public NodoUnidad(UnidadAcademica dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El nodo necesita una unidad academica.");
        }
        this.dato = dato;
        this.hijos = new ArrayList<>();
    }

    public UnidadAcademica getDato() {
        return dato;
    }

    public String getCodigo() {
        return dato.getCodigo();
    }

    /** Lista de solo lectura: los hijos se cambian con agregarHijo / eliminarHijo. */
    public List<NodoUnidad> getHijos() {
        return java.util.Collections.unmodifiableList(hijos);
    }

    /** Agrega el hijo solo si su tipo es el inmediatamente inferior (regla padre–hijo). */
    public boolean agregarHijo(NodoUnidad hijo) {
        if (hijo == null || !dato.getTipo().puedeTenerHijo(hijo.getDato().getTipo())) {
            return false;
        }
        hijos.add(hijo);
        return true;
    }

    public boolean eliminarHijo(NodoUnidad hijo) {
        return hijos.remove(hijo);
    }

    public boolean esHoja() {
        return hijos.isEmpty();
    }

    @Override
    public String toString() {
        return dato.toString();
    }
}
