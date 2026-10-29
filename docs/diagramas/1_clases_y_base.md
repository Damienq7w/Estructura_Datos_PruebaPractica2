# Diagrama de clases y base del árbol general

> **Responsable:** Damian Cunalata · **Rama:** `feature/damian-base-arbol-general`
>
> **Para qué:** mostrar las capas del proyecto, cómo se unen los dos árboles y cómo `agregar` valida código único, padre existente y relación padre–hijo.

## Diagrama de clases

```mermaid
classDiagram
    direction LR
    class TipoUnidad {
        <<enumeration>>
        UNIVERSIDAD
        FACULTAD
        CARRERA
        PARALELO
        +puedeTenerHijo(TipoUnidad) boolean
    }
    class UnidadAcademica {
        -String codigo
        -String nombre
        -TipoUnidad tipo
    }
    class Estudiante {
        -int matricula  «clave, final»
        -String nombre
        -double promedio
    }
    class NodoUnidad {
        -UnidadAcademica dato
        -ArrayList~NodoUnidad~ hijos
        +agregarHijo(NodoUnidad) boolean
        +eliminarHijo(NodoUnidad) boolean
        +esHoja() boolean
    }
    class NodoBinarioUnidad {
        -UnidadAcademica dato
        -NodoBinarioUnidad izquierdo «primer hijo»
        -NodoBinarioUnidad derecho «hermano»
    }
    class NodoEstudiante {
        -Estudiante dato
        -NodoEstudiante izquierdo
        -NodoEstudiante derecho
    }
    class ArbolGeneral {
        -NodoUnidad raiz
        +crearRaiz(UnidadAcademica) boolean
        +agregar(String, UnidadAcademica) boolean
        +buscar(String) NodoUnidad
        +nivel(String) int
        +grado(String) int
        +altura() int
        +contarNodos() int
        +contarHojas() int
        +imprimirJerarquia() String
        +eliminar(String) boolean
        +transformarABinario() NodoBinarioUnidad
    }
    class ArbolEstudiantes {
        -NodoEstudiante raiz
        +insertar(Estudiante) boolean
        +buscar(int) Estudiante
        +recorrer(TipoRecorrido) ArrayList
        +modificar(int, String, double) boolean
        +eliminar(int) boolean
        +altura() int
        +contar() int
        +contarHojas() int
        +minimo() Estudiante
        +maximo() Estudiante
        +promedioGeneral() double
        +esABBValido() boolean
    }
    class Auditoria
    class AnalisisBusqueda
    class Main

    UnidadAcademica --> TipoUnidad
    NodoUnidad --> UnidadAcademica
    NodoUnidad "1" o-- "0..*" NodoUnidad : hijos
    ArbolGeneral --> NodoUnidad : raiz
    ArbolGeneral ..> NodoBinarioUnidad : crea
    NodoEstudiante --> Estudiante
    NodoEstudiante --> NodoEstudiante : izq / der
    ArbolEstudiantes --> NodoEstudiante : raiz
    Auditoria ..> ArbolGeneral
    Auditoria ..> ArbolEstudiantes
    AnalisisBusqueda ..> ArbolEstudiantes
    Main ..> Auditoria
    Main ..> AnalisisBusqueda
```

## Paquetes

```mermaid
flowchart LR
    app --> servicio --> estructuras --> modelo
    app --> estructuras
    pruebas["test/pruebas"] --> app
    pruebas --> servicio
```

## Flujo de agregar(codigoPadre, unidad)

```mermaid
flowchart TD
    A(["agregar(codigoPadre, unidad)"]) --> B{"unidad == null o raiz == null"}
    B -- Sí --> X1(["false"])
    B -- No --> C{"existe(unidad.codigo)"}
    C -- Sí --> X2(["false: código repetido"])
    C -- No --> D["padre = buscar(codigoPadre)"]
    D --> E{"padre == null"}
    E -- Sí --> X3(["false: padre inexistente"])
    E -- No --> F{"padre.tipo.puedeTenerHijo(unidad.tipo)"}
    F -- No --> X4(["false: jerarquía inválida"])
    F -- Sí --> G["padre.hijos.add(nuevo nodo)"]
    G --> H(["true"])
```

## Flujo de buscar(nodo, clave) — recursivo, O(N)

```mermaid
flowchart TD
    A(["buscar(nodo, clave)"]) --> B{"nodo == null"}
    B -- Sí --> R1(["null: caso base"])
    B -- No --> C{"nodo.codigo == clave"}
    C -- Sí --> R2(["nodo: caso base"])
    C -- No --> D{"quedan hijos"}
    D -- No --> R3(["null"])
    D -- Sí --> E["r = buscar(hijo, clave)"]
    E --> F{"r != null"}
    F -- Sí --> R4(["r"])
    F -- No --> D
```
