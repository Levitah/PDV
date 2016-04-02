package br.com.trainning.pdv.ui;

import android.os.Bundle;
import android.util.Log;

import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.constants.Style;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import java.util.List;

import br.com.trainning.pdv.R;
import br.com.trainning.pdv.domain.model.Produto;
import br.com.trainning.pdv.domain.util.Util;
import butterknife.Bind;
import butterknife.OnClick;
import se.emilsjolander.sprinkles.Query;

public class MapaActivity extends BaseActivity {

    @Bind(R.id.mapview)
    MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);
        mapView.onCreate(savedInstanceState);


        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {

                // Set map style
                mapboxMap.setStyleUrl(Style.MAPBOX_STREETS);

                // Set the camera's starting position
                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(-23.5586729, -46.6612236)) // set the camera's center position
                        .zoom(12)  // set the camera's zoom level
                        .tilt(20)  // set the camera's tilt
                        .build();


                List<Produto> produtos = Query.many(Produto.class, "select * from produto order by codigo_barra").get().asList();

                for (Produto produto : produtos) {
                    Log.d("Produto", produto.getLatitude() + " " + produto.getLongitude());
                    if(produto.getLatitude()+produto.getLongitude()!=0.0){
                        Log.d("PRODUTO", produto.getLatitude()+" "+produto.getLongitude());
                        mapboxMap.addMarker(new MarkerOptions()
                                .position(new LatLng(produto.getLatitude(), produto.getLongitude()))
                                .title(produto.getDescricao())
                                .snippet(Util.getCurrencyValue(produto.getPreco()) + " " + produto.getUnidade()));

                    }
                    //mapboxMap.addMarker(new MarkerOptions())
                    //        .setPosition(new LatLng(produto.getLatitude(), produto.getLongitude())
                    //        .title(produto.getDescricao())
                    //        .snippet(Util.getCurrencyValue(produto.getPreco()) + " " + produto.getUnidade());
                }
            }
        });
    }

    @OnClick(R.id.butruas)
    public void onClickRuas() {
        mapView.setStyleUrl(Style.MAPBOX_STREETS);
    }

    @OnClick(R.id.butsatelite)
    public void onClickSatelite() {
        mapView.setStyleUrl(Style.SATELLITE);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}