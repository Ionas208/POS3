package at.htlkaindorf.zodiacsign.bl;

import java.time.MonthDay;
import java.util.ArrayList;
import java.util.List;

import at.htlkaindorf.zodiacsign.R;

public class ZodiacSign {

    private String name;
    private MonthDay startDate;
    private int picutreID;


    public ZodiacSign(String name, MonthDay startDate, int picutreID) {
        this.name = name;
        this.startDate = startDate;
        this.picutreID = picutreID;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MonthDay getStartDate() {
        return startDate;
    }

    public void setStartDate(MonthDay startDate) {
        this.startDate = startDate;
    }

    public int getPicutreID() {
        return picutreID;
    }

    public void setPicutreID(int picutreID) {
        this.picutreID = picutreID;
    }


    public static List<ZodiacSign> getZodiacSignList(){
        List<ZodiacSign> list = new ArrayList<>();
        list.add(new ZodiacSign("Aries", MonthDay.of(3,21), R.drawable.aries));
        list.add(new ZodiacSign("Taurus", MonthDay.of(4,20), R.drawable.taurus));
        list.add(new ZodiacSign("Gemini", MonthDay.of(5,21), R.drawable.gemini));
        list.add(new ZodiacSign("Cancer", MonthDay.of(6,21), R.drawable.cancer));
        list.add(new ZodiacSign("Leo", MonthDay.of(7,23), R.drawable.leo));
        list.add(new ZodiacSign("Virgo", MonthDay.of(8,23), R.drawable.virgo));
        list.add(new ZodiacSign("Libra", MonthDay.of(9,1), R.drawable.libra));
        list.add(new ZodiacSign("Scorpio", MonthDay.of(10,23), R.drawable.scorpius));
        list.add(new ZodiacSign("Sagittarius", MonthDay.of(11,22), R.drawable.sagittarius));
        list.add(new ZodiacSign("Capricorn", MonthDay.of(12,22), R.drawable.capricornus));
        list.add(new ZodiacSign("Aquarius", MonthDay.of(1,20), R.drawable.aquarius));
        list.add(new ZodiacSign("Pisces", MonthDay.of(2,19), R.drawable.pisces));
        return list;
    }
}
