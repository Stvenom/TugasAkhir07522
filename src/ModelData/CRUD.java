package ModelData;
import java.util.Date;

public interface CRUD <T>{
    public Date getCheckIn(String platNomor);
    public void loadMobil();
    public boolean deleteMobil(String platNomor);
}
