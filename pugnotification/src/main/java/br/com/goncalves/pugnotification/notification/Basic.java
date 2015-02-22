package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;
import android.util.Log;
import android.widget.RemoteViews;

import br.com.goncalves.pugnotification.utils.Utils;

public abstract class Basic {
    private static final String TAG = Basic.class.getSimpleName();
    protected final PugNotification mSingleton;
    protected Notification mNotification;
    protected Builder mBuilder;
    protected int mIdentifier;

    public Basic(Builder builder, int identifier) {
        this.mSingleton = Utils.isActiveSingleton(PugNotification.mSingleton);
        this.mBuilder = builder;
        this.mIdentifier = identifier;
    }

    public void build() {
        mNotification = mBuilder.build();
        mNotification.defaults |= Notification.DEFAULT_LIGHTS;
        mNotification.defaults |= Notification.DEFAULT_VIBRATE;
        mNotification.defaults |= Notification.DEFAULT_SOUND;
    }

    public void setBigContentView(RemoteViews views) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            mNotification.bigContentView = views;
            return;
        }
        Log.w(TAG, "Version does not support big content view");
    }

    protected Notification notificationNotify() {
        return notificationNotify(mIdentifier);
    }

    protected Notification notificationNotify(int identifier) {
        NotificationManager notificationManager = (NotificationManager) mSingleton.mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(identifier, mNotification);
        return mNotification;
    }

}
