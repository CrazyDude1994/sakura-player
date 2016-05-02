package com.crazydude.sakuraplayer.events;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Created by Crazy on 21.11.2015.
 */
@Data
@AllArgsConstructor
public class NetworkErrorEvent {

    private String mTitle;
    private String mMessage;
}
