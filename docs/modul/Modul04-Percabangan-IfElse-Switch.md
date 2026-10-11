# Modul 04: Percabangan (Decision Making) — `if-else` dan `switch`

> **Deskripsi Singkat:**  
> Mengontrol alur kemudi eksekusi program berdasarkan evaluasi kondisi dinamis. Modul ini mengajarkan penerapan struktur `if`, `else if`, `else`, sintaks modern `switch-case`, operator percabangan ternary (`? :`), serta teknik sanitasi input teks agar tahan terhadap variasi huruf besar/kecil (*case-sensitivity*).
>
> **Tujuan Pembelajaran:**
> 1. Mampu menerapkan struktur `if-else-if` untuk mengevaluasi rentang nilai (*range*) dan kondisi logika majemuk.
> 2. Menguasai struktur `switch-case` dan memahami pentingnya kata kunci `break` serta blok `default`.
> 3. Memahami dan mampu menangani sifat *case-sensitivity* pada perbandingan teks input (`equalsIgnoreCase` / `toLowerCase`).
> 4. Menggunakan operator Ternary untuk menyederhanakan percabangan dua kondisi sederhana.

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **`if-else` = Penjaga Pintu Masuk / Satpam Wahana Taman Hiburan:**  
   Satpam memeriksa pengunjung:
   - *"Jika tinggi badanmu $\ge 150$ cm, kamu boleh naik roller coaster ekstrem."*
   - *"Jika tidak, tapi tinggimu $\ge 120$ cm, kamu boleh naik bianglala santai."*
   - *"Jika tidak memenuhi semuanya, kamu harus bermain di arena balita."*  
   `if-else` sangat kuat untuk menguji **rentang nilai (range) atau perbandingan logika kompleks**.
2. **`switch-case` = Tombol Lift Gedung Bertingkat:**  
   Ketika kamu masuk lift, kamu menekan angka tombol lantai:
   - Tekan 1 $\rightarrow$ Lantai Lobi.
   - Tekan 2 $\rightarrow$ Lantai Restoran.
   - Tekan 3 $\rightarrow$ Lantai Kolam Renang.
   - Tekan tombol selain itu $\rightarrow$ Lift membunyikan peringatan (*default*).  
   `switch` ideal untuk **pilihan nilai diskrit yang sudah pasti dan terpisah-pisah**.

```
+--------------------------------------------------------------+
| KAPAN MEMILIH STRUKTUR PERCABANGAN?                          |
|                                                              |
| [ if - else if - else ]  --> Rentang angka (skor >= 85),     |
|                              kondisi majemuk (A && B || C)   |
| [ switch - case ]        --> Pilihan pasti (Menu 1, 2, 3,    |
|                              Nama Hari: "SENIN", "SELASA")   |
| [ Ternary (? :) ]        --> Kondisi 1 baris (isLulus ? A:B) |
+--------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Secara default, komputer menjalankan kode dari baris atas ke baris bawah secara lurus (*sekuensial*). Namun dunia nyata penuh dengan cabang pilihan. Percabangan diciptakan agar perangkat lunak dapat beradaptasi secara cerdas terhadap berbagai skenario data dan aksi pengguna yang berbeda.

---

## 3. Penjelasan Teori & Aturan Main

### A. Anatomi `if - else if - else`
```java
if (kondisi_1) {
    // Berjalan jika kondisi_1 true
} else if (kondisi_2) {
    // Berjalan jika kondisi_1 false TAPI kondisi_2 true
} else {
    // Berjalan jika SEMUA kondisi di atas false
}
```

### B. Anatomi `switch-case`
```java
switch (variabelPilihan) {
    case nilai1:
        // Jalankan perintah 1
        break; // Wajib: agar tidak bocor ke case berikutnya
    case nilai2:
        // Jalankan perintah 2
        break;
    default:
        // Dijalankan jika tidak ada case yang cocok
        break;
}
```
*Tipe data yang didukung oleh `switch`:* `byte`, `short`, `char`, `int`, `String`, dan `enum`.

### C. Operator Ternary (Shorthand `if-else`)
Bentuk ringkas untuk percabangan dua arah:
```java
tipeData hasil = (kondisi) ? nilaiJikaTrue : nilaiJikaFalse;
```
Contoh: `String status = (nilai >= 75) ? "LULUS" : "REMIDIAL";`

---

## 4. Contoh Kode Program Komplit & Executable

```java
import java.util.Scanner;

