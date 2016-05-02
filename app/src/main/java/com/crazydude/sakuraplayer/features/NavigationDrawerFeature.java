package com.crazydude.sakuraplayer.features;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 22.09.2015.
 */
@Data
@Accessors(prefix = "m")
@Builder
public class NavigationDrawerFeature implements Feature {

    private boolean mEnabled;

    @Override
    public FeatureEnum getFeatureType() {
        return FeatureEnum.NAVIGATION_DRAWER;
    }
}
