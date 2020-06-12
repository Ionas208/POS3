package at.htlkaindorf.minesweeper.beans;

public enum NumMines {
    Def(-1, -1),
    B0(0, 0xFF6C6C6C),
    B1(1, 0xFF0046FF),
    B2(2, 0xFF13A000),
    B3(3, 0xFFFF0000),
    B4(4, 0xFF5600A3),
    B5(5, 0xFFFBFF00),
    B6(6, 0xFFFFA600),
    B7(7, 0xFFE400FF),
    B8(8, 0xFF00FFC9),
    BOMB(-1, -1);

    private int mines;
    private int fontColor;

    NumMines(int mines, int fontColor) {
        this.mines = mines;
        this.fontColor = fontColor;
    }

    public int getMines() {
        return mines;
    }

    public int getFontColor() {
        return fontColor;
    }
}
