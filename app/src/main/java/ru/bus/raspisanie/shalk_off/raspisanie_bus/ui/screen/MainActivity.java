package ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ru.bus.raspisanie.shalk_off.raspisanie_bus.R;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen.fare.FareFragment;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen.info.InfoFragment;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen.main.MainFragment;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen.schedule.ScheduleFragment;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.ui.screen.settings.SettingsFragment;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.util.Const;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.util.Logger;


public class MainActivity extends AppCompatActivity {

    private void init() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        openFragment(Const.MAIN_FRAGMENT);
    }

    private void openFragment(String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (fragment == null) {
            if (tag.equals(Const.MAIN_FRAGMENT)) {
                fragment = new MainFragment();
            }
            if (tag.equals(Const.FARE_FRAGMENT)) {
                fragment = new FareFragment();
            }
            if (tag.equals(Const.INFO_FRAGMENT)) {
                fragment = new InfoFragment();
            }
            if (tag.equals(Const.SCHEDULE_FRAGMENT)) {
                fragment = new ScheduleFragment();
            }
            if (tag.equals(Const.SETTINGS_FRAGMENT)) {
                fragment = new SettingsFragment();
            }
            Logger.app("Инициализация фрагмента: " + tag);
        }
        Logger.app("Открытие фрагмента: " + tag);
        fragmentTransaction.replace(R.id.fragment_container,fragment,tag);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
