package com.sip.actorlistview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabase db;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db=openOrCreateDatabase("ActeurDB", Context.MODE_PRIVATE,
                null);
        db.execSQL("CREATE TABLE IF NOT EXISTS acteur(nom VARCHAR,age INT, pays VARCHAR, photo VARCHAR);");


        List<Acteur> image_details = getListData();
        /*for(Acteur acteur : image_details) {
            db.execSQL("INSERT INTO acteur VALUES('"+acteur.getNom()+"','"+acteur.getAge() + "','"+acteur.getPays() + "','"+acteur.getPhoto() +"');");
        }*/

        button = (Button) findViewById(R.id.button);
        final ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(new CustomListAdapter(this, image_details));
        // When the user clicks on the ListItem
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id)
            {
                Object o = listView.getItemAtPosition(position);
                Acteur country = (Acteur) o;
                Toast.makeText(MainActivity.this, "Selected :" + " " + country,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
    //private List<Acteur> getListData() {
    public static List<Acteur> getListData() {
        List<Acteur> list = new ArrayList<Acteur>();
        /*Acteur angelina = new Acteur("Angelina", 60, "US","tom");
        Acteur brad = new Acteur("Brad", 60, "US", "angolina");
        Acteur dicaprio = new Acteur("Di Caprio", 60, "US", "dicaprio");
        Acteur tom = new Acteur("Tom", 60, "US", "tom");
        list.add(angelina);
        list.add(brad);
        list.add(dicaprio);
        list.add(tom);*/

        Cursor c = MainActivity.db.rawQuery("SELECT * FROM acteur", null);


        while(c.moveToNext())
        {
            Acteur acteur = new Acteur(c.getString(0),Integer.valueOf(c.getString(1)),c.getString(2),c.getString(3));
            list.add(acteur);

        }
        return list;
    }

    public void callAddActorView(View view){
        Intent i = new Intent(getApplicationContext(), AddActorActivity.class);

        startActivity(i);
    }
}