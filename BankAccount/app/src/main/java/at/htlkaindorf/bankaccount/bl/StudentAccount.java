package at.htlkaindorf.bankaccount.bl;

import android.os.Parcel;
import android.os.Parcelable;

public class StudentAccount extends Account implements Parcelable {

    private boolean debitCard;

    protected StudentAccount(Parcel in) {
        super(in.readString(), in.readDouble(), in.readFloat());
        debitCard = in.readByte() != 0;
    }

    public static final Creator<StudentAccount> CREATOR = new Creator<StudentAccount>() {
        @Override
        public StudentAccount createFromParcel(Parcel in) {
            return new StudentAccount(in);
        }

        @Override
        public StudentAccount[] newArray(int size) {
            return new StudentAccount[size];
        }
    };

    public boolean isDebitCard() {
        return debitCard;
    }

    public void setDebitCard(boolean debitCard) {
        this.debitCard = debitCard;
    }


    public StudentAccount(String iban, double balance, float interest, boolean debitCard) {
        super(iban, balance, interest);
        this.debitCard = debitCard;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getIban());
        dest.writeDouble(this.getBalance());
        dest.writeFloat(this.getInterest());
        dest.writeBoolean(debitCard);
    }
}
