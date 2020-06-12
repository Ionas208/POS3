package at.htlkaindorf.minesweeper.beans;

public class Field {
    private boolean isBomb;
    private NumMines numMines;
    private boolean isHidden;
    private boolean isFlagged;

    public Field(boolean isBomb, NumMines numMines, boolean isHidden, boolean isFlagged) {
        this.isBomb = isBomb;
        this.numMines = numMines;
        this.isHidden = isHidden;
        this.isFlagged = isFlagged;
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
    }

    public boolean isBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public NumMines getNumMines() {
        return numMines;
    }

    public void setNumMines(NumMines numMines) {
        this.numMines = numMines;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public void setFlagged(boolean flagged) {
        isFlagged = flagged;
    }
}
