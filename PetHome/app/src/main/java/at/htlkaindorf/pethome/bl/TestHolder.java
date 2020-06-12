package at.htlkaindorf.pethome.bl;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TestHolder extends RecyclerView.ViewHolder {

    //Instanvarbialen erstellen
    private TextView test;


    //Konstruktur erstellen
    public TestHolder(@NonNull View itemView, TextView test) {
        super(itemView);
        this.test = test;
    }

    //Getter u. Setter

    public TextView getTest() {
        return test;
    }

    public void setTest(TextView test) {
        this.test = test;
    }
}
