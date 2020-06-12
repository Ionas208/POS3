package com.example.test;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import at.htlkaindorf.pairpro.detail;

public class TestHolder extends RecyclerView.ViewHolder {

    private TextView ParentTest;
    private TextView ChildTest;
    private TestObject test;

    public TestHolder(@NonNull View itemView, TextView parentTest, TextView childTest) {
        super(itemView);
        ParentTest = parentTest;
        ChildTest = childTest;

        itemView.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.pointer.getApplicationContext(), detail.class);
            intent.putExtra("test", test);
            MainActivity.pointer.startActivity(intent);
        });
    }


    public TextView getParentTest() {
        return ParentTest;
    }

    public void setParentTest(TextView parentTest) {
        ParentTest = parentTest;
    }

    public TextView getChildTest() {
        return ChildTest;
    }

    public void setChildTest(TextView childTest) {
        ChildTest = childTest;
    }

    public TestObject getTest() {
        return test;
    }

    public void setTest(TestObject test) {
        this.test = test;
    }
}
