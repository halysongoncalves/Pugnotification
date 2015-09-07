package br.com.goncalves.pugnotification.notification;

import android.support.v4.app.NotificationCompat;

public class Simple extends Builder {
    public Simple(NotificationCompat.Builder builder, int identifier, String tag) {
        super(builder, identifier, tag);
    }

    @Override
    public void build() {
        super.build();
        super.notificationNotify();
    }
}
