# Sistema de Administración y Consulta de Calificaciones Universitarias

##  Descripción general

Este proyecto consiste en el desarrollo de un sistema de escritorio creado con **JavaFX**, orientado a la administración, registro y consulta de calificaciones dentro de un entorno universitario.

El sistema modela una universidad con carreras, semestres, materias, profesores, alumnos y administradores. Además, permite configurar reglas de evaluación por materia, registrar calificaciones parciales y actividades, calcular automáticamente el promedio final y determinar el estatus académico del alumno como **Aprobado** o **Reprobado**.

El proyecto fue desarrollado aplicando principios de **Programación Orientada a Objetos**, arquitectura **MVC**, separación por capas, patrones de diseño, validaciones, persistencia de datos y control de acceso por roles.

---

##  Objetivo del proyecto

Desarrollar una aplicación de escritorio funcional que permita gestionar calificaciones universitarias de forma organizada, segura y estructurada, integrando reglas reales de evaluación académica.

El objetivo principal es aplicar de manera práctica los siguientes conceptos:

* Programación Orientada a Objetos.
* Arquitectura en capas.
* Patrón MVC.
* Patrones de diseño.
* Persistencia de datos.
* Interfaces gráficas con JavaFX.
* Validación de datos.
* Login con roles.
* Cálculo automático de promedio final.

---

##  Contexto del sistema

El sistema considera un entorno universitario donde:

* Un profesor puede impartir varias materias.
* Un profesor puede tener múltiples grupos.
* Un alumno pertenece a una carrera.
* Las materias están organizadas por semestre.
* Cada materia tiene una configuración de evaluación propia.
* Los profesores pueden registrar y editar calificaciones.
* Los alumnos pueden consultar sus calificaciones.
* Los administradores pueden gestionar la información principal del sistema.

---

##  Roles del sistema

###  Profesor

El profesor puede:

* Iniciar sesión.
* Visualizar las materias asignadas.
* Registrar calificaciones parciales.
* Registrar calificaciones de actividades.
* Editar calificaciones.
* Calcular automáticamente el promedio final de los alumnos.

###  Alumno

El alumno puede:

* Iniciar sesión.
* Consultar sus calificaciones parciales.
* Consultar su calificación final.
* Ver su estatus académico: Aprobado o Reprobado.

###  Administrador

El administrador puede realizar operaciones CRUD sobre:

* Carreras.
* Semestres.
* Materias.
* Profesores.
* Alumnos.
* Calificaciones.

También puede modificar información académica y corregir calificaciones cuando sea necesario.

---

##  Funcionalidades principales

* Inicio de sesión con validación de usuario.
* Control de acceso según rol.
* Panel independiente para profesor, alumno y administrador.
* Registro de calificaciones parciales.
* Registro de actividades.
* Edición de calificaciones.
* Cálculo automático del promedio final.
* Validación de porcentajes de evaluación.
* Validación de calificaciones entre 0 y 10.
* Determinación automática del estatus académico.
* Consulta de calificaciones por parte del alumno.
* Administración de datos académicos.
* Persistencia de información.

---

##  Cálculo automático del promedio final

Una de las funcionalidades principales del sistema es el cálculo automático del promedio final.

Cada materia puede tener una configuración de evaluación, por ejemplo:

* Parciales: 70%
* Actividades: 30%
* Calificación mínima aprobatoria: 6.0

El sistema calcula el promedio de parciales y actividades, aplica los porcentajes configurados y obtiene el promedio final.

### Fórmula utilizada

```java
promedioFinal = promedioParciales * porcentajeParciales + promedioActividades * porcentajeActividades;
```

### Ejemplo

Si un alumno tiene:

```text
Promedio de parciales: 8.5
Promedio de actividades: 9.0
Porcentaje de parciales: 70%
Porcentaje de actividades: 30%
```

Entonces:

```text
Promedio final = 8.5 * 0.70 + 9.0 * 0.30
Promedio final = 8.65
```

Si la calificación mínima aprobatoria es `6.0`, el alumno queda con estatus:

```text
Aprobado
```

