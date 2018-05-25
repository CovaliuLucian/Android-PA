package com.example.lucian.palab12;

import android.app.Activity;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ListViewLoader extends Activity {


    private ArrayList<String> getNames() throws IOException {

        ArrayList<String> names = new ArrayList<>();

        File file = new File(getFilesDir(), "storage");
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        while (line != null) { // TODO remove this
            names.add(line.split(",")[0]);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        return names;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        ArrayAdapter<String> adapter = null;
        try {
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, getNames());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ListView listView = findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}