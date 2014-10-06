package br.com.goncalves.pugnotification.utils;

import android.os.Looper;

import java.util.Random;

import br.com.goncalves.pugnotification.Notifications;

public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static void checkMain() {
        if (!isMain()) {
            throw new IllegalStateException("Method call should happen from the main thread.");
        }
    }

    public static boolean isMain() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static int radom() {
        Random gerador = new Random();
        return gerador.nextInt(99999);
    }

    public static Notifications isActiveSingleton(Notifications notification) {
        if (notification.shutdown) {
            throw new IllegalStateException("Notification instance already shut down. Cannot submit new requests.");
        }
        return notification;
    }
}
