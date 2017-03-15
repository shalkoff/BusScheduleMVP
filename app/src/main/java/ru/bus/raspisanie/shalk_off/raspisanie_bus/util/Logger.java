package ru.bus.raspisanie.shalk_off.raspisanie_bus.util;

import android.util.Log;

/**
 * Created by pkorl on 15.03.2017.
 */

public class Logger {

    public static void app(String message) {
        Log.d(Const.APP_TAG, message);
    }

    public static void rest(String message) {
        Log.d(Const.REST_TAG, message);
    }
}
