package at.htlkaindorf.a2048;

import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class GameLogic {

    private ButtonColor[][] buttons;
    private Button[][] guiButtons;

    public GameLogic(Button[][] guiButtons) {
        this.buttons = new ButtonColor[4][4];
        this.guiButtons = guiButtons;
        reset();
    }

    public void reset() {
        Random rand = new Random();
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                this.buttons[i][j] = ButtonColor.C0;
                guiButtons[i][j].setText(ButtonColor.C0.getVal() + "");
                guiButtons[i][j].setTextColor(ButtonColor.C0.getFontColor());
                guiButtons[i][j].setBackgroundColor(ButtonColor.C0.getBackgroundColor());
            }
        }
        for (int i = 0; i < 2; ) {
            int rdm = rand.nextInt(3);
            ButtonColor number = null;
            if (rdm >= 0 && rdm <= 1) {
                number = ButtonColor.C2;
            } else {
                number = ButtonColor.C4;
            }
            int x = rand.nextInt(4);
            int y = rand.nextInt(4);
            if (this.buttons[x][y].getVal() == 0) {
                this.buttons[x][y] = number;
                guiButtons[x][y].setText(number.getVal() + "");
                guiButtons[x][y].setTextColor(number.getFontColor());
                guiButtons[x][y].setBackgroundColor(number.getBackgroundColor());
                i++;
            }
        }
    }

    public void handleCases(char direction) {
        switch (direction) {
            case 'L':
                Log.println(Log.ASSERT, "asdf", "L");
                merge();
                updateGUIfield();
                break;
            case 'R':
                Log.println(Log.ASSERT, "asdf", "R");
                merge();
                updateGUIfield();
                break;
            case 'U':
                Log.println(Log.ASSERT, "asdf", "U");
                mirror();
                transpose(1);
                merge();
                transpose(3);
                updateGUIfield();
                break;
            case 'D':
                Log.println(Log.ASSERT, "asdf", "D");
                transpose(1);
                merge();
                transpose(3);
                updateGUIfield();
                break;
        }
    }

    public void mirror() {
        for (int i = 0; i < 4; i++) {
            List<ButtonColor> list = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                list.add(buttons[j][i]);
            }
            int counter = 0;
            for (int j = 3; j >= 0; j--) {
                buttons[j][i] = list.get(counter);
                counter++;
            }
        }
    }

    public void transpose(int xTimes90Right) {
        for (int x = 0; x < xTimes90Right; x++) {
            int size = buttons.length;
            ButtonColor[][] erg = new ButtonColor[size][size];
            for (int i = 0; i < size; ++i)
                for (int j = 0; j < size; ++j)
                    erg[i][j] = buttons[size - j - 1][i];
            buttons = erg;
        }

    }

    public void printArray(char d) {
        String erg = "Array" + d + ":\n";
        for (int i = 0; i < 4; i++) {
            String line = "";
            for (int j = 0; j < 4; j++) {
                line += buttons[j][i].getVal() + "";
            }
            erg += line + "\n";
        }
        Log.println(Log.ASSERT, "asd", erg);
    }

    public void merge() {
        for (int i = 0; i < 4; i++) {
            String lineS = "";
            for (int j = 0; j < 4; j++) {
                if(buttons[i][j].getVal()!=0){
                    lineS += buttons[i][j].getVal()+";";
                }
            }
            Log.println(Log.ASSERT, "asdf", lineS);
            String[] tokens = lineS.split(";");
            int[] line = new int[4];
            Log.println(Log.ASSERT, "asdf", Arrays.toString(tokens));

            /*for (int j = 0; j < 3; j++) {
                if(line[j]==line[j+1] && line[j]!=0){
                    buttons[i][j] = ButtonColor.valueOf("C"+line[j]*2);
                    line[j] = 3;
                    line[j+1] = 3;
                    j++;
                }
                else{
                    buttons[i][j] = ButtonColor.valueOf("C"+line[j]);
                }
            }*/
        }
    }

    public void updateGUIfield() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ButtonColor current = buttons[i][j];
                guiButtons[i][j].setText(current.getVal() + "");
                guiButtons[i][j].setTextColor(current.getFontColor());
                guiButtons[i][j].setBackgroundColor(current.getBackgroundColor());
            }
        }
    }

}
