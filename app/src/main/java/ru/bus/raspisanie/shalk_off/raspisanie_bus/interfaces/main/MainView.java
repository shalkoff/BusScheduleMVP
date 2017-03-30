package ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main;

import java.util.List;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.Schedule;

/**
 * Created by pkorl on 25.03.2017.
 */

public interface MainView {

    void showProgress(boolean flag);
    void showError(String error);
    void showResult(List<Schedule> result);
}
