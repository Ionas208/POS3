package at.htlkaindorf.pethome.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.pethome.R;

public class TestAdapter extends RecyclerView.Adapter<TestHolder> {

    List<String> testlist = new ArrayList<>();

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pet_item,parent,false);
        TextView tvTest = view.findViewById(R.id.tvSearch);
        return new TestHolder(view, tvTest);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        String test = testlist.get(position);
        holder.getTest().setText(test);
    }

    @Override
    public int getItemCount() {
        return testlist.size();
    }
}
