# CONTRIBUTING.md

## Guía de contribución del proyecto

Este documento describe la forma en que se organizó el trabajo colaborativo del equipo, las responsabilidades de cada integrante, la estructura del proyecto y las reglas que se siguieron para mantener un desarrollo ordenado, limpio y fácil de mantener.

El objetivo principal de este archivo es explicar cómo cada integrante contribuyó al desarrollo del sistema, qué carpetas o módulos trabajó y cómo se deben realizar futuras modificaciones al proyecto.

---

## 1. Descripción general del proyecto

Este proyecto fue desarrollado como una aplicación de escritorio utilizando Java y JavaFX, siguiendo una organización por capas para separar la interfaz gráfica, la lógica de negocio, la configuración, las entidades del sistema y el acceso a datos.

La estructura del proyecto busca aplicar buenas prácticas de Programación Orientada a Objetos, separación de responsabilidades y una arquitectura organizada que facilite el mantenimiento, la escalabilidad y la colaboración entre los integrantes del equipo.

El sistema se encuentra dividido en diferentes paquetes y carpetas, donde cada una cumple una función específica dentro del proyecto.

---

## 2. Organización general del equipo

El desarrollo fue dividido por áreas de responsabilidad para que cada integrante pudiera trabajar en una parte específica del sistema. Esta división permitió avanzar de manera ordenada, evitar conflictos entre archivos y mantener una estructura clara dentro del proyecto.

Cada integrante colaboró en carpetas relacionadas con una capa o módulo específico del sistema:

| Integrante | Área principal de contribución                                                               |
| ---------- | -------------------------------------------------------------------------------------------- |
| Gabriel    | Interfaz FXML, clase principal de la aplicación, arranque del sistema y utilidades generales |
| Iancarlo   | Entidades del sistema y enumeraciones                                                        |
| Mario      | Estilos CSS, servicios de lógica de negocio y utilidades                                     |
| Rafa       | Configuraciones, controladores y repositorios                                                |

---

## 3. Contribuciones por integrante

### 3.1 Gabriel

Gabriel contribuyó principalmente en la parte visual y de inicialización del proyecto. Su trabajo estuvo enfocado en los archivos relacionados con JavaFX, la carga de pantallas y el punto de entrada de la aplicación.

#### Carpetas o módulos trabajados

* `fxml`
* `app`
* `main`
* `utils`

#### Responsabilidades principales

Gabriel se encargó de apoyar en la construcción de las vistas del sistema mediante archivos FXML. Estos archivos definen la estructura visual de las ventanas, formularios, botones, tablas, paneles y demás elementos gráficos que utiliza la aplicación.

También colaboró en la configuración inicial de la aplicación, incluyendo la clase principal encargada de ejecutar el sistema. Esta parte es fundamental porque permite iniciar correctamente la aplicación JavaFX y cargar la primera vista del proyecto.

Además, participó en la creación o modificación de utilidades generales que pueden ser reutilizadas por diferentes partes del sistema.

#### Aportaciones específicas

* Creación y organización de archivos FXML.
* Definición de pantallas principales del sistema.
* Apoyo en el diseño de la estructura visual de la aplicación.
* Configuración del arranque de JavaFX.
* Revisión de la clase principal del proyecto.
* Apoyo en métodos utilitarios generales.
* Integración inicial entre vistas y controladores.

#### Importancia de su contribución

La contribución de Gabriel fue importante porque permitió establecer la base visual del sistema y asegurar que la aplicación pudiera ejecutarse correctamente. Sin la configuración del arranque y las vistas FXML, el usuario no podría interactuar con las funciones desarrolladas por el resto del equipo.

---

### 3.2 Iancarlo

Iancarlo contribuyó principalmente en la definición de las clases base del sistema, especialmente las entidades y enumeraciones. Su trabajo fue fundamental para modelar los objetos principales que representan la información del proyecto.

#### Carpetas o módulos trabajados

* `entities`
* `enums`

#### Responsabilidades principales

Iancarlo se encargó de crear y organizar las entidades del sistema. Las entidades representan los objetos principales que se utilizan dentro del proyecto, como usuarios, alumnos, profesores, materias, calificaciones, carreras, grupos u otros elementos relacionados con el funcionamiento del sistema.

También trabajó en las enumeraciones, que permiten representar valores fijos o controlados dentro del sistema. Por ejemplo, roles de usuario, estados, tipos de evaluación, tipos de materia o cualquier conjunto de opciones que no debería escribirse manualmente como texto repetido.

#### Aportaciones específicas

