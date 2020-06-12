package at.htlkaindorf.contactlist.bl;



import android.content.Intent;
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

import at.htlkaindorf.contactlist.Detail;
import at.htlkaindorf.contactlist.MainActivity;


public class ContactHolder  extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvName;
    public Contact contact;
    private GestureDetectorCompat mGestureDetector;


    public ContactHolder(@NonNull View itemView, ImageView ivPicutre, TextView tvName) {
        super(itemView);
        this.ivPicture = ivPicutre;
        this.tvName = tvName;
        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.main, Detail.class);
            intent.putExtra("contact", contact);
            MainActivity.main.startActivity(intent);
        });
        MyGestureListener mgl = new MyGestureListener();
        mGestureDetector = new GestureDetectorCompat(itemView.getContext(), mgl);
        itemView.setOnTouchListener((v, event) -> mGestureDetector.onTouchEvent(event));
    }


    private class MyGestureListener
            extends GestureDetector.SimpleOnGestureListener {
        private final int MIN_DIST = 10;
        private final int MAX_DIST = 1000;
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2,
                               float velocityX, float velocityY) {
            float deltaX = e1.getX() - e2.getX();
            float deltaXAbs = Math.abs(deltaX);
            if (deltaXAbs > MIN_DIST && deltaXAbs < MAX_DIST) {
                if (deltaX < 0) {
                    Log.println(Log.ASSERT, "asdf", "delete");
                    (Util.list).remove(contact);
                    (Util.listFiltered).remove(contact);
                    (Util.ref).notifyDataSetChanged();
                }
            }
            return true;
        }
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
