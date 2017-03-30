package ru.bus.raspisanie.shalk_off.raspisanie_bus.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.db.dao.ScheduleDao;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.Schedule;

/**
 * Created by pkorl on 30.03.2017.
 */

public class DBHelper extends OrmLiteSqliteOpenHelper {

    protected ScheduleDao scheduleDao = null;

    private static final String DATABASE_NAME = "ScheduleApp";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Schedule.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Schedule.class, true);
            onCreate(db, connectionSource);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ScheduleDao getScheduleDao() throws SQLException {
        if (scheduleDao == null) {
            scheduleDao = new ScheduleDao(getConnectionSource(), Schedule.class); // Получаем WeatherDao
        }
        return scheduleDao;
    }

    @Override
    public void close() {
        scheduleDao = null;
        super.close();
    }
}
