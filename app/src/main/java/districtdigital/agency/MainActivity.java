package districtdigital.agency;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.subhrajyoti.passwordview.PasswordView;

import java.util.List;

import districtdigital.agency.utilities.APIurl;
import districtdigital.agency.utilities.loginModel;
import districtdigital.agency.utilities.userModel;
import districtdigital.agency.utilities.wevoAPI;
import districtdigital.agency.votingPackage.mainVotingActivity;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    String BASE_URL = "http://district-workstation.com/wevo/wevapp/public/api/apiV1_0/";

    private ConstraintLayout constraintLayout;
    private AnimationDrawable animationDrawable;
    private String email;
    private String password;
    AutoCompleteTextView vmailAdress;
    PasswordView vpassword;
    View focusView = null;
    ProgressDialog progressDialog;

    TextView reponseTEXT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        animateTheBackground();

         vmailAdress = (AutoCompleteTextView) findViewById(R.id.mailAdressField);
         vpassword = (PasswordView) findViewById(R.id.passwordField);
         progressDialog = new ProgressDialog(MainActivity.this);




    }



    public void clickedSignIn(View view) {

        //makeSnack(mailAdress.getText().toString()+""+password.getText().toString());
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        attemptLogin();

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

    public void makeSnack(String msg)
    {
        Snackbar.make(findViewById(android.R.id.content), msg, Snackbar.LENGTH_SHORT)
                .show();
    }






    /*------------------------------Attempt Login----------------------------------------*/

    private void attemptLogin(){
        boolean mCancel = this.loginValidation();
        if (mCancel) {
            focusView.requestFocus();
        } else {
            loginProcessWithRetrofit(email, password);
        }
    }


    private boolean loginValidation() {
        // Reset errors.
        vmailAdress.setError(null);
        vpassword.setError(null);
        // Store values at the time of the login attempt.
        email = vmailAdress.getText().toString();
        password = vpassword.getText().toString();
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            vpassword.setError("Password not valid !");
            focusView = vpassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            vmailAdress.setError("email empty !");
            focusView = vmailAdress;
            cancel = true;
        } else if (!isEmailValid(email)) {
            vmailAdress.setError("email not valid !");
            focusView = vmailAdress;
            cancel = true;
        }
        return cancel;
    }


    private boolean isEmailValid(String email) {
        return email.contains("@");
    }
    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    private wevoAPI getInterfaceService() {
        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIurl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        final wevoAPI mInterfaceService = retrofit.create(wevoAPI.class);
        return mInterfaceService;
    }



    private void loginProcessWithRetrofit(final String email, String password){
        wevoAPI mApiService = this.getInterfaceService();
        Call<userModel> mService = mApiService.authenticate(email, password);
        mService.enqueue(new Callback<userModel>() {
            @Override
            public void onResponse(Call<userModel> call, Response<userModel> response) {
                //userModel mLoginObject = response.body();
                //String returnedResponse = mLoginObject.getId();
                //reponseTEXT.setText(response.code());
                if(response.code()==200)
                {
                    makeSnack("sasasasasasa");
                    Intent i = new Intent (MainActivity.this, mainVotingActivity.class);
                    startActivity(i);
                }
                else
                {
                    makeSnack("fafafafafaf");

                }

                progressDialog.dismiss();

            }
            @Override
            public void onFailure(Call<userModel> call, Throwable t) {
                call.cancel();
                progressDialog.dismiss();
                makeSnack("orrouoruourouourr");

            }
        });
    }
}
