package com.imam.myasi;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

public class DiagnosaAdapter extends RecyclerView.Adapter<DiagnosaAdapter.DiagnosaVH> {


    ArrayList<DiagnosaModel> diagnosa;
    Context context;
    SharedPreferences session;

    public DiagnosaAdapter(ArrayList<DiagnosaModel> diagnosa, Context context) {
        this.diagnosa = diagnosa;
        this.context = context;
    }

    @NonNull
    @Override
    public DiagnosaVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card_diagnosa, parent, false);
        DiagnosaVH dvh = new DiagnosaVH(view);

        return dvh;
    }

    @Override
    public void onBindViewHolder(@NonNull DiagnosaVH holder, int position) {
        DiagnosaModel dm = diagnosa.get(position);
        holder.judul.setText(dm.getJudul());

        String tanggal[] = dm.getTanggal().split("-");
        String bln[] = {"Januari","Februari","maret","April","Mei"};
        holder.hari.setText(tanggal[2]);
        holder.bulan.setText(bln[Integer.valueOf(tanggal[1])-1]);
        holder.tahun.setText(tanggal[0]);

        Random random = new Random();

        int color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256));
        holder.layout_tangal.setBackgroundColor(color);
        session = ((Activity)context).getSharedPreferences("sessionku", Context.MODE_PRIVATE);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = session.edit();
                editor.putString("jdlDiagnosa", String.valueOf(dm.getJudul()));
                editor.putString("idDiagnosa", String.valueOf(dm.getId()));
                editor.commit();
//                Intent in = new Intent(((Activity)context).getApplicationContext(),MainActivity.class);
//                ((Activity)context).startActivity(in);
                ((FragmentActivity)context).getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.nav_host_fragment_content_diagnosa, new ListDiagnosa())
                                .commit();
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setMessage("Hapus data diagnosis ini "+holder.judul.getText()+"?")
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DbHelper dbHelper = new DbHelper(view.getContext());
                                dbHelper.delData("diagnosa",String.valueOf(dm.getId()));
                                Log.d(TAG, "onClick: "+holder.getAdapterPosition());
                                notifyItemRemoved(holder.getAdapterPosition());
                                notifyItemChanged(holder.getAdapterPosition());

                                ((Activity)context).finish();
                                ((Activity)context).overridePendingTransition( 0, 0);
                                ((Activity)context).startActivity( ((Activity)context).getIntent());
                                ((Activity)context).overridePendingTransition( 0, 0);

                            }
                        })
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertBuilder = builder.create();
                alertBuilder.show();
                Log.d(TAG, "onLongClick: bisa");

                return true;
            }
        });

    }

    @Override
    public int getItemCount() {
        return diagnosa.size();
    }

    class DiagnosaVH  extends RecyclerView.ViewHolder{
        TextView judul, bayi, ibu, cemas, bulan,hari,tahun;
        LinearLayout layout_tangal;
        public DiagnosaVH(@NonNull View v) {
            super(v);
            judul = v.findViewById(R.id.judul);
            bulan = v.findViewById(R.id.bulan);
            hari = v.findViewById(R.id.hari);
            tahun = v.findViewById(R.id.tahun);
            layout_tangal = v.findViewById(R.id.layout_tanggal);
        }
    }
}
