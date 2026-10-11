# Modul 11: Abstraction dan Interface — Desain Arsitektur OOP Modern

> **Deskripsi Singkat:**  
> Puncak dari kurikulum Object-Oriented Programming (OOP) standar industri. Modul ini mengajarkan seni abstraksi: menyembunyikan kompleksitas implementasi dan hanya mengekspos antarmuka kontrak esensial. Mahasiswa akan menguasai perbedaan konseptual antara *Abstract Class* (`abstract`) dan *Interface* (`implements`), teknik mengatasi batasan *single inheritance* melalui implementasi banyak interface (*Multiple Interface Implementation*), serta prinsip arsitektur industri: *Program to an Interface, not an Implementation*.
>
> **Tujuan Pembelajaran:**
> 1. Memahami konsep Abstraksi dan kapan harus memilih *Abstract Class* vs *Interface*.
> 2. Mampu membuat *Abstract Method* sebagai kontrak wajib bagi seluruh kelas turunan.
> 3. Menguasai pembuatan dan implementasi *Interface* (`implements`) secara modular.
> 4. Memahami konsep *Loose Coupling* dan keterhubungan arsitektur menuju framework industri (Spring Boot, Android).

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Abstraksi = Dashboard Mobil & Setir Kemudi:**  
   Saat kamu menginjak pedal gas, mobil melaju kencang. Kamu **tidak perlu tahu** bagaimana busi memercikkan api, bagaimana injektor menyemprotkan bensin, atau bagaimana rasio kompresi piston bekerja. Semua detail rumit itu **disembunyikan (Abstraksi)**. Kamu hanya berinteraksi dengan antarmuka sederhana: pedal gas, rem, dan setir kemudi.
2. **Abstract Class = Sketsa Konsep / Rangka Mobil Setengah Jadi:**  
   Perusahaan otomotif mendesain platform rangka mobil. Rangka ini sudah memiliki 4 ban dan nomor sasis (*Concrete implementation*), tetapi bentuk bodi dan mesinnya belum selesai (*Abstract*). Kamu **tidak bisa membeli rangka mentah ini untuk jalan-jalan di jalan raya (`tidak bisa di-instansiasi`)**. Rangka ini harus diturunkan dulu menjadi produk matang: sedan atau SUV.
3. **Interface = Surat Perjanjian / Kontrak Kerja Resmi:**  
   Interface adalah kontrak yang menyatakan: *"Siapa pun kelas yang menandatangani kontrak ini WAJIB menyediakan implementasi fungsi yang tertulis di dalamnya"*.  
   Contoh: Interface `BisaTerbang`.
   - Burung Merpati menandatangani kontrak `implements BisaTerbang`.
   - Pesawat Boeing menandatangani kontrak `implements BisaTerbang`.  
   Merpati dan Pesawat tidak memiliki hubungan keluarga biologis (*Inheritance*), tetapi keduanya mematuhi kontrak antarmuka yang sama!

```
+---------------------------------------------------------------------------------+
|                               MATRIKS PERBANDINGAN                              |
|                                                                                 |
| Aspek              Abstract Class                    Interface                  |
| ------------------------------------------------------------------------------- |
| Kata Kunci         abstract class, extends           interface, implements      |
| Hubungan           Identitas ("IS-A")                Kemampuan / Kontrak ("CAN")|
| Pewarisan Ganda    TIDAK BISA (Maks 1 Class)         BISA BANYAK (Multi-impl)   |
| Field/Variabel     Bisa instance field biasa         Hanya konstanta (public    |
|                                                      static final)              |
| Constructor        Memiliki constructor              TIDAK memiliki constructor |
+---------------------------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Pada proyek software raksasa yang dikerjakan ratusan insinyur, developer backend tidak boleh menunggu tim payment selesai membuat sistem bank sebelum bisa mulai coding. Mereka membuat **Interface `PaymentService`** sebagai kontrak kesepakatan terlebih dahulu. Dengan begitu, tim frontend, tim database, dan tim pengujian bisa bekerja bersamaan tanpa saling mengunci (*Loose Coupling*).

---

## 3. Penjelasan Teori & Aturan Main

### A. Aturan Abstract Class
1. Dideklarasikan dengan kata kunci `abstract class`.
2. **Tidak dapat di-instansiasi langsung** dengan kata kunci `new` (contoh: `new Kendaraan()` akan memicu error kompilasi).
3. Boleh memiliki method konkret (yang ada isinya `{ }`) dan boleh memiliki method abstrak (hanya tanda tangan tanpa isi `;`).
4. Subclass wajib mengimplementasikan semua abstract method, kecuali jika subclass tersebut juga dideklarasikan sebagai `abstract`.

### B. Aturan Interface
1. Dideklarasikan dengan kata kunci `interface`.
2. Semua method di dalam interface secara default bersifat `public abstract` (sebelum Java 8).
3. Sebuah kelas Java dapat mengimplementasikan **lebih dari satu interface** sekaligus menggunakan koma:
   ```java
   public class Robot implements Bergerak, Bersuara, BisaDicas { ... }
   ```
4. Sejak Java 8+, interface mendukung `default method` (method konkret yang memiliki implementasi dasar).

---

## 4. Contoh Kode Program Komplit & Executable

```java
// 1. KONTRAK INTERFACE (KEMAMPUAN NOTIFIKASI)
interface LayananNotifikasi {
    void kirimPesanNotifikasi(String pesan); // public abstract secara default
}

