package com.MsoftTexas.WeatherOnMyTripRoute;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);



    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.km20:
                if (checked)
                    MapActivity.interval=20000;
                    break;
            case R.id.km30:
                if (checked)
                    MapActivity.interval=30000;
                    break;
            case R.id.km40:
                if (checked)
                    MapActivity.interval=40000;
                    break;
            case R.id.km50:
                if (checked)
                    MapActivity.interval=50000;
                    break;
        }
    }
}
