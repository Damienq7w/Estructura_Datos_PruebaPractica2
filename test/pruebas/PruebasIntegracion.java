package pruebas;

import static pruebas.Verificador.caso;
import static pruebas.Verificador.igual;
import static pruebas.Verificador.verdadero;

import java.util.List;

import app.DatosPrueba;
import estructuras.ArbolEstudiantes;
import estructuras.ArbolGeneral;
import estructuras.TipoRecorrido;
import modelo.Estudiante;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;
import servicio.Auditoria;

/** Pruebas de integración y del cambio en vivo que puede pedir el docente. Responsable: Josue. */
public final class PruebasIntegracion {

    private PruebasIntegracion() {
    }

    public static void ejecutar() {
        System.out.println("== Integración y auditoría (Josue) ==");

        caso("Auditoría con los datos del enunciado", () -> {
            String r = Auditoria.generarReporte(DatosPrueba.cargarJerarquia(), DatosPrueba.cargarEstudiantes());
            verdadero(r.contains("Total de unidades : 9"), "unidades");
            verdadero(r.contains("Hojas             : 5"), "hojas");
            verdadero(r.contains("Altura general    : 3"), "altura general");
            verdadero(r.contains("Matrícula menor   : 20"), "menor");
            verdadero(r.contains("Matrícula mayor   : 85"), "mayor");
            verdadero(r.contains("VÁLIDA"), "invariante");
        });

        caso("Auditoría con árboles vacíos no lanza excepciones", () -> {
            String r = Auditoria.generarReporte(new ArbolGeneral(), new ArbolEstudiantes());
            verdadero(r.contains("Árbol general vacío"), "general vacío");
            verdadero(r.contains("ABB vacío"), "ABB vacío");
        });

        caso("Cambio en vivo: dos matrículas y un nodo académico nuevos (sin respuestas codificadas)", () -> {
            ArbolGeneral g = DatosPrueba.cargarJerarquia();
            verdadero(g.agregar("FCS", new UnidadAcademica("MEDICINA", "Medicina", TipoUnidad.CARRERA)),
                    "nuevo nodo");
            igual(10, g.contarNodos(), "10 unidades");
            igual(5, g.contarHojas(), "FCS deja de ser hoja y MEDICINA lo es");
            igual(1, g.grado("FCS"), "grado FCS");
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            abb.insertar(new Estudiante(10, "Nueva Uno", 9.0));
            abb.insertar(new Estudiante(90, "Nueva Dos", 6.0));
            igual(List.of(10, 20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85, 90),
                    abb.recorrerMatriculas(TipoRecorrido.INORDEN), "inorden con las nuevas");
            igual(4, abb.altura(), "la altura crece a 4");
            igual(10, abb.minimo().getMatricula(), "nuevo mínimo");
            igual(90, abb.maximo().getMatricula(), "nuevo máximo");
            verdadero(abb.esABBValido(), "invariante");
        });
    }

    public static void main(String[] args) {
        ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}
