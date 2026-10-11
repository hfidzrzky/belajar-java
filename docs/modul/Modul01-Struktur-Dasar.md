# Modul 01: Struktur Program Java Dasar

> **Deskripsi Singkat:**  
> Fondasi paling awal dalam memahami arsitektur program Java. Modul ini membimbing pemula memahami anatomi file kode sumber Java (`.java`), fungsi kelas utama (*class*), gerbang masuk eksekusi JVM (*main method*), serta aturan sintaks dasar yang wajib dipatuhi.
>
> **Tujuan Pembelajaran:**
> 1. Memahami peran dan hubungan antara *source code*, compiler (`javac`), bytecode (`.class`), dan Java Virtual Machine (JVM).
> 2. Mampu menulis struktur dasar kelas publik dan metode `main` dengan sintaks standar industri.
> 3. Memahami aturan penting Java mengenai *case-sensitivity*, konvensi penamaan *PascalCase*, dan fungsi titik koma (`;`).
> 4. Mampu melakukan kompilasi dan menjalankan program Java pertama secara mandiri melalui terminal.

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
Bayangkan struktur program Java seperti **sebuah surat dinas resmi di dalam amplop tertutup**:
1. **Nama di Amplop (*Public Class*):**  
   Amplop surat mencantumkan identitas resmi penerima di luar. Di Java, nama *public class* harus sama persis huruf besar dan kecilnya dengan nama berkas fisik amplop tersebut (`Modul01.java` $\leftrightarrow$ `public class Modul01`). Jika namanya berbeda, kurir kantor pos (compiler Java) akan menolak memproses surat tersebut.
2. **Pintu Masuk Kantor / Gerbang Utama (*Main Method*):**  
   Ketika surat tiba di kantor tujuan, ada satu pintu gerbang utama tempat surat pertama kali diterima dan diperiksa. Di Java, gerbang ini bernama `public static void main(String[] args)`. Tanpa gerbang ini, mesin JVM bingung dari mana harus mulai mengeksekusi instruksi.
3. **Instruksi di Dalam Surat (*Code Statements*):**  
   Kalimat-kalimat instruksi di dalam surat diakhiri tanda titik. Di Java, setiap perintah diakhiri dengan tanda titik koma (`;`).

