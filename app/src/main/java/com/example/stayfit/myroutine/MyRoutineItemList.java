package com.example.stayfit.myroutine;

import java.io.Serializable;

public class MyRoutineItemList implements Serializable {
    int img;
    String title;
    String btnText;

    public MyRoutineItemList(int img, String title, String btnText) {
        this.img = img;
        this.title = title;
        this.btnText = btnText;
    }

    public String getBtnText() {
        return btnText;
    }

    public void setBtnText(String btnText) {
        this.btnText = btnText;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "MyRoutineItemList{" +
                "img=" + img +
                ", title='" + title + '\'' +
                ", btnText='" + btnText + '\'' +
                '}';
    }
}
