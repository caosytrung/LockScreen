package sms.backup.tech.soft.lockscreen;

import android.app.Service;
import android.content.Intent;
import android.graphics.Camera;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

/**
 * Created by dee on 10/03/2017.
 */

public class LockService extends Service implements View.OnClickListener, MyViewGroup.ICallback,Runnable {
    private MyViewGroup myViewGroup;
    private WindowManager mWindowManager;
    private WindowManager.LayoutParams mParams;
    private Button btnTurnOFF;
    private android.hardware.Camera mCamera;
    private Thread thread;
    android.hardware.Camera.Parameters mParameters;
    private Button btnzz;


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        initViews();
        mCamera = android.hardware.Camera.open();
        startThread();
        return START_STICKY;

    }

    private void startThread(){

        thread = new Thread(this);
        thread.start();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    private void initCamera(){
//        camera  = android.hardware.Camera.open();
//        android.hardware.Camera.Parameters parameters = camera.getParameters();
//        parameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
//        camera.setParameters(parameters);
//
//
//    }

    public void initViews() {
        mWindowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
        mParams = new WindowManager.LayoutParams();
        mParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        mParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        mParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        mParams.flags = WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS;
        initViewGroup();
        mWindowManager.addView(myViewGroup, mParams);


    }

    private void initViewGroup(){
        myViewGroup = new MyViewGroup(this,this);

        View vContentView = View.inflate(this,R.layout.lock_screen,myViewGroup);
        btnTurnOFF = (Button) vContentView.findViewById(R.id.btnTurnOffLockScreen);
        btnTurnOFF.setOnClickListener(this);
        vContentView.findViewById(R.id.btnOff).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnOff:
                turnOff = true;

            break;
            case R.id.btnTurnOffLockScreen:
                mParameters = mCamera.getParameters();
                mParameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                mCamera.setParameters(mParameters);
                mCamera.startPreview();
            break;
        }
    }

    @Override
    public void callback() {
        btnTurnOFF.setText("aasdas");
        mWindowManager.removeView(myViewGroup);
    }
    boolean isOpen;
    boolean turnOff =false;
    int a =0;
    @Override
    public void run() {
        while (true){



            if (a > 1000){
                if (!isOpen){
                    mParameters = mCamera.getParameters();
                    mParameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_OFF);
                    mCamera.setParameters(mParameters);

                    isOpen = true;

                }
                else {
                    mParameters = mCamera.getParameters();
                    mParameters.setFlashMode(android.hardware.Camera.Parameters.FLASH_MODE_TORCH);
                    mCamera.setParameters(mParameters);
                    mCamera.startPreview();
                    isOpen = false;
                }
            }
            Log.d("qqasdasdas",a + "");
            a += 200;
            if (turnOff){
                mCamera.stopPreview();
                mCamera.release();
                break;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
