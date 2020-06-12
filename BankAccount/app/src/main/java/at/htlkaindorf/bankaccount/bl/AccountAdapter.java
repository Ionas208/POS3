package at.htlkaindorf.bankaccount.bl;

import android.content.res.AssetManager;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import at.htlkaindorf.bankaccount.MainActivity;
import at.htlkaindorf.bankaccount.R;


public class AccountAdapter extends RecyclerView.Adapter<AccountHolder> {

    private List<Account> accounts = new ArrayList<>();
    private List<Account> accountsFiltered = new ArrayList<>();
    private MainActivity pointer;
    public static RecyclerView.Adapter<AccountHolder> pointer4Notify;


    public AccountAdapter(MainActivity pointer){
        this.readItems(pointer);
        this.pointer = pointer;
        pointer4Notify = this;
    }

    @NonNull
    @Override
    public AccountHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_item , parent, false);

        ImageView ivImage = view.findViewById(R.id.ivImage);
        TextView tvHeadline = view.findViewById(R.id.tvHeadline);
        TextView tvAvailable = view.findViewById(R.id.tvAvailable);
        TextView tvAvailableNums = view.findViewById(R.id.tvAvailableNums);
        TextView tvBalance = view.findViewById(R.id.tvBalance);
        TextView tvIban = view.findViewById(R.id.tvIban);
        TextView tvIbanNum = view.findViewById(R.id.tvIbanNum);

        return new AccountHolder(view, ivImage, tvHeadline, tvIban, tvIbanNum, tvBalance, tvAvailable, tvAvailableNums);
    }

    @Override
    public void onBindViewHolder(@NonNull AccountHolder holder, int position) {
        Account account = accountsFiltered.get(position);
        if (account instanceof StudentAccount) {
            holder.getTvHeadline().setText("Studentaccount");
            holder.getTvAvailableNum().setText(account.getBalance()+"");
            holder.getIvImage().setImageResource(R.drawable.ic_attach_money_black_24dp);
        }
        else{
            holder.getTvHeadline().setText("Giroaccount");
            account = (GiroAccount)account;
            holder.getTvAvailableNum().setText(account.getBalance()+((GiroAccount) account).getOverdraft()+"");
            holder.getIvImage().setImageResource(R.drawable.ic_account_balance_black_24dp);
        }
        Double balance = account.getBalance();
        holder.getTvBalance().setText(balance+"");
        if(balance<0){
            holder.getTvBalance().setTextColor(Color.parseColor("#FF0000"));
        }
        else if(balance>0){
            holder.getTvBalance().setTextColor(Color.parseColor("#00FF4A"));
        }
        holder.getTvIbanNum().setText(account.getIban());
        holder.setAccount(account);
        holder.setPointer(this.pointer);
    }

    @Override
    public int getItemCount() {
        return accountsFiltered.size();
    }


    private void readItems(MainActivity pointer){
        AssetManager am = pointer.getAssets();
        try{
            InputStream is = am.open("account_data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line;
            br.readLine();
            while((line = br.readLine()) != null){
                String[] tokens = line.split("\\,");
                String iban = tokens[2];
                Double amount = Double.parseDouble(tokens[3]);
                Double overdraft = Double.parseDouble(tokens[4]);
                boolean card = Boolean.parseBoolean(tokens[5]);
                Float interest = Float.parseFloat(tokens[6]);
                if(tokens[1].equals("GIRO")){
                    accounts.add(new GiroAccount(iban, amount, interest, overdraft));
                }
                else{
                    accounts.add(new StudentAccount(iban, amount, interest, card));
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        accountsFiltered.clear();
        accountsFiltered.addAll(accounts);
        ListPointer.list = accounts;
    }

    public void filter(String filter){
        accountsFiltered.clear();
        accountsFiltered.addAll(accounts);
        Log.println(Log.ASSERT, "asdasd", accounts.get(0).getClass()+"");
        if(filter.equals("Students")){
            accountsFiltered.removeIf(item -> item instanceof GiroAccount);
        }
        else if(filter.equals("Giro")){
            accountsFiltered.removeIf(item -> item instanceof StudentAccount);
        }
        this.notifyDataSetChanged();
    }

}
