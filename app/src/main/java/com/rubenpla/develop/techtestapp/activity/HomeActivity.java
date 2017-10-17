package com.rubenpla.develop.techtestapp.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.rubenpla.develop.techtestapp.R;
import com.rubenpla.develop.techtestapp.application.TechTestApplication;
import com.rubenpla.develop.techtestapp.db.query.GetFamilyQuery;
import com.rubenpla.develop.techtestapp.mvp.model.FamilyMember;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    private final String TAG = HomeActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null){
            setSupportActionBar(toolbar);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetFamilyQuery query = new GetFamilyQuery();
                List<FamilyMember> list = null;
                try {
                    list = (List<FamilyMember>) TechTestApplication.getDataStoreDB().executeWithReadableDB(query,
                            TechTestApplication.getDataStoreDB().getDataStoreTables().TABLE_FAMILY_MAIN);
                } catch (Exception e) {
                    Log.e(TAG, "Exception e : " + e.getLocalizedMessage());
                }
                Snackbar.make(view, list.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
