package ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main;

import java.util.List;

/**
 * Created by pkorl on 25.03.2017.
 */

public interface MainView {

    void showProgress(boolean flag);
    void showError(String error);
    void showResult(List<Object> result);
}
