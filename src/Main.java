import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
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
        //inisialisasi
        int qtyNasiGoreng = 0,qtyMieGoreng = 0,qtyNasiAyam = 0,qtyEsTehManis = 0 ,qtyEsJeruk = 0;
        int kembali = 1;

        if (pilih != 99 && pilih != 0) {
            System.out.println("\n=============================");
            System.out.println("Berapa Pesanan anda");
            System.out.println("=============================\n");
        }

        switch (pilih) {
            case 1 -> {
                System.out.println(menu[0]);
                System.out.println("(input 0 untuk kembali)");
                while(true) {
                    System.out.print("qty => ");
                    int inputQty = input.nextInt();
                    if (inputQty == 0) {
                        printMenu();
                        break;
                    } else {
                        qtyNasiGoreng = inputQty;
                        pesanan[0] = "Nasi Goreng \t" + qtyNasiGoreng + "\t" + qtyNasiGoreng * 15000;
                    }
                }
            }
            case 2 -> {
                System.out.println(menu[1]);
                System.out.println("(input 0 untuk kembali)");
                while(true) {
                    System.out.print("qty => ");
                    int inputQty = input.nextInt();
                    if (inputQty == 0) {
                        printMenu();
                        break;
                    } else {
                        qtyMieGoreng = inputQty;
                        pesanan[1] = "Mie Goreng  \t" + qtyMieGoreng + "\t" + qtyMieGoreng * 13000;
                    }
                }
            }
            case 3 -> {
                System.out.println(menu[2]);
                System.out.println("(input 0 untuk kembali)");
                while(true) {
                    System.out.print("qty => ");
                    int inputQty = input.nextInt();
                    if (inputQty == 0) {
                        printMenu();
                        break;
                    } else {
                        qtyNasiAyam = inputQty;
                        pesanan[2] = "Nasi Ayam   \t"  + qtyNasiAyam + "\t" + qtyNasiAyam * 18000;
                    }
                }
            }
            case 4 -> {
                System.out.println(menu[3]);
                System.out.println("(input 0 untuk kembali)");
                while(true) {
                    System.out.print("qty => ");
                    int inputQty = input.nextInt();
                    if (inputQty == 0) {
                        printMenu();
                        break;
                    } else {
                        qtyEsTehManis = inputQty;
                        pesanan[3] = "Es Teh Manis\t" + qtyEsTehManis + "\t" + qtyEsTehManis * 3000 ;
                    }
                }
            }
            case 5 -> {
                System.out.println(menu[4]);
                System.out.println("(input 0 untuk kembali)");
                while(true) {
                    System.out.print("qty => ");
                    int inputQty = input.nextInt();
                    if (inputQty == 0) {
                        printMenu();
                        break;
                    } else {
                        qtyEsJeruk = inputQty;
                        pesanan[4] = "Es Jeruk    \t" + qtyEsJeruk + "\t" + qtyEsJeruk * 5000;
                    }
                }
            }
            case 99 -> {
                tampilPembayaran(pesanan);
            }
            case 0 -> System.exit(1);
        }

    }

    public static void tampilPembayaran(String[] pesan) throws IOException {


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
        System.out.println("Total \t\t\t" + totalQty + "\t" + totalHarga + "\n\n");

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

    public static void konfirmasiBayar(String structFile) throws IOException {
           File file = new File(structFile);
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
           bwr.write("Terima kasih telah memesan di binar fud\n\n");
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
           bwr.write("Total \t\t\t" + totalQty + "\t" + totalHarga);
           bwr.write("\n\nPembayaran : BinarCash\n\n");

           bwr.write("=============================\n");
           bwr.write("Simpan Struk ini sebagai \nbukti pembayaran\n");
           bwr.write("=============================");

           bwr.close();
           fw.close();
    }
}