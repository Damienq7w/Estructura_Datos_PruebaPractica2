package modelo;

/**
 * Niveles de la jerarquía académica.
 * Regla: un hijo solo puede ser del tipo inmediatamente inferior a su padre.
 */
public enum TipoUnidad {
    UNIVERSIDAD,
    FACULTAD,
    CARRERA,
    PARALELO;

    /** true si una unidad de este tipo puede tener como hijo directo al tipo indicado. */
    public boolean puedeTenerHijo(TipoUnidad tipoHijo) {
        throw new UnsupportedOperationException("Pendiente: puedeTenerHijo - Damian Cunalata");
    }
}
