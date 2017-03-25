package ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen.main;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.R;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainPresenter;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.interfaces.main.MainView;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.presenters.MainPresenterImpl;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.base.BaseFragment;

/**
 * Created by pkorl on 14.03.2017.
 */

public class MainFragment extends BaseFragment implements MainView {

    private View rootView;
    private ProgressBar progressBar;
    private MainPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initViews();
        presenter = new MainPresenterImpl(this);
        presenter.getSchedule();
        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.destroyFragment();
    }

    private void initViews() {
        progressBar = (ProgressBar) rootView.findViewById(R.id.progress_bar);
    }

    @Override
    public void showProgress(boolean flag) {
        if (flag) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showResult(List<Object> result) {
        // Тут получаем исходные данные и заполняем Recycler
    }

}
