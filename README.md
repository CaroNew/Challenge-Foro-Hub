# Challenge-Foro-Hub
Reto Oracle/Alura Back-end

## Objetivo: 

Replicar el proceso de creación de un foro a nivel de back end creando una API REST con las siguientes funcionalidades:

- API con rutas implementadas siguiendo las mejores prácticas del modelo REST;

- Validaciones realizadas según las reglas de negocio;

- Implementación de una base de datos relacional para la persistencia de la información;

- Servicio de autenticación/autorización para restringir el acceso a la información.

## Tecnologias utilizadas

- Java JDK: versión: 17 en adelante 

- Maven: versión 4 en adelante

- Spring: versión 3.2.3 - https://start.spring.io/

- MySQL: versión 8 en adelante 

- IDE (Entorno de desenvolvimento integrado) IntelliJ IDEA- opcional -


Configuración al crear el proyecto en Spring Initializr:

- Java (versión 17 en adelante)

- Maven (Initializr utiliza la versión 4)

- Spring Boot (versión 3.2.3)

- Proyecto en JAR

Dependencias para agregar al crear el proyecto con Spring Initializr:

- Lombok

- Spring Web

- Spring Boot DevTools

- Spring Data JPA

- Flyway Migration

- MySQL Driver

- Validation

- Spring Security


## Cómo probar esta API


Modificar el archivo: [application.properties](./literalura/src/main/resources/application.properties) reemplazando las varibles DB_NAME, DB_USER, DB_PASSWORD por el nombre de una base de datos creada, el nombre de usuario y contraseña respectivamente o declarado las variables de entorno de su sistema operativo, luego ejecutar el archivo ForohubApplication.java.


## Funcionalidad

**Registro (con permiso USER por defecto)**

- Ruta:

```
POST: /api/auth/registro 
```

- Parametros para enviar en el cuerpo del json:

```
{
    "nombre":"John Doe",
    "email":"testmail@mail.com",
    "clave":"password1234"
}	

```

**Autenticarse**

- Ruta:

```
POST: /auth/login

```
- En el cuerpo:

```
{
    "email":"testmail@gmail.com",
    "clave":"password1234"
}
```
---

**Crear un tema**

- Ruta:

```
POST: /api/temas
```

- Cuerpo:

```
{
    "titulo":"Ayuda!!!! Creo que tengo un problema...",
    "mensaje":"He hecho un delete pero ya no me aparecen los datos :'(",
    "usuario_id": "1",
    "nombre_curso":"Bases de Datos Avanzadas"
}
```

**Obtener todos los temas**


- Ruta:

```
GET: /api/temas
```

- Cuerpo:

```
{ 
}
```
**Obtener un tema por id**
- Ruta:

```
GET: /api/temas/{id}
```

- Cuerpo:

```
{   
}
```
**Actualizar un tema**
- Ruta:

```
UPDATE: /api/temas/{id}
```

- Cuerpo:

```
{
    "id": 0,
    "titulo": "string",
    "mensaje": "string",
    "status": "ABIERTO/CERRADO"
}
```

**Borrar un tema (borrado lógico)**

- Ruta:

```
DELETE: /api/temas/{id}
```

- Cuerpo:

```
{   
}
```

---


## Swagger disponible!

Para probar esta api con Swagger docs, solo tienes que correr el proyecto y colocar en tu navegador favorito la siguiente ruta: http://server:port/context-path/v3/api-docs, donde:
- server = el nombre del servidor o ip
- port = el puerto del servidor
- context-path = la ruta de la aplicacion.

Probablemente es [esta](http://localhost:8080/swagger-ui/index.html#/tema-controller/update), ¿adiviné?. No olvides resgistrarte y autenticarte antes de probar los endpoints.


## Decisiones de diseño

- Cuando un usuario se registra, se crea por defecto un usuario con rol USER, este usuario solo tiene permiso de crear, ver todos y modificar los temas/respuestas  

- Cuando se realiza el delete de los temas/usuarios/respuestas se hace un borrado logico, actualizando el campo activo de la tabla correspondiente.


## En construccion...

Los endpoints usuarios/respuestas ya son funcionales pero todavia estan en construcción. 
