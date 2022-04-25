package com.imam.myasi;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.imam.myasi.databinding.FragmentFirstBinding;

import java.util.ArrayList;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    RecyclerView recyclerView;
    DiagnosaAdapter diagnosaAdapter;
    ArrayList<DiagnosaModel> diagnosa;
    TextView txtTotal;
    ImageButton adddiagnosa;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        return inflater.inflate(R.layout.fragment_first, container, false);

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

          DbHelper dbHelper = new DbHelper(getContext());

          recyclerView = view.findViewById(R.id.recyclerview);
//          txtTotal = view.findViewById(R.id.total);
          adddiagnosa = view.findViewById(R.id.addDiagnosa);

          diagnosa = (ArrayList<DiagnosaModel>) dbHelper.getAllDiagnosa();

          diagnosaAdapter = new DiagnosaAdapter(diagnosa, getContext());
//          txtTotal.setText("Total Data: "+diagnosa.size());
          recyclerView.setAdapter(diagnosaAdapter);

          LinearLayoutManager llm = new LinearLayoutManager(getContext());
          llm.setOrientation(RecyclerView.VERTICAL);
          recyclerView.setLayoutManager(llm);

          adddiagnosa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: bisa");

                AlertDialog.Builder alertD = new AlertDialog.Builder(getContext());
                final EditText editDiagnosa = new EditText(getContext());
                alertD.setTitle("Tambah Diagnosa");
                alertD.setView(editDiagnosa);

                LinearLayout layoutDignosa = new LinearLayout(getContext());
                layoutDignosa.setOrientation(LinearLayout.VERTICAL);
                layoutDignosa.addView(editDiagnosa);
                alertD.setView(layoutDignosa);

                alertD.setPositiveButton("Simpan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DiagnosaModel dgs = new DiagnosaModel(editDiagnosa.getText().toString(),0);
                        dbHelper.addDiagnosa(dgs);

                        getActivity().finish();
                        getActivity().overridePendingTransition( 0, 0);
                        Intent intent = new Intent(getActivity(), DiagnosaActivity.class);
                        getActivity().startActivity(intent);
                        getActivity().overridePendingTransition( 0, 0);
                    }
                });

                alertD.setNegativeButton("Batal", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDiagnosa = alertD.create();
                alertDiagnosa.show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}