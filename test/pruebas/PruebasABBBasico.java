package pruebas;

import static pruebas.Verificador.caso;
import static pruebas.Verificador.falso;
import static pruebas.Verificador.igual;
import static pruebas.Verificador.lanza;
import static pruebas.Verificador.verdadero;

import java.util.List;

import app.DatosPrueba;
import estructuras.ArbolEstudiantes;
import estructuras.TipoRecorrido;
import modelo.Estudiante;

/** Pruebas de inserción, búsqueda y recorridos del ABB. Responsable: Mateo. */
public final class PruebasABBBasico {

    private PruebasABBBasico() {
    }

    public static void ejecutar() {
        System.out.println("== ABB: inserción, búsqueda y recorridos (Mateo) ==");

        caso("Aceptación: inorden tras 12 inserciones sale ordenado", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            igual(List.of(20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85),
                    abb.recorrerMatriculas(TipoRecorrido.INORDEN), "inorden");
            igual(12, abb.contar(), "total");
        });

        caso("Preorden y posorden de los datos del enunciado", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            igual(List.of(50, 30, 20, 40, 35, 45, 70, 60, 65, 80, 75, 85),
                    abb.recorrerMatriculas(TipoRecorrido.PREORDEN), "preorden");
            igual(List.of(20, 35, 45, 40, 30, 65, 60, 75, 85, 80, 70, 50),
                    abb.recorrerMatriculas(TipoRecorrido.POSORDEN), "posorden");
        });

        caso("Aceptación: insertar de nuevo 40 se rechaza y el conteo sigue en 12", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            falso(abb.insertar(new Estudiante(40, "Duplicado", 5.0)), "duplicado rechazado");
            igual(12, abb.contar(), "conteo preservado");
            igual("Elena Ruiz", abb.buscar(40).getNombre(), "el dato original no cambia");
        });

        caso("Buscar existente y no existente, contando comparaciones", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            verdadero(abb.buscar(45) != null, "45 existe");
            igual(4, abb.getUltimasComparaciones(), "camino 50 -> 30 -> 40 -> 45");
            igual(null, abb.buscar(99), "99 no existe");
            igual(4, abb.getUltimasComparaciones(), "camino 50 -> 70 -> 80 -> 85 -> null");
        });

        caso("Árbol vacío: recorridos vacíos, buscar null y datos inválidos rechazados", () -> {
            ArbolEstudiantes vacio = new ArbolEstudiantes();
            verdadero(vacio.recorrer(TipoRecorrido.INORDEN).isEmpty(), "inorden vacío");
            igual(null, vacio.buscar(10), "buscar en vacío");
            igual(0, vacio.contar(), "contar vacío");
            lanza(IllegalArgumentException.class, () -> vacio.insertar(null), "insertar null");
            lanza(IllegalArgumentException.class, () -> new Estudiante(1, "X", 11), "promedio > 10");
            lanza(IllegalArgumentException.class, () -> new Estudiante(-5, "X", 8), "matrícula negativa");
        });
    }

    public static void main(String[] args) {
        ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}
