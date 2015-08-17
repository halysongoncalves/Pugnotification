package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;

import java.util.List;

import br.com.goncalves.pugnotification.R;
import br.com.goncalves.pugnotification.interfaces.PendingIntentNotification;

/**
 * Created by Halyson on 04/05/15.
 */
public class Wear extends Builder {
    private NotificationCompat.WearableExtender wearableExtender;
    private RemoteInput remoteInput;

    public Wear(NotificationCompat.Builder builder, int identifier, String tag) {
        super(builder, identifier, tag);
        this.wearableExtender = new NotificationCompat.WearableExtender();
    }

    public Wear hideIcon(boolean hideIcon) {
        wearableExtender.setHintHideIcon(hideIcon);
        return this;
    }

    public Wear contentIcon(@DrawableRes int contentIcon) {
        wearableExtender.setContentIcon(contentIcon);
        return this;
    }

    public Wear addPages(Notification notification) {
        if (notification == null) {
            throw new IllegalArgumentException("Notification Must Not Be Null.");
        }

        wearableExtender.addPage(notification);
        return this;
    }

    public Wear addPages(List<Notification> notificationList) {
        if (notificationList == null || notificationList.isEmpty()) {
            throw new IllegalArgumentException("List Notitifcation Must Not Be Null And Empty!");
        }

        wearableExtender.addPages(notificationList);
        return this;
    }

    public Wear button(@DrawableRes int icon, String title, PendingIntent pendingIntent) {
        if (icon < 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        if (title == null) {
            throw new IllegalStateException("Title Must Not Be Null!");
        }
        if (pendingIntent == null) {
            throw new IllegalArgumentException("PendingIntent Must Not Be Null.");
        }

        this.wearableExtender.addAction(new NotificationCompat.Action(icon, title, pendingIntent));
        return this;
    }


    public Wear remoteInput(@DrawableRes int icon, @StringRes int title, PendingIntentNotification pendingIntentNotification, RemoteInput remoteInput) {
        remoteInput(icon, PugNotification.mSingleton.mContext.getString(title), pendingIntentNotification.onSettingPendingIntent(), remoteInput);
        return this;
    }

    public Wear remoteInput(@DrawableRes int icon, String title, PendingIntentNotification pendingIntentNotification, RemoteInput remoteInput) {
        remoteInput(icon, title, pendingIntentNotification.onSettingPendingIntent(), remoteInput);
        return this;
    }

    public Wear remoteInput(@DrawableRes int icon, @StringRes int title, PendingIntent pendingIntent, RemoteInput remoteInput) {
        remoteInput(icon, PugNotification.mSingleton.mContext.getString(title), pendingIntent, remoteInput);
        return this;
    }

    public Wear remoteInput(@DrawableRes int icon, String title, PendingIntent pendingIntent, RemoteInput remoteInput) {
        if (icon <= 0) {
            throw new IllegalArgumentException("Resource ID Icon Should Not Be Less Than Or Equal To Zero!");
        }

        if (title == null) {
            throw new IllegalArgumentException("Title Must Not Be Null!");
        }

        if (pendingIntent == null) {
            throw new IllegalArgumentException("PendingIntent Must Not Be Null!");
        }

        if (remoteInput == null) {
            throw new IllegalArgumentException("RemoteInput Must Not Be Null!");
        }

        this.remoteInput = remoteInput;
        wearableExtender.addAction(new NotificationCompat.Action.Builder(icon,
                title, pendingIntent)
                .addRemoteInput(remoteInput)
                .build());
        return this;
    }

    public Wear remoteInput(@DrawableRes int icon, String title, PendingIntent pendingIntent) {
        if (icon <= 0) {
            throw new IllegalArgumentException("Resource ID Icon Should Not Be Less Than Or Equal To Zero!");
        }

        if (title == null) {
            throw new IllegalArgumentException("Title Must Not Be Null!");
        }

        if (pendingIntent == null) {
            throw new IllegalArgumentException("PendingIntent Must Not Be Null!");
        }

        this.remoteInput = new RemoteInput.Builder(PugNotification.mSingleton.mContext.getString(R.string.pugnotification_key_voice_reply))
                .setLabel(PugNotification.mSingleton.mContext.getString(R.string.pugnotification_label_voice_reply))
                .setChoices(PugNotification.mSingleton.mContext.getResources().getStringArray(R.array.pugnotification_reply_choices))
                .build();
        wearableExtender.addAction(new NotificationCompat.Action.Builder(icon,
                title, pendingIntent)
                .addRemoteInput(remoteInput)
                .build());
        return this;
    }

    public Wear remoteInput(@DrawableRes int icon, @StringRes int title, PendingIntent pendingIntent, String replyLabel, String[] replyChoices) {
        return remoteInput(icon, PugNotification.mSingleton.mContext.getString(title), pendingIntent, replyLabel, replyChoices);
    }

    public Wear remoteInput(@DrawableRes int icon, String title, PendingIntent pendingIntent, String replyLabel, String[] replyChoices) {
        if (icon <= 0) {
            throw new IllegalArgumentException("Resource ID Icon Should Not Be Less Than Or Equal To Zero!");
        }

        if (title == null) {
            throw new IllegalArgumentException("Title Must Not Be Null!");
        }

        if (replyChoices == null) {
            throw new IllegalArgumentException("Reply Choices Must Not Be Null!");
        }

        if (pendingIntent == null) {
            throw new IllegalArgumentException("PendingIntent Must Not Be Null!");
        }
        if (replyLabel == null) {
            throw new IllegalArgumentException("Reply Label Must Not Be Null!");
        }

        this.remoteInput = new RemoteInput.Builder(PugNotification.mSingleton.mContext.getString(R.string.pugnotification_key_voice_reply))
                .setLabel(replyLabel)
                .setChoices(replyChoices)
                .build();
        wearableExtender.addAction(new NotificationCompat.Action.Builder(icon,
                title, pendingIntent)
                .addRemoteInput(remoteInput)
                .build());
        return this;
    }

    public Wear background(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap Must Not Be Null.");
        }

        this.wearableExtender.setBackground(bitmap);
        return this;
    }

    public Wear background(@DrawableRes int background) {
        if (background <= 0) {
            throw new IllegalArgumentException("Resource ID Background Should Not Be Less Than Or Equal To Zero!");
        }

        Bitmap bitmap = BitmapFactory.decodeResource(PugNotification.mSingleton.mContext.getResources(), background);
        this.wearableExtender.setBackground(bitmap);
        return this;
    }

    public Wear startScrollBottom(boolean startScrollBottom) {
        this.wearableExtender.setStartScrollBottom(startScrollBottom);
        return this;
    }

    @Override
    public void build() {
        builder.extend(wearableExtender);
        super.build();
        super.notificationNotify();
    }
}
