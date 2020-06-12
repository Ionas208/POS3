package at.htlkaindorf.contactlist.bl;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.contactlist.MainActivity;
import at.htlkaindorf.contactlist.R;

public class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {

    private List<Contact> contacts = new ArrayList<>();
    private List<Contact> contactsFiltered = new ArrayList<>();


    public ContactAdapter(MainActivity pointer){
        this.readItems(pointer);
    }

    @NonNull
    @Override
    public ContactHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item , parent, false);

        ImageView ivPicutre = view.findViewById(R.id.ivPicture);
        TextView tvName = view.findViewById(R.id.tvName);

        Util.ref = this;
        Util.list = contacts;
        Util.listFiltered = contactsFiltered;

        return new ContactHolder(view, ivPicutre, tvName);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactHolder holder, int position) {
        Contact contact = contactsFiltered.get(position);
        String url = contact.getPicture();
        Picasso.get()
                .load(url)
                .resize(50, 50)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(holder.getIvPicture());
        holder.contact = contact;
        holder.getTvName().setText(contact.getLastname() + ", " + contact.getFirstname());
    }

    @Override
    public int getItemCount() {
        return contactsFiltered.size();
    }


    private void readItems(MainActivity pointer){
        AssetManager am = pointer.getAssets();
        try{
            InputStream is = am.open("contact_data.csv");
            contacts = new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .skip(1)
                    .map(Contact::new)
                    .collect(Collectors.toList());
        }
        catch (IOException e){
            e.printStackTrace();
        }
        contactsFiltered = new ArrayList<>(contacts);
    }

    public void filter(String filter){
        contactsFiltered.clear();
        contactsFiltered.addAll(contacts);
        contactsFiltered.removeIf(item -> !((item.getFirstname().toUpperCase()+item.getLastname().toUpperCase()).contains(filter.toUpperCase())));
        this.notifyDataSetChanged();
    }

}
