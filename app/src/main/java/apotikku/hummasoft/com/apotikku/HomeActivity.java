package apotikku.hummasoft.com.apotikku;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.msarhan.ummalqura.calendar.UmmalquraCalendar;
//import distroku.hummasoft.com.distroku.Adapters.RecyclerViewAdapter;
//import distroku.hummasoft.com.distroku.Adapters.Result;
//import distroku.hummasoft.com.distroku.ModelClasses.RegisterAPI;
//import distroku.hummasoft.com.distroku.ModelClasses.Value;
//import distroku.hummasoft.com.distroku.fragments.InitialConfigFragment;
//import distroku.hummasoft.com.distroku.fragments.LocationHalper;
//import distroku.hummasoft.com.distroku.network.config.Config;
//import distroku.hummasoft.com.distroku.util.AppSettings;
import net.alhazmy13.hijridatepicker.date.hijri.HijriDatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

//import static distroku.hummasoft.com.distroku.Constants.LOCATION_FRAGMENT;
//import static distroku.hummasoft.com.distroku.Constants.REQUEST_ONBOARDING;
//import static distroku.hummasoft.com.distroku.Constants.REQUEST_CHECK_SETTINGS;
//import static distroku.hummasoft.com.distroku.Constants.REQUEST_TNC;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,Constant,
        LocationHalper.LocationCallback,HijriDatePickerDialog.OnDateSetListener{

    //User user;
    TextView terdekat,terpopuler;
    LinearLayout Tentang,Sepatu,Pakaian,Favorit;
    //private DBManager dbManager;
    private LocationHalper mLocationHelper;
    private Location mLastLocation = null;
    FragmentTransaction transaction;
    //    String id_user, fotonya, nama, email, nope, password, id_admin;
    //DrawerLayout drawer;
    private List<Result> results = new ArrayList<Result>();
    private TextView homeBtn;
    ProgressBar progressBar2;
    //AppSettings settings;
    String alamat, nama_apotik, no_telefon, provinsi, foto_profil,
            jam_buka,id_apotik, deskripsi,status_kesehatan,galeri_apotik,latitude,longitude;
    public static void start(Context context){

        Intent intent = new Intent( context, HomeActivity.class );
        context.startActivity( intent );
    }
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_home );

        initFragment(new list_apotik());

        homeBtn = (TextView) findViewById( R.id.info );
        homeBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generate method stub
                startActivity( new Intent( HomeActivity.this, MainActivity.class ));
                finish();
            }
        });

//        dbManager = new DBManager(this);
//        dbManager.open();
//        user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
//
//        dbManager.insert("telo", "2", "deskripsi telo");
//        dbManager.insert("telo2", "deskripsi telo 2");
//        dbManager.insert("telo3", "deskripsi telo 3");
//
//        Cursor cursor = dbManager.fetchbyid("12");
//    }
//    dbManager.close();

//        progressBar2 = (ProgressBar)findViewById( R.id.progress_bar2 );
//        NavigationView navigationView = (NavigationView)findViewById( R.id.nav_view );
//        navigationView.setNavigationItemSelectedListener( this );
//        View headerview = navigationView.getHeaderView( 0 );

//        Menu nav_Menu = navigationView.getMenu();
//        ambilapotik(id_apotik);
//        final ImageView ivProfil = headerview.findViewById( R.id.imageView );
//        TextView namaBujank = headerview.findViewById( R.id.namanya );
//        TextView emailBujank = headerview.findViewById( R.id.emailuser );
        Toolbar toolbar = (Toolbar)findViewById( R.id.toolbar );
        setSupportActionBar( toolbar );
        HomeActivity.this.getWindow().setSoftInputMode( WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN | WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN );

        mLocationHelper = (LocationHalper)getFragmentManager().findFragmentByTag( LOCATION_FRAGMENT );

        if (mLocationHelper == null){
            mLocationHelper = LocationHalper.newInstance();
            getFragmentManager().beginTransaction().add( mLocationHelper, LOCATION_FRAGMENT ).commit();
            System.out.println("lokasinyaa" +mLastLocation);
        }

//        terdekat=(TextView)findViewById( R.id.Terdekat );
//        terpopuler=(TextView)findViewById( R.id.Terpopuler );
//        Tentang=(LinearLayout)findViewById( R.id.Tentang );
//        Sepatu=(LinearLayout)findViewById( R.id.Sepatu );
//        Pakaian=(LinearLayout)findViewById( R.id.Pakaian );
//        Favorit=(LinearLayout)findViewById( R.id.Favorit );

