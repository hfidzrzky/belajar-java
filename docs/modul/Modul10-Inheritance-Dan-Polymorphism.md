# Modul 10: Inheritance (Pewarisan) dan Polymorphism (Banyak Bentuk)

> **Deskripsi Singkat:**  
> Dua pilar agung OOP yang memampukan hierarki kode terstruktur dan ekstensibel tanpa batas. Modul ini membahas mekanisme pewarisan sifat kelas induk (*Superclass*) ke kelas anak (*Subclass*) menggunakan kata kunci `extends`, pemanggilan constructor induk lewat `super()`, penimpaan perilaku melalui *Method Overriding* (`@Override`), serta kedahsyatan *Dynamic Method Dispatch* (Polymorphism) yang memungkinkan satu tipe referensi umum menampung beragam bentuk objek turunan.
>
> **Tujuan Pembelajaran:**
> 1. Memahami konsep pewarisan relasi *IS-A* (contoh: *Manager IS-A Employee*) menggunakan kata kunci `extends`.
> 2. Menguasai pemanfaatan kata kunci `super` untuk memanggil constructor dan method kelas induk.
> 3. Memahami dan menerapkan *Method Overriding* dengan anotasi `@Override` yang benar.
> 4. Menguasai *Runtime Polymorphism* (*Upcasting*) untuk memproses aneka ragam objek dalam satu array tunggal yang generik.

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Inheritance = Genetika Keluarga & Kerajaan Binatang:**  
   Bayangkan kelas induk **Hewan**. Semua hewan memiliki karakteristik umum: bernapas, punya nama, dan bisa bersuara.  
   - Ketika kamu membuat kelas **Kucing**, kamu tidak perlu menciptakan ulang fitur bernapas dan nama dari nol. Kamu cukup *mewarisi* sifat dari Hewan. Kucing adalah Hewan (*Cat IS-A Animal*).
2. **Method Overriding = Suara Spesifik Setiap Hewan:**  
   Meskipun sama-sama bisa bersuara, suara Kucing tentu berbeda dengan suara Anjing:
   - Kucing menimpa (*override*) aksi bersuara menjadi: *"Meong!"*.
   - Anjing menimpa (*override*) aksi bersuara menjadi: *"Guk Guk!"*.  
   Metode dasarnya sama (`bersuara()`), tetapi implementasi wujudnya disesuaikan oleh kelas anak.
3. **Polymorphism = Port Charger USB Type-C Modern:**  
   Satu lubang port charger USB-C yang sama (**Tipe Referensi Umum**) dapat dicolokkan ke berbagai perangkat: Smartphone, Laptop, TWS, hingga Kipas Angin (**Banyak Bentuk Objek / Poly-morphism**). Pengisi daya tidak perlu tahu secara spesifik merek laptopmu; selama perangkat tersebut memenuhi standar USB-C, aliran listrik tetap berjalan.

```
                  +-------------------------+
                  |    Superclass: Hewan    |
                  | - nama                  |
                  | + bersuara()            |
                  +-------------------------+
                               |
                   extends     |     extends
             +-----------------+-----------------+
             v                                   v
+-------------------------+         +-------------------------+
|     Subclass: Kucing    |         |     Subclass: Anjing    |
| + bersuara() -> "Meong" |         | + bersuara() -> "Guk"   |
+-------------------------+         +-------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Jika perusahaan memiliki 10 jenis karyawan (Staff, Manager, Direktur, Freelancer), tanpa inheritance kita akan menduplikasi 80% kode yang sama (nama, gaji, tanggal masuk) di 10 file berbeda. Inheritance mengeliminasi duplikasi secara radikal. Sementara Polymorphism memungkinkan arsitektur perangkat lunak kita bersifat **Open for Extension, Closed for Modification** (Prinsip Open/Closed SOLID).

---

## 3. Penjelasan Teori & Aturan Main

### A. Kata Kunci `extends` dan Hirarki Tunggal Java
Java **hanya mendukung Single Inheritance** untuk kelas (satu anak hanya boleh memiliki satu induk biologis langsung).
```java
public class Manager extends Karyawan {
    // Manager mewarisi semua field dan method non-private milik Karyawan
}
```

### B. Peran Kata Kunci `super`
1. `super(...)`: Wajib dipanggil di baris pertama constructor subclass untuk menginisialisasi field milik kelas induk.
2. `super.namaMethod()`: Memanggil implementasi asli method milik kelas induk sebelum ditimpa.

### C. Syarat Ketat Method Overriding
1. Nama method, jumlah parameter, dan tipe data parameter harus **sama persis**.
2. Return type harus sama (atau turunan / covariant).
3. Access modifier tidak boleh lebih ketat daripada induknya (misal: method induk `public`, subclass tidak boleh menjadikannya `protected` atau `private`).
4. Sangat disarankan menambahkan anotasi `@Override` agar compiler memverifikasi kecocokan tanda tangan method.

---

## 4. Contoh Kode Program Komplit & Executable

```java
// 1. KELAS INDUK (SUPERCLASS)
class Karyawan {
    private String nama;
    private double gajiPokok;

