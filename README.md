# 🛒 API REST - Sistema de Gestión de Supermercado

## 📝 Descripción
Este proyecto es el backend de un sistema de gestión para supermercados, desarrollado como una API RESTful.
Su objetivo es centralizar la lógica de negocio y el manejo de datos para administrar el inventario de productos, registrar ventas y controlar
el flujo de caja, exponiendo endpoints listos para ser consumidos por cualquier cliente (frontend o aplicación móvil).

## ✨ Características Principales
* **Gestión de Inventario (CRUD):** Creación, lectura, actualización y eliminación de productos, control de stock y categorización.
* **Módulo de Ventas:** Registro transaccional de compras, cálculo de totales y actualización automática del stock.
* **Persistencia Integrada:** Uso de base de datos H2 en memoria para facilitar el despliegue y las pruebas sin configuraciones externas.

## 🛠️ Tecnologías y Herramientas Utilizadas
* **Lenguaje:** Java
* **Framework:** Spring Boot
* **Base de Datos:** H2 Database (En memoria)
* **Gestor de Dependencias:** Maven
* **IDE:** IntelliJ IDEA

## ⚙️ Instalación y Configuración Local

### Requisitos Previos
* Tener instalado JDK 25.
* Git instalado en tu equipo.

### Pasos para ejecutar

1. **Clonar el repositorio:**
   ```bash
   git clone [https://github.com/tu-usuario/sistema-supermercado.git](https://github.com/tu-usuario/sistema-supermercado.git)

   **Navegar al directorio del proyecto:**
   cd sistema-supermercado

   **Ejecutar el proyecto:
   Al utilizar una base de datos H2 en memoria, no necesitas configurar servidores externos. Simplemente levanta la aplicación desde
   tu IDE (ejecutando la clase principal anotada con @SpringBootApplication)**

   **Acceso a la Base de Datos H2:
   Una vez que la aplicación esté corriendo, puedes administrar las tablas ingresando a la consola de H2 desde el navegador con las siguientes credenciales:
   URL de la consola: http://localhost:8080/h2-console
   JDBC URL: jdbc:h2:mem:superdb;MODE=MySQL;DB_CLOSE_DELAY=-1
   User Name: mariodufour
   Password: (dejar en blanco)**

   **📡 Endpoints Principales (Ejemplos)
   Para interactuar con la API, puedes utilizar herramientas como Postman.
   La API responde a las operaciones estándar de un CRUD utilizando los métodos HTTP correspondientes.

   🏢 Sucursales
   GET /api/sucursales - Obtiene el listado de todas las sucursales.
   POST /api/sucursales - Crea una nueva sucursal.
   PUT /api/sucursales/{id} - Actualiza los datos de una sucursal específica.
   DELETE /api/sucursales/{id} - Elimina una sucursal por su ID.

   📦 Productos
   GET /api/productos - Obtiene el listado de todos los productos.
   POST /api/productos - Crea un nuevo producto.
   PUT /api/productos/{id} - Actualiza los datos de un producto específico.
   DELETE /api/productos/{id} - Elimina un producto por su ID.

   🧾 Ventas
   GET /api/ventas - Obtiene el historial de ventas.
   POST /api/ventas - Registra una nueva venta.
   PUT /api/ventas/{id} - Actualiza los datos de una venta específica.
   DELETE /api/ventas/{id} - Anula o elimina una venta por su ID.**

   **🏗️ Arquitectura
   El proyecto sigue una arquitectura en capas basada en el ecosistema de Spring Boot:
   Controllers: Manejan las peticiones HTTP REST y devuelven las respuestas estructuradas (ResponseEntity).
   Services: Contienen toda la lógica de negocio y actúan como intermediarios.
   Repositories: Interfaces de Spring Data JPA para la persistencia de datos.
   DTOs (Data Transfer Objects): Utilizados para transferir datos entre el cliente y el servidor de forma segura, ocultando las entidades internas.**
