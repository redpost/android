package com.etuxia.sign;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.webkit.WebViewClient;

public class Corktop extends Activity
{
	public final int DIALOG_CONNECTION_FAILED = 0;

	@Override
    public void onCreate(Bundle savedInstanceState) {
    	String url = "http://www.google.com";
    	WebView webview;

    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.webview);
    	
    	webview = (WebView)findViewById(R.id.webview);
    	webview.getSettings().setJavaScriptEnabled(true);
    	webview.getSettings().setLoadsImagesAutomatically(true);
    	webview.getSettings().setSupportZoom(false);
    	webview.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
    	webview.setPadding(0, 0, 0, 0);
    	webview.setBackgroundColor(00000000); // Black
    	setContentView(webview);

    	Bundle extras = getIntent().getExtras(); 
    	if (extras != null) {
    		url = extras.getString("corktop");
    	}
    	
    	if (!checkInternetConnection()) {
        	showDialog(DIALOG_CONNECTION_FAILED);
        	return;
    	}

    	webview.loadUrl(url);
    	webview.setWebViewClient(new CorktopClient());

    	// Start the DisableTouchScreen activity
        Intent i = new Intent (Corktop.this, DisableTouchScreen.class);
        startActivity(i);
    }
    
	boolean checkInternetConnection() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo nwInfo = cm.getActiveNetworkInfo();
		try {
			if (nwInfo.isConnectedOrConnecting()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			return false;
		}
	}

    
    private class CorktopClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
    
	protected Dialog onCreateDialog(int id) {
	    Dialog dialog;
	    switch(id) {
	    case DIALOG_CONNECTION_FAILED:
	    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	    	builder.setMessage("Can not reach the internet")
	    	       .setCancelable(false)
	    	       .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
	    	           public void onClick(DialogInterface dialog, int id) {
	    	        	   dialog.cancel();
	    	               Intent i = new Intent (Corktop.this, Sign.class);
	    	               startActivity(i);
	    	               finish();
	    	           }
	    	       });
	    	dialog = builder.create();	        
	        break;
	    default:
	        dialog = null;
	    }
	    return dialog;
	}
}
