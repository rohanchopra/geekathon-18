package com.choprarohan.litrary;

/**
 * Created by Rohan Chopra on 1/7/18.
 */

public class Transaction {

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    private String imgLink;
    private String days;
    private String Title;

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    private int flag;


    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    private String url;

}

