package in.teachcoder.journalapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button signIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Button signUp = (Button) findViewById(R.id.signup2);
//        signIn = (Button) findViewById(R.id.signin);
//
//        signUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Sign Up pressed", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        signIn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(MainActivity.this, "Sign In pressed", Toast.LENGTH_SHORT).show();
//                signIn.setText("Signed In");
//            }
//        });
    }


}
