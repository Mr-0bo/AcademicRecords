# Explicación del Diagrama de Nivel 0

## Objetivo del diagrama

El diagrama de nivel 0 descompone el sistema general en sus procesos principales. Aquí ya se muestran los módulos internos y los almacenes de datos utilizados.

## Procesos principales

### 1.0 Autenticar usuario
Recibe correo y contraseña de administrador, profesor o alumno. Consulta el almacén de usuarios, valida la contraseña y determina el rol para redirigir al panel correspondiente.

### 2.0 Administrar catálogos académicos
Permite que el administrador registre, edite, consulte o elimine alumnos, profesores, carreras y materias. Usa el almacén de catálogos académicos.

### 3.0 Gestionar grupos e inscripciones
Relaciona materias, profesores y alumnos. Este proceso crea grupos académicos, registra inscripciones y genera una calificación inicial asociada a cada inscripción.

### 4.0 Configurar reglas de evaluación
Guarda los porcentajes de evaluación por materia: parciales, actividades y proyecto. También administra la calificación mínima aprobatoria.

### 5.0 Capturar y calcular calificaciones
Permite al profesor capturar calificaciones. El sistema consulta la configuración de evaluación, valida las notas, calcula el promedio final y determina si el alumno aprueba o reprueba.

### 6.0 Consultar calificaciones
Permite al alumno consultar sus calificaciones guardadas, incluyendo parciales, actividades, proyecto, promedio final y estatus.

## Almacenes de datos

- **D1 Usuarios y roles:** usuarios, contraseñas cifradas y tipo de usuario.
- **D2 Catálogos académicos:** carreras, materias, alumnos y profesores.
- **D3 Grupos e inscripciones:** relación entre materia, profesor, periodo y alumnos inscritos.
- **D4 Configuración de evaluación:** porcentajes y criterios de aprobación.
- **D5 Calificaciones:** parciales, actividades, proyecto, promedio final y estatus.

## Interpretación

Este nivel permite ver cómo se organiza el sistema por módulos. También muestra que el cálculo de promedio depende tanto de las calificaciones capturadas como de la configuración de evaluación de la materia.
