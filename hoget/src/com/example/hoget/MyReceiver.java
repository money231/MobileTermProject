package com.example.hoget;
import android.content.*;
import android.location.LocationManager;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		boolean isvalue=intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, false);
		if(isvalue){
			Toast.makeText(context, "��ǥ ������ �����ϴ� ��", Toast.LENGTH_LONG).show();
		}
		else{
			Toast.makeText(context, "��ǥ������ �������", Toast.LENGTH_LONG).show();
		}
		
	}
	
	

}
