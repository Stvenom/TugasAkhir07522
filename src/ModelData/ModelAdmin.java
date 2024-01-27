package ModelData;
import ModelJSON.ModelJSONAdmin;
import NodeData.NodeAdmin;
import java.util.Scanner;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
public class ModelAdmin {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<NodeAdmin> adminList;
    private ModelJSONAdmin modelJSONAdmin;
    public ModelAdmin() {
        adminList = new ArrayList<NodeAdmin>();
        modelJSONAdmin = new ModelJSONAdmin("src/Database/Admin.json");
        loadAdmin();
    }
    public void loadAdmin() {
        adminList = modelJSONAdmin.readFromFile(new TypeToken<ArrayList<NodeAdmin>>() {
        }.getType());
    }

    public ArrayList<NodeAdmin> getAdminList() {
        return adminList;
    }

    public void commit() {
        modelJSONAdmin.writeToFile(adminList);
    }
    public boolean cekLogin(String id, String pw) {
        NodeAdmin admin = new NodeAdmin();
        if (id.equals(admin.getUsername()) && pw.equals(admin.getPassword())) {
            return true;
        } else {
            return false;
        }
    }
}
