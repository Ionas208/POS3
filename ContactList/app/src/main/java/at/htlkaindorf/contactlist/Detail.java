package at.htlkaindorf.contactlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import at.htlkaindorf.contactlist.bl.Contact;

public class Detail extends AppCompatActivity {

    private TextView tvFirstName;
    private TextView tvLastName;
    private TextView tvGender;
    private TextView tvLanguage;
    private TextView tvPhone;
    private ImageView ivPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Contact contact = getIntent().getParcelableExtra("contact");
        tvFirstName = findViewById(R.id.tvFirstname);
        tvLastName = findViewById(R.id.tvLastName);
        tvGender = findViewById(R.id.tvGender);
        tvLanguage = findViewById(R.id.tvLanguage);
        tvPhone = findViewById(R.id.tvPhone);
        ivPicture = findViewById(R.id.ivImage);


        tvFirstName.setText(contact.getFirstname());
        tvLastName.setText(contact.getLastname());
        tvLanguage.setText(contact.getLanguage());
        tvPhone.setText(contact.getPhoneNumber());
        tvGender.setText(contact.getGender()+"");

        String url = contact.getPicture();
        Picasso.get()
                .load(url)
                .resize(100, 100)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(ivPicture);
    }
}
