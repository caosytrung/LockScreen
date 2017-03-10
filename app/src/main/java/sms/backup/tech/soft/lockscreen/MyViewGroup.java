package sms.backup.tech.soft.lockscreen;

import android.content.Context;
import android.graphics.Camera;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.RelativeLayout;

/**
 * Created by dee on 10/03/2017.
 */

public class MyViewGroup extends RelativeLayout {
    private ICallback iCallback;



    public MyViewGroup(Context context,ICallback iCallback) {
        super(context);
        this.iCallback = iCallback;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            Log.d("asdsa","asdadad");
            iCallback.callback();
        }
        return super.dispatchKeyEvent(event);
    }

    public interface ICallback{
        public void callback();
    }
}
