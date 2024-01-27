package Controller;
import ModelData.ModelMobil;
import ModelData.ModelAdmin;
import ModelData.ModelParkir;
import NodeData.NodeMobil;
import NodeData.NodeParkir;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
public class MenuController {
    ModelMobil modelMobil;
    ModelAdmin modelAdmin;
    ModelParkir modelParkir;
    Scanner scanner = new Scanner(System.in);

    public MenuController(ModelAdmin modelAdmin, ModelMobil modelMobil, ModelParkir modelParkir) {
        this.modelAdmin = modelAdmin;
        this.modelMobil = modelMobil;
        this.modelParkir = modelParkir;
    }
    public ArrayList<NodeMobil> getMobilParkir() {
        return modelMobil.getMobilList();
    }
    public ArrayList<NodeMobil> getSemuaMobil() {
        return modelMobil.getAllMobilList();
    }
    public NodeMobil checkIn(String platNomor, Date checkIn) {
        modelMobil.AddMobil(new NodeMobil(platNomor,checkIn));
        modelMobil.commit();
        return null;
    }
    public Date getCheckIn(String platNomor){
        return modelMobil.getCheckIn(platNomor);
    }
    public int getHargaTotal(Date checkIn, Date checkOut){
        return modelMobil.BiayaParkir(checkIn,checkOut);
    }
    public void checkOut(String platNomor) {
        modelMobil.deleteMobil(platNomor);
        modelMobil.commit();
        modelParkir.commit();
    }
    public void addParkir(String platNomor,Date checkIn,Date checkOut, int Harga){
        modelParkir.addParkir(new NodeParkir(platNomor, checkIn, checkOut,  Harga));
    }
    public void viewMobilParkir() {
        modelMobil.viewMobilParkir(getMobilParkir());
    }
    public void viewSemuaMobil () {
        modelMobil.viewSemuaMobil(getSemuaMobil());
    }
    public boolean cekCheckIn(String platNomor) {
        if (platNomor.isEmpty()||platNomor.equals(modelMobil.getPlatNomor(platNomor))) {
            return false;
        }
        return true;
    }
    public boolean cekCheckOut(String platNomor) {
        if (platNomor.equals(modelMobil.getPlatNomor(platNomor))) {
            return true;
        }
        return false;
    }
    public Date getCheckInParkir(String platNomor){
        return modelParkir.getCheckIn(platNomor);
    }
    public Date getCheckOutParkir(String platNomor){
        return modelParkir.getCheckOut(platNomor);
    }
    public int getHargaParkir(String platNomor){
       return modelParkir.getHarga(platNomor);
    }

            public void adminLogin () {
                Scanner input = new Scanner(System.in);
                int i = 2;
                do {
                    String username, password;
                    System.out.print("Username : ");
                    username = input.nextLine();
                    System.out.print("Password : ");
                    password = input.nextLine();

                    boolean login = modelAdmin.cekLogin(username, password);

                    if (login) {
                        System.out.println("\nLogin Berhasil !");
                        return;
                    } else {
                        System.out.println("Login Gagal");
                        i--;
                    }

                } while (i > 0);
                System.out.println("Silahkan Coba Kembali");
                System.exit(0);
            }


    }
