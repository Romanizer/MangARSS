package com.hayn.mangarss.ui.favorites;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.hayn.mangarss.R;
import com.hayn.mangarss.myAdapter;

public class FavoritesFragment extends Fragment {

    private FavoritesViewModel favoritesViewModel;

    ListView listFav;
    ListAdapter adapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        favoritesViewModel = ViewModelProviders.of(this).get(FavoritesViewModel.class);

        View root = inflater.inflate(R.layout.fragment_favorites, container, false);

        adapter = new myAdapter(this,  );
        listFav = root.findViewById(R.id.ListViewFav);
        listFav.setAdapter(adapter);

        favoritesViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });


        return root;
    }
}