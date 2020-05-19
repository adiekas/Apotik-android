package apotikku.hummasoft.com.apotikku;

import com.google.android.gms.fitness.data.Value;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface RegisterAPI {

    @FormUrlEncoded
    @POST("viewData.php")
    Call<Value> daftar(@Field( "id_apotik" )String id_apotik,
                       @Field( "nama_apotik" )String nama_apotik,
                       @Field( "no_telefon" )String no_telefon,
                       @Field( "deskripsi" )String deskripsi,
                       @Field( "provinsi" )String provinsi,
                       @Field( "status_kesehatan" )String status_kesehtan,
                       @Field( "alamat" )String alamat,
                       @Field( "latitude" )String latitude,
                       @Field( "longitude" )String longitude);

    @GET("getdatapotik.php")
    Call<apotikku.hummasoft.com.apotikku.Value> view();


//    @GET("viewkajian.php")
//    Call<Value> viewkajian();

    @FormUrlEncoded
    @POST("updateapotikketerangan.php")
    Call<Value> ubah(@Field( "id_apotik" )String id_apotik,
                     @Field( "nama_apotik" )String nama_apotik,
                     @Field( "deskripsi" )String deskripsi,
                     @Field( "provinsi" )String provinsi,
                     @Field( "status_kesehatan" )String status_kesehatan,
                     @Field( "alamat" )String alamat,
                     @Field( "lotitude")String latitude,
                     @Field( "longitude" )String longitude);

    @FormUrlEncoded
    @POST("http://192.168.43.179/php/delete.php")
    Call<Value> hapus(@Field( "id_apotik" )String search);

    @FormUrlEncoded
    @POST("search.php")
    Call<apotikku.hummasoft.com.apotikku.Value> search(@Field( "search" )String search);

    @FormUrlEncoded
    @POST("getdatapotik.php")
    Call<apotikku.hummasoft.com.apotikku.Value> getdatapotik(@Field( "search" )String search);

    @FormUrlEncoded
    @POST("ambilgambar.php")
    Call<apotikku.hummasoft.com.apotikku.Value> ambilgambar(@Field( "search" )String search);

    @GET("http://192.168.43.179/addin/kajian/viewkajian.php")
    Call<Value> kajian();

    }
