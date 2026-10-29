package pruebas;

import java.util.Objects;

/**
 * Mini marco de pruebas con aserciones (sin dependencias externas).
 * Cada caso se ejecuta aislado: si falla, se reporta y se continúa con el siguiente.
 */
public final class Verificador {

    private static int aprobadas;
    private static int fallidas;

    private Verificador() {
        throw new UnsupportedOperationException("Pendiente: Verificador - Damian Cunalata");
    }

    /** Bloque de prueba que puede lanzar cualquier excepción. */
    @FunctionalInterface
    public interface Bloque {
        void ejecutar() throws Exception;
    }

    public static void caso(String nombre, Bloque cuerpo) {
        throw new UnsupportedOperationException("Pendiente: caso - Damian Cunalata");
    }

    public static void igual(Object esperado, Object obtenido, String mensaje) {
        throw new UnsupportedOperationException("Pendiente: igual - Damian Cunalata");
    }

    public static void cercano(double esperado, double obtenido, double tolerancia, String mensaje) {
        throw new UnsupportedOperationException("Pendiente: cercano - Damian Cunalata");
    }

    public static void verdadero(boolean condicion, String mensaje) {
        throw new UnsupportedOperationException("Pendiente: verdadero - Damian Cunalata");
    }

    public static void falso(boolean condicion, String mensaje) {
        throw new UnsupportedOperationException("Pendiente: falso - Damian Cunalata");
    }

    public static void lanza(Class<? extends Throwable> tipo, Bloque bloque, String mensaje) {
        throw new UnsupportedOperationException("Pendiente: lanza - Damian Cunalata");
    }

    /** Imprime el resumen y devuelve la cantidad de fallas (0 = todo pasó). */
    public static int resumen() {
        throw new UnsupportedOperationException("Pendiente: resumen - Damian Cunalata");
    }
}
