package com.example.author.reminder3v;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class DetailFragment extends Fragment {

    private EditText title_edit;
    private EditText body_edit;
    private String id;


    public DetailFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        title_edit = (EditText) view.findViewById(R.id.title_edit);
        body_edit = (EditText) view.findViewById(R.id.body_edit);
        Button ok_btn = ok_btn = (Button) view.findViewById(R.id.ok_btn);

        savedInstanceState=getArguments();
        title_edit.setText(savedInstanceState.getString("title", null));
        body_edit.setText(savedInstanceState.getString("body", null));
        id=savedInstanceState.getString("id", null);

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
                backToMain();
            }
        });
        return view;

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.delete_itm:

                getActivity().getContentResolver().delete(MyContentProvider.CONTENT_URI, MyDBHelper.COLUMN_ID+"="+id, null);
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDBHelper.COLUMN_TITLE, title_edit.getText().toString());
        contentValues.put(MyDBHelper.COLUMN_BODY, body_edit.getText().toString());
        getActivity().getContentResolver().insert(MyContentProvider.CONTENT_URI, contentValues);
    }

    private void backToMain(){
        MainFragment fragment = new MainFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.addToBackStack(null)
                .replace(R.id.main_fragment, fragment)
                .commit();
    }


}
