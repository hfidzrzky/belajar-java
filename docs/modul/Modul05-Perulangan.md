# Modul 5: Perulangan (Looping) di Java — `for`, `while`, dan `do-while`

> **Deskripsi Singkat:**  
> Modul ini membedah tuntas mekanisme kontrol perulangan (*iteration control*) dalam Java. Mahasiswa akan memahami kapan dan bagaimana menggunakan `for`, `while`, dan `do-while`, teknik manipulasi alur dengan `break` dan `continue`, cara kerja counter di memori JVM, serta pencegahan kesalahan fatal seperti *infinite loop*.
>
> **Tujuan Pembelajaran:**
> 1. Memahami perbedaan mendasar logika kerja `for`, `while`, dan `do-while` serta mampu memilih struktur loop yang tepat sesuai kebutuhan kasus.
> 2. Menguasai kata kunci pengendali iterasi (`break` dan `continue`) untuk mengoptimalkan efisiensi komputasi program.
> 3. Menganalisis siklus eksekusi loop di memori JVM dan menghindari kesalahan umum pemula (*off-by-one*, *infinite loop*, *resource leakage*).
> 4. Mengintegrasikan logika perulangan dengan percabangan (*nested logic*) sebagai pondasi krusial menuju manipulasi data pada Array dan Object-Oriented Programming (OOP).

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
Bayangkan tiga aktivitas fisik berikut:
1. **Lari 5 Putaran di Lintasan Atletik (`for` loop):**  
   Sebelum mulai lari, pelatih sudah menentukan target: *"Lari tepat 5 putaran!"*. Kamu mulai dari putaran ke-1, setiap selesai satu putaran kamu menambah hitungan (+1), dan begitu mencapai putaran ke-5, kamu berhenti. Jumlah putarannya **pasti dan terukur sejak awal**.
2. **Mengendarai Sepeda Motor (`while` loop):**  
   Kamu berkendara selama kondisi tangki bensin belum habis (`bensin > 0`). Kamu mengecek indikator bensin **sebelum** tancap gas. Jika dari awal bensin sudah kosong, motormu bahkan tidak akan pernah berjalan satu meter pun.
3. **Mencicipi Sup di Restoran (`do-while` loop):**  
   Koki wajib mengambil satu sendok sup lalu **mencicipinya terlebih dahulu minimal satu kali** sebelum memutuskan: *"Apakah rasanya masih kurang garam sehingga perlu diaduk dan dicicipi lagi?"*. Kondisi baru dievaluasi **setelah** aksi pertama selesai dilakukan.

```
+---------------------------------------------------------------------------------+
|                                 SPEKTRUM LOOP                                   |
|                                                                                 |
|  [ for ]                --> Jumlah iterasi SUDAH DIKETAHUI di awal              |
|  [ while ]              --> Kondisi dicek DI AWAL (Bisa jadi tidak jalan sama   |
|                             sekali jika kondisi false)                          |
|  [ do-while ]           --> Kondisi dicek DI AKHIR (Dijamin MINIMAL 1X jalan)   |
+---------------------------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Tanpa konsep perulangan, untuk mencetak angka 1 sampai 1.000 atau memproses 100 data transaksi pelanggan, kita terpaksa menulis `System.out.println()` sebanyak 1.000 kali. Hal ini melanggar prinsip fundamental software engineering: **DRY (*Don't Repeat Yourself*)**.  
Perulangan memampukan CPU mengeksekusi jutaan kalkulasi berulang hanya dengan 3-4 baris kode instruksi, menghemat memori kode, mempermudah pemeliharaan (*maintenance*), dan memungkinkan aplikasi berskala dinamis.

---

## 3. Penjelasan Teori & Aturan Main

### A. Anatomi `for` Loop
Digunakan saat batas bawah, batas atas, dan step (kenaikan/penurunan) sudah terdefinisi jelas.
```java
for (inisialisasi; kondisi; update) {
    // Blok kode yang diulang
}
```
1. **Inisialisasi:** Deklarasi variabel counter (dijalankan **hanya satu kali** di awal).
2. **Kondisi:** Ekspresi bertipe `boolean`. Sebelum blok loop dijalankan, kondisi ini dicek. Jika `true`, lanjut; jika `false`, keluar dari loop.
3. **Update:** Menambah (`i++`) atau mengurangi (`i--`) nilai counter setiap kali satu putaran blok kode selesai.

### B. Anatomi `while` Loop
Digunakan saat jumlah iterasi bergantung pada kondisi dinamis atau kejadian tak tentu (misal: menunggu input tertentu dari user atau menunggu koneksi database).
```java
while (kondisi_boolean) {
    // Blok kode dieksekusi selama kondisi bernilai true
    // Wajib ada baris perubahan status kondisi agar tidak terjadi infinite loop!
}
```

### C. Anatomi `do-while` Loop
Paling sering digunakan pada sistem berbasis **Menu Interaktif Console** atau validasi input.
```java
do {
    // Blok kode ini dijamin berjalan MINIMAL 1 KALI
} while (kondisi_boolean); // <-- CATATAN: Wajib diakhiri tanda titik koma (;)
```

### D. Keyword `break` vs `continue`
- **`break`:** Menghentikan loop seketika dan langsung melompat ke baris setelah penutup blok loop.
- **`continue`:** Melewati sisa kode pada iterasi saat ini dan langsung melompat ke iterasi/putaran berikutnya.

### Perbandingan Karakteristik 3 Jenis Loop
| Karakteristik | `for` | `while` | `do-while` |
| :--- | :--- | :--- | :--- |
| **Kapan kondisi dievaluasi?** | Sebelum iterasi dimulai | Sebelum iterasi dimulai | Setelah iterasi selesai |
| **Peluang jalan 0 kali?** | Ya (jika kondisi awal `false`) | Ya (jika kondisi awal `false`) | Tidak (pasti minimal 1 kali) |
| **Kasus Penggunaan Terbaik** | Iterasi deret, array, hitungan pasti | Membaca stream data, status sensor | Menu CLI, konfirmasi password |
| **Tanda Titik Koma Akhir** | Tidak ada | Tidak ada | Wajib (setelah `while(..);`) |

---

## 4. Contoh Kode Program Komplit & Executable

Simpan berkas berikut dengan nama file `Modul5.java`:

```java
import java.util.Scanner;

