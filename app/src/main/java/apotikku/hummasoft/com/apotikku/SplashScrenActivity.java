package apotikku.hummasoft.com.apotikku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

//import apotikku.hummasoft.com.apotikku.fragments.InitialConfigFragment;
  import apotikku.hummasoft.com.apotikku.LocationHalper;
//import apotikku.hummasoft.com.apotikku.intro.WelcomeActivity;
//import apotikku.hummasoft.com.apotikku.util.AppSettings;

public class SplashScrenActivity extends AppCompatActivity implements Constant,LocationHalper.LocationCallback {
    private LocationHalper mLocationHelper;
    private Location mLastLocation = null;
    private PermissionCheck permissionCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Menghilangkan action bar
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //AppSettings settings = AppSttings.getInstance(this);
        //setting.st(AppSettings.Key.HAS_DEFAULT_SET, true);

        permissionCheck = new PermissionCheck();

        if(Build.VERSION.SDK_INT >= 21){
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("0097e6"));
        }
        setContentView(R.layout.splash_screen);

        mLocationHelper = (LocationHalper) getFragmentManager().findFragmentByTag(LOCATION_FRAGMENT);

        if(mLocationHelper == null) {
            mLocationHelper = LocationHalper.newInstance();
            getFragmentManager().beginTransaction().add(mLocationHelper, LOCATION_FRAGMENT).commit();
            System.out.println("jalankan2"+mLastLocation);
        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashScrenActivity.this, HomeActivity.class));
                finish();
            }

        },3000L); //3000 L = 3 detik
    }
    @Override
    protected void onResume(){
        super.onResume();

        if (mLastLocation == null ) {
            fetchLocation();
        }
    }
    @Override
    public void onLocationSettingsFailed() {

    }

    @Override
    public void onLocationChanged(Location location){
        mLastLocation = location;
        System.out.println("jalankan2sss"+mLastLocation);

//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.replace(R.id.framefragment, sholat.newInstance(mLastLocation));
//        transaction.commit();
    }
    private void fetchLocation() {
        if (mLocationHelper != null) {
            mLocationHelper.checkLocationPermissions();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode){
                case Activity.RESULT_OK:
                    //All required changes were succecsfully made
                    fetchLocation();
                    break;
                case Activity.RESULT_CANCELED:
                    //the user was asked to change settings, but chose not to
                    onLocationSettingsFailed();
                    break;
                default:
                    onLocationSettingsFailed();
                    break;
            }
        } else if (requestCode == REQUEST_ONBOARDING){
            if (resultCode == RESULT_OK) {

            }
        }else if (requestCode == REQUEST_TNC) {
            if (resultCode == RESULT_CANCELED) {
                finish();
            }else {

            }
        }else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

//    private void startOnboardingFor(int index) {
//        Intent intent = new Intent(getApplicationContext(), OnboardingActivity.class);
//        Intent.putExtra(OnboardingActivity.EXTRA_CARD_INDEX, index);
//        startActivityForResult(intent, REQUEST_ONBOARDING);
//    }
}
