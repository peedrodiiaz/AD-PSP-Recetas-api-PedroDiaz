# API de Gestión de Recetas

API REST para gestionar recetas de cocina con Spring Boot.

## Descripción

Proyecto de Grado Superior que permite gestionar recetas, categorías e ingredientes. Incluye relación Many-to-Many entre recetas e ingredientes.

## Tecnologías

- Java 21
- Spring Boot 3.5.8
- Spring Data JPA
- H2 Database (en memoria)
- Maven

## Requisitos

- JDK 21
- Maven 3.6+

## Instalación

1. Clonar el repositorio:
```bash
git clone https://github.com/peedrodiiaz/AD-PSP-Recetas-api-PedroDiaz.git
cd AD-PSP-Recetas-api-PedroDiaz/ProyectoApiRecetas
```

2. Ejecutar la aplicación:
```bash
mvn spring-boot:run
```

La aplicación se inicia en `http://localhost:8080`

## Endpoints

### Categorías
- `GET /categorias/` - Listar todas
- `GET /categorias/{id}` - Obtener por ID
- `POST /categorias/` - Crear nueva
- `PUT /categorias/{id}` - Editar
- `DELETE /categorias/{id}` - Eliminar

### Recetas
- `GET /recetas` - Listar todas
- `GET /recetas/{id}` - Obtener por ID con ingredientes
- `POST /recetas` - Crear nueva
- `PUT /recetas/{id}` - Editar
- `DELETE /recetas/{id}` - Eliminar
- `POST /recetas/{id}/ingredientes` - Añadir ingrediente a receta

### Ingredientes
- `GET /ingredientes` - Listar todos
- `POST /ingredientes` - Crear nuevo

## Documentación Swagger

Acceder a la documentación interactiva:
```
http://localhost:8080/swagger-ui/index.html
```

## Base de datos H2

Para ver la base de datos:
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Usuario: sa
Contraseña: (vacío)
```

## Modelo de Datos

- **Categoria**: id, nombre, descripcion
- **Receta**: id, name, tiempoPreparacion, dificultad, categoria
- **Ingrediente**: id, nombre
- **RecetaIngrediente**: id, receta, ingrediente, cantidad, unidad (relación M:M)

## Ejemplo de Uso

1. Crear una categoría:
```json
POST /categorias/
{
  "nombre": "Platos principales",
  "descripcion": "Platos para comidas"
}
```

2. Crear un ingrediente:
```json
POST /ingredientes
{
  "nombre": "Arroz"
}
```

3. Crear una receta:
```json
POST /recetas
{
  "name": "Paella",
  "tiempoPreparacion": 60,
  "dificultad": "MEDIA",
  "categoriaId": 1
}
```

4. Añadir ingrediente a receta:
```json
POST /recetas/1/ingredientes
{
  "ingredienteId": 1,
  "cantidad": "400",
  "unidad": "gramos"
}
```

## Autor

Pedro Díaz - Grado Superior DAM