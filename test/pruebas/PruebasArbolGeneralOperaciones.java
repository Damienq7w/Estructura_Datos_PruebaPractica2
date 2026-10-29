package pruebas;

import static pruebas.Verificador.caso;
import static pruebas.Verificador.falso;
import static pruebas.Verificador.igual;
import static pruebas.Verificador.verdadero;

import app.DatosPrueba;
import estructuras.ArbolGeneral;
import estructuras.NodoBinarioUnidad;
import modelo.TipoUnidad;
import modelo.UnidadAcademica;

/** Pruebas de metricas, eliminacion segura y transformacion. Responsable: Joel. */
public final class PruebasArbolGeneralOperaciones {

    private PruebasArbolGeneralOperaciones() {
    }

    public static void ejecutar() {
        System.out.println("== Arbol general: operaciones (Joel) ==");

        caso("Aceptacion jerarquia inicial: 9 unidades, 5 hojas, altura 3, grado FISEI 2, nivel 3B 3", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            igual(9, a.contarNodos(), "unidades");
            igual(5, a.contarHojas(), "hojas (FCHE, FCS, 3A, 3B, 4A)");
            igual(3, a.altura(), "altura en aristas");
            igual(2, a.grado("FISEI"), "grado de FISEI");
            igual(3, a.nivel("3B"), "nivel de 3B");
            igual(0, a.nivel("UTA"), "la raiz esta en el nivel 0");
            igual(-1, a.nivel("XYZ"), "nivel de inexistente");
            igual(-1, a.grado("XYZ"), "grado de inexistente");
        });

        caso("Eliminar FISEI con descendencia: rechazo y jerarquia sin cambios", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            String antes = a.imprimirJerarquia();
            falso(a.eliminar("FISEI"), "FISEI tiene hijos");
            igual(antes, a.imprimirJerarquia(), "la jerarquia no debe cambiar");
            igual(9, a.contarNodos(), "siguen 9 unidades");
        });

        caso("Eliminacion segura: la raiz UTA no se elimina; una hoja si; inexistente no", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            falso(a.eliminar("UTA"), "la raiz no se elimina");
            falso(a.eliminar("NOEXISTE"), "inexistente");
            verdadero(a.eliminar("4A"), "4A es hoja");
            falso(a.existe("4A"), "4A ya no existe");
            igual(8, a.contarNodos(), "quedan 8 unidades");
            igual(5, a.contarHojas(), "TI pasa a ser hoja: FCHE, FCS, 3A, 3B, TI");
        });

        caso("Transformacion hijo-izquierdo / hermano-derecho", () -> {
            ArbolGeneral a = DatosPrueba.cargarJerarquia();
            NodoBinarioUnidad b = a.transformarABinario();
            igual("UTA", b.getDato().getCodigo(), "raiz");
            igual(null, b.getDerecho(), "la raiz no tiene hermanos");
            NodoBinarioUnidad fisei = b.getIzquierdo();
            igual("FISEI", fisei.getDato().getCodigo(), "primer hijo de UTA");
            igual("FCHE", fisei.getDerecho().getDato().getCodigo(), "hermano derecho de FISEI");
            igual("FCS", fisei.getDerecho().getDerecho().getDato().getCodigo(), "hermano derecho de FCHE");
            igual("SOFTWARE", fisei.getIzquierdo().getDato().getCodigo(), "primer hijo de FISEI");
            igual("TI", fisei.getIzquierdo().getDerecho().getDato().getCodigo(), "hermano de SOFTWARE");
            igual(9, a.contarNodos(), "la transformacion no modifica el arbol general");
        });

        caso("Arbol vacio y un solo nodo: altura -1 y 0, sin NullPointerException", () -> {
            ArbolGeneral vacio = new ArbolGeneral();
            igual(-1, vacio.altura(), "altura vacia");
            igual(0, vacio.contarNodos(), "nodos");
            igual(0, vacio.contarHojas(), "hojas");
            igual(-1, vacio.nivel("UTA"), "nivel");
            falso(vacio.eliminar("UTA"), "eliminar en vacio");
            igual(null, vacio.transformarABinario(), "transformar vacio");
            ArbolGeneral uno = new ArbolGeneral();
            uno.crearRaiz(new UnidadAcademica("UTA", "UTA", TipoUnidad.UNIVERSIDAD));
            igual(0, uno.altura(), "altura singleton");
            igual(1, uno.contarHojas(), "la raiz sola es hoja");
            igual(0, uno.grado("UTA"), "grado singleton");
        });
    }

    public static void main(String[] args) {
        ejecutar();
        System.exit(Verificador.resumen() == 0 ? 0 : 1);
    }
}