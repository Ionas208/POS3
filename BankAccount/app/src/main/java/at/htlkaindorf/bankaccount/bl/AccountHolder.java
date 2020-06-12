package at.htlkaindorf.bankaccount.bl;



import android.content.Intent;
import android.media.Image;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import at.htlkaindorf.bankaccount.MainActivity;


public class AccountHolder  extends RecyclerView.ViewHolder {

    private ImageView ivImage;
    private TextView tvHeadline;
    private TextView tvIban;
    private TextView tvIbanNum;
    private TextView tvBalance;
    private TextView tvAvailable;
    private TextView tvAvailableNum;
    private Account account;
    private MainActivity pointer;

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public MainActivity getPointer() {
        return pointer;
    }

    public void setPointer(MainActivity pointer) {
        this.pointer = pointer;
    }

    public AccountHolder(@NonNull View itemView, ImageView ivImage, TextView tvHeadline, TextView tvIban, TextView tvIbanNum, TextView tvBalance, TextView tvAvailable, TextView tvAvailableNum) {
        super(itemView);
        this.ivImage = ivImage;
        this.tvHeadline = tvHeadline;
        this.tvIban = tvIban;
        this.tvIbanNum = tvIbanNum;
        this.tvBalance = tvBalance;
        this.tvAvailable = tvAvailable;
        this.tvAvailableNum = tvAvailableNum;

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(pointer, TransactionActivity.class);
            if(account instanceof StudentAccount){
                intent.putExtra("account", (StudentAccount)account);
            }
            else{
                intent.putExtra("account", (GiroAccount)account);
            }
            pointer.startActivity(intent);
        });
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public TextView getTvHeadline() {
        return tvHeadline;
    }

    public void setTvHeadline(TextView tvHeadline) {
        this.tvHeadline = tvHeadline;
    }

    public TextView getTvIban() {
        return tvIban;
    }

    public void setTvIban(TextView tvIban) {
        this.tvIban = tvIban;
    }

    public TextView getTvIbanNum() {
        return tvIbanNum;
    }

    public void setTvIbanNum(TextView tvIbanNum) {
        this.tvIbanNum = tvIbanNum;
    }

    public TextView getTvBalance() {
        return tvBalance;
    }

    public void setTvBalance(TextView tvBalance) {
        this.tvBalance = tvBalance;
    }

    public TextView getTvAvailable() {
        return tvAvailable;
    }

    public void setTvAvailable(TextView tvAvailable) {
        this.tvAvailable = tvAvailable;
    }

    public TextView getTvAvailableNum() {
        return tvAvailableNum;
    }

    public void setTvAvailableNum(TextView tvAvailableNum) {
        this.tvAvailableNum = tvAvailableNum;
    }
}
