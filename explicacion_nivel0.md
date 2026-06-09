# Explicación del Diagrama de Contexto

## Objetivo del diagrama

Este diagrama muestra el sistema completo como un solo proceso principal. Su finalidad es identificar qué actores externos interactúan con el sistema y qué información intercambian con él.

## Sistema representado

El proceso central es el **Sistema Universitario de Administración y Consulta de Calificaciones**, desarrollado con JavaFX y persistencia local en SQLite.

## Entidades externas

### 1. Administrador
Interactúa con el sistema para iniciar sesión y administrar información académica. Envía datos de alumnos, profesores, carreras y materias. Recibe listados, validaciones, mensajes de confirmación y errores.

### 2. Profesor
Inicia sesión, selecciona grupos asignados y captura calificaciones. El sistema le devuelve los alumnos inscritos, las calificaciones actuales, el promedio calculado y el estatus final.

### 3. Alumno
Inicia sesión y solicita la consulta de sus calificaciones. El sistema le muestra parciales, actividades, proyecto, promedio final y estatus de aprobación.

## Interpretación

El diagrama de contexto no detalla tablas ni procesos internos. Solo explica los límites del sistema y los intercambios principales de información con usuarios externos.
