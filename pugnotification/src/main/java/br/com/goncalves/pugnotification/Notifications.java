package br.com.goncalves.pugnotification;

import android.app.NotificationManager;
import android.content.Context;

import br.com.goncalves.pugnotification.utils.Utils;

public class Notifications {
    private static final String TAG = Notifications.class.getSimpleName();
    public static Notifications mSingleton = null;
    public final Context mContext;
    public boolean shutdown;

    public Notifications(Context context) {
        this.mContext = context;
    }

    public static Notifications with(Context context) {
        if (mSingleton == null) {
            synchronized (Notifications.class) {
                if (mSingleton == null) {
                    mSingleton = new Contractor(context).build();
                }
            }
        }
        return mSingleton;
    }

    public LoadNotification load() {
        return new LoadNotification(this);
    }

    public void cancel(int identifier) {
        Utils.checkMain();
        NotificationManager notifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(identifier);
    }

    public void shutdown() {
        if (this == mSingleton) {
            throw new UnsupportedOperationException("Default singleton instance cannot be shutdown.");
        }
        if (shutdown) {
            return;
        }
        shutdown = true;
    }

    private static class Contractor {
        private final Context mContext;

        public Contractor(Context context) {
            if (context == null) {
                throw new IllegalArgumentException("Context must not be null.");
            }
            this.mContext = context.getApplicationContext();
        }

        public Notifications build() {
            return new Notifications(mContext);
        }
    }

}
