# Modul 02: Variabel, Tipe Data, dan Input Scanner

> **Deskripsi Singkat:**  
> Menguasai bagaimana komputer mengalokasikan dan menyimpan informasi di dalam memori kerja (RAM). Modul ini mengupas tuntas klasifikasi tipe data primitif vs referensi, pemformatan string tingkat lanjut dengan `printf`, mekanisme pembacaan input keyboard melalui kelas `Scanner`, serta pencegahan *resource leak*.
>
> **Tujuan Pembelajaran:**
> 1. Memahami perbedaan tipe data primitif (`int`, `double`, `boolean`, `char`) dan tipe data objek/referensi (`String`).
> 2. Mampu menggunakan kelas `Scanner` untuk menangkap berbagai jenis input dinamis dari pengguna.
> 3. Menguasai pemformatan tampilan angka dan teks profesional menggunakan *specifier* pada metode `System.out.printf()`.
> 4. Memahami tata kelola alokasi memori serta disiplin menutup stream input (`scanner.close()`).

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Variabel & Tipe Data = Kotak Penyimpanan Berlabel Khusus:**  
   Bayangkan kamu memiliki lemari dengan beberapa kotak:
   - **Kotak Kecil Bertuliskan "Koin" (`int`):** Hanya boleh diisi angka bulat tanpa koma.
   - **Gelas Ukur Bertuliskan "Cairan" (`double`):** Menampung takaran presisi dengan angka pecahan/desimal.
   - **Kotak Saklar "On/Off" (`boolean`):** Hanya punya dua kondisi: menyala (`true`) atau mati (`false`).
   - **Binder Map Tebal Dokumen (`String`):** Menampung lembaran teks, huruf, kata, hingga kalimat utuh.  
   Jika kamu mencoba menuangkan cairan ke dalam kotak koin berlubang, program akan menolak karena tipe wadah tidak cocok (*Type Mismatch*).
2. **Scanner = Mikrofon / Corong Penerima:**  
   Program komputermu seperti stasiun radio. Kelas `Scanner(System.in)` bertindak sebagai mikrofon yang terhubung ke studio untuk mendengarkan ketikan tuts keyboard pendengar (*user*).

```
+-----------------------------------------------------------+
| RAM (Random Access Memory)                                |
|                                                           |
| [ int umur = 21 ]          --> 4 Bytes (Angka Bulat)      |
| [ double ipk = 3.85 ]      --> 8 Bytes (Presisi Desimal)  |
| [ boolean isAktif = true ] --> 1 Bit/Byte (Status Logika) |
| [ String nama ] ---------> Menunjuk teks di Heap Memory   |
+-----------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Komputer bekerja menggunakan miliaran saklar biner (0 dan 1). Manusia tidak efisien jika harus menghafal alamat memori heksadesimal (`0x7FFF5FBFF8C8`) untuk menyimpan angka gaji karyawan. Konsep variabel diciptakan untuk memberi **nama alias yang bermakna (*identifier*)** pada suatu alamat memori, dan tipe data diciptakan agar CPU tahu persis berapa byte kapasitas yang harus dipesan.

---

## 3. Penjelasan Teori & Aturan Main

### A. Klasifikasi Tipe Data di Java
Java adalah bahasa **statically-typed**, artinya tipe variabel harus dideklarasikan di awal dan tidak dapat berubah tipe selama eksekusi.
1. **Tipe Data Primitif (Menyimpan nilai langsung di Stack Memory):**
   - `byte`, `short`, `int` (standar), `long` (angka sangat besar): Bilangan bulat.
   - `float`, `double` (standar presisi industri): Bilangan riil/desimal.
   - `char`: Karakter tunggal 16-bit Unicode (diapit petik satu `'A'`).
   - `boolean`: Nilai kebenaran (`true` atau `false`).
2. **Tipe Data Referensi (Menyimpan alamat rujukan objek di Heap Memory):**
   - `String`: Deretan karakter teks (diapit petik dua `"Java"`).

### B. Format Specifier pada `System.out.printf`
| Specifier | Tipe Data | Keterangan Format |
| :--- | :--- | :--- |
| `%s` | String | Teks / Karakter |
| `%d` | Integer (`int`, `long`) | Angka bulat (desimal basis 10) |
| `%,d` | Integer | Angka bulat dengan pemisah ribuan (misal: `1,000,000`) |
| `%.2f` | Floating-point | Angka desimal dibulatkan tepat 2 digit di belakang koma |
| `%b` | Boolean | Nilai logika `true` atau `false` |
| `%n` | Platform-independent | Baris baru (lebih dianjurkan dibanding `\n` pada `printf`) |

---

## 4. Contoh Kode Program Komplit & Executable

```java
import java.util.Scanner;

