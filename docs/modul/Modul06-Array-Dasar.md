# Modul 06: Array Satu Dimensi dan Manipulasi Data

> **Deskripsi Singkat:**  
> Pengenalan struktur data linear pertama di Java. Modul ini membahas mekanisme penyimpanan sekumpulan data bertipe homogen secara berurutan di dalam memori, akses elemen melalui indeks 0-based, properti `.length`, iterasi menggunakan loop tradisional vs *Enhanced For-Loop* (*for-each*), serta algoritma pencarian (*Linear Search*) dan agregasi nilai (Maksimum, Minimum, Rata-rata).
>
> **Tujuan Pembelajaran:**
> 1. Memahami representasi array di memori JVM (alokasi *contiguous memory* di Heap).
> 2. Mampu mendeklarasikan, mengalokasikan ukuran, dan menginisialisasi elemen array satu dimensi.
> 3. Menguasai teknik iterasi elemen array menggunakan loop tradisional (`for`) dan *enhanced for-loop* (`for-each`).
> 4. Mengimplementasikan algoritma dasar manipulasi data: pencarian nilai (*search*), pencarian nilai ekstrem (min/max), dan kalkulasi rata-rata.

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Array = Deretan Loker Bernomor di Stasiun Kereta:**  
   Bayangkan sebuah lemari loker panjang di stasiun:
   - Semua pintu loker berukuran dan bertipe sama (hanya muat koper ukuran tertentu $\rightarrow$ *homogen*).
   - Setiap pintu loker memiliki nomor indeks penanda posisi yang dimulai dari **0** (Loker 0, Loker 1, Loker 2, dst.).
   - Jumlah loker sudah dibangun permanen sejak stasiun dibuka (panjang/ukuran array **bersifat tetap (*fixed-size*)**, tidak bisa tiba-tiba bertambah di tengah jalan tanpa membuat lemari baru).
2. **Indeks 0-Based = Jarak Langkah dari Titik Awal (Offset):**  
   Mengapa elemen pertama berada di indeks 0? Di memori komputer, indeks adalah penunjuk jarak pergeseran (*offset*) dari alamat memori dasar. Elemen pertama berada tepat di titik awal (jarak pergeseran 0 byte).

```
Indeks (Index):       [ 0 ]     [ 1 ]     [ 2 ]     [ 3 ]     [ 4 ]
Nilai (Value):       |  85  |  |  92  |  |  78  |  |  90  |  |  88  |
Alamat Heap:        0x100     0x104     0x108     0x10C     0x110
(Ukuran fixed = 5 elemen integer bertipe homogen)
```

### Mengapa Konsep Ini Diciptakan?
Jika seorang guru ingin mencatat nilai 50 siswa di kelas, tanpa array ia harus membuat 50 nama variabel terpisah: `nilai1`, `nilai2`, ..., `nilai50`. Kode akan sangat berantakan dan mustahil diolah menggunakan perulangan secara dinamis. Array memungkinkan kita mengelompokkan ratusan hingga jutaan data di bawah **satu nama variabel tunggal**.

---

## 3. Penjelasan Teori & Aturan Main

### A. Cara Deklarasi dan Alokasi Array
1. **Cara 1: Tentukan Ukuran Dulu (Nilai default nol/null)**
   ```java
   int[] daftarNilai = new int[5]; // Membuat array berkapasitas 5 elemen
   daftarNilai[0] = 85;            // Mengisi elemen indeks ke-0
   ```
2. **Cara 2: Inisialisasi Langsung dengan Nilai (*Array Literal*)**
   ```java
   String[] daftarBulan = {"Januari", "Februari", "Maret", "April"};
   ```

### B. Properti Panjang Array (`.length`)
Untuk mengetahui berapa banyak elemen yang ditampung oleh array, gunakan properti `.length` (tanpa tanda kurung):
```java
int totalElemen = daftarNilai.length;
```

### C. Dua Gaya Iterasi Array
1. **Traditional For-Loop (Dibutuhkan jika butuh nomor indeks):**
   ```java
   for (int i = 0; i < daftarNilai.length; i++) {
       System.out.println("Siswa ke-" + i + ": " + daftarNilai[i]);
   }
   ```
2. **Enhanced For-Loop / For-Each (Rapi & aman jika hanya membaca data):**
   ```java
   for (int nilai : daftarNilai) {
       System.out.println("Nilai: " + nilai);
   }
   ```

