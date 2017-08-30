package com.example.author.reminder3v;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;


public class MainFragment extends Fragment {

    public MainFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ListView listView=(ListView)view.findViewById(R.id.list_view);

        //db
        MyDBHelper myDBHelper = new MyDBHelper(getActivity());
        SQLiteDatabase db = myDBHelper.getWritableDatabase();

        //select
        Cursor cursor = db.rawQuery("select * from reminder_record", null);

        //adapter
        String[] from = {"title", "body"};
        int[] to = {R.id.title_view, R.id.body_view};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getActivity(), R.layout.listview_item, cursor, from, to, 0);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DetailFragment fragment = new DetailFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction transaction = fragmentManager.beginTransaction();
                transaction.replace(R.id.main_fragment, fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }
}

