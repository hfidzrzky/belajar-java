# Modul 08: Konsep Dasar Object-Oriented Programming (OOP) — Class, Object, Field, dan Behavior

> **Deskripsi Singkat:**  
> Gerbang transisi dari pemrograman prosedural menuju paradigma *Object-Oriented Programming* (OOP). Modul ini menanamkan cara pandang bahwa program komputer adalah kumpulan objek independen yang saling berkomunikasi. Mahasiswa akan menguasai cara mendefinisikan *Class* sebagai cetak biru (*blueprint*), menginstansiasi *Object* nyata di Heap Memory dengan kata kunci `new`, membedakan *Attribute/Field* (State) dengan *Method* (Behavior), serta memahami kata kunci penunjuk diri `this`.
>
> **Tujuan Pembelajaran:**
> 1. Memahami pergeseran paradigma dari *Procedural Programming* menuju *Object-Oriented Programming*.
> 2. Mampu membuat file kelas entitas kustom dan menginisialisasi objek nyata darinya.
> 3. Membedakan secara presisi antara State (variabel instans/field) dan Behavior (metode instans).
> 4. Memahami alokasi memori objek (variabel referensi di Stack menunjuk ke objek di Heap) dan peran kata kunci `this`.

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Class = Cetak Biru / Desain Arsitektur Rumah (*Blueprint*):**  
   Seorang arsitek menggambar desain rumah di atas kertas kalkir: ada spesifikasi 3 kamar tidur, 1 garasi, dan warna cat. Gambar kertas itu **bukan rumah fisik**—kamu tidak bisa tidur atau memasak di dalam kertas tersebut. Itu adalah sebuah **Class**.
2. **Object = Rumah Fisik Nyata yang Berhasil Dibangun (*Instance*):**  
   Dari satu lembar cetak biru yang sama, kontraktor bisa membangun:
   - Rumah Pak Budi di Blok A1 (Cat Putih, ada kolam renang).
   - Rumah Bu Siti di Blok B5 (Cat Abu-abu, ada taman bunga).  
   Keduanya adalah **Objek** nyata. Keduanya dibangun dari cetak biru yang sama, tetapi memiliki data/karakteristik masing-masing dan berdiri di atas tanah fisik yang berbeda di memori (**Heap Memory**).
3. **State (Field) vs Behavior (Method):**
   - **State (Apa yang dimiliki objek):** Warna mobil, jumlah roda, kapasitas mesin, saldo rekening.
   - **Behavior (Apa yang bisa dilakukan objek):** Menyalakan mesin, mengerem, membunyikan klakson, mentransfer dana.

```
+-------------------------------------------------------------+
| Class Mobil (Blueprint)                                     |
| - Fields   : merk, warna, kecepatan                        |
| - Behaviors: tancapGas(), injakRem()                        |
+-------------------------------------------------------------+
                              |
                   new Mobil() (Instansiasi)
                              |
       +----------------------+----------------------+
       v                                             v
[ Objek 1: mobilAvanza ]                   [ Objek 2: mobilFerrari ]
- merk      : "Toyota"                     - merk      : "Ferrari"
- warna     : "Hitam"                      - warna     : "Merah"
- kecepatan : 60 km/jam                    - kecepatan : 220 km/jam
```

### Mengapa Konsep Ini Diciptakan?
Pada aplikasi enterprise raksasa (misal: sistem perbankan), jika kita hanya menggunakan variabel lepas (`namaNasabah1`, `saldoNasabah1`, `namaNasabah2`), mengelola 10.000 nasabah akan menjadi mimpi buruk. OOP diciptakan agar kode di komputer **mencerminkan entitas dunia nyata secara natural**, di mana data dan aksi yang memanipulasinya disatukan dalam satu paket utuh.

---

## 3. Penjelasan Teori & Aturan Main

### A. Anatomi Pembuatan Kelas Entitas
Dalam standar industri yang bersih (*clean code*), kelas entitas dan kelas eksekutor `main` diletakkan terpisah:
```java
// Entitas / Blueprint
public class Karyawan {
    // 1. Instance Fields (State / Properti Objek)
    String nama;
    String jabatan;
    double gajiPokok;

    // 2. Instance Methods (Behavior / Aksi Objek)
    void cetakInfo() {
        System.out.println("Nama: " + this.nama + " | Jabatan: " + this.jabatan);
    }
}
```

