package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.text.Spanned;

import br.com.goncalves.pugnotification.R;
import br.com.goncalves.pugnotification.interfaces.PendingIntentNotification;
import br.com.goncalves.pugnotification.pendingintent.ClickPendingIntentActivity;
import br.com.goncalves.pugnotification.pendingintent.ClickPendingIntentBroadCast;
import br.com.goncalves.pugnotification.pendingintent.DismissPendingIntentActivity;
import br.com.goncalves.pugnotification.pendingintent.DismissPendingIntentBroadCast;
import br.com.goncalves.pugnotification.utils.Utils;

public class Load {
    private static final String TAG = Load.class.getSimpleName();
    private final PugNotification notification;
    private NotificationCompat.Builder builder;
    private int notificationId;
    private String title;
    private String message;
    private int smallIcon;
    private Spanned messageSpanned;
    private String tag;

    public Load(PugNotification notification) {
        this.notification = Utils.isActiveSingleton(notification);
        builder = new NotificationCompat.Builder(this.notification.mContext);
        createNotifationDefault();
    }

    private void createNotifationDefault() {
        this.notificationId = Utils.radom();
        this.builder.setContentTitle("");
        this.builder.setContentText("");
        this.builder.setSmallIcon(R.drawable.pugnotification_ic_launcher);
        this.builder.setDefaults(Notification.DEFAULT_ALL);

        this.builder.setContentIntent(PendingIntent.getBroadcast(notification.mContext, 0, new Intent(), PendingIntent.FLAG_UPDATE_CURRENT));
    }

