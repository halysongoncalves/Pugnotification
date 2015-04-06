package br.com.goncalves.pugnotification.interfaces;

/**
 * Created by ramdac on 4/5/15.
 */
public interface ImageLoader {
    void load(String uri, OnImageLoadingCompleted onCompleted);

    void load(int imageResId, OnImageLoadingCompleted onCompleted);
}
