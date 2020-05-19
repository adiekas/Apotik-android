package apotikku.hummasoft.com.apotikku;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;
import apotikku.hummasoft.com.apotikku.Adapters.MyCustomPagerAdapter;
//import apotikku.hummasoft.com.apotikku.Adapters.MyRecyclerfasilitasAdapter;
//import distroku.hummasoft.com.distroku.User;
//import distroku.hummasoft.com.distroku.PrefUtil;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

    public class detail_apotik extends AppCompatActivity implements OnMapReadyCallback
            ,GoogleMap.OnMyLocationButtonClickListener
            ,GoogleMap.OnMyLocationClickListener{
        private Context context;
        private ImageView galeriBtn;
        private GoogleMap mMap;
        ViewPager viewPager;
        //    public static final String URL = "http://192.168.1.10/php/";
        private List<Result> resultsnya = new ArrayList<Result>();
        private List<String> images = new ArrayList<String>();
        //    int images[] = {R.drawable.property,R.drawable.property,R.drawable.property,R.drawable.property,R.drawable.property,R.drawable.property};
        MyCustomPagerAdapter myCustomPagerAdapter;
        SupportMapFragment mapFragment;
        //    AppSettings settings;
        private TextView imageNo;
        TextView textViewnama;
//                TextView textViewsenin,textViewseninWaktu,textViewselasa,textViewselasaWaktu,textViewrabu,textViewrabuWaktu,textViewkamis,
//                textViewkamisWaktu,textViewjumat,textViewjumatWaktu,textViewsabtu,textViewsabtuWaktu,textViewminggu,textViewmingguWaktu;
        ImageView back,share,telefon,rutex;
        TextView lay_nama,lay_alamat;
        TextView textViewdeskripsi,textViewdetaildes;
        TextView totalnox;
        TextView nomor;
        ImageView imageViewfoto_profil;
        ImageView imageViewimageView1;
        ImageView imageViewimageView2;
        TextView textViewno_telepon;
        TextView textViewprovinsi;
        TextView textViewjam_buka;

//        TextView textViewluas_tanah;
//        TextView textViewstatus_tanah;
//        TextView textViewluas_bangunan;
//        TextView textViewtahun_berdiri;
//        TextView textViewketearangan;
//        TextView textViewfasilitas;
//        TextView textViewlatitude;
//    TextView textViewlongitude;
    TextView textViewjarak;
//    String latitude,longitude;
    private boolean isCheck;
//    private MyRecyclerfasilitasAdapter adapter;
    FloatingActionMenu materialDesignFAM;
    String nama_apotik;
    String id_apotik;
    String deskripsi;
    String provinsi;
    String status_kesehatan;
    String foto_profil;
    String jam_buka;
    String no_telefon;
    String alamat;
    String latitude;
    String longitude;
    String galeri_apotik;
    FloatingActionButton Floatb1, Floatb2, Floatb3,Floatb4,Floatb5;
//    User user;
//
//    private ArrayList<PropertyImageModel> homeListModelClassArrayList;
//    private RecyclerView recyclerView1;
//    private PropertyImageAdapter mAdapter;
//     private Integer image[]={R.drawable.property,R.drawable.property,R.drawable.property,
//             R.drawable.property,R.drawable.property,R.drawable.property};
//     private String imageNo[]={"1","2","3","4","5","6"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle datanya=getIntent().getExtras();
        nama_apotik = datanya.getString( "nama_apotik" );
        alamat = datanya.getString("alamat");
        deskripsi = datanya.getString("deskripsi");
        provinsi = datanya.getString("provinsi");
        status_kesehatan = datanya.getString("status_kesehatan");
        foto_profil = datanya.getString("foto_profil");
        jam_buka = datanya.getString("jam_buka");
        no_telefon= datanya.getString("no_telefon");
        latitude= datanya.getString("latitude");
        longitude = datanya.getString("longitude");
        id_apotik= datanya.getString("id_apotik");
        galeri_apotik= datanya.getString("galeri_apotik");
        System.out.println("idnyamasjid "+id_apotik);
//        ambilgambarnya(id_apotik);
//        settings= AppSettings.getInstance(getApplicationContext());
        nama_apotik = datanya.getString("nama_apotik");
        if (Build.VERSION.SDK_INT >= 21) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#0097e6"));
            window.setTitle(nama_apotik);
        }
        setContentView(R.layout.activity_property_details);
