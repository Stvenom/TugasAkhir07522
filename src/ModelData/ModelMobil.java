package ModelData;
import ModelJSON.ModelJSONMobil;
import ModelJSON.ModelJSONAllMobil;
import NodeData.NodeMobil;
import java.util.Date;
import java.util.Scanner;
import java.util.ArrayList;

import NodeData.NodeParkir;
import com.google.gson.reflect.TypeToken;
public class ModelMobil implements CRUD<NodeMobil>{
    Scanner scanner = new Scanner(System.in);
    private ArrayList<NodeMobil> mobilList;
    private ArrayList<NodeMobil> allMobilList;
    private ModelJSONMobil modelJSONMobil;
    private ModelJSONAllMobil modelJSONAllMobil;

    public ModelMobil() {
        mobilList = new ArrayList<NodeMobil>();
        allMobilList = new ArrayList<NodeMobil>();
        modelJSONMobil = new ModelJSONMobil("src/Database/MobilParkir.json");
        modelJSONAllMobil = new ModelJSONAllMobil("src/Database/SemuaMobil.json");
        loadMobil();
    }
@Override
    public void loadMobil() {
        mobilList = modelJSONMobil.readFromFile(new TypeToken<ArrayList<NodeMobil>>() {
        }.getType());
        allMobilList = modelJSONAllMobil.readFromFile(new TypeToken<ArrayList<NodeMobil>>() {
        }.getType());
    }

    public ArrayList<NodeMobil> getMobilList() {
        return mobilList;
    }

    public ArrayList<NodeMobil> getAllMobilList() {
        return allMobilList;
    }
    public void AddMobil(NodeMobil mobil) {
        mobilList.add(mobil);
        allMobilList.add(mobil);
    }
    public void commit() {
        modelJSONMobil.writeToFile(mobilList);
        modelJSONAllMobil.writeToFile(allMobilList);
    }
    public NodeMobil getMobil(String platNomor) {
        NodeMobil Mobil = null;
        for (NodeMobil mobil : mobilList) {
            if (mobil.getPlatNomor().equals(platNomor)) {
                return mobil;
            } else {
                System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan.");
            }
        }
        return Mobil;
    }
    public String getPlatNomor(String platNomor) {
        for (NodeMobil mobil : mobilList) {
            if (mobil != null && mobil.getPlatNomor().equals(platNomor)) {
                return mobil.getPlatNomor();
            } else {
                System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan.");
            }
        }
        return null;
    }
    @Override
    public Date getCheckIn(String platNomor) {
        for (NodeMobil mobil : mobilList) {
            if (mobil != null && mobil.getPlatNomor().equals(platNomor)) {
                return mobil.getCheckInTime();
            } else {
                System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan.");
            }
        }
        return null;
    }

    public void viewMobilParkir(ArrayList<NodeMobil> mobilList) {
        if (mobilList != null && !mobilList.isEmpty()) {
            System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<< View Mobil Parkir >>>>>>>>>>>>>>>>>>>>>>>");
            for (NodeMobil mobil : mobilList) {
                System.out.println("Plat Nomor = " + mobil.getPlatNomor());
                System.out.println("Tanggal/Waktu CheckIn = " + mobil.getCheckInTime());
                System.out.println("=============================================\n");
            }
        } else {
            System.out.println("Tidak Ada Mobil Yang Terparkir");
        }
    }

    public void viewSemuaMobil(ArrayList<NodeMobil> allMobilList) {
        if (allMobilList != null && !allMobilList.isEmpty()) {
            System.out.println("\n<<<<<<<<<<<<<<<<<<<<<<< View Semua Mobil >>>>>>>>>>>>>>>>>>>>>>>");
            for (NodeMobil mobil : allMobilList) {
                System.out.println("Plat Nomor = " + mobil.getPlatNomor());
                System.out.println("Tanggal/Waktu CheckIn = " + mobil.getCheckInTime());
                System.out.println("=============================================\n");
            }
        } else {
            System.out.println("History Masih Kosong");
        }
    }
@Override
    public boolean deleteMobil(String platNomor) {
        NodeMobil mobil = getMobil(platNomor);
        if (mobil != null && mobil.getPlatNomor().equals(platNomor)) {
            mobilList.remove(mobil);
            return true;
        } else {
            return false;
        }
    }

    public int BiayaParkir(Date checkInTime, Date checkOutTime) {
            int totalBiaya;
            long durasiParkir = (checkOutTime.getTime() - checkInTime.getTime()) / (1000 * 60);
            if (durasiParkir <= 10) {
                totalBiaya = 0;
            } else {
                totalBiaya = 10000;
                int hari = (int) Math.ceil(durasiParkir / (24 * 60.0));
                totalBiaya += ((hari - 1) * 5000);

            }
            return totalBiaya;
        }
}
