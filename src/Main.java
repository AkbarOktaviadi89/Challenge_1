import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import java.io.*;
import java.util.Scanner;

public class Main {
    static String[] pesanan = new String[5];
    static int totalQty = 0, totalHarga = 0;
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) throws IOException {

        printMenu(); // Panggil metode untuk menampilkan menu

        //menutup scanner
        input.close();
    }

    public static void printMenu() throws IOException {
        //tampilkan header
        System.out.println("=============================");
        System.out.println("Selamat datang di binar fud");
        System.out.println("=============================");
        System.out.println();

        System.out.println("Silahkan pilih makanan: ");

        // Buat array untuk menampung semua pilihan makanan
        String[] menu = {
                "1. Nasi Goreng\t | 15.000",
                "2. Mie Goreng\t | 13.000",
                "3. Nasi + Ayam\t | 18.000",
                "4. Es Teh Manis\t | 3.000",
                "5. Es Jeruk\t\t | 5.000",
                "99. Pesan dan Bayar",
                "0. Keluar Aplikasi"
        };

        // Tampilkan setiap array menu dengan for-each
        for (String tampilMenu : menu) {
            System.out.println(tampilMenu);
        }

        System.out.println();
        // Pilih makanan
        System.out.print("=> ");
        int pilihMenu = input.nextInt();

        // Tampilkan pesanan
        tampilPesanan(pilihMenu, menu);
    }

    public static void tampilPesanan(int pilih, String[] menu) throws IOException {
        int[] qtyPesanan = new int[menu.length];

        if (pilih != 99 && pilih != 0) {
            System.out.println("\n=============================");
            System.out.println("Berapa Pesanan anda");
            System.out.println("=============================\n");
        }
        //menambahkan pesanan sesuai menu yg dipilih
        if (pilih >= 1 && pilih <= menu.length) {
            System.out.println(menu[pilih - 1]);
            System.out.println("(input 0 untuk kembali)");
            while (true) {
                System.out.print("qty => ");
                int inputQty = input.nextInt();
                if (inputQty == 0) {
                    printMenu(); // Kembali ke menu utama jika inputQty = 0
                    break;
                } else {
                    qtyPesanan[pilih - 1] = inputQty;
                    String[] namaMenu = {"Nasi Goreng  ", "Mie Goreng  ", "Nasi Ayam   ", "Es teh manis", "Es jeruk    "};
                    //menambahkan pesanan ke menu
                    pesanan[pilih - 1] = namaMenu[pilih - 1] + "\t" + qtyPesanan[pilih - 1] + "\t" + qtyPesanan[pilih - 1] * hargaMenu(pilih);
                    //notif pesan berhasil
                    System.out.println("Berhasil Menambahkan " + inputQty + " " + namaMenu[pilih - 1]);
                }
            }
        } else if (pilih == 99) {
            tampilPembayaran(pesanan); // Tampilkan tampilan pembayaran
        } else if (pilih == 0) {
            System.exit(1); // Keluar dari aplikasi jika pilih = 0
        }

    }

    public static int hargaMenu(int pilih) {
        int[] harga = {15000, 13000, 18000, 3000, 5000};
        return harga[pilih - 1]; // Mengembalikan harga makanan berdasarkan pilihan
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

                totalQty += qty; // Menambahkan jumlah kuantitas pesanan
                totalHarga += harga; // Menambahkan total harga pesanan

                System.out.println(tampilPesan); // Tampilkan pesanan
            }
        }

        System.out.println("-----------------------------+");
        System.out.println("Total \t\t\t" + totalQty + "\t" + totalHarga + "\n");

        System.out.println("1. Konfirmasi dan Bayar");
        System.out.println("2. Kembali ke menu utama");
        System.out.println("0. Keluar aplikasi");

        System.out.print("=> ");
        int pilih = input.nextInt();

        if (pilih == 1) {
            konfirmasiBayar("Struk.txt"); // Konfirmasi dan buat struk pembayaran
        } else if (pilih == 2) {
            printMenu(); // Kembali ke menu utama jika pilih = 2
        } else if (pilih == 0) {
            System.exit(1); // Keluar dari aplikasi jika pilih = 0
        } else {
            System.out.println("Pilih : 1/2/0");
        }
    }

    // Method pembuatan Struk
    public static void konfirmasiBayar(String structFile) throws IOException {
        int counter = 1;
        String newFileName = structFile;

        // Membuat file baru dengan penanganan nama berurutan
        while (new File(newFileName).exists()) {
            newFileName = "Struk_" + counter + ".txt";
            counter++;
        }
        //Membuat file baru
        File file = new File(newFileName);
        //mengecek apakah file berhasil dibuat
        if (file.createNewFile()) {
            System.out.println("Struct File successfully created");
        } else {
            System.out.println("File not created");
        }

        FileWriter fw = new FileWriter(file);
        BufferedWriter StructWr = new BufferedWriter(fw);
        StructWr.write("=============================\n");
        StructWr.write("Binar fud\n");
        StructWr.write("=============================\n\n");
        StructWr.write("Terima kasih sudah memesan \ndi binar fud\n\n");
        StructWr.write("Dibawah ini adalah pesanan anda\n\n");

        // Menulis pesanan ke dalam file
        for (String tampilPesan : pesanan) {
            if (tampilPesan != null) {
                StructWr.write(tampilPesan);
                StructWr.newLine();
            }
        }
        StructWr.write("-----------------------------+\n");
        StructWr.write("Total \t\t" + totalQty + "\t" + totalHarga);
        StructWr.write("\n\nPembayaran : BinarCash\n\n");

        StructWr.write("=============================\n");
        StructWr.write("Simpan Struk ini sebagai \nbukti pembayaran\n");
        StructWr.write("=============================");

        StructWr.flush();

        //menutup fileWritter dan bufferedWritter
        StructWr.close();
        fw.close();
    }
}
