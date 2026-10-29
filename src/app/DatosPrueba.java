package app;

import estructuras.ArbolGeneral;
import estructuras.ArbolEstudiantes;
import modelo.Estudiante;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/** Datos de entrada obligatorios del enunciado. */
public final class DatosPrueba {

    private DatosPrueba() {
        throw new UnsupportedOperationException("Pendiente: DatosPrueba - Damian Cunalata");
    }

    /** UTA → FISEI, FCHE, FCS; FISEI → SOFTWARE, TI; SOFTWARE → 3A, 3B; TI → 4A. */
    public static ArbolGeneral cargarJerarquia() {
        throw new UnsupportedOperationException("Pendiente: cargarJerarquia - Damian Cunalata");
    }

    /** Orden de inserción obligatorio del enunciado. */
    public static final int[] MATRICULAS = {50, 30, 70, 20, 40, 60, 80, 35, 45, 65, 75, 85};

    private static final String[] NOMBRES = {
        "Ana López", "Bruno Díaz", "Carla Mena", "Diego Paredes", "Elena Ruiz", "Fabián Toro",
        "Gabriela Vaca", "Hugo Sánchez", "Inés Castro", "Jorge Andrade", "Karen Molina", "Luis Ortega"
    };

    private static final double[] PROMEDIOS = {8.5, 7.2, 9.1, 6.8, 8.0, 7.5, 9.4, 6.5, 8.8, 7.9, 9.0, 8.3};

    /** ABB con las 12 matrículas insertadas en el orden pedido. */
    public static ArbolEstudiantes cargarEstudiantes() {
        throw new UnsupportedOperationException("Pendiente: cargarEstudiantes - Mateo Chalco");
    }
}
