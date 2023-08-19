import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    static String[] pesanan = new String[5];
    static int totalQty = 0,totalHarga = 0;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        printMenu();

    }
    public static void printMenu() throws IOException {

        System.out.println("=============================");
        System.out.println("Selamat datang di binar fud");
        System.out.println("=============================");
        System.out.println();

        System.out.println("Silahkan pilih makanan: ");

        //buat array untuk menampung semua pilihan makanan
        String[] menu = {"1. Nasi Goreng\t | 15.000", "2. Mie Goreng\t | 13.000", "3. Nasi + Ayam\t | 18.000", "4. Es Teh Manis\t | 3.000", "5. Es Jeruk\t\t | 5.000", "99. Pesan dan Bayar", "0. Keluar Aplikasi"};

        //tampilkan setiap array menu dengan for each
        for (String tampilMenu : menu) {
            System.out.println(tampilMenu);
        }

        System.out.println();
        //pilih makanan
        System.out.print("=> ");
        int pilihMenu = input.nextInt();

        //tampilkan pesanan
        tampilPesanan(pilihMenu,menu);

        input.close();
    }
    public static void tampilPesanan(int pilih, String[] menu) throws IOException {
        int[] qtyPesanan = new int[menu.length];

        if (pilih != 99 && pilih != 0) {
            System.out.println("\n=============================");
            System.out.println("Berapa Pesanan anda");
            System.out.println("=============================\n");
        }

        if (pilih >= 1 && pilih <= menu.length) {
            System.out.println(menu[pilih - 1]);
            System.out.println("(input 0 untuk kembali)");
            while (true) {
                System.out.print("qty => ");
                int inputQty = input.nextInt();
                if (inputQty == 0) {
                    printMenu();
                    break;
                } else {
                    qtyPesanan[pilih - 1] = inputQty;
                    String[] namaMenu = {"Nasi Goreng  ", "Mie Goreng  ", "Nasi Ayam   ", "Es teh manis", "Es jeruk    "};
                    pesanan[pilih - 1] = namaMenu[pilih - 1] + "\t" + qtyPesanan[pilih - 1] + "\t" + qtyPesanan[pilih - 1] * hargaMenu(pilih);
                }
            }
        } else if (pilih == 99) {
            tampilPembayaran(pesanan);
        } else if (pilih == 0) {
            System.exit(1);
        }
    }
    public static int hargaMenu(int pilih) {
        int[] harga = {15000, 13000, 18000, 3000, 5000};
        return harga[pilih - 1];
    }

    public static void tampilPembayaran(String[] pesan) throws IOException {

        // Reset totalQty dan totalHarga
        totalQty = 0;
        totalHarga = 0;

        System.out.println("\n=============================");
        System.out.println("Konfirmasi & Pembayaran");
        System.out.println("=============================\n");
            for (String tampilPesan : pesan) {
                if (tampilPesan != null) {
                    String[] parts = tampilPesan.split("\t");
                    int qty = Integer.parseInt(parts[1]);
                    int harga = Integer.parseInt(parts[2]);

                    totalQty += qty;
                    totalHarga += harga;

                    System.out.println(tampilPesan);
                }
            }

        System.out.println("-----------------------------+");
        System.out.println("Total \t\t\t" + totalQty + "\t" + totalHarga + "\n");

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        System.out.print("=> ");
        int pilih = input.nextInt();

        if (pilih == 1){
            konfirmasiBayar("Struk.txt");
        } else if (pilih == 2) {
            printMenu();
        } else if (pilih == 0) {
            System.exit(1);
        }else{
            System.out.println("Pilih : 1/2/0");
        }
    }

    //Method pembuatan Struct
    public static void konfirmasiBayar(String structFile) throws IOException {
            totalQty = 0;
            totalHarga = 0;
            int counter = 1;
            String newFileName = structFile;

            //membuat file baru
            while (new File(newFileName).exists()) {
                newFileName = "Struk_" + counter + ".txt";
                counter++;
            }
           File file = new File(newFileName);
           if (file.createNewFile()) {
               System.out.println("Struct File succesfully created");
           } else {
               System.out.println("File not created");
           }
           FileWriter fw = new FileWriter(file);
           BufferedWriter bwr = new BufferedWriter(fw);
           bwr.write("=============================\n");
           bwr.write("Binar fud\n");
           bwr.write("=============================\n\n");
           bwr.write("Terima kasih sudah memesan \ndi binar fud\n\n");
           bwr.write("Dibawah ini adalah pesanan anda\n\n");

           for (String tampilPesan : pesanan) {
               if (tampilPesan != null) {
                   String[] parts = tampilPesan.split("\t");
                   int qty = Integer.parseInt(parts[1]);
                   int harga = Integer.parseInt(parts[2]);

                   totalQty += qty;
                   totalHarga += harga;

                   bwr.write(tampilPesan);
                   bwr.newLine();
               }
           }
           bwr.write("-----------------------------+\n");
           bwr.write("Total \t\t" + totalQty + "\t" + totalHarga);
           bwr.write("\n\nPembayaran : BinarCash\n\n");

           bwr.write("=============================\n");
           bwr.write("Simpan Struk ini sebagai \nbukti pembayaran\n");
           bwr.write("=============================");

           bwr.flush();
           bwr.close();
           fw.close();
    }
}