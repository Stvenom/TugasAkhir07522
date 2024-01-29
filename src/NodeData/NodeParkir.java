package NodeData;
import java.util.Date;


public class NodeParkir {
    private String platNomor;
    private Date checkInTime;
    private Date checkOutTime;
    private int Harga;

    public NodeParkir (String platNomor, Date checkInTime, Date checkOutTime, int Harga) {
        this.platNomor = platNomor;
        this.checkInTime = checkInTime;
        this.checkOutTime = checkOutTime;
        this.Harga = Harga;
    }
    public String getPlatNomor() {
        return platNomor;
    }
    public void setPlatNomor(String platNomor) {
        this.platNomor = platNomor;
    }
    public Date getCheckInTime() {
        return checkInTime;
    }
    public void setCheckInTime(Date checkInTime) {
        this.checkInTime = checkInTime;
    }

    public Date getCheckOutTime() {
        return checkOutTime;
    }
    public void setCheckOutTime(Date checkOutTime) {
        this.checkOutTime = checkOutTime;
    }
    public int getHarga() {
        return Harga;
    }
    public void setHarga(int harga) {
        Harga = harga;
    }
}
