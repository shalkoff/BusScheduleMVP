package ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by pkorl on 25.03.2017.
 */

public class ResponseVersion {

    @SerializedName("version")
    @Expose
    public String version;
}