//        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        galeriBtn = (ImageView)findViewById( R.id.imageView1 );
        galeriBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generate method stub
                startActivity( new Intent( detail_apotik.this, GaleriActivity.class ));
                finish();
            }
        });

        galeriBtn = (ImageView)findViewById( R.id.imageView2 );
        galeriBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generate method stub
                startActivity( new Intent( detail_apotik.this, GaleriActivity.class ));
                finish();
            }
        });

//        getSupportActionBar().hide();
//        user = PrefUtil.getUser(this, PrefUtil.USER_SESSION);
//        materialDesignFAM = (FloatingActionMenu) findViewById(R.id.material_design_android_floating_action_menu);
//
//        Floatb1 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item1);
//        Floatb2 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item2);
//        Floatb3 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item3);
//        Floatb4 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item4);
//        Floatb5 = (FloatingActionButton) findViewById(R.id.material_design_floating_action_menu_item5);
//
//        Floatb1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu first item clicked
//
//            }
//        });
//        Floatb2.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu first item clicked
//
//            }
//        });
//        Floatb3.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
////                Intent i = new Intent(getApplicationContext(), update_keterangan.class);
////                startActivity(i);
//                Intent i = new Intent(getBaseContext(), update_keterangan.class);
//                i.putExtra("nama_masjid", nama_masjid);
//                i.putExtra("alamat_masjid", alamat_masjid);
//                i.putExtra("deskripsi", deskripsi);
//                i.putExtra("luas_tanah", luas_tanah);
//                i.putExtra("status_tanah", status_tanah);
//                i.putExtra("luas_bangunan", luas_bangunan);
//                i.putExtra("tahun_berdiri", tahun_berdiri);
//                i.putExtra("no_telepon", no_telepon);
//                i.putExtra("keterangan", keterangan);
//                i.putExtra("fasilitas", fasilitas);
//                i.putExtra("latitude", latitudex);
//                i.putExtra("longitude", longitudex);
//                i.putExtra("jarak_masjid", jarak_masjid);
//                i.putExtra("id_masjid", id_masjid);
//                startActivity(i);
//
//            }
//        });
//        Floatb4.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                Intent i = new Intent(getBaseContext(), update_map.class);
//                i.putExtra("nama_masjid", nama_masjid);
//                i.putExtra("alamat_masjid", alamat_masjid);
//                i.putExtra("deskripsi", deskripsi);
//                i.putExtra("luas_tanah", luas_tanah);
//                i.putExtra("status_tanah", status_tanah);
//                i.putExtra("luas_bangunan", luas_bangunan);
//                i.putExtra("tahun_berdiri", tahun_berdiri);
//                i.putExtra("no_telepon", no_telepon);
//                i.putExtra("keterangan", keterangan);
//                i.putExtra("fasilitas", fasilitas);
//                i.putExtra("latitude", latitudex);
//                i.putExtra("longitude", longitudex);
//                i.putExtra("jarak_masjid", jarak_masjid);
//                i.putExtra("id_masjid", id_masjid);
//                startActivity(i);
//
//            }
//        });
//        Floatb5.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                //TODO something when floating action menu first item clicked
//                Intent i = new Intent(getBaseContext(), update_fasilitas.class);
//                i.putExtra("nama_masjid", nama_masjid);
//                i.putExtra("alamat_masjid", alamat_masjid);
//                i.putExtra("deskripsi", deskripsi);
//                i.putExtra("luas_tanah", luas_tanah);
//                i.putExtra("status_tanah", status_tanah);
//                i.putExtra("luas_bangunan", luas_bangunan);
//                i.putExtra("tahun_berdiri", tahun_berdiri);
//                i.putExtra("no_telepon", no_telepon);
//                i.putExtra("keterangan", keterangan);
//                i.putExtra("fasilitas", fasilitas);
//                i.putExtra("latitude", latitudex);
//                i.putExtra("longitude", longitudex);
//                i.putExtra("jarak_masjid", jarak_masjid);
//                i.putExtra("id_masjid", id_masjid);
//                startActivity(i);
//
//            }
//        });
//
//        if (user != null) {
//            String id_admin = user.getData().getid_admin();
//            System.out.println("aaa detail " + id_admin);
//            if (id_admin.equals("0")) {
//                materialDesignFAM.setVisibility(View.INVISIBLE);
//            } else {
//                materialDesignFAM.setVisibility(View.VISIBLE);
//            }
//        }
        back =(ImageView)findViewById(R.id.back);
        share= (ImageView)findViewById(R.id.share);
