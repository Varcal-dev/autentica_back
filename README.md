# 🛍️ Auténtica - Tienda Online

**Auténtica** es una plataforma de comercio electrónico desarrollada en **Spring Boot** (backend) y **MySQL**, que permite la gestión completa de productos, carritos, pedidos, usuarios, reseñas y cupones de descuento.

---

## 🚀 Características principales

### 👤 Módulo de Usuarios

* Registro y autenticación de usuarios.
* Gestión de roles (ADMIN, CLIENTE, etc.).
* Estados de usuario (ACTIVO, INACTIVO, ELIMINADO).
* Asociación de direcciones a cada usuario.

### 📦 Módulo de Productos

* CRUD de productos y variantes.
* Gestión de categorías, marcas y stock.
* Control de precios y estado del producto.

### 🛒 Módulo de Carrito de Compras

* Carrito asociado a cada usuario.
* Agregar, actualizar o eliminar productos del carrito.
* Cálculo automático del total del carrito.

### 📑 Módulo de Pedidos

* Creación de pedidos a partir del carrito.
* Registro de dirección de entrega y método de pago.
* Gestión de estados de pedido (PENDIENTE, PAGADO, ENVIADO, ENTREGADO, CANCELADO).
* Historial de pedidos por usuario.

### 💬 Módulo de Reseñas

* Publicación de reseñas y calificaciones por producto.
* Control de duplicidad por usuario y producto.
* Promedio de calificaciones visible en el producto.

### 🎟️ Módulo de Cupones y Descuentos

* Validación y aplicación de cupones a pedidos.
* Control de fecha de expiración, número de usos y estado.
* Cálculo de descuentos automáticos sobre el total del pedido.

---

## 🧩 Arquitectura del Proyecto

**Backend:** Spring Boot 3
**Base de datos:** MySQL
**ORM:** Hibernate / JPA
**Lombok:** Para simplificar el código (Getters, Setters, Builders, etc.)
**JSON:** Jackson (control de referencias cíclicas entre entidades)

### 📁 Estructura de Paquetes

```
com.autentica
 ┣ 📂 controller       → Controladores REST
 ┣ 📂 service          → Lógica de negocio
 ┣ 📂 repository       → Interfaces JPA
 ┣ 📂 model/entity     → Entidades JPA
 ┣ 📂 dto              → Objetos de transferencia de datos
 ┣ 📂 enums            → Enumeraciones del sistema
 ┗ 📂 config           → Configuración general
```

---

## 🛠️ Requisitos

* **Java 17 o superior**
* **Maven 3.8+**
* **MySQL 8.0+**

---

## ⚙️ Instalación y Ejecución

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/tuusuario/autentica.git
   ```

2. **Configurar la base de datos en `application.properties`:**

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/autentica_db
   spring.datasource.username=root
   spring.datasource.password=tu_contraseña
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Ejecutar el proyecto:**

   ```bash
   mvn spring-boot:run
   ```

4. **Acceder a la API:**

   * URL base: `http://localhost:8080/api`

---

## 📘 Endpoints principales

| Módulo    | Método | Endpoint           | Descripción                 |
| --------- | ------ | ------------------ | --------------------------- |
| Usuarios  | `POST` | `/usuarios`        | Registrar usuario           |
| Productos | `GET`  | `/productos`       | Listar productos            |
| Carrito   | `POST` | `/carrito/agregar` | Agregar producto al carrito |
| Pedidos   | `POST` | `/pedidos`         | Crear pedido desde carrito  |
| Reseñas   | `POST` | `/reseñas`         | Crear reseña de producto    |
| Cupones   | `POST` | `/cupones/validar` | Validar cupón               |

---

## 🧠 Próximas Mejoras

* Implementación de autenticación JWT.
* Integración con pasarela de pagos.
* Panel administrativo con estadísticas.
* Integración con frontend (React / Next.js).

---

## 👨‍💻 Autor

**Leand Varcal**
Desarrollador FullStack - Proyecto **Auténtica**
📧 contacto: [[cesarlea2010@hotmail.com](mailto:cesarlea2010@hotmail.com)]

---

## 🪪 Licencia

Este proyecto está bajo la licencia **MIT**. Puedes usarlo, modificarlo y distribuirlo libremente con atribución.
