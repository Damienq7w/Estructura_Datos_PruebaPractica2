package servicio;

import java.util.Arrays;

import estructuras.ArbolEstudiantes;
import modelo.Estudiante;

/**
 * Compara las comparaciones de busqueda en dos ABB con las MISMAS matriculas:
 * - cadena: insertadas en orden ascendente (el arbol degenera en lista, h = n -
 * 1);
 * - equilibrado: insertadas tomando siempre la mediana de cada rango (h ≈ log2
 * n).
 * No es un arbol autoequilibrado (AVL/rojo-negro): el equilibrio depende solo
 * del
 * orden de insercion. Si despues se insertan datos ordenados, puede volver a
 * degenerar.
 */
public final class AnalisisBusqueda {

    private AnalisisBusqueda() {
    }

    /**
     * Resultado de la comparacion, listo para mostrar en consola o en el README.
     */
    public static final class Resultado {
        public final int tamanio;
        public final int alturaCadena;
        public final int alturaEquilibrado;
        public final int existente;
        public final int noExistente;
        public final int compExisteCadena;
        public final int compNoExisteCadena;
        public final int compExisteEquilibrado;
        public final int compNoExisteEquilibrado;

        Resultado(int tamanio, int alturaCadena, int alturaEquilibrado, int existente, int noExistente,
                int compExisteCadena, int compNoExisteCadena,
                int compExisteEquilibrado, int compNoExisteEquilibrado) {
            this.tamanio = tamanio;
            this.alturaCadena = alturaCadena;
            this.alturaEquilibrado = alturaEquilibrado;
            this.existente = existente;
            this.noExistente = noExistente;
            this.compExisteCadena = compExisteCadena;
            this.compNoExisteCadena = compNoExisteCadena;
            this.compExisteEquilibrado = compExisteEquilibrado;
            this.compNoExisteEquilibrado = compNoExisteEquilibrado;
        }

        @Override
        public String toString() {
            return "Análisis de búsqueda (n = " + tamanio + " en ambos ABB)\n"
                    + String.format("%-22s | %-8s | %-18s | %-18s%n", "ABB", "Altura",
                            "Buscar " + existente + " (existe)", "Buscar " + noExistente + " (no existe)")
                    + String.format("%-22s | %-8d | %-18d | %-18d%n", "Cadena (ascendente)",
                            alturaCadena, compExisteCadena, compNoExisteCadena)
                    + String.format("%-22s | %-8d | %-18d | %-18d%n", "Equilibrado (medianas)",
                            alturaEquilibrado, compExisteEquilibrado, compNoExisteEquilibrado)
                    + "Búsqueda O(h): en cadena h = n - 1 -> O(n); en el equilibrado h ≈ log2(n) -> O(log n).";
        }
    }

    /** ABB construido con inserciones ascendentes (peor caso). */
    public static ArbolEstudiantes construirAscendente(int[] matriculas) {
        int[] ordenadas = ordenar(matriculas);
        ArbolEstudiantes abb = new ArbolEstudiantes();
        for (int m : ordenadas) {
            abb.insertar(crear(m));
        }
        return abb;
    }

    /**
     * ABB construido insertando primero la mediana y luego cada mitad (recursivo).
     */
    public static ArbolEstudiantes construirEquilibrado(int[] matriculas) {
        int[] ordenadas = ordenar(matriculas);
        ArbolEstudiantes abb = new ArbolEstudiantes();
        insertarMedianas(abb, ordenadas, 0, ordenadas.length - 1);
        return abb;
    }

    // Caso base: rango vacio. Se inserta la mediana y se repite en cada mitad.
    private static void insertarMedianas(ArbolEstudiantes abb, int[] datos, int inicio, int fin) {
        if (inicio > fin) {
            return;
        }
        int medio = (inicio + fin) / 2;
        abb.insertar(crear(datos[medio]));
        insertarMedianas(abb, datos, inicio, medio - 1);
        insertarMedianas(abb, datos, medio + 1, fin);
    }

    public static Resultado comparar(int[] matriculas, int existente, int noExistente) {
        ArbolEstudiantes cadena = construirAscendente(matriculas);
        ArbolEstudiantes equilibrado = construirEquilibrado(matriculas);

        cadena.buscar(existente);
        int c1 = cadena.getUltimasComparaciones();
        cadena.buscar(noExistente);
        int c2 = cadena.getUltimasComparaciones();
        equilibrado.buscar(existente);
        int e1 = equilibrado.getUltimasComparaciones();
        equilibrado.buscar(noExistente);
        int e2 = equilibrado.getUltimasComparaciones();

        return new Resultado(cadena.contar(), cadena.altura(), equilibrado.altura(),
                existente, noExistente, c1, c2, e1, e2);
    }

    private static int[] ordenar(int[] matriculas) {
        int[] copia = Arrays.copyOf(matriculas, matriculas.length);
        Arrays.sort(copia);
        return copia;
    }

    private static Estudiante crear(int matricula) {
        return new Estudiante(matricula, "Estudiante " + matricula, 7.0);
    }
}
