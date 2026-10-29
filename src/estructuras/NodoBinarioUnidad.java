package estructuras;

import modelo.UnidadAcademica;

/**
 * Nodo del arbol binario que resulta de transformar el arbol general.
 * Ojo: el enlace derecho NO es un hijo, es el siguiente hermano en el arbol original.
 */
public class NodoBinarioUnidad {

    private final UnidadAcademica dato;
    private NodoBinarioUnidad izquierdo; // primer hijo
    private NodoBinarioUnidad derecho;   // siguiente hermano

    public NodoBinarioUnidad(UnidadAcademica dato) {
        this.dato = dato;
    }

    public UnidadAcademica getDato() {
        return dato;
    }

    public NodoBinarioUnidad getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoBinarioUnidad izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoBinarioUnidad getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoBinarioUnidad derecho) {
        this.derecho = derecho;
    }
}