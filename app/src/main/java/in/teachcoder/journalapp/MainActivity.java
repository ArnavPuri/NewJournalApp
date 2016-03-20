package in.teachcoder.journalapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import in.teachcoder.journalapp.database.MyDB;

public class MainActivity extends AppCompatActivity {
    ListView entriesList;
    MyDB dbAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entriesList = (ListView) findViewById(R.id.entry_list);
        dbAccess = new MyDB(MainActivity.this);
        displayListView();
    }


    public void displayListView(){
        Cursor cursor = dbAccess.getEntries();
        String[] from = new String[]{
                Constants.TITLE_NAME,
                Constants.CONTENT_NAME,
                Constants.DATE_NAME
        };

        int[] to = new int[]{
                R.id.entry_title,
                R.id.entry_desc,
                R.id.entry_date
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.row, cursor, from, to, 0);
        entriesList.setAdapter(adapter);

    }


}
