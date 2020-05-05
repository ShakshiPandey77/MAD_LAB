package com.example.database_gps;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements OnClickListener, LocationListener {
    EditText Id, address;
    Button Insert, Delete, View, getloc;
    public static String msg = "";

    private static final int MY_PERMISSION_LOCATION = 123;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Id = (EditText) findViewById(R.id.id);
        address = (EditText) findViewById(R.id.address);
        Insert = (Button) findViewById(R.id.Insert);
        Delete = (Button) findViewById(R.id.Delete);
        View = (Button) findViewById(R.id.View);
        getloc = (Button) findViewById(R.id.get_location);

        Insert.setOnClickListener(this);
        Delete.setOnClickListener(this);
        View.setOnClickListener(this);
        getloc.setOnClickListener(this);

        db = openOrCreateDatabase("StudentDB", Context.MODE_PRIVATE, null);
        db.execSQL("Create Table if not exists faculty(id VARCHAR,address VARCHAR);");


    }

    public void onClick(View view) {
        if (view == Insert) {
            if (Id.getText().toString().trim().length() == 0 || address.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter all values");
                return;
            }
            db.execSQL("INSERT INTO faculty VALUES('" + Id.getText() + "','" + address.getText() + "');");
            showMessage("Success", "Record added");
            clearText();
        }
        if (view == Delete) {
            if (Id.getText().toString().trim().length() == 0) {
                showMessage("Error", "Please enter Rollno");
                return;
            }
            Cursor c = db.rawQuery("SELECT * FROM faculty WHERE id='" + Id.getText() + "'", null);
            if (c.moveToFirst()) {
                db.execSQL("DELETE FROM faculty WHERE id='" + Id.getText() + "'");
                showMessage("Success", "Record Deleted");
            } else {
                showMessage("Error", "Invalid Rollno");
            }
            clearText();
        }


        if (view == View) {

            if (Id.getText().toString().trim().length() != 0) {
                Cursor c = db.rawQuery("SELECT * FROM faculty WHERE id='" + Id.getText() + "'", null);
                if (c.moveToFirst()) {
                    address.setText(c.getString(1));
                    Id.setText(c.getString(0));
                } else {
                    showMessage("Error", "Invalid Id");
                    clearText();
                }
            } else if (address.getText().toString().trim().length() != 0) {
                Cursor c = db.rawQuery("SELECT * FROM faculty WHERE address='" + address.getText() + "'", null);
                if (c.moveToFirst()) {
                    address.setText(c.getString(1));
                    Id.setText(c.getString(0));
                } else {
                    showMessage("Error", "Invalid Id");
                    clearText();
                }
            } else {
                showMessage("Error", "Please enter Rollno");
                return;
            }
        }

        if (view == getloc) {

            LocationManager locationManager;
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);

        }
    }
    public void showMessage(String title,String message)
    {
        Builder builder=new Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    public void clearText()
    {
        Id.setText("");
        address.setText("");
        Id.requestFocus();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick_getLocation(View view) {
        LocationManager locationManager;
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            requestPermissions(
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSION_LOCATION);
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 1, this);
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        if (requestCode == MY_PERMISSION_LOCATION
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //  gps functionality
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        msg = "Latitude: " + location.getLatitude()  + " Longitude: " + location.getLongitude();
        address.setText(msg);
        Toast.makeText(MainActivity.this,msg,Toast.LENGTH_SHORT);
    }


    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "GPS is turned on!! ",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);  startActivity(intent);
        Toast.makeText(getBaseContext(), "GPS is turned off!! ",Toast.LENGTH_SHORT).show();
    }
}

