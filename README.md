# Estructura_Datos_PruebaPractica2

**Sistema de Auditoría Jerárquica y Académica UTA** — Prueba práctica grupal de árboles en Java.

Estructura de Datos · Tercero "B" · Carrera de Software · FISEI · UTA
Docente: Ing. José Rubén Caiza Caizabuano, PhD

## Propósito

La aplicación audita a la vez:

- la **jerarquía académica** con un árbol general (UTA → facultades → carreras → paralelos), y
- la **matrícula de estudiantes** con un árbol binario de búsqueda (ABB) ordenado por matrícula.

Los dos árboles están implementados con nodos propios: no se usa `TreeMap`, `TreeSet` ni librerías externas. `ArrayList` solo se usa para los hijos del árbol general y para los resultados de los recorridos. Todas las consultas son recursivas.

## Estructura del proyecto

| Carpeta            | Contenido                                                                                                |
| ------------------ | -------------------------------------------------------------------------------------------------------- |
| `src/modelo/`      | `TipoUnidad`, `UnidadAcademica`, `Estudiante` (datos y validaciones)                                     |
| `src/estructuras/` | `NodoUnidad`, `ArbolGeneral`, `NodoBinarioUnidad`, `NodoEstudiante`, `ArbolEstudiantes`, `TipoRecorrido` |
| `src/servicio/`    | `Auditoria` (reporte integrado) y `AnalisisBusqueda` (cadena vs equilibrado)                             |
| `src/app/`         | `Main` (menú de consola) y `DatosPrueba` (datos obligatorios del enunciado)                              |
| `test/pruebas/`    | `Verificador` (aserciones) y una clase de pruebas por integrante + `EjecutarPruebas`                     |
| `docs/diagramas/`  | Diagramas Mermaid de clases, flujo y secuencia de cada integrante                                        |

Capas: `app → servicio → estructuras → modelo`.

## Compilar y ejecutar

Requisitos: JDK 17 o superior y Visual Studio Code (Extension Pack for Java). Abrir la carpeta del repositorio en una terminal.

**PowerShell (Windows):**

```powershell
javac -encoding UTF-8 -d bin (Get-ChildItem -Recurse src,test -Filter *.java).FullName
java "-Dstdout.encoding=UTF-8" -cp bin app.Main
```

**Git Bash / Linux / macOS:**

```bash
javac -encoding UTF-8 -d bin $(find src test -name "*.java")
java -Dstdout.encoding=UTF-8 -cp bin app.Main
```

**Pruebas (todas):**

```bash
java -Dstdout.encoding=UTF-8 -cp bin pruebas.EjecutarPruebas
```

Cada clase de pruebas también se ejecuta sola, por ejemplo `java -cp bin pruebas.PruebasABBEliminacion`. El programa de pruebas termina con código 0 si todo pasó y 1 si algo falló.

## Menú

| Opción | Operación                                                                                           |
| ------ | --------------------------------------------------------------------------------------------------- |
| 1–5    | Árbol general: ver jerarquía, agregar, buscar (nivel y grado), eliminar hoja, transformar a binario |
| 6–10   | ABB: insertar, buscar (con comparaciones), recorridos, modificar, eliminar                          |
| 11     | Auditoría: unidades, hojas, altura general, matrícula menor/mayor, promedio e invariante ABB        |
| 12     | Análisis de búsqueda: ABB en cadena vs ABB equilibrado                                              |
| 13     | Ejecuta paso a paso el escenario del enunciado                                                      |
| 14     | Reinicia los datos de prueba (útil después de los cambios en vivo del docente)                      |

## Casos de aceptación

| Prueba                          | Esperado                                                                         | Obtenido              | Prueba automática                                      |
| ------------------------------- | -------------------------------------------------------------------------------- | --------------------- | ------------------------------------------------------ |
| Jerarquía inicial               | 9 unidades; hojas FCHE, FCS, 3A, 3B, 4A (5); altura 3; grado FISEI 2; nivel 3B 3 | igual                 | `PruebasArbolGeneralOperaciones`                       |
| ABB tras 12 inserciones         | inorden 20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85                           | igual                 | `PruebasABBBasico`                                     |
| Insertar de nuevo 40            | rechazo, conteo 12                                                               | igual                 | `PruebasABBBasico`                                     |
| Eliminar 20, 60 y 70            | inorden 30, 35, 40, 45, 50, 65, 75, 80, 85; 9 nodos                              | igual                 | `PruebasABBEliminacion`                                |
| Eliminar FISEI con descendencia | rechazo, jerarquía sin cambios                                                   | igual                 | `PruebasArbolGeneralOperaciones`                       |
| Transformación general-binario  | primer hijo de UTA = FISEI; hermano de FISEI = FCHE; hermano de FCHE = FCS       | igual                 | `PruebasArbolGeneralOperaciones`                       |
| Árboles vacíos y un nodo        | sin NullPointerException; altura vacía -1, singleton 0                           | igual                 | `PruebasArbolGeneralOperaciones`, `PruebasABBMetricas` |
| Búsqueda ABB                    | comparar O(h) en cadena y equilibrado                                            | 12 vs 4 comparaciones | `PruebasABBMetricas`                                   |

## Salida del escenario del enunciado (opción 13)

