package at.htlkaindorf.pethome.bl;

import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.pethome.MainActivity;

public class IO_Helper {
    /*public static List<Pet> readFile(){
        List<Pet> pets = new ArrayList<>();
        AssetManager am = MainActivity.thisPointer.getAssets();
        try {
            FileInputStream fis = new FileInputStream(String.valueOf(am.open("pets.ser")));
            ObjectInputStream oos = new ObjectInputStream(fis);
            pets = (List<Pet>) oos.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return pets;
    }
    public static void writeToFile(List<Pet> pets){
        AssetManager am = MainActivity.thisPointer.getAssets();
        try {
            FileOutputStream fis = new FileOutputStream(String.valueOf(am.open("pets.ser")));
            ObjectOutputStream oos = new ObjectOutputStream(fis);
            oos.writeObject(pets);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    public static List<Pet> initList(){
        List<Pet> pets = new ArrayList<>();
        AssetManager am = MainActivity.thisPointer.getAssets();
        try {
            InputStream is = am.open("pets.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            br.readLine();
            while((line = br.readLine()) != null){

                String[] tokens = line.split("\\,");
                String type = tokens[0];
                String name = tokens[1];
                String gender = tokens[2];
                String birthdate = tokens[3];
                String size = tokens[4];
                String color = tokens[5];
                String avatar = tokens[6];

                String[] bdParts = birthdate.split("/");

                int month = Integer.parseInt(bdParts[0]);
                int day = Integer.parseInt(bdParts[1]);
                int year = Integer.parseInt(bdParts[2]);

                LocalDate dateOfBirth = LocalDate.of(year, month, day);

                Gender genderENUM;
                if(gender.equals("Male")){
                    genderENUM = Gender.MALE;
                }
                else{
                    genderENUM = Gender.FEMALE;
                }

                if(type.equals("cat")){
                    CatColor colorENUM = CatColor.valueOf(color.toUpperCase());
                    pets.add(new Cat(name, dateOfBirth, genderENUM, colorENUM, avatar));
                }
                else{
                    Size sizeENUM = null;
                    switch(size){
                        case "S":
                            sizeENUM = Size.SMALL;
                            break;
                        case "M":
                            sizeENUM = Size.MEDIUM;
                            break;
                        case "L":
                            sizeENUM = Size.LARGE;
                            break;
                    }
                    pets.add(new Dog(name, dateOfBirth, genderENUM, sizeENUM));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.println(Log.ASSERT, "asdf",pets.size()+"");
        return pets;
    }
}
