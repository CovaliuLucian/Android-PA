package com.example.lucian.palab12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {

    private File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMessage(View view) throws IOException {
        if (file == null)
            file = new File(getFilesDir(), "storage");

        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        EditText text = findViewById(R.id.nameInput);
        String name = text.getText().toString();
        text.getText().clear();

        text = findViewById(R.id.dateInput);
        String date = text.getText().toString();
        text.getText().clear();

        text = findViewById(R.id.detailsInput);
        String details = text.getText().toString();
        text.getText().clear();

        if (!name.isEmpty() && !date.isEmpty() && !details.isEmpty())
            bufferedWriter.write(name + ',' + date + ',' + details + '\n');
        bufferedWriter.close();


        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line = bufferedReader.readLine();

        while (line != null) { // TODO remove this
            System.out.println(line);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
    }

    public void toList(View view) {
        Intent list = new Intent(this, ListViewLoader.class);
        startActivity(list);
    }
}
