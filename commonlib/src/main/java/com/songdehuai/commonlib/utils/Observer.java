package com.songdehuai.commonlib.utils;


public class Observer<T> {

    public Object target;
    public Notice<T> notice;

    public Observer(Object target, Notice<T> notice) {
        super();
        this.target = target;
        this.notice = notice;

    }

}

interface Notice<T> {
    public void notice(T value);
}




