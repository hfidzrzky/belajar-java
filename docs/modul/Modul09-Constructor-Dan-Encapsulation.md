# Modul 09: Constructor, Encapsulation, dan Access Modifier

> **Deskripsi Singkat:**  
> Mengamankan integritas data internal objek dan standarisasi proses kelahiran (*lifecycle*) objek di memori. Modul ini mengupas tuntas pilar pertama OOP yaitu *Encapsulation* (pembungkusan data), penerapan hak akses ketat (`private`, `public`, `protected`, default), pembuatan *Constructor* (Default vs Parameterized vs Overloaded), serta pembuatan gerbang kendali resmi melalui metode *Getter* dan *Setter*.
>
> **Tujuan Pembelajaran:**
> 1. Memahami prinsip Encapsulation untuk melindungi integritas status objek dari manipulasi ilegal pihak luar.
> 2. Mampu membuat *Constructor* untuk menjamin setiap objek terlahir dalam kondisi valid dan siap pakai.
> 3. Menguasai 4 tingkat *Access Modifier* di Java (`private`, `default/package-private`, `protected`, `public`).
> 4. Mengimplementasikan metode *Getter* dan *Setter* dengan validasi bisnis (*business validation*).

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Encapsulation = Kapsul Obat:**  
   Sebuah kapsul obat membungkus berbagai serbuk zat kimia pahit dan berbahaya di dalamnya. Pasien tidak perlu menyentuh atau mencampur serbuk kimia itu sendiri; pasien cukup menelan kapsul utuh melalui cangkangnya yang aman. Di pemrograman, data sensitif disembunyikan (`private`), dan hanya diakses lewat metode publik yang teruji.
2. **Getter & Setter = Loket Teller / Mesin ATM:**  
   Brankas uang bank berada di ruangan tertutup yang terkunci rapat (`private`). Nasabah tidak boleh masuk langsung dan mengambil uang di brankas. Jika nasabah ingin mengambil uang, nasabah harus melalui mesin ATM / Teller (`public getSaldo()` atau `public tarikUang()`) yang akan memeriksa PIN, kartu, dan saldo terlebih dahulu sebelum mengeluarkan uang secara sah.
3. **Constructor = Akta Kelahiran Bayi Saat Lahir:**  
   Ketika seorang bayi lahir ke dunia, bayi tidak mungkin lahir tanpa nama, tanggal lahir, dan golongan darah. Constructor adalah **prosedur wajib** yang otomatis dijalankan begitu objek diciptakan dengan `new`, memastikan objek tidak pernah terlahir dalam kondisi "kosong tak jelas" (*uninitialized state*).

```
+-------------------------------------------------------------+
| KELAS TERENKAPSULASI (Data Hiding)                          |
|                                                             |
| +---------------------------------------------------------+ |
| | [ PRIVATE FIELDS ]                                      | |
| | - private String nomorHp;                               | |
| | - private double saldo;                                 | |
| +---------------------------------------------------------+ |
|                              ^                              |
|                   VALIDASI BISNIS KETAT                     |
|                              v                              |
| +---------------------------------------------------------+ |
| | [ PUBLIC GATEWAYS ]                                     | |
| | + public double getSaldo()                              | |
| | + public void setSaldo(double saldoBaru)                | |
| +---------------------------------------------------------+ |
+-------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Tanpa enkapsulasi, variabel internal objek terbuka bebas (*naked data*). Kode dari modul lain bisa mengubah saldo nasabah menjadi minus, mengisi umur dengan angka $-50$, atau merusak status internal aplikasi. Enkapsulasi menjamin bahwa **hanya pemilik kelas yang memiliki hak penuh untuk menentukan bagaimana datanya boleh dibaca dan dimodifikasi**.

---

## 3. Penjelasan Teori & Aturan Main

### A. 4 Tingkat Access Modifier di Java
| Modifier | Pada Class yang Sama | Pada Package yang Sama | Subclass (Turunan) | Di Mana Saja (World) |
| :--- | :---: | :---: | :---: | :---: |
| `private` | **YA** | Tidak | Tidak | Tidak |
| *default* (tanpa kata kunci) | **YA** | **YA** | Tidak | Tidak |
| `protected` | **YA** | **YA** | **YA** | Tidak |
| `public` | **YA** | **YA** | **YA** | **YA** |

*Prinsip Keamanan Industri (Principle of Least Privilege):* Selalu jadikan field bertipe `private`, kecuali ada alasan arsitektur yang sangat spesifik.

### B. Aturan Pembuatan Constructor
1. Namanya harus **sama persis** dengan nama kelas (termasuk huruf kapitalnya).
2. **Tidak memiliki return type** apa pun (bahkan tidak boleh ada kata `void`).
3. Dipanggil secara otomatis hanya satu kali ketika kata kunci `new` dieksekusi.
4. Mendukung *Constructor Overloading* (bisa memiliki lebih dari satu constructor dengan kombinasi parameter berbeda).

---

## 4. Contoh Kode Program Komplit & Executable

```java
// Entitas Karyawan Terenkapsulasi Penuh (Java Bean / POJO Standard)
class Pegawai {
    // 1. Data disembunyikan rapat dari akses liar (Private Fields)
    private String nip;
    private String namaLengkap;
    private double gajiBulanan;

