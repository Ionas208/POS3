package at.htlkaindorf.zodiacsign.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.zodiacsign.R;

public class ZodiacAdapter extends RecyclerView.Adapter<ZodiacHolder> {


    private List<ZodiacSign> zodiacList = ZodiacSign.getZodiacSignList();

    @NonNull
    @Override
    public ZodiacHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.zodiac_item, parent, false);
        ImageView ivPicutre = view.findViewById(R.id.ivPicture);
        TextView tvTitle = view.findViewById(R.id.tvTitle);
        TextView tvDate = view.findViewById(R.id.tvDate);
        return new ZodiacHolder(view, ivPicutre, tvTitle, tvDate);
    }

    @Override
    public void onBindViewHolder(@NonNull ZodiacHolder holder, int position) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM");
        ZodiacSign sign = zodiacList.get(position);
        holder.getIvPicture().setImageResource(sign.getPicutreID());
        holder.getTvTitle().setText(sign.getName());
        //MonthDay endDate = null;

        holder.getTvDate().setText(dtf.format(sign.getStartDate())/* + " - " + dtf.format(endDate)*/);
    }

    @Override
    public int getItemCount() {
        return zodiacList.size();
    }
}
