package in.teachcoder.journalapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import in.teachcoder.journalapp.database.MyDB;

public class AddEntryActivity extends AppCompatActivity {
    EditText title, highlight, content;
    Button submitButton;
    MyDB myDB;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);
        title = (EditText) findViewById(R.id.add_entry_title);
        highlight = (EditText) findViewById(R.id.add_entry_highlight);
        content = (EditText) findViewById(R.id.add_entry_content);
        submitButton = (Button) findViewById(R.id.add_entry_button);
        myDB = new MyDB(AddEntryActivity.this);



        String comingFrom = getIntent().getStringExtra("coming_from");

        if (comingFrom == null) {
            comingFrom = " ";
        }
        if (comingFrom.equals("detail_activity")) {
            int position = getIntent().getIntExtra("clicked_item", 0);
            Cursor myCursor = ((SimpleCursorAdapter) MainActivity.entriesList.getAdapter()).getCursor();
            myCursor.moveToPosition(position);
            id = myCursor.getInt(myCursor.getColumnIndexOrThrow(Constants.KEY_ID));
            title.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.TITLE_NAME)));
            content.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.CONTENT_NAME)));
            highlight.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.HIGHLIGHT_NAME)));
            submitButton.setText("Update Entry");
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    updateDB();
                }
            });
        }
        else{
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveToDB();
                }
            });
        }

    }

    private void updateDB() {
        myDB.open();
        String entryTitle = title.getText().toString();
        String entryHighlight = highlight.getText().toString();
        String entryContent = content.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        myDB.updateEntry(entryTitle, entryHighlight, entryContent, dateFormat.format(date), id);
        myDB.close();

        Log.d("Database Updated", entryTitle + " updated in db");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    private void saveToDB() {
        myDB.open();
        String entryTitle = title.getText().toString();
        String entryHighlight = highlight.getText().toString();
        String entryContent = content.getText().toString();
        long entryDate = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = new Date();
        myDB.insertEntry(entryTitle, entryHighlight, entryContent, dateFormat.format(date));
        myDB.close();


        title.setText("");
        content.setText("");
        highlight.setText("");
        Log.d("Database Updated", entryTitle + " added to db");
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
