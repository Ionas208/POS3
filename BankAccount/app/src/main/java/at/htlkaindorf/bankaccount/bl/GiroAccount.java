package at.htlkaindorf.bankaccount.bl;

import android.os.Parcel;
import android.os.Parcelable;

public class GiroAccount extends Account implements Parcelable {
    private double overdraft;

    public GiroAccount(String iban, double balance, float interest, double overdraft) {
        super(iban, balance, interest);
        this.overdraft = overdraft;
    }

    protected GiroAccount(Parcel in) {
        super(in.readString(), in.readDouble(), in.readFloat());
        overdraft = in.readDouble();
    }

    public static final Creator<GiroAccount> CREATOR = new Creator<GiroAccount>() {
        @Override
        public GiroAccount createFromParcel(Parcel in) {
            return new GiroAccount(in);
        }

        @Override
        public GiroAccount[] newArray(int size) {
            return new GiroAccount[size];
        }
    };

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
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
        dest.writeDouble(overdraft);
    }
}
