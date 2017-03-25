package ru.bus.raspisanie.shalk_off.raspisanie_bus.presenters;

import java.util.List;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.interactors.MainInteractorImpl;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainInteractor;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainPresenter;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainView;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
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
    private Subscription mainFragmentSubscription;

    public MainPresenterImpl(MainView view) {
        this.view = view;
        interactor = new MainInteractorImpl(this);
    }

    @Override
    public void showResultPresenter(Observable<ResponseSchedule> result) {
        mainFragmentSubscription =
                result.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<ResponseSchedule>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {

                            }

                            @Override
                            public void onNext(ResponseSchedule responseSchedule) {
                                responseSchedule.toString();
                            }
                        });
        if (view == null) {
            //view.showResult(result);
        }

    }

    @Override
    public void showError(String error) {
        if (view == null) {
            view.showError(error);
        }
    }

    @Override
    public void getSchedule() {
        interactor.getScheduleInteractor();
    }

    @Override
    public void destroyFragment() {
        if (mainFragmentSubscription != null) {
            mainFragmentSubscription.unsubscribe();
        }
    }
}
