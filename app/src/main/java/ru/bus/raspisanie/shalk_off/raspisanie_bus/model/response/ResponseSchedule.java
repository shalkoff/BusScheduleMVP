package ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.db.dao.ScheduleDao;

/**
 * Created by pkorl on 25.03.2017.
 */
public class ResponseSchedule implements Serializable {
    @SerializedName("bus")
    @Expose
    public List<Schedule> bus = new ArrayList<>();

}