public class Modul5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== DEMO 1: FOR LOOP (Iterasi Terhitung) ===");
        System.out.print("Masukkan jumlah pengulangan roket meluncur: ");
        int hitunganMundur = input.nextInt();

        // FOR LOOP: Inisialisasi, Kondisi, Update
        for (int i = hitunganMundur; i >= 1; i--) {
            System.out.printf("Hitungan ke-%d: Mesin aktif!%n", i);
        }
        System.out.println("Meluncur ke luar angkasa!\n");

        System.out.println("=== DEMO 2: WHILE LOOP (Berdasarkan Kondisi Dinamis) ===");
        int energiBaterai = 50;
        int putaranRobot = 0;

        // WHILE LOOP: Berhenti ketika baterai tidak lagi mencukupi
        while (energiBaterai > 10) {
            putaranRobot++;
            energiBaterai -= 15; // Konsumsi energi tiap putaran
            System.out.printf("Robot patroli putaran ke-%d (Sisa Baterai: %d%%)%n", putaranRobot, energiBaterai);
        }
        System.out.println("Baterai menipis! Robot harus kembali ke dock pengisian.\n");

        System.out.println("=== DEMO 3: DO-WHILE LOOP (Minimal Jalan Sekali) ===");
        int pinRahasia = 1234;
        int inputPin;
        int percobaan = 0;

        // DO-WHILE: Meminta input pengguna minimal 1 kali sebelum evaluasi kondisi
        do {
            percobaan++;
            System.out.print("Masukkan PIN keamanan (Hint: 1234): ");
            inputPin = input.nextInt();

            if (inputPin == pinRahasia) {
                System.out.println("Autentikasi Berhasil! Pintu terbuka.");
                break; // Menghentikan loop seketika jika PIN valid
            } else {
                System.out.printf("PIN salah! (Percobaan ke-%d)%n", percobaan);
            }

            if (percobaan >= 3) {
                System.out.println("Akun terkunci sementara karena 3x salah memasukkan PIN!");
                break;
            }
        } while (inputPin != pinRahasia);

        System.out.println("\n=== DEMO 4: PENGGUNAAN CONTINUE & BREAK ===");
        System.out.println("Mencetak bilangan ganjil 1 s/d 10 (melewati genap):");
        for (int angka = 1; angka <= 10; angka++) {
            if (angka % 2 == 0) {
                continue; // Lewati sisa kode di bawah dan langsung ke iterasi berikutnya
            }
            System.out.printf("Angka ganjil terpilih: %d%n", angka);
        }

        // Mencegah kebocoran sumber daya sistem (resource leak)
        input.close();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 1 & 5:** Mengimpor `java.util.Scanner` dan membuat kelas publik `Modul5` dengan metode utama `main` sebagai *entry point* eksekusi JVM.
