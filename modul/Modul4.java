import java.util.Scanner;

public class Modul4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("masukkan nilai: ");
        double nilai = input.nextDouble();

        if(nilai >= 85) {
            System.out.printf("%n '%.1f'Grade A", nilai);
        } else if(nilai >= 75) {
            System.out.printf("%n '%.1f'Grade B", nilai);
        } else if(nilai >= 55) {
            System.out.printf("%n '%.1f'Grade C", nilai);
        } else {
            System.out.printf("%n '%.1f'Grade D", nilai);
        }

        input.close();
    }
}