---

##  Arquitectura del proyecto

El proyecto está organizado utilizando el patrón **MVC** y separación en capas.

```text
src/main/java
│
├── model
│   ├── Usuario.java
│   ├── Alumno.java
│   ├── Profesor.java
│   ├── Administrador.java
│   ├── Carrera.java
│   ├── Semestre.java
│   ├── Materia.java
│   ├── Grupo.java
│   ├── Inscripcion.java
│   ├── Calificacion.java
│   └── ConfiguracionEvaluacion.java
│
├── controller
│   ├── LoginController.java
│   ├── ProfesorController.java
│   ├── AlumnoController.java
│   └── AdministradorController.java
│
├── service
│   ├── LoginService.java
│   ├── CalculoPromedioService.java
│   ├── CalificacionService.java
│   └── UsuarioService.java
│
├── repository
│   ├── UsuarioRepository.java
│   ├── CalificacionRepository.java
│   └── MateriaRepository.java
│
└── app
    └── Main.java
```

---

##  Modelo de datos sugerido

El sistema utiliza las siguientes entidades principales:

### Universidad

Representa la institución educativa.

```text
Universidad:
- id
- nombre
```

### Carrera

Representa las carreras disponibles dentro de la universidad.

```text
Carrera:
- id
- nombre
- id_universidad
```

### Semestre

Representa el semestre académico.

```text
Semestre:
- id
- numero
```

### Materia

Representa las asignaturas que pertenecen a una carrera y semestre.

```text
Materia:
- id
- nombre
- id_carrera
- id_semestre
```

### Usuario

Entidad base para manejar el inicio de sesión.

```text
Usuario:
- id
- nombre
- correo
- contraseña
- tipo
```

### Alumno

Representa a los estudiantes registrados.

```text
Alumno:
- id_usuario
- matricula
- id_carrera
```

### Profesor

Representa a los docentes.

```text
Profesor:
- id_usuario
- numero_empleado
```

### Grupo

Representa la relación entre una materia, un profesor y un periodo escolar.

```text
Grupo:
- id
- id_materia
- id_profesor
- periodo
```

### Inscripción

Relaciona a un alumno con un grupo.

```text
Inscripcion:
- id
- id_alumno
- id_grupo
```

### Configuración de evaluación

Define las reglas de evaluación de cada materia.

```text
ConfiguracionEvaluacion:
- id
- id_materia
- porcentaje_parciales
- porcentaje_actividades
- calificacion_minima
- faltas
```

### Calificación

Almacena las calificaciones y el promedio final.

```text
Calificacion:
- id
- id_inscripcion
- parciales
- actividades
- promedio_final
- estatus
```

---

##  Patrones de diseño aplicados

### MVC

Se utiliza el patrón **Modelo-Vista-Controlador** para separar la lógica del sistema:

* **Modelo:** representa las entidades del sistema.
* **Vista:** contiene las interfaces hechas con JavaFX.
* **Controlador:** conecta la interfaz con la lógica de negocio.
* **Servicio:** contiene las reglas del sistema.
* **Repositorio:** maneja la persistencia de datos.

### Service Layer

La lógica de negocio se encuentra en clases de servicio, evitando que los controladores tengan código excesivo o reglas académicas directamente en la interfaz.

Ejemplo:

```text
CalculoPromedioService
```

Esta clase se encarga de validar calificaciones, aplicar porcentajes y calcular el promedio final.

### Repository Pattern

Se utiliza para separar el acceso a datos de la lógica principal del sistema.

Esto permite cambiar la forma de persistencia, por ejemplo de archivos a base de datos, sin modificar directamente la interfaz gráfica.

---

##  Persistencia de datos

El sistema puede manejar persistencia mediante:

* Archivos JSON.
* Archivos XML.
* Base de datos relacional.

La capa de repositorio se encarga de guardar, consultar, actualizar y eliminar información del sistema.

---

##  Seguridad

El sistema implementa inicio de sesión con roles.

Cada usuario tiene un tipo asignado:

```text
PROFESOR
ALUMNO
ADMINISTRADOR
```

