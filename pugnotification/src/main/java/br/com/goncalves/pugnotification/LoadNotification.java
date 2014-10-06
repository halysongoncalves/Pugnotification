package br.com.goncalves.pugnotification;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import br.com.goncalves.pugnotification.interfaces.PendingIntentNotification;
import br.com.goncalves.pugnotification.pendingintent.ClickPendingIntentActivity;
import br.com.goncalves.pugnotification.pendingintent.ClickPendingIntentBroadCast;
import br.com.goncalves.pugnotification.pendingintent.DismissPendingIntentActivity;
import br.com.goncalves.pugnotification.pendingintent.DismissPendingIntentBroadCast;
import br.com.goncalves.pugnotification.utils.Utils;

public class LoadNotification {
    private static final String TAG = LoadNotification.class.getSimpleName();
    private final Notifications mNotification;
    private Builder mBuilder;
    private int mIdentifier;
    private String mTitle;
    private String mMessage;
    private int mSmallIcon;

    public LoadNotification(Notifications notification) {
        this.mNotification = Utils.isActiveSingleton(notification);
        this.mBuilder = new NotificationCompat.Builder(mNotification.mContext);
        this.createNotifationDefault();
    }

    private void createNotifationDefault() {
        this.mBuilder.setContentTitle("");
        this.mBuilder.setContentText("");
        this.mBuilder.setSmallIcon(R.drawable.pugnotification_ic_launcher);
        this.mBuilder.setLargeIcon(BitmapFactory.decodeResource(mNotification.mContext.getResources(),
                R.drawable.pugnotification_ic_launcher));
    }

    public LoadNotification identifier(int identifier) {
        if (identifier <= 0) {
            throw new IllegalStateException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mIdentifier = identifier;
        return this;
    }

    public LoadNotification title(int title) {
        if (title <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (mTitle != null) {
            throw new IllegalStateException("Title Already Set!");
        }

        this.mTitle = mNotification.mContext.getResources().getString(title);
        this.mBuilder.setContentTitle(mTitle);
        return this;
    }

    public LoadNotification title(String title) {
        if (mTitle != null) {
            throw new IllegalStateException("Title Already Set!");
        }

        if (title == null) {
            throw new IllegalStateException("Title Must Not Be Null!");
        }

        if (title.trim().length() == 0) {
            throw new IllegalArgumentException("Path Must Not Be Empty!");
        }

        this.mTitle = title;
        this.mBuilder.setContentTitle(mTitle);
        return this;
    }

    public LoadNotification message(int message) {
        if (message <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (mMessage != null) {
            throw new IllegalStateException("Message Already Set!");
        }

        this.mMessage = mNotification.mContext.getResources().getString(message);
        this.mBuilder.setContentText(mMessage);
        return this;
    }

    public LoadNotification message(String message) {
        if (mMessage != null) {
            throw new IllegalStateException("Message already set.");
        }

        if (message == null) {
            throw new IllegalStateException("Message Must Not Be Null!");
        }

        if (message.trim().length() == 0) {
            throw new IllegalArgumentException("Path Must Not Be Empty!");
        }

        this.mMessage = message;
        this.mBuilder.setContentText(message);
        return this;
    }

    public LoadNotification ticker(int ticker) {
        if (ticker <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setTicker(mNotification.mContext.getResources().getString(ticker));
        return this;
    }

    public LoadNotification ticker(String ticker) {
        if (ticker == null) {
            throw new IllegalStateException("Ticker Must Not Be Null!");
        }

        if (ticker.trim().length() == 0) {
            throw new IllegalArgumentException("Ticker Must Not Be Empty!");
        }

        this.mBuilder.setTicker(ticker);
        return this;
    }

    public LoadNotification when(long when) {
        if (when <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setWhen(when);
        return this;
    }

    public LoadNotification bigTextStyle(int bigTextStyle) {
        if (bigTextStyle <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(mNotification.mContext.getResources().getString(
                bigTextStyle)));
        return this;
    }

    public LoadNotification bigTextStyle(String bigTextStyle) {
        if (bigTextStyle == null) {
            throw new IllegalStateException("Big Text Style Must Not Be Null!");
        }

        if (bigTextStyle.trim().length() == 0) {
            throw new IllegalArgumentException("Ticker Must Not Be Empty!");
        }

        this.mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText((bigTextStyle)));
        return this;
    }

    public LoadNotification autoCancel(boolean autoCancel) {
        this.mBuilder.setAutoCancel(autoCancel);
        return this;
    }

    public LoadNotification smallIcon(int smallIcon) {
        if (smallIcon <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mSmallIcon = smallIcon;
        this.mBuilder.setSmallIcon(mSmallIcon);
        return this;
    }

    public LoadNotification largeIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap Must Not Be Null.");
        }

        this.mBuilder.setLargeIcon(bitmap);
        return this;
    }

    public LoadNotification vibrate(long[] vibrate) {
        for (int count = 0; count < vibrate.length; count++) {
            if (vibrate[count] <= 0) {
                throw new IllegalArgumentException("Vibrate Time " + vibrate[count] + " Invalid!");
            }
        }

        this.mBuilder.setVibrate(vibrate);
        return this;
    }

    public LoadNotification lights(int color, int ledOnMs, int ledOfMs) {
        if (ledOnMs < 0) {
            throw new IllegalStateException("Led On Milliseconds Invalid!");
        }

        if (ledOfMs < 0) {
            throw new IllegalStateException("Led Off Milliseconds Invalid!");
        }

        this.mBuilder.setLights(color, ledOnMs, ledOfMs);
        return this;
    }

    public LoadNotification sound(Uri sound) {
        if (sound == null) {
            throw new IllegalArgumentException("Sound Must Not Be Null.");
        }

        this.mBuilder.setSound(sound);
        return this;
    }

    public LoadNotification click(Class<?> activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity Must Not Be Null.");
        }

        this.mBuilder.setContentIntent(new ClickPendingIntentActivity(activity, bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public LoadNotification click(Class<?> activity) {
        click(activity, null);
        return this;
    }

    public LoadNotification click(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle Must Not Be Null.");
        }

        this.mBuilder.setContentIntent(new ClickPendingIntentBroadCast(bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public LoadNotification click(PendingIntentNotification pendingIntentNotification) {
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("PendingIntentNotification Must Not Be Null.");
        }

        this.mBuilder.setContentIntent(pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public LoadNotification dismiss(Class<?> activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity Must Not Be Null.");
        }

        this.mBuilder.setDeleteIntent(new DismissPendingIntentActivity(activity, bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public LoadNotification dismiss(Class<?> activity) {
        dismiss(activity, null);
        return this;
    }

    public LoadNotification dismiss(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle Must Not Be Null.");
        }

        this.mBuilder.setDeleteIntent(new DismissPendingIntentBroadCast(bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public LoadNotification dismiss(PendingIntentNotification pendingIntentNotification) {
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("Pending Intent Notification Must Not Be Null.");
        }

        this.mBuilder.setDeleteIntent(pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public CustomNotification custom() {
        Utils.checkMain();
        return new CustomNotification(mBuilder, mIdentifier, mTitle, mMessage, mSmallIcon);
    }

    public SimpleNotification simple() {
        return new SimpleNotification(mBuilder, mIdentifier);
    }
}
