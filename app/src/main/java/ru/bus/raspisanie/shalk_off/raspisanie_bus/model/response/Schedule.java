package ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.db.dao.ScheduleDao;

/**
 * Created by pkorl on 30.03.2017.
 */

@DatabaseTable(tableName = Schedule.TABLE_NAME_SCHEDULE, daoClass = ScheduleDao.class)
public class Schedule implements Serializable {

    public static final String TABLE_NAME_SCHEDULE = "schedule_table";

    public static final String FIELD_NAME_ID = "id";
    public static final String ID_AVTO = "idAvto";
    public static final String NOMER_AVTO = "nomerAvto";
    public static final String NAME1 = "name1";
    public static final String NAME2 = "name2";
    public static final String V_GOROD = "v_gorod";
    public static final String S_GORODA = "s_goroda";
    public static final String COLOR = "color";
    public static final String PRICE = "price";
    public static final String PRICE_MONEY = "priceMoney";
    public static final String DETALS = "detals";
    public Schedule() {

    }

    @DatabaseField(columnName = FIELD_NAME_ID, generatedId = true)
    private int id;

    @DatabaseField(columnName = ID_AVTO)
    @SerializedName("idAvto")
    @Expose
    public String idAvto;

    @DatabaseField(columnName = NOMER_AVTO)
    @SerializedName("nomerAvto")
    @Expose
    public String nomerAvto;

    @DatabaseField(columnName = NAME1)
    @SerializedName("name1")
    @Expose
    public String name1;

    @DatabaseField(columnName = NAME2)
    @SerializedName("name2")
    @Expose
    public String name2;

    @DatabaseField(columnName = V_GOROD)
    @SerializedName("v_gorod")
    @Expose
    public String vGorod;

    @DatabaseField(columnName = S_GORODA)
    @SerializedName("s_goroda")
    @Expose
    public String sGoroda;

    @DatabaseField(columnName = COLOR)
    @SerializedName("color")
    @Expose
    public String color;

    @DatabaseField(columnName = PRICE)
    @SerializedName("price")
    @Expose
    public String price;

    @DatabaseField(columnName = PRICE_MONEY)
    @SerializedName("priceMoney")
    @Expose
    public String priceMoney;

    @DatabaseField(columnName = DETALS)
    @SerializedName("detals")
    @Expose
    public String detals;
}