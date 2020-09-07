package com.demo.ess;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EditData extends AppCompatActivity {
    EditText editText;
    Button button;
    DatabaseHelper db;
    String str_position;
    long idsingle ;
    int position;
    EditText mCustomerName;
    EditText mPhoneNumber;
    EditText mEventDate;
    EditText mEventLocation;
    EditText mNumberOfPax;
    EditText mAmount;
    EditText mRemarks;
    private  Data  singledata ;

    DatePickerDialog picker;
    EditText eText;

    private List<Data> List;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        final List<Data> List = new ArrayList<>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_data);

        mCustomerName = findViewById(R.id.CustomerName);
        mPhoneNumber = findViewById(R.id.PhoneNumber);
        mEventDate = findViewById(R.id.SelectedDate);
        mEventLocation = findViewById(R.id.location);
        mNumberOfPax = findViewById(R.id.pax);
        mAmount = findViewById(R.id.amount);
        mRemarks = findViewById(R.id.remarks);


        button = findViewById(R.id.button);


        mEventDate.setFocusable(false);

        Bundle bundle = getIntent().getExtras();
        str_position = bundle.getString("position");
        position = Integer.parseInt(str_position);
        idsingle = Long.parseLong(bundle.getString("idsingle"));


        db = new DatabaseHelper(this);
        singledata= db.getData(idsingle);
        mCustomerName.setText(singledata.getCustomerName());
        mPhoneNumber.setText(singledata.getPhoneNumber());
        mAmount.setText(singledata.getAmount());
        mEventDate.setText(singledata.getEventDate());



        mEventLocation.setText(singledata.getEventLocation());
        mRemarks.setText(singledata.getRemarks());
        mNumberOfPax.setText(singledata.getNumberOfPax());



       //Log.d("mydata", String.valueOf(idsingle));

        List.addAll(db.getAllDatas());
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Data data = List.get(position);
               // mCustomerName.setText(mCustomerName.getText().toString().trim()); ;
                // updating data text
                data .setCustomerName(mCustomerName.getText().toString().trim());
                data .setPhoneNumber(mPhoneNumber.getText().toString().trim());
                data .setEventDate(mEventDate.getText().toString().trim());
                data .setEventLocation(mEventLocation.getText().toString().trim());
                data .setNumberOfPax(mNumberOfPax.getText().toString().trim());
                data .setAmount(mAmount.getText().toString().trim());
                data .setRemarks(mRemarks.getText().toString().trim());

                // updating data in db
                db.updateData(data);
                MainActivity.notifyAdapter();
                Intent intent = new Intent(EditData.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });



        eText=(EditText) findViewById(R.id.SelectedDate);
        eText.setInputType(InputType.TYPE_NULL);

        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                picker = new DatePickerDialog(EditData.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                String month = String.valueOf(monthOfYear+1);
                                String Day = String.valueOf(dayOfMonth);
                                if  (monthOfYear<10){
                                    month = "0"+ month ;
                                }
                                if  (dayOfMonth<10){
                                    Day = "0"+ Day ;
                                }
                                eText.setText(year+ "-" + (month ) + "-" + Day  );
                                month  ="";
                                Day = "";
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }



    private String formatDate(String dateStr) {
        try {

            SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
            Date date = fmt.parse(dateStr);
            SimpleDateFormat fmtOut = new SimpleDateFormat("MMM d yyyy");
            return fmtOut.format(date);
        } catch (ParseException e) {
            Log.e("error",e.getMessage());
        }
        return "";
    }
}