* Creación de clases entidad.
* Definición de atributos principales de cada entidad.
* Aplicación de encapsulamiento mediante atributos privados.
* Creación de constructores.
* Implementación de métodos `get` y `set`.
* Organización de clases del modelo.
* Creación de enumeraciones para valores constantes.
* Apoyo en la consistencia de nombres y tipos de datos.

#### Importancia de su contribución

La contribución de Iancarlo fue esencial porque las entidades representan la base de datos lógica del sistema dentro del código. Gracias a estas clases, el resto de las capas puede trabajar con objetos bien definidos y estructurados.

Las enumeraciones también ayudan a reducir errores, ya que evitan escribir valores repetidos o inconsistentes dentro del código.

---

### 3.3 Mario

Mario contribuyó principalmente en los estilos visuales, la lógica de negocio y algunas utilidades del proyecto. Su trabajo ayudó a mejorar tanto la apariencia del sistema como el funcionamiento interno de varias operaciones.

#### Carpetas o módulos trabajados

* `css`
* `services`
* `utils`

#### Responsabilidades principales

Mario se encargó de trabajar en los archivos CSS utilizados para personalizar la apariencia de la interfaz gráfica. Estos archivos permiten definir colores, tamaños, fuentes, bordes, fondos, botones y demás elementos visuales del sistema.

También colaboró en la capa de servicios, donde se concentra la lógica de negocio. Esta capa es importante porque permite separar las reglas del sistema de la interfaz gráfica y de la persistencia de datos.

Además, trabajó en utilidades reutilizables que pueden ser usadas por diferentes clases del proyecto.

#### Aportaciones específicas

* Creación y modificación de estilos CSS.
* Mejora visual de botones, tablas, formularios y paneles.
* Organización de reglas de estilo para JavaFX.
* Creación de servicios para manejar lógica de negocio.
* Separación de procesos internos de los controladores.
* Apoyo en validaciones y operaciones reutilizables.
* Creación o modificación de métodos utilitarios.

#### Importancia de su contribución

La contribución de Mario fue importante porque permitió mejorar la presentación visual del sistema y separar correctamente la lógica de negocio. Gracias a la capa de servicios, el proyecto se vuelve más ordenado, más fácil de probar y más sencillo de mantener.

Los estilos CSS también ayudan a que la aplicación tenga una apariencia más profesional y uniforme.

---

### 3.4 Rafa

Rafa contribuyó principalmente en la configuración del sistema, los controladores y la capa de repositorios. Su trabajo estuvo enfocado en conectar la interfaz con la lógica del sistema y manejar el acceso a los datos.

#### Carpetas o módulos trabajados

* `configs`
* `controllers`
* `repositories`

#### Responsabilidades principales

Rafa se encargó de trabajar en los archivos de configuración del proyecto. Esta parte permite establecer parámetros importantes para el funcionamiento del sistema, como conexiones, rutas, inicializaciones o configuraciones generales.

También colaboró en los controladores de JavaFX. Los controladores son los encargados de recibir los eventos de la interfaz gráfica, como clics en botones, selección de datos, envío de formularios o carga de información en tablas.

Además, trabajó en los repositorios, que son responsables de administrar la comunicación con los datos del sistema. Los repositorios permiten guardar, consultar, actualizar o eliminar información sin mezclar esa lógica directamente con los controladores.

#### Aportaciones específicas

* Creación y organización de archivos de configuración.
* Manejo de controladores asociados a vistas FXML.
* Programación de eventos de botones y formularios.
* Conexión entre interfaz gráfica y servicios.
* Apoyo en la carga y actualización de datos.
* Creación de repositorios para acceso a datos.
* Separación entre lógica de presentación y lógica de persistencia.
* Validación de flujo entre vista, controlador, servicio y repositorio.

#### Importancia de su contribución

La contribución de Rafa fue fundamental para conectar las diferentes partes del sistema. Los controladores permiten que el usuario interactúe con la aplicación, mientras que los repositorios facilitan el manejo de la información.

Gracias a esta organización, el proyecto puede mantener una arquitectura más limpia y ordenada.

---

## 4. Estructura recomendada del proyecto

La estructura del proyecto puede organizarse de la siguiente manera:

```text
src/
 └── main/
     ├── java/
     │   └── proyecto/
     │       ├── app/
     │       │   └── App.java
     │       │
     │       ├── main/
     │       │   └── Main.java
     │       │
     │       ├── configs/
     │       │   └── AppConfig.java
     │       │
     │       ├── controllers/
     │       │   └── EjemploController.java
     │       │
     │       ├── entities/
     │       │   └── Ejemplo.java
     │       │
     │       ├── enums/
     │       │   └── TipoEjemplo.java
     │       │
     │       ├── repositories/
     │       │   └── EjemploRepository.java
     │       │
     │       ├── services/
     │       │   └── EjemploService.java
     │       │
     │       └── utils/
     │           └── Validaciones.java
     │
     └── resources/
         ├── fxml/
         │   └── vista-ejemplo.fxml
         │
         └── css/
             └── estilos.css
```

