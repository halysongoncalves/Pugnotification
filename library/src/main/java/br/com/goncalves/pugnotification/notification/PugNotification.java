package br.com.goncalves.pugnotification.notification;

import android.app.NotificationManager;
import android.content.Context;

public class PugNotification {
    public static PugNotification singleton = null;
    public final Context context;
    public final String channelId;
    private boolean shutdown;

    private PugNotification(Context context, String channelId) {
        this.context = context;
        this.channelId = channelId;
    }

    public static PugNotification with(Context context, String channelId) {
        if (singleton == null) {
            synchronized (PugNotification.class) {
                if (singleton == null) {
                    singleton = new Contractor(context, channelId).build();
                }
            }
        }
        return singleton;
    }

    public Load load() {
        return new Load();
    }

    public void cancel(int identifier) {
        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(identifier);
    }

    public void cancel(String tag, int identifier) {
        NotificationManager notifyManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(tag, identifier);
    }

    public void shutdown() {
        if (this == singleton) {
            throw new UnsupportedOperationException("Default singleton instance cannot be shutdown.");
        }
        if (shutdown) {
            return;
        }
        shutdown = true;
    }

    private static class Contractor {
        private final Context context;
        private final String channelId;

        Contractor(Context context, String channelId) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            if (channelId == null) {
                throw new IllegalArgumentException("Channel must not be null.");
            }
            this.context = context.getApplicationContext();
            this.channelId = channelId;
        }

        public PugNotification build() {
            return new PugNotification(context, channelId);
        }
    }
}