### B. Instansiasi dengan Kata Kunci `new`
Objek diciptakan dengan memanggil:
```java
Karyawan staff1 = new Karyawan();
```
- `Karyawan staff1`: Membuat variabel referensi di **Stack Memory**.
- `new Karyawan()`: Mengalokasikan blok memori fisik baru di **Heap Memory** untuk menampung data `staff1`.
- Operator `.` (titik): Digunakan untuk mengakses atribut dan metode objek (`staff1.nama = "Hafidz"; staff1.cetakInfo();`).

### C. Kata Kunci `this`
Kata kunci `this` merujuk langsung ke **objek saat ini (*current instance*)** yang sedang mengeksekusi metode. Sangat penting digunakan untuk membedakan antara field kelas dan parameter method jika keduanya memiliki nama yang sama.

---

## 4. Contoh Kode Program Komplit & Executable

Dalam contoh ini, kita menggabungkan dua kelas dalam satu file percontohan agar dapat langsung dijalankan:

```java
// KELAS BLUEPRINT (Entitas Rekening Bank)
class RekeningBank {
    // Attributes / Fields (State)
    String nomorRekening;
    String namaPemilik;
    double saldo;

    // Behaviors / Methods (Aksi Objek)
    void setorTunai(double nominal) {
        if (nominal > 0) {
            this.saldo += nominal;
            System.out.printf("Setor Tunai Rp %,.2f Berhasil! Saldo saat ini: Rp %,.2f%n", nominal, this.saldo);
        } else {
            System.out.println("Nominal setor tunai harus lebih dari Rp 0!");
        }
    }

    void tarikTunai(double nominal) {
        if (nominal <= 0) {
            System.out.println("Nominal penarikan tidak valid!");
        } else if (nominal > this.saldo) {
            System.out.printf("Penarikan Gagal! Saldo tidak mencukupi (Saldo: Rp %,.2f)%n", this.saldo);
        } else {
            this.saldo -= nominal;
            System.out.printf("Tarik Tunai Rp %,.2f Berhasil! Sisa Saldo: Rp %,.2f%n", nominal, this.saldo);
        }
    }

    void cetakBukuTabungan() {
        System.out.println("--------------------------------------------------");
        System.out.printf("No. Rekening : %s%n", this.nomorRekening);
        System.out.printf("Pemilik      : %s%n", this.namaPemilik);
        System.out.printf("Total Saldo  : Rp %,.2f%n", this.saldo);
        System.out.println("--------------------------------------------------");
    }
}

// KELAS UTAMA UNTUK MENJALANKAN PROGRAM
public class Modul08 {
    public static void main(String[] args) {
        System.out.println("=== SISTEM PERBANKAN BERBASIS OOP ===");

        // 1. Instansiasi Objek Pertama
        RekeningBank rekeningBudi = new RekeningBank();
        rekeningBudi.nomorRekening = "ID-10029381";
        rekeningBudi.namaPemilik = "Budi Pratama";
        rekeningBudi.saldo = 500000.0;

        // 2. Instansiasi Objek Kedua (Independen dari Objek Pertama)
        RekeningBank rekeningSiti = new RekeningBank();
        rekeningSiti.nomorRekening = "ID-10029382";
        rekeningSiti.namaPemilik = "Siti Rahma";
        rekeningSiti.saldo = 1200000.0;

        // 3. Menjalankan Behavior pada Masing-Masing Objek
        System.out.println("\n[Aktivitas Rekening Budi]");
        rekeningBudi.setorTunai(250000.0);
        rekeningBudi.tarikTunai(1000000.0); // Penarikan harus gagal karena saldo kurang
        rekeningBudi.tarikTunai(300000.0);  // Penarikan berhasil

        System.out.println("\n[Aktivitas Rekening Siti]");
        rekeningSiti.tarikTunai(200000.0);

        // 4. Memeriksa Laporan Status Akhir
        System.out.println("\n[LAPORAN AKHIR STATUS REKENING]");
        rekeningBudi.cetakBukuTabungan();
        rekeningSiti.cetakBukuTabungan();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 2 (`class RekeningBank`):** Cetak biru entitas. Perhatikan di sini **tidak ada kata kunci `static`** pada field atau method, karena field dan method ini menjadi kepemilikan unik setiap objek (*instance member*).
- **Baris 9 (`void setorTunai(double nominal)`):** Behavior yang mengubah keadaan internal (*state*) objek. Nilai `this.saldo` diubah secara aman dengan validasi terlebih dahulu.
- **Baris 46 (`RekeningBank rekeningBudi = new RekeningBank();`):** Kata kunci `new` menciptakan satu entitas `RekeningBank` fisik di Heap Memory. `rekeningBudi` bertindak sebagai *remote control* penunjuk objek tersebut.
- **Baris 52 (`RekeningBank rekeningSiti = new RekeningBank();`):** Menciptakan objek kedua. Mutasi saldo pada `rekeningBudi` **sama sekali tidak mempengaruhi** saldo `rekeningSiti`. Inilah pembuktian isolasi data OOP.

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Tragedi Paling Terkenal: `NullPointerException`
* **Masalah:** Mendeklarasikan variabel objek tetapi lupa menginisialisasinya dengan kata kunci `new`.
  ```java
  RekeningBank rekeningSaya; // Hanya pointer kosong (null)!
  rekeningSaya.setorTunai(50000); // CRASH SEKETIKA!
  ```
* **Pesan Error:**
  ```text
  Exception in thread "main" java.lang.NullPointerException: Cannot invoke "...setorTunai(double)" because "rekeningSaya" is null
  ```
* **Solusi:** Selalu pastikan objek telah di-instansiasi dengan `new ClassName()` sebelum memanggil properti atau metodenya.

---

### 2. Mengubah Atribut dengan Nilai yang Tidak Masuk Akal
* **Masalah:** Karena field `saldo` masih bersifat default/publik, programmer lain dari kelas `main` bisa sengaja atau tidak sengaja menulis:
  ```java
  rekeningBudi.saldo = -99999999; // Saldo langsung hancur tanpa validasi!
  ```
* **Pelajaran Industri:** Inilah alasan utama mengapa kita membutuhkan pilar **Encapsulation & Private Modifiers** (yang akan kita pelajari tuntas di Modul 9).

---

### 3. Salah Menaruh Keyword `static` pada Field Objek
* **Masalah:** Jika kamu menulis `static double saldo;` di dalam `class RekeningBank`.
* **Dampak Mengerikan:** Variabel static menjadi milik bersama kelas. Jika Budi menyetor Rp 500.000, tiba-tiba saldo Siti dan seluruh nasabah lain di dunia berubah ikut menjadi Rp 500.000!

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju Modul Berikutnya

- **Mengapa Modul 8 Membutuhkan Modul 9?** Di modul ini kamu melihat bahwa atribut objek masih bisa diakses dan diubah secara bebas dari luar (`rekeningBudi.saldo = 0`). Di **Modul 9: Constructor & Encapsulation**, kita akan mempelajari cara "mengunci" data menggunakan hak akses `private` dan memastikan inisialisasi awal wajib lewat *Constructor*.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Entitas Produk E-Commerce
- **Instruksi:** Buat file `Latihan08Dasar` dengan class `Produk` yang memiliki field: `namaProduk` (`String`), `harga` (`int`), dan `stok` (`int`). Tambahkan method `tampilkanDetail()` dan method `kurangiStok(int jumlah)`.
- **Target Output:**
  ```text
  Produk: Mouse Wireless Gaming | Harga: Rp 250,000 | Stok: 15
  Membeli 3 unit...
  Sisa stok terbaru: 12
  ```

---

### Tingkat Menengah: Simulator Karakter Game RPG
- **Instruksi:** Buat class `KarakterGame` dengan field: `nama`, `level`, `darah (HP)`, dan `attackPower`. Buat method:
  1. `serang(KarakterGame musuh)` $\rightarrow$ Mengurangi darah karakter musuh sebesar attackPower karakter penyerang.
  2. `sembuhkanDiri()` $\rightarrow$ Menambah darah sebanyak 20 poin.
  Simulasikan duel 2 karakter hingga salah satu darahnya berkurang.
- **Target Output:**
  ```text
  Ksatria Arthur menyerang Monster Goblin (Damage: 25)!
  Darah Goblin tersisa: 75 HP
  ```

---

### Tingkat Tantangan (Challenge): Sistem Rental Kendaraan Terintegrasi
- **Instruksi:** Buat class `KendaraanRental` dengan field `nomorPlat`, `tipeMobil`, `tarifPerHari`, dan status `isTersedia` (`boolean`).
  Lengkapi dengan method `sewaMobil(int jumlahHari)` yang hanya dapat disewa jika status `isTersedia == true`, menghitung total biaya sewa, lalu mengubah statusnya menjadi `false`. Buat juga method `kembalikanMobil()` yang mengembalikan ketersediaan menjadi `true`.
- **Target Output:**
  ```text
  Mobil Avanza (B 1234 CD) berhasil disewa untuk 3 hari. Total Biaya: Rp 900,000
  Mencoba menyewa lagi... Peringatan: Mobil sedang disewa orang lain!
  Mobil dikembalikan... Status Avanza kini: Tersedia kembali!
  ```
