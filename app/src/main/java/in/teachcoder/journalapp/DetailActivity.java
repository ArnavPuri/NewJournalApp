package in.teachcoder.journalapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {
    TextView title, content, date, highlight;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView) findViewById(R.id.detail_title);
        content = (TextView) findViewById(R.id.detail_content);
        date = (TextView) findViewById(R.id.detail_date);
        highlight = (TextView) findViewById(R.id.detail_highlight);

        Cursor myCursor = ((SimpleCursorAdapter)MainActivity.entriesList.getAdapter()).getCursor();
        position = getIntent().getIntExtra("item_clicked",0);
        myCursor.moveToPosition(position);
        title.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.TITLE_NAME)));
        date.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.DATE_NAME)));
        content.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.CONTENT_NAME)));
        highlight.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.HIGHLIGHT_NAME)));
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.edit_entry_menu:
                Intent intent = new Intent(DetailActivity.this, AddEntryActivity.class);
                intent.putExtra("coming_from", "detail_activity");
                intent.putExtra("item_clicked", position);
                startActivity(intent);
                return true;
            case R.id.share_entry_menu:
                Toast.makeText(DetailActivity.this, "Share menu clicked", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
