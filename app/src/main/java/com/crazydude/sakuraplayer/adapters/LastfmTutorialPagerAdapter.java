package com.crazydude.sakuraplayer.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.crazydude.sakuraplayer.gui.fragments.LastfmTutorialTextFragment;


/**
 * Created by kartavtsev.s on 28.05.2015.
 */
public class LastfmTutorialPagerAdapter extends FragmentPagerAdapter {

    private static final String[] TUTORIAL_TEXTS = {"Добро пожаловать в приложение Sakura Player!",
            "Этот плеер был создан для людей которые не могут жить без музыки",
            "Хоть данный плеер и не требует наличие профиля в музыкальной социальной сети LastFM, но я настоятельно рекомендую Вам зарегистрироваться на LastFM",
            "LastFM - это не просто социальная сеть, это огромная база данных о всех твоих любимых музыкальных группах и исполнителей",
            "С помощью него Вы можете: читать новости в сфере музыки, находить новые группы в зависимости от твоих вкусов, а так-же сопоставлять свои музыкальные вкусы со своими друзьями",
            "Вливайся! И ты откроешь для себя лучшие колективы."
    };

    public LastfmTutorialPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new LastfmTutorialTextFragment();
    }

    @Override
    public int getCount() {
        return TUTORIAL_TEXTS.length;
    }
}
