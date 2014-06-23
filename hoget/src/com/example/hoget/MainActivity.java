package com.example.hoget;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        Button startbutton=(Button)findViewById(R.id.start);
        Button hogetmodebutton=(Button)findViewById(R.id.hogetmode);
        Button listbutton=(Button)findViewById(R.id.list);
                
        startbutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this, StartActivity.class);
				
				startActivity(intent);
				
			}
		});
      
        hogetmodebutton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(MainActivity.this,EnrollmentActivity.class);
				
				startActivity(intent);
				
			}
		});
        listbutton.setOnClickListener(new OnClickListener() {
		
        	@Override
        	public void onClick(View v) {
        		// TODO Auto-generated method stub
        		Intent intent=new Intent(MainActivity.this, ItemListActivity.class);
			
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