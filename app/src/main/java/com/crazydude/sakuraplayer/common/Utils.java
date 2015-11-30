package com.crazydude.sakuraplayer.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;

import com.crazydude.sakuraplayer.events.UpdateLibraryCompletedEvent;
import com.crazydude.sakuraplayer.events.UpdateLibraryStartedEvent;
import com.crazydude.sakuraplayer.models.PlaylistModel;
import com.crazydude.sakuraplayer.models.TrackModel;
import com.squareup.otto.Bus;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by CrazyDude on 14.03.2015.
 */
public class Utils {

    Context mContext;
    Bus mBus;

    public Utils(Context context, Bus bus) {
        this.mContext = context;
        this.mBus = bus;
    }

    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String MD5(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("MD5");
        byte[] md5hash;
        md.update(text.getBytes("UTF-8"), 0, text.length());
        md5hash = md.digest();
        return convertToHex(md5hash);
    }

    public PlaylistModel generateSingleTrackPlaylist(TrackModel model) {
        ArrayList<TrackModel> tracks = new ArrayList<>();
        tracks.add(model);
        return new PlaylistModel(tracks, "Current");
    }

    public void triggerMediaScan() {
        mContext.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED,
                Uri.parse("file://" + Environment.getExternalStorageDirectory())));
        MediaScanCompletedReceiver receiver = new MediaScanCompletedReceiver((s, uri) -> mBus.post(new UpdateLibraryCompletedEvent()));
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_MEDIA_SCANNER_FINISHED);
        intentFilter.addDataScheme("file");
        mContext.registerReceiver(receiver, intentFilter);
        mBus.post(new UpdateLibraryStartedEvent());
    }

    private class MediaScanCompletedReceiver extends BroadcastReceiver {

        private MediaScannerConnection.OnScanCompletedListener listener;

        public MediaScanCompletedReceiver(MediaScannerConnection.OnScanCompletedListener listener) {
            super();
            this.listener = listener;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            if (listener != null) {
                listener.onScanCompleted(null, null);
            }
            if (context != null) {
                context.unregisterReceiver(this);
            }
        }
    }

    public String getSignature(TreeMap<String, String> params) {
        params.put("api_key", Constants.LASTFM_API_KEY);
        String signature = "";
        for (String key : params.keySet()) {
            signature += key + params.get(key);
        }
        signature += Constants.LASTFM_API_SECRET;

        try {
            return Utils.MD5(signature);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
