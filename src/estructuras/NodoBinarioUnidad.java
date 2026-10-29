package estructuras;

import modelo.UnidadAcademica;

/**
 * Nodo del árbol binario que resulta de transformar el árbol general.
 * Ojo: el enlace derecho NO es un hijo, es el siguiente hermano en el árbol original.
 */
public class NodoBinarioUnidad {

    private final UnidadAcademica dato;
    private NodoBinarioUnidad izquierdo; // primer hijo
    private NodoBinarioUnidad derecho;   // siguiente hermano

    public NodoBinarioUnidad(UnidadAcademica dato) {
        throw new UnsupportedOperationException("Pendiente: NodoBinarioUnidad - Joel Tisalema");
    }

    public UnidadAcademica getDato() {
        throw new UnsupportedOperationException("Pendiente: getDato - Joel Tisalema");
    }

    public NodoBinarioUnidad getIzquierdo() {
        throw new UnsupportedOperationException("Pendiente: getIzquierdo - Joel Tisalema");
    }

    public void setIzquierdo(NodoBinarioUnidad izquierdo) {
        throw new UnsupportedOperationException("Pendiente: setIzquierdo - Joel Tisalema");
    }

    public NodoBinarioUnidad getDerecho() {
        throw new UnsupportedOperationException("Pendiente: getDerecho - Joel Tisalema");
    }

    public void setDerecho(NodoBinarioUnidad derecho) {
        throw new UnsupportedOperationException("Pendiente: setDerecho - Joel Tisalema");
    }
}
