package com.sip.actorlistview;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActorActivity extends AppCompatActivity {

    TextView actorName;
    EditText modifiedAge, modifiedCountry, modifiedPhoto;
    Button btnUpdateActor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_actor);

        actorName = (TextView) findViewById(R.id.modifiedName);
        modifiedAge = (EditText) findViewById(R.id.modifiedAge);
        modifiedCountry = (EditText) findViewById(R.id.modifiedCountry);
        modifiedPhoto = (EditText) findViewById(R.id.modifiedPhoto);
        btnUpdateActor = (Button) findViewById(R.id.btnUpdateActor);

        Bundle extras = getIntent().getExtras();
        String nom = extras.getString("nom");

        Cursor c = MainActivity.db.rawQuery("SELECT * FROM acteur WHERE nom='"+ nom +"'", null);
        if(c.moveToFirst())
        {
            actorName.setText(c.getString(0));
            modifiedAge.setText(c.getString(1));
            modifiedCountry.setText(c.getString(2));
            modifiedPhoto.setText(c.getString(3));
        }
    }

    public void updateActor(View view) {
        if(view==btnUpdateActor)
        {
            if(actorName.getText().toString().trim().length()==0)
            {
                showMessage("Error", "Please enter catId");
                return;
            }
            /*Cursor c = MainActivity.db.rawQuery("SELECT * FROM acteur WHERE id='"+modifiedName.getText()+"'", null);
            if(c.moveToFirst())
            {*/
            MainActivity.db.execSQL("UPDATE acteur SET age="+Integer.valueOf(modifiedAge.getText().toString()) +
                    ", pays='" + modifiedCountry.getText() + "', photo='" + modifiedPhoto.getText() +
                    "' WHERE nom='"+actorName.getText()+"'");
            showMessage("Success", "Record updated");
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(i);
            /*}
            else
            {
                showMessage("Error", "Invalid catId");
            }*/
            //clearText();
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