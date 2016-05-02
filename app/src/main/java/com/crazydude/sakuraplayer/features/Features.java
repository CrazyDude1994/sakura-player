package com.crazydude.sakuraplayer.features;

import android.support.annotation.NonNull;

import java.util.HashSet;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 11.08.2015.
 */
@Data
@Accessors(prefix = "m")
public class Features {

    private HashSet<Feature> mFeatures;

    public Features(HashSet<Feature> features) {
        this.mFeatures = features;
    }

    public static class FeaturesBuilder {

        private HashSet<Feature> features;

        public FeaturesBuilder() {
            this.features = new HashSet<>();
        }

        public FeaturesBuilder addFeature(@NonNull Feature feature) {
            this.features.add(feature);
            return this;
        }

        public Features build() {
            return new Features(this.features);
        }
    }
}