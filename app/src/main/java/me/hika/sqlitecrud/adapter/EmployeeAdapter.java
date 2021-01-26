package me.hika.sqlitecrud.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import lombok.NonNull;
import me.hika.sqlitecrud.R;
import me.hika.sqlitecrud.helper.DatabaseHelper;
import me.hika.sqlitecrud.model.EmployeeModel;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    List<EmployeeModel> employeeModelList;
    Context context;
    DatabaseHelper databaseHelper;

    public EmployeeAdapter( List<EmployeeModel> employeeModelList, Context context ) {
        this.employeeModelList = employeeModelList;
        this.context = context;
        databaseHelper = new DatabaseHelper( context );
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder( @NonNull ViewGroup parent, int viewType ) {
        LayoutInflater layoutInflater = LayoutInflater.from( parent.getContext() );
        View view = layoutInflater.inflate( R.layout.employee_item_list, parent, false );
        ViewHolder viewHolder = new ViewHolder( view );
        return viewHolder;
    }

    @Override
    public void onBindViewHolder( @NonNull ViewHolder holder, int position ) {
        final EmployeeModel employeeModel = employeeModelList.get( position );
        holder.textViewId.setText( Integer.toString( employeeModel.getId() ) );
        holder.editTextName.setText( employeeModel.getName() );
        holder.editTextEmail.setText( employeeModel.getEmail() );

    }

    @Override
    public int getItemCount() {
        return employeeModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewId;
        EditText editTextName,editTextEmail;
        Button buttonEdit,buttonDelete;

        public ViewHolder( @NonNull View itemView ) {
            super(itemView);
            textViewId = itemView.findViewById( R.id.itemId );
            editTextName = itemView.findViewById( R.id.editTextNameItem );
            editTextEmail = itemView.findViewById( R.id.editTextEmailItem );
            buttonDelete = itemView.findViewById( R.id.buttonDelete );
            buttonEdit = itemView.findViewById( R.id.buttonEdit );
        }
    }
}
