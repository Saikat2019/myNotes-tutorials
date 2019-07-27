package com.saikat.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewNoteActivity extends AppCompatActivity {

    public static final String NOTE_TITLE_ADDED = "title_added";
    public static final String NOTE_BODY_ADDED = "body_added";
    private EditText etNoteTitle;
    private EditText etNoteBody;
    private Button btnSave;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);

        toolbar = findViewById(R.id.toolbar_main);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle(R.string.new_note_tool_title);
        }


        etNoteTitle = findViewById(R.id.newNoteTitle);
        etNoteBody = findViewById(R.id.newNoteBody);
        btnSave = findViewById(R.id.bAdd);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent resultIntent = new Intent();
                if(TextUtils.isEmpty(etNoteTitle.getText()) && TextUtils.isEmpty(etNoteBody.getText())){
                    setResult(RESULT_CANCELED,resultIntent);
                }else {
                    String noteTitle = etNoteTitle.getText().toString();
                    String noteBody = etNoteBody.getText().toString();
                    resultIntent.putExtra(NOTE_TITLE_ADDED,noteTitle);
                    resultIntent.putExtra(NOTE_BODY_ADDED,noteBody);
                    setResult(RESULT_OK,resultIntent);
                }
                finish();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
