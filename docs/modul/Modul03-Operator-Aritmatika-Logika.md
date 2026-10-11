# Modul 03: Operator Aritmatika, Perbandingan, dan Logika

> **Deskripsi Singkat:**  
> Mesin komputasi logika Java. Modul ini membahas tuntas cara kerja operator aritmatika (`+`, `-`, `*`, `/`, `%`), operator penugasan (*assignment*), operator perbandingan (`==`, `!=`, `<`, `>`, `<=`, `>=`), operator logika (`&&`, `||`, `!`), serta konsep krusial *Short-Circuit Evaluation* dan prioritas operator (*operator precedence*).
>
> **Tujuan Pembelajaran:**
> 1. Menguasai operasi aritmatika dasar dan memahami sifat pembagian integer vs bilangan desimal serta sisa bagi (modulo).
> 2. Mampu membuat ekspresi relasional perbandingan untuk menghasilkan nilai kebenaran `boolean`.
> 3. Memahami kombinasi gerbang logika majemuk (`AND`, `OR`, `NOT`) dan optimasi *short-circuit evaluation*.
> 4. Menghindari kesalahan presisi tipe data dan kerancuan assignment `=` versus equality `==`.

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Operator Modulo (`%`) = Membagi Permen ke Anak-Anak:**  
   Jika kamu memiliki 14 butir permen dan ingin membaginya rata kepada 4 anak, setiap anak mendapat 3 permen utuh ($14 / 4 = 3$). Sisa permen yang tidak bisa dibagi rata di tanganmu adalah 2 ($14 \% 4 = 2$). Modulo adalah cara komputer mencari **sisa pembagian** (sangat berguna untuk menentukan angka genap/ganjil atau pembagian waktu menit/detik).
2. **Operator Logika `&&` (AND) = Pintu Brankas dengan Dua Kunci:**  
   Pintu brankas bank hanya terbuka jika Manajer memutar Kunci A **DAN** Satpam memutar Kunci B. Jika salah satu kunci tidak cocok, pintu tetap terkunci (`false`).
3. **Operator Logika `||` (OR) = Pembayaran dengan Tunai ATAU QRIS:**  
   Kamu bisa membawa pulang barang belanjaan jika membayar dengan Tunai **ATAU** QRIS. Selama salah satu metode berhasil, transaksi sukses (`true`).
4. **Short-Circuit Evaluation = Tombol Darurat yang Ditekan Lebih Dulu:**  
   Jika kunci pertama pada brankas sudah patah, manajer tidak perlu membuang energi mencoba kunci kedua, karena brankas sudah pasti gagal dibuka!

```
+--------------------------------------------------------------+
| TABEL LOGIKA KILAT:                                          |
|                                                              |
| Kondisi A  Kondisi B   A && B (AND)   A || B (OR)   !A (NOT) |
|  true       true        true           true          false   |
|  true       false       false          true          false   |
|  false      true        false          true          true    |
|  false      false       false          false         true    |
+--------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Komputer tidak memiliki intuisi manusia. Agar komputer mampu mengambil keputusan cerdas (misalnya: *"Apakah nasabah ini berhak mendapat pinjaman?"*), programmer harus mampu menerjemahkan aturan bisnis dunia nyata menjadi formula logika aljabar Boolean matematis yang presisi.

---

## 3. Penjelasan Teori & Aturan Main

### A. Operator Aritmatika & Jebakan Integer Truncation
- `+`, `-`, `*`: Penjumlahan, pengurangan, perkalian.
- `/`: Pembagian. **Aturan:** Jika membagi dua bilangan bulat (`int / int`), hasilnya selalu dibulatkan ke bawah (desimal dipotong). Contoh: `7 / 2` menghasilkan `3`, bukan `3.5`. Agar menghasilkan `3.5`, salah satu operand harus bertipe desimal (`7.0 / 2` atau `(double) 7 / 2`).
- `%`: Modulo (sisa bagi). Contoh: `10 % 3 = 1`.

### B. Operator Perbandingan (Hasil Selalu `boolean`)
- `==`: Sama dengan (membandingkan nilai).
- `!=`: Tidak sama dengan.
- `>`, `<`, `>=`, `<=`: Lebih besar, lebih kecil, lebih besar sama dengan, lebih kecil sama dengan.

### C. Operator Logika & Short-Circuit
- `&&` (Logical AND): Menghasilkan `true` hanya jika kedua sisi bernilai `true`. Jika operand kiri sudah `false`, operand kanan **tidak akan dievaluasi** (menghemat waktu prosesor).
- `||` (Logical OR): Menghasilkan `true` jika minimal salah satu sisi bernilai `true`. Jika operand kiri sudah `true`, operand kanan **tidak akan dievaluasi**.
- `!` (Logical NOT): Membalikkan nilai logika (`!true` menjadi `false`).

---

## 4. Contoh Kode Program Komplit & Executable

```java
import java.util.Scanner;

