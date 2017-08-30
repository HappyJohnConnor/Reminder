package com.example.author.reminder3v;

import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements DetailFragment.OnClickListener{

    EditText title_edit;
    EditText body_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title_edit=(EditText)findViewById(R.id.title_edit);
        body_edit=(EditText)findViewById(R.id.body_edit);


        FloatingActionButton addBtn=(FloatingActionButton) findViewById(R.id.add_btn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailFragment fragment = new DetailFragment();
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_fragment, fragment);
                transaction.commit();
            }
        });

        // Set fragment of ListView
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment,mainFragment);
        transaction.commit();
    }


    @Override
    public void onClick() {
        saveData();
        MainFragment mainFragment = new MainFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.main_fragment,mainFragment);
        transaction.commit();
    }

    private void saveData() {
        ContentValues contentValues=new ContentValues();
        contentValues.put(MyDBHelper.COLUMN_TITLE, title_edit.getText().toString());
        contentValues.put(MyDBHelper.COLUMN_BODY, body_edit.getText().toString());
        Uri uri=getContentResolver().insert(MyContentProvider.CONTENT_URI, contentValues);
    }
}
