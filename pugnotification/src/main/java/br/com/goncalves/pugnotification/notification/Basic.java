package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.widget.RemoteViews;

import br.com.goncalves.pugnotification.utils.Utils;

public abstract class Basic {
    private static final String TAG = Basic.class.getSimpleName();
    protected final PugNotification pugNotification;
    protected String tag;
    protected Notification notification;
    protected NotificationCompat.Builder builder;
    protected int notificationId;

    public Basic(NotificationCompat.Builder builder, int identifier, String tag) {
        this.pugNotification = Utils.isActiveSingleton(PugNotification.mSingleton);
        this.builder = builder;
        this.notificationId = identifier;
        this.tag = tag;
    }

    public void build() {
        notification = builder.build();
    }

    public void setBigContentView(RemoteViews views) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification.bigContentView = views;
            return;
        }
        Log.w(TAG, "Version does not support big content view");
    }

    protected Notification notificationNotify() {
        if(tag != null){
            return notificationNotify(tag, notificationId);
        }
        return notificationNotify(notificationId);
    }

    protected Notification notificationNotify(int identifier) {
        NotificationManagerCompat notificationManager =NotificationManagerCompat.from(pugNotification.mContext);
        notificationManager.notify(identifier, notification);
        return notification;
    }

    protected Notification notificationNotify(String tag, int identifier) {
        NotificationManagerCompat notificationManager =NotificationManagerCompat.from(pugNotification.mContext);
        notificationManager.notify(tag, identifier, notification);
        return notification;
    }

}
