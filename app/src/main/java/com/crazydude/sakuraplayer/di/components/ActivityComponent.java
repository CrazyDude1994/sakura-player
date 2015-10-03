package com.crazydude.sakuraplayer.di.components;

import com.crazydude.sakuraplayer.di.scopes.ActivityScope;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Crazy on 27.09.2015.
 */
@ActivityScope
@Component(dependencies = ApplicationComponent.class)
public interface ActivityComponent {
}
