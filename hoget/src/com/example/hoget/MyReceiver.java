package com.example.hoget;
import android.content.*;
import android.location.LocationManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean isvalue=intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
		if(isvalue){
			Toast.makeText(context, "목표 지점에 접근하는 중", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(context, "목표지점에 벗어나는중", Toast.LENGTH_LONG).show();
		}
		
	}
	
	

}
