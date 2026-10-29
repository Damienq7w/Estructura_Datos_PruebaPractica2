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
    }

    /** Bloque de prueba que puede lanzar cualquier excepción. */
    @FunctionalInterface
    public interface Bloque {
        void ejecutar() throws Exception;
    }

    public static void caso(String nombre, Bloque cuerpo) {
        try {
            cuerpo.ejecutar();
            aprobadas++;
            System.out.println("  [OK]    " + nombre);
        } catch (Throwable t) {
            fallidas++;
            System.out.println("  [FALLA] " + nombre + " -> " + t);
        }
    }

    public static void igual(Object esperado, Object obtenido, String mensaje) {
        if (!Objects.equals(esperado, obtenido)) {
            throw new AssertionError(mensaje + " | esperado: " + esperado + " | obtenido: " + obtenido);
        }
    }

    public static void cercano(double esperado, double obtenido, double tolerancia, String mensaje) {
        if (Math.abs(esperado - obtenido) > tolerancia) {
            throw new AssertionError(mensaje + " | esperado: " + esperado + " | obtenido: " + obtenido);
        }
    }

    public static void verdadero(boolean condicion, String mensaje) {
        if (!condicion) {
            throw new AssertionError(mensaje);
        }
    }

    public static void falso(boolean condicion, String mensaje) {
        verdadero(!condicion, mensaje);
    }

    public static void lanza(Class<? extends Throwable> tipo, Bloque bloque, String mensaje) {
        try {
            bloque.ejecutar();
        } catch (Throwable t) {
            if (tipo.isInstance(t)) {
                return;
            }
            throw new AssertionError(mensaje + " | se esperaba " + tipo.getSimpleName() + " y llegó " + t);
        }
        throw new AssertionError(mensaje + " | se esperaba " + tipo.getSimpleName() + " y no se lanzó nada");
    }

    /** Imprime el resumen y devuelve la cantidad de fallas (0 = todo pasó). */
    public static int resumen() {
        System.out.println("----------------------------------------");
        System.out.println("Pruebas aprobadas: " + aprobadas + " | fallidas: " + fallidas
                + " | total: " + (aprobadas + fallidas));
        return fallidas;
    }
}
