package com.example.httplistner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map.Entry;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;

import com.example.httplistner.NanoHTTPD.Response.IStatus;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
	

public class HttpListnerActivity extends Activity {

	private MyHTTPD server;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_http_listner);
		
		try {
			server = new MyHTTPD();
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
	}
	
  	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.http_listner, menu);
		return true;
	}
  	
  	  class MyHTTPD extends NanoHTTPD {

		public MyHTTPD() throws IOException {
  	      super(2200);
  	    }
  	 

		@Override
		public Response serve(IHTTPSession session) {
			
			Hashtable<String, String> body = new Hashtable<String, String>();
			try {
				session.parseBody(body);
				System.out.println(body.toString());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ResponseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			final String html = "<html><head><head><body><h1>Helloww, World</h1></body></html>";
			return new NanoHTTPD.Response(Response.Status.OK, MIME_HTML, html);
		}
		
  	  }
		

}
