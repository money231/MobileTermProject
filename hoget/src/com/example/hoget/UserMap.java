package com.example.hoget;


import java.util.ArrayList;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Canvas;
import android.graphics.Point;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.MapFragment;



import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.Projection;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class UserMap extends MapActivity{

	private LocationManager mLocationManager;
    private CoffeeIntentReceiver mIntentReceiver; 
    ArrayList mPendingIntentList; 
    String intentKey = "coffeeProximity";

	
	GoogleMap map;
	int remarker=0;
	
	double latitudes,longitudes;

	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_usermap);

	    init();
	    map = ((MapFragment)getFragmentManager().findFragmentById(R.id.map)).getMap();

	    getMyLocation();
	
	    int countTargets=2;
	    register(1001, 36.222222, 126.222222, 200, -1);
        register(1002, 38.222222, 128.222222, 200, -1);
        mIntentReceiver = new CoffeeIntentReceiver(intentKey);
        registerReceiver(mIntentReceiver, mIntentReceiver.getFilter());
        Toast.makeText(getApplicationContext(), countTargets+"개 지점에 대한 근접 리스너 등록", Toast.LENGTH_LONG).show();
	}
	public void onResume() {
	    super.onResume();
	    map.setMyLocationEnabled(true);
	  }
	public void onPause() {
	    super.onPause();
	    map.setMyLocationEnabled(false);
	  }
	private void init() {
	     mLocationManager = (LocationManager) 
	     getSystemService(Context.LOCATION_SERVICE);
         mPendingIntentList = new ArrayList();
	}
	private void register(int id, double latitude, double longitude,float radius, long expiration) {
      Intent proximityIntent = new Intent(intentKey);
      proximityIntent.putExtra("id", id);
      proximityIntent.putExtra("latitude", latitude);
      proximityIntent.putExtra("longitude", longitude);
      PendingIntent intent = PendingIntent.getBroadcast(this, id, proximityIntent,PendingIntent.FLAG_CANCEL_CURRENT);
      mLocationManager.addProximityAlert(latitude, longitude, radius, expiration, intent);
      mPendingIntentList.add(intent);
    }
	private class CoffeeIntentReceiver extends BroadcastReceiver {
	    private String mExpectedAction;
	    private Intent mLastReceivedIntent;
	    public CoffeeIntentReceiver(String expectedAction) {
	      mExpectedAction = expectedAction;
	      mLastReceivedIntent = null;
	    }
	    public IntentFilter getFilter() {
	      IntentFilter filter = new IntentFilter(mExpectedAction);
	      return filter;
	    }
	    public void onReceive(Context context, Intent intent) {
	        if (intent != null) {
	          mLastReceivedIntent = intent;
	          int id = intent.getIntExtra("id", 0);
	          double latitude = intent.getDoubleExtra("latitude", 0.0D);
	          double longitude = intent.getDoubleExtra("longitude", 0.0D);
	          Toast.makeText(context,"근접한 커피숍 : " + id + ", " + latitude + ", " + longitude, 2000).show();
	        }
	      }
	      public Intent getLastReceivedIntent(){
	        return mLastReceivedIntent;
	      }
	      public void clearReceivedIntents(){
	        mLastReceivedIntent = null;
	      }
	     } 


	private void getMyLocation(){
		LocationManager manager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		
		Location location=manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
		
		long minTime = 10000;
		float minDistance=0;
		
		MyLocationListener listener=new MyLocationListener();
		
		manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance, listener);
		
	}
	
	
	class MyLocationListener implements LocationListener{
		
		@Override
		public void onLocationChanged(Location location) {
			
			double latitude=location.getLatitude();						
			double longitude=location.getLongitude();
		
			showCurrentLocation(latitude, longitude);

		}
		private void showCurrentLocation(Double latitude, Double longitude) {
		    LatLng curPoint = new LatLng(latitude, longitude);
		    map.animateCamera(CameraUpdateFactory.newLatLngZoom(curPoint, 15));
		    map.setMapType(GoogleMap.MAP_TYPE_NORMAL); 
		    
		    showAllBankItems(latitude, longitude);

		}
		private void showAllBankItems(double latitude, double longitude) {
		    MarkerOptions marker = new MarkerOptions();
		    marker.position(new LatLng(latitude+0.001, longitude+0.001));
		    marker.title("신발판다다다");
		    marker.snippet("신발 50퍼 할인");
		    marker.draggable(true);
		    //marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.bank));

		    map.addMarker(marker); 
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
						
		}

		@Override
		public void onProviderEnabled(String provider) {
						
		}

		@Override
		public void onProviderDisabled(String provider) {
						
		}
		
	}


	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
class MyOverlay extends Overlay{
	
	@Override
	public void draw(Canvas canvas, MapView mapView
			, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow);
		Log.d("kim","draw");
		
		GeoPoint geopoint= new GeoPoint(37486981,126898784);
		Point geopix = new Point();
	
		com.google.android.maps.Projection projection = mapView.getProjection();
	
		
	}

}