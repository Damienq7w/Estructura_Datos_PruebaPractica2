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
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException("El código de la unidad es obligatorio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("El tipo de la unidad es obligatorio.");
        }
        this.codigo = normalizar(codigo);
        this.tipo = tipo;
        this.nombre = validarNombre(nombre);
    }

    private static String validarNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre de la unidad es obligatorio.");
        }
        return nombre.trim();
    }

    
    public static String normalizar(String codigo) {
        return codigo == null ? "" : codigo.trim().toUpperCase();
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = validarNombre(nombre);
    }

    public TipoUnidad getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return codigo + " - " + nombre + " [" + tipo + "]";
    }
}
