package districtdigital.agency;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

public class registerActivity extends AppCompatActivity {
    private TextView txt;
    private Toolbar toolbar;
    private Spinner sexeSpinner;

    //Date de naissance
    private EditText input_ddn;
    private int year,month,dayOfMonth;
    private String millisInString;


    private String choosenSexe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //component initialiser
        sexeSpinner = findViewById(R.id.sexeSpinner);


        //Toolbar Initialiser
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        txt = (TextView) findViewById(R.id.ToolbarTXT);
        txt.setText("S'inscrire");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        sexeInitialiser();

    }

    public void goToLoginActivity(View view) {
        Intent i = new Intent (registerActivity.this,loginActivity.class);
        startActivity(i);
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
                    choosenSexe = "Homme";
                } else if (position == 2) {
                    choosenSexe = "Femme";
                } else if (position == 3) {
                    choosenSexe = "Autre";
                }else{
                    choosenSexe ="Erreur";
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
                        input_ddn.setText(day+"/"+month+"/"+year);
                    }
                }, year, month, dayOfMonth);
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();

        //Data will be stored f year w month w dayof...

    }


}
