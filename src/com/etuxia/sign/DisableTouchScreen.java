package com.etuxia.sign;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class DisableTouchScreen extends Activity {
	@Override
		public void onCreate(Bundle icicle) {
		super.onCreate(icicle);
	}

    @Override
    	public boolean onCreateOptionsMenu (Menu menu) {
    	MenuInflater inflater = getMenuInflater();
    	inflater.inflate(R.menu.menu, menu);
    	return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// Handle item selection
    	switch (item.getItemId()) {
    	case R.id.changeUrl:
    		// Restart the application
    		Intent i = new Intent(DisableTouchScreen.this, Sign.class);
    		startActivity(i);
    		finish();
    		return true;
    	default:
    		return super.onOptionsItemSelected(item);
    	}
    }
}
