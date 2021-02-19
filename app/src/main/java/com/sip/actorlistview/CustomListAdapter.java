package com.sip.actorlistview;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import java.util.List;

public class CustomListAdapter extends BaseAdapter {
    private List<Acteur> listData;
    private LayoutInflater layoutInflater;
    private Context context;
    public CustomListAdapter(Context aContext, List<Acteur> listData) {
        this.context = aContext;
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
 /*LayoutInflater est une classe utilisée pour instancier le fichier XML de
mise en page dans ses objets de vue correspondants qui peuvent être utilisés dans les
programmes Java. En termes simples, il existe deux façons de créer une interface
utilisateur dans android . L'un est une manière statique et l'autre est dynamique ou
par programme.*/
    }
    @Override
    public int getCount() {
        return listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.list_item_layout, null);
            holder = new ViewHolder();
            holder.flagView = (ImageView)
                    convertView.findViewById(R.id.imageView_flag);
            holder.actorNameView = (TextView)
                    convertView.findViewById(R.id.actorName);
            holder.actorAgeView = (TextView)
                    convertView.findViewById(R.id.actorAge);
            holder.actorCountryView = (TextView)
                    convertView.findViewById(R.id.actorCountry);
            holder.btnModifyActor = (Button)
                    convertView.findViewById(R.id.btnModifyActor);
            holder.btnDeleteActor = (Button)
                    convertView.findViewById(R.id.btnDeleteActor);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Acteur acteur = this.listData.get(position);
        holder.actorNameView.setText(acteur.getNom());
        holder.actorAgeView.setText("Age : " + acteur.getAge());
        holder.actorCountryView.setText("Pays: " + acteur.getPays());
        int imageId = this.getMipmapResIdByName(acteur.getPhoto());
        holder.flagView.setImageResource(imageId);
        holder.btnModifyActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), UpdateActorActivity.class);
                i.putExtra("nom", acteur.getNom());
                context.startActivity(i);
            }
        });
        holder.btnDeleteActor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acteur.getNom();

                    Cursor c = MainActivity.db.rawQuery("SELECT * FROM acteur WHERE nom='"+acteur.getNom()+"'", null);
                    if(c.moveToFirst())
                    {
                        MainActivity.db.execSQL("DELETE FROM acteur WHERE nom='"+acteur.getNom()+"'");
                        showMessage("Success", "Record Deleted");
                    }
                    else
                    {
                        showMessage("Error", "Invalid name");
                    }
                updateResults(MainActivity.getListData());
            }
        });
        return convertView;
    }
    // Find Image ID corresponding to the name of the image (in the directory mipmap).
    public int getMipmapResIdByName(String resName) {
        String pkgName = context.getPackageName();
        // Return 0 if not found.
        int resID = context.getResources().getIdentifier(resName , "mipmap",
                pkgName);
        Log.i("CustomListView", "Res Name: "+ resName+"==> Res ID = "+ resID);
        return resID;
    }
    static class ViewHolder {
        ImageView flagView;
        TextView actorNameView;
        TextView actorAgeView;
        TextView actorCountryView;
        Button btnModifyActor;
        Button btnDeleteActor;
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateResults(List<Acteur> results) {
        listData = results;
        //Triggers the list update
        notifyDataSetChanged();
    }
}