    public Load identifier(int identifier) {
        if (identifier <= 0) {
            throw new IllegalStateException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.notificationId = identifier;
        return this;
    }

    public Load tag(@NonNull String tag) {
        this.tag = tag;
        return this;
    }

    public Load title(@StringRes int title) {
        if (title <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.title = notification.mContext.getResources().getString(title);
        this.builder.setContentTitle(this.title);
        return this;
    }

    public Load title(String title) {
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

    public Load message(@StringRes int message) {
        if (message <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.message = notification.mContext.getResources().getString(message);
        this.builder.setContentText(this.message);
        return this;
    }

    public Load message(@NonNull String message) {
        if (message.trim().length() == 0) {
            throw new IllegalArgumentException("Message Must Not Be Empty!");
        }

        this.message = message;
        this.builder.setContentText(message);
        return this;
    }

    public Load message(@NonNull Spanned message) {
        if (message.length() == 0) {
            throw new IllegalArgumentException("Message Must Not Be Empty!");
        }

        this.messageSpanned = message;
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

    public Load ticker(@StringRes int ticker) {
        if (ticker <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        this.builder.setTicker(notification.mContext.getResources().getString(ticker));
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

    public Load bigTextStyle(@StringRes int bigTextStyle) {
        if (bigTextStyle <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        return bigTextStyle(notification.mContext.getResources().getString(
                bigTextStyle), null);
    }

    public Load bigTextStyle(@StringRes int bigTextStyle, @StringRes int summaryText) {
        if (bigTextStyle <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        return bigTextStyle(notification.mContext.getResources().getString(
                bigTextStyle), notification.mContext.getResources().getString(
                summaryText));
    }


    public Load bigTextStyle(@NonNull String bigTextStyle) {
        if (bigTextStyle.trim().length() == 0) {
            throw new IllegalArgumentException("Big Text Style Must Not Be Empty!");
        }

        return bigTextStyle(bigTextStyle, null);
    }

    public Load bigTextStyle(@NonNull String bigTextStyle, String summaryText) {
        if (bigTextStyle.trim().length() == 0) {
            throw new IllegalArgumentException("Big Text Style Must Not Be Empty!");
        }

        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(bigTextStyle);
        if (summaryText != null) {
            bigStyle.setSummaryText(summaryText);
        }
        this.builder.setStyle(bigStyle);
        return this;
    }

    public Load bigTextStyle(@NonNull Spanned bigTextStyle, String summaryText) {
        if (bigTextStyle.length() == 0) {
            throw new IllegalArgumentException("Big Text Style Must Not Be Empty!");
        }

        NotificationCompat.BigTextStyle bigStyle = new NotificationCompat.BigTextStyle();
        bigStyle.bigText(bigTextStyle);
        if (summaryText != null) {
            bigStyle.setSummaryText(summaryText);
        }
        this.builder.setStyle(bigStyle);
        return this;
    }

    public Load inboxStyle(@NonNull String[] inboxLines, @NonNull String title, String summary) {
        if (inboxLines.length <= 0) {
            throw new IllegalArgumentException("Inbox Lines Must Have At Least One Text!");
        }

        if (title.trim().length() == 0) {
            throw new IllegalArgumentException("Title Must Not Be Empty!");
        }

        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
        for (int i = 0; i < inboxLines.length; i++) {
            inboxStyle.addLine(inboxLines[i]);
        }
        inboxStyle.setBigContentTitle(title);
        if (summary != null) {
            inboxStyle.setSummaryText(summary);
        }
        this.builder.setStyle(inboxStyle);
        return this;
    }

    public Load autoCancel(boolean autoCancel) {
        this.builder.setAutoCancel(autoCancel);
        return this;
    }

    public Load smallIcon(@DrawableRes int smallIcon) {
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

    public Load largeIcon(@DrawableRes int largeIcon) {
        if (largeIcon <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        Bitmap bitmap = BitmapFactory.decodeResource(notification.mContext.getResources(), largeIcon);
        this.builder.setLargeIcon(bitmap);
        return this;
    }

    public Load group(@NonNull String groupKey) {
        if (groupKey.trim().length() == 0) {
            throw new IllegalArgumentException("Group Key Must Not Be Empty!");
        }

        this.builder.setGroup(groupKey);
        return this;
    }

    public Load groupSummary(boolean groupSummary) {
        this.builder.setGroupSummary(groupSummary);
        return this;
    }

    public Load number(int number) {
        this.builder.setNumber(number);
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

    public Load onlyAlertOnce(boolean onlyAlertOnce) {
        this.builder.setOnlyAlertOnce(onlyAlertOnce);
        return this;
    }

    public Load addPerson(@NonNull String uri) {
        if (uri.length() == 0) {
            throw new IllegalArgumentException("URI Must Not Be Empty!");
        }
        this.builder.addPerson(uri);
        return this;
    }

    public Load button(@DrawableRes int icon, String title, PendingIntent pendingIntent) {
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

    public Load button(@DrawableRes int icon, String title, PendingIntentNotification pendingIntentNotification) {
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

    public Load button(NotificationCompat.Action action) {
        if (action == null) {
            throw new IllegalArgumentException("Action Must Not Be Null.");
        }

        this.builder.addAction(action);
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

    public Load priority(int priority) {
        if (priority <= 0) {
            throw new IllegalArgumentException("Priority Should Not Be Less Than Or Equal To Zero!");
        }
        this.builder.setPriority(priority);
        return this;
    }

    public Load defaults(int defaults) {
        if (defaults < 0) {
            throw new IllegalArgumentException("Defaults Should Not Be Less Than Or Equal!");
        }
        this.builder.setDefaults(defaults);
        return this;
    }


    public Load click(@NonNull PendingIntent pendingIntent) {
        this.builder.setContentIntent(pendingIntent);
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

    public Load dismiss(@NonNull PendingIntent pendingIntent) {
        this.builder.setDeleteIntent(pendingIntent);
        return this;
    }

    public Custom custom() {
        Utils.checkMain();
        if (messageSpanned != null) {
            return new Custom(builder, notificationId, title, messageSpanned, smallIcon, tag);
        }
        return new Custom(builder, notificationId, title, message, smallIcon, tag);
    }

    public Simple simple() {
        return new Simple(builder, notificationId, tag);
    }

    public Wear wear() {
        return new Wear(builder, notificationId, tag);
    }
}
