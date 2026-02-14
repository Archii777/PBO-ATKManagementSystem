# ATK Management System (Java Swing + MariaDB)

## Deskripsi

ATK Management System adalah aplikasi desktop berbasis Java (Swing) yang dirancang untuk mengelola persediaan Alat Tulis Kantor (ATK) secara sederhana dan terstruktur. Sistem ini dibuat sebagai proyek PBO (Pemrograman Berorientasi Objek) dengan arsitektur berlapis (GUI, DAO, Models, Database).

Aplikasi ini mampu melakukan manajemen data barang ATK seperti kertas, pulpen, spidol, dan tinta printer, lengkap dengan fitur CRUD (Create, Read, Update, Delete) serta monitoring stok minimum.

---

## Fitur Utama

* Login Sistem (Admin)
* Manajemen Barang ATK (CRUD)
* Tabel Data Barang (JTable)
* Validasi Input
* Notifikasi Stok Minimum
* Koneksi Database MariaDB (Laragon)
* GUI berbasis NetBeans Swing (.form)
* Arsitektur DAO (Data Access Object)

---

## Teknologi yang Digunakan

* Java JDK 25 (LTS)
* NetBeans IDE 28
* Swing (GUI Builder)
* MariaDB / MySQL
* Laragon (Local Server)
* JDBC (MariaDB Driver)
* Apache Ant (Build System)

---

## Struktur Project

```
ATKManagementSystem/
├── src/
│   ├── main/
│   │   └── Main.java
│   ├── database/
│   │   ├── DatabaseConnection.java
│   │   └── DatabaseInitializer.java
│   ├── models/
│   │   ├── BarangATK.java
│   │   ├── Kertas.java
│   │   ├── Pulpen.java
│   │   ├── Spidol.java
│   │   ├── TintaPrinter.java
│   │   ├── Anggota.java
│   │   └── AdminGA.java
│   ├── dao/
│   │   ├── BarangDAO.java
│   │   └── UserDAO.java
│   ├── gui/
│   │   ├── LoginJFrameForm.java
│   │   └── ManajemenBarangJFrameForm.java
│   └── utils/
│       ├── ValidationUtils.java
│       └── IDGenerator.java
├── nbproject/
├── build.xml
└── manifest.mf
```

---

## Konsep OOP yang Diterapkan

* Inheritance → BarangATK → (Kertas, Pulpen, Spidol, TintaPrinter)
* Polymorphism → Method getKategori()
* Encapsulation → Getter & Setter pada model
* DAO Pattern → Pemisahan logic database dan GUI
* MVC-like Structure → Models, DAO, GUI terpisah

---

## Cara Menjalankan Project (NetBeans + Laragon)

### 1. Jalankan Database (Laragon)

1. Buka Laragon
2. Klik Start All
3. Buka HeidiSQL / phpMyAdmin
4. Buat database:

```
db_atk
```

---

### 2. Konfigurasi Database (Default)

File: `DatabaseConnection.java`

```
URL      : jdbc:mariadb://localhost:3306/db_atk
USER     : root
PASSWORD : (kosong - default Laragon)
```

---

### 3. Import Project ke NetBeans

1. Buka NetBeans 28
2. File → Open Project
3. Pilih folder:

```
ATKManagementSystem
```

4. Klik Open Project

---

### 4. Tambahkan Driver MariaDB (WAJIB)

1. Klik kanan Project → Properties
2. Libraries → Compile
3. Klik (+) pada Classpath
4. Tambahkan:

```
mariadb-java-client-x.x.x.jar
```

(JANGAN di Modulepath, harus di Classpath)

---

### 5. Run Aplikasi

Klik:

```
Run Project (F6)
```

Atau jalankan:

```
Main.java
```

---

## Default Login (Demo)

```
Username: admin
Password: admin
```

---

## Fitur GUI

### Login Form

* Validasi login sederhana
* Redirect ke dashboard manajemen barang

### Manajemen Barang ATK

* Tambah barang
* Update barang
* Hapus barang
* Refresh data
* Tabel otomatis load dari database
* ComboBox kategori (Kertas, Pulpen, Spidol, Tinta Printer)

---

## Struktur Database (Tabel Barang)

```sql
CREATE TABLE barang (
    kode_barang VARCHAR(20) PRIMARY KEY,
    nama_barang VARCHAR(100),
    kategori VARCHAR(50),
    stok INT,
    satuan VARCHAR(20),
    stok_minimum INT
);
```

---

## Keunggulan Sistem

* GUI siap demo 
* Clean architecture (DAO Pattern)
* Tidak menggunakan ResultSet langsung di GUI
* Error handling database
* Mudah dikembangkan (Restock, Laporan, Multi-user)

---

## Status Project

* GUI: Selesai
* CRUD Barang: Berfungsi
* Database: Terintegrasi
* Siap Demo Sidang / Presentasi

---

## Catatan Keamanan

Project ini menggunakan koneksi database lokal (localhost) dengan konfigurasi default Laragon (root tanpa password). Tidak mengandung API key, token, atau kredensial sensitif sehingga aman untuk repository publik.

---

## Author

Nama: Archilleus Aldo Kurniyanto
Project: Final Project PBO – Sistem Manajemen ATK
Tahun: 2026