Dependiendo del rol, el sistema muestra un panel diferente y limita las acciones disponibles.

Esto evita que un alumno pueda modificar calificaciones o que un profesor acceda a funciones administrativas.

---

##  Tecnologías utilizadas

* Java.
* JavaFX.
* Programación Orientada a Objetos.
* MVC.
* Persistencia de datos.
* CSS para estilos de interfaz.
* Git y GitHub para control de versiones.

---

##  Validaciones implementadas

El sistema incluye validaciones para evitar errores durante la ejecución:

* Las calificaciones deben estar entre 0 y 10.
* Los campos obligatorios no pueden estar vacíos.
* Los porcentajes de evaluación deben sumar 100%.
* La calificación mínima aprobatoria debe estar entre 0 y 10.
* El usuario debe iniciar sesión con credenciales válidas.
* Cada rol solo puede acceder a sus funciones correspondientes.
* Se muestran mensajes de error controlados en lugar de cerrar la aplicación.

---

##  Cómo ejecutar el proyecto

### Requisitos previos

Antes de ejecutar el proyecto, se necesita tener instalado:

* Java JDK 17 o superior.
* JavaFX SDK.
* Un IDE compatible, como IntelliJ IDEA, NetBeans o Eclipse.
* Git, en caso de clonar el repositorio.

### Clonar el repositorio

```bash
git clone https://github.com/usuario/nombre-del-repositorio.git
```

### Abrir el proyecto

1. Abrir el IDE.
2. Seleccionar la opción **Open Project**.
3. Elegir la carpeta del proyecto.
4. Configurar JavaFX si es necesario.
5. Ejecutar la clase principal:

```text
Main.java
```

---

##  Estructura general del proyecto

```text
Sistema-Calificaciones-Universitarias
│
├── src
│   └── main
│       ├── java
│       │   ├── app
│       │   ├── controller
│       │   ├── model
│       │   ├── repository
│       │   └── service
│       │
│       └── resources
│           ├── view
│           ├── css
│           └── data
│
├── README.md
└── pom.xml / build.gradle
```

---

##  Casos de prueba sugeridos

### Caso 1: Registro correcto de calificaciones

```text
Parcial 1: 8
Parcial 2: 9
Parcial 3: 8.5
Actividad 1: 10
Actividad 2: 9
Actividad 3: 9.5
```

Resultado esperado:

```text
Promedio final calculado correctamente.
Estatus: Aprobado.
```

### Caso 2: Calificación fuera de rango

```text
Parcial 1: 12
```

Resultado esperado:

```text
Error: Las calificaciones deben estar entre 0 y 10.
```

### Caso 3: Porcentajes incorrectos

```text
Parciales: 80%
Actividades: 30%
```

Resultado esperado:

```text
Error: Los porcentajes de evaluación deben sumar 100%.
```

### Caso 4: Alumno reprobado

```text
Promedio final: 5.8
Calificación mínima aprobatoria: 6.0
```

Resultado esperado:

```text
Estatus: Reprobado.
```

---

##  Autor

Proyecto desarrollado como parte de una actividad académica de Programación Orientada a Objetos y desarrollo de aplicaciones de escritorio con JavaFX.

```text
Nombre: Rafael Morales
Nombre: Mario Garcia
Nombre: Omar Espinosa
Nombre: Iancarlo Sigler
Carrera: Ingeniería en Sistemas Computacionales
```

---

##  Aprendizajes obtenidos

Durante el desarrollo de este proyecto se reforzaron conocimientos sobre:

* Diseño de sistemas orientados a objetos.
* Separación de responsabilidades.
* Desarrollo de interfaces gráficas con JavaFX.
* Manejo de roles y restricciones de acceso.
* Validación de datos.
* Cálculo automático de promedios.
* Organización del código en capas.
* Aplicación de patrones de diseño.
* Persistencia de información.

---

##  Licencia

Este proyecto fue desarrollado con fines académicos.

Puede utilizarse, modificarse y adaptarse como referencia educativa, siempre respetando la autoría original del proyecto.
