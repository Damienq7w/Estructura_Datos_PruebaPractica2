package pruebas;

/** Ejecuta todas las pruebas del grupo. Código de salida 0 = todo pasó. */
public final class EjecutarPruebas {

    private EjecutarPruebas() {
    }

    public static void main(String[] args) {
        PruebasArbolGeneralBase.ejecutar();
        PruebasArbolGeneralOperaciones.ejecutar();
        PruebasABBBasico.ejecutar();
        PruebasABBEliminacion.ejecutar();
        PruebasABBMetricas.ejecutar();
        PruebasIntegracion.ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}
