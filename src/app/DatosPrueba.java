package app;

import estructuras.ArbolGeneral;
import estructuras.ArbolEstudiantes;
import modelo.Estudiante;
import estructuras.ArbolEstudiantes;
import estructuras.ArbolGeneral;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/** Datos de entrada obligatorios del enunciado. */
public final class DatosPrueba {

    private DatosPrueba() {
        throw new UnsupportedOperationException("Pendiente: DatosPrueba - Damian Cunalata");
    }

    /** UTA → FISEI, FCHE, FCS; FISEI → SOFTWARE, TI; SOFTWARE → 3A, 3B; TI → 4A. */
    public static ArbolGeneral cargarJerarquia() {
        ArbolGeneral arbol = new ArbolGeneral();
        arbol.crearRaiz(new UnidadAcademica("UTA", "Universidad Técnica de Ambato", TipoUnidad.UNIVERSIDAD));
        arbol.agregar("UTA", new UnidadAcademica("FISEI", "Fac. Ing. Sistemas, Electrónica e Industrial", TipoUnidad.FACULTAD));
        arbol.agregar("UTA", new UnidadAcademica("FCHE", "Fac. Ciencias Humanas y de la Educación", TipoUnidad.FACULTAD));
        arbol.agregar("UTA", new UnidadAcademica("FCS", "Fac. Ciencias de la Salud", TipoUnidad.FACULTAD));
        arbol.agregar("FISEI", new UnidadAcademica("SOFTWARE", "Carrera de Software", TipoUnidad.CARRERA));
        arbol.agregar("FISEI", new UnidadAcademica("TI", "Carrera de Tecnologías de la Información", TipoUnidad.CARRERA));
        arbol.agregar("SOFTWARE", new UnidadAcademica("3A", "Software Tercero A", TipoUnidad.PARALELO));
        arbol.agregar("SOFTWARE", new UnidadAcademica("3B", "Software Tercero B", TipoUnidad.PARALELO));
        arbol.agregar("TI", new UnidadAcademica("4A", "TI Cuarto A", TipoUnidad.PARALELO));
        return arbol;
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
        ArbolEstudiantes abb = new ArbolEstudiantes();
        for (int i = 0; i < MATRICULAS.length; i++) {
            abb.insertar(new Estudiante(MATRICULAS[i], NOMBRES[i], PROMEDIOS[i]));
        }
        return abb;
        throw new UnsupportedOperationException("Pendiente: cargarEstudiantes - Mateo Chalco");
    }
}
