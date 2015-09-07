package br.com.goncalves.pugnotification.notification;

import android.support.v4.app.NotificationCompat;

/**
 * Created by HalysonLima on 07/09/15.
 */
public class Progress extends Builder {
    public Progress(NotificationCompat.Builder builder, int identifier, String tag) {
        super(builder, identifier, tag);
    }

    @Override
    public void build() {
        super.build();
        super.notificationNotify();
    }

    public Progress update(int identifier, int progress, int max, boolean indeterminate) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(PugNotification.mSingleton.mContext);
        builder.setProgress(max, progress, indeterminate);

        notification = builder.build();
        notificationNotify(identifier);
        return this;
    }

    public Progress value(int progress, int max, boolean indeterminate) {
        builder.setProgress(max, progress, indeterminate);
        return this;
    }

}
