public class CustomerAddress {
    private int id, streetnum, zip;
    private String streetname, city, state;

    public CustomerAddress(int custID, int num, String street, String aCity, String st, int zp) {
        this.id = custID;
        this.streetnum = num;
        this.streetname = street;
        this.city = aCity;
        this.state = st;
        this.zip = zp;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStreetNum() {
        return streetnum;
    }

    public void setStreetNum(int num) {
        this.streetnum = num;
    }

    public String getStreetName() {
        return streetname;
    }

    public void setStreetName(String stName) {
        this.streetname = stName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String ct) {
        this.city = ct;
    }

    public String getState() {
        return state;
    }

    public void setState(String st) {
        this.state = st;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zp) {
        this.zip = zp;
    }
}
