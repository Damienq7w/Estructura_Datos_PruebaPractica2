package estructuras;

import java.util.ArrayList;
import java.util.List;

import modelo.UnidadAcademica;

/** Nodo del árbol general: un dato y una lista con cualquier cantidad de hijos. */
public class NodoUnidad {

    private final UnidadAcademica dato;
    private final ArrayList<NodoUnidad> hijos;

    public NodoUnidad(UnidadAcademica dato) {
        throw new UnsupportedOperationException("Pendiente: NodoUnidad - Damian Cunalata");
    }

    public UnidadAcademica getDato() {
        throw new UnsupportedOperationException("Pendiente: getDato - Damian Cunalata");
    }

    public String getCodigo() {
        throw new UnsupportedOperationException("Pendiente: getCodigo - Damian Cunalata");
    }

    /** Lista de solo lectura: los hijos se cambian con agregarHijo / eliminarHijo. */
    public List<NodoUnidad> getHijos() {
        throw new UnsupportedOperationException("Pendiente: getHijos - Damian Cunalata");
    }

    /** Agrega el hijo solo si su tipo es el inmediatamente inferior (regla padre–hijo). */
    public boolean agregarHijo(NodoUnidad hijo) {
        throw new UnsupportedOperationException("Pendiente: agregarHijo - Damian Cunalata");
    }

    public boolean eliminarHijo(NodoUnidad hijo) {
        throw new UnsupportedOperationException("Pendiente: eliminarHijo - Damian Cunalata");
    }

    public boolean esHoja() {
        throw new UnsupportedOperationException("Pendiente: esHoja - Damian Cunalata");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Pendiente: toString - Damian Cunalata");
    }
}
