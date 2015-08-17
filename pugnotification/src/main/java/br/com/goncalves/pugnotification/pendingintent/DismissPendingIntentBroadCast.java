package br.com.goncalves.pugnotification.pendingintent;

import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;

import br.com.goncalves.pugnotification.constants.BroadcastActions;
import br.com.goncalves.pugnotification.interfaces.PendingIntentNotification;
import br.com.goncalves.pugnotification.notification.PugNotification;

public class DismissPendingIntentBroadCast implements PendingIntentNotification {
    private final Bundle mBundle;
    private final int mIdentifier;

    public DismissPendingIntentBroadCast(Bundle bundle, int identifier) {
        this.mBundle = bundle;
        this.mIdentifier = identifier;
    }

    @Override
    public PendingIntent onSettingPendingIntent() {
        Intent clickIntentBroadcast = new Intent(BroadcastActions.ACTION_PUGNOTIFICATION_DIMISS_INTENT);
        clickIntentBroadcast.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        clickIntentBroadcast.setPackage(PugNotification.mSingleton.mContext.getPackageName());
        if (mBundle != null) {
            clickIntentBroadcast.putExtras(mBundle);
        }

        return PendingIntent.getBroadcast(PugNotification.mSingleton.mContext, mIdentifier, clickIntentBroadcast,
                PendingIntent.FLAG_UPDATE_CURRENT);
    }

}
