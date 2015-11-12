package com.crazydude.sakuraplayer.di.components;

import com.crazydude.sakuraplayer.di.modules.ManagersModule;
import com.crazydude.sakuraplayer.di.modules.ServiceModule;
import com.crazydude.sakuraplayer.services.PlayerService;

import dagger.Subcomponent;

/**
 * Created by Crazy on 12.11.2015.
 */
@Subcomponent(modules = ServiceModule.class)
public interface ServiceComponent {
    void inject(PlayerService playerService);
}
