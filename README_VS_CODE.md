# Backend GuauMiau - Spring Boot con Kotlin

## 🚀 Guía para Visual Studio Code

Este backend Spring Boot está diseñado para integrarse con tu aplicación Android "AppGuauMiau".

---

## 📋 Requisitos Previos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

1. **Java Development Kit (JDK) 17 o superior**
   - Verifica con: `java -version`
   - Descarga desde: https://adoptium.net/

2. **Extensiones de VS Code recomendadas:**
   - **Extension Pack for Java** (Microsoft)
   - **Spring Boot Extension Pack** (VMware)
   - **Kotlin** (Mathias Fröhlich)
   - **Gradle for Java** (Microsoft)

---

## 🏗️ Estructura del Proyecto

```
backend-guaumiau/
├── src/main/kotlin/com/example/guaumiau/
│   ├── BackendGuaumiauApplication.kt  # Clase principal
│   ├── model/                         # Entidades JPA
│   │   ├── User.kt
│   │   ├── Pet.kt
│   │   ├── LoginRequest.kt
│   │   └── LoginResponse.kt
│   ├── repository/                    # Interfaces JPA
│   │   ├── UserRepository.kt
│   │   └── PetRepository.kt
│   └── controller/                    # REST Controllers
│       ├── AuthController.kt
│       └── PetController.kt
├── src/main/resources/
│   └── application.properties         # Configuración
└── build.gradle.kts                   # Dependencias Gradle
```

---

## ⚙️ Configuración

El archivo `application.properties` está configurado con:

- **Puerto:** 8080 (accesible desde Android en `http://10.0.2.2:8080`)
- **Base de datos:** H2 en memoria (perfecta para desarrollo)
- **Consola H2:** Disponible en `http://localhost:8080/h2-console`
  - JDBC URL: `jdbc:h2:mem:testdb`
  - Usuario: `sa`
  - Contraseña: (vacía)

---

## 🎯 Ejecutar el Backend desde VS Code

### Opción 1: Usando la Terminal Integrada

1. Abre la terminal en VS Code: `Ctrl + Ñ` o `View > Terminal`
2. Ejecuta el siguiente comando:

```cmd
gradlew.bat bootRun
```

3. Espera a ver el mensaje: `Tomcat started on port 8080`

### Opción 2: Usando el botón Run

1. Abre el archivo `BackendGuaumiauApplication.kt`
2. Busca el icono ▶️ (Run) junto a la función `main`
3. Haz clic en "Run" o "Debug"

### Opción 3: Usando Spring Boot Dashboard

1. Si instalaste "Spring Boot Extension Pack", verás el panel "Spring Boot Dashboard" en la barra lateral
2. Expande "backend-guaumiau"
3. Haz clic en el botón ▶️ para iniciar la aplicación

---

## 🔌 Endpoints de la API

### Autenticación (`/auth`)

#### Registrar Usuario
```http
POST http://localhost:8080/auth/register
Content-Type: application/json

{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "password": "123456",
  "phone": "555-1234",
  "pets": []
}
```

#### Login
```http
POST http://localhost:8080/auth/login
Content-Type: application/json

{
  "email": "juan@example.com",
  "password": "123456"
}
```

**Respuesta:**
```json
{
  "token": "token_falso_1"
}
```

### Mascotas (`/pets`)

#### Obtener todas las mascotas
```http
GET http://localhost:8080/pets
```

#### Agregar mascota
```http
POST http://localhost:8080/pets
Content-Type: application/json

{
  "name": "Firulais",
  "type": "Perro"
}
```

#### Actualizar mascota
```http
PUT http://localhost:8080/pets/1
Content-Type: application/json

{
  "name": "Firulais Jr",
  "type": "Perro"
}
```

#### Eliminar mascota
```http
DELETE http://localhost:8080/pets/1
```

---

## 🧪 Probar la API

### Con REST Client (Extensión de VS Code)

1. Instala la extensión **REST Client** (Huachao Mao)
2. Crea un archivo `test-api.http` en la raíz del proyecto
3. Copia los ejemplos de endpoints anteriores
4. Haz clic en "Send Request" sobre cada petición

