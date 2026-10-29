package pruebas;

import static pruebas.Verificador.caso;
import static pruebas.Verificador.cercano;
import static pruebas.Verificador.falso;
import static pruebas.Verificador.igual;
import static pruebas.Verificador.verdadero;

import app.DatosPrueba;
import estructuras.ArbolEstudiantes;
import estructuras.NodoEstudiante;
import estructuras.TipoRecorrido;
import modelo.Estudiante;
import servicio.AnalisisBusqueda;

/**
 * Pruebas de metricas del ABB, invariante y analisis de busqueda. Responsable:
 * Sara.
 */
public final class PruebasABBMetricas {

    private PruebasABBMetricas() {
    }

    public static void ejecutar() {
        System.out.println("== ABB: métricas y análisis (Sara) ==");

        caso("Métricas con los 12 estudiantes: altura 3, 6 hojas, mínimo 20, máximo 85", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            igual(3, abb.altura(), "altura");
            igual(6, abb.contarHojas(), "hojas: 20, 35, 45, 65, 75, 85");
            igual(20, abb.minimo().getMatricula(), "mínimo");
            igual(85, abb.maximo().getMatricula(), "máximo");
        });

        caso("Consistencia del promedio: coincide con el cálculo hecho a mano y se actualiza", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            double suma = 0;
            for (Estudiante e : abb.recorrer(TipoRecorrido.INORDEN)) {
                suma += e.getPromedio();
            }
            cercano(suma / abb.contar(), abb.promedioGeneral(), 1e-9, "promedio vs recorrido");
            cercano(97.0 / 12, abb.promedioGeneral(), 1e-9, "97.0 / 12");
            abb.modificar(35, "Hugo Sánchez", 9.5); // 6.5 -> 9.5 suma 3 puntos
            cercano(100.0 / 12, abb.promedioGeneral(), 1e-9, "el promedio refleja la modificación");
        });

        caso("Árbol vacío y un nodo: altura -1 / 0, sin NullPointerException", () -> {
            ArbolEstudiantes vacio = new ArbolEstudiantes();
            igual(-1, vacio.altura(), "altura vacía");
            igual(0, vacio.contarHojas(), "hojas vacías");
            igual(null, vacio.minimo(), "mínimo vacío");
            igual(null, vacio.maximo(), "máximo vacío");
            cercano(0.0, vacio.promedioGeneral(), 1e-9, "promedio vacío");
            verdadero(vacio.esABBValido(), "un árbol vacío cumple la invariante");
            ArbolEstudiantes uno = new ArbolEstudiantes();
            uno.insertar(new Estudiante(10, "Único", 8.0));
            igual(0, uno.altura(), "altura singleton");
            igual(1, uno.contarHojas(), "la raíz sola es hoja");
            igual(10, uno.minimo().getMatricula(), "mínimo = máximo = 10");
            igual(10, uno.maximo().getMatricula(), "máximo");
        });

        caso("Invariante ABB: válida con los datos y detecta un enlace roto a mano", () -> {
            ArbolEstudiantes abb = DatosPrueba.cargarEstudiantes();
            verdadero(abb.esABBValido(), "válida al inicio");
            NodoEstudiante nodo40 = abb.getRaiz().getIzquierdo().getDerecho();
            nodo40.setDerecho(new NodoEstudiante(new Estudiante(55, "Mal ubicado", 7.0)));
            falso(abb.esABBValido(), "55 a la izquierda de 50 rompe la invariante");
        });

        caso("Análisis: cadena O(n) vs equilibrado O(log n) con el mismo tamaño", () -> {
            AnalisisBusqueda.Resultado r = AnalisisBusqueda.comparar(DatosPrueba.MATRICULAS, 85, 90);
            igual(12, r.tamanio, "mismo tamaño");
            igual(11, r.alturaCadena, "cadena: h = n - 1");
            igual(3, r.alturaEquilibrado, "equilibrado: h = 3");
            igual(12, r.compExisteCadena, "buscar 85 recorre toda la cadena");
            igual(12, r.compNoExisteCadena, "buscar 90 también");
            verdadero(r.compExisteEquilibrado <= r.alturaEquilibrado + 1, "equilibrado: <= h + 1");
            verdadero(r.compNoExisteEquilibrado <= r.alturaEquilibrado + 1, "equilibrado: <= h + 1");
        });
    }

    public static void main(String[] args) {
        ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}
