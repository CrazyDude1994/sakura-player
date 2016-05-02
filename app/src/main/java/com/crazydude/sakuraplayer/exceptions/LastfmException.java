package com.crazydude.sakuraplayer.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by Crazy on 21.11.2015.
 */
@EqualsAndHashCode(callSuper = false)
@Data
@Accessors(prefix = "m")
@AllArgsConstructor
public class LastfmException extends RuntimeException {

    private String mMessage;
    private int mCode;
}
