package latihan;
import java.util.Scanner;

public class Modul3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("masukkan angka: ");
        int angka = input.nextInt();

        boolean isPositif = angka > 0;
        boolean isPositifAndGenap = (angka % 2 == 0) && (angka > 0);

        System.out.printf("apakah angka '%d' adalah positif? %b%n", angka, isPositif);
        System.out.printf("apakah angka '%d' adalah genap dan positif? %b%n", angka, isPositifAndGenap);

        input.close();
    }
}