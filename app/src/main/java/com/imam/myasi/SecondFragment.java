package com.imam.myasi;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.imam.myasi.databinding.FragmentSecondBinding;

import java.util.ArrayList;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private ListView listView;
    private ArrayList<String> listItem;
    private ArrayAdapter adapter;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        return inflater.inflate(R.layout.fragment_second, container, false);

    }

    @SuppressLint("ResourceAsColor")
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getActivity().getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(R.color.teal_700);
        }

        listView = view.findViewById(R.id.listProfil);
        listItem =  new ArrayList<>();
        listItem.add("Nama");
        listItem.add("No. KTP");
        listItem.add("Email");
        listItem.add("No. Handphone");
        viewData(listItem);

    }

    private void viewData(ArrayList<String> list) {
        adapter = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_2, android.R.id.text1, list){
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                TextView title = view.findViewById(android.R.id.text1);
                TextView subtitle = view.findViewById(android.R.id.text2);
                subtitle.setTextColor(Color.GRAY);
                if (title.getText().equals("Nama")) {
                    subtitle.setText("Imam Amirulloh");
                } else if (title.getText().equals("No. KTP")){
                    subtitle.setText("12345678899");
                } else if (title.getText().equals("No. Handphone")){
                    subtitle.setText("08324278899");
                } else {
                    subtitle.setText("imamamirulloh@gmail.com");
                }

                return view;
            }
        };
        listView.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}