    // 2. Default Constructor (Tanpa Parameter)
    public Pegawai() {
        this.nip = "TBD-000";
        this.namaLengkap = "Pegawai Baru";
        this.gajiBulanan = 3500000.0; // Gaji UMR default
    }

    // 3. Parameterized Constructor (Inisialisasi Data Lengkap)
    public Pegawai(String nip, String namaLengkap, double gajiBulanan) {
        this.nip = nip;
        this.namaLengkap = namaLengkap;
        setGajiBulanan(gajiBulanan); // Memanfaatkan setter agar validasi bisnis tetap aktif
    }

    // 4. Getter & Setter Methods (Pintu Gerbang Resmi)
    public String getNip() {
        return this.nip;
    }

    public String getNamaLengkap() {
        return this.namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        if (namaLengkap != null && namaLengkap.trim().length() >= 3) {
            this.namaLengkap = namaLengkap;
        } else {
            System.out.println("Peringatan: Nama pegawai minimal terdiri dari 3 karakter!");
        }
    }

    public double getGajiBulanan() {
        return this.gajiBulanan;
    }

    public void setGajiBulanan(double gajiBulanan) {
        // Validasi Bisnis: Gaji tidak boleh negatif atau di bawah ketentuan perusahaan
        if (gajiBulanan >= 3000000.0) {
            this.gajiBulanan = gajiBulanan;
        } else {
            System.out.println("Error: Gaji bulanan tidak boleh di bawah batas minimal Rp 3.000.000!");
        }
    }

    public void cetakProfil() {
        System.out.println("--------------------------------------------------");
        System.out.printf("NIP Pegawai   : %s%n", this.nip);
        System.out.printf("Nama Lengkap  : %s%n", this.namaLengkap);
        System.out.printf("Gaji Pokok    : Rp %,.2f%n", this.gajiBulanan);
        System.out.println("--------------------------------------------------");
    }
}

