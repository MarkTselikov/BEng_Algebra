package com.example.hw3;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvSummary;
    private SharedPreferences settings;

    private static final String NAME_KEY = "fullName";
    private static final String LOCAION_KEY = "location";
    private static final String GENDER_KEY = "gender";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = PreferenceManager.getDefaultSharedPreferences(this);
        tvSummary = findViewById(R.id.tvSummary);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.clearSettings:
                deleteConfirm();
                return true;
            case R.id.refresh:
                tvSummary.setText(getSettings());
                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    private void deleteConfirm() {
        new AlertDialog.Builder(this)
                .setTitle("Confirm Clearing")
                .setMessage("Are you sure you want to clear the settings?")
                .setCancelable(false)
                .setPositiveButton("Yep", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        settings.edit().clear().apply();
                        tvSummary.setText("");
                    }
                })
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    private String getSettings() {

        String name = settings.getString(NAME_KEY, "someone");

        String location = "";
        int locationVal = Integer.parseInt(settings.getString(LOCAION_KEY, "-1"));
        String[] locations = getResources().getStringArray(R.array.locations_names);
        if(locationVal == -1)
            location = "nowhere";
        else
            location = locations[locationVal];

        String gender = "";
        boolean genderVal = settings.getBoolean(GENDER_KEY, true);
        if(!genderVal)
            gender = "Male";
        else
            gender = "Female";


        StringBuilder sb = new StringBuilder();
        sb.append("You are ");
        sb.append(name);
        sb.append(", and you are ");
        sb.append(gender);
        sb.append(" from ");
        sb.append(location);
        sb.append(".");

        return sb.toString();
    }
}