//        bookmarkx= (ImageView)findViewById(R.id.bookmark);
//        rutex= (ImageView)findViewById(R.id.rute);
//        telefon=(ImageView)findViewById(R.id.telp);

        textViewnama = (TextView)findViewById(R.id.Name);
//        viewPager = (ViewPager)findViewById(R.id.viewpager);
        textViewdeskripsi = (TextView)findViewById(R.id.textViewDeskripsi);
//        alamatdeskripsi = (TextView)findViewById(R.id.alamatdeskripsi);
//        textViewsenin = (TextView) findViewById( R.id.senin );
//        textViewseninWaktu = (TextView) findViewById( R.id.seninWaktu );
//        textViewselasa = (TextView) findViewById( R.id.selasa );
//        textViewselasaWaktu = (TextView) findViewById( R.id.selasaWaktu );
//        textViewrabu = (TextView) findViewById( R.id.rabu );
//        textViewrabuWaktu = (TextView) findViewById( R.id.rabuWaktu );
//        textViewkamis = (TextView) findViewById( R.id.kamis );
//        textViewkamisWaktu  = (TextView) findViewById( R.id.kamisWaktu );
//        textViewjumat = (TextView) findViewById( R.id.jumat );
//        textViewjumatWaktu = (TextView) findViewById( R.id.jumatWaktu );
//        textViewsabtu  = (TextView) findViewById( R.id.sabtu );
//        textViewsabtuWaktu  = (TextView) findViewById( R.id.sabtuWaktu );
//        textViewminggu = (TextView) findViewById( R.id.minggu );
//        textViewmingguWaktu = (TextView) findViewById( R.id.mingguWaktu );

        nomor = (TextView) findViewById( R.id.nomor );
        imageViewimageView1  = (ImageView)findViewById( R.id.imageView1 );
        imageViewimageView2 = (ImageView)findViewById( R.id.imageView2 );
        imageViewfoto_profil = (ImageView)findViewById( R.id.gambarditel );

        lay_nama = (TextView)findViewById(R.id.propertyName);
        lay_alamat = (TextView)findViewById(R.id.street1);

//        textViewluas_tanah = (TextView)findViewById(R.id.luastanah);
//        textViewstatus_tanah = (TextView)findViewById(R.id.statustanah);
//        textViewluas_bangunan = (TextView)findViewById(R.id.luasbangun);
//        textViewtahun_berdiri = (TextView)findViewById(R.id.tahunberdiri);
//        textViewno_telefon = (TextView)findViewById(R.id.nomer);

//        textViewnama = (TextView)findViewById( R.id.Name );
        textViewjam_buka = (TextView)findViewById( R.id.jam_buka ) ;
        textViewdeskripsi = (TextView)findViewById(R.id.textViewDeskripsi);
        textViewdetaildes = (TextView)findViewById(R.id.alamatdeskripsi);
        textViewno_telepon = (TextView)findViewById( R.id.nomor );
        textViewprovinsi = (TextView)findViewById( R.id.provinsideskripsi );
//        textViewjam_buka = (TextView)findViewById( R.id.seninWaktu );
//        textViewjam_buka = (TextView)findViewById( R.id.senin );


//        lay_nama.setText(nama_apotik);
//        detailatasx.setText(nama_apotik);
//        lay_alamat.setText(alamat);

