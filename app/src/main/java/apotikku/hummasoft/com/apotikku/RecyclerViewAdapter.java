package apotikku.hummasoft.com.apotikku;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

//import apotikku.hummasoft.com.apotikku.detail_apotik;
//import apotikku.hummasoft.com.apotikku.sqlite.DManager;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private Context context;
    private List<Result>results;
//    AppSettings settings;

    public RecyclerViewAdapter(Context context, List<Result>results){
        this.context = context;
        this.results = results;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from( parent.getContext()).inflate( R.layout.recycler_view, parent, false );
            ViewHolder holder = new ViewHolder(v);
//            settings = AppSettings.getInstance(context);
//            System.out.println("latitudenya"+settings.getLatFor(0));
//            System.out.println("longitudenya"+settings.getLngFor(0));
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position){
        final Result result = results.get(position);
        holder.textViewid_apotik.setText(result.getid_apotik());
        holder.textViewnama_apotik.setText(result.getnama_apotik());
        holder.textViewalamat.setText(result.getalamat());

//        holder.textViewKelas.setText(result.getKelas());
//        holder.textViewSesi.setText(result.getSesi());

        Location loc1 = new Location ("");
//        loc1.setLatitude(Double.parseDouble( result.getlatitude()));
//        loc1.setLongitude( Double.parseDouble( result.getlongitude()));

        Location loc2 = new Location("");
//        loc2.setLatitude( Double.parseDouble( result.getlatitude()));
//        loc2.setLongitude( Double.parseDouble( result.getlongitude ()));

        float distanceInMeters = loc1.distanceTo(loc2);

//        holder.textViewjarak.setText(String.format( "%.0f", distanceInMeters)+"Meter");
        holder.textViewdeskripsi = result.getdeskripsi();
//        holder.textViewalamat = result.getalamat();
//        holder.textViewnama_apotik = result.getnama_apotik();
//        holder.textViewid_apotik = result.getid_apotik();
        holder.textViewno_telefon = result.getno_telefon();
        holder.textViewstatus_kesehatan = result.getstatus_kesehatan();
        holder.textViewprovinsi = result.getprovinsi();
        holder.textViewfoto_profil = result.getfoto_profil();
        holder.textViewjam_buka = result.getjam_buka();
        holder.textViewgaleri_apotik = result.getgaleri_apotik();
        holder.textViewlatitude = result.getlatitude();
        holder.textViewlongitude = result.getlongitude();

        // new DowloadImageTask(holder.gambardepan).execute ("http://java.soget1.n1/javaBlog/wp-content/uploads
        // Picasso.with(context).load("http://java.soget1.n1/JavaBlog/wp-content/uploads/2009/04/android_icon_256

        Picasso.with(context)
                .load(Config.BASE_URL_GAMBAR+result.getfoto_profil())
                .placeholder(R.drawable.art)
                .into(holder.textViewgambardepan);
        Picasso.with(context).setLoggingEnabled(true);

        //Cursor cursor = holder.dbManager.fetchbyid(result.getid_apotik());
//        System.out.println("di klik"+ result.getid_apotik());
//        System.out.println("cursor"+cursor.getCount());
//        if (cursor.getCount()!= 0){
            //holder.favorits.setImageRsource(R.drawable.ic_bookmark_blue);
//            holder.dbManager.update(result.getnama_apotik(),result.getid_apotik()
//                    ,result.getalamat(),result.getdeskripsi(),result.getfoto_profil()
//                    ,result.getstatus_kesehatan(),result.getjam_buka(), result.getprovinsi()
//                    ,result.getno_telefon(),result.getlatitude(),result.getlongitude()
//                    ,result.getprofil(),result.getfasilitas());
//            System.out.println("masuk");
//        }else {
//            holder.favorits.setImageResource(R.drawable.ic_bookmark_gry);
//            System.out.println("tidak");
//        }

//        holder.favorits.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View view){
////                Cursor cursor = holder.dbManager.fetchbyid(result.getid_apotik());
////                 if (cursor.getCount()!= 0){
//                    System.out.println("masuk");
//
//                    new AlertDialog.Builder(context)
//                            .setTitle( "Peringatan" )
//                            .setMessage( "Menghapus masjid ini dari daftar favorit?" )
//                            .setPositiveButton( "iya", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                   // continue with delete
////                                   holder.dbManager.deletebyid(result.getid_apotik());
////                                   holder.favorits.setImageResource(R.drawable.ic_bookmark_grey);
//                                }
//                            }).setNegativeButton( "Batal", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//
//                        }
//                    }).setIcon( android.R.drawable.ic_dialog_alert )
//                            .show();
//                //}else {
////                    holder.favorits.setImageResource(R.drawable.ic_bookmark_grey);
//                    new AlertDialog.Builder(context)
//                            .setTitle( "Peringatan" )
//                            .setMessage( "Menambahkan masjid ini ke daftar favorit" )
//                            .setPositiveButton( "iya", new DialogInterface.OnClickListener() {
//                                public void onClick(DialogInterface dialog, int which) {
//                                    // continue with delete
////                                    holder.dbManager.insert(result.getid_apotik(), result.getid_apotik()
////                                            ,result.getalamat(),result.getnama_apotik(),result.getdeskripsi()
////                                            ,result.getprovinsi(),result.getfoto_profil(),result.getno_telefon()
////                                            ,result.getstatus_kesehatan(),result.getjam_buka()
////                                            ,result.getlatitude(),result.getlongitude(),result.getprofil());
////                                    holder.favorits.setImageResource(R.drawable.ic_bookmark_blue);
//                                }
//                            }).setNegativeButton( "Batal", new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            // do nothing
//                        }
//                    }).setIcon( android.R.drawable.ic_dialog_alert )
//                            .show();
//
//                    System.out.println("tidak");
//                }
////                System.out.println("di klik"+ result.getNPM());
////                getSupportActionBar().hide();
////            }
//        });
    }

    @Override
    public int getItemCount(){
        return results.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textViewid_apotik;
        TextView textViewnama_apotik;
        TextView textViewalamat;
        TextView textViewjarak;

        String textViewdeskripsi;
        String textViewno_telefon;
        String textViewstatus_kesehatan;
        String textViewprovinsi;
        String textViewfoto_profil;
        String textViewjam_buka;
        String textViewlatitude;
        String textViewlongitude;
        String textViewgaleri_apotik;
        ImageView  textViewgambardepan;
//        ImageView favorits;
//        DBManager dbManager;

        public ViewHolder(View itemView){
            super(itemView);
            textViewid_apotik = (TextView)itemView.findViewById( R.id.propertyName );
            textViewnama_apotik = (TextView)itemView.findViewById( R.id.propertyName );
            textViewalamat = (TextView)itemView.findViewById( R.id.street1 );
            textViewgambardepan = (ImageView)itemView.findViewById( R.id.foodImage );
//            favorits = (ImageView)itemView.findViewById( R.id.favorits );
//            dbManager = new DBManager(context );
//            dbManager.open();

//            new DownloadImageTask(gambardepan).execute("http://java.sogeti.n1/JavaBlog/wp-content/upload")
            itemView.setOnClickListener( this );
        }

        @Override
        public void onClick(View view){
            String id_apotik = textViewid_apotik.getText().toString();
            String nama_apotik = textViewnama_apotik.getText().toString();
//            String jarak_masjid = textViewjarak.getText().toString();
            String deskripsi = textViewdeskripsi;
            String no_telefon = textViewno_telefon;
            String provinsi = textViewprovinsi;
            String status_kesehatan = textViewstatus_kesehatan;
            String alamat = textViewalamat.getText().toString();
            String foto_profil = textViewfoto_profil;
            String jam_buka = textViewjam_buka;
            String latitude = textViewlatitude;
            String longitude = textViewlongitude;
            String galeri_apotik = textViewgaleri_apotik;
            //String id_masjid = textViewdno_masjid;

            System.out.println("bbbb"+id_apotik);
            System.out.println("bbbb"+nama_apotik);
            System.out.println("bbbb"+deskripsi);
            System.out.println("bbbb"+no_telefon);
            System.out.println("bbbb"+provinsi);
            System.out.println("bbbb"+status_kesehatan);
            System.out.println("bbbb"+alamat);
            System.out.println("bbbb"+foto_profil);
            System.out.println("bbbb"+jam_buka);
            System.out.println("bbbb"+galeri_apotik);
            System.out.println("bbbb"+latitude);
            System.out.println("bbbb"+longitude);


//            Intent intent = new Intent( get, PropertyDetailsActivity.class );
//            startActivity(intent);
//            String kelas = textViewKelas.getText().toString();
//            String sesi = textViewSesi.getText().toString();

            Intent i = new Intent(context, detail_apotik.class);
            i.putExtra( "nama_apotik", nama_apotik);
            i.putExtra( "no_telefon", no_telefon );
            i.putExtra( "deskripsi", deskripsi );
            i.putExtra( "provinsi", provinsi);
            i.putExtra( "status_kesehatan",status_kesehatan );
            i.putExtra( "alamat", alamat);
            i.putExtra( "foto_profil", foto_profil );
            i.putExtra( "jam_buka", jam_buka );
            i.putExtra( "id_apotik", id_apotik );
            i.putExtra( "latitude",latitude );
            i.putExtra( "longitude",longitude );
            i.putExtra( "galeri_apotik",galeri_apotik );
            i.putExtra( "latitude", latitude );
            i.putExtra( "longitude", longitude );
            context.startActivity(i);
        }

    }
}
