package br.com.goncalves.pugnotification.notification;

import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;

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
    private NotificationCompat.Builder builder;
    private int notificationId;
    private String title;
    private String message;
    private int smallIcon;

    public Load(PugNotification notification) {
        this.mNotification = Utils.isActiveSingleton(notification);
        this.builder = new NotificationCompat.Builder(mNotification.mContext);
        this.createNotifationDefault();
    }

    private void createNotifationDefault() {
        this.notificationId = Utils.radom();
        this.builder.setContentTitle("");
        this.builder.setContentText("");
        this.builder.setSmallIcon(R.drawable.pugnotification_ic_launcher);
        this.builder.setLargeIcon(BitmapFactory.decodeResource(mNotification.mContext.getResources(),
                R.drawable.pugnotification_ic_launcher));
        this.builder.setContentIntent(PendingIntent.getBroadcast(mNotification.mContext, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public Load identifier(int identifier) {
        if (identifier <= 0) {
            throw new IllegalStateException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.notificationId = identifier;
        return this;
    }

    public Load title(int title) {
        if (title <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (this.title != null) {
            throw new IllegalStateException("Title Already Set!");
        }

        this.title = mNotification.mContext.getResources().getString(title);
        this.builder.setContentTitle(this.title);
        return this;
    }

    public Load title(String title) {
        if (this.title != null) {
            throw new IllegalStateException("Title Already Set!");
        }

        if (title == null) {
            throw new IllegalStateException("Title Must Not Be Null!");
        }

        if (title.trim().length() == 0) {
            throw new IllegalArgumentException("Title Must Not Be Empty!");
        }

        this.title = title;
        this.builder.setContentTitle(this.title);
        return this;
    }

    public Load message(int message) {
        if (message <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (this.message != null) {
            throw new IllegalStateException("Message Already Set!");
        }

        this.message = mNotification.mContext.getResources().getString(message);
        this.builder.setContentText(this.message);
        return this;
    }

    public Load message(String message) {
        if (this.message != null) {
            throw new IllegalStateException("Message already set.");
        }

        if (message == null) {
            throw new IllegalStateException("Message Must Not Be Null!");
        }

        if (message.trim().length() == 0) {
            throw new IllegalArgumentException("Message Must Not Be Empty!");
        }

        this.message = message;
        this.builder.setContentText(message);
        return this;
    }

    public Load color(int color) {
        if (color <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.builder.setColor(color);
        return this;
    }

    public Load ticker(int ticker) {
        if (ticker <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.builder.setTicker(mNotification.mContext.getResources().getString(ticker));
        return this;
    }

    public Load ticker(String ticker) {
        if (ticker == null) {
            throw new IllegalStateException("Ticker Must Not Be Null!");
        }

        if (ticker.trim().length() == 0) {
            throw new IllegalArgumentException("Ticker Must Not Be Empty!");
        }

        this.builder.setTicker(ticker);
        return this;
    }

    public Load when(long when) {
        if (when <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.builder.setWhen(when);
        return this;
    }

    public Load bigTextStyle(int bigTextStyle) {
        if (bigTextStyle <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.builder.setStyle(new NotificationCompat.BigTextStyle().bigText(mNotification.mContext.getResources().getString(
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

        this.builder.setStyle(new NotificationCompat.BigTextStyle().bigText((bigTextStyle)));
        return this;
    }

    public Load autoCancel(boolean autoCancel) {
        this.builder.setAutoCancel(autoCancel);
        return this;
    }

    public Load smallIcon(int smallIcon) {
        if (smallIcon <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.smallIcon = smallIcon;
        this.builder.setSmallIcon(this.smallIcon);
        return this;
    }

    public Load largeIcon(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap Must Not Be Null.");
        }

        this.builder.setLargeIcon(bitmap);
        return this;
    }

    public Load largeIcon(int largeIcon) {
        if (largeIcon <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        Bitmap bitmap = BitmapFactory.decodeResource(mNotification.mContext.getResources(), largeIcon);
        this.builder.setLargeIcon(bitmap);
        return this;
    }

    public Load vibrate(long[] vibrate) {
        for (long aVibrate : vibrate) {
            if (aVibrate <= 0) {
                throw new IllegalArgumentException("Vibrate Time " + aVibrate + " Invalid!");
            }
        }

        this.builder.setVibrate(vibrate);
        return this;
    }

    public Load lights(int color, int ledOnMs, int ledOfMs) {
        if (ledOnMs < 0) {
            throw new IllegalStateException("Led On Milliseconds Invalid!");
        }

        if (ledOfMs < 0) {
            throw new IllegalStateException("Led Off Milliseconds Invalid!");
        }

        this.builder.setLights(color, ledOnMs, ledOfMs);
        return this;
    }

    public Load sound(Uri sound) {
        if (sound == null) {
            throw new IllegalArgumentException("Sound Must Not Be Null.");
        }

        this.builder.setSound(sound);
        return this;
    }

    public Load button(int icon, String title, PendingIntent pendingIntent) {
        if (icon < 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (title == null) {
            throw new IllegalStateException("Title Must Not Be Null!");
        }
        if (pendingIntent == null) {
            throw new IllegalArgumentException("PendingIntent Must Not Be Null.");
        }

        this.builder.addAction(icon, title, pendingIntent);
        return this;
    }

    public Load button(int icon, String title, PendingIntentNotification pendingIntentNotification) {
        if (icon < 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (title == null) {
            throw new IllegalStateException("Title Must Not Be Null!");
        }
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("PendingIntentNotification Must Not Be Null.");
        }

        this.builder.addAction(icon, title, pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public Load click(Class<?> activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity Must Not Be Null.");
        }

        this.builder.setContentIntent(new ClickPendingIntentActivity(activity, bundle, notificationId).onSettingPendingIntent());
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

        this.builder.setContentIntent(new ClickPendingIntentBroadCast(bundle, notificationId).onSettingPendingIntent());
        return this;
    }

    public Load click(PendingIntentNotification pendingIntentNotification) {
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("PendingIntentNotification Must Not Be Null.");
        }

        this.builder.setContentIntent(pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public Load dismiss(Class<?> activity, Bundle bundle) {
        if (activity == null) {
            throw new IllegalArgumentException("Activity Must Not Be Null.");
        }

        this.builder.setDeleteIntent(new DismissPendingIntentActivity(activity, bundle, notificationId).onSettingPendingIntent());
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

        this.builder.setDeleteIntent(new DismissPendingIntentBroadCast(bundle, notificationId).onSettingPendingIntent());
        return this;
    }

    public Load dismiss(PendingIntentNotification pendingIntentNotification) {
        if (pendingIntentNotification == null) {
            throw new IllegalArgumentException("Pending Intent Notification Must Not Be Null.");
        }

        this.builder.setDeleteIntent(pendingIntentNotification.onSettingPendingIntent());
        return this;
    }

    public Custom custom() {
        Utils.checkMain();
        return new Custom(builder, notificationId, title, message, smallIcon);
    }

    public Simple simple() {
        return new Simple(builder, notificationId);
    }
}
