package latihan;
import java.util.Scanner;;

public class Modul4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("masukkan kategori kendaraan: ");
        String Kendaraan = input.nextLine().toLowerCase();

        switch(Kendaraan.toLowerCase()) {
            case "motor":
                System.out.printf("%nTarif tol untuk '%s' Rp.5000", Kendaraan);
                break;
            case "mobil":
                System.out.printf("%nTarif tol untuk '%s' Rp.10.000", Kendaraan);
                break;
            case "truk":
                System.out.printf("%nTarif tol untuk '%s' Rp.20.000", Kendaraan);
                break;
            default:
                System.out.printf("%nkategori kendaraan tidak sesuai!");
        }

        input.close();
    }
}
