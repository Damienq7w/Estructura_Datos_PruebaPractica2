package modelo;

/**
 * Dato de cada nodo del ABB.
 * La matrícula es la clave del árbol: es final y no tiene setter,
 * porque cambiarla sin reposicionar el nodo rompería el orden del ABB.
 */
public class Estudiante {

    private final int matricula;
    private String nombre;
    private double promedio;

    public Estudiante(int matricula, String nombre, double promedio) {
        throw new UnsupportedOperationException("Pendiente: Estudiante - Damian Cunalata");
    }

    public static void validarNombre(String nombre) {
        throw new UnsupportedOperationException("Pendiente: validarNombre - Damian Cunalata");
    }

    public static void validarPromedio(double promedio) {
        throw new UnsupportedOperationException("Pendiente: validarPromedio - Damian Cunalata");
    }

    public int getMatricula() {
        throw new UnsupportedOperationException("Pendiente: getMatricula - Damian Cunalata");
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Pendiente: getNombre - Damian Cunalata");
    }

    public void setNombre(String nombre) {
        throw new UnsupportedOperationException("Pendiente: setNombre - Damian Cunalata");
    }

    public double getPromedio() {
        throw new UnsupportedOperationException("Pendiente: getPromedio - Damian Cunalata");
    }

    public void setPromedio(double promedio) {
        throw new UnsupportedOperationException("Pendiente: setPromedio - Damian Cunalata");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Pendiente: toString - Damian Cunalata");
    }
}
