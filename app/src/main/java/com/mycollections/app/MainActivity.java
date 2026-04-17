package com.mycollections.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView    recyclerView;
    private ItemAdapter     adapter;
    private DatabaseHelper  dbHelper;
    private List<CollectionItem> itemList;
    private TextView        tvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper     = new DatabaseHelper(this);
        recyclerView = findViewById(R.id.recycler_view);
        tvEmpty      = findViewById(R.id.tv_empty);
        Button btnAdd = findViewById(R.id.btn_add);

        LinearLayoutManager lm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(lm);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        btnAdd.setOnClickListener(v ->
                startActivity(new Intent(this, AddItemActivity.class)));

        loadItems();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadItems();
    }

    private void loadItems() {
        itemList = dbHelper.getAllItems();
        adapter  = new ItemAdapter(itemList, (item, pos) -> {
            dbHelper.deleteItem(item.getId());
            adapter.removeItem(pos);
            updateEmptyView();
        });
        recyclerView.setAdapter(adapter);
        updateEmptyView();
    }

    private void updateEmptyView() {
        if (itemList.isEmpty()) {
            tvEmpty.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}
