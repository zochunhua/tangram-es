package com.mapzen.tangram.viewholder;

import android.content.Context;
import android.util.Log;
import android.view.TextureView;

import androidx.annotation.NonNull;

import com.mapzen.tangram.BuildConfig;

public class TextureViewHolderFactory implements GLViewHolderFactory {
    /**
     * Responsible to create an instance of {@link TextureViewHolder} which holds
     * an instance of {@link TextureView} for map display.
     * @param context Application Context
     * @return {@link TextureViewHolder}
     */
    @Override
    @NonNull
    public GLViewHolder build(Context context) {
        TextureView textureView = new TextureView(context);
        TextureViewHolder textureViewHolder = new TextureViewHolder(textureView);

        textureViewHolder.setEGLContextClientVersion(2);
        textureViewHolder.setPreserveEGLContextOnPause(true);
        try {
            textureViewHolder.setEGLConfigChooser(new ConfigChooser(8, 8, 8, 0, 16, 8));
            return textureViewHolder;
        } catch(IllegalArgumentException e) {
            // TODO: print available configs to check whether we could support them
            Log.e(BuildConfig.TAG, "EGLConfig 8-8-8-0 not supported");
        }
        try {
            textureViewHolder.setEGLConfigChooser(new ConfigChooser(8, 8, 8, 8, 16, 8));
            return textureViewHolder;
        } catch(IllegalArgumentException e) {
            Log.e(BuildConfig.TAG, "EGLConfig 8-8-8-8 not supported");
        }
        try {
            textureViewHolder.setEGLConfigChooser(new ConfigChooser(5, 6, 5, 0, 16, 8));
            return textureViewHolder;
        } catch(IllegalArgumentException e) {
            Log.e(BuildConfig.TAG, "EGLConfig 5-6-5-0 not supported");
        }
        return null;
    }
}
