# 🍳 API de Gestión de Recetas

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.8-brightgreen)
![Maven](https://img.shields.io/badge/Maven-4.0.0-blue)
![License](https://img.shields.io/badge/License-Apache%202.0-blue)

API RESTful para la gestión de recetas de cocina, categorías e ingredientes con relación Many-to-Many.

## 📋 Tabla de Contenidos

- [Descripción](#-descripción)
- [Características](#-características)
- [Tecnologías](#️-tecnologías)
- [Requisitos Previos](#-requisitos-previos)
- [Instalación](#-instalación)
- [Uso](#-uso)
- [Endpoints](#-endpoints)
- [Documentación Swagger](#-documentación-swagger)
- [Modelo de Datos](#-modelo-de-datos)
- [Ejemplos](#-ejemplos)
- [Colección Postman](#-colección-postman)
- [Autor](#-autor)

## 🎯 Descripción

Esta API permite gestionar recetas de cocina de manera completa, incluyendo:
- Categorización de recetas
- Gestión de ingredientes
- Asignación de ingredientes a recetas con cantidades y unidades específicas
- Relación Many-to-Many entre recetas e ingredientes con datos adicionales

El proyecto implementa las mejores prácticas de desarrollo con Spring Boot, incluyendo manejo de errores personalizado, DTOs para transferencia de datos y documentación completa con OpenAPI 3.0.

## ✨ Características

### 📂 Categorías (CRUD Completo)
- ✅ Crear nuevas categorías
- ✅ Listar todas las categorías
- ✅ Obtener categoría por ID
- ✅ Editar categorías existentes
- ✅ Eliminar categorías
- ✅ Validación de nombres duplicados

### 🍽️ Recetas (CRUD Completo)
- ✅ Crear recetas (requiere categoría existente)
- ✅ Listar todas las recetas
- ✅ Obtener receta por ID con ingredientes
- ✅ Editar recetas
- ✅ Eliminar recetas
- ✅ Validación de tiempo de preparación
- ✅ Niveles de dificultad (FACIL, MEDIA, DIFICIL)

### 🥕 Ingredientes (CRUD Básico)
- ✅ Crear ingredientes
- ✅ Listar todos los ingredientes
- ✅ Validación de nombres duplicados

### 🔗 Gestión de Ingredientes en Recetas (M:M)
- ⭐ Añadir ingredientes a recetas con cantidad y unidad
- ⭐ Tabla de enlace con datos adicionales
- ⭐ Control de duplicados
- ⭐ Consulta de recetas con ingredientes completos

## 🛠️ Tecnologías

| Tecnología | Versión | Descripción |
|------------|---------|-------------|
| **Java** | 21 | Lenguaje de programación |
| **Spring Boot** | 3.5.8 | Framework principal |
| **Spring Data JPA** | - | Persistencia de datos |
| **Spring Web** | - | API REST |
| **H2 Database** | - | Base de datos en memoria |
| **Lombok** | - | Reducción de código  |
| **SpringDoc OpenAPI** | 2.3.0 | Documentación Swagger |
| **Maven** | - | Gestión de dependencias |

## 📋 Requisitos Previos

- **JDK 21** o superior
- **Maven 3.6+** (incluido en el proyecto con Maven Wrapper)
- **IDE** recomendado: IntelliJ IDEA, Eclipse o VS Code

## 🚀 Instalación

### 1. Clonar el repositorio

```bash
git clone https://github.com/peedrodiiaz/AD-PSP-Recetas-api-PedroDiaz.git
cd AD-PSP-Recetas-api-PedroDiaz/ProyectoApiRecetas
```

La aplicación se iniciará en `http://localhost:8080`

## 💻 Uso

### Verificar que la API está funcionando

```bash
curl http://localhost:8080/ingredientes
```

### Acceder a la base de datos H2 (desarrollo)

```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:testdb
Usuario: sa
Contraseña: (dejar vacío)
```

## 📍 Endpoints

### Categorías

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/categorias/` | Listar todas las categorías |
| `GET` | `/categorias/{id}` | Obtener categoría por ID |
| `POST` | `/categorias/` | Crear nueva categoría |
| `PUT` | `/categorias/{id}` | Editar categoría |
| `DELETE` | `/categorias/{id}` | Eliminar categoría |

### Recetas

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/recetas` | Listar todas las recetas |
| `GET` | `/recetas/{id}` | Obtener receta con ingredientes |
| `POST` | `/recetas` | Crear nueva receta |
| `PUT` | `/recetas/{id}` | Editar receta |
| `DELETE` | `/recetas/{id}` | Eliminar receta |
| `POST` | `/recetas/{id}/ingredientes` | ⭐ Añadir ingrediente a receta |

### Ingredientes

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| `GET` | `/ingredientes` | Listar todos los ingredientes |
| `POST` | `/ingredientes` | Crear nuevo ingrediente |

## 📖 Documentación Swagger

### Acceso a Swagger UI

Una vez iniciada la aplicación:

**Interfaz interactiva:**
```
http://localhost:8080/swagger-ui/index.html
```

**Especificación OpenAPI JSON:**
```
http://localhost:8080/v3/api-docs
```

### Características de Swagger

- ✅ Documentación completa de todos los endpoints
- ✅ Ejemplos de peticiones y respuestas
- ✅ Pruebas interactivas desde el navegador
- ✅ Schemas de DTOs documentados
- ✅ Códigos de respuesta HTTP
- ⭐ Documentación especial del endpoint M:M con cantidad y unidad

## 🗃️ Modelo de Datos

### Diagrama de Entidades

```
┌─────────────────┐
│   Categoria     │
│─────────────────│
│ id (PK)         │
│ nombre          │
│ descripcion     │
└────────┬────────┘
         │ 1
         │
         │ N
┌────────┴────────┐         ┌──────────────────────┐
│    Receta       │    N    │ RecetaIngrediente    │
│─────────────────│◄────────┤──────────────────────│
│ id (PK)         │         │ id (PK)              │
│ name            │         │ receta_id (FK)       │
│ tiempoPrep      │         │ ingrediente_id (FK)  │
│ dificultad      │         │ cantidad      ⭐     │
│ categoria_id(FK)│         │ unidad        ⭐     │
└─────────────────┘         └────────┬─────────────┘
                                     │ N
                                     │
                                     │ 1
                            ┌────────┴─────────┐
                            │   Ingrediente    │
                            │──────────────────│
                            │ id (PK)          │
                            │ nombre           │
                            └──────────────────┘
```

### Entidades

#### Categoria
```java
{
  "id": 1,
  "nombre": "Postres",
  "descripcion": "Dulces y postres"
}
```

#### Receta
```java
{
  "id": 1,
  "name": "Arroz con pollo",
  "tiempoPreparacion": 45,
  "dificultad": "MEDIA",
  "categoriaId": 1
}
```

#### Ingrediente
```java
{
  "id": 1,
  "nombre": "Arroz"
}
```

#### RecetaIngrediente (Tabla de enlace con datos)
```java
{
  "ingredienteId": 1,
  "cantidad": "200",
  "unidad": "gramos"
}
```

## 📝 Ejemplos

### 1. Crear una categoría

**Request:**
```http
POST /categorias/
Content-Type: application/json

{
  "nombre": "Platos principales",
  "descripcion": "Platos principales para comidas"
}
```

**Response:**
```json
{
  "id": 1,
  "nombre": "Platos principales",
  "descripcion": "Platos principales para comidas"
}
```

### 2. Crear ingredientes

**Request:**
```http
POST /ingredientes
Content-Type: application/json

{
  "nombre": "Arroz"
}
```

### 3. Crear una receta

**Request:**
```http
POST /recetas
Content-Type: application/json

{
  "name": "Paella valenciana",
  "tiempoPreparacion": 60,
  "dificultad": "DIFICIL",
  "categoriaId": 1
}
```

**Response:**
```json
{
  "id": 1,
  "name": "Paella valenciana",
  "tiempoPreparacion": 60,
  "dificultad": "DIFICIL",
  "categoriaName": "Platos principales",
  "ingredientes": []
}
```

### 4. ⭐ Añadir ingrediente a receta (con cantidad y unidad)

**Request:**
```http
POST /recetas/1/ingredientes
Content-Type: application/json

{
  "ingredienteId": 1,
  "cantidad": "400",
  "unidad": "gramos"
}
```

**Response:**
```json
{
  "ingredienteNombre": "Arroz",
  "cantidad": "400",
  "unidad": "gramos"
}
```

### 5. Obtener receta con ingredientes

**Request:**
```http
GET /recetas/1
```

**Response:**
```json
{
  "id": 1,
  "name": "Paella valenciana",
  "tiempoPreparacion": 60,
  "dificultad": "DIFICIL",
  "categoriaName": "Platos principales",
  "ingredientes": [
    {
      "ingredienteNombre": "Arroz",
      "cantidad": "400",
      "unidad": "gramos"
    },
    {
      "ingredienteNombre": "Pollo",
      "cantidad": "500",
      "unidad": "gramos"
    },
    {
      "ingredienteNombre": "Tomate",
      "cantidad": "3",
      "unidad": "unidades"
    }
  ]
}
```

## 📦 Colección Postman

El proyecto incluye una colección completa de Postman con todas las peticiones:

**Archivo:** `API Gestión de Recetas.postman_collection.json`

### Secciones incluidas:
1. **Categorías** - CRUD completo con pruebas de error
2. **Recetas** - CRUD completo con validaciones
3. **Ingredientes** - Crear y listar
4. **Gestión Ingredientes en Recetas** - Endpoint M:M con ejemplos

### Importar en Postman:
1. Abrir Postman
2. Clic en "Import"
3. Seleccionar el archivo `API Gestión de Recetas.postman_collection.json`
4. ¡Listo para probar!

## 🔧 Configuración

### application.properties

```properties
# Base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA/Hibernate
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
```

## 🎯 Características Especiales

### Manejo de Errores

El proyecto incluye manejo global de excepciones:

- `CategoriaNotFoundException` - 404 cuando no se encuentra categoría
- `RecetaNoEncontradaException` - 404 cuando no se encuentra receta
- `IngredienteYaAnadidoException` - 409 cuando el ingrediente ya existe en la receta
- `NombreDuplicadoException` - 409 para nombres duplicados
- `TiempoInvalidoException` - 400 para tiempos de preparación inválidos
- `CategoriaInvalidaException` - 404 cuando la categoría no existe

### DTOs

El proyecto utiliza DTOs para separar la capa de presentación:

- `EditCategoriaDto` - Crear/editar categorías
- `EditRecetaDto` - Crear/editar recetas
- `EditIngredienteDto` - Crear ingredientes
- `AniadirIngredienteDto` - ⭐ Añadir ingrediente con cantidad y unidad
- `ResponseCategoriaDto` - Respuesta de categoría
- `ResponseRecetaDto` - Respuesta de receta con ingredientes
- `ResponseIngredienteDto` - Respuesta de ingrediente
- `IngredienteEnRecetaDto` - Ingrediente con cantidad y unidad

## 📚 Documentación Adicional

- **SWAGGER_README.md** - Guía completa de Swagger UI
- **ENDPOINT_DOCUMENTATION.md** - Documentación detallada del endpoint M:M

## 🤝 Contribuir

1. Fork el proyecto
2. Crea tu rama de características (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📄 Licencia

Este proyecto está bajo la Licencia Apache 2.0 - ver el archivo LICENSE para más detalles.

## 👤 Autor

**Pedro Díaz**
- GitHub: [@peedrodiiaz](https://github.com/peedrodiiaz)
- Proyecto: Grado Superior - Desarrollo de Aplicaciones

## 🙏 Agradecimientos

- Salesianos Triana DAM
- Spring Boot Team
- Comunidad de desarrolladores Java

---

⭐ **Características destacadas:**
- ✅ CRUD completo de Categorías y Recetas
- ✅ CRUD básico de Ingredientes
- ⭐ Relación M:M con datos adicionales (cantidad y unidad)
- 📖 Documentación completa con Swagger
- 🧪 Colección Postman incluida
- 🛡️ Manejo robusto de errores

**¡Disfruta gestionando tus recetas!** 🍽️