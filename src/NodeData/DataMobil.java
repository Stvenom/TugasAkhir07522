package NodeData;

public abstract class DataMobil {
    protected String platNomor;

    public DataMobil(String platNomor) {
        this.platNomor = platNomor;
    }

    public String getPlatNomor(){
        return platNomor;
    }
}