//        terdekat.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
////                initFragment(new Terdekat());
////                getSupportActionBar().hide();
//            }
//        });
//
//        terpopuler.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
////                initFragment(new Terpopuler());
////                getSupportActionBar().hide();
//            }
//        });
//        Tentang.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
////                initFragment(new Tentang());
////                getSupportActionBar().hide();
//            }
//        });
//
//        Sepatu.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
////                initFragment(new Sepatu());
////                getSupportActionBar().hide();
//            }
//        });
//
//        Pakaian.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view){
////                initFragment(new Pakaian());
////                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                transaction.replace( R.id.frameFragment, Pakaian.newInstance(mLastLocation));
////                transaction.commit();
////                getSupportActionBar().hide();
//            }
//        });
//
//        Favorit.setOnClickListener( new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                initFragment(new Favorit());
////                getSupportActionBar().hide();
//
//
//                //            UmmalquraCalendar now = new UmmalquraCalendar();
////            HijriDatePickerDialog dpd = HijriDatePickerDialog.newInstance(
////                    HomeActivity.this,
////                    now.get( Calendar.YEAR ),
////                    now.get( Calendar.MONTH ),
////                    now.get( Calendar.DAY_OF_MONTH )
////            );
////            dpd.setAccentColor(Color.parseColor("#0097e6"));
////            dpd.setLocale(new Locale("ar"));
////            dpd.show(getFragmentManager(),"Datepickerdialog");
////                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
////                transaction.replace(R.id.frameFragment, sholat.newIntance(mLastLocation));
////                transaction.commit();
//            }
//        });

//        if (AppSettings.getInstance(getApplicationContext()).isDefaultSet()){
        System.out.println("lokasinyaif"+mLastLocation);
//        } else {
//
//        }
//
    }
    private void initFragment(Fragment classFragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace( R.id.frameFragment, classFragment );
        transaction.commit();
    }

