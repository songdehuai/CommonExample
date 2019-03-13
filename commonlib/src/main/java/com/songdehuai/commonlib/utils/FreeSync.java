package com.songdehuai.commonlib.utils;

import android.util.ArrayMap;

import java.util.ArrayList;
import java.util.LinkedHashMap;


/**
 * @author songdehuai
 */
public class FreeSync {

    /**
     * 默认本类名存储key，自定义key避免使用此名称
     */
    private static final String DEFAULTFREESYNCNAME = "FREESYNC_DEFAULTFREESYNCNAME";

    private LinkedHashMap<String, ArrayList<FreeSyncCallback>> freeSyncCallbackHashMap = new LinkedHashMap<>();
    private static ArrayMap<String, FreeSync> freeSyncArrayMap = new ArrayMap<>();
    private static FreeSync freeSync = new FreeSync();

    private FreeSync() {

    }

    public static FreeSync defaultFreeSync() {
        freeSyncArrayMap.put(DEFAULTFREESYNCNAME, freeSync);
        return freeSync;
    }

    public static FreeSync freeSyncWithName(String name) {
        if (freeSyncArrayMap.containsKey(name)) {
            return freeSyncArrayMap.get(name);
        } else {
            freeSyncArrayMap.put(name, new FreeSync());
            return freeSyncArrayMap.get(name);
        }
    }

    public void addCallBack(String name, FreeSyncCallback freeSyncCallback) {
        boolean isHas = false;
        for (String s : freeSyncCallbackHashMap.keySet()) {
            if (name.equals(s)) {
                freeSyncCallbackHashMap.get(s).add(freeSyncCallback);
                isHas = true;
            }
        }
        if (!isHas) {
            ArrayList<FreeSyncCallback> callBackList = new ArrayList<>();
            callBackList.add(freeSyncCallback);
            freeSyncCallbackHashMap.put(name, callBackList);
        }
    }

    public void callBack(String name, Object object) {
        ArrayList<FreeSyncCallback> callBackList;
        for (String s : freeSyncCallbackHashMap.keySet()) {
            if (name.equals(s)) {
                callBackList = freeSyncCallbackHashMap.get(s);
                for (int i = 0; i < callBackList.size(); i++) {
                    callBackList.get(i).onCallBack(name, object);
                }
            }
        }
    }

    public void callBack(String name) {
        ArrayList<FreeSyncCallback> callBackList;
        for (String s : freeSyncCallbackHashMap.keySet()) {
            if (name.equals(s)) {
                callBackList = freeSyncCallbackHashMap.get(s);
                for (int i = 0; i < callBackList.size(); i++) {
                    callBackList.get(i).onCallBack(name, "");
                }
            }
        }
    }

    public void removeCallBack(String name) {
        for (String s : freeSyncCallbackHashMap.keySet()) {
            if (name.equals(s)) {
                freeSyncCallbackHashMap.remove(name);
            }
        }
    }

    public void removeFreeSync(String name) {
        for (String s : freeSyncArrayMap.keySet()) {
            if (name.equals(s)) {
                freeSyncArrayMap.remove(name);
            }
        }
    }

    public void clearAllDefault() {
        freeSyncCallbackHashMap.clear();
    }

    public void clearAllWithName(String name) {
        FreeSync freeSync = freeSyncWithName(name);
        if (freeSync != null) {
            freeSync.freeSyncCallbackHashMap.clear();
        }
    }

    public interface FreeSyncCallback {
        void onCallBack(String name, Object object);
    }
}
