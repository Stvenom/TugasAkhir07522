package ModelData;

import ModelJSON.ModelJSONAllMobil;
import ModelJSON.ModelJSONMobil;
import ModelJSON.ModelJSONParkir;
import NodeData.NodeMobil;
import NodeData.NodeParkir;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class ModelParkir {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<NodeParkir> parkirList;
    private ModelJSONParkir modelJSONParkir;

    public ModelParkir() {
        parkirList = new ArrayList<NodeParkir>();
        modelJSONParkir = new ModelJSONParkir("src/Database/StrukParkir.json");
        loadParkir();
    }

    public void loadParkir() {
        parkirList = modelJSONParkir.readFromFile(new TypeToken<ArrayList<NodeParkir>>() {
        }.getType());
    }

    public ArrayList<NodeParkir> getParkirList() {
        return parkirList;
    }

    public void addParkir(NodeParkir parkir) {
        parkirList.add(parkir);
    }

    public void commit() {
        modelJSONParkir.writeToFile(parkirList);
    }

    public Date getCheckIn(String platNomor) {
        for (NodeParkir parkir : parkirList) {
            if (parkir != null && parkir.getPlatNomor().equals(platNomor)) {
                return parkir.getCheckInTime();
            } else {
                System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan.");
            }
        }
        return null;
    }

    public Date getCheckOut(String platNomor) {
        for (NodeParkir parkir : parkirList) {
            if (parkir != null && parkir.getPlatNomor().equals(platNomor)) {
                return parkir.getCheckOutTime();
            } else {
                System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan.");
            }
        }
        return null;
    }

    public int getHarga(String platNomor) {
        for (NodeParkir parkir : parkirList) {
            if (parkir != null && parkir.getPlatNomor().equals(platNomor)) {
                return parkir.getHarga();
            } else {
                System.out.println("Mobil dengan plat nomor " + platNomor + " tidak ditemukan.");
            }
        }
        return 0;
    }

}