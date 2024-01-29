package NodeData;
import java.util.Date;

public class NodeMobil extends DataMobil{
        private Date checkInTime;

        public NodeMobil (String platNomor, Date checkInTime) {
            super(platNomor);
            this.platNomor = platNomor;
            this.checkInTime = checkInTime;
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
}