- **Baris 12:** `for (int i = hitunganMundur; i >= 1; i--)`
  - `int i = hitunganMundur`: Mengalokasikan variabel counter `i` pada *Java Stack Memory* lokal loop.
  - `i >= 1`: JVM memverifikasi apakah `i` masih lebih besar atau sama dengan 1 sebelum masuk ke tubuh loop.
  - `i--`: Nilai `i` dikurangi satu setiap kali instruksi cetak di dalam kurung kurawal selesai dijalankan.
- **Baris 23:** `while (energiBaterai > 10)`
  - Mengevaluasi nilai `energiBaterai`. Pada saat nilainya turun menjadi `5` (setelah 3 iterasi), kondisi `5 > 10` bernilai `false`. JVM otomatis menghentikan perulangan dan melompat ke baris 28.
- **Baris 36:** `do { ... } while (inputPin != pinRahasia);`
  - Blok instruksi di dalam kurung kurawal `do` dieksekusi terlebih dahulu. Pengguna diminta menginput PIN.
  - Baru di baris akhir, ekspresi `inputPin != pinRahasia` diuji. Jika PIN masih salah, kontrol melompat kembali ke atas `do`.
- **Baris 42 & 49:** Kata kunci `break`
  - Baris 42 keluar dari loop secara elegan saat PIN berhasil ditebak.
  - Baris 49 berfungsi sebagai pengaman batas percobaan (*guard clause*) agar pengguna tidak bisa menebak tanpa batas waktu.
- **Baris 56:** `if (angka % 2 == 0) continue;`
  - Jika angka habis dibagi 2 (genap), `continue` memutus eksekusi iterasi saat itu dan langsung memicu pembaruan `angka++` di header `for`, sehingga baris `System.out.printf` tidak pernah dipanggil untuk angka genap.
- **Baris 63:** `input.close();`
  - Menutup stream `System.in` untuk melepas handle OS dan mencegah peringatan kebocoran memori (*resource leak warning*).

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Perangkap Perulangan Tanpa Akhir (*Infinite Loop*)
* **Masalah:** Lupa menambahkan perintah pengubah counter/status di dalam badan `while` loop, atau kondisi loop selalu bernilai `true`.
* **Contoh Kode Salah:**
  ```java
  int i = 1;
  while (i <= 5) {
      System.out.println("Nilai: " + i);
      // Lupa menulis: i++;
  }
  ```
* **Gejala:** CPU melonjak tinggi (100% usage), terminal mencetak teks tiada henti, dan program tidak pernah selesai (*freeze*).
* **Solusi:** Pastikan setiap `while` memiliki instruksi yang mendekatkan variabel penguji ke arah kondisi terminasi (`false`).

---

### 2. Kesalahan Satu Hitungan (*Off-by-One Error*)
* **Masalah:** Kebingungan membedakan operator `<` (kurang dari) dengan `<=` (kurang dari sama dengan).
* **Contoh Kasus:**
  Ingin mencetak angka 1 sampai 10, tetapi menulis:
  ```java
  for (int i = 1; i < 10; i++) { // Hanya berulang sampai 9!
      System.out.println(i);
  }
  ```
* **Solusi:** Teliti membaca batas akhir: gunakan `<=` jika nilai ujung ingin diikutsertakan, atau perhatikan bahwa jika mulai dari `0`, batas `< n` akan berulang tepat sebanyak `n` kali.

---

### 3. Jebakan Titik Koma di Belakang Header Loop
* **Masalah:** Memberikan tanda titik koma (`;`) langsung di ujung baris `for` atau `while`.
* **Contoh Kode Salah:**
  ```java
  for (int i = 1; i <= 5; i++); // <-- TITIK KOMA FATAL!
  {
      System.out.println("Angka: " + i); // Error: cannot find symbol variable i
  }
  ```
* **Pesan Error:**
  `error: cannot find symbol variable i`
* **Penjelasan:** Titik koma setelah `for` membuat loop mengulang pernyataan kosong (*empty statement*) sebanyak 5 kali. Begitu loop selesai, variabel `i` langsung dimusnahkan dari memori, sehingga kurung kurawal di bawahnya tidak mengenali variabel `i`.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

### Fondasi dari Modul Sebelumnya
Modul 5 menggabungkan kekuatan dari **Modul 3 (Operator Aritmatika & Perbandingan)** dan **Modul 4 (Percabangan)**. Evaluasi kondisi loop `i <= 10` menggunakan operator relasional, dan di dalam loop hampir selalu terdapat logika seleksi `if-else` untuk menyaring data.

