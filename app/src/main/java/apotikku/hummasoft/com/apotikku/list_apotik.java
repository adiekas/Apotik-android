package apotikku.hummasoft.com.apotikku;

import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;




public class list_apotik extends Fragment implements Constant,NavigationView.OnNavigationItemSelectedListener,
        LocationHalper.LocationCallback {

    TextView frag_masjid, frag_sholat;

    LinearLayout lay_kiri, lay_kanan;

    private LocationHalper mLocationHalper;
    private Location mLastLocation = null;
    FragmentTransaction transaction;

    public static list_apotik newInstance(Location location) {
        list_apotik fragment = new list_apotik();
        // Bundle args = new Bundle();
        //args.putParce;ab;e(EXTRA_LAST_LOCATION, location);
        //fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        //if (getArguments() = != null) {
        //mLastLocation = (Location) getArguments().getParcelable(EXTRA_LAST_LOCATION);
        //}
        System.out.println("lokasinya listapotik" + mLastLocation);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listapotik, null, false);

        //mLocationHelper = (LocationHeper) getFragmentManager().findFragmentByTag(LOCATION_FRAGMENT);

        frag_masjid = (TextView) view.findViewById(R.id.fragmasjid);
        frag_sholat = (TextView) view.findViewById(R.id.fragsholat);
        lay_kiri = (LinearLayout) view.findViewById(R.id.laykiri);
        lay_kanan = (LinearLayout) view.findViewById(R.id.laykanan);

        //initFramentsnya(new shilat());


        System.out.println("lokasinya listapotik" + mLastLocation);

        transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.framedatamasjid, terdekat_apotik.newInstance(mLastLocation));
        transaction.commit();

        lay_kanan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framedatamasjid, data_apotik.newInstance(mLastLocation));
                transaction.commit();

                frag_sholat.setTextColor(Color.parseColor("#ffffff"));
                lay_kiri.setBackgroundColor(Color.parseColor("#3498db"));

                frag_masjid.setTextColor(Color.parseColor("#3498db"));
                lay_kanan.setBackgroundColor(Color.parseColor("#ffffff"));
            }
        });
        lay_kiri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.framedatamasjid, terdekat_apotik.newInstance(mLastLocation));
                transaction.commit();

                frag_sholat.setTextColor(Color.parseColor("#3498db"));
                lay_kiri.setBackgroundColor(Color.parseColor("#ffffff"));

                frag_masjid.setTextColor(Color.parseColor("#ffffff"));
                lay_kanan.setBackgroundColor(Color.parseColor("#3498db"));
                //getSupportActionBar().hide();
            }
        });
        //
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mLastLocation == null) {
            fetchLocation();
        }
    }

    @Override
    public void onLocationSettingsFailed() {

    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        System.out.println("lokasinya listapotik" + mLastLocation);
        //android.support.v4.app.FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.frame_layout, MenuUtama.newInstance(mLastLOcation));

        //trasaction.commit();

    }

    private void fetchLocation() {
        if (mLastLocation != null) {
            mLocationHalper.checkLocationPermissions();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CHECK_SETTINGS) {
            switch (resultCode) {
                case RESULT_OK:

                    // All required changed were succesfully made
                    fetchLocation();
                    break;
                case RESULT_CANCELED:
                    //The user was asked to change settings, but chose not to
                    onLocationSettingsFailed();
                    break;
                default:
                    onLocationSettingsFailed();
                    break;
            }
        } else if (requestCode == REQUEST_ONBOARDING) {
            if (resultCode == RESULT_OK) {
            }
        } else if (requestCode == REQUEST_TNC) {
            if (resultCode == RESULT_CANCELED) {

            } else {
                //AppSettings settings = AppSettings.getInstance(getActivity());
                //settings.set(AppSettings.Key.IS_TNC_ACCEPTED, true);
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}