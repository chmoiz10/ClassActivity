package com.example.classactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.textfield.TextInputEditText;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected List<EmployeeData> onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        TextInputEditText txtID, txtName, txtSalary;
        MaterialButton btnAddEmp, btnDeleteEmp, btnUpdateEmp, btnViewAllEmp;
        DB_Con dtEmployee;
        ArrayList<EmployeeData> EmployeeList;

        protected void onCreate (Bundle savedInstanceState){
            super.onCreate( savedInstanceState );
            setContentView( R.layout.activity_main );

            txtID = findViewById( R.id.txtID );
            txtName = findViewById( R.id.txtID2 );
            txtSalary = findViewById( R.id.txtID3 );

            btnAddEmp = findViewById( R.id.btnAddEmp );
            btnDeleteEmp = findViewById( R.id.btnDeleteEmp );
            btnUpdateEmp = findViewById( R.id.btnUpdateEmp );
            btnViewAllEmp = findViewById( R.id.btnViewAllEmp );

            btnAddEmp.setOnClickListener( (View v) -> {
                if (TextUtils.isEmpty( txtID.getText( ).toString( ) )) {
                    Toast.makeText( MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG ).show( );
                } else {
                    EmployeeData Data = new EmployeeData( );
                    Data.setID( Integer.parseInt( txtID.getText( ).toString( ) ) );
                    Data.setName( txtName.getText( ).toString( ) );
                    Data.setSalary( Integer.parseInt( txtID.getText( ).toString( ) ) );

                    dtEmployee = new DB_Con( MainActivity.this, "ClassActivity", null, 1 );

                    int RtnValue = dtEmployee.AddEmployee( Data );
                    if (RtnValue > -1)
                        Toast.makeText( MainActivity.this, "Data Inserted: " + RtnValue, Toast.LENGTH_LONG ).show( );
                    else
                        Toast.makeText( MainActivity.this, "Data not Inserted due to duplication of ID", Toast.LENGTH_LONG ).show( );
                }
            } );

            btnDeleteEmp.setOnClickListener( v -> {
                dtEmployee = new DB_Con( this, "ClassActivity", null, 1 );
                int RtnValue = dtEmployee.DeleteEmployee( Integer.parseInt( txtID.getText( ).toString( ) ) );
                if (RtnValue > -1) {
                    Toast.makeText( this, "Data Deleted: " + RtnValue, Toast.LENGTH_LONG ).show( );

                } else
                    Toast.makeText( this, "Data not Deleted.", Toast.LENGTH_LONG ).show( );
            } );

            btnUpdateEmp.setOnClickListener( v -> {
                if (TextUtils.isEmpty( txtID.getText( ).toString( ) )) {
                    Toast.makeText( MainActivity.this, "Something went wrong. Check your input values", Toast.LENGTH_LONG ).show( );
                } else {

                    try {
                        EmployeeData Record = dtEmployee.FindEmployee( Integer.parseInt( txtID.getText( ).toString( ) ) );
                        if (Record != null) {
                            txtID.setText( String.valueOf( Record.getID( ) ) );
                            txtID.setEnabled( false );
                            txtName.setText( Record.getName( ) );
                            txtSalary.setText( String.valueOf( Record.getSalary( ) ) );

                        }
                    } catch (ParseException e) {
                        e.printStackTrace( );
                    }

                    EmployeeData Data = new EmployeeData( );
                    Data.setID( Integer.parseInt( txtID.getText( ).toString( ) ) );
                    Data.setName( txtName.getText( ).toString( ) );
                    Data.setSalary( Integer.parseInt( txtID.getText( ).toString( ) ) );

                    dtEmployee = new DB_Con( MainActivity.this, "ClassActivity", null, 1 );

                    int RtnValue = dtEmployee.AddEmployee( Data );
                    if (RtnValue > -1)
                        Toast.makeText( MainActivity.this, "Data Inserted: " + RtnValue, Toast.LENGTH_LONG ).show( );
                    else
                        Toast.makeText( MainActivity.this, "Data not Inserted due to duplication of ID", Toast.LENGTH_LONG ).show( );
                }
            } );

            btnViewAllEmp.setOnClickListener( v -> {
                String Data = "";

                LayoutInflater inflater = LayoutInflater.from( this );
                View subView = inflater.inflate( R.layout.viewall, null );

                final TextView AllData = (TextView) subView.findViewById( R.id.AllData );

                dtEmployee = GetAllEmployeeData( );
                for (EmployeeData userData : EmployeeList) {
                    Data += "ID: " + userData.getID( ) + "\n";
                    Data += "Name: " + userData.getName( ) + "\n";
                    Data += "Salary: " + userData.getSalary( ) + "\n";
                }
                AllData.setText( Data );
                AlertDialog.Builder AlertBuilder = new AlertDialog.Builder( MainActivity.this );
                AlertBuilder.setTitle( "All Data" );
                AlertBuilder.setView( subView );
                AlertBuilder.create( );

                AlertBuilder.show( );
            } );
        }

        private ArrayList<EmployeeData> GetAllEmployeeData() {
            dtEmployee = new DB_Con( this, "ClassActivity", null, 1 );
            return dtEmployee.EmployeeList( );
        }
    }

    private DB_Con GetAllEmployeeData() {
    }
}
}