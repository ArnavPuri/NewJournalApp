package in.teachcoder.journalapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import in.teachcoder.journalapp.database.MyDB;

public class AddEntryActivity extends AppCompatActivity {
    EditText title, highlight, content;
    Button submitButton;
    MyDB myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        title = (EditText) findViewById(R.id.add_entry_title);
        highlight = (EditText) findViewById(R.id.add_entry_highlight);
        content = (EditText) findViewById(R.id.add_entry_content);
        submitButton = (Button) findViewById(R.id.add_entry_button);
        myDB = new MyDB(AddEntryActivity.this);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveToDB();
            }
        });
    }

    private void saveToDB(){
        myDB.open();
        String entryTitle = title.getText().toString();
        String entryHighlight = highlight.getText().toString();
        String entryContent = content.getText().toString();
        long entryDate = System.currentTimeMillis();
        myDB.insertEntry(entryTitle,entryHighlight,entryContent,entryDate);
        myDB.close();
        title.setText("");
        content.setText("");
        highlight.setText("");
        Log.d("Database Updated", entryTitle + " added to db");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
