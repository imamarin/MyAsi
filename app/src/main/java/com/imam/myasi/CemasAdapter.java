package com.imam.myasi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CemasAdapter extends RecyclerView.Adapter<CemasAdapter.CemasVH> {

    ArrayList<CemasModel> cemas;
    Context context;

    public CemasAdapter(ArrayList<CemasModel> cemas, Context context) {
        this.cemas = cemas;
        this.context = context;
    }

    @NonNull
    @Override
    public CemasVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_item_cemas, parent, false);
        CemasAdapter.CemasVH cvh = new CemasAdapter.CemasVH(view);

        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CemasVH holder, int position) {
        CemasModel cm = cemas.get(position);
        holder.pertanyaan.setText(cm.getPertanyaan());
    }

    @Override
    public int getItemCount() {
        return cemas.size();
    }

    class CemasVH extends RecyclerView.ViewHolder{
        TextView pertanyaan;
        public CemasVH(@NonNull View v) {
            super(v);
            pertanyaan = v.findViewById(R.id.txtpertanyaan);
        }

    }
}
