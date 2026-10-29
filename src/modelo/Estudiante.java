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
        if (matricula <= 0) {
            throw new IllegalArgumentException("La matrícula debe ser un entero positivo.");
        }
        validarNombre(nombre);
        validarPromedio(promedio);
        this.matricula = matricula;
        this.nombre = nombre.trim();
        this.promedio = promedio;
    }

    public static void validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre del estudiante es obligatorio.");
        }
    }

    public static void validarPromedio(double promedio) {
        if (Double.isNaN(promedio) || promedio < 0 || promedio > 10) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 10.");
        }
    }

    public int getMatricula() {
        return matricula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        validarNombre(nombre);
        this.nombre = nombre.trim();
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        validarPromedio(promedio);
        this.promedio = promedio;
    }

    @Override
    public String toString() {
        return matricula + " - " + nombre + " (" + promedio + ")";
    }
}