public class Modul02 {
    public static void main(String[] args) {
        // Membuka kanal pembacaan keyboard dari System.in
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM PENDAFTARAN MAHASISWA BARU ===");

        // Mengambil input berupa teks satu baris penuh
        System.out.print("Masukkan Nama Lengkap: ");
        String namaLengkap = input.nextLine();

        // Mengambil input berupa angka bilangan bulat
        System.out.print("Masukkan Tahun Angkatan: ");
        int tahunAngkatan = input.nextInt();

        // Mengambil input berupa bilangan desimal (gunakan titik atau koma sesuai locale)
        System.out.print("Masukkan Target IPK (contoh: 3.85): ");
        double targetIpk = input.nextDouble();

        // Mengambil input status keaktifan boolean
        System.out.print("Apakah Mahasiswa Reguler? (true/false): ");
        boolean isReguler = input.nextBoolean();

        // Output dengan teknik String Formatting berstandar profesional
        System.out.println("\n-------------------------------------------");
        System.out.println("           KARTU REGISTRASI MAHASISWA      ");
        System.out.println("-------------------------------------------");
        System.out.printf("Nama Mahasiswa : %s%n", namaLengkap);
        System.out.printf("Tahun Masuk    : %d%n", tahunAngkatan);
        System.out.printf("Target Indeks  : %.2f (Skala 4.00)%n", targetIpk);
        System.out.printf("Status Reguler : %b%n", isReguler);
        System.out.println("-------------------------------------------");

        // Disiplin menutup resource Scanner untuk mencegah resource leak
        input.close();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 1 & 6 (`import java.util.Scanner; Scanner input = new Scanner(System.in);`):** Mengimpor kelas Scanner dari pustaka utilitas bawaan Java dan membuat objek pembaca input dari aliran keyboard (`System.in`).
- **Baris 12 (`input.nextLine();`):** Mengambil satu baris teks penuh termasuk karakter spasi hingga tombol Enter ditekan.
- **Baris 16 (`input.nextInt();`):** Mengurai token input berikutnya menjadi nilai bilangan bulat bertipe `int`.
- **Baris 20 (`input.nextDouble();`):** Membaca angka pecahan bertipe `double`.
- **Baris 29-32 (`System.out.printf(...)`):** Melakukan pencetakan terformat:
  - `%.2f` membatasi tampilan desimal `targetIpk` menjadi dua digit setelah tanda desimal.
  - `%n` menyisipkan pemisah baris yang kompatibel secara otomatis di Windows (`\r\n`) maupun Linux/macOS (`\n`).
- **Baris 36 (`input.close();`):** Melepaskan handle I/O sistem operasi. Tanpa baris ini, IDE modern akan memunculkan peringatan *"Resource leak: 'input' is never closed"*.

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Jebakan "Buffer Ghost" Antara `nextInt()` dan `nextLine()`
* **Masalah:** Menggunakan `nextInt()` lalu tepat di bawahnya memanggil `nextLine()`. Baris `nextLine()` akan tampak terlewati otomatis tanpa menunggu input pengguna!
* **Penyebab:** `nextInt()` hanya membaca karakter angka dan menyisakan karakter Enter (`\n`) di buffer input keyboard. Ketika `nextLine()` dipanggil, ia langsung melahap sisa Enter tersebut.
* **Solusi:** Selalu tambahkan `input.nextLine();` kosong tepat setelah `nextInt()` atau `nextDouble()` untuk membersihkan buffer pemisah baris sebelum membaca string berikutnya.

---

### 2. Format Specifier Tidak Sesuai Tipe Data (*IllegalFormatConversionException*)
* **Masalah:** Menggunakan `%d` untuk mencetak variabel bertipe `double`, atau `%f` untuk `int`.
* **Pesan Error:**
  ```text
  Exception in thread "main" java.util.IllegalFormatConversionException: d != java.lang.Double
  ```
* **Solusi:** Selalu cocokkan: `%d` untuk integer, `%f` untuk double/float, `%s` untuk string.

---

### 3. Masalah Tanda Desimal Titik vs Koma (*InputMismatchException*)
* **Masalah:** Memasukkan desimal dengan titik `3.75` pada OS yang disetel ke Bahasa Indonesia (yang memakai koma `3,75`), atau sebaliknya.
* **Pesan Error:**
  ```text
  Exception in thread "main" java.util.InputMismatchException
  ```
* **Solusi:** Gunakan `input.useLocale(java.util.Locale.US);` setelah inisialisasi Scanner agar pembacaan desimal konsisten menggunakan tanda titik standar internasional.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

- **Variabel Menjadi "Field/Attribute":** Pada OOP (Modul 8), variabel lokal yang kamu pelajari di sini akan dipindahkan ke tingkat kelas menjadi *State / Attributes* objek (misalnya objek `Mahasiswa` memiliki atribut `String nama; double ipk;`).
- **Scanner sebagai Objek Nyata Pertama:** Perintah `Scanner input = new Scanner(System.in);` sebenarnya adalah contoh pembuatan *instance* dari sebuah objek nyata! Kata kunci `new` memanggil *constructor* kelas Scanner.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Konverter Suhu Sederhana
- **Instruksi:** Buat program `Latihan02Dasar` yang meminta input suhu dalam derajat Celcius (`double`), lalu konversikan ke Fahrenheit menggunakan rumus $F = (C \times 9/5) + 32$. Tampilkan hasil dengan 1 angka di belakang koma.
- **Clue:** Hati-hati dengan pembagian integer: tulis `9.0 / 5.0` agar tidak terpotong menjadi 1.
- **Target Output:**
  ```text
  Masukkan suhu Celcius: 30.0
  Suhu dalam Fahrenheit: 86.0 °F
  ```

---

### Tingkat Menengah: Kalkulator Payroll Karyawan
- **Instruksi:** Buat program `Latihan02Menengah` untuk menghitung slip gaji karyawan. Input: Nama Karyawan (`String`), Jumlah Jam Kerja seminggu (`int`), Upah per Jam (`double`). Hitung total gaji kotor, potong pajak PPh 5%, lalu tampilkan slip gaji dengan format ribuan (`%,d` atau `%,.2f`).
- **Target Output:**
  ```text
  Nama Karyawan : Sarah Jenkins
  Gaji Kotor    : Rp 2,000,000.00
  Pajak (5%)    : Rp 100,000.00
  Gaji Bersih   : Rp 1,900,000.00
  ```

---

### Tingkat Tantangan (Challenge): Pembersih Buffer Scanner Kasus Registrasi
- **Instruksi:** Buat program `Latihan02Tantangan` yang meminta input: Nomor Induk Kependudukan / NIK (`long`), Umur (`int`), dilanjutkan dengan Alamat Lengkap (`String` yang memiliki spasi), dan Saldo Awal Tabungan (`double`). Buktikan bahwa kamu telah mengatasi masalah *Buffer Ghost* sehingga input Alamat Lengkap tidak terlewati!
- **Clue:** Panggil `input.nextLine();` ekstra setelah membaca variabel `Umur`.
- **Target Output:**
  ```text
  Masukkan NIK: 3201019283740001
  Masukkan Umur: 24
  Masukkan Alamat Lengkap: Jl. Sudirman No. 45 Jakarta Pusat
  Masukkan Saldo: 500000
  Data Berhasil Disimpan: [NIK: 3201019283740001 | 24 Thn | Alamat: Jl. Sudirman No. 45 Jakarta Pusat | Saldo: Rp 500,000.00]
  ```
