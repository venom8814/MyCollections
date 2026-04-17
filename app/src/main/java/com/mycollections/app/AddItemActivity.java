package com.mycollections.app;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddItemActivity extends AppCompatActivity {

    private EditText       etTitle, etDescription;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        dbHelper      = new DatabaseHelper(this);
        etTitle       = findViewById(R.id.et_title);
        etDescription = findViewById(R.id.et_description);
        Button btnSave   = findViewById(R.id.btn_save);
        Button btnCancel = findViewById(R.id.btn_cancel);

        btnSave.setOnClickListener(v   -> saveItem());
        btnCancel.setOnClickListener(v -> finish());
    }

    private void saveItem() {
        String title = etTitle.getText().toString().trim();
        String desc  = etDescription.getText().toString().trim();

        if (TextUtils.isEmpty(title)) {
            etTitle.setError(getString(R.string.error_title_required));
            return;
        }

        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())
                .format(new Date());

        CollectionItem item = new CollectionItem();
        item.setTitle(title);
        item.setDescription(desc);
        item.setDateAdded(date);

        dbHelper.insertItem(item);
        Toast.makeText(this, R.string.item_saved, Toast.LENGTH_SHORT).show();
        finish();
    }
}
