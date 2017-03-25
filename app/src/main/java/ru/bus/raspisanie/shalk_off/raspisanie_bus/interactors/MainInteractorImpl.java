package ru.bus.raspisanie.shalk_off.raspisanie_bus.interactors;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.App;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainInteractor;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainPresenter;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pkorl on 25.03.2017.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainPresenter presenter;

    public MainInteractorImpl(MainPresenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void getScheduleInteractor() {
         Observable<ResponseSchedule> request = App.getAPIService().getSchedule("getAll");
         presenter.showResultPresenter(request);
    }
}
