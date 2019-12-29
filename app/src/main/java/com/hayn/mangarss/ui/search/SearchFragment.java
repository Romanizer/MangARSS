package com.hayn.mangarss.ui.search;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hayn.mangarss.R;
import com.hayn.mangarss.ReaderActivity;

public class SearchFragment extends Fragment {

    private SearchViewModel searchViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        searchViewModel = ViewModelProviders.of(this).get(SearchViewModel.class);

        View root = inflater.inflate(R.layout.fragment_search, container, false);

        final ListView listViewSearch = root.findViewById(R.id.ListViewSearch);
        Button btn = root.findViewById(R.id.buttonSearch);

        searchViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), ReaderActivity.class));
            }
        });

        return root;
    }
}