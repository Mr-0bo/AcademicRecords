# Security Policy

## Política de seguridad

Este documento describe la política de seguridad del proyecto, las versiones que reciben soporte, las recomendaciones para reportar vulnerabilidades y las medidas básicas que deben seguirse para mantener el sistema protegido.

El objetivo de esta política es establecer un proceso claro para identificar, reportar, analizar y corregir posibles fallas de seguridad dentro del proyecto.

---

## Supported Versions

Actualmente, este proyecto se encuentra en desarrollo académico y puede recibir mejoras, correcciones y actualizaciones conforme avance su implementación.

Las versiones soportadas son aquellas que pertenecen a la rama principal del proyecto o a versiones recientes publicadas oficialmente en el repositorio.

| Version | Supported          |
| ------- | ------------------ |
| 1.0.x   | :white_check_mark: |
| 0.9.x   | :white_check_mark: |
| 0.8.x   | :x:                |
| < 0.8   | :x:                |

### Explicación de soporte

* `1.0.x`: versión principal del proyecto. Recibe correcciones, mejoras y actualizaciones de seguridad.
* `0.9.x`: versión previa estable. Puede recibir correcciones importantes si afectan el funcionamiento general.
* `0.8.x` y versiones anteriores: no reciben soporte activo, ya que pueden contener código incompleto, pruebas antiguas o estructuras que fueron reemplazadas.

Si el proyecto aún no maneja versiones oficiales, se considera soportada la versión más reciente disponible en la rama principal `main` o `master`.

---

## Reporting a Vulnerability

Si encuentras una vulnerabilidad, error de seguridad o comportamiento inesperado dentro del proyecto, se recomienda reportarlo de forma responsable.

Una vulnerabilidad puede incluir, pero no se limita a:

* Acceso no autorizado a información.
* Validaciones incorrectas en formularios.
* Manejo inseguro de datos.
* Exposición de contraseñas o información sensible.
* Errores en el inicio de sesión.
* Fallas en permisos de usuarios.
* Problemas en el almacenamiento de datos.
* Uso incorrecto de archivos de configuración.
* Dependencias inseguras o desactualizadas.
* Posibles errores que permitan modificar información sin autorización.

---

## How to Report a Vulnerability

Para reportar una vulnerabilidad, se recomienda abrir un issue privado o contactar directamente al equipo responsable del proyecto.

Si el repositorio permite reportes privados de seguridad, utiliza la opción:

```text
Security > Report a vulnerability
```

En caso de que esa opción no esté disponible, puedes crear un issue en GitHub evitando publicar información demasiado sensible.

### Información recomendada para el reporte

Al reportar una vulnerabilidad, incluye la mayor cantidad de información posible:

```text
Título:
Descripción breve de la vulnerabilidad.

Versión afectada:
Indicar la versión, rama o commit donde se encontró el problema.

Módulo afectado:
Por ejemplo: login, controladores, repositorios, servicios, entidades, configuración, base de datos, vistas FXML, etc.

Pasos para reproducir:
1. Abrir la aplicación.
2. Ir a la sección afectada.
3. Ingresar los datos o realizar la acción.
4. Observar el error o comportamiento inseguro.

Resultado esperado:
Explicar qué debería ocurrir.

Resultado obtenido:
Explicar qué ocurrió realmente.

Nivel de gravedad:
Bajo, medio, alto o crítico.

Evidencia:
Capturas de pantalla, logs, fragmentos de código o descripción del error.

Posible solución:
Opcionalmente, proponer una forma de corregir el problema.
```

---

## Response Process

Cuando se reciba un reporte de vulnerabilidad, el equipo seguirá el siguiente proceso:

1. **Recepción del reporte**
   Se revisará la información enviada para confirmar que corresponde a una vulnerabilidad real o posible riesgo de seguridad.

2. **Análisis del problema**
   Se identificará el módulo afectado, el nivel de impacto y la posibilidad de reproducir el error.

3. **Clasificación de gravedad**
   La vulnerabilidad será clasificada de acuerdo con su impacto en el sistema.

4. **Corrección**
   El equipo realizará los cambios necesarios en el código, configuración o documentación.

