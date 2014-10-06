package br.com.goncalves.pugnotification;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationCompat.Builder;

public class SimpleNotification extends BasicNotification {
    private static final String TAG = SimpleNotification.class.getSimpleName();
    private int mProgress;
    private int mMax;
    private boolean mIndeterminate;

    public SimpleNotification(Builder builder, int identifier) {
        super(builder, identifier);
    }

    @Override
    public void build() {
        mBuilder.setProgress(mMax, mProgress, mIndeterminate);
        super.build();
        super.notificationNotify();
    }

    public SimpleNotification update(int identifier) {
        Builder builder = new NotificationCompat.Builder(Notifications.mSingleton.mContext);
        builder.setProgress(mMax, mProgress, mIndeterminate);

        mNotificaton = builder.build();
        mNotificaton.flags |= Notification.FLAG_NO_CLEAR;
        notificationNotify(identifier);
        return this;
    }

    public SimpleNotification setProgress(int progress, int max, boolean indeterminate) {
        this.mProgress = progress;
        this.mMax = max;
        this.mIndeterminate = indeterminate;
        return this;
    }
}
