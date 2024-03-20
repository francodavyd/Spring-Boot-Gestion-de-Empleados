# Gestion de empleados API Spring Boot
Es una aplicación Spring Boot donde contamos con la entidad Empleado, podremos calcular sus sueldos, generar un pdf con su reporte mensual de cada sueldo y enviarlo a su correo electronico.
Cada empleado tiene una lista de sueldos, al crear un nuevo empleado esta lista esta vacia, luego mediante la creacion de un sueldo de un empleado se asigna y se agrega a su lista de sueldos, que antes estaba vacia.
Si se vuelve a querer crear un sueldo, y en el mes actual ya se ha creado ese sueldo, no podra realizarse la accion. 
Despues podremos generar un pdf con la informacion del sueldo y el empleado, y la enviamos a su correo electronico.

# Instalación
Para probar este proyecto clona este repositorio e importalo a tu IDE favorito, crea una base de datos MySQL llamada "empleados_bd" y luego dirigete al application.properties
y configura las variables mail.username, mail.password con tu correo y contraseña. Para no usar tu contraseña, puedes ir a Verificación en 2 pasos, en tu cuenta de Google y crear una contraseña de aplicación 
para utilizar.
Para ver la documentación de los end-points, con la app ejecutada ve a http://localhost:8080/doc/swagger-ui/index.html