```text
1) Jerarquía inicial
UTA - Universidad Técnica de Ambato [UNIVERSIDAD]
  FISEI - Fac. Ing. Sistemas, Electrónica e Industrial [FACULTAD]
    SOFTWARE - Carrera de Software [CARRERA]
      3A - Software Tercero A [PARALELO]
      3B - Software Tercero B [PARALELO]
    TI - Carrera de Tecnologías de la Información [CARRERA]
      4A - TI Cuarto A [PARALELO]
  FCHE - Fac. Ciencias Humanas y de la Educación [FACULTAD]
  FCS - Fac. Ciencias de la Salud [FACULTAD]
   Unidades=9 Hojas=5 Altura=3 Grado FISEI=2 Nivel 3B=3
2) ABB tras 12 inserciones, inorden: [20, 30, 35, 40, 45, 50, 60, 65, 70, 75, 80, 85]
   Preorden: [50, 30, 20, 40, 35, 45, 70, 60, 65, 80, 75, 85]
   Posorden: [20, 35, 45, 40, 30, 65, 60, 75, 85, 80, 70, 50]
3) Insertar 40 otra vez -> rechazado, total = 12
4) Modificar 35 -> 35 - Hugo Sánchez Mod. (9.6)
5) Eliminar 20, 60, 70 -> inorden [30, 35, 40, 45, 50, 65, 75, 80, 85], nodos = 9
6) Eliminar FISEI -> rechazado, unidades = 9
7) Transformación general-binario:
Raíz: UTA
  Izq (primer hijo): FISEI
    Izq (primer hijo): SOFTWARE
      Izq (primer hijo): 3A
        Der (hermano): 3B
      Der (hermano): TI
        Izq (primer hijo): 4A
    Der (hermano): FCHE
      Der (hermano): FCS
8) ========== AUDITORÍA UTA ==========
-- Jerarquía académica (árbol general)
  Total de unidades : 9
  Hojas             : 5
  Altura general    : 3 (aristas)
-- Matrícula (ABB)
  Total estudiantes : 9
  Hojas del ABB     : 4
  Altura del ABB    : 3 (aristas)
  Matrícula menor   : 30 - Bruno Díaz (7.2)
  Matrícula mayor   : 85 - Luis Ortega (8.3)
  Promedio general  : 8,52
  Invariante ABB    : VÁLIDA
===================================
9) Análisis de búsqueda (n = 12 en ambos ABB)
ABB                    | Altura   | Buscar 85 (existe) | Buscar 90 (no existe)
Cadena (ascendente)    | 11       | 12                 | 12
Equilibrado (medianas) | 3        | 4                  | 4
```

> Capturas: agregar aquí las capturas de VS Code con la ejecución del menú y de `EjecutarPruebas` (carpeta `docs/capturas/`).

## Pruebas

28 casos con aserciones, todos aprobados (`Pruebas aprobadas: 28 | fallidas: 0`). Cubren: jerarquía válida e inválida, padre inexistente, códigos duplicados, raíz única, árboles vacíos y de un nodo, inorden antes y después de eliminar, eliminación de unidad no hoja, consistencia del promedio, invariante del ABB, análisis de comparaciones y el cambio en vivo de dos matrículas y un nodo académico.

## Complejidad

| Operación                                                           | Costo | Motivo                                                               |
| ------------------------------------------------------------------- | ----- | -------------------------------------------------------------------- |
| Árbol general: buscar, nivel, altura, contar, eliminar, transformar | O(N)  | no hay orden: se pueden visitar todas las unidades                   |
| ABB: insertar, buscar, eliminar, mínimo, máximo                     | O(h)  | se sigue un solo camino; h = log₂ n equilibrado, h = n − 1 en cadena |
| ABB: recorridos, altura, contar, hojas, promedio, invariante        | O(n)  | se visita cada nodo una vez                                          |

## Contribuciones

| Integrante      | GitHub                          | Rama                                     | Aporte                                                                                                                               |
| --------------- | ------------------------------- | ---------------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------ |
| Damian Cunalata | `Damienq7w`                     | `feature/damian-base-arbol-general`      | Estructura del proyecto, modelo, `NodoUnidad`, `agregar`/`buscar` del árbol general, `Verificador`, pruebas base, diagrama de clases |
| Joel Tisalema   | `Joel03032007`                  | `feature/joel-operaciones-arbol-general` | nivel, grado, altura, conteo de nodos y hojas, jerarquía con sangría, eliminación segura, transformación a binario y sus pruebas     |
| Mateo Chalco    | `KEN3DYY`                       | `feature/mateo-abb-insertar-recorridos`  | `NodoEstudiante`, insertar, buscar con contador de comparaciones, contar, tres recorridos y sus pruebas                              |
| Luis Silva      | `luuissilva`                    | `feature/luis-abb-modificar-eliminar`    | modificar sin cambiar la clave, eliminar en los tres casos con sucesor inorden y sus pruebas                                         |
| Sara Tacuri     | `tacurisantillanmonicasara-dot` | `feature/sara-abb-metricas-analisis`     | altura, hojas, mínimo, máximo, promedio, invariante ABB, análisis cadena vs equilibrado y sus pruebas                                |
| Josue Camacho   | `jampiercamacho94-cloud`        | `feature/josue-integracion-readme`       | auditoría, menú de consola, escenario del enunciado, pruebas de integración, ejecutor de pruebas y README                            |

Cada aporte puede verificarse en _Insights → Contributors_, en el historial de commits y en las pull requests revisadas por otro integrante.

## Referencias

- L. E. Vargas Azcona, _Problemas y Algoritmos_: cap. 2–4 (recursión), 8–11 (complejidad), 14 (árboles binarios y ABB).
- Oracle, _Java Platform, Standard Edition 17 API Specification_.
- Mermaid, documentación de diagramas de clases, flujo y secuencia.
