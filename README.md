# 📚 Book Service API (Spring Boot + Keycloak)

Proyek ini adalah contoh implementasi **REST API untuk manajemen buku** menggunakan:
- **Spring Boot** sebagai backend framework
- **Spring Data JPA** untuk akses database
- **PostgreSql** untuk database
- **Keycloak** untuk autentikasi & otorisasi
- **Swagger** untuk dokumentasi API

---

## 🚀 Fitur Utama
- CRUD Book (Create, Read, Update, Delete)
- Integrasi **Keycloak** sebagai Identity Provider
- Dokumentasi API dengan Swagger UI

---

## ⚙️ Teknologi yang Digunakan
- Java 21
- Spring Boot 3.x
- Spring Data JPA
- PostgreSql
- Keycloak 21+
- Swagger (Springdoc OpenAPI)
- Docker

---

## 📦 Cara Menjalankan Proyek

### 1. Clone Repository
```bash
git clone https://github.com/Obon14/sprintboot-keycloak-crud.git
cd REPO-NAME
```
### 2. Jalankan Keycloak Via Docker
```bash
docker run -p 127.0.0.1:8080:8080 -e KC_BOOTSTRAP_ADMIN_USERNAME=admin -e KC_BOOTSTRAP_ADMIN_PASSWORD=admin quay.io/keycloak/keycloak:26.3.2 start-dev
```
### 3. Buat Realm, Client, Realm Roles, & User
- Buat realm: **Manage Realm** -> **Create Realm** -> **Realm Name** isi sesuai keinginan anda setelah itu save, lalu pilih realm yang anda buat
- Buat Client:
    - **Client**
    - **Create Client** _isi client id sesuai keinginan anda_ setelah itu next
    - untuk **Valid redirect URIs** isi dengan **http://localhost:8081/api/swagger-ui/oauth2-redirect.html** dan untuk web origins isi dengan **http://localhost:8081**, lalu lanjut kan next
    - di **Capability config** on kan saja ke **Client authentication** dan **Authorization**, lalu ceklis bagian **Standard flow** dan **Direct access grants**
- Buat **Realm Roles** lalu **Create Role** dan berikan name nya **ADMIN**
- Buat User :
    - isi saja bagian **General** lalu **Create**
    - masuk ke menu **Credentials** lalu set **Password** untuk **Temporary** di matikan saja
    - dan sekarang ke menu **Role Mapping** lalu **Assign Role** pilih **Realm Roles**, pilih role **ADMIN**

### 4. Jalankan Swagger
- jalankan springboot dengan perintah
  ```bash
  mvn spring-boot:run
  ```
- masuk ke browser dan akses
  ```bash
  http://localhost:8081/api/swagger-ui/index.html#
  ```
- lalu klik bagian **Authorize** di sinih masukan **client id** yang anda buat serta masukan **Client Secret** untuk mendaptkannya masuk ke menu **Client** dan ada menu **Credentials** copy kan saja lalu masukan ke swager

## ✨ Notes
- konfigurasi database sesuikan dengan milik anda
- pastikan keycloak sebelum menjalankan project









