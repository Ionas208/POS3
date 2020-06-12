/*
 * Jonas Seidl
 */

package zwei_klassen_modell.bl;

public class Address {
    private String street;
    private int number;
    private String city;
    private int zipCode;
    
    @Override
    public String toString(){
        return "Address: "+street+" "+number+" - "+zipCode+" "+city;
    }

    public Address(String street, int number, String city, int zipCode) {
        this.street = street;
        this.number = number;
        this.city = city;
        this.zipCode = zipCode;
    }
  
    public String getStreet() {
        return street;
    }

    public int getNumber() {
        return number;
    }

    public String getCity() {
        return city;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    
}
