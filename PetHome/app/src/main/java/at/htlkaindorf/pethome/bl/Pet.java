package at.htlkaindorf.pethome.bl;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.time.LocalDate;

public class Pet implements Parcelable {
    private String name;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Pet(String name, LocalDate dateOfBirth, Gender gender) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
    }

    protected Pet(Parcel in) {
        name = in.readString();
        dateOfBirth = LocalDate.ofEpochDay(in.readLong());
        gender = Gender.valueOf(in.readString());
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeLong(dateOfBirth.toEpochDay());
        dest.writeString(gender.toString());
    }
}