    public Karyawan(String nama, double gajiPokok) {
        this.nama = nama;
        this.gajiPokok = gajiPokok;
    }

    public String getNama() {
        return this.nama;
    }

    public double getGajiPokok() {
        return this.gajiPokok;
    }

    // Method yang akan dioverride oleh anak-anaknya
    public double hitungTotalGaji() {
        return this.gajiPokok; // Gaji karyawan standar hanya gaji pokok
    }

    public void cetakSlipGaji() {
        System.out.printf("[Staff] %-15s | Total Diterima: Rp %,.2f%n", this.nama, hitungTotalGaji());
    }
}

// 2. KELAS ANAK 1 (SUBCLASS: MANAGER)
class Manager extends Karyawan {
    private double tunjanganManajerial;

    public Manager(String nama, double gajiPokok, double tunjanganManajerial) {
        // Memanggil constructor superclass Karyawan
        super(nama, gajiPokok);
        this.tunjanganManajerial = tunjanganManajerial;
    }

    // Method Overriding: Manager mendapat tambahan tunjangan
    @Override
    public double hitungTotalGaji() {
        return super.getGajiPokok() + this.tunjanganManajerial;
    }

    @Override
    public void cetakSlipGaji() {
        System.out.printf("[Manager] %-13s | Total Diterima: Rp %,.2f (Termasuk Tunjangan)%n", 
                          getNama(), hitungTotalGaji());
    }
}

// 3. KELAS ANAK 2 (SUBCLASS: PROGRAMMER)
class Programmer extends Karyawan {
    private double bonusProyek;

    public Programmer(String nama, double gajiPokok, double bonusProyek) {
        super(nama, gajiPokok);
        this.bonusProyek = bonusProyek;
    }

    // Method Overriding: Programmer mendapat bonus lembur/proyek
    @Override
    public double hitungTotalGaji() {
        return super.getGajiPokok() + this.bonusProyek;
    }

    @Override
    public void cetakSlipGaji() {
        System.out.printf("[Programmer] %-10s | Total Diterima: Rp %,.2f (Termasuk Bonus Proyek)%n", 
                          getNama(), hitungTotalGaji());
    }
}

