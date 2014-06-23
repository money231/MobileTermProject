package com.example.hoget;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

public class ItemListActivity extends ListActivity {
	
	private ArrayList<String> list;
	private ArrayAdapter<String> adapter;
	private EditText inputText;
	private Button inputButton;
	
	private SharedPreferences pref;
    SharedPreferences.Editor editor;	
	int i=0,j=0;
	
	public void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_list);
	    
	    inputText=(EditText)findViewById(R.id.inputText);
	    inputButton = (Button)findViewById(R.id.inputButton);
	    list = new ArrayList<String>(); 
	    
	    pref = getSharedPreferences("itemList",MODE_PRIVATE);
	    editor = pref.edit(); 
	    
	    	    
	    inputButton.setOnClickListener(new OnClickListener() {
		
			public void onClick(View v) {
				list.add(inputText.getText().toString());
				inputText.setText("");   
				adapter.notifyDataSetChanged(); 
				
				
				editor.putString("ar"+i,list.get(i).toString());
				editor.commit();
				i++;
			}
		});
	    while(true){
	    	if(pref.getString("ar"+i, "").equals("")){
	    		break;
	    	}
	    	else
	    	{
	    		list.add(pref.getString("ar"+i, ""));
	    		i++;
	    	}
	    }
	    
	    adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,list);
	    setListAdapter(adapter);
	    
	}
	

}
