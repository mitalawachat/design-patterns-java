class Bird {
    private double latitude, longitude, altitude;

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

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}

class Penguine extends Bird {
    @Override
    public void setAltitude(double altitude) {
        // do nothing as penguins can't fly (this violates LSP)
    }
}

public class LSPViolation {
    public static void main(String[] args) {
        double lat = 10.10;
        double lon = 20.20;
        double alt = 30.30;

        Penguine penguine = new Penguine();
        penguine.setLatitude(lat);
        penguine.setLongitude(lon);
        penguine.setAltitude(alt);

        assert (lat == penguine.getLatitude());
        assert (lon == penguine.getLongitude());
        assert (alt == penguine.getAltitude());
    }
}
