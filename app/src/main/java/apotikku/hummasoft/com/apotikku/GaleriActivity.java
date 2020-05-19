package apotikku.hummasoft.com.apotikku;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import apotikku.hummasoft.com.apotikku.Adapters.MyCustomPagerAdapter;


public class GaleriActivity extends AppCompatActivity {
    ViewPager viewPager;
//    int images[] = {R.drawable.profilpotik};
      private List<Result> images;
    String galeri_apotik;
    private Context context;
    ImageView imageViewgaleri_apotik,back;
    MyCustomPagerAdapter myCustomPagerAdapter;
    private TextView imageNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeri);

        viewPager = (ViewPager)findViewById(R.id.toolbar);
        imageNo = (TextView)findViewById(R.id.imageNo);

        myCustomPagerAdapter = new MyCustomPagerAdapter(GaleriActivity.this, images);

        back =(ImageView)findViewById(R.id.kembali);
        imageViewgaleri_apotik = (ImageView)findViewById( R.id.q );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Picasso.with(context)
                .load(Config.BASE_URL_GAMBAR+galeri_apotik)
                .placeholder(R.drawable.art)
                .into(imageViewgaleri_apotik);
        Picasso.with(context).setLoggingEnabled(true);

    }

}