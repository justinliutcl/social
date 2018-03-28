package com.justin.social.accessor;

import android.content.Context;

/**
 * Created by Justinliu on 2018/2/7.
 */

public class CommonSettingValue extends GlobPre {

    private static CommonSettingValue commonValue;

    public static synchronized CommonSettingValue getIns(Context context) {
        if (commonValue == null) {
            commonValue = new CommonSettingValue(context.getApplicationContext());
        }
        return commonValue;
    }
    private static final String KEY_SOUND_CLOUD_ENABLE  = "key_sound_cloud_enable";
    private static final String KEY_MUSIC_UPDATE        = "key_music_update";
    protected CommonSettingValue(Context context) {
        super(context);
    }

    public void setSoundCloudEnable(boolean enable) {
        putBoolean(KEY_SOUND_CLOUD_ENABLE, enable);
    }

    public boolean isSoundClodEnable() {
        return getBoolean(KEY_SOUND_CLOUD_ENABLE, false);
    }

    public void setMusicUpdate(boolean enable) {
        putBoolean(KEY_MUSIC_UPDATE, enable);
    }

    public boolean isMusicUpdate() {
        return getBoolean(KEY_MUSIC_UPDATE, false);
    }
}