---

## 4. Contoh Kode Program Komplit & Executable

```java
import java.util.Scanner;

public class Modul06 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== SISTEM ANALISIS NILAI UJIAN MAHASISWA ===");
        System.out.print("Masukkan jumlah mahasiswa yang akan diinput: ");
        int jumlahData = input.nextInt();

        // 1. Alokasi array dengan ukuran dinamis sesuai input user
        double[] nilaiMahasiswa = new double[jumlahData];

        // 2. Mengisi elemen array menggunakan traditional for-loop
        System.out.println("\n--- PENGISIAN DATA NILAI ---");
        for (int i = 0; i < nilaiMahasiswa.length; i++) {
            System.out.printf("Masukkan nilai mahasiswa ke-%d: ", (i + 1));
            nilaiMahasiswa[i] = input.nextDouble();
        }

        // 3. Algoritma Agregasi: Total, Rata-rata, Nilai Tertinggi (Max), Terendah (Min)
        double totalNilai = 0.0;
        double nilaiTertinggi = nilaiMahasiswa[0];
        double nilaiTerendah = nilaiMahasiswa[0];

        // Membaca array menggunakan Enhanced For-Loop (for-each)
        for (double nilai : nilaiMahasiswa) {
            totalNilai += nilai;

            if (nilai > nilaiTertinggi) {
                nilaiTertinggi = nilai;
            }
            if (nilai < nilaiTerendah) {
                nilaiTerendah = nilai;
            }
        }

        double rataRata = totalNilai / nilaiMahasiswa.length;

        // 4. Algoritma Linear Search: Mencari apakah ada mahasiswa bernilai sempurna (100)
        boolean adaNilaiSempurna = false;
        int indeksDitemukan = -1;

        for (int i = 0; i < nilaiMahasiswa.length; i++) {
            if (nilaiMahasiswa[i] == 100.0) {
                adaNilaiSempurna = true;
                indeksDitemukan = i;
                break; // Keluar dari loop pencarian segera setelah ditemukan
            }
        }

        // Menampilkan Rekapitulasi Statistik
        System.out.println("\n===========================================");
        System.out.println("          LAPORAN STATISTIK KELAS          ");
        System.out.println("===========================================");
        System.out.printf("Total Mahasiswa Terdaftar : %d orang%n", nilaiMahasiswa.length);
        System.out.printf("Nilai Rata-rata Kelas     : %.2f%n", rataRata);
        System.out.printf("Nilai Tertinggi (Maksimum): %.2f%n", nilaiTertinggi);
        System.out.printf("Nilai Terendah (Minimum)  : %.2f%n", nilaiTerendah);
        System.out.printf("Ada Nilai 100 Sempurna?   : %b", adaNilaiSempurna);

        if (adaNilaiSempurna) {
            System.out.printf(" (Ditemukan pertama pada mahasiswa ke-%d)%n", (indeksDitemukan + 1));
        } else {
            System.out.println();
        }
        System.out.println("===========================================");

        input.close();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 11 (`double[] nilaiMahasiswa = new double[jumlahData];`):** Kata kunci `new` memesan blok memori kontigu di JVM Heap Memory berukuran tepat sebesar `jumlahData * 8` byte. Variabel referensi `nilaiMahasiswa` di simpan pada Stack.
- **Baris 15-18 (`for (int i = 0; i < nilaiMahasiswa.length; i++)`):** Menggunakan loop tradisional karena kita memerlukan variabel indeks `i` untuk mencetak label `"mahasiswa ke-(i + 1)"` dan menetapkan elemen array `nilaiMahasiswa[i]`.
- **Baris 22-23 (`double nilaiTertinggi = nilaiMahasiswa[0];`):** Inisialisasi awal nilai ekstrim (min/max) selalu menggunakan elemen pertama array itu sendiri, **bukan angka 0**, agar valid jika suatu saat array berisi angka negatif.
- **Baris 26 (`for (double nilai : nilaiMahasiswa)`):** *Enhanced For-Loop* membuat kode jauh lebih bersih dan bebas dari resiko salah ketik indeks saat kita hanya perlu membaca setiap nilai.
- **Baris 42 (`if (nilaiMahasiswa[i] == 100.0) { ... break; }`):** Algoritma *Linear Search*. Menggunakan `break` segera setelah target ditemukan menghemat waktu eksekusi CPU (*Early Exit Optimization*).

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Error Menabrak Batas Indeks (*ArrayIndexOutOfBoundsException*)
* **Masalah:** Mengakses indeks yang sama dengan atau melebihi ukuran array.
  ```java
  int[] data = new int[5]; // Indeks valid hanya: 0, 1, 2, 3, 4
  System.out.println(data[5]); // ERROR!
  ```
* **Pesan Error:**
  ```text
  Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
  ```
* **Solusi:** Selalu gunakan kondisi pembatas `< array.length` (kurang dari), bukan `<= array.length`.

---

### 2. Mencetak Array Langsung ke Konsol Menghasilkan Hash Memori
* **Masalah:** Menulis `System.out.println(nilaiMahasiswa);`.
* **Output yang Muncul:** `[D@2f92e0f4`
* **Penyebab:** Variabel array adalah *Reference Type*. Mencetaknya langsung memanggil `toString()` bawaan objek yang menampilkan tipe `[D` (Array of Double) beserta kode hash memorinya.
* **Solusi:** Lakukan perulangan elemen, atau gunakan utilitas bawaan: `java.util.Arrays.toString(nilaiMahasiswa)`.

---

### 3. Mengubah Nilai Elemen Menggunakan Enhanced For-Loop
* **Masalah:**
  ```java
  for (double nilai : nilaiMahasiswa) {
      nilai = 100.0; // Nilai asli di array TIDAK BERUBAH!
  }
  ```
* **Penjelasan:** Variabel `nilai` pada *for-each loop* hanyalah salinan lokal (*copy by value*).
* **Solusi:** Jika ingin memodifikasi isi elemen array, wajib menggunakan loop tradisional dengan indeks: `nilaiMahasiswa[i] = 100.0;`.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

- **Fondasi "Array of Objects":** Pada Modul 8 & 9 (OOP), array tidak hanya menampung angka primitif `double` atau `int`, melainkan kumpulan objek buatanmu sendiri: `Mahasiswa[] kelasTI = new Mahasiswa[40];`.
- **Cikal Bakal Java Collections (`ArrayList`):** Keterbatasan ukuran tetap (*fixed size*) pada array murni ini akan melahirkan pemahaman mengapa industri modern sering memanfaatkan struktur data dinamis seperti `ArrayList<T>` dan `HashMap<K, V>`.

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Balik Urutan Array (Reverse Print)
- **Instruksi:** Buat program `Latihan06Dasar` yang memiliki array integer dengan nilai `{10, 20, 30, 40, 50}`. Cetak elemen-elemen tersebut secara terbalik dari indeks terakhir ke indeks pertama.
- **Clue:** Awali `for` loop dari `int i = data.length - 1` hingga `i >= 0` dengan `i--`.
- **Target Output:**
  ```text
  Hasil cetak terbalik: 50 40 30 20 10
  ```

---

### Tingkat Menengah: Penghitung Frekuensi & Filter Nilai Lulus
- **Instruksi:** Buat program `Latihan06Menengah` yang menerima input 8 nilai mahasiswa (`int`). Hitung berapa jumlah mahasiswa yang dinyatakan lulus (nilai $\ge 75$) dan berapa mahasiswa yang tidak lulus (nilai $< 75$). Tampilkan juga persentase kelulusan kelas.
- **Target Output:**
  ```text
  Jumlah Mahasiswa Lulus    : 6 orang
  Jumlah Mahasiswa Tidak Lulus : 2 orang
  Persentase Kelulusan Kelas: 75.0%
  ```

---

### Tingkat Tantangan (Challenge): Detektor Duplikasi Data & Elemen Unik
- **Instruksi:** Buat program `Latihan06Tantangan` yang memeriksa apakah di dalam sebuah array terdapat angka yang berulang (duplikat). Jika ada, sebutkan angka berapa saja yang muncul lebih dari satu kali.
- **Clue:** Gunakan *nested loop* (loop di dalam loop) di mana loop luar memegang satu angka, dan loop dalam membandingkannya dengan elemen-elemen di posisi setelahnya.
- **Target Output:**
  ```text
  Data Array: [12, 45, 12, 89, 70, 45, 99]
  Peringatan: Ditemukan angka duplikat: 12, 45
  ```
