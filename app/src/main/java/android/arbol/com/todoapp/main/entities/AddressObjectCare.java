package android.arbol.com.todoapp.main.entities;

/**
 * Created by Jorge on 01/09/2017.
 */

public class AddressObjectCare {

    private double latitude;
    private double longitude;
    private String zone;
    private String colony;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }
}
