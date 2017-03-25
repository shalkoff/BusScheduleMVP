package ru.bus.raspisanie.shalk_off.raspisanie_bus.model.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pkorl on 25.03.2017.
 */

public class RequestNewUser {

    @SerializedName("multicast_id")
    @Expose
    public String multicastId;
    @SerializedName("success")
    @Expose
    public String success;
    @SerializedName("failure")
    @Expose
    public String failure;
    @SerializedName("canonical_ids")
    @Expose
    public String canonicalIds;
    @SerializedName("results")
    @Expose
    public List<Result> results = new ArrayList<Result>();

    public class Result {

        @SerializedName("error")
        @Expose
        public String error;

    }
}
