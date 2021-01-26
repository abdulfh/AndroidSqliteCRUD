package me.hika.sqlitecrud.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import me.hika.sqlitecrud.model.EmployeeModel;

public class DatabaseHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "employee_database";
    //Database Table name
    private static final String TABLE_NAME = "employee";
    //Table columns
    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    //creating table query
    private static final String CREATE_TABLE = "create table " + TABLE_NAME +"("+ID+
            " INTEGER PRIMARY KEY AUTOINCREMENT," + NAME + " TEXT NOT NULL,"+EMAIL+" TEXT NOT NULL);";

    public DatabaseHelper( @Nullable Context context) {
        super( context, DATABASE_NAME,  null, DATABASE_VERSION );
    }

    @Override
    public void onCreate( SQLiteDatabase db ) {
        db.execSQL( CREATE_TABLE );
    }

    @Override
    public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
        db.execSQL( " DROP TABLE IF EXISTS " + TABLE_NAME );
        onCreate( db );
    }

    // Add Employee
    public void addEmployee( EmployeeModel employeeModel ) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( DatabaseHelper.NAME, employeeModel.getName() );
        contentValues.put( DatabaseHelper.EMAIL, employeeModel.getEmail() );

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.insert( DatabaseHelper.TABLE_NAME,null,contentValues );

    }
    // List View Employee
    public List<EmployeeModel> getAllEmployee() {
        String sql = "SELECT * FROM " + TABLE_NAME;
        sqLiteDatabase = this.getReadableDatabase();

        List<EmployeeModel> storeEmployee = new ArrayList<>();
        Cursor cursor = sqLiteDatabase.rawQuery( sql, null );

        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt( cursor.getString( 0 ) );
                String name = cursor.getString( 1 );
                String email = cursor.getString( 2 );

                EmployeeModel employeeModel = new EmployeeModel();
                employeeModel.setId( id );
                employeeModel.setName( name );
                employeeModel.setEmail( email );

                storeEmployee.add( employeeModel );
            }while (cursor.moveToNext());
        }
        cursor.close();
        Log.d( "TAG", "getAllEmployee: " + storeEmployee );
        return storeEmployee;
    }

    public void updateEmployee(EmployeeModel employeeModel) {
        ContentValues contentValues = new ContentValues();
        contentValues.put( DatabaseHelper.NAME, employeeModel.getName() );
        contentValues.put( DatabaseHelper.EMAIL, employeeModel.getEmail() );

        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.update( TABLE_NAME, contentValues, ID + " = ?", new String[]{String.valueOf( employeeModel.getId() )} );
    }

    public void deleteEmployee(int id) {
        sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.delete( TABLE_NAME, ID + " = ? ", new String[] {String.valueOf( id )});
    }
}
