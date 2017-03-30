package ru.bus.raspisanie.shalk_off.raspisanie_bus.db.dao;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.Schedule;
import rx.Observable;

/**
 * Created by pkorl on 30.03.2017.
 */

public class ScheduleDao extends BaseDaoImpl<Schedule, Integer> {

    public ScheduleDao(ConnectionSource connectionSource, Class<Schedule> weatherClass) throws SQLException {
        super(connectionSource, weatherClass);
    }

    public Observable<List<Schedule>> getAllSchedule() {
        return Observable.fromCallable(new Callable<List<Schedule>>() {
            public List<Schedule> call() throws Exception {
                return ScheduleDao.this.queryForAll();
            }
        });
    }

    public void clearTable() {
        try {
            TableUtils.clearTable(connectionSource, Schedule.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
