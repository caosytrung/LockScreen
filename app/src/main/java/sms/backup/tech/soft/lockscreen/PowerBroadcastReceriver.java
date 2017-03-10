package sms.backup.tech.soft.lockscreen;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by dee on 10/03/2017.
 */

public class PowerBroadcastReceriver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        switch (intent.getAction()){
            case Intent.ACTION_POWER_CONNECTED:
                context.startService(new Intent(context,LockService.class));
                break;
        }
    }
}
