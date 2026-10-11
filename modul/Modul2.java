import java.util.Scanner;

public class Modul2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("masukkan nama universitas: ");
        String universitas = input.nextLine();

        System.out.print("masukkan tahun masuk: ");
        int tahunMasuk = input.nextInt();

        System.out.printf("%nKamu adalah mahasiswa %s tahun %d", universitas, tahunMasuk);

        input.close();
    }
}