package districtdigital.agency;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

import districtdigital.agency.utilities.APIurl;
import districtdigital.agency.utilities.userModel;
import districtdigital.agency.utilities.wevoAPI;
import districtdigital.agency.votingPackage.mainVotingActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registerActivity extends AppCompatActivity {
    View focusView = null;


    private TextView txt;
    private Toolbar toolbar;
    private Spinner sexeSpinner;

    //Date de naissance
    private int year,month,dayOfMonth;
    private String millisInString;

    private String choosenSexe;

    private TextInputLayout input_firstName,input_lastName,input_ddn,input_phone,input_email,input_password,input_adress,input_city;
    String firstName, lastName, ddn, phone, email, password, adress, city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //component initialiser
        sexeSpinner = findViewById(R.id.sexeSpinner);


        //Toolbar Initialiser
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt = (TextView) findViewById(R.id.ToolbarTXT);
        txt.setText("Sign up");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        TextView txt = findViewById(R.id.txt);
        input_firstName = findViewById(R.id.prenom);
        input_lastName = findViewById(R.id.nom);
        input_ddn = findViewById(R.id.input_ddn);
        input_phone = findViewById(R.id.input_phone);
        input_email = findViewById(R.id.input_email);
        input_password = findViewById(R.id.input_password);
        input_adress = findViewById(R.id.adresse);
        input_city = findViewById(R.id.ville);



        sexeInitialiser();

    }


    public void registerClicked(View view) {
        attemptRegister();

    }


    public void goToLoginActivity(View view) {
        //Intent i = new Intent (registerActivity.this,loginActivity.class);
        //startActivity(i);
    }



    private void sexeInitialiser() {
        final String[] items = getResources().getStringArray(R.array.sexe_array);

        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.layout_single_spinner,items){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {

                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        sexeSpinner.setAdapter(spinnerArrayAdapter);

        sexeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);

                if(position == 1)
                {
                    choosenSexe = "Male";
                } else if (position == 2) {
                    choosenSexe = "Female";
                } else if (position == 3) {
                    choosenSexe = "Other";
                }else{
                    choosenSexe ="OUPS!";
                }
            }


            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        sexeSpinner.setAdapter(spinnerArrayAdapter);
    }



    public void pickDate(View view) {

        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(registerActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        millisInString=year+"-"+month+"-"+day;
                        //input_ddn.setText(day+"/"+month+"/"+year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();

        //Data will be stored f year w month w dayof...

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_menu, menu);
        return true;
    }


    /*------------------------------Attempt register----------------------------------------*/

    private void attemptRegister(){
        boolean mCancel = this.Validation();
        if (mCancel) {
            focusView.requestFocus();
        } else {
            registerProcessWithRetrofit(firstName+" "+lastName, email, password, ddn, adress, city, phone, choosenSexe);
        }
    }


    private boolean Validation() {
        // Reset errors.
        input_firstName.setError(null);
        input_firstName.setError(null);
        input_lastName.setError(null);
        input_ddn.setError(null);
        input_phone.setError(null);
        input_email.setError(null);
        input_password.setError(null);
        input_adress.setError(null);
        input_city.setError(null);

        // Store values at the time of the login attempt.
        firstName = input_firstName.getEditText().getText().toString();
        lastName = input_lastName.getEditText().getText().toString();
        ddn = input_ddn.getEditText().getText().toString();
        phone = input_phone.getEditText().getText().toString();
        email = input_email.getEditText().getText().toString();
        password = input_password.getEditText().getText().toString();
        adress = input_adress.getEditText().getText().toString();
        city = input_city.getEditText().getText().toString();

        makeToast(firstName+": :"+lastName+": :"+ddn+": :"+phone+": :"+email+": :"+password+": :"+adress+": :"+city);
        txt.setText(firstName+": :"+lastName+": :"+ddn+": :"+phone+": :"+email+": :"+password+": :"+adress+": :"+city);
        boolean cancel = false;
        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !ispasswordValid(password)) {
            //vpassword.setError("Password not valid !");
            //focusView = vpassword;
            cancel = true;
        }
        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            //vmailAdress.setError("email empty !");
            //focusView = vmailAdress;
            cancel = true;
        } else if (!isemailValid(email)) {
            //vmailAdress.setError("email not valid !");
            //focusView = vmailAdress;
            cancel = true;
        }

        return cancel;
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



    private void registerProcessWithRetrofit(final String fullName, String mailAddress, String password, String age,String address,String city,String phoneNumber,String sex){
        wevoAPI mApiService = this.getInterfaceService();
        Call<userModel> mService = mApiService.register(fullName, mailAddress, password, age, address, city, phoneNumber, sex);
        mService.enqueue(new Callback<userModel>() {
            @Override
            public void onResponse(Call<userModel> call, Response<userModel> response) {
                //userModel mLoginObject = response.body();
                //String returnedResponse = mLoginObject.getId();
                //reponseTEXT.setText(response.code());
                if(response.code()==200)
                {
                    makeToast("sasasasasasa");
                    //Intent i = new Intent (MainActivity.this, mainVotingActivity.class);
                    //startActivity(i);
                }
                else
                {
                    makeToast("fafafafafaf");

                }


            }
            @Override
            public void onFailure(Call<userModel> call, Throwable t) {
                call.cancel();


            }
        });
    }

    //Validators

    private boolean isfirstNameValid(String firstName) {
        return firstName.length() > 2;
    }
    private boolean islastNameValid(String lastName) {
        return lastName.length() > 2;
    }
    private boolean isphoneValid(String phone) {
        return phone.isEmpty();
    }
    private boolean isemailValid(String password) {
        return password.length() > 4;
    }
    private boolean ispasswordValid(String password) {
        return password.length() > 4;
    }
    private boolean isadressValid(String adress) {
        return adress.isEmpty();
    }
    private boolean iscityValid(String city) {
        return city.length() > 2;
    }



    //makeToast
    public void makeToast(String msg)
    {
        Toast.makeText(registerActivity.this, msg, Toast.LENGTH_SHORT).show();
    }
}
