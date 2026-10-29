# Integración: menú y auditoría

> **Responsable:** Josue Camacho · **Rama:** `feature/josue-integracion-readme`
>
> **Para qué:** cómo el menú llama a los dos árboles y cómo la auditoría junta sus métricas sin modificarlos.

## Flujo del menú

```mermaid
flowchart TD
    A(["main"]) --> B["reiniciarDatos(): DatosPrueba"]
    B --> C["mostrarMenu()"]
    C --> D["opcion = leerEntero()"]
    D --> E{"opcion"}
    E -- "1-5" --> G["ArbolGeneral"]
    E -- "6-10" --> H["ArbolEstudiantes"]
    E -- "11" --> I["Auditoria.generarReporte"]
    E -- "12" --> J["AnalisisBusqueda.comparar"]
    E -- "13" --> K["escenario del enunciado"]
    E -- "0" --> Z(["fin"])
    G --> C
    H --> C
    I --> C
    J --> C
    K --> C
    D -. "dato inválido" .-> ERR["[ERROR] mensaje y vuelve al menú"]
    style ERR fill:#f8d7da,stroke:#c00
```

## Secuencia de la auditoría (opción 11)

```mermaid
sequenceDiagram
    participant M as Main
    participant Au as Auditoria
    participant G as ArbolGeneral
    participant B as ArbolEstudiantes
    M->>Au: generarReporte(general, abb)
    Au->>G: contarNodos(), contarHojas(), altura()
    G-->>Au: 9, 5, 3
    Au->>B: contar(), contarHojas(), altura()
    B-->>Au: 12, 6, 3
    Au->>B: minimo(), maximo(), promedioGeneral()
    B-->>Au: 20, 85, 8.08
    Au->>B: esABBValido()
    B-->>Au: true
    Au-->>M: reporte en texto
```
