package sms.backup.tech.soft.lockscreen;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        boolean check = getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        if(Build.VERSION.SDK_INT >= 23) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, 1234);
            }

        }
        if(
                (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)  != PackageManager.PERMISSION_GRANTED)){
            // user permission not granted
            // ask permission
            requestPermissions(new String[]{ Manifest.permission.CAMERA},2);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