---

## 5. Descripción de carpetas y paquetes

### `app`

Contiene clases relacionadas con la inicialización principal de la aplicación JavaFX. Aquí se puede encontrar la clase que extiende de `Application` y que carga la primera ventana del sistema.

### `main`

Contiene el punto de entrada principal del proyecto. Generalmente incluye la clase `Main`, encargada de ejecutar el método principal y lanzar la aplicación.

### `configs`

Contiene archivos de configuración general. Puede incluir configuraciones de rutas, carga de recursos, conexión a base de datos, constantes globales o parámetros iniciales del sistema.

### `controllers`

Contiene los controladores de JavaFX. Cada controlador se relaciona con una vista FXML y se encarga de manejar los eventos del usuario.

Ejemplos de responsabilidades:

* Recibir datos desde formularios.
* Ejecutar acciones al presionar botones.
* Cargar información en tablas.
* Validar entradas básicas.
* Conectar la vista con los servicios.

### `entities`

Contiene las clases que representan los objetos principales del sistema.

Ejemplos:

* Alumno
* Profesor
* Materia
* Carrera
* Grupo
* Calificación
* Usuario

Estas clases deben tener atributos, constructores, métodos de acceso y, cuando sea necesario, métodos adicionales relacionados con el comportamiento del objeto.

### `enums`

Contiene enumeraciones utilizadas para representar valores fijos dentro del sistema.

Ejemplos:

* Roles de usuario.
* Tipos de evaluación.
* Estados de registro.
* Tipos de materia.
* Tipos de calificación.

### `repositories`

Contiene las clases encargadas del acceso a datos. Su objetivo es separar la persistencia del resto del sistema.

Ejemplos de funciones:

* Guardar datos.
* Buscar datos.
* Actualizar registros.
* Eliminar registros.
* Consultar listas de información.

### `services`

Contiene la lógica de negocio del sistema. Esta capa se encarga de aplicar reglas, validaciones y procesos antes de enviar o recibir información desde los repositorios.

Ejemplos de responsabilidades:

* Calcular promedios.
* Validar reglas académicas.
* Procesar calificaciones.
* Administrar usuarios.
* Coordinar operaciones entre controladores y repositorios.

### `utils`

Contiene clases de apoyo reutilizables.

Ejemplos:

* Validaciones.
* Formateo de texto.
* Métodos para fechas.
* Alertas.
* Conversión de datos.
* Métodos auxiliares generales.

### `fxml`

Contiene los archivos de interfaz gráfica diseñados con FXML. Estos archivos definen la estructura visual de las pantallas.

### `css`

Contiene los archivos de estilos para personalizar la apariencia de la aplicación JavaFX.

---

## 6. Flujo general de la arquitectura

El proyecto sigue una separación por capas. El flujo recomendado es el siguiente:

```text
Vista FXML
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Datos
```

### Explicación del flujo

1. El usuario interactúa con una pantalla creada en FXML.
2. El controlador recibe la acción del usuario.
3. El controlador envía la solicitud a un servicio.
4. El servicio aplica la lógica de negocio.
5. El servicio utiliza un repositorio si necesita consultar o guardar información.
6. El repositorio administra el acceso a los datos.
7. El resultado regresa al controlador.
8. El controlador actualiza la interfaz gráfica.

Este flujo permite que el proyecto sea más limpio, más fácil de mantener y más sencillo de escalar.

---

## 7. Reglas para contribuir al proyecto

Para mantener el proyecto ordenado, cualquier contribución debe seguir las siguientes reglas:

### 7.1 Mantener la estructura del proyecto

Cada archivo debe colocarse en la carpeta correspondiente.

Ejemplos:

* Las vistas deben ir en `fxml`.
* Los estilos deben ir en `css`.
* Los controladores deben ir en `controllers`.
* Las entidades deben ir en `entities`.
* Los servicios deben ir en `services`.
* Los repositorios deben ir en `repositories`.
* Las configuraciones deben ir en `configs`.
* Las utilidades deben ir en `utils`.

No se deben mezclar responsabilidades entre carpetas.

---

### 7.2 Usar nombres claros

Los nombres de clases, métodos y variables deben ser descriptivos.

Ejemplos recomendados:

