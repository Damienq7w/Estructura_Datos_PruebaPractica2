package estructuras;

import modelo.Estudiante;

/** Nodo del ABB: un estudiante y los enlaces izquierdo (menores) y derecho (mayores). */
public class NodoEstudiante {

    private Estudiante dato;
    private NodoEstudiante izquierdo;
    private NodoEstudiante derecho;

    public NodoEstudiante(Estudiante dato) {
        if (dato == null) {
            throw new IllegalArgumentException("El nodo necesita un estudiante.");
        }
        this.dato = dato;
    }

    public Estudiante getDato() {
        return dato;
    }

    /** Se usa al eliminar con dos hijos: el nodo recibe el dato del sucesor inorden. */
    public void setDato(Estudiante dato) {
        this.dato = dato;
    }

    public int getMatricula() {
        return dato.getMatricula();
    }

    public NodoEstudiante getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(NodoEstudiante izquierdo) {
        this.izquierdo = izquierdo;
    }

    public NodoEstudiante getDerecho() {
        return derecho;
    }

    public void setDerecho(NodoEstudiante derecho) {
        this.derecho = derecho;
    }

    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}
