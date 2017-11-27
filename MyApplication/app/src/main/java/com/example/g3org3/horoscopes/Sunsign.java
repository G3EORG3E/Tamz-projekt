package com.example.g3org3.horoscopes;

import java.io.Serializable;

/**
 * Created by G3ORG3 on 25.11.2017.
 */

public class Sunsign implements Serializable {
    int id;
    String horoscope;
    String intensity;
    String mood;
    String keywords;
    String date;
    String sunsign;
    String note = "";

    public Sunsign() {
        this.horoscope = "";
        this.intensity = "";
        this.mood = "";
        this.keywords = "";
        this.date = "";
        this.sunsign = "";
    }

    public Sunsign(String horoscope, String intensity, String mood, String keywords, String date, String sunsign) {
        this.horoscope = horoscope;
        this.intensity = intensity;
        this.mood = mood;
        this.keywords = keywords;
        this.date = date;
        this.sunsign = sunsign;
    }

    public Sunsign(int id, String horoscope, String intensity, String mood, String keywords, String date, String sunsign, String note) {
        this.id = id;
        this.horoscope = horoscope;
        this.intensity = intensity;
        this.mood = mood;
        this.keywords = keywords;
        this.date = date;
        this.sunsign = sunsign;
        this.note = note;
    }

    protected void setAttrs(String horoscope, String intensity, String mood, String keywords, String date, String sunsign) {
        this.horoscope = horoscope;
        this.intensity = intensity;
        this.mood = mood;
        this.keywords = keywords;
        this.date = date;
        this.sunsign = sunsign;
    }

    public int getId() {
        return id;
    }

    public String getHoroscope() {
        return horoscope;
    }

    public String getIntensity() {
        return intensity;
    }

    public String getMood() {
        return mood;
    }

    public String getKeywords() {
        return keywords;
    }

    public String getDate() {
        return date;
    }

    public String getSunsign() {
        return sunsign;
    }

    public String getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHoroscope(String horoscope) {
        this.horoscope = horoscope;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setSunsign(String sunsign) {
        this.sunsign = sunsign;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
