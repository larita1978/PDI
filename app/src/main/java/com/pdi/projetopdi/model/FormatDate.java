package com.pdi.projetopdi.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FormatDate {

    private String data;
    private long dataLong;
    private SimpleDateFormat sdf = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss", Locale.US);

    public FormatDate(){}

    public FormatDate(String data){
        this.data = data;
    }

    public FormatDate(long dataLong){
        this.data = String.valueOf(dataLong);
    }

    public String getDataLong() throws ParseException {
        return sdf.format(dataLong);
    }

    public void setDataLong(long dataLong) {
        this.dataLong = dataLong;
    }


    public String getData() {
        return sdf.format(data);
    }

    public void setData(String data) {
        this.data = data;
    }
}
