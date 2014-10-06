package br.com.goncalves.pugnotification.model;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.net.Uri;

/**
 * @author halysonlimagoncalves
 */
public class NotificationBean {
    private int identifier;
    private String title;
    private String message;
    private String bigTextStyle;
    private String ticker;
    private int background;
    private int smallIcon;
    private int colorLight;
    private int ledOnMs;
    private int ledOffMs;
    private long when;
    private long[] vibratorTime;
    private boolean autoCancel;
    private Bitmap largeIcon;
    private Uri sound;
    private PendingIntent clickPendingIntent;
    private PendingIntent dismissPendingIntent;

    public NotificationBean() {
        super();
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBigTextStyle() {
        return bigTextStyle;
    }

    public void setBigTextStyle(String bigTextStyle) {
        this.bigTextStyle = bigTextStyle;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public void setSmallIcon(int smallIcon) {
        this.smallIcon = smallIcon;
    }

    public int getColorLight() {
        return colorLight;
    }

    public void setColorLight(int colorLight) {
        this.colorLight = colorLight;
    }

    public int getLedOnMs() {
        return ledOnMs;
    }

    public void setLedOnMs(int ledOnMs) {
        this.ledOnMs = ledOnMs;
    }

    public int getLedOffMs() {
        return ledOffMs;
    }

    public void setLedOffMs(int ledOffMs) {
        this.ledOffMs = ledOffMs;
    }

    public long getWhen() {
        return when;
    }

    public void setWhen(long when) {
        this.when = when;
    }

    public long[] getVibratorTime() {
        return vibratorTime;
    }

    public void setVibratorTime(long[] vibratorTime) {
        this.vibratorTime = vibratorTime;
    }

    public boolean isAutoCancel() {
        return autoCancel;
    }

    public void setAutoCancel(boolean autoCancel) {
        this.autoCancel = autoCancel;
    }

    public Bitmap getLargeIcon() {
        return largeIcon;
    }

    public void setLargeIcon(Bitmap largeIcon) {
        this.largeIcon = largeIcon;
    }

    public Uri getSound() {
        return sound;
    }

    public void setSound(Uri sound) {
        this.sound = sound;
    }

    public PendingIntent getClickPendingIntent() {
        return clickPendingIntent;
    }

    public void setClickPendingIntent(PendingIntent clickPendingIntent) {
        this.clickPendingIntent = clickPendingIntent;
    }

    public PendingIntent getDismissPendingIntent() {
        return dismissPendingIntent;
    }

    public void setDismissPendingIntent(PendingIntent dismissPendingIntent) {
        this.dismissPendingIntent = dismissPendingIntent;
    }
}
