package at.htlkaindorf.contactlist.bl;



import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.GestureDetectorCompat;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.contactlist.MainActivity;


public class ContactHolder  extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvName;
    private GestureDetectorCompat mGestureDetector;


    public ContactHolder(@NonNull View itemView, ImageView ivPicutre, TextView tvName) {
        super(itemView);
        this.ivPicture = ivPicutre;
        this.tvName = tvName;
        itemView.setOnClickListener(v -> {
            
        });
    }


    public ImageView getIvPicture() {
        return ivPicture;
    }

    public void setIvPicture(ImageView ivPicture) {
        this.ivPicture = ivPicture;
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }


}
