package com.imam.myasi;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends ArrayAdapter {

    private Context context;
    private List<CemasModel> cemas;

    private static class ViewHolder{
        TextView pertanyaan;
    }

    public ListAdapter(@NonNull  Context context, ArrayList<CemasModel> cemas){
        super(context,0,cemas);
        this.cemas = cemas;
    }

    @Override
    public int getCount() {
        return cemas.size();
    }

    @Override
    public Object getItem(int i) {
        return cemas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder = new ViewHolder();
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item_cemas, parent, false);

        }

        viewHolder.pertanyaan = view.findViewById(R.id.txtpertanyaan);
        CemasModel cms = cemas.get(position);
        viewHolder.pertanyaan.setText(cms.getPertanyaan());

        view.setTag(view);


        return view;
    }

    //    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//
//        ViewHolder viewHolder;
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//        if(view == null){
//            viewHolder = new ViewHolder();
//            view = inflater.inflate(R.layout.list_item_cemas, viewGroup, false);
//            viewHolder.pertanyaan = view.findViewById(R.id.txtpertanyaan);
//
//            view.setTag(view);
//        }else{
//            viewHolder = (ViewHolder) view.getTag();
//        }
//
//        CemasModel cms = cemas.get(i);
//        viewHolder.pertanyaan.setText(cms.getPertanyaan());
//
//        return null;
//    }
}