public class Modul09 {
    public static void main(String[] args) {
        System.out.println("=== SISTEM KEPEGAWAIAN STANDAR ENKAPSULASI ===");

        // Menggunakan Default Constructor
        Pegawai pegawaiBaru = new Pegawai();
        System.out.println("[Profil Pegawai Default]");
        pegawaiBaru.cetakProfil();

        // Menggunakan Parameterized Constructor
        Pegawai devSenior = new Pegawai("EMP-2026-001", "Hafidz Rizki", 15000000.0);
        System.out.println("\n[Profil Pegawai Senior]");
        devSenior.cetakProfil();

        // Menguji Keamanan Enkapsulasi: Percobaan Modifikasi Gaji Ilegal
        System.out.println("\n[Uji Validasi Setter Gaji]");
        devSenior.setGajiBulanan(-500000.0); // Ditolak oleh setter!
        devSenior.setGajiBulanan(18000000.0); // Diterima

        System.out.println("\n[Status Akhir Setelah Validasi]");
        System.out.printf("Gaji Terverifikasi: Rp %,.2f%n", devSenior.getGajiBulanan());
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 4-6 (`private String nip; ...`):** Menggunakan hak akses `private`. Jika ada kode luar yang mencoba mengetik `devSenior.gajiBulanan = 0`, compiler Java akan langsung memblokir proses kompilasi.
- **Baris 9 (`public Pegawai()`):** Default constructor. Jika objek dibuat tanpa argumen (`new Pegawai()`), field diberi nilai dasar yang aman sehingga tidak ada variabel bernilai `null` tak terduga.
- **Baris 16 (`public Pegawai(String nip, ...)`):** Parameterized constructor. Menghemat baris kode sehingga saat objek dibuat, semua properti langsung terisi rapi dalam satu tarikan napas.
- **Baris 19 (`setGajiBulanan(gajiBulanan);`):** Praktik *clean code* terbaik: constructor mendelegasikan pengisian ke method setter agar aturan validasi bisnis tidak perlu diduplikasi (*DRY*).
- **Baris 39-46 (`public void setGajiBulanan(...)`):** Pintu gerbang validasi. Variabel `this.gajiBulanan` hanya dimutasi jika angka yang dikirimkan memenuhi kriteria ($\ge 3.000.000$).

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Error Constructor Default Lenyap Saat Membuat Constructor Kustom
* **Masalah:** Kamu membuat constructor berparameter, tetapi saat menulis `new Pegawai()` muncul compile error.
* **Pesan Error:**
  ```text
  error: constructor Pegawai in class Pegawai cannot be applied to given types;
  required: String,String,double; found: no arguments
  ```
* **Penyebab:** Begitu kamu menulis satu saja constructor kustom, Java **mencabut** fasilitas default constructor bawaannya secara otomatis!
* **Solusi:** Selalu deklarasikan constructor kosong secara eksplisit jika kamu masih ingin mendukung pembuatan objek tanpa parameter.

---

### 2. Error Akses Langsung Field Private
* **Masalah:** Mencoba membaca atau mengisi data secara langsung dari kelas `main`:
  ```java
  devSenior.gajiBulanan = 20000000;
  ```
* **Pesan Error:**
  ```text
  error: gajiBulanan has private access in Pegawai
  ```
* **Solusi:** Gunakan metode mutator resmi yang disediakan kelas: `devSenior.setGajiBulanan(20000000);`.

---

### 3. Setter Tanpa Logika Validasi (Pass-Through Blind Setter)
* **Peringatan Industri:** Membuat setter yang hanya berisi `this.field = field;` tanpa evaluasi apa pun sama saja dengan membiarkan field bertipe publik. Berikan validasi nilai kosong, batas minus, atau aturan format regex jika memungkinkan.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju Modul Berikutnya

- **Constructor Sebagai Pondasi Pewarisan (Modul 10):** Ketika sebuah class diturunkan (*Inheritance*), constructor class anak (*Subclass*) wajib memanggil constructor class induk (*Superclass*) menggunakan perintah `super()`.
- **Dasar Pola Desain (Design Patterns):** Enkapsulasi adalah pilar wajib sebelum memahami pola industri seperti *Builder Pattern*, *Factory Pattern*, dan *Immutable Objects*.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Enkapsulasi Buku Perpustakaan
- **Instruksi:** Buat class `Buku` dengan atribut private: `isbn`, `judul`, `penulis`, dan `tahunTerbit`. Buat constructor lengkap, method getter untuk semua field, dan setter khusus untuk `tahunTerbit` dengan validasi bahwa tahun tidak boleh lebih kecil dari tahun 1500 atau lebih besar dari 2026.
- **Target Output:**
  ```text
  ISBN: 978-602-03-1234-5 | Judul: Clean Code | Penulis: Robert C. Martin (2008)
  Ubah tahun ke 1200 -> Error: Tahun terbit tidak valid!
  ```

---

### Tingkat Menengah: Dompet Digital (E-Wallet Security)
- **Instruksi:** Buat class `DompetDigital` dengan field private: `nomorHp`, `saldo`, dan `pinKeamanan` (4 digit int).
  - Constructor wajib meminta `nomorHp` dan `pinKeamanan`. Saldo awal default adalah 0.
  - Method `transfer(DompetDigital tujuan, double nominal, int pinInput)`: Transfer hanya berhasil jika `pinInput` cocok dan saldo mencukupi.
- **Target Output:**
  ```text
  Transfer Rp 50,000 ke 08123456789 dengan PIN salah -> Transaksi Ditolak: PIN Salah!
  Transfer dengan PIN benar -> Berhasil! Sisa Saldo: Rp 150,000
  ```

---

### Tingkat Tantangan (Challenge): Constructor Chaining dengan `this()`
- **Instruksi:** Buat class `TiketKonser` dengan 3 jenis constructor overloaded:
  1. `TiketKonser()` $\rightarrow$ Tiket kategori "Festival", harga Rp 500.000, kursi "Standing".
  2. `TiketKonser(String kategori, double harga)` $\rightarrow$ Kursi "Regular Seating".
  3. `TiketKonser(String kategori, double harga, String nomorKursi)`.
  Terapkan teknik **Constructor Chaining** menggunakan panggilan `this(...)` agar kode inisialisasi terpusat pada satu constructor utama saja.
- **Target Output:**
  ```text
  Tiket 1 (Default): Festival | Rp 500,000 | Kursi: Standing
  Tiket 2 (VIP): VIP Gold | Rp 1,500,000 | Kursi: Regular Seating
  Tiket 3 (VVIP): VVIP Box | Rp 3,000,000 | Kursi: Row A-01
  ```
