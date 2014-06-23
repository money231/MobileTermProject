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

public class EnrollmentActivity extends ListActivity {
	
	private SharedPreferences pref;
    SharedPreferences.Editor editor;
    
    private SharedPreferences pref2;
    SharedPreferences.Editor editor2;
	int i=0,j=0;
	
	private ArrayList<String> itemList;
	private ArrayAdapter<String> adapter;
	
	private ArrayList<String> contentList;
	private EditText inputItemText,inputContentText;
	private Button inputButton;
	
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    setContentView(R.layout.activity_enrollment);
	    
	    
	    inputItemText=(EditText)findViewById(R.id.inputitem);
	    inputContentText=(EditText)findViewById(R.id.inputcontent);
	    inputButton = (Button)findViewById(R.id.inputButton);
	    itemList = new ArrayList<String>(); 
	    contentList=new ArrayList<String>();
	    
	    pref = getSharedPreferences("hogetitemList",MODE_PRIVATE);
	    editor = pref.edit(); 
	    pref2 = getSharedPreferences("hogetcontent",MODE_PRIVATE);
	    editor2 = pref2.edit(); 
	    
	    inputButton.setOnClickListener(new OnClickListener() {
		
	    	public void onClick(View v) {
				itemList.add(inputItemText.getText().toString());
				contentList.add(inputContentText.getText().toString());
				inputItemText.setText("");
				inputContentText.setText("");
				
				adapter.notifyDataSetChanged(); 
				
				
				editor.putString("ar"+i,itemList.get(i).toString());
				editor.commit();
				editor2.putString("ara"+i,contentList.get(i).toString());
				editor2.commit();
				
				i++;
			}
		});
	    while(true){
	    	if(pref.getString("ar"+i, "").equals("")){
	    		break;
	    	}
	    	else
	    	{
	    		itemList.add(pref.getString("ar"+i, ""));
	    		i++;
	    	}
	    }
	    
	    adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,itemList);
	    setListAdapter(adapter);
	}
	
	
}
