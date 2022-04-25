package com.imam.myasi;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestAdapterVH> {


    public TestAdapter(ArrayList<DiagnosaModel> diagnosa, Context context) {

    }

    @NonNull
    @Override
    public TestAdapterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TestAdapterVH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TestAdapterVH extends RecyclerView.ViewHolder{

        public TestAdapterVH(@NonNull View itemView) {
            super(itemView);

        }
    }

}
