package apotikku.hummasoft.com.apotikku;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionCheck {
    private Activity mActivity;
    private RequestPermissionListener mRequestPermissionListener;
    private int mRequestCode;

    public void requestPermission(Activity activity, @NonNull String[] permissions, int requestCode,
                                  RequestPermissionListener listener){
        mActivity = activity;
        mRequestCode = requestCode;
        mRequestPermissionListener = listener;

        if (!needRequestRuntimePermissions()){
            mRequestPermissionListener.onSucces();
            return;
        }
        requestUnGrantedPermissions(permissions, requestCode);
    }

    private boolean needRequestRuntimePermissions(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
    }

    private void requestUnGrantedPermissions(String[] permissions,int requestCode){
        String[] unGrantedpermissions = findUnGrantedPermissions(permissions);
        if (unGrantedpermissions.length == 0){
            mRequestPermissionListener.onSucces();
            return;
        }
        ActivityCompat.requestPermissions( mActivity, unGrantedpermissions, requestCode );
    }

    private boolean isPermissionGranted(String permission){
        return ActivityCompat.checkSelfPermission( mActivity, permission )
                == PackageManager.PERMISSION_GRANTED;
    }

    private String[] findUnGrantedPermissions(String[] permissions){
        List<String> unGrantedPermissionList = new ArrayList<>(  );
        for (String permission : permissions){
            if (!isPermissionGranted(permission)){
                unGrantedPermissionList.add( permission );
            }
        }
        return unGrantedPermissionList.toArray(new String[0]);
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantedResults){
        if (requestCode == mRequestCode){
            if (grantedResults.length >0){
                for (int grantedResult : grantedResults){
                    if (grantedResult != PackageManager.PERMISSION_GRANTED){
                        mRequestPermissionListener.onFailed();
                        return;
                    }
                }
                mRequestPermissionListener.onSucces();
            }else {
                mRequestPermissionListener.onFailed();
            }
        }
    }

    public interface RequestPermissionListener{
        void onSucces();
        void onFailed();
    }

}
