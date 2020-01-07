package com.hayn.mangarss.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.hayn.mangarss.AsyncResponse;
import com.hayn.mangarss.AsyncResponseArray;
import com.hayn.mangarss.HomeLoaderTask;
import com.hayn.mangarss.MangaLoaderTask;
import com.hayn.mangarss.R;
import com.hayn.mangarss.myAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeFragment extends Fragment implements AsyncResponseArray, AsyncResponse {

    private HomeViewModel homeViewModel;
    private ListView listHome;
    private myAdapter adapter;

    private String[] mangaNames;
    private String[] mangaImgUrls;
    private String[] mangaUrls;

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

        listHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        new HomeLoaderTask(this).execute(HOME_URL);

        return root;
    }

    @Override
    public void processFinish(HashMap<String,String> result){

        List<String> keys   = new ArrayList<>(result.keySet());
        List<String> values = new ArrayList<>(result.values());

        mangaNames =   keys.toArray(new String[keys.size()]);
        mangaUrls  = values.toArray(new String[values.size()]);

        //System.out.println("nigga got here ma boiii");

        //System.out.println("M1 " + result.containsKey("Tales of Demons and Gods"));

        mangaImgUrls = new String[mangaUrls.length];

        for (String name : mangaNames) {
            System.out.println(name);
        }

        for (String url : mangaUrls) {
            System.out.println("eat major dicks " + url);
        }

        for(int i = 0; i < mangaUrls.length; i++){
            new MangaLoaderTask(this,i).execute(mangaUrls[i]);
        }

        updateListView(); // do?
    }

    private synchronized void updateListView(){
        adapter = new myAdapter(getContext(), mangaNames, mangaImgUrls);
        int x = listHome.getScrollX();
        int y = listHome.getScrollY();
        listHome.setAdapter(adapter);
        adapter.notifyDataSetChanged();  // bugs out scrolling while loading wtf
        listHome.setScrollX(x);
        listHome.setScrollY(y);
    }

    @Override
    public void processFinish(String result) {
        // nothing
    }

    @Override
    public void processFinish(String result, int index) {
        mangaImgUrls[index] = result; // write result to list of Img Urls
        updateListView();
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------------- " + result);
    }
}