package estructuras;

/** Recorridos en profundidad del ABB. */
public enum TipoRecorrido {
    PREORDEN,  // visita, izquierdo, derecho
    INORDEN,   // izquierdo, visita, derecho (sale ordenado)
    POSORDEN   // izquierdo, derecho, visita
}
