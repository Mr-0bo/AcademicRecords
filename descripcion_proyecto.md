digraph DFD_Nivel_1 {

graph [fontname="Arial", rankdir=LR, bgcolor="white", labelloc="t", fontsize=20, splines=ortho, nodesep=0.7, ranksep=1.0];
node [fontname="Arial", fontsize=11, margin="0.12,0.08"];
edge [fontname="Arial", fontsize=9, color="#374151", arrowsize=0.8];

label="DFD - Nivel 1\nDetalle del flujo de autenticación, administración y calificaciones";

admin [shape=box, style="rounded,filled", fillcolor="#EEF2FF", color="#4F46E5", label="Administrador"];
prof [shape=box, style="rounded,filled", fillcolor="#ECFDF5", color="#059669", label="Profesor"];
alumno [shape=box, style="rounded,filled", fillcolor="#FFF7ED", color="#EA580C", label="Alumno"];

p11 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="1.1\nRecibir credenciales"];
p12 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="1.2\nValidar campos"];
p13 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="1.3\nConsultar usuario\npor correo"];
p14 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="1.4\nVerificar contraseña\nBCrypt"];
p15 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="1.5\nCrear sesión y\nredirigir por rol"];

p21 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="2.1\nRegistrar / editar\nalumnos"];
p22 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="2.2\nRegistrar / editar\nprofesores"];
p23 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="2.3\nRegistrar carreras\ny materias"];
p31 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="3.1\nCrear grupo\npor materia y profesor"];
p32 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="3.2\nInscribir alumno\nal grupo"];
p33 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="3.3\nGenerar registro\nde calificación inicial"];

p41 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="4.1\nCapturar porcentajes\ny mínima aprobatoria"];
p42 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="4.2\nValidar que porcentajes\nsumen 100%"];

p51 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="5.1\nProfesor selecciona\ngrupo"];
p52 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="5.2\nCargar alumnos\ny calificaciones"];
p53 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="5.3\nValidar notas\n0 a 10"];
p54 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="5.4\nCalcular promedio\ny estatus"];
p55 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="5.5\nGuardar calificación\nactualizada"];

p61 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="6.1\nAlumno solicita\nmis calificaciones"];
p62 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="6.2\nConsultar inscripciones\ndel alumno"];
p63 [shape=ellipse, style="filled", fillcolor="#F8FAFC", color="#111827", label="6.3\nMostrar tabla de\ncalificaciones"];

d1 [shape=cylinder, style="filled", fillcolor="#FEF3C7", color="#D97706", label="D1\nUsuario, alumno,\nprofesor, admin"];
d2 [shape=cylinder, style="filled", fillcolor="#FEF3C7", color="#D97706", label="D2\nCarrera y\nmateria"];
d3 [shape=cylinder, style="filled", fillcolor="#FEF3C7", color="#D97706", label="D3\nGrupo e\ninscripción"];
d4 [shape=cylinder, style="filled", fillcolor="#FEF3C7", color="#D97706", label="D4\nConfiguración\nde evaluación"];
d5 [shape=cylinder, style="filled", fillcolor="#FEF3C7", color="#D97706", label="D5\nCalificación"];

admin -> p11 [label="Credenciales"];
prof -> p11 [label="Credenciales"];
alumno -> p11 [label="Credenciales"];
p11 -> p12 [label="Correo y contraseña"];
p12 -> p13 [label="Datos completos"];
p13 -> d1 [label="Buscar por correo"];
d1 -> p14 [label="Usuario encontrado\nhash y rol"];
p14 -> p15 [label="Acceso válido"];
p15 -> admin [label="Panel administrador"];
p15 -> prof [label="Panel profesor"];
p15 -> alumno [label="Panel alumno"];
p12 -> admin [label="Error: campos vacíos", style=dashed];
p14 -> admin [label="Error: credenciales inválidas", style=dashed];

admin -> p21 [label="Datos alumno\nmatrícula, carrera y contraseña"];
admin -> p22 [label="Datos profesor\nnúmero empleado y contraseña"];
admin -> p23 [label="Nombre carrera/materia\nsemestre"];
p21 -> d1 [label="Guardar usuario tipo ALUMNO"];
p21 -> d2 [label="Consultar carrera"];
p22 -> d1 [label="Guardar usuario tipo PROFESOR"];
p23 -> d2 [label="Guardar carrera/materia"];
d1 -> p21 [label="Alumnos registrados"];
d1 -> p22 [label="Profesores registrados"];
d2 -> p23 [label="Catálogo académico"];
p21 -> admin [label="Alumno guardado/eliminado"];
p22 -> admin [label="Profesor guardado/eliminado"];
p23 -> admin [label="Carrera/materia guardada"];

admin -> p31 [label="Materia, profesor y periodo"];
p31 -> d2 [label="Validar materia"];
p31 -> d1 [label="Validar profesor"];
p31 -> d3 [label="Guardar grupo"];
admin -> p32 [label="Alumno y grupo"];
p32 -> d1 [label="Validar alumno"];
p32 -> d3 [label="Guardar inscripción"];
p32 -> p33 [label="Inscripción creada"];
p33 -> d5 [label="Calificación inicial en 0"];
p33 -> admin [label="Registro académico listo"];

admin -> p41 [label="% parciales, % actividades, % proyecto, mínima"];
p41 -> p42 [label="Reglas capturadas"];
p42 -> d4 [label="Guardar si suman 100%"];
p42 -> admin [label="Error si configuración inválida", style=dashed];
d4 -> admin [label="Configuración consultable"];

prof -> p51 [label="Selecciona grupo"];
p51 -> d3 [label="Buscar grupos del profesor"];
d3 -> p52 [label="Grupos e inscritos"];
p52 -> d5 [label="Leer calificaciones"];
d5 -> p52 [label="Calificaciones actuales"];
p52 -> prof [label="Tabla de alumnos"];
prof -> p53 [label="Parcial 1, 2, 3, actividades, proyecto"];
p53 -> d4 [label="Solicitar reglas por materia"];
d4 -> p54 [label="Porcentajes y mínima"];
p53 -> p54 [label="Notas válidas"];
p54 -> p55 [label="Promedio final + aprobado/reprobado"];
p55 -> d5 [label="Actualizar calificación"];
p55 -> prof [label="Confirmación y resultado"];
p53 -> prof [label="Error si nota fuera de 0 a 10", style=dashed];

alumno -> p61 [label="Consultar calificaciones"];
p61 -> p62 [label="ID alumno en sesión"];
p62 -> d3 [label="Buscar inscripciones"];
d3 -> p62 [label="Inscripciones del alumno"];
p62 -> d5 [label="Buscar calificación por inscripción"];
d5 -> p63 [label="Parciales, actividades, proyecto, final, estatus"];
p63 -> alumno [label="Tabla de calificaciones"];
}
