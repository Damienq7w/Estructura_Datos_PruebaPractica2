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

/** Pruebas de modificar sin cambiar la clave y de los tres casos de eliminacion. Responsable: Luis. */
public final class PruebasABBEliminacion {

    private PruebasABBEliminacion() {
    }

    public static void ejecutar() {
        System.out.println("== ABB: modificar y eliminar (Luis) ==");

        caso("Modificar 35: cambia nombre y promedio, la matricula y la posicion se conservan", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            List<Integer> antes = abb.recorrerMatriculas(TipoRecorrido.PREORDEN);
            verdadero(abb.modificar(35, "Hugo Sanchez Mod.", 9.6), "35 existe");
            Estudiante e = abb.buscar(35);
            igual("Hugo Sanchez Mod.", e.getNombre(), "nombre nuevo");
            igual(9.6, e.getPromedio(), "promedio nuevo");
            igual(antes, abb.recorrerMatriculas(TipoRecorrido.PREORDEN), "la forma del arbol no cambia");
        });

        caso("Modificar con datos invalidos o matricula inexistente no cambia nada", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            falso(abb.modificar(99, "Nadie", 5.0), "99 no existe");
            lanza(IllegalArgumentException.class, () -> abb.modificar(35, "Nombre valido", 15.0),
                    "promedio fuera de rango");
            igual("Hugo Sánchez", abb.buscar(35).getNombre(), "el nombre no se cambio a medias");
        });

        caso("Inorden antes y despues de eliminar 20 (hoja), 60 (un hijo) y 70 (dos hijos)", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            igual(List.of(20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85),
                    abb.recorrerMatriculas(TipoRecorrido.INORDEN), "inorden antes");
            verdadero(abb.eliminar(20), "hoja");
            verdadero(abb.eliminar(60), "un hijo (65)");
            verdadero(abb.eliminar(70), "dos hijos: sube el sucesor inorden 75");
            igual(List.of(30, 35, 40, 45, 50, 65, 75, 80, 85),
                    abb.recorrerMatriculas(TipoRecorrido.INORDEN), "inorden despues");
            igual(9, abb.contar(), "9 nodos");
            igual(List.of(50, 30, 40, 35, 45, 75, 65, 80, 85),
                    abb.recorrerMatriculas(TipoRecorrido.PREORDEN), "el 75 ocupa el lugar del 70");
        });

        caso("Eliminar inexistente, eliminar la raiz y vaciar el arbol", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            falso(abb.eliminar(99), "99 no existe");
            igual(12, abb.contar(), "sin cambios");
            verdadero(abb.eliminar(50), "raiz con dos hijos");
            igual(60, abb.getRaiz().getMatricula(), "la nueva raiz es el sucesor 60");
            for (int m : DatosPrueba.MATRICULAS) {
                abb.eliminar(m);
            }
            verdadero(abb.estaVacio(), "arbol vacio");
            falso(abb.eliminar(50), "eliminar en vacio no falla");
        });
    }

    public static void main(String[] args) {
        ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}