public class Modul04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM GERBANG TOL OTOMATIS & PENILAIAN ===");

        // BAGIAN 1: Demonstrasi if-else-if untuk Evaluasi Skor Ujian
        System.out.print("Masukkan Skor Ujian Mengemudi (0-100): ");
        double skor = input.nextDouble();

        String grade;
        if (skor >= 85.0) {
            grade = "A (Sangat Mahir)";
        } else if (skor >= 75.0) {
            grade = "B (Mahir)";
        } else if (skor >= 60.0) {
            grade = "C (Cukup)";
        } else {
            grade = "D (Wajib Latihan Tambahan)";
        }
        System.out.printf("Hasil Ujian: %s%n", grade);

        // BAGIAN 2: Operator Ternary
        String statusKelulusan = (skor >= 75.0) ? "SELAMAT ANDA LULUS SIM" : "MOHON MAAF BELUM LULUS";
        System.out.printf("Status Akhir: %s%n%n", statusKelulusan);

        // Membersihkan buffer sebelum membaca input String berikutnya
        input.nextLine();

        // BAGIAN 3: Demonstrasi switch-case & Antisipasi Case-Sensitivity
        System.out.println("--- PENENTUAN TARIF GERBANG TOL ---");
        System.out.print("Masukkan Kategori Kendaraan (Motor / Mobil / Truk / Bus): ");
        String jenisKendaraan = input.nextLine();

        int tarifTol;
        // Mengubah input ke huruf kecil agar "MOBIL", "Mobil", "mobil" terproses sama
        switch (jenisKendaraan.toLowerCase().trim()) {
            case "motor":
                tarifTol = 5000;
                break;
            case "mobil":
                tarifTol = 15000;
                break;
            case "bus":
                tarifTol = 25000;
                break;
            case "truk":
                tarifTol = 35000;
                break;
            default:
                tarifTol = -1; // Penanda kategori tidak valid
                break;
        }

        if (tarifTol != -1) {
            System.out.printf("Kendaraan '%s' dikenakan tarif: Rp %,d%n", jenisKendaraan, tarifTol);
        } else {
            System.out.printf("Peringatan: Kategori kendaraan '%s' tidak dikenali sistem!%n", jenisKendaraan);
        }

        input.close();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 13-22 (`if-else-if Ladder`):** Menguji kondisi secara berurutan dari atas ke bawah. Begitu salah satu kondisi benar (misal `skor = 90` memenuhi `skor >= 85.0`), seluruh blok `else if` di bawahnya dilewati seketika.
- **Baris 26 (`String statusKelulusan = (skor >= 75.0) ? ... : ...;`):** Operator ternary menguji apakah `skor >= 75.0`. Jika `true`, teks sebelah kiri titik dua dipilih; jika `false`, teks sebelah kanan dipilih.
- **Baris 30 (`input.nextLine();`):** Menguras sisa karakter Enter dari pemanggilan `nextDouble()` sebelumnya agar input teks kendaraan berikutnya tidak terlewat (*buffer handling*).
- **Baris 39 (`jenisKendaraan.toLowerCase().trim()`):**  
  - `.toLowerCase()` mengubah semua karakter teks menjadi huruf kecil sehingga aman dicocokkan dengan `case "mobil"`.
  - `.trim()` membuang spasi tak sengaja di awal atau akhir teks (misal `" mobil "`).
- **Baris 42 & 45 (`break;`):** Memaksa alur keluar dari blok `switch`. Tanpa kata kunci ini, Java akan mengeksekusi case berikutnya secara otomatis (*fall-through*).

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Lupa Menambahkan Kata Kunci `break` pada `switch` (Fall-Through Bug)
* **Masalah:** Lupa menulis `break;` pada setiap `case`.
* **Gejala:** Jika user memilih `"motor"`, program akan menjalankan tarif motor, lalu lanjut menjalankan tarif mobil, truk, dan bahkan default sekaligus!
* **Solusi:** Selalu pasang `break;` di setiap akhir blok `case`, kecuali sengaja ingin menggabungkan beberapa case bersamaan.

---