```java
AlumnoController
ProfesorService
CalificacionRepository
TipoUsuario
ValidacionCampos
```

Ejemplos no recomendados:

```java
Clase1
ControladorNuevo
Datos2
PruebaFinal
Metodos
```

---

### 7.3 Respetar la Programación Orientada a Objetos

El proyecto debe aplicar los principios básicos de POO:

* Encapsulamiento.
* Abstracción.
* Herencia, si aplica.
* Polimorfismo, si aplica.
* Separación de responsabilidades.

Las entidades deben tener atributos privados y métodos públicos de acceso cuando sea necesario.

---

### 7.4 No colocar lógica pesada en los controladores

Los controladores no deben contener toda la lógica del sistema. Su función principal es conectar la interfaz gráfica con los servicios.

Incorrecto:

```java
public void calcularPromedio() {
    // Toda la lógica de validación, cálculo y guardado dentro del controlador
}
```

Correcto:

```java
public void calcularPromedio() {
    promedioService.calcularPromedio(alumno);
}
```

La lógica debe estar principalmente en la capa `services`.

---

### 7.5 Usar repositorios para el manejo de datos

Los datos no deben manejarse directamente desde los controladores. Para eso se deben utilizar las clases del paquete `repositories`.

Esto permite que el código sea más organizado y evita dependencias innecesarias entre la interfaz gráfica y la persistencia.

---

### 7.6 Validar antes de guardar

Antes de guardar información, se deben validar los datos ingresados por el usuario.

Ejemplos de validaciones:

* Campos vacíos.
* Formato incorrecto.
* Calificaciones fuera de rango.
* Datos duplicados.
* Selecciones obligatorias.
* Tipos de datos inválidos.

---

### 7.7 Mantener estilos separados

Los estilos visuales deben colocarse en archivos CSS. No se recomienda aplicar estilos directamente desde el código Java, excepto en casos muy específicos.

Correcto:

```css
.boton-principal {
    -fx-background-color: #1E3A8A;
    -fx-text-fill: white;
}
```

No recomendado:

```java
boton.setStyle("-fx-background-color: blue;");
```

---

## 8. Flujo de trabajo con Git y GitHub

Para colaborar correctamente en el proyecto, se recomienda seguir este flujo:

### 8.1 Clonar el repositorio

```bash
git clone URL_DEL_REPOSITORIO
```

### 8.2 Entrar a la carpeta del proyecto

```bash
cd nombre-del-proyecto
```

### 8.3 Crear una nueva rama

Cada integrante debe trabajar en una rama distinta para evitar conflictos.

Ejemplos:

```bash
git checkout -b feature/fxml-gabriel
git checkout -b feature/entities-iancarlo
git checkout -b feature/services-mario
git checkout -b feature/controllers-rafa
```

### 8.4 Agregar cambios

```bash
git add .
```

### 8.5 Crear un commit

```bash
git commit -m "Agrega controladores principales"
```

### 8.6 Subir la rama

```bash
git push origin nombre-de-la-rama
```

### 8.7 Crear un Pull Request

Después de subir la rama, se debe crear un Pull Request en GitHub para revisar los cambios antes de integrarlos a la rama principal.

---

## 9. Convención de commits

Los mensajes de commit deben ser claros y explicar qué cambio se realizó.

Se recomienda usar la siguiente estructura:

```text
tipo: descripción breve del cambio
```

Tipos recomendados:

| Tipo       | Uso                           |
| ---------- | ----------------------------- |
| `feat`     | Nueva funcionalidad           |
| `fix`      | Corrección de errores         |
| `docs`     | Cambios en documentación      |
| `style`    | Cambios visuales o de formato |
| `refactor` | Mejora interna del código     |
| `test`     | Pruebas                       |
| `config`   | Configuración del proyecto    |

Ejemplos:

```bash
git commit -m "feat: agrega vista de inicio de sesión"
git commit -m "fix: corrige validación de calificaciones"
git commit -m "docs: actualiza README del proyecto"
git commit -m "style: mejora diseño de botones"
git commit -m "refactor: separa lógica de promedio en servicio"
```

---

## 10. Reglas para Pull Requests

Antes de enviar un Pull Request, se debe verificar lo siguiente:

* El proyecto compila correctamente.
* No existen errores de sintaxis.
* Los archivos están en las carpetas correctas.
* Los nombres de clases y métodos son claros.
* No se eliminaron archivos importantes.
* No se duplicó código innecesariamente.
* La funcionalidad fue probada.
* El código sigue la estructura del proyecto.
* El Pull Request tiene una descripción clara.

