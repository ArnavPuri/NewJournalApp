package in.teachcoder.journalapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import in.teachcoder.journalapp.database.MyDB;

public class MainActivity extends AppCompatActivity {
    static ListView entriesList;
    MyDB dbAccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entriesList = (ListView) findViewById(R.id.entry_list);
        dbAccess = new MyDB(MainActivity.this);
        displayListView();


        entriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, DetailActivity.class);
                i.putExtra("item_clicked",position);
                startActivity(i);

//                SimpleCursorAdapter temp = (SimpleCursorAdapter) entriesList.getAdapter();
//                Cursor c = temp.getCursor();
//                String entryTitle = c.getString(c.getColumnIndexOrThrow(Constants.TITLE_NAME));
//                Toast.makeText(MainActivity.this ,entryTitle, Toast.LENGTH_SHORT).show();
            }
        });

//        entriesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
////                Cursor c = ((SimpleCursorAdapter)entriesList.getAdapter()).getCursor();
////                String entryTitle = c.getString(c.getColumnIndexOrThrow(Constants.TITLE_NAME));
////                Toast.makeText(MainActivity.this,entryTitle,Toast.LENGTH_SHORT).show();
//
////                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
////                intent.putExtra("item_clicked", position);
////                startActivity(intent);
//            }
//        });
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
