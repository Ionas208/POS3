package at.htlkaindorf.bankaccount.bl;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import at.htlkaindorf.bankaccount.R;

public class TransactionActivity extends AppCompatActivity {

    private TextView tvHeadline;
    private TextView tvIbanNum;
    private TextView tvBalanceNum;
    private TextView tvAvailable;
    private ImageView ivImage;
    private AutoCompleteTextView etIban;
    private EditText etAmount;
    private Button btTransfer;
    private Account account;
    private boolean ibanV;
    private boolean amountV;
    private String[] ibans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        List<Account> list = ListPointer.list;
        ibans = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ibans[i] = list.get(i).getIban();
        }

        tvHeadline = findViewById(R.id.tvHeadline);
        tvIbanNum = findViewById(R.id.tvIbanNum);
        tvBalanceNum = findViewById(R.id.tvBalance);
        tvAvailable = findViewById(R.id.tvAvailableNums);
        etIban = findViewById(R.id.etIban);
        etAmount = findViewById(R.id.etAmount);
        btTransfer = findViewById(R.id.btTransfer);
        ivImage = findViewById(R.id.ivImage);

        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, android.R.layout.select_dialog_item, ibans);
        etIban.setAdapter(ad);


        account = this.getIntent().getParcelableExtra("account");
        if(account instanceof StudentAccount){
            tvHeadline.setText("Studentaccount");
            tvAvailable.setText(account.getBalance()+"");
            ivImage.setImageResource(R.drawable.ic_attach_money_black_24dp);
        }
        else{
            tvHeadline.setText("Giroaccount");
            tvAvailable.setText(account.getBalance()+((GiroAccount) account).getOverdraft()+"");
            ivImage.setImageResource(R.drawable.ic_account_balance_black_24dp);
        }
        tvBalanceNum.setText(account.getBalance()+"");
        tvIbanNum.setText(account.getIban());

        btTransfer.setOnClickListener(v -> {
            String iban = etIban.getText().toString();
            Double amountToTransfer = Double.parseDouble(etAmount.getText().toString());
            int accountIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).equals(account)){
                    accountIndex = i;
                }
            }
            int accountToTransfer2Index = -1;
            for (int i = 0; i < list.size(); i++) {
                if(list.get(i).getIban().equals(iban)){
                    accountToTransfer2Index = i;
                }
            }
            list.get(accountIndex).setBalance(list.get(accountIndex).getBalance()-amountToTransfer);
            list.get(accountToTransfer2Index).setBalance(list.get(accountToTransfer2Index).getBalance()+amountToTransfer);
            AccountAdapter.pointer4Notify.notifyDataSetChanged();
            this.finish();
        });

        etAmount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try{
                    Double amount = Double.parseDouble(s.toString());
                    Double amountAfter = account.getBalance() - amount;
                    Double validAmount = account.getBalance();
                    tvBalanceNum.setText(amountAfter+"");
                    if(amountAfter<0){
                        tvBalanceNum.setTextColor(Color.parseColor("#FF0000"));
                    }
                    else if(amountAfter>0){
                        tvBalanceNum.setTextColor(Color.parseColor("#00FF4A"));
                    }
                    if(account instanceof GiroAccount){
                        validAmount += ((GiroAccount)account).getOverdraft();
                    }
                    if(amount <= validAmount && amount >0){
                        amountV = true;
                        ifBothTrue();
                    }
                    else{
                        amountV = false;
                        ifBothTrue();
                    }
                }
                catch (NumberFormatException e){
                    //this is perfectly fine
                }

            }
        });
        etIban.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                boolean invalid = true;
                for (int i = 0; i < ibans.length; i++) {
                    if(ibans[i].equals(s.toString())){
                        ibanV = true;
                        invalid = false;
                        ifBothTrue();
                        break;
                    }
                }
                if(invalid){
                    ibanV = false;
                    ifBothTrue();
                }
            }
        });
    }
    private void ifBothTrue(){
        if(ibanV && amountV){
            btTransfer.setEnabled(true);
        }
        else{
            btTransfer.setEnabled(false);
        }
    }
}
