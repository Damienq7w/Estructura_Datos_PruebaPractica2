
# ABB: insertar, buscar y recorridos

> **Responsable:** Mateo Chalco · **Rama:** `Mateo-Chalco`
>
> **Para qué:** inserción recursiva sin duplicados, búsqueda O(h) con contador de comparaciones y los tres recorridos.

## ABB tras las 12 inserciones (50, 30, 70, 20, 40, 60, 80, 35, 45, 65, 75, 85)

```mermaid
flowchart TD
    50 --> 30
    50 --> 70
# ABB: insertar, buscar y recorridos

> **Responsable:** Mateo Chalco · **Rama:** `Mateo-Chalco`
>
> **Para qué:** inserción recursiva sin duplicados, búsqueda O(h) con contador de comparaciones y los tres recorridos.

## ABB tras las 12 inserciones (50, 30, 70, 20, 40, 60, 80, 35, 45, 65, 75, 85)

```mermaid
flowchart TD
    50 --> 30
    50 --> 70
    30 --> 20
    30 --> 40
    40 --> 35
    40 --> 45
    70 --> 60
    70 --> 80
    60 --> 65
    80 --> 75
    80 --> 85
```

## Flujo de insertar(nodo, e)

```mermaid
flowchart TD
    A(["insertar(nodo, e)"]) --> B{"nodo == null"}
    B -- Sí --> C(["new NodoEstudiante(e): caso base"])
    B -- No --> D{"e.matricula < nodo.matricula"}
    D -- Sí --> E["nodo.izq = insertar(nodo.izq, e)"]
    D -- No --> F["nodo.der = insertar(nodo.der, e)"]
    E --> G(["nodo"])
    F --> G
```

El duplicado se detecta antes con `buscarNodo`: si existe, `insertar` devuelve `false` y el árbol no cambia.

## Secuencia de buscar(45)

```mermaid
sequenceDiagram
    participant M as Main
    participant A as ArbolEstudiantes
    M->>A: buscar(45)
    A->>A: comparaciones = 0
    A->>A: 50: 45 < 50 → izquierda (1)
    A->>A: 30: 45 > 30 → derecha (2)
    A->>A: 40: 45 > 40 → derecha (3)
    A->>A: 45 == 45 → encontrado (4)
    A-->>M: Estudiante 45, getUltimasComparaciones() = 4
```

## Recorridos

| Recorrido | Orden | Resultado |
|---|---|---|
| Preorden | visita, izq, der | 50 30 20 40 35 45 70 60 65 80 75 85 |
| Inorden | izq, visita, der | 20 30 35 40 45 50 60 65 70 75 80 85 |
| Posorden | izq, der, visita | 20 35 45 40 30 65 60 75 85 80 70 50 |

    30 --> 20
    30 --> 40
    40 --> 35
    40 --> 45
    70 --> 60
    70 --> 80
    60 --> 65
    80 --> 75
    80 --> 85
```

## Flujo de insertar(nodo, e)

```mermaid
flowchart TD
    A(["insertar(nodo, e)"]) --> B{"nodo == null"}
    B -- Sí --> C(["new NodoEstudiante(e): caso base"])
    B -- No --> D{"e.matricula < nodo.matricula"}
    D -- Sí --> E["nodo.izq = insertar(nodo.izq, e)"]
    D -- No --> F["nodo.der = insertar(nodo.der, e)"]
    E --> G(["nodo"])
    F --> G
```

El duplicado se detecta antes con `buscarNodo`: si existe, `insertar` devuelve `false` y el árbol no cambia.

## Secuencia de buscar(45)

```mermaid
sequenceDiagram
    participant M as Main
    participant A as ArbolEstudiantes
    M->>A: buscar(45)
    A->>A: comparaciones = 0
    A->>A: 50: 45 < 50 → izquierda (1)
    A->>A: 30: 45 > 30 → derecha (2)
    A->>A: 40: 45 > 40 → derecha (3)
    A->>A: 45 == 45 → encontrado (4)
    A-->>M: Estudiante 45, getUltimasComparaciones() = 4
```

## Recorridos

| Recorrido | Orden | Resultado |
|---|---|---|
| Preorden | visita, izq, der | 50 30 20 40 35 45 70 60 65 80 75 85 |
| Inorden | izq, visita, der | 20 30 35 40 45 50 60 65 70 75 80 85 |
| Posorden | izq, der, visita | 20 35 45 40 30 65 60 75 85 80 70 50 |
