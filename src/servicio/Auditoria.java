package servicio;

import estructuras.ArbolEstudiantes;
import estructuras.ArbolGeneral;
import modelo.Estudiante;

/** Reporte que integra los dos árboles. No modifica ninguno. */
public final class Auditoria {

    private Auditoria() {
    }

    public static String generarReporte(ArbolGeneral general, ArbolEstudiantes abb) {
        StringBuilder sb = new StringBuilder();
        sb.append("========== AUDITORÍA UTA ==========\n");
        sb.append("-- Jerarquía académica (árbol general)\n");
        if (general == null || general.estaVacio()) {
            sb.append("  Árbol general vacío.\n");
        } else {
            sb.append("  Total de unidades : ").append(general.contarNodos()).append('\n');
            sb.append("  Hojas             : ").append(general.contarHojas()).append('\n');
            sb.append("  Altura general    : ").append(general.altura()).append(" (aristas)\n");
        }
        sb.append("-- Matrícula (ABB)\n");
        if (abb == null || abb.estaVacio()) {
            sb.append("  ABB vacío: sin matrícula menor/mayor, promedio 0.0\n");
        } else {
            Estudiante menor = abb.minimo();
            Estudiante mayor = abb.maximo();
            sb.append("  Total estudiantes : ").append(abb.contar()).append('\n');
            sb.append("  Hojas del ABB     : ").append(abb.contarHojas()).append('\n');
            sb.append("  Altura del ABB    : ").append(abb.altura()).append(" (aristas)\n");
            sb.append("  Matrícula menor   : ").append(menor).append('\n');
            sb.append("  Matrícula mayor   : ").append(mayor).append('\n');
            sb.append(String.format("  Promedio general  : %.2f%n", abb.promedioGeneral()));
        }
        boolean valido = abb == null || abb.esABBValido();
        sb.append("  Invariante ABB    : ").append(valido ? "VÁLIDA" : "ROTA").append('\n');
        sb.append("===================================");
        return sb.toString();
    }
}

