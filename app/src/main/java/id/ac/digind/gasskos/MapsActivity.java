package id.ac.digind.gasskos;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import id.ac.digind.gasskos.API.RetrofitClient;
import id.ac.digind.gasskos.adapters.PenginapanAdapter;
import id.ac.digind.gasskos.models.Penginapan;
import id.ac.digind.gasskos.models.PenginapanResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, OnInfoWindowClickListener {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        getPenginapan();
    }

    @Override
    public void onInfoWindowClick(Marker marker) {
        Integer idPenginapan = (Integer) marker.getTag();
        Bundle bundle = new Bundle();
        bundle.putInt("id_penginapan", idPenginapan);
        startActivity(new Intent(this, DetailKostActivity.class).putExtras(bundle));
    }

    private void getPenginapan(){
        SharedPreferences sharedPreferences = getSharedPreferences("GassKos_Shared_Preferences", Context.MODE_PRIVATE);
        Call<PenginapanResponse> getPenginapan = RetrofitClient.getInstance().getAPI().getPenginapan(sharedPreferences.getString("token", ""));
        getPenginapan.enqueue(new Callback<PenginapanResponse>() {
            @Override
            public void onResponse(Call<PenginapanResponse> call, Response<PenginapanResponse> response) {
                List<Penginapan> penginapanList = response.body().getPenginapans();

                for (Penginapan penginapan: penginapanList){
                    Marker temp = mMap.addMarker(
                            new MarkerOptions()
                                    .position(new LatLng(penginapan.getLatitude(), penginapan.getLongitude()))
                                    .title(penginapan.getNama())
                                    .snippet(penginapan.getAlamat()));
                    temp.setTag(penginapan.getIdPenginapan());
                }

                LatLng surabaya = new LatLng(-7.250445, 112.768845);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(surabaya, 12));
                setListener();
            }

            @Override
            public void onFailure(Call<PenginapanResponse> call, Throwable t) {


            }
        });
    }

    private void setListener(){
        mMap.setOnInfoWindowClickListener(this);
    }
}
