package app;

import java.util.Scanner;

import estructuras.ArbolEstudiantes;
import estructuras.ArbolGeneral;
import estructuras.NodoUnidad;
import estructuras.TipoRecorrido;
import modelo.Estudiante;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;
import servicio.AnalisisBusqueda;
import servicio.Auditoria;

/** Menú de consola del Sistema de Auditoría Jerárquica y Académica UTA. */
public class Main {

    private static final Scanner ENTRADA = new Scanner(System.in);
    private static ArbolGeneral general;
    private static ArbolEstudiantes abb;

    public static void main(String[] args) {
        reiniciarDatos();
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Opción: ");
            try {
                ejecutar(opcion);
            } catch (IllegalArgumentException e) {
                System.out.println("[ERROR] " + e.getMessage());
            }
        } while (opcion != 0);
        System.out.println("Fin del programa.");
    }

    private static void mostrarMenu() {
        System.out.println();
        System.out.println("===== AUDITORÍA JERÁRQUICA Y ACADÉMICA UTA =====");
        System.out.println(" -- Árbol general --");
        System.out.println("  1. Ver jerarquía");
        System.out.println("  2. Agregar unidad");
        System.out.println("  3. Buscar unidad (nivel y grado)");
        System.out.println("  4. Eliminar unidad (solo hojas)");
        System.out.println("  5. Transformar a binario (hijo izq / hermano der)");
        System.out.println(" -- ABB de estudiantes --");
        System.out.println("  6. Insertar estudiante");
        System.out.println("  7. Buscar estudiante (con comparaciones)");
        System.out.println("  8. Recorridos pre / in / posorden");
        System.out.println("  9. Modificar nombre y promedio");
        System.out.println(" 10. Eliminar estudiante");
        System.out.println(" -- Integración --");
        System.out.println(" 11. Auditoría");
        System.out.println(" 12. Análisis de búsqueda (cadena vs equilibrado)");
        System.out.println(" 13. Ejecutar escenario del enunciado");
        System.out.println(" 14. Reiniciar datos de prueba");
        System.out.println("  0. Salir");
    }

    private static void ejecutar(int opcion) {
        switch (opcion) {
            case 1 -> System.out.print(general.imprimirJerarquia());
            case 2 -> agregarUnidad();
            case 3 -> buscarUnidad();
            case 4 -> eliminarUnidad();
            case 5 -> System.out.print(general.representarBinario());
            case 6 -> insertarEstudiante();
            case 7 -> buscarEstudiante();
            case 8 -> mostrarRecorridos();
            case 9 -> modificarEstudiante();
            case 10 -> eliminarEstudiante();
            case 11 -> System.out.println(Auditoria.generarReporte(general, abb));
            case 12 -> System.out.println(AnalisisBusqueda.comparar(DatosPrueba.MATRICULAS, 85, 90));
            case 13 -> escenarioEnunciado();
            case 14 -> {
                reiniciarDatos();
                System.out.println("Datos de prueba cargados de nuevo.");
            }
            case 0 -> { }
            default -> System.out.println("Opción no válida.");
        }
    }

    private static void reiniciarDatos() {
        general = DatosPrueba.cargarJerarquia();
        abb = DatosPrueba.cargarEstudiantes();
    }

    // ---------- Árbol general ----------

    private static void agregarUnidad() {
        String padre = leerTexto("Código del padre: ");
        String codigo = leerTexto("Código de la nueva unidad: ");
        String nombre = leerTexto("Nombre: ");
        TipoUnidad tipo = leerTipo();
        boolean ok = general.agregar(padre, new UnidadAcademica(codigo, nombre, tipo));
        System.out.println(ok ? "Unidad agregada."
                : "Rechazada: código repetido, padre inexistente o relación padre–hijo inválida.");
    }

    private static void buscarUnidad() {
        String codigo = leerTexto("Código: ");
        NodoUnidad nodo = general.buscar(codigo);
        if (nodo == null) {
            System.out.println("No existe la unidad " + codigo + ".");
            return;
        }
        System.out.println(nodo.getDato());
        System.out.println("Nivel: " + general.nivel(codigo) + " | Grado: " + general.grado(codigo)
                + " | Hoja: " + (nodo.esHoja() ? "sí" : "no"));
    }

    private static void eliminarUnidad() {
        String codigo = leerTexto("Código a eliminar: ");
        boolean ok = general.eliminar(codigo);
        System.out.println(ok ? "Unidad eliminada."
                : "Rechazada: no existe, es la raíz o tiene descendientes.");
    }

    // ---------- ABB ----------

    private static void insertarEstudiante() {
        int matricula = leerEntero("Matrícula: ");
        String nombre = leerTexto("Nombre: ");
        double promedio = leerDecimal("Promedio (0-10): ");
        boolean ok = abb.insertar(new Estudiante(matricula, nombre, promedio));
        System.out.println(ok ? "Estudiante insertado." : "Rechazado: la matrícula ya existe.");
        System.out.println("Total de estudiantes: " + abb.contar());
    }

    private static void buscarEstudiante() {
        int matricula = leerEntero("Matrícula: ");
        Estudiante e = abb.buscar(matricula);
        System.out.println(e == null ? "No existe." : e.toString());
        System.out.println("Comparaciones: " + abb.getUltimasComparaciones()
                + " | Altura actual del ABB: " + abb.altura());
    }

    private static void mostrarRecorridos() {
        System.out.println("Preorden : " + abb.recorrerMatriculas(TipoRecorrido.PREORDEN));
        System.out.println("Inorden  : " + abb.recorrerMatriculas(TipoRecorrido.INORDEN));
        System.out.println("Posorden : " + abb.recorrerMatriculas(TipoRecorrido.POSORDEN));
    }

    private static void modificarEstudiante() {
        int matricula = leerEntero("Matrícula (no se puede cambiar): ");
        String nombre = leerTexto("Nuevo nombre: ");
        double promedio = leerDecimal("Nuevo promedio (0-10): ");
        boolean ok = abb.modificar(matricula, nombre, promedio);
        System.out.println(ok ? "Datos actualizados: " + abb.buscar(matricula) : "No existe la matrícula.");
    }

    private static void eliminarEstudiante() {
        int matricula = leerEntero("Matrícula a eliminar: ");
        boolean ok = abb.eliminar(matricula);
        System.out.println(ok ? "Eliminado." : "No existe la matrícula.");
        System.out.println("Inorden: " + abb.recorrerMatriculas(TipoRecorrido.INORDEN));
    }

    /** Reproduce paso a paso los casos del enunciado (salidas trazables para el README). */
    private static void escenarioEnunciado() {
        reiniciarDatos();
        System.out.println("1) Jerarquía inicial");
        System.out.print(general.imprimirJerarquia());
        System.out.println("   Unidades=" + general.contarNodos() + " Hojas=" + general.contarHojas()
                + " Altura=" + general.altura() + " Grado FISEI=" + general.grado("FISEI")
                + " Nivel 3B=" + general.nivel("3B"));
        System.out.println("2) ABB tras 12 inserciones, inorden: "
                + abb.recorrerMatriculas(TipoRecorrido.INORDEN));
        System.out.println("   Preorden: " + abb.recorrerMatriculas(TipoRecorrido.PREORDEN));
        System.out.println("   Posorden: " + abb.recorrerMatriculas(TipoRecorrido.POSORDEN));
        boolean dup = abb.insertar(new Estudiante(40, "Duplicado", 5.0));
        System.out.println("3) Insertar 40 otra vez -> " + (dup ? "aceptado (ERROR)" : "rechazado")
                + ", total = " + abb.contar());
        abb.modificar(35, "Hugo Sánchez Mod.", 9.6);
        System.out.println("4) Modificar 35 -> " + abb.buscar(35));
        abb.eliminar(20);
        abb.eliminar(60);
        abb.eliminar(70);
        System.out.println("5) Eliminar 20, 60, 70 -> inorden " + abb.recorrerMatriculas(TipoRecorrido.INORDEN)
                + ", nodos = " + abb.contar());
        boolean fisei = general.eliminar("FISEI");
        System.out.println("6) Eliminar FISEI -> " + (fisei ? "eliminado (ERROR)" : "rechazado")
                + ", unidades = " + general.contarNodos());
        System.out.println("7) Transformación general-binario:");
        System.out.print(general.representarBinario());
        System.out.println("8) " + Auditoria.generarReporte(general, abb));
        System.out.println("9) " + AnalisisBusqueda.comparar(DatosPrueba.MATRICULAS, 85, 90));
    }

    // ---------- Lectura segura ----------

    private static String leerTexto(String mensaje) {
        System.out.print(mensaje);
        if (!ENTRADA.hasNextLine()) {
            System.out.println("\nFin de la entrada.");
            System.exit(0);
        }
        return ENTRADA.nextLine().trim();
    }

    private static int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número entero.");
            }
        }
    }

    private static double leerDecimal(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje).replace(',', '.');
            try {
                return Double.parseDouble(texto);
            } catch (NumberFormatException e) {
                System.out.println("Ingrese un número (por ejemplo 8.5).");
            }
        }
    }

    private static TipoUnidad leerTipo() {
        while (true) {
            String texto = leerTexto("Tipo (FACULTAD, CARRERA, PARALELO): ").toUpperCase();
            try {
                return TipoUnidad.valueOf(texto);
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo no válido.");
            }
        }
    }
}
