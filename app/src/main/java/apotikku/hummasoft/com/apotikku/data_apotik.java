package apotikku.hummasoft.com.apotikku;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import apotikku.hummasoft.com.apotikku.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class data_apotik extends Fragment implements SearchView.OnQueryTextListener{
    //public static final String URL = "http://192.168.1.21/php";
    private List<Result>results = new ArrayList<>();
    private RecyclerViewAdapter viewAdapter;

    ProgressBar progressBar;
    SearchView pencarian;

    private RecyclerView recyclerView;

    public static data_apotik newInstance(Location location){
        data_apotik fragment  = new data_apotik();
        return fragment;
    }

    @Override
    public void onCreate(Bundle saveInstanceState){
        super.onCreate( saveInstanceState );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle saveInstanceState){
        View view = inflater.inflate( R.layout.fragment_data_apotik, null, false );
        setHasOptionsMenu( true );

        recyclerView = (RecyclerView)view.findViewById( R.id.recyclerView );
        progressBar = (ProgressBar)view.findViewById( R.id.progress_bar );

        viewAdapter = new RecyclerViewAdapter( getContext(), results);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager( getContext());

//        RecyclerView.LayoutManager mlayoutManager = new LinearLayoutManager( getContext());
        recyclerView.setLayoutManager( mLayoutManager );
        recyclerView.setItemAnimator( new DefaultItemAnimator());
        recyclerView.setAdapter( viewAdapter );

        loadDataMahasiswa();
        return view;
    }

    private void loadDataMahasiswa(){
         Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Config.BASE_URL_APOTIK )
                .addConverterFactory( GsonConverterFactory.create())
                .build();
        RegisterAPI api  = retrofit.create( RegisterAPI.class );
        Call<Value> call = api.view();
        call.enqueue( new Callback<Value>() {
            @Override
            public void onResponse(Call<Value>call, Response<Value>response){
                String value  = response.body().getValue();
                progressBar.setVisibility( View.GONE );
                if (value.equals( "1" )){
                    results  = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter(getContext(),results);
                    recyclerView.setAdapter( viewAdapter );
                }
            }
            @Override
            public void onFailure(Call<Value>call, Throwable t){
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        inflater.inflate( R.menu.menu_search, menu);
        MenuItem searchItem = menu.findItem( R.id.action_search );
        SearchView searchView =(SearchView)MenuItemCompat.getActionView( searchItem );
        searchView.setQueryHint("Cari Apotik...");
        searchView.setIconified(false);
        searchView.clearFocus();
        searchView.setOnQueryTextListener( this );
        super.onCreateOptionsMenu( menu, inflater );
    }

    @Override
    public boolean onQueryTextSubmit(String query){
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText){
        recyclerView.setVisibility( View.GONE );
        progressBar.setVisibility( View.VISIBLE );
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl( Config.BASE_URL_APOTIK )
                .addConverterFactory( GsonConverterFactory.create() )
                .build();
        RegisterAPI api = retrofit.create( RegisterAPI.class );
        Call<Value> call = api.search(newText);
        call.enqueue( new Callback<Value>() {
            @Override
            public void onResponse(Call<Value> call, Response<Value> response){
                String value = response.body().getValue();
                progressBar.setVisibility( View.GONE );
                recyclerView.setVisibility( View.VISIBLE );
                if (value.equals( "1" )){
                    results  = response.body().getResult();
                    viewAdapter = new RecyclerViewAdapter(getContext(),results);
                    recyclerView.setAdapter( viewAdapter );
                }
            }

            @Override
            public void onFailure(Call<Value> call, Throwable t) {
                progressBar.setVisibility( View.GONE );

            }
        });
        return true;
    }
}
