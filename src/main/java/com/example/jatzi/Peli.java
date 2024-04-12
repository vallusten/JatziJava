package com.example.jatzi;
import java.io.Serializable;
import java.util.Date;

public class Peli implements Serializable{
    private int huippupisteet;
    private String nimimerkki;
    private Date pvm;

    public int getHuippupisteet() {
        return huippupisteet;
    }

    public void setHuippupisteet(int huippupisteet) {
        this.huippupisteet = huippupisteet;
    }

    public String getNimimerkki() {
        return nimimerkki;
    }

    public void setNimimerkki(String nimimerkki) {
        this.nimimerkki = nimimerkki;
    }

    public Date getPvm() {
        return pvm;
    }

    public void setPvm(Date pvm) {
        this.pvm = pvm;
    }

    public Peli(int huippupisteet, String nimimerkki, Date pvm){
        this.huippupisteet = huippupisteet;
        this.nimimerkki = nimimerkki;
        this.pvm = pvm;
    }
}
