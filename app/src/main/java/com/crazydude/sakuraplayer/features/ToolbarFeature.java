package com.crazydude.sakuraplayer.features;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 10.09.2015.
 */
@Data
@Accessors(prefix = "m")
@Builder
public class ToolbarFeature implements Feature {

    private boolean mIsBackButton;
    private boolean mSetVisible;
    private String mTitle;
    private Integer mTitleRes;

    @Override
    public FeatureEnum getFeatureType() {
        return FeatureEnum.TOOLBAR;
    }
}
