package com.example.hoget;


import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.Overlay;
import com.google.android.maps.MapView;

public class HogetkunMap extends FragmentActivity{

	GoogleMap map;
	int remarker=0;
	
	double latitudes,longitudes;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_usermap);
	    
	   
	    map=((SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
	    
	    getMyLocation();
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
						
			LatLng loc=new LatLng(latitude, longitude);
			MarkerOptions marker = new MarkerOptions().position(loc); // 맵에 기본 마커 표시
			
			Marker m=map.addMarker(marker);
						
			if(remarker != 0)
				m.remove();
			
			map.moveCamera(CameraUpdateFactory.newLatLng(loc));
			map.animateCamera(CameraUpdateFactory.zoomTo(16));
			remarker++;			
			
			
			
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



}
