package com.crazydude.sakuraplayer.di.components;

import com.crazydude.sakuraplayer.di.modules.ActivityModule;
import com.crazydude.sakuraplayer.di.modules.ApplicationModule;
import com.crazydude.sakuraplayer.di.modules.ServiceModule;
import com.crazydude.sakuraplayer.di.scopes.ApplicationScope;

import dagger.Component;

/**
 * Created by Crazy on 27.09.2015.
 */
@Component(modules = ApplicationModule.class)
@ApplicationScope
public interface ApplicationComponent {

    ActivityComponent provideActivityComponent(ActivityModule activityModule);
    ServiceComponent provideServiceComponent(ServiceModule serviceModule);

}
