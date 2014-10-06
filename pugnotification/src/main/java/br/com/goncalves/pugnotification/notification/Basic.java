package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.NotificationCompat.Builder;

import br.com.goncalves.pugnotification.utils.Utils;

public abstract class Basic {
    private static final String TAG = Basic.class.getSimpleName();
    protected final PugNotification mSingleton;
    protected Notification mNotificaton;
    protected Builder mBuilder;
    protected int mIdentifier;

    public Basic(Builder builder, int identifier) {
        this.mSingleton = Utils.isActiveSingleton(PugNotification.mSingleton);
        this.mBuilder = builder;
        this.mIdentifier = identifier;
    }

    public void build() {
        mNotificaton = mBuilder.build();
    }

    protected Notification notificationNotify() {
        return notificationNotify(mIdentifier);
    }

    protected Notification notificationNotify(int identifier) {
        NotificationManager notificationManager = (NotificationManager) mSingleton.mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(identifier, mNotificaton);
        return mNotificaton;
    }

}
