package br.com.goncalves.pugnotification.notification;

import android.app.Notification;
import android.support.v4.app.NotificationCompat;

public class Simple extends Builder {
    private static final String TAG = Simple.class.getSimpleName();
    private int mProgress;
    private int mMax;
    private boolean mIndeterminate;

    public Simple(NotificationCompat.Builder builder, int identifier, String tag) {
        super(builder, identifier, tag);
    }

    @Override
    public void build() {
        builder.setProgress(mMax, mProgress, mIndeterminate);
        super.build();
        super.notificationNotify();
    }

    public Simple update(int identifier) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(PugNotification.mSingleton.mContext);
        builder.setProgress(mMax, mProgress, mIndeterminate);

        notification = builder.build();
        notification.flags |= Notification.FLAG_NO_CLEAR;
        notificationNotify(identifier);
        return this;
    }

    public Simple setProgress(int progress, int max, boolean indeterminate) {
        this.mProgress = progress;
        this.mMax = max;
        this.mIndeterminate = indeterminate;
        return this;
    }
}
