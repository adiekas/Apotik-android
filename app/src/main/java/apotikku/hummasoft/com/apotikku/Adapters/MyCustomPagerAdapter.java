package apotikku.hummasoft.com.apotikku.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import apotikku.hummasoft.com.apotikku.Config;
import apotikku.hummasoft.com.apotikku.R;
//import com.hummasoft.addin.network.config.Config;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import apotikku.hummasoft.com.apotikku.Result;

/**
 * Created by wolfsoft1 on 17/3/18.
 */

public class MyCustomPagerAdapter extends PagerAdapter {
    Context context;
    //    int images[];
    private List<Result> images;
    LayoutInflater layoutInflater;


    public MyCustomPagerAdapter(Context context, List<Result> images) {
        this.context = context;
        this.images = images;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        System.out.println("masuks "+images.size());
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        View itemView = layoutInflater.inflate(R.layout.item, container, false);
        ImageView imageViews = (ImageView) itemView.findViewById(R.id.imageView);
        System.out.println("dalam "+images.size());

        Result result = images.get(position);
        System.out.println("aaaaaaa " + result.getfoto_profil());


        System.out.println("hasiln" + result);
        Picasso.with(context)
                .load(Config.BASE_URL_GAMBAR + result.getfoto_profil())
                .placeholder(R.drawable.map)
                .into(imageViews);
        Picasso.with(context).setLoggingEnabled(true);



//        imageView.setImageResource(images.get(position));

        container.addView(itemView);

        //listening to image click
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "you clicked image " + (position + 1), Toast.LENGTH_LONG).show();
//            }
//        });

        return itemView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((RelativeLayout) object);
    }
}