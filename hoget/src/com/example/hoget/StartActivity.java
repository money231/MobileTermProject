package com.example.hoget;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_start);
	    	    
	    Button userbutton=(Button)findViewById(R.id.user);
	    Button hogetkunbutton=(Button)findViewById(R.id.hogetkun);
	    
	    userbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(StartActivity.this, UserMap.class);
				
				startActivity(intent);
				
			}
		});
	    
	    hogetkunbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intent=new Intent(StartActivity.this, HogetkunMap.class);
				
				startActivity(intent);
				
			}
		});
		
	}
	 public boolean onCreateOptionsMenu(Menu menu) {
   	  // Inflate the menu; this adds items to the action bar if it is present.
   	  getMenuInflater().inflate(R.menu.main, menu);
   	  return true;
   }
}
