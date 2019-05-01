package com.songdehuai.commonexample.ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TestC {

    public static int getIntData(String oldData) {
        int newData = 0;
        if (oldData != null && !oldData.equals("") && !oldData.isEmpty()) {
            newData = Integer.valueOf(oldData.trim());
        }
        return newData;
    }

    public static void main(String[] a) {
        SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null;
        try {
            date = sp.parse("2019-04-29 23:00:59");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar test = Calendar.getInstance();
//        test.setTime(date);
        test.add(Calendar.HOUR_OF_DAY, 2);
        test.add(Calendar.MINUTE, 1);

        System.out.println(test.get(Calendar.YEAR) + "&&" +
                (test.get(Calendar.MONTH) + 1) + "&&" +
                test.get(Calendar.DAY_OF_MONTH) + "&&" +
                test.get(Calendar.HOUR_OF_DAY) + "&&" +
                test.get(Calendar.MINUTE));
    }

}
