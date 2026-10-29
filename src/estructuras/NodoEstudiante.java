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

        throw new UnsupportedOperationException("Pendiente: NodoEstudiante - Mateo Chalco");
    }

    public Estudiante getDato() {
        throw new UnsupportedOperationException("Pendiente: getDato - Mateo Chalco");
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
        throw new UnsupportedOperationException("Pendiente: setDato - Mateo Chalco");
    }

    public int getMatricula() {
        throw new UnsupportedOperationException("Pendiente: getMatricula - Mateo Chalco");
    }

    public NodoEstudiante getIzquierdo() {
        throw new UnsupportedOperationException("Pendiente: getIzquierdo - Mateo Chalco");
    }

    public void setIzquierdo(NodoEstudiante izquierdo) {
        throw new UnsupportedOperationException("Pendiente: setIzquierdo - Mateo Chalco");
    }

    public NodoEstudiante getDerecho() {
        throw new UnsupportedOperationException("Pendiente: getDerecho - Mateo Chalco");
    }

    public void setDerecho(NodoEstudiante derecho) {
        throw new UnsupportedOperationException("Pendiente: setDerecho - Mateo Chalco");
    }

    public boolean esHoja() {
        throw new UnsupportedOperationException("Pendiente: esHoja - Mateo Chalco");
    }
}