// 2. KONTRAK INTERFACE KEDUA (KEMAMPUAN AUDIT LOGGING)
interface AuditLoggable {
    void catatRiwayatTransaksi(String idTransaksi, double nominal);
}

// 3. ABSTRACT CLASS SEBAGAI RANGKA DASAR PEMBAYARAN
abstract class PembayaranDigital {
    private String idTransaksi;
    private double saldoTersedia;

    // Abstract class boleh memiliki constructor
    public PembayaranDigital(String idTransaksi, double saldoTersedia) {
        this.idTransaksi = idTransaksi;
        this.saldoTersedia = saldoTersedia;
    }

    public String getIdTransaksi() {
        return this.idTransaksi;
    }

    public double getSaldoTersedia() {
        return this.saldoTersedia;
    }

    protected void kurangiSaldo(double nominal) {
        this.saldoTersedia -= nominal;
    }

    // Concrete Method: Logika verifikasi dasar yang seragam untuk semua jenis dompet
    public void validasiDasar() {
        System.out.printf("Memverifikasi ID Transaksi: %s di server pusat... OK!%n", this.idTransaksi);
    }

    // ABSTRACT METHOD: Setiap dompet digital punya algoritma potongan biaya admin yang beda!
    public abstract boolean prosesPembayaran(double nominalBelanja);
}

// 4. KELAS KONKRET: GOPAY (Menggabungkan Inheritance dan Multiple Interfaces)
class GoPay extends PembayaranDigital implements LayananNotifikasi, AuditLoggable {
    private String nomorPonsel;

    public GoPay(String idTransaksi, double saldo, String nomorPonsel) {
        super(idTransaksi, saldo);
        this.nomorPonsel = nomorPonsel;
    }

    // Wajib mengimplementasikan abstract method dari kelas induk
    @Override
    public boolean prosesPembayaran(double nominalBelanja) {
        validasiDasar();
        double biayaAdmin = 1000.0;
        double totalTagihan = nominalBelanja + biayaAdmin;

        if (getSaldoTersedia() >= totalTagihan) {
            kurangiSaldo(totalTagihan);
            System.out.printf("[GoPay] Transaksi Rp %,.2f Berhasil (Biaya Admin: Rp %,.2f)%n", 
                              nominalBelanja, biayaAdmin);
            
            // Panggil kemampuan dari interface
            kirimPesanNotifikasi("Pembayaran merchant sebesar Rp " + nominalBelanja + " berhasil dipotong.");
            catatRiwayatTransaksi(getIdTransaksi(), nominalBelanja);
            return true;
        } else {
            System.out.println("[GoPay] Transaksi Gagal! Saldo tidak mencukupi.");
            return false;
        }
    }

    // Implementasi kontrak LayananNotifikasi
    @Override
    public void kirimPesanNotifikasi(String pesan) {
        System.out.printf("-> [SMS/Push Notif ke %s]: %s%n", this.nomorPonsel, pesan);
    }

