package apotikku.hummasoft.com.apotikku;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import apotikku.hummasoft.com.apotikku.Adapters.MyCustomPagerAdapter;

public class PropertyDetailsActivity extends AppCompatActivity implements OnMapReadyCallback {
    ViewPager viewPager;
    int images[] = {R.drawable.property,R.drawable.property,R.drawable.property,R.drawable.property};
    MyCustomPagerAdapter myCustomPagerAdapter;

    private TextView imageNo;



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
        setContentView(R.layout.activity_property_details);


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

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        LatLng sydney = new LatLng(-33.852, 151.211);
        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
