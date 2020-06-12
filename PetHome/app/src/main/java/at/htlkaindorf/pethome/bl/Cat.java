package at.htlkaindorf.pethome.bl;

import android.os.Parcel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.time.LocalDate;

public class Cat extends Pet{
    private CatColor color;
    private String pictureURI;

    public Cat(String name, LocalDate dateOfBirth, Gender gender, CatColor color, String pictureURI) {
        super(name, dateOfBirth, gender);
        this.color = color;
        this.pictureURI = pictureURI;
    }

    public CatColor getColor() {
        return color;
    }

    public void setColor(CatColor color) {
        this.color = color;
    }

    public String getPictureURI() {
        return pictureURI;
    }

    public void setPictureURI(String pictureURI) {
        this.pictureURI = pictureURI;
    }

    protected Cat(Parcel in) {
        super(in);
        this.color = CatColor.valueOf(in.readString());
        this.pictureURI = in.readString();
    }

    public static final Creator<Cat> CREATOR = new Creator<Cat>() {
        @Override
        public Cat createFromParcel(Parcel in) {
            return new Cat(in);
        }

        @Override
        public Cat[] newArray(int size) {
            return new Cat[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeString(color.toString());
        dest.writeString(pictureURI);
    }

}
