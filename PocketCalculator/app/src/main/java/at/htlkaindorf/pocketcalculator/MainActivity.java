package at.htlkaindorf.pocketcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import bl.Stack;

public class MainActivity extends AppCompatActivity {

    private TextView tvIO;

    private Button btComma;
    private Button btChange;

    private Button btPlus;
    private Button btMinus;
    private Button btMult;
    private Button btDiv;

    private Button btEnter;
    private Button btClear;

    private boolean showingResult = false;

    Stack stack = new Stack();

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvIO = (TextView)findViewById(R.id.tvIO);

        btComma = (Button)findViewById(R.id.btComma);
        btChange = (Button)findViewById(R.id.btChange);

        btPlus = (Button)findViewById(R.id.btPlus);
        btMinus = (Button)findViewById(R.id.btMinus);
        btMult = (Button)findViewById(R.id.btMult);
        btDiv = (Button)findViewById(R.id.btDiv);

        btEnter = (Button)findViewById(R.id.btEnter);
        btClear = (Button)findViewById(R.id.btClear);


        btPlus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onOperator(v);
            }
        });
        btMinus.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onOperator(v);
            }
        });
        btMult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onOperator(v);
            }
        });
        btDiv.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onOperator(v);
            }
        });
        btComma.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onComma(v);
            }
        });
        btChange.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onSign(v);
            }
        });


        btClear.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clear(v);
            }
        });

        btEnter.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                onEnter(v);
            }
        });

    }

    private void onComma(View v){
        if(showingResult){
            tvIO.setText(0+"");
            showingResult = false;
        }
        if((tvIO.getText()+"").length()<11){
            if(!(tvIO.getText()+"").contains(".")){
                tvIO.append(".");
            }
        }
    }

    private void onSign(View v){
        if(showingResult){
            tvIO.setText(0+"");
            showingResult = false;
        }
        if((tvIO.getText()+"").length()<12){
            if((tvIO.getText()+"").contains("-")){
                tvIO.setText((tvIO.getText()+"").replace("-", ""));
            }
            else{
                tvIO.setText("-"+tvIO.getText());
            }
        }
    }

    public void onDigit(View v){
        int num = (v.getId())-2131165218;
        if(showingResult){
            tvIO.setText(0+"");
            showingResult = false;
        }
        if((tvIO.getText()+"").length()<12){
            if((tvIO.getText()+"").equals("0")){
                tvIO.setText(num+"");
            }
            else{
                tvIO.append(num+"");
            }
        }
    }

    private void clear(View v)
    {
        tvIO.setText(0+"");
        stack.clear();
    }

    private void onEnter(View v){
        try{
            double num = Double.parseDouble(tvIO.getText()+"");
            stack.push(num);
            showingResult = true;
        }
        catch(RuntimeException e){
            Toast toast = Toast.makeText(getApplicationContext(), "Stackoverflow", Toast.LENGTH_SHORT);
            toast.show();
            clear(v);
        }

    }

    private void onOperator(View v){
        int op = (v.getId())-2131165218;
        double num;
        double num1;
        double num2;
        Toast toast;
        switch(op){
            //Plus
            case 17:
                try{
                    num = Double.parseDouble(tvIO.getText()+"");
                    stack.push(num);

                    num2 = stack.pop();
                    num1 = stack.pop();
                    output((num1+num2)+"");
                    stack.push(num1+num2);
                    showingResult = true;
                }

                catch(RuntimeException e){
                    toast = Toast.makeText(getApplicationContext(), "Stackoverflow", Toast.LENGTH_SHORT);
                    toast.show();
                    clear(v);
                }
                break;
            //Minus
            case 15:
                try{
                    num = Double.parseDouble(tvIO.getText()+"");
                    stack.push(num);

                    num2 = stack.pop();
                    num1 = stack.pop();
                    output((num1-num2)+"");
                    stack.push(num1-num2);
                    showingResult = true;
                }

                catch(RuntimeException e){
                    toast = Toast.makeText(getApplicationContext(), "Stackoverflow", Toast.LENGTH_SHORT);
                    toast.show();
                    clear(v);
                }
                break;
            //Mult
            case 16:
                try{
                    num = Double.parseDouble(tvIO.getText()+"");
                    stack.push(num);

                    num2 = stack.pop();
                    num1 = stack.pop();
                    output((num1*num2)+"");
                    stack.push(num1*num2);
                    showingResult = true;
                }
                catch(RuntimeException e){
                    toast = Toast.makeText(getApplicationContext(), "Stackoverflow", Toast.LENGTH_LONG);
                    toast.show();
                    clear(v);
                }
                break;
            //Div
            case 13:
                try{
                    num = Double.parseDouble(tvIO.getText()+"");
                    stack.push(num);

                    num2 = stack.pop();
                    num1 = stack.pop();
                    if(num2 == 0){
                        throw new ArithmeticException();
                    }
                    output((num1/num2)+"");
                    stack.push(num1/num2);
                    showingResult = true;
                }

                catch(ArithmeticException e){
                    toast = Toast.makeText(getApplicationContext(), "Div by 0", Toast.LENGTH_SHORT);
                    toast.show();
                    clear(v);
                }

                catch(RuntimeException e){
                    toast = Toast.makeText(getApplicationContext(), "Stackoverflow", Toast.LENGTH_SHORT);
                    toast.show();
                    clear(v);
                }

                break;
        }
    }

    private void output(String number){

        if(number.contains("E")){
            String[] parts = number.split("E");
            String[] tokens = parts[0].split("\\.");

            int len = 10-parts[1].length() - tokens[0].length();
            String result = tokens[0] + "." + tokens[1].substring(0, len) + "E" + parts[1];
            tvIO.setText(result);
        }
        else{
            String[] parts = number.split("\\.");
            if(parts[0].length() > 10){
                tvIO.setText("Error: Number too long");
            }
            else {
                if(number.length()<=12){
                    tvIO.setText(number);
                }
                else{
                    String result = parts[0] + "." + parts[1].substring(0, 11-parts[0].length());
                    tvIO.setText(result);
                }

            }
        }

    }
}
