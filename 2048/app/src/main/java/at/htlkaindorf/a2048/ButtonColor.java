package at.htlkaindorf.a2048;

import android.graphics.Color;

public enum ButtonColor {
    C0(0, 0xFFFFFFFF, 0xFFFF0000),
    C2(2, 0xFFFFFFFF, 0xFFFF0000),
    C4(4, 0xFFFFFFFF, 0xFFFF0000),
    C8(8, 0xFFFFFFFF, 0xFFFF0000),
    C16(16, 0xFFFFFFFF, 0xFFFF0000),
    C32(32, 0xFFFFFFFF, 0xFFFF0000),
    C64(64, 0xFFFFFFFF, 0xFFFF0000),
    C128(128, 0xFFFFFFFF, 0xFFFF0000),
    C256(256, 0xFFFFFFFF, 0xFFFF0000),
    C512(512, 0xFFFFFFFF, 0xFFFF0000),
    C1024(1024, 0xFFFFFFFF, 0xFFFF0000),
    C2048(2048, 0xFFFFFFFF, 0xFFFF0000);

    private int val;
    private int fontColor;
    private int backgroundColor;

    ButtonColor(int val, int fontColor, int backgroundcolor) {
        this.val = val;
        this.fontColor = fontColor;
        this.backgroundColor = backgroundcolor;
    }

    public int getVal() {
        return val;
    }

    public int getFontColor() {
        return fontColor;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }
}
