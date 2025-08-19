# 📚 Book Service API (Spring Boot + Keycloak)

Proyek ini adalah contoh implementasi **REST API untuk manajemen buku** dengan integrasi **Keycloak** sebagai Identity Provider.  
Dibangun menggunakan teknologi modern untuk memudahkan belajar **Spring Boot** dan **autentikasi berbasis token**.

---

## 🚀 Fitur Utama
- CRUD Book (Create, Read, Update, Delete)
- Integrasi **Keycloak** untuk autentikasi & otorisasi
- Dokumentasi API dengan Swagger UI
- Konfigurasi menggunakan **Docker** untuk Keycloak

---

## ⚙️ Teknologi yang Digunakan
- ☕ Java 21
- 🚀 Spring Boot 3.x
- 🗄️ Spring Data JPA
- 🐘 PostgreSQL
- 🔑 Keycloak 21+
- 📖 Swagger (Springdoc OpenAPI)
- 🐳 Docker

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
    - **Client** -> **Create Client** _isi client id sesuai kebutuhan
    - **Valid redirect URIs** isi dengan **http://localhost:8081/api/swagger-ui/oauth2-redirect.html**
    - web origins isi dengan **http://localhost:8081**
    - di **Capability config** on kan saja ke **Client authentication** dan **Authorization**, lalu ceklis bagian **Standard flow** dan **Direct access grants**
- Buat **Realm Roles** lalu **Create Role** dan berikan name nya **ADMIN**
- Buat User :
    - isi saja bagian **General** lalu **Create**
    - masuk ke menu **Credentials** lalu set **Password** untuk **Temporary** di matikan saja
    - dan sekarang ke menu **Role Mapping** lalu **Assign Role** pilih **Realm Roles**, pilih role **ADMIN**

### 4. Akses Swagger
- jalankan springboot dengan perintah
  ```bash
  mvn spring-boot:run
  ```
- buka browser dan akses
  ```bash
  http://localhost:8081/api/swagger-ui/index.html#
  ```
- Klik tombol Authorize, lalu masukkan:
    - Client ID (sesuai konfigurasi)
    - Client Secret (bisa didapatkan di menu Client → Credentials di Keycloak)

## ✨ Notes
- Pastikan konfigurasi database application.properties sesuai dengan environment lokal.
- Jalankan Keycloak terlebih dahulu sebelum menjalankan aplikasi.
- Repo ini bisa jadi template untuk proyek dengan kebutuhan Auth + CRUD API menggunakan Spring Boot.









