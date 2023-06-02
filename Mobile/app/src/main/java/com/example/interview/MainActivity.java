package com.example.interview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.widget.Button;

import com.example.interview.databinding.ActivityMainBinding;
import com.example.interview.model.db.ContentJoke;
import com.example.interview.model.provider.DatabaseHelper;

import java.util.List;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    AppCompatTextView contentTv;
    Button btnLike;
    Button btnDisLike;
    int idCurrent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Objects.requireNonNull(getSupportActionBar()).hide();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initView(binding);

        DatabaseHelper db = new DatabaseHelper(this);
        db.clearDatabase();

        initHardData(db);
        List<ContentJoke> contents = db.getAllContentsJoke();
        contentTv.setText(contents.get(idCurrent).getContentText());

        onPressBtnLike(contents, db);
        onPressBtnUnLike(contents, db);
    }

    void initView(ActivityMainBinding binding) {
        contentTv = binding.content;
        btnLike = binding.btnLike;
        btnDisLike = binding.btnDislike;
    }

    void initHardData(DatabaseHelper db) {
        db.addContentJoke(new ContentJoke(Constants.contentText, 0, 0));
        db.addContentJoke(new ContentJoke(Constants.contentTextOther, 0, 0));
        db.addContentJoke(new ContentJoke(Constants.contentTextOtherA, 0, 0));
        db.addContentJoke(new ContentJoke(Constants.contentTextOtherB, 0, 0));
    }

    void onPressBtnLike(List<ContentJoke> contents, DatabaseHelper db) {
        btnLike.setOnClickListener(v -> {
            for (ContentJoke c : contents) {
                if (c.getIsRead() == 0 && c.getId() < contents.size()) {
                    contentTv.setText(contents.get(c.getId()).getContentText());
                    c.setIsLike(1);
                    c.setIsRead(1);
                    db.updateContentJoke(c);
                    break;
                } else {
                    contentTv.setText(Constants.contentTextOver);
                }
            }
        });
    }

    void onPressBtnUnLike(List<ContentJoke> contents, DatabaseHelper db) {
        btnDisLike.setOnClickListener(v -> {
            for (ContentJoke c : contents) {
                if (c.getIsRead() == 0 && c.getId() < contents.size()) {
                    contentTv.setText(contents.get(c.getId()).getContentText());
                    c.setIsLike(0);
                    c.setIsRead(1);
                    db.updateContentJoke(c);
                    break;
                } else {
                    contentTv.setText(Constants.contentTextOver);
                }
            }
        });
    }
}