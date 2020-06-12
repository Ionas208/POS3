package at.htlkaindorf.pethome.bl;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.pethome.MainActivity;
import at.htlkaindorf.pethome.detail;
import at.htlkaindorf.pethome.pet_list;

public class PetHolder extends RecyclerView.ViewHolder {
    private TextView tvName;
    private TextView tvDateOfBirth;
    private TextView tvSize;
    private ImageView ivImage;
    private ImageView ivGender;
    private Pet pet;


    public PetHolder(@NonNull View itemView, TextView tvName, TextView tvDateOfBirth, TextView tvSize, ImageView ivImage, ImageView ivGender) {
        super(itemView);
        this.tvName = tvName;
        this.tvDateOfBirth = tvDateOfBirth;
        this.tvSize = tvSize;
        this.ivImage = ivImage;
        this.ivGender = ivGender;

        itemView.setOnClickListener(v -> {
            Pet pet = this.getPet();
            Intent intent = new Intent(MainActivity.thisPointer, detail.class);
            intent.putExtra("pet", pet);
            MainActivity.thisPointer.startActivity(intent);
        });
    }

    public TextView getTvName() {
        return tvName;
    }

    public void setTvName(TextView tvName) {
        this.tvName = tvName;
    }

    public TextView getTvDateOfBirth() {
        return tvDateOfBirth;
    }

    public void setTvDateOfBirth(TextView tvDateOfBirth) {
        this.tvDateOfBirth = tvDateOfBirth;
    }

    public TextView getTvSize() {
        return tvSize;
    }

    public void setTvSize(TextView tvSize) {
        this.tvSize = tvSize;
    }

    public ImageView getIvImage() {
        return ivImage;
    }

    public void setIvImage(ImageView ivImage) {
        this.ivImage = ivImage;
    }

    public ImageView getIvGender() {
        return ivGender;
    }

    public void setIvGender(ImageView ivGender) {
        this.ivGender = ivGender;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }
}
