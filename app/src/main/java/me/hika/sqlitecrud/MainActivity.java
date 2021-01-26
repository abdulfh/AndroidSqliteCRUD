package me.hika.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import me.hika.sqlitecrud.activity.EmployeeListActivity;
import me.hika.sqlitecrud.helper.DatabaseHelper;
import me.hika.sqlitecrud.model.EmployeeModel;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText editTextName,editTextEmail;
    private Button buttonAdd,buttonView;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        editTextName = findViewById( R.id.editTextName );
        editTextEmail = findViewById( R.id.editTextEmail );

        buttonAdd = findViewById( R.id.buttonAdd );
        buttonAdd.setOnClickListener( this );

        buttonView = findViewById( R.id.buttonView );
        buttonView.setOnClickListener( this );

    }

    @Override
    public void onClick( View view ) {
        switch (view.getId()){
            case R.id.buttonAdd:
                addEmployee();
                break;
            case R.id.buttonView:
                viewAllEmployee();
                break;
        }
    }

    private void addEmployee() {
        String name = editTextName.getText().toString();
        String email = editTextEmail.getText().toString();

        if (name.isEmpty() || email.isEmpty()) {
            Toast.makeText( MainActivity.this, "All Data Must Filled" , Toast.LENGTH_SHORT ).show();
        } else {
            DatabaseHelper databaseHelper = new DatabaseHelper( MainActivity.this );

            EmployeeModel employeeModel = new EmployeeModel();
            employeeModel.setName( name );
            employeeModel.setEmail( email );

            databaseHelper.addEmployee( employeeModel );
            Toast.makeText( MainActivity.this, "Employee Added" , Toast.LENGTH_SHORT ).show();
            finish();
            startActivity( getIntent() );
        }

    }
    private void viewAllEmployee() {
        Intent intent = new Intent(MainActivity.this, EmployeeListActivity.class );
        startActivity( intent );
    }
}