//    @Override
//    public void onBackPressed(){
////        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
////        if (drawer.isDrawerOpen( GravityCompat.START )){
////            drawer.closeDrawer( GravityCompat.START );
////        }else {
////            super.onBackPressed();
////        }
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
//        Handle action bar item clicks here.The action bar will
//                automatically handle clicks on the Home/Up button, so longitude
//                as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings){
//            return true;
//        }
        return super.onOptionsItemSelected( item );
    }

    @SuppressWarnings( "StatementWithEmptyBody" )
    @Override
    public boolean onNavigationItemSelected(MenuItem item){
        //Handle navigation view item clicks here.
//        int id = item.getItemId();

        //noinspection SimpliFiableIfStatement
//        if(id == R.id.new_slideshow){
//            initFragment( new SayaFragment(id_user, nama, email, nope, password));
//            drawer.closeDrawer(Gravity.START, false);
//        }

//        if (id == R.id.Terdekat){
//            UmmalquraCalendar now = new UmmalquraCalendar();
//            HijriDatePickerDialog dpd = HijriDatePickerDialog.newInstance(
//                    HomeActivity.this,
//                    now.get(Calendar.YEAR),
//                    now.get(Calendar.MONTH),
//                    now.get(Calendar.DAY_OF_MONTH)
//            );
//            dpd.setAccentColor( Color.parseColor( "#0097e6" ));
//            dpd.setLocale(new Locale("ar"));
//            dpd.show( getFragmentManager(), "Datepickerdialog");
////            drawer.closeDrawer(Gravity.START, false);
//        }
//        if (id== R.id.Terpopuler){
////            PrefUtil.clear(HomeActivity.this);
//            startActivity( new Intent( HomeActivity.this,MainActivity.class ));
//            finish();
//        }
//        if (id== R.id.nav_masjid){
        Intent i = new Intent( getBaseContext(), PropertyDetailsActivity.class );
        i.putExtra( "alamat", alamat);
        i.putExtra( "latitude", latitude );
        i.putExtra( "longitude", longitude );
        i.putExtra( "nama_apotik", nama_apotik );
        i.putExtra( "deskripsi",deskripsi );
        i.putExtra( "no_telp", no_telefon );
        i.putExtra( "provinsi", provinsi );
        i.putExtra( "status_kesehatan",status_kesehatan );
        i.putExtra( "foto_barang", foto_profil );
        i.putExtra( "jam_buka", jam_buka );
        i.putExtra( "id_distro", id_apotik);
        i.putExtra( "foto_profil", foto_profil );
        i.putExtra( "galeri_apotik", galeri_apotik);
        startActivity( i );
//        }
        return true;
    }
    @Override
    protected void onResume(){
        super.onResume();

        if (mLastLocation == null){
            fetchLocation();
        }
    }

    @Override
    public void onLocationSettingsFailed(){

    }

    @Override
    public void onLocationChanged(Location location){
        mLastLocation = location;
        System.out.println("jalankan2home" +mLastLocation);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.replace(R.id.frameFragment, list_apotik.newInstance(mLastLocation));
        transaction.commit();
    }

    private void fetchLocation(){
        if (mLocationHelper != null){
            mLocationHelper.checkLocationPermissions();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_CHECK_SETTINGS){
            switch (resultCode){
                case Activity.RESULT_OK:
                    //All required changes were succesfully made
                    fetchLocation();
                    break;
                case Activity.RESULT_CANCELED:
                    //The user was asked to change settings, but chose not to
                    onLocationSettingsFailed();
                    break;
                default:
                    onLocationSettingsFailed();
                    break;
            }
        } else if (requestCode == REQUEST_ONBOARDING){
            if (resultCode == RESULT_OK){
                onUseDefaultSelected();
            }
        } else if (requestCode == REQUEST_TNC){
            if (resultCode == RESULT_OK){
                finish();
            }else {
//                AppSettings settings = AppSettings.getInstance(this);
//                settings.set(AppSettings.key.IS_TNC_ACCEPTED, true);
            }
        }else {
            super.onActivityResult( requestCode, resultCode, data );
        }
    }

    //    @Override
    public void onConfigNowSelected(int num){startOnboardingFor(num);}

    //    @Override
    public void onUseDefaultSelected(){
        if (mLastLocation != null){
            //NOT THE BEST SOLUTION, THINK OF SOMETHING ELSE

        }
    }
    private void startOnboardingFor(int index){
//        Intent intent = new Intent( getApplicationContext(),OnboardingActivity.class );
//        intent.putExtra( OnboardingActivity.EXTRA_CARD_INDEX, index );
//        startActivityForResult( intent, REQUEST_ONBOARDING );
    }

    @Override
    public void onDateSet(HijriDatePickerDialog view, int year, int monthOfYear, int dayOfMonth){

    }

    public void ambilapotik(String newText){
        progressBar2.setVisibility( View.VISIBLE );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL_APOTIK )
                .addConverterFactory (GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create( RegisterAPI.class );
        Call<Value>call = api.getdatapotik(newText);
        call.enqueue( new Callback<Value>(){
            @Override
            public void onResponse(Call<Value>call, Response<Value> response){
                String value  = response.body().getValue();
                progressBar2.setVisibility( View.GONE );
                if (value.equals( "1" )){
                    results = response.body().getResult();
                    Result result2 = results.get(0);
                    alamat = result2.getalamat();
                    latitude = result2.getlatitude();
                    longitude = result2.getlongitude();
                    nama_apotik = result2.getnama_apotik();
                    deskripsi = result2.getdeskripsi();
                    no_telefon = result2.getno_telefon();
                    provinsi = result2.getprovinsi();
                    jam_buka = result2.getjam_buka();
                    id_apotik = result2.getid_apotik();
                    foto_profil = result2.getfoto_profil();
                    galeri_apotik = result2.getgaleri_apotik();

                    Location locl = new Location( "" );
//                    locl.setLatitude( settings.getLatFor(0));
//                    locl.getLongitude(settings.getLngFor(0));

                    Location loc2 = new Location( "" );
//                    loc2.setLatitude(Double.parseDouble( result2.getlatitude()));
//                    loc2.setLongitude(Double.parseDouble( result2.getlongitude ()));
                    float distanceInMeters = locl.distanceTo( loc2 );
//                    jarak_masjid = String.format( "%.0f", distanceInMeters )+"Meter";

                    System.out.println("bbbb"+alamat);
                    System.out.println("bbbb"+latitude);
                    System.out.println("bbbb"+longitude);
                    System.out.println("bbbb"+nama_apotik);
                    System.out.println("bbbb"+deskripsi);
                    System.out.println("bbbb"+no_telefon);
                    System.out.println("bbbb"+provinsi);
                    System.out.println("bbbb"+status_kesehatan);
                    System.out.println("bbbb"+jam_buka);
                    System.out.println("bbbb"+id_apotik);
                    System.out.println("bbbb"+foto_profil);
                    System.out.println("bbbb"+ galeri_apotik);
                }
            }

            @Override
            public void onFailure(Call<Value>call, Throwable t){
                progressBar2.setVisibility( View.GONE );
            }
        });

    }

}