### Plantilla recomendada para Pull Request

```text
## Descripción

Explica brevemente qué cambios se realizaron.

## Tipo de cambio

- [ ] Nueva funcionalidad
- [ ] Corrección de error
- [ ] Documentación
- [ ] Refactorización
- [ ] Estilos visuales
- [ ] Configuración

## Archivos modificados

- archivo 1
- archivo 2
- archivo 3

## Pruebas realizadas

Explica cómo se probó el cambio.

## Integrante responsable

Nombre del integrante que realizó la contribución.
```

---

## 11. Estándares de código

### 11.1 Clases

Los nombres de clases deben escribirse en PascalCase.

Ejemplos:

```java
AlumnoController
CalificacionService
ProfesorRepository
```

### 11.2 Métodos y variables

Los métodos y variables deben escribirse en camelCase.

Ejemplos:

```java
calcularPromedioFinal()
guardarAlumno()
listaProfesores
calificacionFinal
```

### 11.3 Constantes

Las constantes deben escribirse en mayúsculas con guion bajo.

Ejemplo:

```java
public static final double CALIFICACION_MINIMA = 0.0;
public static final double CALIFICACION_MAXIMA = 10.0;
```

### 11.4 Paquetes

Los paquetes deben escribirse en minúsculas.

Ejemplos:

```java
controllers
services
repositories
entities
configs
utils
```

---

## 12. Buenas prácticas utilizadas

Durante el desarrollo del proyecto se recomienda mantener las siguientes buenas prácticas:

* Separar la interfaz gráfica de la lógica del sistema.
* Evitar código duplicado.
* Usar nombres descriptivos.
* Validar datos antes de procesarlos.
* Mantener cada clase con una responsabilidad clara.
* Usar servicios para lógica de negocio.
* Usar repositorios para acceso a datos.
* Mantener los estilos en archivos CSS.
* Documentar partes importantes del código.
* Realizar pruebas antes de subir cambios.
* Revisar que el proyecto compile antes de hacer un Pull Request.

---

## 13. Ejemplo de integración entre capas

Un ejemplo de integración correcta sería el cálculo de una calificación o promedio:

```text
calificaciones.fxml
        ↓
CalificacionController.java
        ↓
CalificacionService.java
        ↓
CalificacionRepository.java
        ↓
Datos almacenados
```

### Explicación

* El archivo FXML muestra la pantalla al usuario.
* El controlador recibe la información ingresada.
* El servicio valida y calcula la información.
* El repositorio guarda o consulta los datos.
* El resultado se muestra nuevamente en la interfaz.

Esta separación permite que el sistema sea más ordenado y evita mezclar la lógica visual con la lógica de negocio.

---

## 14. Resumen de aportaciones del equipo

El equipo distribuyó el trabajo de forma modular, asignando a cada integrante una parte específica del proyecto.

Gabriel apoyó en la construcción de la interfaz gráfica, archivos FXML, arranque de la aplicación y utilidades generales. Su contribución permitió establecer la base visual y funcional inicial del sistema.

Iancarlo trabajó en las entidades y enumeraciones, definiendo la estructura principal de los objetos utilizados en el sistema. Su aportación permitió representar correctamente los datos dentro del proyecto.

Mario colaboró en los estilos visuales, servicios y utilidades, aportando tanto a la apariencia de la aplicación como a la lógica de negocio. Su trabajo ayudó a que el sistema fuera más funcional y visualmente organizado.

Rafa participó en la configuración, controladores y repositorios, conectando la interfaz con la lógica del sistema y el acceso a datos. Su contribución fue clave para integrar las diferentes capas del proyecto.

En conjunto, las contribuciones permitieron desarrollar una aplicación organizada, modular y basada en buenas prácticas de programación.

---

## 15. Créditos

Este proyecto fue desarrollado de manera colaborativa por:

* Gabriel Espinosa
* Mario Garcia 
* Rafael Morales 

Cada integrante participó en módulos específicos del sistema, contribuyendo al desarrollo de una aplicación estructurada y funcional.

---

## 16. Conclusión

Este archivo establece las reglas y lineamientos para contribuir correctamente al proyecto. La división de responsabilidades permitió que el equipo trabajara de forma ordenada y que cada módulo tuviera una función clara dentro de la arquitectura general.

Seguir esta guía ayuda a mantener el código limpio, organizado y fácil de mantener. Además, permite que futuras modificaciones puedan realizarse sin afectar negativamente otras partes del sistema.

La colaboración del equipo fue fundamental para integrar las diferentes capas del proyecto, desde la interfaz gráfica hasta la lógica de negocio y el manejo de datos.
