package apotikku.hummasoft.com.apotikku;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import apotikku.hummasoft.com.apotikku.LocationUtil.LocationHelper;
import apotikku.hummasoft.com.apotikku.RecyclerViewAdapter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class terdekat_apotik extends Fragment implements Constant,GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,ActivityCompat.OnRequestPermissionsResultCallback {
    private Location mLastLocation;
    private GoogleApiClient mGoogleApiClient;
    double latitude;
    double longitude;
    LocationHelper locationHelper;

    boolean isPermissionGranted;

    //public static final String URL = "http://192.168.1.10/php/";
    private List<Result> results=new ArrayList<>();
    private RecyclerViewAdapter viewAdapter;
    private GoogleMap mMap;
//    String latitude;
//    String longitude;
//    String nama_apotik;
    private static boolean sIsAlarmInit=false;
    int mIndex =0;
//    Location mLastLocation;
    ProgressBar progressBar;
    SearchView pencarian;

    private RecyclerView recyclerView;

    public static terdekat_apotik newInstance(Location location) {
        terdekat_apotik fragment = new terdekat_apotik();
        Bundle args = new Bundle();
//        args.putParcelable( EXTRA_LAST_LOCATION,location );
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState) {
        super.onCreate( saveInstanceState );



//        if (mLastLocation != null) {
//            latitude=mLastLocation.getLatitude();
//            longitude=mLastLocation.getLongitude();
//
//            System.out.println("lokasi saya"+latitude);
//            System.out.println("lokasi"+longitude);
//            getAddress();
//        }
    }

    public void getAddress()
    {
        Address locationAddress;

        locationAddress=locationHelper.getAddress(latitude,longitude);

        if(locationAddress!=null)
        {

            String address = locationAddress.getAddressLine(0);
            String address1 = locationAddress.getAddressLine(1);
            String city = locationAddress.getLocality();
            String state = locationAddress.getAdminArea();
            String country = locationAddress.getCountryName();
            String postalCode = locationAddress.getPostalCode();


            String currentLocation;

            if(!TextUtils.isEmpty(address))
            {
                currentLocation=address;

                if (!TextUtils.isEmpty(address1))
                    currentLocation+="\n"+address1;

                if (!TextUtils.isEmpty(city))
                {
                    currentLocation+="\n"+city;

                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation+=" - "+postalCode;
                }
                else
                {
                    if (!TextUtils.isEmpty(postalCode))
                        currentLocation+="\n"+postalCode;
                }

                if (!TextUtils.isEmpty(state))
                    currentLocation+="\n"+state;

                if (!TextUtils.isEmpty(country))
                    currentLocation+="\n"+country;

//                tvEmpty.setVisibility(View.GONE);
//                tvAddress.setText(currentLocation);
//                tvAddress.setVisibility(View.VISIBLE);
//
//                if(!btnProceed.isEnabled())
//                    btnProceed.setEnabled(true);
            }

        }
//        else
//            showToast("Something went wrong");
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        locationHelper.onActivityResult(requestCode,resultCode,data);
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState) {
        getActivity().setTitle("Apotikku");
        View view = inflater.inflate( R.layout.fragment_terdekat_apotik, null, false );
        setHasOptionsMenu(true);

        recyclerView = (RecyclerView) view.findViewById( R.id.recyclerView );
        progressBar = (ProgressBar) view.findViewById( R.id.progress_bar );

        viewAdapter = new RecyclerViewAdapter( getContext(), results );
        RecyclerView.LayoutManager mLayoutManager=new LinearLayoutManager( getContext() );

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( new LinearLayoutManager());
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter( viewAdapter );

        System.out.println( "ipnya" + Config.BASE_URL_APOTIK );
        loadDataMahasiswa();

        System.out.println("masuk");

        locationHelper=new LocationHelper(getContext());
        locationHelper.checkpermission();


        if (mLastLocation != null) {
            latitude=mLastLocation.getLatitude();
            longitude=mLastLocation.getLongitude();

            System.out.println("lokasi saya"+latitude);
            System.out.println("lokasi saya"+longitude);
            getAddress();
        }

        init( view );
        return view;
    }

    private void loadDataMahasiswa() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Config.BASE_URL_APOTIK )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        RegisterAPI api = retrofit.create( RegisterAPI.class );
        Call<Value> call = api.view();
        call.enqueue( new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                progressBar.setVisibility( View.GONE );
                if (value.equals( "1" )) {
                    results = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter( getContext(), results );
                    recyclerView.setAdapter( viewAdapter );
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
            }
        } );
    }

    protected void init(View view) {
        if (mLastLocation == null) {
            System.out.println("kosong" + mLastLocation);
            return;
        }
    }

    private void updateAlarmStatus() {

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SET_ALARM) {
            if (resultCode == Activity.RESULT_OK) {
                updateAlarmStatus();
            } else {
                super.onActivityResult( requestCode, resultCode, data );
            }
        }
    }
    public void setLocation(Location location) {
        mLastLocation=location;
//  AppSettings.getInstance().setLatFor(mIndex, location.getLatitude());
//  AppSetings.getInstance().setLatFor(mIndex, location.gatLatitude());
        if (isAdded()) {

            init( getView());
        }

    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        System.out.println("masukmap");
//
//        System.out.println("latitude"+latitude);
//        System.out.println("longitude"+longitude);
//
//        LatLng Lokasiapotik = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
//        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Lokasiapotik,4));
//        Marker marker =  googleMap.addMarker(new MarkerOptions().position(Lokasiapotik)
//                .title(nama_apotik)
//                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapdes1)));
//        marker.showInfoWindow();
//        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lokasiapotik));
//
//        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
//                    && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION
//                        , Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
//            } else {
//                mMap.setMyLocationEnabled(true);
//            }
//        } else {
//            mMap.setMyLocationEnabled(true);
//        }
//
//        mMap.setOnMyLocationButtonClickListener(this);
//        mMap.setOnMyLocationClickListener(this);
//    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(), "Permission Granted", Toast.LENGTH_SHORT).show();
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    mMap.setMyLocationEnabled(true);
                }
            } else {
                Toast.makeText(getContext(), "Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

//    @Override
//    public boolean onMyLocationButtonClick() {
//        Toast.makeText(getContext(), "My Location Button Clicked", Toast.LENGTH_SHORT).show();
//        return true;
//    }
//
//    @Override
//    public void onMyLocationClick(@NonNull Location location) {
//        Toast.makeText(getContext(), "Lokasiku saat ini : " + location, Toast.LENGTH_LONG).show();
//    }

}
