package se.kontaktAddresser;

import android.os.Bundle;

import com.google.android.maps.MapActivity;

public class MapsActivity extends MapActivity {

	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.karta);
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
