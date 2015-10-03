package com.crazydude.sakuraplayer.interfaces;


import com.crazydude.sakuraplayer.features.Features;

import static com.crazydude.sakuraplayer.features.Features.*;

/**
 * Created by kartavtsev.s on 10.09.2015.
 */
public interface SwitchableFragment {

    Features requestFeatures(FeaturesBuilder builder);
}
