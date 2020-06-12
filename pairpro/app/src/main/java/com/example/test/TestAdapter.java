package com.example.test;

import android.content.res.AssetManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class TestAdapter extends RecyclerView.Adapter<TestHolder> {

    private List<TestObject> list;

    public TestAdapter(){
        AssetManager am = MainActivity.pointer.getAssets();
        try {
            InputStream is = am.open("data.csv");
            list = (List<TestObject>) new BufferedReader(new InputStreamReader(is))
                    .lines()
                    .skip(1)
                    .map(TestObject::reader)
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item,parent,false);
        TextView childTest = view.findViewById(R.id.ChildTest);
        TextView parentTest = view.findViewById(R.id.ParentTest);
        return new TestHolder(view, parentTest, childTest);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        TestObject test = list.get(position);
        holder.getParentTest().setText(test.getName());
        if(test instanceof TestChild1){
            holder.getChildTest().setText(((TestChild1) test).getUrl().toString());
        }
        else{
            holder.getChildTest().setText(((TestChild2)test).getKeyboard());
        }
        holder.setTest(test);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
