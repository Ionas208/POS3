package at.htlkaindorf.pethome;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import at.htlkaindorf.pethome.bl.Cat;
import at.htlkaindorf.pethome.bl.Dog;
import at.htlkaindorf.pethome.bl.Gender;
import at.htlkaindorf.pethome.bl.Pet;

public class detail extends AppCompatActivity {

    private ImageView ivImage;
    private TextView tvName;
    private TextView tvDoB;
    private TextView tvSizeColor;
    private TextView tvSizeColorText;
    private ImageView ivGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImage = findViewById(R.id.ivImage);
        tvName = findViewById(R.id.tvName);
        tvDoB = findViewById(R.id.tvDoB);
        tvSizeColor = findViewById(R.id.tvSizeColor);
        tvSizeColorText = findViewById(R.id.tvSizeColorText);
        ivGender = findViewById(R.id.ivGender);

        Pet pet = (Pet) this.getIntent().getParcelableExtra("pet");
        tvName.setText(pet.getName());
        tvDoB.setText(pet.getDateOfBirth().toString());
        if((pet.getGender()).equals(Gender.MALE)){
            ivGender.setImageResource(R.drawable.male);
        }
        else{
            ivGender.setImageResource(R.drawable.female);
        }
        if(pet instanceof Cat){
            tvSizeColor.setText("Color:");
            tvSizeColorText.setText(((Cat) pet).getColor().toString().toLowerCase());
            Picasso.get()
                    .load(((Cat) pet).getPictureURI())
                    .placeholder(R.drawable.cat)
                    .resize(200, 200)
                    .into(ivImage);
        }
        else{
            tvSizeColor.setText("Size:");
            tvSizeColorText.setText(((Dog)pet).getSize().toString().toLowerCase());
            ivImage.setImageResource(R.drawable.dog);
        }
    }
}
