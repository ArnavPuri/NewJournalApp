package in.teachcoder.journalapp;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    TextView title, content, date, highlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView) findViewById(R.id.detail_title);
        content = (TextView) findViewById(R.id.detail_content);
        date = (TextView) findViewById(R.id.detail_date);
        highlight = (TextView) findViewById(R.id.detail_highlight);

        Cursor myCursor = ((SimpleCursorAdapter)MainActivity.entriesList.getAdapter()).getCursor();
        int position = getIntent().getIntExtra("item_clicked",0);
        myCursor.moveToPosition(position);
        title.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.TITLE_NAME)));
        date.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.DATE_NAME)));
        content.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.CONTENT_NAME)));
        highlight.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.HIGHLIGHT_NAME)));
    }
}
