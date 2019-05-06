package com.songdehuai.commonexample.ui;

public class Myentity {
    private String str;
    private boolean isSelect;

    public Myentity() {
    }

    public Myentity(String str, boolean isSelect) {
        this.str = str;
        this.isSelect = isSelect;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
