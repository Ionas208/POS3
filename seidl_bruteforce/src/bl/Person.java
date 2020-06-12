/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bl;

/**
 *
 * @author 10jon
 */
public class Person {
    private String firstname;
    private String lastname;
    private String hash;
    private String password;

    public Person(String line) {
        String[] tokens = line.split(",");
        this.firstname = tokens[0];
        this.lastname = tokens[1];
        this.password = tokens[2];
        this.hash = tokens[3];
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String shash) {
        this.hash = shash;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" + "firstname=" + firstname + ", lastname=" + lastname + ", hash=" + hash + ", password=" + password + '}';
    }
    
    
    
}
