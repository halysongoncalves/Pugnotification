package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import java.util.List;

import br.com.goncalves.pugnotification.utils.Utils;

/**
 * Created by Halyson on 04/05/15.
 */
public class Wear extends Basic {
    private final PugNotification pugNotification;
    private NotificationCompat.WearableExtender wearableExtender;

    public Wear(NotificationCompat.Builder builder, int identifier, PugNotification pugNotification1) {
        super(builder, identifier);
        this.pugNotification = Utils.isActiveSingleton(pugNotification1);
        this.wearableExtender = new NotificationCompat.WearableExtender();
    }

    public Wear setHideIcon(boolean hideIcon) {
        wearableExtender.setHintHideIcon(hideIcon);
        return this;
    }

    public Wear setPages(Notification notification) {
        wearableExtender.addPage(notification);
        return this;
    }

    public Wear setPages(List<Notification> listPages) {
        wearableExtender.addPages(listPages);
        return this;
    }

    public Wear background(Bitmap bitmap) {
        if (bitmap == null) {
            throw new IllegalArgumentException("Bitmap Must Not Be Null.");
        }

        this.wearableExtender.setBackground(bitmap);
        return this;
    }

    public Wear background(int background) {
        if (background <= 0) {
            throw new IllegalArgumentException("Resource ID Should Not Be Less Than Or Equal To Zero!");
        }

        Bitmap bitmap = BitmapFactory.decodeResource(pugNotification.mContext.getResources(), background);
        this.wearableExtender.setBackground(bitmap);
        return this;
    }

    @Override
    public void build() {
        builder.extend(wearableExtender);
        super.build();
        super.notificationNotify();
    }
}