//        textViewluas_tanah.setText(luas_tanah);
//        textViewstatus_tanah.setText(status_tanah);
//        textViewluas_bangunan.setText(luas_bangunan);
//        textViewtahun_berdiri.setText(tahun_berdiri);
//        textViewsenin.setText( senin );
          textViewnama.setText( nama_apotik );
          textViewno_telepon.setText(no_telefon);
          textViewdeskripsi.setText(deskripsi);
          textViewdetaildes.setText( alamat);
          textViewprovinsi.setText( provinsi );
          textViewjam_buka.setText( jam_buka );
//        textViewjarak.setText(jarak_masjid);

        Picasso.with(context)
                .load(Config.BASE_URL_GAMBAR+foto_profil)
                .placeholder(R.drawable.art)
                .into(imageViewfoto_profil);
        Picasso.with(context).setLoggingEnabled(true);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT, "ADDIN (Pusat Informasi Dakwah)");
                String message = "\n Informasi Terbaru dari "+nama_apotik+" \n\n";

                i.putExtra(Intent.EXTRA_TEXT, message);
                startActivity(Intent.createChooser(i, "Pilih Aplikasi"));
            }
        });
//        rutex.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
////                String uri = "http://maps.google.com/maps?saddr=" + settings.getLatFor(0) + "," + settings.getLngFor(0) + "&daddr=" + latitudex + "," + longitudex;
////                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
////                intent.setPackage("com.google.android.apps.maps");
////                startActivity(intent);
//            }
//        });

//        telefon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String dial ="tel:"+no_telefon;
//                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(dial));
//                startActivity(intent);
//            }
//        });

//        textViewdetaildes.setText("Selengkapnya");
//
//        textViewdetaildes.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (isCheck) {
//                    textViewdeskripsi.setMaxLines(30);
//                    textViewdetaildes.setText("Singkatnya");
//                    isCheck = false;
//                } else {
//                    textViewdeskripsi.setMaxLines(4);
//                    textViewdetaildes.setText("Selengkapnya");
//                    isCheck = true;
//                }
//            }
//        });






//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//
//                imageNo.setText("" + (position +1));
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });

        // Retrieve the content view that renders the map.

        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
//                .findFragmentById(R.id.map);
//        mapFragment.getMapAsync(this);
////
//        recyclerView1 = (RecyclerView)findViewById(R.id.recyclerView1);
//        homeListModelClassArrayList = new ArrayList<>();
//
//        for (int i = 0; i < image.length; i++) {
//            PropertyImageModel beanClassForRecyclerView_contacts = new PropertyImageModel(image[i],imageNo[i]);
//
//            homeListModelClassArrayList.add(beanClassForRecyclerView_contacts);
//        }
//        mAdapter = new PropertyImageAdapter(PropertyDetailsActivity.this,homeListModelClassArrayList);
//
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(PropertyDetailsActivity.this,LinearLayoutManager.HORIZONTAL, false);
//        recyclerView1.setLayoutManager(mLayoutManager);
//        recyclerView1.setItemAnimator(new DefaultItemAnimator());
//        recyclerView1.setAdapter(mAdapter);


        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