public class Modul03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM EVALUASI KELAYAKAN BEASISWA ===");

        System.out.print("Masukkan Nilai Tes Potensi Akademik (0-100): ");
        int nilaiTpa = input.nextInt();

        System.out.print("Masukkan Pendapatan Orang Tua per Bulan (Rp): ");
        double pendapatanOrtu = input.nextDouble();

        System.out.print("Masukkan Jumlah Tanggungan Keluarga (anak): ");
        int tanggungan = input.nextInt();

        // 1. Operasi Aritmatika: Rata-rata beban pendapatan per tanggungan
        double bebanPerAnak = pendapatanOrtu / tanggungan;

        // 2. Operasi Perbandingan & Logika
        boolean isNilaiMemenuhi = nilaiTpa >= 80;
        boolean isEkonomiMemenuhi = (bebanPerAnak <= 1500000.0) || (tanggungan >= 4);

        // 3. Evaluasi Kelayakan Majemuk dengan Gerbang AND (&&)
        boolean isLayakBeasiswa = isNilaiMemenuhi && isEkonomiMemenuhi;

        // 4. Pembuktian Modulo: Nomor antrean ganjil atau genap
        boolean isNomorGanjil = (nilaiTpa % 2 != 0);

        System.out.println("\n-------------------------------------------");
        System.out.println("             HASIL ANALISIS DATA           ");
        System.out.println("-------------------------------------------");
        System.out.printf("Rata-rata Biaya / Tanggungan: Rp %,.2f%n", bebanPerAnak);
        System.out.printf("Nilai TPA Lolos Kriteria?   : %b%n", isNilaiMemenuhi);
        System.out.printf("Kondisi Finansial Layak?    : %b%n", isEkonomiMemenuhi);
        System.out.printf("STATUS FINAL DITERIMA       : %b%n", isLayakBeasiswa);
        System.out.printf("Verifikasi Kode Nilai Ganjil: %b%n", isNomorGanjil);
        System.out.println("-------------------------------------------");

        input.close();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 18 (`double bebanPerAnak = pendapatanOrtu / tanggungan;`):** Pembagian antara `double` dan `int`. Java secara otomatis melakukan *type promotion*, mengubah `tanggungan` menjadi representasi floating-point sehingga hasil pembagian memiliki presisi desimal akurat.
- **Baris 21 (`boolean isNilaiMemenuhi = nilaiTpa >= 80;`):** Mengevaluasi nilai TPA. Jika `nilaiTpa` bernilai 85, ekspresi ini menghasilkan nilai literal `true`.
- **Baris 22 (`(bebanPerAnak <= 1500000.0) || (tanggungan >= 4);`):** Menggunakan operator `||` (OR). Pelajar tetap dinyatakan memenuhi kriteria finansial jika beban per anak rendah ATAU keluarga memiliki anak banyak ($\ge 4$).
- **Baris 25 (`boolean isLayakBeasiswa = isNilaiMemenuhi && isEkonomiMemenuhi;`):** Logika AND (`&&`). Mahasiswa hanya lulus seleksi akhir jika KEDUA syarat (akademik DAN ekonomi) terpenuhi.
- **Baris 28 (`boolean isNomorGanjil = (nilaiTpa % 2 != 0);`):** Penggunaan modulo `%` untuk mendeteksi bilangan ganjil (sisa pembagian dengan 2 tidak sama dengan 0).

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Tertukar Antara Operator Assignment (`=`) dan Equality (`==`)
* **Masalah:** Menulis `if (skor = 100)` saat ingin membandingkan nilai.
* **Pesan Error:**
  ```text
  error: incompatible types: int cannot be converted to boolean
  ```
