# Java Fundamental & Object-Oriented Programming (OOP) Documentation

Repository ini berisi kurikulum lengkap pembelajaran pemrograman Java dari tingkat pemula (*Basic Java*) hingga mahir dalam paradigma *Object-Oriented Programming* (OOP) berstandar industri (*clean code & best practices*).

Seluruh materi disusun secara **modular, mandiri, dan komprehensif** dalam folder [`docs/modul/`](file:///d:/Projects/BELAJAR%20JAVA/docs/modul) menggunakan **8 Struktur Wajib** (Header & Tujuan, Analogi Dunia Nyata, Teori & Aturan Main, Contoh Kode Utuh Executable, Bedah Kode Line-by-Line, Jebakan Error Pemula, Jembatan OOP, dan 3 Tingkat Soal Latihan Praktek Mandiri).

---

## 📚 Daftar Lengkap Modul Pembelajaran (Learning Path)

### 🚀 Fase 1: Fundamental Java (Dasar Komputasi & Algoritma)
1. **[Modul 01: Struktur Program Java Dasar](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul01-Struktur-Dasar.md)**
   - *Topik:* Arsitektur file `.java`, fungsi *class*, peran `main` method, JVM, dan aturan dasar penulisan (*case-sensitive*, titik koma).
2. **[Modul 02: Variabel, Tipe Data, dan Input Scanner](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul02-Variabel-TipeData-Scanner.md)**
   - *Topik:* Alokasi memori stack vs heap, tipe data primitif vs objek, input `Scanner`, format `printf`, dan pencegahan *resource leak*.
3. **[Modul 03: Operator Aritmatika, Perbandingan, dan Logika](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul03-Operator-Aritmatika-Logika.md)**
   - *Topik:* Operasi matematika, pembagian integer vs desimal, sisa bagi (modulo `%`), relasi boolean, dan *short-circuit evaluation* (`&&`, `||`, `!`).
4. **[Modul 04: Percabangan (Decision Making) — `if-else` dan `switch`](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul04-Percabangan-IfElse-Switch.md)**
   - *Topik:* Alur keputusan `if-else-if`, operator ternary, `switch-case`, serta penanganan *case-sensitivity* dan string input sanitization.
5. **[Modul 05: Perulangan (Looping) — `for`, `while`, dan `do-while`](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul05-Perulangan.md)**
   - *Topik:* Loop terhitung (*for*), loop kondisi (*while*), loop post-test (*do-while*), keyword `break` & `continue`, serta antisipasi *infinite loop*.
6. **[Modul 06: Array Satu Dimensi dan Manipulasi Data](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul06-Array-Dasar.md)**
   - *Topik:* Struktur data linear homogen di Heap, indeks 0-based, properti `.length`, *traditional* vs *enhanced for-loop*, dan algoritma pencarian linear.
7. **[Modul 07: Method (Fungsi), Parameter, dan Return Value](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul07-Method-Dan-Fungsi.md)**
   - *Topik:* Modularisasi kode (DRY), method `void` vs ber-return value, mekanisme *Pass-by-Value*, *Method Overloading*, dan alokasi *Call Stack*.

---

### 🏛️ Fase 2: Object-Oriented Programming (OOP)
8. **[Modul 08: Konsep Dasar OOP — Class, Object, Field, dan Behavior](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul08-Konsep-Dasar-OOP.md)**
   - *Topik:* Pergeseran paradigma dari prosedural ke objek, kelas sebagai *blueprint*, instansiasi objek dengan `new`, State vs Behavior, dan kata kunci `this`.
9. **[Modul 09: Constructor, Encapsulation, dan Access Modifier](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul09-Constructor-Dan-Encapsulation.md)**
   - *Topik:* Proteksi data (*Data Hiding*), 4 hak akses (`private`, default, `protected`, `public`), *Default & Parameterized Constructor*, dan validasi setter-getter.
10. **[Modul 10: Inheritance (Pewarisan) dan Polymorphism](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul10-Inheritance-Dan-Polymorphism.md)**
    - *Topik:* Hubungan *IS-A*, kata kunci `extends`, pemanggilan induk `super()`, *Method Overriding* (`@Override`), *Upcasting*, dan *Dynamic Method Dispatch*.
11. **[Modul 11: Abstraction dan Interface — Desain Arsitektur Modern](file:///d:/Projects/BELAJAR%20JAVA/docs/modul/Modul11-Abstraction-Dan-Interface.md)**
    - *Topik:* Seni menyembunyikan detail kerumitan, *Abstract Class* vs *Interface*, implementasi banyak kontrak (*Multiple Interfaces*), *Loose Coupling*, dan jembatan ke industri/Spring Boot.

---

## 🛠️ Struktur Direktori Proyek

```text
BELAJAR JAVA/
│
├── docs/
│   └── modul/                                 <-- Arsip Modul Pembelajaran (.md)
│       ├── Modul01-Struktur-Dasar.md
│       ├── Modul02-Variabel-TipeData-Scanner.md
│       ├── Modul03-Operator-Aritmatika-Logika.md
│       ├── Modul04-Percabangan-IfElse-Switch.md
│       ├── Modul05-Perulangan.md
│       ├── Modul06-Array-Dasar.md
│       ├── Modul07-Method-Dan-Fungsi.md
│       ├── Modul08-Konsep-Dasar-OOP.md
│       ├── Modul09-Constructor-Dan-Encapsulation.md
│       ├── Modul10-Inheritance-Dan-Polymorphism.md
│       └── Modul11-Abstraction-Dan-Interface.md
│
├── modul/                                     <-- Workspace Kode Praktek Mahasiswa
│   ├── Modul1.java
│   ├── Modul2.java
│   ├── Modul3.java
│   ├── Modul4.java
│   └── Modul5.java
│
├── latihan/                                   <-- Solusi & Latihan Mandiri Mahasiswa
│   ├── Modul3.java
│   ├── Modul4.java
│   └── Modul5.java
│
└── README.md                                  <-- Pintu Masuk / Indeks Dokumentasi
```

---

## 💡 Panduan Belajar Mandiri
1. Buka modul materi yang ingin dipelajari di folder `docs/modul/`.
2. Pahami **Analogi Dunia Nyata** dan **Konsep Inti** sebelum menyentuh kode.
3. Ketik ulang (jangan sekadar copy-paste) **Contoh Kode Program** di folder `modul/` untuk melatih memori otot (*muscle memory*).
4. Baca bagian **Jebakan Error Pemula** untuk memahami pesan error umum yang dihasilkan compiler.
5. Kerjakan 3 tingkat **Soal Latihan Praktek Mandiri** di folder `latihan/` untuk menguji pemahaman logikamu!