    // Implementasi kontrak AuditLoggable
    @Override
    public void catatRiwayatTransaksi(String idTransaksi, double nominal) {
        System.out.printf("-> [AUDIT DATABASE]: Log tercatat: Transaksi %s nominal Rp %,.2f telah tervalidasi.%n",
                          idTransaksi, nominal);
    }
}

// 5. KELAS EKSEKUTOR UTAMA
public class Modul11 {
    public static void main(String[] args) {
        System.out.println("=== ARSITEKTUR PAYMENT GATEWAY MODERN (ABSTRACT & INTERFACE) ===");

        // Membuktikan Loose Coupling: Referensi menggunakan tipe Abstraksi
        PembayaranDigital transaksi1 = new GoPay("TRX-990812", 200000.0, "081298765432");

        System.out.println("\n[Skenario 1: Transaksi Belanja Rp 75.000]");
        transaksi1.prosesPembayaran(75000.0);

        System.out.println("\n[Skenario 2: Transaksi Belanja Melebihi Saldo]");
        transaksi1.prosesPembayaran(300000.0);

        System.out.println("\n-------------------------------------------------------------");
        System.out.printf("Sisa Saldo Terakhir di Server: Rp %,.2f%n", transaksi1.getSaldoTersedia());
        System.out.println("=============================================================");
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 2 & 7 (`interface LayananNotifikasi` & `interface AuditLoggable`):** Dua antarmuka terpisah. Mengikuti prinsip *Interface Segregation Principle* (huruf I pada SOLID): buat kontrak kecil yang fokus daripada satu kontrak raksasa.
- **Baris 12 (`abstract class PembayaranDigital`):** Kelas abstrak. Memiliki field nyata `saldoTersedia` dan concrete method `validasiDasar()`. Kelas ini tidak dapat dibuat langsung dengan `new PembayaranDigital(...)`.
- **Baris 38 (`public abstract boolean prosesPembayaran(double nominalBelanja);`):** Abstract method tanpa kurung kurawal tubuh `{ }`. Ini adalah deklarasi murni yang memaksa setiap anak kelas (seperti GoPay, OVO, ShopeePay) untuk mendefinisikan cara kerja pembayarannya sendiri.
- **Baris 42 (`class GoPay extends PembayaranDigital implements LayananNotifikasi, AuditLoggable`):** Kelas GoPay mendemonstrasikan kekuatan Java modern: mewarisi karakteristik satu superclass (`extends`), dan secara fleksibel mengadopsi banyak kontrak sekaligus (`implements ... , ...`).
- **Baris 89 (`PembayaranDigital transaksi1 = new GoPay(...);`):** Prinsip arsitektur: *"Program to an Interface / Abstraction"*. Program pemanggil hanya peduli bahwa objek adalah tipe `PembayaranDigital`, sehingga suatu saat jika sistem mengganti GoPay menjadi metode lain, kode di atasnya tidak perlu dirombak!

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Mencoba Menginstansiasi Kelas Abstrak atau Interface Secara Langsung
* **Masalah:** Menulis `PembayaranDigital p = new PembayaranDigital("TRX-1", 10000);`.
* **Pesan Error:**
  ```text
  error: PembayaranDigital is abstract; cannot be instantiated
  ```
* **Solusi:** Kelas abstrak hanya dapat digunakan sebagai tipe referensi variabel, objek fisiknya wajib dibuat dari subclass konkret turunan yang sudah melengkapi seluruh method abstrak.

---

### 2. Lupa Mengimplementasikan Salah Satu Method Interface
* **Masalah:** Menggunakan `implements LayananNotifikasi`, namun lupa menulis method `kirimPesanNotifikasi()`.
* **Pesan Error:**
  ```text
  error: GoPay is not abstract and does not override abstract method kirimPesanNotifikasi(String) in LayananNotifikasi
  ```
* **Solusi:** Kontrak interface bersifat absolut: setiap method yang tertera di interface wajib dibuat implementasinya dengan access modifier `public`.

---

### 3. Menghapus Kata Kunci `public` pada Method Implementasi Interface
* **Masalah:** Menulis `void kirimPesanNotifikasi(...)` (tanpa kata kunci `public`).
* **Pesan Error:**
  ```text
  error: kirimPesanNotifikasi(String) in GoPay cannot implement kirimPesanNotifikasi(String) in LayananNotifikasi
  attempting to assign weaker access privileges; was public
  ```
* **Penjelasan:** Semua method pada interface secara implisit adalah `public`. Mengimplementasikannya tanpa `public` berarti menurunkan hak akses ke *default/package*, yang melanggar aturan Java.

---

## 7. Penutup Kurikulum & Jembatan ke Industri Nyata

Selamat! Dengan menguasai **Modul 01 hingga Modul 11**, kamu telah resmi menyelesaikan fondasi esensial Java standar industri:
- **Fase 1 (Sintaks & Algoritma Dasar):** Dari struktur dasar kelas, alokasi memori variabel, pemformatan printf, evaluasi logika Boolean, percabangan kendali alur, perulangan, penanganan linear array, hingga dekomposisi method.
- **Fase 2 (Object-Oriented Programming Murni):** Dari memahami cetak biru objek (*Class & Object*), proteksi enkapsulasi (*Data Hiding & Getter-Setter*), efisiensi pewarisan (*Inheritance*), fleksibilitas bentuk (*Polymorphism*), hingga arsitektur modular (*Abstract Class & Interface*).

### Langkah Selanjutnya di Industri:
Fondasi yang kokoh ini adalah tiket utamamu untuk melangkah ke tingkat profesional:
1. **Java Collections Framework** (`List`, `ArrayList`, `Map`, `HashMap`, `Set`).
2. **Exception Handling** (`try-catch-finally`, custom exceptions).
3. **Java Generics & Lambda Expressions / Stream API**.
4. **Framework Backend Enterprise Terpopuler Dunia:** **Spring Boot** (di mana seluruh konfigurasinya didominasi oleh penerapan *Interface*, *Inversion of Control*, dan *Dependency Injection*).

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Interface Remote Control Elektronik
- **Instruksi:** Buat interface `DapatDikendalikan` dengan method `nyalakan()` dan `matikan()`. Buat dua kelas konkret: `Televisi` dan `PendinginRuangan (AC)` yang mengimplementasikan interface tersebut.
- **Target Output:**
  ```text
  Televisi dinyalakan -> Layar menyala menampilkan channel TVRI.
  AC dimatikan -> Mesin kompresor berhenti berputar.
  ```

---

### Tingkat Menengah: Sistem Ekspor Dokumen Multi-Format
- **Instruksi:** Buat interface `PengeksporDokumen` dengan method `ekspor(String judulData, String isiData)`. Buat 3 kelas implementasi:
  1. `EksporPDF`: Mencetak format simulasi dokumen PDF dengan header biner.
  2. `EksporExcel`: Mencetak data dalam representasi format tabel CSV dipisahkan koma.
  3. `EksporJSON`: Mencetak data dalam struktur format `{ "judul": "...", "isi": "..." }`.
- **Target Output:**
  ```text
  [PDF Mode] Menghasilkan file Laporan-Tahunan.pdf (Halaman 1)
  [JSON Mode] {"judul": "Laporan Keuangan", "status": "Sukses Disimpan"}
  ```

---

### Tingkat Tantangan (Challenge): Simulator Kendaraan Otonom Listrik (Smart Mobility)
- **Instruksi:** 
  1. Buat abstract class `Kendaraan` dengan atribut `merk`, `kapasitasPenumpang`, method konkret `tampilkanSpesifikasi()`, dan abstract method `hitungJarakTempuhMaksimal()`.
  2. Buat interface `BisaDiisiListrik` dengan method `isiDayaBaterai(int kWh)` dan `getPersentaseBaterai()`.
  3. Buat interface `NavigasiOtonom` dengan method `aktifkanSelfDriving(String koordinatTujuan)`.
  4. Buat kelas konkret `MobilTesla` yang merupakan turunan dari `Kendaraan` sekaligus mengimplementasikan kedua interface di atas!
- **Target Output:**
  ```text
  === SPESIFIKASI KENDARAAN OTONOM ===
  Merk: Tesla Model 3 | Kapasitas: 5 Orang
  Estimasi Jarak Tempuh Baterai: 480 Km
  [Charging Station] Mengisi daya 50 kWh... Baterai terisi 100%!
  [Autopilot AI] Sistem Self-Driving Aktif menuju koordinat: -6.2088, 106.8456
  ```
