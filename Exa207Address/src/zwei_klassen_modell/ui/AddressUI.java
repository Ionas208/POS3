    /*
 * Jonas Seidl
 */

package zwei_klassen_modell.ui;

import java.io.IOException;
import zwei_klassen_modell.bl.Address;
import java.util.Scanner;

public class AddressUI {
    
    private int numberCurrentAddresses=0;
    private Address[] addresses = new Address[100];
    
    public Address createAddress(){
        
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        System.out.print("Enter street: ");
        String street = scan.next();
        System.out.print("Enter number: ");
        int number = scan.nextInt();
        System.out.print("Enter city: ");
        String city = scan.next();
        System.out.print("Enter zipcode: ");
        int zipCode = scan.nextInt(); 
        addresses[numberCurrentAddresses] = new Address(street,number,city,zipCode);
        numberCurrentAddresses++;
        System.out.println(addresses[numberCurrentAddresses-1]);
        System.out.println("Press any button to continue...");
        scan.next();
        return addresses[numberCurrentAddresses-1];
    }
    
    public void cls(){
        for (int i = 0; i < 50; i++) {
            System.out.println("");
        }
    }
    
    public void changeAddress(Address ad){
        int selection=0;
        Scanner scan = new Scanner(System.in).useDelimiter("\n");
        do {
            System.out.println("What would you like to change?");
            System.out.println("(1)Street: " + ad.getStreet());
            System.out.println("(2)Number: " + ad.getNumber());
            System.out.println("(3)City: " + ad.getCity());
            System.out.println("(4)Zip Code: " + ad.getZipCode());
            System.out.println("(5)I don't want to change anything");
            selection = scan.nextInt();
        } while (selection < 1 && selection > 5);

        switch (selection) {
            case 1:
                System.out.print("Enter new value: ");
                ad.setStreet(scan.next());
                break;
            case 2:
                System.out.print("Enter new value: ");
                ad.setNumber(scan.nextInt());
                break;
            case 3:
                System.out.print("Enter new value: ");
                ad.setCity(scan.next());
                break;
            case 4:
                System.out.print("Enter new value: ");
                ad.setZipCode(scan.nextInt());
                break;
        }
        System.out.println(ad);
        System.out.println("Press any button to continue...");
        scan.next();
    }
    
    public void deleteAddress(int index){
        for (int i = index;i<numberCurrentAddresses;i++) {
            addresses[i]=addresses[i+1];
        }
        numberCurrentAddresses--;
    }
    
    public void manageAddresses(){
        boolean exit=false;
        int option;
        Scanner scan = new Scanner(System.in);
        while(!exit){
            System.out.println("====Address Management====");
            System.out.println("Current Addresses: "+numberCurrentAddresses);
            System.out.println("Select Operation:");
            System.out.println("(1) Add new Address");
            System.out.println("(2) View Address");
            System.out.println("(3) Change Address");
            System.out.println("(4) Delete Address");
            System.out.println("(5) Exit");
            option=scan.nextInt();
            switch(option){
                case 1:
                    createAddress();
                    break;
                case 2:
                    System.out.println("What Address would you like to see?");
                    System.out.println(addresses[scan.nextInt()-1]);
                    System.out.println("Press any button to continue...");
                    scan.next();
                    break;
                case 3:
                    System.out.println("What Address would you like to change?");
                    changeAddress(addresses[scan.nextInt()-1]);
                    break;
                case 4:
                    System.out.println("What Address would you like to delete?");
                    deleteAddress(scan.nextInt()-1);
                    break;
                default:
                    exit=true;
                    break;
            }
            cls();
        }
    }
    
    public static void main(String[] args) {
        AddressUI adUI = new AddressUI(); 
        adUI.manageAddresses();
    }
}
