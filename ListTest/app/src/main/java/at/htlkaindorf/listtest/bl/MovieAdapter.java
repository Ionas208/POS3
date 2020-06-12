package at.htlkaindorf.listtest.bl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.time.Year;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import at.htlkaindorf.listtest.R;

public class MovieAdapter extends RecyclerView.Adapter<MovieHolder> {


    private List<Movie> movieList = Arrays.asList(
            new Movie("Filip Zackbert", YearMonth.of(2003, 3), 228),
            new Movie("Waul Peiß", YearMonth.of(2008, 8), 121),
            new Movie("Lol", YearMonth.of(2069, 6), 666)
      );

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);

        TextView tvMovieName = view.findViewById(R.id.tvMovieName);
        TextView tvDuration = view.findViewById(R.id.tvDuration);
        TextView tvReleased = view.findViewById(R.id.tvReleased);

        return new MovieHolder(view, tvMovieName, tvDuration, tvReleased);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, int position) {
            Movie movie = movieList.get(position);
            holder.getTvMovieName().setText(movie.getName());
            holder.getTvDuration().setText(movie.getDurationString());
            holder.getTvReleased().setText(movie.getReleasedString());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
