package districtdigital.agency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickedSignIn(View view) {
        Intent i = new Intent (MainActivity.this,loginActivity.class);
        startActivity(i);
    }

    public void clickedRegistre(View view) {
        Intent i = new Intent (MainActivity.this,registerActivity.class);
        startActivity(i);
    }

    public void clickedGetToKnowTheApp(View view) {
        Intent i = new Intent (MainActivity.this,getToKnowActivity.class);
        startActivity(i);
    }
}
