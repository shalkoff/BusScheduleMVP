package ru.bus.raspisanie.shalk_off.raspisanie_bus.interactors;

import android.util.Log;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.App;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.db.dao.ScheduleDao;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainInteractor;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainPresenter;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.Schedule;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by pkorl on 25.03.2017.
 */

public class MainInteractorImpl implements MainInteractor {

    private MainPresenter presenter;
    private Subscription dbSubscription;
    private Subscription serverSubscription;
    private List<Schedule> listSchedule = new ArrayList<>();
    private boolean checkScheduleToBD = false;
    private ScheduleDao scheduleDao;

    public MainInteractorImpl(MainPresenter presenter) {
        this.presenter = presenter;
        try {
            scheduleDao = App.getHelper().getScheduleDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getScheduleInteractor() {
        checkScheduleToBD();
        if (checkScheduleToBD) {
            // Есть в БД расписание, отправляем данные во вьюху
            presenter.showResultPresenter(listSchedule);
        } else {
            // Данных нет в БД, скачиваем с сервера, добавляем в БД
            getScheduleFromServer();
            presenter.showResultPresenter(listSchedule);
        }
    }

    @Override
    public void destroyFragmentInteractor() {
        // Тут отписывается от всех подписок
        if (dbSubscription != null) {
            dbSubscription.unsubscribe();
        }
        if (serverSubscription != null) {
            serverSubscription.unsubscribe();
        }
    }

    // Получить расписание с сервера
    private void getScheduleFromServer() {
        Observable<ResponseSchedule> request = App.getAPIService().getSchedule("getAll");
        serverSubscription =
                request.subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .flatMap(new Func1<ResponseSchedule, Observable<Schedule>>() {
                            @Override
                            public Observable<Schedule> call(ResponseSchedule responseSchedule) {
                                return Observable.from(responseSchedule.bus);
                            }
                        }).subscribe(new Subscriber<Schedule>() {
                    @Override
                    public void onCompleted() {
                        saveScheduleToDB();
                    }

                    @Override
                    public void onError(Throwable e) {
                        presenter.showError("[ERROR]Загрузка расписания с сервера\n"+e.getMessage());
                    }

                    @Override
                    public void onNext(Schedule schedule) {
                        listSchedule.add(schedule);
                    }
                });
    }

    // Сохранить расписание в БД
    private void saveScheduleToDB() {
        try {
            if (scheduleDao != null) {
                scheduleDao.clearTable();
                for (int i = 0; i < listSchedule.size(); i++) {
                    scheduleDao.create(listSchedule.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            presenter.showError("[ERROR]Сохранение расписания в БД\n"+e.getMessage());
        }
    }


    // Проверяем наличие расписания в БД, а так же, если оно есть, то получаем его и записываем в переменную listSchedule
    private void checkScheduleToBD() {
        if (scheduleDao != null) {
            dbSubscription =
                    scheduleDao.getAllSchedule().subscribe(new Subscriber<List<Schedule>>() {
                                @Override
                                public void onCompleted() {
                                    if (listSchedule.size() == 0) {
                                        checkScheduleToBD = false;
                                    } else {
                                        checkScheduleToBD = true;
                                    }
                                }

                                @Override
                                public void onError(Throwable e) {
                                    checkScheduleToBD = false;
                                }

                                @Override
                                public void onNext(List<Schedule> schedules) {
                                    listSchedule = schedules;
                                }
                            });
        }
    }
}
