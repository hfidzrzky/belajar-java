# Modul 07: Method (Fungsi), Parameter, dan Return Value

> **Deskripsi Singkat:**  
> Dekomposisi logika kode menjadi modul-modul fungsi yang dapat digunakan kembali (*reusable*). Modul ini mengupas tuntas perbedaan method void vs method ber-return value, mekanisme passing parameter *Pass-by-Value*, konsep *Method Overloading*, serta cara kerja *Call Stack* memori JVM saat sebuah fungsi dipanggil.
>
> **Tujuan Pembelajaran:**
> 1. Mampu merancang fungsi kustom yang modular untuk mengeliminasi duplikasi kode (*Don't Repeat Yourself*).
> 2. Memahami perbedaan mendasar method tanpa nilai kembalian (`void`) dan method yang mengembalikan data (`return value`).
> 3. Menguasai mekanisme pengiriman parameter dan sifat *Pass-by-Value* di Java.
> 4. Mengimplementasikan *Method Overloading* (nama method sama dengan parameter berbeda).

---

## 2. Analogi Dunia Nyata & Konsep Inti

### Analogi Kehidupan Sehari-Hari
1. **Method Ber-Return Value = Mesin Pembuat Kopi (Vending Machine):**  
   Kamu memasukkan koin uang dan menekan tombol (memberikan **Argumen/Parameter**). Mesin bekerja di dalam, lalu mengeluarkan segelas kopi hangat ke tanganmu (**Nilai Kembalian / Return Value**). Kamu bisa meminum kopi itu atau menyimpannya di meja (disimpan ke variabel).
2. **Method `void` = Satpam yang Meniup Peluit:**  
   Satpam melihat ada mobil parkir sembarangan, lalu ia meniup peluit kencang: *"Priiiitt!"*. Satpam menjalankan sebuah **aksi/perintah**, tetapi ia tidak memberikan benda fisik apa pun kembali ke sakumu (**tidak mengembalikan data / `void`**).
3. **Method Overloading = Tombol "Pencarian" di Aplikasi Ojek Online:**  
   Kamu bisa mencari tumpangan dengan:
   - Mencari berdasarkan Nama Tempat: `cari("Bandara Soekarno Hatta")`
   - Mencari berdasarkan Koordinat GPS: `cari(-6.1275, 106.6537)`  
   Namanya tetap sama-sama `cari()`, tetapi sistem otomatis tahu aksi mana yang dijalankan berdasarkan **jenis data yang kamu kirimkan**.

```
+-------------------------------------------------------------+
|                 ANATOMI METHOD DI DALAM KELAS               |
|                                                             |
| modifier  returnType  namaMethod ( parameterList ) {        |
|    // Tubuh method / Logika komputasi                       |
|    return nilaiHasil; // Wajib jika returnType bukan void   |
| }                                                           |
+-------------------------------------------------------------+
```

### Mengapa Konsep Ini Diciptakan?
Tanpa method, seluruh logika aplikasi perbankan (transfer, cek saldo, blokir kartu) akan menumpuk di dalam method `main` ribuan baris panjangnya (*Spaghetti Code*). Method diciptakan untuk menerapkan prinsip arsitektur **Separation of Concerns**: memecah masalah raksasa menjadi potongan-potongan tugas kecil yang teruji, mudah dibaca, dan dapat dipakai berulang-ulang dari mana saja.

---

## 3. Penjelasan Teori & Aturan Main

### A. Anatomi Deklarasi Method
```java
public static double hitungDiskon(double totalBelanja, double persentase) {
    double potongan = totalBelanja * (persentase / 100.0);
    return potongan; // Mengembalikan hasil hitungan ke pemanggil method
}
```
- `public static`: Visibilitas dan penanda dapat dipanggil langsung dari konteks kelas.
- `double`: Tipe data nilai kembalian (*return type*). Jika tidak mengembalikan data, gunakan `void`.
- `hitungDiskon`: Nama method (wajib format *camelCase*).
- `(double totalBelanja, double persentase)`: Parameter penerima data.

### B. Mekanisme "Pass-by-Value" di Java
Java selalu menerapkan **Pass-by-Value**:
- Ketika variabel primitif dikirim ke method, yang dikirim adalah **salinan nilainya** (*copy*).
- Mengubah nilai parameter primitif di dalam method **tidak akan pernah** mengubah nilai variabel aslinya di method pemanggil (`main`).

### C. Aturan Method Overloading
Dua atau lebih method di dalam kelas yang sama boleh memiliki **nama yang identik**, asalkan:
1. Jumlah parameternya berbeda, ATAU
2. Tipe data parameternya berbeda, ATAU
3. Urutan tipe data parameternya berbeda.  
*Catatan:* Mengubah tipe data return value saja tanpa mengubah parameter TIDAK dianggap overloading yang valid oleh compiler Java.

---

## 4. Contoh Kode Program Komplit & Executable

```java
import java.util.Scanner;

public class Modul07 {

    // 1. Method void: Menjalankan tugas cetak banner tanpa mengembalikan data
    public static void tampilkanHeader(String judul) {
        System.out.println("==================================================");
        System.out.printf("  %S%n", judul);
        System.out.println("==================================================");
    }

    // 2. Method dengan Return Value: Menghitung diskon belanja
    public static double hitungDiskon(double totalBelanja, double persentase) {
        if (totalBelanja < 50000.0) {
            return 0.0; // Early return: belanja di bawah 50rb tidak dapat diskon
        }
        return totalBelanja * (persentase / 100.0);
    }

    // 3. Method Overloading: Menghitung ongkos kirim standar (berdasarkan berat)
    public static int hitungOngkir(double beratKg) {
        int tarifPerKg = 10000;
        return (int) (Math.ceil(beratKg) * tarifPerKg);
    }

    // Method Overloading kedua: Menghitung ongkos kirim dengan opsi pengiriman kilat
    public static int hitungOngkir(double beratKg, boolean isExpress) {
        int ongkirDasar = hitungOngkir(beratKg); // Memanggil method overloading pertama
        if (isExpress) {
            return ongkirDasar + 15000; // Tambahan biaya kurir instan
        }
        return ongkirDasar;
    }

    // Main Method sebagai orkestrator pemanggil
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        tampilkanHeader("Sistem Logistik & Kasir Toko Online");

        System.out.print("Masukkan Total Belanja Pelanggan (Rp): ");
        double belanja = input.nextDouble();

        System.out.print("Masukkan Berat Paket (Kg): ");
        double berat = input.nextDouble();

        System.out.print("Gunakan Layanan Kirim Express? (true/false): ");
        boolean isExpress = input.nextBoolean();

        // Memanggil method-method yang telah didefinisikan
        double potonganDiskon = hitungDiskon(belanja, 10.0); // Diskon tetap 10%
        int biayaOngkir = hitungOngkir(berat, isExpress);
        double totalAkhir = (belanja - potonganDiskon) + biayaOngkir;

        System.out.println("\n--------------------------------------------------");
        System.out.println("                RINCIAN PEMBAYARAN                ");
        System.out.println("--------------------------------------------------");
        System.out.printf("Subtotal Belanja    : Rp %,.2f%n", belanja);
        System.out.printf("Potongan Diskon     : Rp %,.2f%n", potonganDiskon);
        System.out.printf("Ongkos Pengiriman   : Rp %,d%n", biayaOngkir);
        System.out.println("--------------------------------------------------");
        System.out.printf("TOTAL YANG DIBAYAR  : Rp %,.2f%n", totalAkhir);
        System.out.println("==================================================");

        input.close();
    }
}
```

---

## 5. Bedah Kode (Line-by-Line Explanation)

- **Baris 6 (`public static void tampilkanHeader(String judul)`):** Diberi penanda `void` karena fungsinya murni untuk presentasi grafis konsol dan tidak mengembalikan data apa pun ke pemanggil.
- **Baris 13 (`public static double hitungDiskon(...)`):** Bertipe `double`. Wajib memiliki kata kunci `return` yang mengembalikan angka desimal. Baris `if (totalBelanja < 50000.0) return 0.0;` menerapkan pola *Guard Clause* untuk pengembalian nilai lebih awal (*Early Exit*).
- **Baris 21 & 27 (`Method Overloading hitungOngkir`):** Dua method bernama persis sama `hitungOngkir`. Yang pertama hanya menerima satu parameter `(double beratKg)`, yang kedua menerima dua parameter `(double beratKg, boolean isExpress)`. JVM secara otomatis membedakan method mana yang dipanggil berdasarkan argumen yang dikirim di baris 47.
- **Baris 28 (`int ongkirDasar = hitungOngkir(beratKg);`):** Praktik *clean code* terbaik: method kedua menggunakan kembali logika method pertama untuk menghindari duplikasi kode tarif dasar.

---

## 6. Jebakan Error & Peringatan Pemula (Common Pitfalls)

### 1. Error Method Non-Void Tidak Memiliki Return Statement
* **Masalah:** Menaruh `return` hanya di dalam blok `if`, sementara jalur `else` tidak memiliki return.
* **Pesan Error:**
  ```text
  error: missing return statement
  ```
* **Penyebab:** Compiler mendeteksi ada skenario di mana `if` bernilai false sehingga method berakhir tanpa mengembalikan nilai `double`.
* **Solusi:** Pastikan ada pernyataan `return` default di luar blok percabangan.

---

### 2. Memanggil Method Non-Static dari Konteks Static
* **Masalah:** Lupa menambahkan kata kunci `static` pada deklarasi method pembantu, lalu memanggilnya langsung dari `main`.
* **Pesan Error:**
  ```text
  error: non-static method hitungDiskon(double,double) cannot be referenced from a static context
  ```
* **Solusi:** Karena `main` bertipe `static`, semua fungsi pembantu dalam satu file prosedural harus diberi kata kunci `static` (sebelum kita masuk ke konsep instansiasi objek OOP).

---

### 3. Salah Sangka Terhadap Mutasi Nilai Primitif (Pass-by-Value Bug)
* **Masalah:** Membuat method `tambahUmur(int umur)` lalu di dalamnya menulis `umur = umur + 1;`. Saat kembali ke `main`, variabel umur asli tidak bertambah sama sekali.
* **Solusi:** Ingat bahwa tipe primitif hanya menduplikasi nilai. Jika ingin nilai baru digunakan, kembalikan nilai tersebut lewat `return` dan tampung hasilnya: `umur = tambahUmur(umur);`.

---

## 7. Koneksi ke Materi Lain / Jembatan Menuju OOP

### Jembatan Langsung Menuju "Behavior" Objek!
Method di modul ini adalah bentuk dasar dari **Behavior (Perilaku)** sebuah objek di OOP (Modul 8). Ketika kita menghapus kata kunci `static`, method tersebut resmi berubah menjadi **Instance Method**, yaitu aksi yang melekat langsung pada objek nyata:
```java
Mobil avanza = new Mobil();
avanza.gas(); // Inilah instance method di OOP!
```

---

## 8. Soal Latihan Praktek Mandiri

### Tingkat Dasar: Validator Bilangan Prima
- **Instruksi:** Buat class `Latihan07Dasar` dengan method publik static bertipe boolean: `public static boolean isPrima(int angka)`. Method mengembalikan `true` jika angka adalah bilangan prima ($> 1$ dan hanya habis dibagi 1 serta dirinya sendiri), dan `false` jika bukan.
- **Clue:** Uji perulangan dari 2 hingga $\sqrt{angka}$ (`i <= Math.sqrt(angka)`). Jika ada angka yang habis membagi (`angka % i == 0`), langsung `return false`.
- **Target Output:**
  ```text
  isPrima(17) -> true
  isPrima(15) -> false
  ```

---

### Tingkat Menengah: Konverter Mata Uang dengan Overloading
- **Instruksi:** Buat class `Latihan07Menengah` dengan dua method overloading bernama `konversiKeRupiah`:
  1. `konversiKeRupiah(double jumlahUsd)` $\rightarrow$ Kurs tetap $1 USD = Rp 16.000$.
  2. `konversiKeRupiah(double jumlahValas, double kursKustom)` $\rightarrow$ Menghitung berdasarkan kurs mata uang yang dimasukkan secara manual.
- **Target Output:**
  ```text
  $100 USD (Kurs Standar): Rp 1,600,000.00
  100 SGD (Kurs Rp 12.200): Rp 1,220,000.00
  ```

---

### Tingkat Tantangan (Challenge): Analisis Data Array Menggunakan Method Modular
- **Instruksi:** Buat program `Latihan07Tantangan` yang memisahkan pengolahan array nilai siswa menjadi 3 method terpisah:
  1. `hitungRataRata(int[] data)` $\rightarrow$ Mengembalikan nilai `double`.
  2. `cariMaksimum(int[] data)` $\rightarrow$ Mengembalikan nilai `int`.
  3. `hitungSiswaLulus(int[] data, int kkm)` $\rightarrow$ Mengembalikan jumlah siswa dengan nilai $\ge kkm$.
  Metode `main` hanya bertugas menerima input dan menampilkan rangkuman akhir secara elegan.
- **Target Output:**
  ```text
  Input Array: [75, 80, 92, 60, 88]
  Rata-rata Kelas : 79.00
  Nilai Tertinggi : 92
  Siswa Lulus KKM : 4 orang
  ```
