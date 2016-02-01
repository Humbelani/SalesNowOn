package com.example.hmdau.salenowon;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

    public void LoginClickHandler(View view) {


        DBHelper db = new DBHelper(this);

        EditText et = (EditText) findViewById(R.id.editText);
        EditText editText = (EditText) findViewById(R.id.editText2);

        String savedEmail = et.getText().toString();
        String savedPassword = editText.getText().toString();

        Cursor cursor = db.getData(savedEmail, savedPassword);
        if (cursor.getCount() > 0)
        {
            startActivity(new Intent(this,HomeActivity.class));
        }


    }

    public void SignUpClickHandler(View view) {


        startActivity(new Intent(MainActivity.this, Registration.class));
    }
}
