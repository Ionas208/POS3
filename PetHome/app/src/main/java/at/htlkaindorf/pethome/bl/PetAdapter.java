package at.htlkaindorf.pethome.bl;

import android.content.res.AssetManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.pethome.MainActivity;
import at.htlkaindorf.pethome.R;

public class PetAdapter extends RecyclerView.Adapter<PetHolder>{
    private List<Pet> pets;
    private List<Pet> filtered;

    public PetAdapter() {
        pets = IO_Helper.initList();
        filtered = new ArrayList<>();
        filtered.addAll(pets);
    }

    @NonNull
    @Override
    public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item,parent,false);
        TextView tvName = view.findViewById(R.id.tvName);
        TextView tvDateOfBirth = view.findViewById(R.id.tvDateOfBirth);
        TextView tvSize = view.findViewById(R.id.tvSize);
        ImageView ivImage = view.findViewById(R.id.ivImage);
        ImageView ivGender = view.findViewById(R.id.ivGender);
        PetHolder petHolder = new PetHolder(view, tvName, tvDateOfBirth, tvSize, ivImage, ivGender);
        return petHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull PetHolder holder, int position) {
        Pet pet = filtered.get(position);
       holder.getTvName().setText(pet.getName());
       holder.getTvDateOfBirth().setText(pet.getDateOfBirth().toString());
        if((pet.getGender()).equals(Gender.MALE)){
            holder.getIvGender().setImageResource(R.drawable.male);
        }
        else{
            holder.getIvGender().setImageResource(R.drawable.female);
        }
       if(pet instanceof Cat){
           String uri = ((Cat)pet).getPictureURI();
           Picasso lol = Picasso.get();
           lol.load(uri)
               .resize(200, 200)
               .placeholder(R.drawable.cat)
               .into(holder.getIvImage());
            holder.getTvSize().setText((((Cat) pet).getColor().toString()).toLowerCase());
       }
       else{
           Size size = ((Dog)pet).getSize();
           String sizeText = "";
           switch(size){
               case SMALL:
                   sizeText = "Small";
                   break;
               case MEDIUM:
                   sizeText = "Medium";
                   break;
               case LARGE:
                   sizeText = "Large";
                   break;
           }
           holder.getTvSize().setText(sizeText);
           holder.getIvImage().setImageResource(R.drawable.dog);
       }
       holder.setPet(pet);

    }
    public void filter(boolean cat){
        if(cat){
            filtered.clear();
            filtered.addAll(pets);
            filtered.removeIf(p -> p instanceof Dog);
            this.notifyDataSetChanged();
        }
        else{
            filtered.clear();
            filtered.addAll(pets);
            filtered.removeIf(p -> p instanceof Cat);
            this.notifyDataSetChanged();
        }
    }

    public void search(String text){
        if(!text.equals("")){
            filtered.removeIf(p ->{
                String searchedText = "";
                searchedText += p.getName()+"|";
                searchedText += p.getDateOfBirth()+"|";
                searchedText += p.getGender().toString()+"|";
                if(p instanceof Cat){
                    searchedText += ((Cat) p).getColor();
                }
                else{
                    searchedText += ((Dog)p).getSize();
                }
                searchedText = searchedText.toLowerCase();
                return !searchedText.contains(text.toLowerCase());
            });
            this.notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return filtered.size();
    }
}
