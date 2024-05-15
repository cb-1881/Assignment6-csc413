public class CustomerAddressDTO {
    private int streetNum;
    private String streetName;
    private String city;
    private String state;
    private int zip;
    private int cusId;  // Note that cusId is last in the order





    public CustomerAddressDTO(int streetNum, String streetName, String city, String state, int zip, int cusId) {
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.cusId = cusId;
    }


    public int getCusId() {
        return cusId;
    }




    public void setCusId(int cusId) {
        this.cusId = cusId;
    }


    public int getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(int streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }
}