* **Solusi:** Tanda `=` dipakai untuk memasukkan data ke variabel, sedangkan `==` (dua sama dengan) dipakai untuk membandingkan kesetaraan dua nilai.

---

### 2. Truncation Pembagian Bilangan Bulat
* **Masalah:** Menulis `double hasil = 5 / 2;`. Mahasiswa mengharapkan `2.5`, namun yang tersimpan adalah `2.0`.
* **Penyebab:** Evaluasi sebelah kanan tanda sama dengan (`5 / 2`) dihitung lebih dulu sebagai pembagian dua integer, menghasilkan `2`. Baru kemudian `2` diubah menjadi `double` (`2.0`).
* **Solusi:** Pastikan minimal satu operand adalah desimal: `double hasil = 5.0 / 2;` atau `5 / 2.0;`.

---

### 3. Bahaya Membandingkan Objek String Menggunakan `==`
* **Masalah:** Menulis `nama == "Budi"`.
* **Penjelasan:** Operator `==` pada tipe data non-primitif (Objek) membandingkan **alamat memori (pointer)**, bukan isi teksnya!
* **Solusi:** Selalu gunakan metode bawaan `.equals()` untuk membandingkan teks: `nama.equals("Budi")`.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

- **Bahan Bakar Kontrol Percabangan (Modul 4):** Setiap struktur `if`, `else if`, dan `switch` sepenuhnya digerakkan oleh ekspresi boolean yang dihasilkan oleh operator perbandingan dan logika ini.
- **Metode Pembantu Boolean di OOP (*Predicate Methods*):** Di OOP, kamu akan sering membuat method yang mengembalikan boolean berdasarkan formula logika modul ini, misalnya `nasabah.isEligibleForLoan()` atau `rekening.canWithdraw(nominal)`.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Pengecek Bilangan Genap & Kelipatan 5
- **Instruksi:** Buat program `Latihan03Dasar` yang meminta input 1 angka bulat, lalu cetak apakah angka tersebut merupakan bilangan genap DAN sekaligus kelipatan 5.
- **Clue:** Gabungkan dua operasi modulo `(angka % 2 == 0) && (angka % 5 == 0)`.
- **Target Output:**
  ```text
  Masukkan angka: 20
  Apakah 20 adalah bilangan genap dan kelipatan 5? true
  ```

---

### Tingkat Menengah: Konverter Durasi Detik ke Jam, Menit, Detik
- **Instruksi:** Buat program `Latihan03Menengah` yang menerima total durasi waktu dalam satuan detik (contoh: 3665 detik), lalu pecah menjadi format jam, menit, dan sisa detik menggunakan kombinasi pembagian `/` dan modulo `%`.
- **Clue:** 1 jam = 3600 detik, 1 menit = 60 detik.
- **Target Output:**
  ```text
  Masukkan total detik: 3665
  Hasil Konversi: 1 Jam, 1 Menit, 5 Detik.
  ```

---

### Tingkat Tantangan (Challenge): Simulator Hak Akses Sistem Multi-Level
- **Instruksi:** Buat program `Latihan03Tantangan` yang menguji apakah seorang pengguna berhak membuka pintu ruang server.
  Syarat lolos: Memiliki ID Card valid (`boolean`), Mengetahui Kode PIN yang tepat (`int == 9988`), DAN (Memiliki hak akses Administrator `true` ATAU Waktu akses berada di jam kerja antara jam 8 s/d 17).
- **Clue:** Gunakan pengelompokan tanda kurung `(isIdCardValid && pin == 9988) && (isAdmin || (jam >= 8 && jam <= 17))`.
- **Target Output:**
  ```text
  ID Card Valid (true/false) : true
  Input PIN                   : 9988
  Status Admin (true/false)   : false
  Jam Akses Sekarang (0-23)   : 14
  STATUS AKSES PINTU SERVER   : DIIZINKAN (true)
  ```
