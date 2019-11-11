class Bird {

    private double latitude, longitude;

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
}

class FlyingBird extends Bird {

    private double altitude;

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }
}

public class LSP {
    public static void main(String[] args) {
        double lat = 10.10;
        double lon = 20.20;
        double alt = 30.30;

        Bird bird = new Bird();
        bird.setLatitude(lat);
        bird.setLongitude(lon);

        assert (lat == bird.getLatitude());
        assert (lon == bird.getLongitude());

        FlyingBird flyingBird = new FlyingBird();
        flyingBird.setLatitude(lat);
        flyingBird.setLongitude(lon);
        flyingBird.setAltitude(alt);

        assert (lat == flyingBird.getLatitude());
        assert (lon == flyingBird.getLongitude());
        assert (alt == flyingBird.getAltitude());
    }
}
