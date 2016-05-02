package com.crazydude.sakuraplayer.features;

/**
 * Created by kartavtsev.s on 22.09.2015.
 */
public class FeatureIsNullException extends RuntimeException {

    public FeatureIsNullException() {
        super("Features is null. Please provide requested features!");
    }
}
