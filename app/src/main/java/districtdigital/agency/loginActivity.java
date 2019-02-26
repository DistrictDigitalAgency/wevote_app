package districtdigital.agency;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import districtdigital.agency.votingPackage.mainVotingActivity;

public class loginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void goToRegisterActivity(View view) {
        Intent i = new Intent (loginActivity.this,registerActivity.class);
        startActivity(i);
    }

    public void goToVotingActivity(View view) {
        Intent i = new Intent (loginActivity.this, mainVotingActivity.class);
        startActivity(i);
    }
}
