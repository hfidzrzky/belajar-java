import java.util.Scanner;

public class Modul3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("masukkan nilai ujian: ");
        int nilai = input.nextInt();

        System.out.print("masukkan persentase kehadiran: ");
        int kehadiran = input.nextInt();

        int sisaBagi = kehadiran % 2;

        boolean lulus = (nilai >= 75) && (kehadiran >= 80);

        System.out.printf("%napakah nilai genap? %b%n", (sisaBagi == 0));
        System.out.printf("status kelulusan (Lulus = true): %b%n", lulus);

        input.close();
    }
}
