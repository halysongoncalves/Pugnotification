package br.com.goncalves.pugnotification.notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

import br.com.goncalves.pugnotification.R;
import br.com.goncalves.pugnotification.interfaces.PendingIntentNotification;
import br.com.goncalves.pugnotification.pendingintent.ClickPendingIntentActivity;
import br.com.goncalves.pugnotification.pendingintent.ClickPendingIntentBroadCast;
import br.com.goncalves.pugnotification.pendingintent.DismissPendingIntentActivity;
import br.com.goncalves.pugnotification.pendingintent.DismissPendingIntentBroadCast;
import br.com.goncalves.pugnotification.utils.Utils;

public class Load {
    private static final String TAG = Load.class.getSimpleName();
    private final PugNotification mNotification;
    private Builder mBuilder;
    private int mIdentifier;
    private String mTitle;
    private String mMessage;
    private int mSmallIcon;

    public Load(PugNotification notification) {
        this.mNotification = Utils.isActiveSingleton(notification);
        this.mBuilder = new NotificationCompat.Builder(mNotification.mContext);
        this.createNotifationDefault();
    }

    private void createNotifationDefault() {
        this.mIdentifier = Utils.radom();
        this.mBuilder.setContentTitle("");
        this.mBuilder.setContentText("");
        this.mBuilder.setSmallIcon(R.drawable.pugnotification_ic_launcher);
        this.mBuilder.setLargeIcon(BitmapFactory.decodeResource(mNotification.mContext.getResources(),
                R.drawable.pugnotification_ic_launcher));
        this.mBuilder.setContentIntent(PendingIntent.getBroadcast(mNotification.mContext, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public Load identifier(int identifier) {
        if (identifier <= 0) {
            throw new IllegalStateException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mIdentifier = identifier;
        return this;
    }

    public Load title(int title) {
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

    public Load title(String title) {
        if (mTitle != null) {
            throw new IllegalStateException("Title Already Set!");
        }

        if (title == null) {
            throw new IllegalStateException("Title Must Not Be Null!");
        }

        if (title.trim().length() == 0) {
            throw new IllegalArgumentException("Title Must Not Be Empty!");
        }

        this.mTitle = title;
        this.mBuilder.setContentTitle(mTitle);
        return this;
    }

    public Load message(int message) {
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

    public Load message(String message) {
        if (mMessage != null) {
            throw new IllegalStateException("Message already set.");
        }

        if (message == null) {
            throw new IllegalStateException("Message Must Not Be Null!");
        }

        if (message.trim().length() == 0) {
            throw new IllegalArgumentException("Message Must Not Be Empty!");
        }

        this.mMessage = message;
        this.mBuilder.setContentText(message);
        return this;
    }

    public Load color(int color) {
        if (color <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setColor(color);
        return this;
    }

    public Load ticker(int ticker) {
        if (ticker <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setTicker(mNotification.mContext.getResources().getString(ticker));
        return this;
    }

    public Load ticker(String ticker) {
        if (ticker == null) {
            throw new IllegalStateException("Ticker Must Not Be Null!");
        }

        if (ticker.trim().length() == 0) {
            throw new IllegalArgumentException("Ticker Must Not Be Empty!");
        }

        this.mBuilder.setTicker(ticker);
        return this;
    }

    public Load when(long when) {
        if (when <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setWhen(when);
        return this;
    }

    public Load bigTextStyle(int bigTextStyle) {
        if (bigTextStyle <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText(mNotification.mContext.getResources().getString(
                bigTextStyle)));
        return this;
    }

    public Load bigTextStyle(String bigTextStyle) {
        if (bigTextStyle == null) {
            throw new IllegalStateException("Big Text Style Must Not Be Null!");
        }

        if (bigTextStyle.trim().length() == 0) {
            throw new IllegalArgumentException("Big Text Style Must Not Be Empty!");
        }

        this.mBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText((bigTextStyle)));
        return this;
    }

    public Load autoCancel(boolean autoCancel) {
        this.mBuilder.setAutoCancel(autoCancel);
        return this;
    }

    public Load smallIcon(int smallIcon) {
        if (smallIcon <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.mSmallIcon = smallIcon;
        this.mBuilder.setSmallIcon(mSmallIcon);
        return this;
    }

    public Load largeIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap Must Not Be Null.");
        }

        this.mBuilder.setLargeIcon(bitmap);
        return this;
    }

    public Load largeIcon(int largeIcon) {
        if (largeIcon <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        Bitmap bitmap = BitmapFactory.decodeResource(mNotification.mContext.getResources(), largeIcon);
        this.mBuilder.setLargeIcon(bitmap);
        return this;
    }

    public Load vibrate(long[] vibrate) {
        for (int count = 0; count < vibrate.length; count++) {
            if (vibrate[count] <= 0) {
                throw new IllegalArgumentException("Vibrate Time " + vibrate[count] + " Invalid!");
            }
        }

        this.mBuilder.setVibrate(vibrate);
        return this;
    }

    public Load lights(int color, int ledOnMs, int ledOfMs) {
        if (ledOnMs < 0) {
            throw new IllegalStateException("Led On Milliseconds Invalid!");
        }

        if (ledOfMs < 0) {
            throw new IllegalStateException("Led Off Milliseconds Invalid!");
        }

        this.mBuilder.setLights(color, ledOnMs, ledOfMs);
        return this;
    }

    public Load sound(Uri sound) {
        if (sound == null) {
            throw new IllegalArgumentException("Sound Must Not Be Null.");
        }

        this.mBuilder.setSound(sound);
        return this;
    }

    public Load click(Class<?> activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity Must Not Be Null.");
        }

        this.mBuilder.setContentIntent(new ClickPendingIntentActivity(activity, bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public Load click(Class<?> activity) {
        click(activity, null);
        return this;
    }

    public Load click(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle Must Not Be Null.");
        }

        this.mBuilder.setContentIntent(new ClickPendingIntentBroadCast(bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public Load click(PendingIntentNotification pendingIntentNotification) {
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("PendingIntentNotification Must Not Be Null.");
        }

        this.mBuilder.setContentIntent(pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public Load dismiss(Class<?> activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity Must Not Be Null.");
        }

        this.mBuilder.setDeleteIntent(new DismissPendingIntentActivity(activity, bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public Load dismiss(Class<?> activity) {
        dismiss(activity, null);
        return this;
    }

    public Load dismiss(Bundle bundle) {
        if (bundle == null) {
            throw new IllegalArgumentException("Bundle Must Not Be Null.");
        }

        this.mBuilder.setDeleteIntent(new DismissPendingIntentBroadCast(bundle, mIdentifier).onSettingPendingIntent());
        return this;
    }

    public Load dismiss(PendingIntentNotification pendingIntentNotification) {
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("Pending Intent Notification Must Not Be Null.");
        }

        this.mBuilder.setDeleteIntent(pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public Custom custom() {
        Utils.checkMain();
        return new Custom(mBuilder, mIdentifier, mTitle, mMessage, mSmallIcon);
    }

    public Simple simple() {
        return new Simple(mBuilder, mIdentifier);
    }
}