```
+-------------------------------------------------------------+
| Berkas Fisik: Modul01.java                                  |
|  +-------------------------------------------------------+  |
|  | public class Modul01 {                                |  |
|  |    +-----------------------------------------------+  |  |
|  |    | public static void main(String[] args) {      |  |  |
|  |    |    // JVM mulai mengeksekusi dari sini         |  |  |
|  |    |    System.out.println("Hello, World!");       |  |  |
|  |    +-----------------------------------------------+  |  |
|  | }                                                     |  |
|  +-------------------------------------------------------+  |
+-------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Bahasa generasi awal sering kali mengeksekusi kode secara prosedural dari baris 1 file tanpa batasan wadah modul. Java diciptakan dengan filosofi **"Write Once, Run Anywhere" (WORA)** dan berbasis objek murni. Setiap instruksi harus memiliki rumah (*class*) agar JVM dapat mengisolasi memori, menjamin keamanan eksekusi, serta memudahkan pengelolaan aplikasi berskala enterprise dengan jutaan baris kode.

---

## 3. Penjelasan Teori & Aturan Main

### A. Alur Kerja Java: Dari Teks hingga Eksekusi
1. **Source Code (`.java`):** Kode yang kita tulis dan dapat dibaca manusia.
2. **Compiler (`javac`):** Menerjemahkan source code menjadi *Bytecode* biner portabel (`.class`).
3. **Java Virtual Machine (JVM):** Mesin virtual yang membaca *bytecode* dan menerjemahkannya ke instruksi prosesor mesin lokal pengguna.

### B. Bedah Header `public static void main(String[] args)`
- `public`: Penanda hak akses (access modifier) agar JVM dari luar kelas dapat memanggil metode ini tanpa batasan.
- `static`: Menandakan metode ini dapat dijalankan langsung oleh JVM tanpa harus membuat objek (*instansiasi*) terlebih dahulu di memori heap.
- `void`: Metode ini murni menjalankan tugas dan tidak mengembalikan nilai balik (*return value*).
- `main`: Nama standar baku yang dicari oleh launcher JVM sebagai titik awal eksekusi.
- `String[] args`: Parameter array teks untuk menampung argumen baris perintah (*command-line arguments*) saat program dijalankan.

### C. Aturan Konvensi Penamaan (Nomenclature Rules)
| Elemen | Konvensi | Contoh Baku | Contoh Keliru |
| :--- | :--- | :--- | :--- |
| **Nama Class** | PascalCase (Huruf depan tiap kata kapital) | `StrukturDasar`, `DataMahasiswa` | `strukturdasar`, `data_mahasiswa` |
| **Nama Method / Variabel** | camelCase (Kata pertama huruf kecil, selanjutnya kapital) | `hitungTotalGaji()`, `namaLengkap` | `HitungTotalGaji()`, `namalengkap` |
| **Konstanta** | UPPER_SNAKE_CASE | `MAX_CAPACITY`, `PI` | `maxCapacity`, `pi` |

---

## 4. Contoh Kode Program Komplit & Executable

```java
public class Modul01 {
    public static void main(String[] args) {
        // Menampilkan teks informasi ke konsol standar dengan baris baru (println)
        System.out.println("==================================================");
        System.out.println("       SELAMAT DATANG DI DUNIA PEMROGRAMAN JAVA   ");
        System.out.println("==================================================");

        // Menampilkan data profil sederhana
        System.out.println("Modul      : 01 - Struktur Program Java Dasar");
        System.out.println("Status     : Fondasi Pertama Menuju Engineer Handal");
        System.out.println("Arsitektur : Clean Code & Standard Industri");

        // Contoh mencetak tanpa baris baru otomatis menggunakan print
        System.out.print("Pesan Singkat: ");
        System.out.print("Konsistensi belajar adalah kunci utama!\n");
        System.out.println("==================================================");
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 1 (`public class Modul01`):** Mendefinisikan class utama dengan visibilitas `public`. Nama class wajib `Modul01` karena berkas disimpan dengan nama `Modul01.java`.
- **Baris 2 (`public static void main(String[] args)`):** Titik awal yang dicari oleh runtime Java saat perintah `java Modul01` dieksekusi di terminal.
- **Baris 4-6 (`System.out.println(...)`):**
  - `System`: Kelas bawaan Java di package `java.lang` yang menyediakan akses ke utilitas sistem.
  - `out`: Objek *Standard Output Stream* (konsol terminal).
  - `println()`: Metode untuk mencetak teks dan langsung menambahkan karakter baris baru (*newline* / `\n`) di ujungnya.
- **Baris 13-14 (`System.out.print(...)`):**
  - Berbeda dengan `println()`, metode `print()` mencetak teks tanpa baris baru, sehingga teks berikutnya di baris 14 akan dicetak berdampingan di baris konsol yang sama.

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Error Nama File Tidak Cocok dengan Nama Class Publik
* **Penyebab:** Menyimpan file dengan nama `program.java` sementara di dalamnya tertulis `public class Modul01`.
* **Pesan Error:**
  ```text
  error: class Modul01 is public, should be declared in a file named Modul01.java
  ```
* **Solusi:** Samakan nama file dan nama class secara presisi, termasuk huruf besar dan kecilnya.

---

### 2. Typo atau Penulisan Huruf Kecil pada Java Keywords / Identifier
* **Penyebab:** Menulis `system.out.println` atau `string[] args` (huruf depan `s` kecil).
* **Pesan Error:**
  ```text
  error: package system does not exist
  error: cannot find symbol class string
  ```
* **Solusi:** Java bersifat *strictly case-sensitive*. Semua tipe data bawaan kelas diawali huruf kapital (`System`, `String`).

---

### 3. Lupa Menutup Instruksi dengan Titik Koma (`;`)
* **Penyebab:** Lupa menambahkan tanda titik koma di ujung baris perintah.
* **Pesan Error:**
  ```text
  error: ';' expected
  ```
* **Solusi:** Setiap satu baris pernyataan instruksi wajib diakhiri tanda titik koma.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

### Mengapa Struktur Ini Penting untuk OOP?
Struktur dasar ini adalah gerbang awal memahami paradigma Berorientasi Objek:
- **Class sebagai Cetak Biru (*Blueprint*):** Kamu telah menulis `class Modul01`. Di modul-modul OOP nanti, kelas tidak hanya dipakai untuk meletakkan `main`, melainkan untuk mencetak objek nyata seperti `Mahasiswa`, `RekeningBank`, atau `Pesanan`.
- **Method Scope:** Memahami pembungkus kurung kurawal `{ }` pada metode `main` melatih kita memahami konsep *encapsulation* dan batasan akses variabel (*variable scope*).

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Kartu Identitas Siswa
- **Instruksi:** Buat class bernama `Latihan01Dasar` yang mencetak kartu identitas konsol sederhana: Nama Lengkap, Asal Kampus/Sekolah, dan Alasan Belajar Java menggunakan kombinasi `System.out.println()`.
- **Clue:** Pastikan nama file disimpan sebagai `Latihan01Dasar.java`.
- **Target Output:**
  ```text
  ===== KARTU IDENTITAS PROGRAMMER =====
  Nama   : Budi Santoso
  Institusi: Universitas Teknologi
  Target : Menguasai Backend Java Enterprise
  ======================================
  ```

---

### Tingkat Menengah: Banner Logo ASCII Art
- **Instruksi:** Buat class `Latihan01Menengah` yang mencetak banner sambutan atau inisial namamu dalam format teks balok (ASCII Art) dengan garis pembatas rapi.
- **Clue:** Manfaatkan karakter baris baru `\n` dan tabulasi `\t` untuk merapikan spasi.
- **Target Output:**
  ```text
  ********************************
  *   JAVA DEVELOPER INDONESIA   *
  *      [ BUILD - TEST - RUN ]  *
  ********************************
  ```

---

### Tingkat Tantangan (Challenge): Ekstraksi Parameter Command-Line
- **Instruksi:** Buat class `Latihan01Tantangan` yang memanfaatkan parameter `String[] args` pada method `main`. Jika program dijalankan dengan parameter nama pengguna di terminal (contoh: `java Latihan01Tantangan Alex`), program akan menyapa nama tersebut. Jika dijalankan tanpa argumen, cetak panduan penggunaan.
- **Clue:** Periksa panjang array argumen dengan `args.length > 0` dan ambil data indeks ke-0 dengan `args[0]`.
- **Target Output (Dengan Argumen `java Latihan01Tantangan Alex`):**
  ```text
  Halo Alex, selamat datang di pelatihan Java! Program berhasil dieksekusi dengan argumen input.
  ```
