package com.sip.actorlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddActorActivity extends AppCompatActivity {
    Button btnAddActor;
    EditText nom, age, pays, photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_actor);

        btnAddActor = (Button) findViewById(R.id.btnUpdateActor);
        nom = (EditText) findViewById(R.id.modifiedName);
        age = (EditText) findViewById(R.id.modifiedAge);
        pays = (EditText) findViewById(R.id.modifiedCountry);
        photo = (EditText) findViewById(R.id.modifiedPhoto);
    }

    public void addActor(View view) {
        if(view==btnAddActor)
        {
            if(nom.getText().toString().trim().length()==0|| age.getText().toString().trim().length()==0
                    || pays.getText().toString().trim().length()==0|| photo.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter all values");
                return;
            }
            MainActivity.db.execSQL("INSERT INTO acteur VALUES('"+nom.getText()+"','"+Integer.valueOf(age.getText().toString()) + "','"+pays.getText() + "','"+photo.getText() +"');");
            showMessage("Success", "Record added");
            //clearText();
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


}