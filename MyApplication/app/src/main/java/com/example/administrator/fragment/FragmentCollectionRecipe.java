package com.example.administrator.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.adapter.AdapterCollectionRecipe;
import com.example.administrator.domain.DataCollectionRecipe;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.RecipeShowActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘博园on 2016/12/21.
 */

public class FragmentCollectionRecipe extends Fragment{
    private ArrayAdapter<String> adapter;
    int image = R.drawable.img_loading;
    private List<DataCollectionRecipe> ldcr = new ArrayList<>();
    private ListView lv_collection_recipe;
    private AdapterCollectionRecipe collectionRecipe_adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_collection_recipe,container,false);
        collectionRecipe_adapter = new AdapterCollectionRecipe(ldcr,getActivity());
        lv_collection_recipe = (ListView)view.findViewById(R.id.Lv_collection_recipe);
        lv_collection_recipe.setAdapter(collectionRecipe_adapter);
        setListener();
        getData();
        return view;
    }

    private void setListener() {
        lv_collection_recipe.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), RecipeShowActivity.class);
                startActivity(intent);
            }
        });
    }
        private void getData() {
            ldcr.add((new DataCollectionRecipe(0L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(1L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(2L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(3L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(0L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(1L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(2L,R.drawable.img_loading,"食谱","食谱")));
            ldcr.add((new DataCollectionRecipe(3L,R.drawable.img_loading,"食谱","食谱")));

        }

}
