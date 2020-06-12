package at.htlkaindorf.zodiacsign.bl;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ZodiacHolder extends RecyclerView.ViewHolder {

    private ImageView ivPicture;
    private TextView tvTitle;
    private TextView tvDate;

    public ZodiacHolder(@NonNull View itemView, ImageView ivPicture, TextView tvTitle, TextView tvDate) {
        super(itemView);
        this.ivPicture = ivPicture;
        this.tvTitle = tvTitle;
        this.tvDate = tvDate;
    }

    public ImageView getIvPicture() {
        return ivPicture;
    }

    public void setIvPicture(ImageView ivPicture) {
        this.ivPicture = ivPicture;
    }

    public TextView getTvTitle() {
        return tvTitle;
    }

    public void setTvTitle(TextView tvTitle) {
        this.tvTitle = tvTitle;
    }

    public TextView getTvDate() {
        return tvDate;
    }

    public void setTvDate(TextView tvDate) {
        this.tvDate = tvDate;
    }
}