### Con Thunder Client (Extensión de VS Code)

1. Instala **Thunder Client** (Ranga Vadhineni)
2. Abre el panel de Thunder Client en la barra lateral
3. Crea una nueva colección "GuauMiau API"
4. Agrega las peticiones manualmente

### Con Postman

1. Descarga Postman desde: https://www.postman.com/
2. Importa los endpoints desde el archivo JSON (si lo creas)

---

## 📱 Integración con Android

### Configuración en tu App Android

1. **Base URL en Retrofit:** `http://10.0.2.2:8080/`
   - `10.0.2.2` es la IP especial del emulador para acceder al localhost de tu PC

2. **AndroidManifest.xml:**
   ```xml
   <uses-permission android:name="android.permission.INTERNET" />
   
   <application
       android:usesCleartextTraffic="true"
       ...>
   ```

3. **Ejemplo de Retrofit (di/AppModule.kt):**
   ```kotlin
   @Provides
   @Singleton
   fun provideRetrofit(): Retrofit {
       return Retrofit.Builder()
           .baseUrl("http://10.0.2.2:8080/")
           .addConverterFactory(GsonConverterFactory.create())
           .build()
   }
   ```

---

## 🛠️ Solución de Problemas

### El servidor no arranca

- **Error de puerto ocupado:** Cambia el puerto en `application.properties`:
  ```properties
  server.port=8081
  ```
- **Java no encontrado:** Asegúrate de tener JDK 17 instalado y en el PATH

### La app Android no se conecta

- **Verifica la URL:** `http://10.0.2.2:8080/` (no uses `localhost` ni `127.0.0.1`)
- **Firewall:** Asegúrate de que el firewall de Windows permita conexiones al puerto 8080
- **Cleartext Traffic:** Verifica `android:usesCleartextTraffic="true"` en el manifest

### Base de datos se reinicia

- H2 está en memoria, los datos se pierden al reiniciar
- Para persistencia, cambia a MySQL o PostgreSQL en `application.properties`

---

## 📊 Ver la Base de Datos (H2 Console)

1. Con el servidor corriendo, abre: `http://localhost:8080/h2-console`
2. Usa estas credenciales:
   - **JDBC URL:** `jdbc:h2:mem:testdb`
   - **User Name:** `sa`
   - **Password:** (dejar vacío)
3. Haz clic en "Connect"
4. Ejecuta consultas SQL directamente:
   ```sql
   SELECT * FROM USERS;
   SELECT * FROM PETS;
   ```

---

## 🔒 Notas de Seguridad

⚠️ **Este backend es para DESARROLLO solamente:**

- Las contraseñas se almacenan en texto plano
- El "token" es falso (no es un JWT real)
- No hay validación de autorización
- La base de datos H2 es en memoria

**Para producción, debes implementar:**
- Encriptación de contraseñas (BCrypt)
- JWT real con Spring Security
- Base de datos persistente (MySQL, PostgreSQL)
- Validación de roles y permisos
- HTTPS/SSL

---

## 📝 Comandos Útiles

```cmd
# Compilar el proyecto
gradlew.bat build

# Ejecutar el proyecto
gradlew.bat bootRun

# Limpiar y compilar
gradlew.bat clean build

# Ejecutar tests
gradlew.bat test

# Ver dependencias
gradlew.bat dependencies
```

---

## 🎓 Próximos Pasos

1. ✅ Backend funcionando en puerto 8080
2. 📱 Conecta tu app Android
3. 🧪 Prueba registro y login
4. 🐶 Prueba el CRUD de mascotas
5. 🔐 Implementa JWT real (opcional)
6. 💾 Migra a base de datos real (opcional)

---

## 📚 Recursos Adicionales

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Kotlin + Spring Boot](https://spring.io/guides/tutorials/spring-boot-kotlin/)
- [JPA con Kotlin](https://kotlinlang.org/docs/jpa.html)
- [Retrofit Documentation](https://square.github.io/retrofit/)

---

¡Tu backend está listo para desarrollar! 🚀
