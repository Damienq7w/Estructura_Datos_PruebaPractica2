package modelo;

/**
 * Niveles de la jerarquia academica.
 * Regla: un hijo solo puede ser del tipo inmediatamente inferior a su padre.
 */
public enum TipoUnidad {
    UNIVERSIDAD,
    FACULTAD,
    CARRERA,
    PARALELO;

    
    public boolean puedeTenerHijo(TipoUnidad tipoHijo) {
        return tipoHijo != null && tipoHijo.ordinal() == this.ordinal() + 1;
    }
}
