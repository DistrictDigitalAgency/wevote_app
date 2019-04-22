package districtdigital.agency;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        animateTheBackground();
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



    private void animateTheBackground() {
        // init constraintLayout
        constraintLayout = (ConstraintLayout) findViewById(R.id.constraintLayout);

        // initializing animation drawable by getting background from constraint layout
        animationDrawable = (AnimationDrawable) constraintLayout.getBackground();

        // setting enter fade animation duration to 5 seconds
        animationDrawable.setEnterFadeDuration(5000);

        // setting exit fade animation duration to 2 seconds
        animationDrawable.setExitFadeDuration(2000);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (animationDrawable != null && !animationDrawable.isRunning()) {
            // start the animation
            animationDrawable.start();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (animationDrawable != null && animationDrawable.isRunning()) {
            // stop the animation
            animationDrawable.stop();
        }
    }
}