### Mengapa Perulangan Menjadi Jantung OOP?
Dalam dunia industri dan OOP, perulangan bukan sekadar untuk mencetak deret angka matematika. Perulangan adalah instrumen utama untuk:
1. **Memproses Kumpulan Objek (Collections & Arrays - Modul 6 & 8):**
   Bayangkan sistem e-commerce memiliki 10.000 objek `Product`. Anda membutuhkan loop untuk menghitung total omzet atau mencari barang tertentu.
2. **Desain Pola Perilaku Objek (Iterator Pattern):**
   OOP menggunakan antarmuka seperti `Iterable` dan `Iterator` untuk menjelajahi elemen objek di memori secara aman dan terenkapsulasi.
3. **Pemrosesan Data Skala Besar (Java Stream API):**
   Loop tradisional adalah cikal bakal pemahaman sebelum mahasiswa belajar paradigma fungsional modern di Java (`stream().filter().map()`).

---

## 8. Soal Latihan Praktek Mandiri

Kerjakan latihan ini pada berkas terpisah atau modifikasi berkas di folder `latihan/Modul5.java`:

### Tingkat Dasar: Generator Tabel Perkalian
- **Instruksi:** Buat program yang meminta input sebuah angka bilangan bulat positif dari pengguna, lalu cetak tabel perkalian angka tersebut dari 1 hingga 10 menggunakan `for` loop.
- **Clue:** Gunakan format penulisan `System.out.printf("%d x %d = %d%n", angka, i, (angka * i));`.
- **Target Output:**
  ```text
  Masukkan angka perkalian: 7
  7 x 1 = 7
  7 x 2 = 14
  ...
  7 x 10 = 70
  ```

---

### Tingkat Menengah: Kalkulator Tabungan Bunga Majemuk
- **Instruksi:** Buat program yang menghitung pertumbuhan saldo tabungan nasabah per tahun menggunakan `while` loop.
  Program menerima input: saldo awal (contoh: Rp 1.000.000), target saldo akhir (contoh: Rp 2.000.000), dan persentase bunga tahunan (contoh: 5%). Hitung berapa tahun yang dibutuhkan sampai target tercapai.
- **Clue:** Jalankan loop dengan kondisi `while (saldoSaatIni < targetSaldo)`. Tambahkan counter `tahun++` di setiap putaran.
- **Target Output:**
  ```text
  Tahun ke-1: Saldo Rp 1,050,000
  Tahun ke-2: Saldo Rp 1,102,500
  ...
  Target tercapai pada Tahun ke-15 dengan total saldo Rp 2,078,928!
  ```

---

### Tingkat Tantangan (Challenge): Kasir Kantin Interaktif Dinamis
- **Instruksi:** Buat sistem kasir kantin cerdas menggunakan perulangan menu `do-while`, di mana pengguna bisa memilih menu:
  1. Tambah Transaksi Belanja
  2. Selesai & Cetak Struk
  Bila pengguna memilih 1, masukkan nominal belanja. Bila memilih 2, hentikan program dan cetak total barang belanjaan, total biaya, potongan diskon 10% (jika belanja $\ge$ Rp 100.000), dan total akhir yang harus dibayar. Lindungi program dari input harga bernilai $\le 0$ menggunakan kata kunci `continue`.
- **Clue:** Lihat referensi kode pada `latihan/Modul5.java` yang telah disediakan di workspace proyek.
- **Target Output:**
  ```text
  ==================================================
    LATIHAN MODUL 5: SISTEM KASIR KANTIN CERDAS    
  ==================================================
  --- MENU PILIHAN ---
  1. Tambah Transaksi Belanja
  2. Selesai & Cetak Struk Pembayaran
  Pilih opsi (1/2): 1
  Masukkan harga barang (Rp): 60000
  Item ke-1 berhasil dicatat (+Rp 60,000)

  --- MENU PILIHAN ---
  1. Tambah Transaksi Belanja
  2. Selesai & Cetak Struk Pembayaran
  Pilih opsi (1/2): 1
  Masukkan harga barang (Rp): 50000
  Item ke-2 berhasil dicatat (+Rp 50,000)

  --- MENU PILIHAN ---
  1. Tambah Transaksi Belanja
  2. Selesai & Cetak Struk Pembayaran
  Pilih opsi (1/2): 2
  
  ==================================================
               STRUK RESMI PEMBAYARAN               
  ==================================================
  Total Item Dibeli   : 2 item
  Total Tagihan       : Rp 110,000
  Potongan Diskon (10%): Rp 11,000
  --------------------------------------------------
  TOTAL AKHIR DIBAYAR : Rp 99,000
  ==================================================
  Terima kasih telah berbelanja!
  ```