//        System.out.println("fasilitas "+fasilitas);
//        ArrayList<Integer> viewfasilitas = new ArrayList<>();
//        ArrayList<String> datafasilitas = new ArrayList<>();
//
//
//        String[] fasili =fasilitas.split(";");
//
//        System.out.println("fasilitasx "+fasili[0]);
//
//        if(fasili[0].equals("1")){
//            viewfasilitas.add(R.drawable.wudhu);
//            datafasilitas.add("Tempat Wudhu");}
//        if(fasili[1].equals("1")){
//            viewfasilitas.add(R.drawable.toilet);
//            datafasilitas.add("Toilet");}
//        if(fasili[2].equals("1")){
//            viewfasilitas.add(R.drawable.airc);
//            datafasilitas.add("AC");}
//        if(fasili[3].equals("1")){
//            viewfasilitas.add(R.drawable.rak);
//            datafasilitas.add("Penitipan Alas Kaki");}
//        if(fasili[4].equals("1")){
//            viewfasilitas.add(R.drawable.motor);
//            datafasilitas.add("Parkir Motor");}
//        if(fasili[5].equals("1")){
//            viewfasilitas.add(R.drawable.mobil);
//            datafasilitas.add("Parkir Mobil");}
//        if(fasili[6].equals("1")){
//            viewfasilitas.add(R.drawable.bus);
//            datafasilitas.add("Parkir Bis");}
//        if(fasili[7].equals("1")){
//            viewfasilitas.add(R.drawable.jenset);
//            datafasilitas.add("Genset");}
//        if(fasili[8].equals("1")){
//            viewfasilitas.add(R.drawable.sound);
//            datafasilitas.add("Sound System");}
//        if(fasili[9].equals("1")){
//            viewfasilitas.add(R.drawable.kantor);
//            datafasilitas.add("Kantor");}
//        if(fasili[10].equals("1")){
//            viewfasilitas.add(R.drawable.taman);
//            datafasilitas.add("Taman");}
//        if(fasili[11].equals("1")){
//            viewfasilitas.add(R.drawable.tpq);
//            datafasilitas.add("TPQ");}
//        if(fasili[12].equals("1")){
//            viewfasilitas.add(R.drawable.perpus);
//            datafasilitas.add("Perpustakaan");}
//        if(fasili[13].equals("1")){
//            viewfasilitas.add(R.drawable.koperasi);
//            datafasilitas.add("Koperasi");}
//        if(fasili[14].equals("1")){
//            viewfasilitas.add(R.drawable.klinik);
//            datafasilitas.add("Poliklinik");}
//        if(fasili[15].equals("1")){
//            viewfasilitas.add(R.drawable.gudang);
//            datafasilitas.add("Gudang");}
//        if(fasili[16].equals("1")){
//            viewfasilitas.add(R.drawable.ambulan);
//            datafasilitas.add("Ambulan");}
//        if(fasili[17].equals("1")){
//            viewfasilitas.add(R.drawable.jenazah);
//            datafasilitas.add("Pengurusan Jenazah");}


        // data to populate the RecyclerView with
        // set up the RecyclerView


        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager horizontalLayoutManagaer= new LinearLayoutManager(detail_apotik.this, LinearLayoutManager.HORIZONTAL, false);
//        recyclerView.setLayoutManager(horizontalLayoutManagaer);
//        adapter = new MyRecyclerfasilitasAdapter(this, viewfasilitas, datafasilitas);
//        adapter.setClickListener(this);
//        recyclerView.setAdapter(adapter);



    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng Lokasiapotik = new LatLng(Double.parseDouble(latitude), Double.parseDouble(longitude));
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Lokasiapotik,20.0f));
        Marker marker =  googleMap.addMarker(new MarkerOptions().position(Lokasiapotik)
                .title(nama_apotik)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.mapdes1)));
        marker.showInfoWindow();
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Lokasiapotik));

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION
                        , Manifest.permission.ACCESS_COARSE_LOCATION}, 1);
            } else {
                mMap.setMyLocationEnabled(true);
            }
        } else {
            mMap.setMyLocationEnabled(true);
        }

        mMap.setOnMyLocationButtonClickListener(this);
        mMap.setOnMyLocationClickListener(this);
    }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
            if (requestCode == 1) {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        mMap.setMyLocationEnabled(true);
                    }
                } else {
                    Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
            }
        }

        @Override
        public boolean onMyLocationButtonClick() {
            Toast.makeText(this, "My Location Button Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }

        @Override
        public void onMyLocationClick(@NonNull Location location) {
            Toast.makeText(this, "Lokasiku saat ini : " + location, Toast.LENGTH_LONG).show();
        }

        public boolean ambilgambarnya(String newText) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL_APOTIK)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RegisterAPI api = retrofit.create(RegisterAPI.class);
        Call<Value> call = api.ambilgambar(newText);
        call.enqueue(new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response) {
                String value = response.body().getValue();
                if (value.equals("1")) {
                    resultsnya = response.body().getResult();
                    myCustomPagerAdapter = new MyCustomPagerAdapter(detail_apotik.this,resultsnya);
                    viewPager.setAdapter(myCustomPagerAdapter);
                }
                System.out.println("hasiljml "+resultsnya.size());
                totalnox.setText(""+resultsnya.size());

            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
            }
        });
        return true;
    }

//    @Override
//    public void onItemClick(View view, int position) {
//        Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on item position " + position, Toast.LENGTH_SHORT).show();
//    }
}