// 4. KELAS UTAMA: DEMONSTRASI POLYMORPHISM
public class Modul10 {
    public static void main(String[] args) {
        System.out.println("=== SISTEM PAYROLL PERUSAHAAN (DEMO POLYMORPHISM) ===");

        // Polymorphic Array: Tipe referensi Superclass menampung aneka ragam Subclass!
        Karyawan[] daftarTim = new Karyawan[3];
        daftarTim[0] = new Karyawan("Andi Pratama", 5000000.0);
        daftarTim[1] = new Manager("Rina Wijaya", 12000000.0, 5000000.0);
        daftarTim[2] = new Programmer("Hafidz Rizki", 9000000.0, 3500000.0);

        double totalPengeluaranGajiPerusahaan = 0.0;

        // Dynamic Method Dispatch: JVM otomatis memanggil method versi anak yang tepat saat runtime!
        System.out.println("\n--- DAFTAR SLIP GAJI KARYAWAN BULAN INI ---");
        for (Karyawan k : daftarTim) {
            k.cetakSlipGaji(); // Polimorfisme bekerja di sini!
            totalPengeluaranGajiPerusahaan += k.hitungTotalGaji();
        }

        System.out.println("------------------------------------------------------------------");
        System.out.printf("TOTAL BIAYA PENGELUARAN GAJI PT: Rp %,.2f%n", totalPengeluaranGajiPerusahaan);
        System.out.println("==================================================================");
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 29 (`class Manager extends Karyawan`):** Kata kunci `extends` menjadikan kelas Manager mewarisi seluruh kemampuan Karyawan tanpa perlu mendeklarasikan ulang field `nama` atau `gajiPokok`.
- **Baris 34 (`super(nama, gajiPokok);`):** Mengirimkan data nama dan gaji ke constructor kelas induk. Aturan Java mewajibkan ini berada di baris pertama badan constructor.
- **Baris 39 (`@Override public double hitungTotalGaji()`):** Menimpa logika hitung gaji. Jika objek yang dipanggil adalah Manager, JVM akan menjalankan rumus gaji pokok + tunjangan.
- **Baris 72 (`Karyawan[] daftarTim = new Karyawan[3];`):** **Keajaiban Polimorfisme!** Satu array bertipe umum `Karyawan` dapat menampung objek `Manager` dan `Programmer`. Tanpa polimorfisme, kamu harus membuat 3 array terpisah untuk setiap profesi!
- **Baris 80 (`k.cetakSlipGaji();`):** *Dynamic Method Dispatch*. Saat iterasi mencapai indeks ke-1, JVM tahu di memori bahwa objek aslinya adalah `Manager`, sehingga method milik Manager yang dieksekusi secara otomatis.

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Lupa Memanggil `super()` pada Constructor Subclass
* **Masalah:** Superclass tidak memiliki default constructor kosong, dan subclass tidak memanggil `super(arg1, arg2)`.
* **Pesan Error:**
  ```text
  error: constructor Karyawan in class Karyawan cannot be applied to given types;
  required: String,double; found: no arguments
  ```
* **Solusi:** Selalu teruskan argumen yang dibutuhkan ke constructor induk menggunakan `super(...)`.

---

### 2. Typo Nama Method Saat Berniat Melakukan Override
* **Masalah:** Di superclass namanya `hitungTotalGaji()`, di subclass salah ketik menjadi `hitungtotalgaji()` (huruf `t` kecil).
* **Akibat Buruk Tanpa `@Override`:** Java menganggap kamu membuat method baru biasa, bukan menimpa. Polimorfisme akan gagal bekerja secara diam-diam!
* **Solusi:** Selalu pasang anotasi `@Override`. Compiler akan langsung berteriak error jika tanda tangan method tidak cocok dengan induknya.

---

### 3. Salah Membedakan Overloading dan Overriding
* **Overloading:** Nama sama, parameter BEDA, terjadi di dalam kelas yang sama (compile-time polymorphism).
* **Overriding:** Nama sama, parameter SAMA PERSIS, terjadi antara kelas induk dan kelas anak (runtime polymorphism).

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju Modul 11

- **Pondasi Menuju Abstraction & Interface (Modul 11):** Di modul ini, kelas `Karyawan` masih bisa di-instansiasi langsung (`new Karyawan()`). Namun di dunia nyata, "Karyawan Umum" hanyalah sebuah konsep abstrak. Di Modul 11, kita akan belajar bagaimana mengubah kelas induk menjadi *Abstract Class* dan *Interface* agar tidak bisa di-instansiasi sembarangan dan wajib diimplementasikan oleh turunannya.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Hierarki Geometri Bangun Datar
- **Instruksi:** Buat superclass `BangunDatar` dengan method `hitungLuas()` dan `hitungKeliling()`. Buat subclass `Persegi` (field `sisi`) dan `Lingkaran` (field `radius`). Override kedua method tersebut dengan rumus matematika yang benar.
- **Target Output:**
  ```text
  Persegi (sisi 4) -> Luas: 16.0 | Keliling: 16.0
  Lingkaran (radius 7) -> Luas: 153.94 | Keliling: 43.98
  ```

---

### Tingkat Menengah: Sistem Akun Media Sosial
- **Instruksi:** Buat superclass `Pengguna` (field `username`, `email`). Buat subclass:
  1. `PenggunaBiasa`: Memiliki kuota posting maksimal 3 kali sehari.
  2. `PenggunaPremium`: Bebas posting tanpa batas dan memiliki centang biru (`isVerified = true`).
  Override method `buatPostingan(String konten)` pada masing-masing subclass dengan batasan aturannya.
- **Target Output:**
  ```text
  [Biasa] @andi berhasil memposting: "Halo dunia!" (Sisa kuota: 2)
  [Premium] @sultan [Verified] berhasil memposting: "Investasi properti!" (Posting Tanpa Batas)
  ```

---

### Tingkat Tantangan (Challenge): Simulator Pembayaran Multi-Payment Gateway
- **Instruksi:** Buat superclass `MetodePembayaran` dengan method `prosesBayar(double nominal)`. Buat 3 subclass:
  1. `TransferBank` (biaya admin flat Rp 4.000).
  2. `EWallet` (biaya admin 1.5% dari nominal).
  3. `KartuKredit` (biaya surcharge 2.5% + proteksi penipuan).
  Buat sebuah array polimorfik berisi ketiga metode pembayaran tersebut dan simulasikan transaksi pembayaran belanja sebesar Rp 500.000 di sebuah e-commerce.
- **Target Output:**
  ```text
  Metode: Transfer Bank BCA | Total Bayar: Rp 504,000.00
  Metode: E-Wallet Gopay    | Total Bayar: Rp 507,500.00
  Metode: Kartu Kredit VISA | Total Bayar: Rp 512,500.00
  ```