5. **Pruebas**
   Se validará que la corrección funcione correctamente y no afecte otros módulos del sistema.

6. **Publicación de la solución**
   La corrección se integrará al repositorio mediante un commit o Pull Request.

7. **Cierre del reporte**
   Una vez corregido el problema, se cerrará el reporte indicando que la vulnerabilidad fue atendida.

---

## Expected Response Time

El tiempo de respuesta puede variar dependiendo de la gravedad del problema.

| Severity | Expected Response |
| -------- | ----------------- |
| Critical | 24 - 48 horas     |
| High     | 2 - 4 días        |
| Medium   | 5 - 7 días        |
| Low      | 1 - 2 semanas     |

### Criterios de gravedad

#### Critical

Una vulnerabilidad crítica es aquella que puede comprometer gravemente el sistema.

Ejemplos:

* Acceso no autorizado como administrador.
* Exposición de contraseñas.
* Manipulación completa de datos.
* Eliminación no autorizada de información.
* Ejecución de acciones sin validación.

#### High

Una vulnerabilidad alta afecta funciones importantes del sistema.

Ejemplos:

* Modificación de datos sin permisos adecuados.
* Validaciones incorrectas en módulos principales.
* Fallas en el control de acceso.
* Exposición parcial de información sensible.

#### Medium

Una vulnerabilidad media afecta el funcionamiento o seguridad de forma moderada.

Ejemplos:

* Validaciones incompletas.
* Manejo incorrecto de errores.
* Mensajes que muestran información interna del sistema.
* Problemas menores en el flujo de autenticación.

#### Low

Una vulnerabilidad baja representa un riesgo limitado.

Ejemplos:

* Errores visuales que podrían confundir al usuario.
* Mensajes poco claros.
* Validaciones de formato menores.
* Recomendaciones de mejora en la estructura del código.

---

## Accepted Vulnerabilities

Una vulnerabilidad será aceptada si cumple con alguno de los siguientes puntos:

* Puede afectar la seguridad de los usuarios.
* Puede permitir acceso no autorizado.
* Puede alterar información sin permisos.
* Puede provocar pérdida o corrupción de datos.
* Puede exponer información sensible.
* Puede afectar la integridad del sistema.
* Puede ser reproducida con pasos claros.
* Puede estar relacionada con dependencias inseguras.

Cuando una vulnerabilidad sea aceptada, se creará una tarea, issue o Pull Request para darle seguimiento hasta su corrección.

---

## Declined Vulnerabilities

Un reporte puede ser rechazado si:

* No se puede reproducir el problema.
* No representa un riesgo real de seguridad.
* Corresponde únicamente a una sugerencia de estilo.
* Ya fue reportado anteriormente.
* El problema se encuentra en una versión no soportada.
* No incluye información suficiente para analizarlo.
* Depende de una configuración externa no relacionada con el proyecto.

Si un reporte es rechazado, se explicará brevemente el motivo.

---

## Security Best Practices

Para mantener el proyecto seguro, se recomienda seguir las siguientes prácticas:

### 1. No subir información sensible

No se deben subir al repositorio archivos que contengan:

* Contraseñas.
* Tokens.
* Llaves privadas.
* Credenciales de base de datos.
* Información personal real.
* Archivos `.env` con datos sensibles.
* Configuraciones privadas del sistema.

En caso de necesitar variables de configuración, se recomienda usar archivos de ejemplo como:

```text
.env.example
config.example.properties
```

---

### 2. Validar entradas del usuario

Toda información ingresada por el usuario debe validarse antes de ser procesada.

Ejemplos:

* Campos obligatorios.
* Longitud mínima y máxima.
* Formato de correo electrónico.
* Rangos válidos para calificaciones.
* Fechas correctas.
* Selecciones válidas en listas o formularios.

---

### 3. Evitar lógica insegura en controladores

Los controladores deben encargarse principalmente de recibir eventos de la interfaz gráfica. La lógica importante debe estar en la capa de servicios.

Flujo recomendado:

```text
FXML
  ↓
Controller
  ↓
Service
  ↓
Repository
  ↓
Datos
```

Esto permite mantener el código más limpio y reduce errores de seguridad.

---

### 4. Proteger el acceso a datos

