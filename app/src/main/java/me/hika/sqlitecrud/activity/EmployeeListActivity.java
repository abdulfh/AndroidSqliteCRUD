package me.hika.sqlitecrud.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import me.hika.sqlitecrud.MainActivity;
import me.hika.sqlitecrud.R;
import me.hika.sqlitecrud.adapter.EmployeeAdapter;
import me.hika.sqlitecrud.helper.DatabaseHelper;
import me.hika.sqlitecrud.model.EmployeeModel;

public class EmployeeListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_employee_list );

        recyclerView = findViewById( R.id.recyclerView );
        recyclerView.setLayoutManager( new LinearLayoutManager( this ) );
        recyclerView.setHasFixedSize( true );

        DatabaseHelper databaseHelper = new DatabaseHelper( this );
        List<EmployeeModel> employeeModels = databaseHelper.getAllEmployee();

        if(employeeModels.size() > 0) {
            EmployeeAdapter employeeAdapter = new EmployeeAdapter( employeeModels,EmployeeListActivity.this );
            recyclerView.setAdapter( employeeAdapter );
        } else {
            Toast.makeText( this, "Data Empty", Toast.LENGTH_SHORT ).show();
        }
    }
}