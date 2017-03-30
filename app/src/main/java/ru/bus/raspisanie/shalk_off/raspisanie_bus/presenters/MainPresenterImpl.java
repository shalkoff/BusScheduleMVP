package ru.bus.raspisanie.shalk_off.raspisanie_bus.presenters;

import java.util.List;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.interactors.MainInteractorImpl;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainInteractor;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainPresenter;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainView;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.Schedule;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by pkorl on 25.03.2017.
 */

public class MainPresenterImpl implements MainPresenter {

    private MainView view;
    private MainInteractor interactor;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        interactor = new MainInteractorImpl(this);
    }

    @Override
    public void showResultPresenter(List<Schedule> result) {
        if (view != null) {
            view.showProgress(false);
            view.showResult(result);
        }

    }

    @Override
    public void showError(String error) {
        if (view != null) {
            view.showProgress(false);
            view.showError(error);
        }
    }

    @Override
    public void getSchedule() {
        if (view != null) {
            view.showProgress(true);
            interactor.getScheduleInteractor();
        }
    }

    @Override
    public void destroyFragment() {

    }
}