Los repositorios deben manejar correctamente las operaciones de consulta, registro, actualización y eliminación de datos.

Se recomienda:

* Validar datos antes de guardar.
* Evitar duplicados.
* Controlar errores de conexión.
* No exponer directamente datos sensibles.
* Separar la lógica de persistencia de la interfaz gráfica.

---

### 5. Manejar errores correctamente

No se recomienda mostrar errores técnicos completos al usuario final.

Incorrecto:

```text
java.sql.SQLException: Access denied for user root@localhost...
```

Correcto:

```text
No fue posible conectar con la base de datos. Verifica la configuración del sistema.
```

Los errores técnicos deben registrarse internamente para revisión del equipo.

---

### 6. Mantener dependencias actualizadas

Si el proyecto utiliza dependencias externas, se recomienda revisar periódicamente que no tengan vulnerabilidades conocidas.

Ejemplos de dependencias:

* JavaFX.
* MySQL Connector.
* Librerías de validación.
* Frameworks externos.
* Dependencias de Maven o Gradle.

---

### 7. Revisar cambios antes de integrarlos

Antes de aceptar un Pull Request, se debe revisar que:

* El código compile correctamente.
* No se suban credenciales.
* No se rompa la estructura del proyecto.
* Las validaciones funcionen.
* No se introduzcan errores de seguridad.
* La lógica esté en la capa correspondiente.
* Los cambios sean claros y justificados.

---

## Secure Development Guidelines

Para futuras contribuciones, se recomienda seguir estas reglas:

* Usar nombres claros en clases, métodos y variables.
* Mantener los paquetes organizados.
* Separar controladores, servicios y repositorios.
* Validar todos los datos recibidos.
* Evitar código duplicado.
* No dejar código de prueba innecesario.
* No subir archivos temporales.
* Documentar cambios importantes.
* Probar los módulos antes de subirlos.
* Revisar que no existan credenciales dentro del código.

---

## Private Data Policy

El proyecto no debe almacenar ni compartir información personal real sin autorización.

Si durante las pruebas se necesitan datos, se recomienda usar información ficticia.

Ejemplo:

```text
Nombre: Juan Pérez
Correo: usuario@ejemplo.com
Matrícula: 000000
Contraseña: password123
```

No se recomienda usar información real de alumnos, profesores, usuarios o integrantes del equipo.

---

## Security Review Checklist

Antes de publicar una nueva versión o realizar una entrega final, se recomienda revisar lo siguiente:

* [ ] El proyecto compila correctamente.
* [ ] No existen contraseñas visibles en el código.
* [ ] No se subieron archivos `.env` con información real.
* [ ] Los formularios validan campos vacíos.
* [ ] Los datos ingresados tienen formato correcto.
* [ ] Los controladores no contienen lógica excesiva.
* [ ] Los servicios contienen las reglas principales del sistema.
* [ ] Los repositorios manejan correctamente el acceso a datos.
* [ ] Los errores técnicos no se muestran directamente al usuario.
* [ ] Las dependencias utilizadas están actualizadas.
* [ ] El proyecto fue probado antes de publicarse.
* [ ] La documentación está actualizada.

---

## Security Updates

Las actualizaciones de seguridad se realizarán cuando se detecte un problema que pueda afectar la integridad, disponibilidad o confidencialidad del sistema.

Estas actualizaciones pueden incluir:

* Corrección de errores.
* Mejoras en validaciones.
* Actualización de dependencias.
* Ajustes en la configuración.
* Mejoras en el manejo de datos.
* Cambios en permisos o roles.
* Correcciones en controladores, servicios o repositorios.

---

## Contact

Para reportar problemas de seguridad, se recomienda contactar al equipo responsable del proyecto mediante GitHub Issues o mediante el sistema de reportes de seguridad del repositorio.

Equipo responsable del proyecto:

* Gabriel Espinosa
* Iancarlo Sigler
* Mario Garcia 
* Rafael Morales

---

## Final Note

La seguridad del proyecto depende de la colaboración de todos los integrantes. Cualquier vulnerabilidad, error o comportamiento inesperado debe reportarse de manera responsable para poder corregirse y mantener el sistema estable, confiable y seguro.
