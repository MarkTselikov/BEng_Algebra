package Model;

public class Address {

    private int doorNumber;
    private String street;
    private String area;
    private String city;
    private String state;
    private String code;

    public Address(int doorNumber, String street, String area, String city, String state, String code) {
        this.doorNumber = doorNumber;
        this.street = street;
        this.area = area;
        this.city = city;
        this.state = state;
        this.code = code;
    }

    @Override
    public String toString() {
        return street + " " + doorNumber + ", " + area + ", " + city + ", " + state + " / " + code;
    }
}
