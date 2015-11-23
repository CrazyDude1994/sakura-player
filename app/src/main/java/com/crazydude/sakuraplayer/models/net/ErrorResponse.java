package com.crazydude.sakuraplayer.models.net;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by kartavtsev.s on 02.06.2015.
 */
@Data
@Accessors(prefix = "m")
public class ErrorResponse {

    private Integer mError;
    private String mMessage;
}
