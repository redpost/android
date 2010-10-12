package com.etuxia.sign;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Sign extends Activity
{
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		String url;
		super.onCreate(savedInstanceState);
   		setContentView(R.layout.main);

   		Button btn = (Button) findViewById(R.id.go); 
   		btn.setOnClickListener(new OnClickListener() {
        public void onClick(View v) {
			// Remember Url
			EditText et = (EditText)findViewById(R.id.url);
			String url = et.getText().toString();
			SharedPreferences settings = getPreferences(MODE_PRIVATE);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("corktop", url);
			editor.commit();

			// Start the Corktop webview
			Intent i = new Intent(Sign.this, Corktop.class);
			i.putExtra("corktop", url);
			startActivity(i);
			finish();			
        }
    });

		// Get url from preferences
		try {
			SharedPreferences settings = getPreferences(MODE_PRIVATE);
			url = settings.getString("corktop", "https://corktop.theredpost.com/");
		} catch (Exception e) {
			return;
		}

   		EditText et = (EditText)this.findViewById(R.id.url);
   		et.setText(url);
	}
}
