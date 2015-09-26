package br.com.goncalves.pugnotification.notification;

import android.app.NotificationManager;
import android.content.Context;

public class PugNotification {
    private static final String TAG = PugNotification.class.getSimpleName();
    public static PugNotification mSingleton = null;
    public final Context mContext;
    public boolean shutdown;

    public PugNotification(Context context) {
        this.mContext = context;
    }

    public static PugNotification with(Context context) {
        if (mSingleton == null) {
            synchronized (PugNotification.class) {
                if (mSingleton == null) {
                    mSingleton = new Contractor(context).build();
                }
            }
        }
        return mSingleton;
    }

    public Load load() {
        return new Load();
    }

    public void cancel(int identifier) {
        NotificationManager notifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(identifier);
    }

    public void cancel(String tag, int identifier) {
        NotificationManager notifyManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notifyManager.cancel(tag, identifier);
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

        public PugNotification build() {
            return new PugNotification(mContext);
        }
    }
}
