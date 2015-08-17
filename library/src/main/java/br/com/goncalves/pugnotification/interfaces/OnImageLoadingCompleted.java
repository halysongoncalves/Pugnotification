package br.com.goncalves.pugnotification.interfaces;

import android.graphics.Bitmap;

/**
 * Created by ramdac on 4/5/15.
 */
public interface OnImageLoadingCompleted {
    /**
     * Call this method when you finish loading the Image via ImageLoader
     *
     * @param bitmap the bitmap that you would like to report being completed
     */
    void imageLoadingCompleted(Bitmap bitmap);
}
