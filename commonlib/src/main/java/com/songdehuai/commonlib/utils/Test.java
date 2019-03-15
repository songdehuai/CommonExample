package com.songdehuai.commonlib.utils;


class DataCenter {


    String name = "111";

    Observable<String> shopName = new Observable<String>("sss");
}


class Test {

    DataCenter center = new DataCenter();

    void function() {
//        DataCenter dataCenter = new DataCenter();
        center.shopName.addNotice("", new Notice() {
            @Override
            public void notice(Object value) {
                
            }
        });
    }

    void click(){
        center.shopName.setValue("3456789");
    }



}