### 2. Membandingkan String Input Menggunakan `==` pada Blok `if`
* **Masalah:** Menulis `if (jenisKendaraan == "mobil")`.
* **Gejala:** Kondisi selalu bernilai `false` meskipun user mengetikkan kata `mobil`.
* **Penjelasan:** String dari `Scanner` adalah objek baru di memori Heap, sedangkan `"mobil"` berada di *String Constant Pool*. Operator `==` membandingkan alamat fisik memori, bukan kesamaan hurufnya.
* **Solusi:** Gunakan `jenisKendaraan.equalsIgnoreCase("mobil")` atau `.equals()`.

---

### 3. Urutan Kondisi Rentang Nilai yang Terbalik
* **Masalah:** Menaruh kondisi yang lebih longgar di paling atas.
  ```java
  if (skor >= 60) {
      grade = "C";
  } else if (skor >= 85) { // Baris ini TIDAK PERNAH tercapai!
      grade = "A";
  }
  ```
* **Penjelasan:** Nilai 90 akan langsung ditangkap oleh `skor >= 60`, sehingga orang dengan nilai 90 justru mendapat nilai C.
* **Solusi:** Susun kondisi rentang secara hierarkis dari yang paling ketat (tertinggi) ke yang paling longgar (terendah).

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

- **Guard Clause dalam Desain Metode OOP:** Pada OOP, percabangan digunakan sebagai *Guard Clause* untuk memvalidasi parameter sebelum mengubah status objek (contoh: melempar error jika penarikan uang melebihi saldo rekening).
- **Refactoring Polimorfisme (Polymorphism):** Di level OOP lanjut, blok `switch-case` yang terlalu panjang dan kompleks sering diubah (*refactor*) menjadi *Polymorphism / Factory Pattern* agar kode lebih fleksibel dan mudah diperluas tanpa mengubah kode yang sudah ada.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Cek Tahun Kabisat
- **Instruksi:** Buat program `Latihan04Dasar` untuk menentukan apakah suatu tahun adalah tahun kabisat atau bukan.
  Aturan: Tahun habis dibagi 400 adalah kabisat. Jika tidak, tahun habis dibagi 4 TAPI tidak habis dibagi 100 adalah kabisat. Selain itu adalah tahun biasa.
- **Clue:** `(tahun % 400 == 0) || ((tahun % 4 == 0) && (tahun % 100 != 0))`.
- **Target Output:**
  ```text
  Masukkan tahun: 2024
  Tahun 2024 adalah Tahun Kabisat (Februari 29 Hari).
  ```

---

### Tingkat Menengah: Kalkulator Tarif Parkir Progresif
- **Instruksi:** Buat program `Latihan04Menengah` yang menghitung biaya parkir berdasarkan jenis kendaraan (`Mobil` atau `Motor`) dan lama parkir dalam satuan jam.
  Aturan tarif:
  - Motor: Jam ke-1 Rp 2.000, setiap jam berikutnya Rp 1.000.
  - Mobil: Jam ke-1 Rp 5.000, setiap jam berikutnya Rp 3.000.
  - Maksimal biaya parkir harian: Rp 20.000 (motor) dan Rp 50.000 (mobil).
- **Target Output:**
  ```text
  Jenis Kendaraan: Mobil
  Durasi Parkir: 4 Jam
  Total Biaya Parkir: Rp 14,000
  ```

---

### Tingkat Tantangan (Challenge): Mesin ATM Interaktif (Menu Transaksi)
- **Instruksi:** Buat program `Latihan04Tantangan` yang menyimulasikan transaksi perbankan dasar dengan saldo awal Rp 500.000.
  Tampilkan menu pilihan:
  1. Cek Saldo
  2. Tarik Tunai (Validasi: Nominal harus kelipatan 50.000 dan saldo tidak boleh minus)
  3. Setor Tunai (Validasi: Nominal harus $> 0$)
  Gunakan `switch-case` untuk menu dan `if-else` berlapis untuk memvalidasi transaksi.
- **Target Output:**
  ```text
  === ATM BANK SEJAHTERA ===
  1. Cek Saldo | 2. Tarik Tunai | 3. Setor Tunai
  Pilih Menu: 2
  Masukkan jumlah tarik tunai: 200000
  Penarikan Berhasil! Sisa Saldo Anda: Rp 300,000
  ```
