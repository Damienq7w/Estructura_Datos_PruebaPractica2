package pruebas;

import static pruebas.Verificador.caso;
import static pruebas.Verificador.falso;
import static pruebas.Verificador.igual;
import static pruebas.Verificador.verdadero;

import app.DatosPrueba;
import estructuras.ArbolGeneral;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/** Pruebas de la base del árbol general: raíz única, agregar y buscar. Responsable: Damian. */
public final class PruebasArbolGeneralBase {

    private PruebasArbolGeneralBase() {
    }

    public static void ejecutar() {
        System.out.println("== Árbol general: base (Damian) ==");

        caso("Jerarquía válida: se cargan las 9 unidades y se encuentran por código", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            String[] codigos = {"UTA", "FISEI", "FCHE", "FCS", "SOFTWARE", "TI", "3A", "3B", "4A"};
            for (String c : codigos) {
                verdadero(a.existe(c), "debe existir " + c);
            }
            verdadero(a.buscar(" software ") != null, "la búsqueda ignora espacios y mayúsculas");
        });

        caso("Jerarquía inválida: una FACULTAD no puede colgar de una CARRERA", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            falso(a.agregar("SOFTWARE", new UnidadAcademica("FX", "Facultad X", TipoUnidad.FACULTAD)),
                    "debe rechazar la relación padre–hijo inválida");
            falso(a.existe("FX"), "la unidad rechazada no debe quedar en el árbol");
        });

        caso("Padre inexistente: se rechaza la unidad", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            falso(a.agregar("NOEXISTE", new UnidadAcademica("5A", "Paralelo 5A", TipoUnidad.PARALELO)),
                    "no debe agregar sin padre");
        });

        caso("Código duplicado: se rechaza aunque el padre sea válido", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            falso(a.agregar("TI", new UnidadAcademica("3a", "Repetido", TipoUnidad.PARALELO)),
                    "3A ya existe bajo SOFTWARE");
        });

        caso("Raíz única: no se acepta una segunda raíz ni una raíz que no sea UNIVERSIDAD", () -> {
            ArbolGeneral vacio = new ArbolGeneral();
            falso(vacio.crearRaiz(new UnidadAcademica("FISEI", "Facultad", TipoUnidad.FACULTAD)),
                    "la raíz debe ser UNIVERSIDAD");
            verdadero(vacio.crearRaiz(new UnidadAcademica("UTA", "UTA", TipoUnidad.UNIVERSIDAD)),
                    "primera raíz aceptada");
            falso(vacio.crearRaiz(new UnidadAcademica("UTA2", "Otra", TipoUnidad.UNIVERSIDAD)),
                    "segunda raíz rechazada");
        });

        caso("Árbol vacío: buscar y agregar no lanzan NullPointerException", () -> {
            ArbolGeneral vacio = new ArbolGeneral();
            igual(null, vacio.buscar("UTA"), "buscar en vacío devuelve null");
            falso(vacio.agregar("UTA", new UnidadAcademica("FISEI", "F", TipoUnidad.FACULTAD)),
                    "agregar en vacío devuelve false");
            verdadero(vacio.estaVacio(), "sigue vacío");
        });
    }

    public static void main(String[] args) {
        ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}
