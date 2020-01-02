package com.hayn.mangarss.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.hayn.mangarss.AsyncResponseArray;
import com.hayn.mangarss.HomeLoaderTask;
import com.hayn.mangarss.R;
import com.hayn.mangarss.myAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment implements AsyncResponseArray {

    private HomeViewModel homeViewModel;
    private ListView listHome;
    private myAdapter adapter;

    private String[] mangaNames;
    private String[] mangaImgUrls;

    private static final String HOME_URL = "https://onmanga.com/manga/?m_orderby=trending";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        listHome = root.findViewById(R.id.ListViewHome);

        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                //textView.setText(s);
            }
        });



        new HomeLoaderTask(this).execute(HOME_URL);

        return root;
    }

    private void updateListView(){

        adapter = new myAdapter(getContext(), mangaNames, mangaImgUrls);
        listHome.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void processFinish(HashMap<String,String> result){

        List<String> keys = new ArrayList<>(result.keySet());
        List<String>  values = new ArrayList<>(result.values());

        mangaNames = keys.toArray(new String[keys.size()]);
        mangaImgUrls = values.toArray(new String[values.size()]);
        System.out.println("nigga got here ma boiii");

        System.out.println("M1 " + result.containsKey("Tales of Demons and Gods"));

        for(int i = 0; i < mangaNames.length; i++){
            System.out.println(mangaNames[i] + " e");
        }

        for(int i = 0; i < mangaImgUrls.length; i++){
            System.out.println("eat major dicks " + mangaImgUrls[i]);
        }

        updateListView();
    }
}