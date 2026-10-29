# ABB: modificar y eliminar

> **Responsable:** Luis Silva · **Rama:** `feature/luis-abb-modificar-eliminar`
>
> **Para que:** modificar sin tocar la clave y eliminar en los tres casos (hoja, un hijo, dos hijos).

## Flujo de modificar(matricula, nombre, promedio)

```mermaid
flowchart TD
    A(["modificar(m, nombre, promedio)"]) --> B["nodo = buscarNodo(raiz, m)"]
    B --> C{"nodo == null"}
    C -- Si --> X(["false"])
    C -- No --> D["validar nombre y promedio"]
    D --> E{"¿validos?"}
    E -- No --> ERR["IllegalArgumentException: no se cambia nada"]
    E -- Si --> F["cambiar nombre y promedio (la matricula NO)"]
    F --> G(["true"])
    style ERR fill:#f8d7da,stroke:#c00
```

Cambiar la matricula sin reposicionar el nodo romperia el orden: si 35 pasara a 99, quedaria a la izquierda de 50 siendo mayor. Por eso la matricula es `final`; para cambiarla se elimina y se vuelve a insertar.

## Flujo de eliminar(nodo, m)

```mermaid
flowchart TD
    A(["eliminar(nodo, m)"]) --> B{"nodo == null"}
    B -- Si --> R0(["null: caso base"])
    B -- No --> C{"m < nodo.m"}
    C -- Si --> L["nodo.izq = eliminar(nodo.izq, m)"]
    C -- No --> D{"m > nodo.m"}
    D -- Si --> R["nodo.der = eliminar(nodo.der, m)"]
    D -- No --> E{"izq == null"}
    E -- Si --> R1(["devuelve der: hoja o un hijo"])
    E -- No --> F{"der == null"}
    F -- Si --> R2(["devuelve izq: un hijo"])
    F -- No --> G["suc = minimoNodo(nodo.der)"]
    G --> H["nodo.dato = suc.dato"]
    H --> I["nodo.der = eliminar(nodo.der, suc.m)"]
    L --> Z(["nodo"])
    R --> Z
    I --> Z
```

## Traza del enunciado

| Paso | Caso | Que pasa | Inorden |
|---|---|---|---|
| eliminar(20) | hoja | 30 pierde su hijo izquierdo | 30 35 40 45 50 60 65 70 75 80 85 |
| eliminar(60) | un hijo | 65 sube al lugar de 60 | 30 35 40 45 50 65 70 75 80 85 |
| eliminar(70) | dos hijos | sucesor inorden = 75 (menor del subarbol derecho) | 30 35 40 45 50 65 75 80 85 |

El sucesor inorden es mayor que todo el subarbol izquierdo (65) y menor que el resto del derecho (80, 85), por eso el ABB sigue ordenado.

## Secuencia de eliminar(70)

```mermaid
sequenceDiagram
    participant M as Main
    participant A as ArbolEstudiantes
    M->>A: eliminar(70)
    A->>A: buscarNodo(raiz, 70) ≠ null
    A->>A: eliminar(50, 70): 70 > 50 → derecha
    A->>A: eliminar(70, 70): dos hijos
    A->>A: minimoNodo(80) = 75
    A->>A: nodo.dato = 75
    A->>A: eliminar(80, 75) → quita la hoja 75
    A-->>M: true
```
