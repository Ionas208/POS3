package at.htlkaindorf.minesweeper.bl;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.Toast;

import java.util.Random;

import at.htlkaindorf.minesweeper.MainActivity;
import at.htlkaindorf.minesweeper.R;
import at.htlkaindorf.minesweeper.beans.Field;
import at.htlkaindorf.minesweeper.beans.NumMines;
import at.htlkaindorf.minesweeper.game;

public class GameLogic {

    private Field[][] field;
    private Button[][] buttons;
    private game main;
    private int numBombs;
    private int size;
    private int counter = 0;

    public GameLogic(Button[][] buttons, game main, int numBombs, int size){
        this.buttons = buttons;
        this.main = main;
        this.numBombs = numBombs;
        this.size = size;
    }

    public void placeMines(int startButton){
        counter = 0;
        field = new Field[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                field[i][j] = new Field(false, NumMines.Def, true, false);
            }
        }
        Random rand = new Random();
        for (int i = 0; i < numBombs;) {
            int randomX = rand.nextInt(size);
            int randomY = rand.nextInt(size);
            if(buttons[randomX][randomY].getId() != startButton){
                field[randomX][randomY] = new Field(true, NumMines.BOMB, true, false);
                i++;
            }
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(!field[i][j].isBomb()){
                    int num = scoutNeighbours(i, j);
                    field[i][j] = new Field(false, NumMines.valueOf("B"+num), true, false);
                }
            }
        }

        int startX = 0;
        int startY = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(buttons[i][j].getId()==startButton){
                    startX = i;
                    startY = j;
                    break;
                }
            }
        }
        field[startX][startY].setHidden(false);
        if(field[startX][startY].getNumMines().equals(NumMines.B0)){
            reveal(startX, startY);
        }
        else{
            counter++;
            checkForWin("placemines");
        }
        synchroniseArrays();
    }

    public void makeMove(int bt){
        int x = 0;
        int y = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(buttons[i][j].getId()==bt){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        Field f = field[x][y];
        if(!f.isFlagged()){
            if(f.isBomb()){
                Drawable bomb = main.getApplicationContext().getResources().getDrawable(R.drawable.bomb);
                buttons[x][y].setBackground(bomb);
                for (int i = 0; i < size; i++) {
                    for (int j = 0; j < size; j++) {
                        if(field[i][j].isBomb()){
                            buttons[i][j].setBackground(bomb);
                            field[i][j].setHidden(false);
                        }
                    }
                }
                synchroniseArrays();
                main.endGame(false);
            }
            else if(f.getNumMines().equals(NumMines.B0)){
                reveal(x, y);
            }
            else{
                f.setHidden(false);
                counter++;
                checkForWin("makemove");
            }
            synchroniseArrays();
        }
    }

    public int scoutNeighbours(int x, int y){
        int counter = 0;
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                try{
                    if(field[i][j].isBomb()){
                        counter++;
                    }
                }
                catch(IndexOutOfBoundsException e){
                    //perfectly fine
                }
            }
        }
        return counter;
    }

    public void reveal(int x, int y){
        for (int i = x-1; i < x+2; i++) {
            for (int j = y-1; j < y+2; j++) {
                try{
                    if(field[i][j].getNumMines().equals(NumMines.B0) && field[i][j].isHidden() == true){
                        field[i][j].setHidden(false);
                        counter++;
                        checkForWin("reaveal1");
                        reveal(i, j);
                    }
                    else if(field[i][j].isHidden() == true){
                        field[i][j].setHidden(false);
                        counter++;
                        checkForWin("reveal2");
                    }
                }
                catch(IndexOutOfBoundsException e){
                    //perfectly fine
                }
            }
        }
    }

    public void synchroniseArrays(){
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Field f = field[i][j];
                if(f.isHidden()){
                    buttons[i][j].setText("");
                }
                else{
                    if(f.isBomb()){
                        buttons[i][j].setText("");
                    }
                    else{
                        if(f.getNumMines().getMines()==0){
                            buttons[i][j].setText("");
                            buttons[i][j].setBackgroundColor(0xFFD3D3D3);
                        }
                        else{
                            buttons[i][j].setText(f.getNumMines().getMines()+"");
                            buttons[i][j].setTextColor(f.getNumMines().getFontColor());
                            buttons[i][j].setBackgroundColor(0xFFD3D3D3);
                        }
                    }
                }
            }
        }
    }

    public boolean flag(int bt){
        if(field==null){
            return false;
        }
        int x = 0;
        int y = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(buttons[i][j].getId()==bt){
                    x = i;
                    y = j;
                    break;
                }
            }
        }
        Field f = field[x][y];

        if(f.isFlagged()){
            f.setFlagged(false);
            return false;
        }
        else{
            f.setFlagged(true);
            return true;
        }
    }

    private void checkForWin(String fromwhere){
        int counter2 = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(!field[i][j].isHidden() || field[i][j].isBomb()){
                    counter2++;
                }
            }
        }
        if(counter2==size*size){
            main.endGame(true);
        }
    }

}
