package com.imam.myasi;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class CemasAdapter extends RecyclerView.Adapter<CemasAdapter.CemasVH> {

    ArrayList<CemasModel> cemas;
    Context context;
    private PassData passData;

    String[] idPertanyaan;
    String[] idHasil;
    String status;

    public CemasAdapter() {
    }

    public String[] getIdPertanyaan() {
        return idPertanyaan;
    }

    public void setIdPertanyaan(String[] idPertanyaan) {
        this.idPertanyaan = idPertanyaan;
    }

    public String[] getIdHasil() {
        return idHasil;
    }

    public void setIdHasil(String[] idHasil) {
        this.idHasil = idHasil;
    }

    public void setData(String[] idPertanyaan, String[] idHasil){
        this.idPertanyaan = idPertanyaan;
        this.idHasil = idHasil;
    }


    public interface PassData{
        void onClick(String pertanyaan,String id, String hasil, int pos);
        void onCreate(String[] hasil);
    }

    public CemasAdapter(ArrayList<CemasModel> cemas, Context context,  PassData passData) {
        this.cemas = cemas;
        this.context = context;
        this.passData = passData;

        initArray();
    }

    @SuppressLint("Range")
    private void initArray() {
        this.idHasil = new String[15];
        this.idPertanyaan = new String[15];

        String iddgs = ((Activity) context).getIntent().getStringExtra("idDgs");
        DbHelper db = new DbHelper(context.getApplicationContext());
        String[] where = {iddgs,"cemas"};
        Cursor cr = db.viewDataHasil(where);
        if(cr.getCount()>0){
            while(cr.moveToNext()){
                this.idHasil[cr.getPosition()] = cr.getString(cr.getColumnIndex("hasil"));
                this.idPertanyaan[cr.getPosition()] = cr.getString(cr.getColumnIndex("idquestion"));
            }
        }else {

        }


        cr.close();
    }

    @NonNull
    @Override
    public CemasVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_cemas, parent, false);
        CemasAdapter.CemasVH cvh = new CemasAdapter.CemasVH(view);

        return cvh;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void onBindViewHolder(@NonNull CemasVH holder, @SuppressLint("RecyclerView") int pos) {

        CemasModel cm = cemas.get(pos);

        String iddgs = ((Activity) context).getIntent().getStringExtra("idDgs");
        DbHelper dbHelper = new DbHelper(((Activity) context).getBaseContext());
        Integer p = pos+1;

        holder.pertanyaan.setText(p+". "+cm.getPertanyaan());

//        HasilModel hm = new HasilModel(iddgs,"cemas"+p,null,null);
//        Integer hsl = dbHelper.findHasil3(hm);
//        Log.d(ContentValues.TAG, "getView: "+pos+"-"+hsl);
//        if(dbHelper.findHasil2(hm).equals("1")){
//            idHasil[pos] = hsl.toString();
//            idPertanyaan[pos] = cm.getIdPertanyaan();
//            Log.d(TAG, "onBindViewHolder: "+getIdHasil());
////            if (hsl == 1){
////                holder.tidakpernah.setBackgroundResource(R.color.hijau);
////                holder.kadang.setBackgroundResource(R.color.merah);
////                holder.sebagian.setBackgroundResource(R.color.merah);
////                holder.hampir.setBackgroundResource(R.color.merah);
////            }else if (hsl == 2){
////                holder.kadang.setBackgroundResource(R.color.hijau);
////                holder.tidakpernah.setBackgroundResource(R.color.merah);
////                holder.sebagian.setBackgroundResource(R.color.merah);
////                holder.hampir.setBackgroundResource(R.color.merah);
////            }else if (hsl == 3){
////                holder.sebagian.setBackgroundResource(R.color.hijau);
////                holder.tidakpernah.setBackgroundResource(R.color.merah);
////                holder.kadang.setBackgroundResource(R.color.merah);
////                holder.hampir.setBackgroundResource(R.color.merah);
////
////            }else if (hsl == 4){
////                holder.hampir.setBackgroundResource(R.color.hijau);
////                holder.sebagian.setBackgroundResource(R.color.merah);
////                holder.tidakpernah.setBackgroundResource(R.color.merah);
////                holder.kadang.setBackgroundResource(R.color.merah);
////            }else{
////                holder.sebagian.setBackgroundResource(R.color.merah);
////                holder.tidakpernah.setBackgroundResource(R.color.merah);
////                holder.kadang.setBackgroundResource(R.color.merah);
////                holder.hampir.setBackgroundResource(R.color.merah);
////                Log.d(TAG, "onBindViewHolder: holder");
////            }
//        }
        Log.d(TAG, "onBindViewHolder: holder="+ Arrays.toString(getIdHasil()));
        if(getIdHasil()!=null){
            if(idHasil[pos] == null){
                holder.sebagian.setBackgroundResource(R.color.merah);
                holder.tidakpernah.setBackgroundResource(R.color.merah);
                holder.kadang.setBackgroundResource(R.color.merah);
                holder.hampir.setBackgroundResource(R.color.merah);
                Log.d(TAG, "onBindViewHolder: array ="+idHasil[0]);
            }else{
                if (idHasil[pos].equals("1")){
                    holder.tidakpernah.setBackgroundResource(R.color.hijau);
                    holder.kadang.setBackgroundResource(R.color.merah);
                    holder.sebagian.setBackgroundResource(R.color.merah);
                    holder.hampir.setBackgroundResource(R.color.merah);
                }else if (idHasil[pos].equals("2")){
                    holder.kadang.setBackgroundResource(R.color.hijau);
                    holder.tidakpernah.setBackgroundResource(R.color.merah);
                    holder.sebagian.setBackgroundResource(R.color.merah);
                    holder.hampir.setBackgroundResource(R.color.merah);
                }else if (idHasil[pos].equals("3")){
                    holder.sebagian.setBackgroundResource(R.color.hijau);
                    holder.tidakpernah.setBackgroundResource(R.color.merah);
                    holder.kadang.setBackgroundResource(R.color.merah);
                    holder.hampir.setBackgroundResource(R.color.merah);

                }else if (idHasil[pos].equals("4")){
                    holder.hampir.setBackgroundResource(R.color.hijau);
                    holder.sebagian.setBackgroundResource(R.color.merah);
                    holder.tidakpernah.setBackgroundResource(R.color.merah);
                    holder.kadang.setBackgroundResource(R.color.merah);
                }else{
                    holder.sebagian.setBackgroundResource(R.color.merah);
                    holder.tidakpernah.setBackgroundResource(R.color.merah);
                    holder.kadang.setBackgroundResource(R.color.merah);
                    holder.hampir.setBackgroundResource(R.color.merah);
                    Log.d(TAG, "onBindViewHolder: array ="+idHasil[0]);
                }
            }

        }

//        passData.onCreate(getIdHasil());

        holder.rgcemas.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                holder.tidakpernah.setBackgroundResource(R.color.merah);
                holder.kadang.setBackgroundResource(R.color.merah);
                holder.sebagian.setBackgroundResource(R.color.merah);
                holder.hampir.setBackgroundResource(R.color.merah);

                switch (i){
                    case R.id.tidakpernah:
                        passData.onClick(cm.getPertanyaan(),cm.getIdPertanyaan(),"1",pos);
                        holder.tidakpernah.setBackgroundResource(R.color.hijau);
                        break;
                    case R.id.kadangkadang:
                        passData.onClick(cm.getPertanyaan(),cm.getIdPertanyaan(),"2",pos);
                        holder.kadang.setBackgroundResource(R.color.hijau);
                        break;
                    case R.id.sebagian:
                        passData.onClick(cm.getPertanyaan(),cm.getIdPertanyaan(),"3",pos);
                        holder.sebagian.setBackgroundResource(R.color.hijau);
                        break;
                    case R.id.hampirwaktu:
                        passData.onClick(cm.getPertanyaan(),cm.getIdPertanyaan(),"4",pos);
                        holder.hampir.setBackgroundResource(R.color.hijau);
                        break;
                    default:

                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return cemas.size();
    }

    class CemasVH extends RecyclerView.ViewHolder{
        TextView pertanyaan;
        RadioButton tidakpernah,kadang,sebagian,hampir;
        RadioGroup rgcemas;
        @SuppressLint("RestrictedApi")
        public CemasVH(@NonNull View v) {
            super(v);
            pertanyaan = v.findViewById(R.id.txtpertanyaan);
            tidakpernah = v.findViewById(R.id.tidakpernah);
            kadang = v.findViewById(R.id.kadangkadang);
            sebagian = v.findViewById(R.id.sebagian);
            hampir = v.findViewById(R.id.hampirwaktu);
            rgcemas = v.findViewById(R.id.rg_cemas);

            if(status.equals("1")){
                tidakpernah.setEnabled(false);
                kadang.setEnabled(false);
                sebagian.setEnabled(false);
                hampir.setEnabled(false);
            }

//            passData.onCreate();
        }

    }


}
