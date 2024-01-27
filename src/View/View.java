package View;
import Controller.MenuController;
import java.util.InputMismatchException;
import java.util.Date;
import java.util.Scanner;
public class View {
    MenuController menucontroller;
    public View(MenuController menucontroller) {
        this.menucontroller = menucontroller;
    }
    public void ParkirOnline() {
        Scanner scanner = new Scanner(System.in);
        int pilihan;
        String platNomor;
        menucontroller.adminLogin();
        do {
            try {
                System.out.println("✯✯✯✯✯✯✯✯✯✯✯✯✯✯ PARKIR ONLINE ✯✯✯✯✯✯✯✯✯✯✯✯✯✯\n");
                System.out.println("1. Check In Mobil");
                System.out.println("2. Check Out Mobil");
                System.out.println("3. View Daftar Mobil Parkir");
                System.out.println("4. View Daftar History Semua Mobil");
                System.out.println("5. Cetak Struk");
                System.out.println("6. Exit\n");
                System.out.printf("Pilih : ");
                pilihan = scanner.nextInt();

                switch (pilihan) {
                    case 1:
                        System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<< Check In >>>>>>>>>>>>>>>>>>>>>>>");
                        scanner.nextLine();
                        System.out.print("Masukkan Plat Nomor Mobil : ");
                        platNomor = scanner.nextLine();
                        boolean cek = menucontroller.cekCheckIn(platNomor);

                        if (cek) {
                            menucontroller.checkIn(platNomor, new Date());
                            Date checkIn = menucontroller.getCheckIn(platNomor);
                            System.out.println("Anda melakukan CheckIn pada " + checkIn);
                            System.out.println("CheckIn Berhasil !");
                        } else {
                            System.out.println("CheckIn Gagal");
                        }
                        System.out.println("======================================================\n");
                        break;
                    case 2:
                        scanner.nextLine();
                        System.out.print("Masukkan Plat Nomor Mobil untuk CheckOut : ");
                        platNomor = scanner.nextLine();
                        cek = menucontroller.cekCheckOut(platNomor);

                        if (cek) {
                            Date checkInTime = menucontroller.getCheckIn(platNomor);
                            Date checkOutTime = new Date();
                            int Harga = menucontroller.getHargaTotal(checkInTime, checkOutTime);
                            menucontroller.addParkir(platNomor, checkInTime, checkOutTime, Harga);
                            menucontroller.checkOut(platNomor);
                            System.out.println("Biaya parkir untuk Mobil dengan plat nomor " + platNomor + " adalah: " + Harga);
                            System.out.println("CheckOut Berhasil !");
                        } else {
                            System.out.println("CheckOut Gagal");
                        }
                        break;
                    case 3:
                        menucontroller.viewMobilParkir();
                        break;
                    case 4:
                        menucontroller.viewSemuaMobil();
                        break;
                    case 5:
                        scanner.nextLine();
                        System.out.print("Masukkan Plat Nomor Mobil untuk CheckOut : ");
                        platNomor = scanner.nextLine();
                        cek = menucontroller.cekCheckIn(platNomor);
                        if (cek) {
                            Date checkInTime = menucontroller.getCheckInParkir(platNomor);
                            Date checkOutTime = menucontroller.getCheckOutParkir(platNomor);
                            int Harga = menucontroller.getHargaParkir(platNomor);
                            System.out.println("\n==================== STRUK PARKIR ====================");
                            System.out.println("Plat Nomor: " + platNomor + "\n");
                            System.out.println("Tanggal & Jam Masuk: " + checkInTime + "\n");
                            System.out.println("Tanggal & Jam Keluar: " + checkOutTime + "\n");
                            System.out.println("Biaya Parkir: " + Harga);
                            System.out.println("======================================================\n");
                        } else {
                            System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan atau belum CheckOut.");
                        }
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("input yang anda masukkan salah !!!");
                }
            }catch (InputMismatchException e) {
                System.out.println("input yang anda masukkan salah !!!");
                scanner.nextLine();
                pilihan = -1;
            }
        } while (pilihan != 0);
        scanner.close();
    }
}
