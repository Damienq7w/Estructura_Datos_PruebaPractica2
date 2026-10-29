package modelo;

/**
 * Dato de cada nodo del árbol general (UTA, facultad, carrera o paralelo).
 * El código es la clave única: se guarda normalizado (sin espacios y en mayúsculas).
 */
public class UnidadAcademica {

    private final String codigo;
    private String nombre;
    private final TipoUnidad tipo;

    public UnidadAcademica(String codigo, String nombre, TipoUnidad tipo) {
        throw new UnsupportedOperationException("Pendiente: UnidadAcademica - Damian Cunalata");
    }

    private static String validarNombre(String nombre) {
        throw new UnsupportedOperationException("Pendiente: validarNombre - Damian Cunalata");
    }

    /** Normaliza un código para compararlo: quita espacios y pasa a mayúsculas. */
    public static String normalizar(String codigo) {
        throw new UnsupportedOperationException("Pendiente: normalizar - Damian Cunalata");
    }

    public String getCodigo() {
        throw new UnsupportedOperationException("Pendiente: getCodigo - Damian Cunalata");
    }

    public String getNombre() {
        throw new UnsupportedOperationException("Pendiente: getNombre - Damian Cunalata");
    }

    public void setNombre(String nombre) {
        throw new UnsupportedOperationException("Pendiente: setNombre - Damian Cunalata");
    }

    public TipoUnidad getTipo() {
        throw new UnsupportedOperationException("Pendiente: getTipo - Damian Cunalata");
    }

    @Override
    public String toString() {
        throw new UnsupportedOperationException("Pendiente: toString - Damian Cunalata");
    }
}
