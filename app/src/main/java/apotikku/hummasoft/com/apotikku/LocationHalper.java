package apotikku.hummasoft.com.apotikku;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStatusCodes;

import apotikku.hummasoft.com.apotikku.Constant;
import apotikku.hummasoft.com.apotikku.PermissionUtil;

public class LocationHalper extends Fragment implements Constant, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private static Location sLastLocation;

    GoogleApiClient mGoogleApiClient;
    LocationRequest mCoarseLocationRequest;


    private LocationCallback mCallback;
    private Activity mActivity;
    private static boolean sLoationPermissionDenied;

    public static LocationHalper newInstance() {return new LocationHalper(); }

    public LocationHalper(){
        //Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof LocationCallback) {
            mCallback = (LocationCallback) activity;
        }else {
            throw new IllegalArgumentException("activity must extend BaseActivity and implement LocationHelper.LocationCallback");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mCallback = null;
        mActivity = null;
    }

    public Location getLocation() {return sLastLocation; }

    @SuppressLint("NewApi")
   public void checkLocationPermissions(){
        if (PermissionUtil.haSelfPermission( getActivity(),Manifest.permission.ACCESS_FINE_LOCATION )){
            initAppAfterCheckingLocation();
        }else {
            //UNCOMMENT TO SUPPORT ANDROID M RUNTIME PERMISSIONS
            //intent intent = mActivity.getPackageManager().buildRequestPermissionsIntent(new String[]Manifest.permission.ACCES_FINE_LOCATION));
            //starActivityForResult(intent, REQUEST_LOCATION);
            if (!sLoationPermissionDenied) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
            }
        }
    }

    private void initAppAfterCheckingLocation() {
        if (mGoogleApiClient == null) {
            buildGoogleApiClient();
        } else if (sLastLocation == null) {
            if (mGoogleApiClient.isConnected()) {
                //check for a location.
                checkIfLocationServicesEnabled();
            }//else if (mGoogleApiClient.isConnecting()){
            //do nothing
            //}
        } else {
            Log.d( "salaatTimesActivity", sLastLocation.getLatitude() + "," + sLastLocation.getLongitude() );
            if (mCallback != null) {
                mCallback.onLocationChanged( sLastLocation );
            }
        }
    }
    private synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
    }
    @SuppressLint("MissingPermission")
    private void checkLocationInit() {
        if (sLastLocation == null) {
            sLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        }
        if (sLastLocation == null) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, createLocationRequest(), new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    sLastLocation = location;
                    if (mCallback != null) {
                        mCallback.onLocationChanged( sLastLocation );
                    }
                    initAppAfterCheckingLocation();
                    LocationServices.FusedLocationApi.removeLocationUpdates( mGoogleApiClient, this );
                }
            });
            } else {
                initAppAfterCheckingLocation();
            }
        }

        private LocationRequest createLocationRequest() {
            if (mCoarseLocationRequest == null) {
                mCoarseLocationRequest = new LocationRequest();
                mCoarseLocationRequest.setInterval(5000);
                mCoarseLocationRequest.setFastestInterval(1000);
                mCoarseLocationRequest.setNumUpdates(1);
                mCoarseLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
            }
            return mCoarseLocationRequest;
        }

        private void checkIfLocationServicesEnabled() {
            LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                    .addLocationRequest(createLocationRequest());
            PendingResult<LocationSettingsResult> result = LocationServices.SettingsApi.checkLocationSettings(mGoogleApiClient, builder.build());
            result.setResultCallback( new ResultCallback<LocationSettingsResult>() {

                @Override
                public void onResult(LocationSettingsResult result) {
                    final Status status=result.getStatus();
                    switch (status.getStatusCode()) {
                        case LocationSettingsStatusCodes.SUCCESS:
                            //all location settings are satisfied. The client can initialiare location
                            // requests here.
                            checkLocationInit();
                            break;
                        case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                            //Location settings are nit satisfied. But could be fixed by showing the user
                            //a dialog.
                            try {
                                //show the dialog by calling startResolutionForResult(),
                                //and check the result in onActivityResult().
                                status.startResolutionForResult( getActivity(), REQUEST_CHECK_SETTINGS );
                            } catch (IntentSender.SendIntentException e) {
                                // Ignore the error.
                            }
                            break;
                        case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                            //location settings are not satisfied. Homewer, we have no way to fix the
                            //settings so we won't show the dialog.
                            break;
                    }
                }
            });
            }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //final LocationSettingsStates states - LcationSettingsStates.fromIntent(data);
        switch (requestCode) {
            case REQUEST_CHECK_SETTINGS:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        //All required changes were succesfully made
                        checkLocationInit();
                        break;
                    case Activity.RESULT_CANCELED:
                        //The user was asked to change settings,but chose not to
                        if(mCallback != null) {
                            mCallback.onLocationSettingsFailed();
                        }
                        break;
                    default:
                        if (mCallback != null) {
                            mCallback.onLocationSettingsFailed();
                        }
                        break;
                }
                break;
        }
    }
    @Override
    public void onConnected(Bundle bundle) {
        checkIfLocationServicesEnabled();
    }
    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult){

    }

    public void setLoationPermissionDenied(boolean mLoationPermissionDenied) {
        this.sLoationPermissionDenied = mLoationPermissionDenied;
    }

    public static boolean isLoationPermissionDenied() {
        return sLoationPermissionDenied;
    }
    //@Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                checkLocationPermissions();
            }else {
                Log.i("BaseActivity", "LOCATION permission was NOT granted.");
                setLoationPermissionDenied(true);
            }
        }else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    public void requestPassiveLocationUpdates(Context context, PendingIntent pendingIntent) {
        long oneHourInMillis = 3600000;
        float fiftyKinMeters = 50000.0f;

       LocationManager locationManager = (LocationManager) context.getSystemService( Context.LOCATION_SERVICE );
       try {
           locationManager.requestLocationUpdates( LocationManager.PASSIVE_PROVIDER,
                   oneHourInMillis, fiftyKinMeters, pendingIntent);
        } catch (SecurityException se) {
            //do nothing.We should always have permision in order to reach this screen.
        }
    }

    public void removePassiveLocationUpdates(Context context, PendingIntent pendingIntent) {
        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.removeUpdates(pendingIntent);
        }catch (SecurityException se){
            //do nothing.We should always have permision in order to reach this screen.
        }

    }


    public interface LocationCallback {
        void onLocationSettingsFailed();
        void onLocationChanged(Location location);
    }
}
