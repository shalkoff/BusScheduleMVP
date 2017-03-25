package ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import rx.Observable;

/**
 * Created by pkorl on 25.03.2017.
 */

public interface MainPresenter {

    void showResultPresenter(Observable<ResponseSchedule> result);
    void showError(String error);
    void getSchedule();
    void destroyFragment();

}
