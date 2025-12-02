# Sistema de Gestión de Usuarios

Proyecto creado con **Spring Boot + Java + MySQL** para gestionar usuarios, realizar login y administrar áreas y clientes.  
Incluye encriptación de contraseñas, manejo de estatus y relaciones entre entidades.

Este proyecto está pensado como una estructura simple pero funcional, con separación de responsabilidades (Controladores, Servicios, Repositorios, DTOs y Entidades).

---

# Tecnologías utilizadas

### **Backend**
- Java 21
- Spring Boot (Web, JPA, Validation)
- MySQL
- Hibernate
- Maven
- Postman / Insomnia para pruebas

---

# Requisitos previos

Asegúrate de tener instalado:

- **Java 21**
- **Maven**
- **MySQL Server**
- **Postman/Insomnia** (opcional para pruebas)
- **Git**

---

# Configuración de la Base de Datos

Crea una base de datos en MySQL:

```sql
CREATE DATABASE sistema_usuarios;
```
# Edita el archivo application.properties
Agrega lo siguiente reemplace user password por tus credenciales de acceso al servidor de MySQL:

```vim
spring.datasource.url=jdbc:mysql://localhost:3306/sistema_usuarios
spring.datasource.username=user
spring.datasource.password=password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

---
# Ejecucion del proyecto

* ### Clona el repositorio

```bash
    git clone url
```
* ### Entra al proyecto

```bash
    cd proyect
```

* ###  Complila e instala dependencias

```bash
    mvn clean install
```

* ### Ejecuta
```bash
    mvn spring-boot:run
```

# Estructura del proyecto
```
src/main/java
└── pruebaAdeA
├── Controllers
│ ├── AreaController.java
│ ├── AuthController.java
│ ├── ClientController.java
│ └── UserController.java
├── Dtos
│ ├── Request
│ │ ├── AreaRequestDto.java
│ │ ├── ClientRequestDto.java
│ │ ├── LoginRequestDto.java
│ │ └── UserRequestDto.java
│ └── Response
│     ├── AreaResponseDto.java
│     ├── ClientResponseDto.java
│     ├── LoginResponseDto.java
│     └── UserResponseDto.java
├── Models
│ ├── AreaModel.java
│ ├── ClientModel.java
│ ├── StatusUserModel.java
│ └── UserModel.java
├── PruebaDeCrudYLoginApplication.java
├── Repositories
│ ├── AreaRepository.java
│ ├── ClientRepository.java
│ └── UserRepository.java
├── Services
│ ├── Areas
│ │ ├── AreaServiceImpl.java
│ │ └── AreaService.java
│ ├── Clients
│ │ ├── ClientServiceImpl.java
│ │ └── ClientService.java
│ └── Users
│     ├── UserServiceImpl.java
│     └── UserService.java
└── Utils
└── PasswordUtil.java
```