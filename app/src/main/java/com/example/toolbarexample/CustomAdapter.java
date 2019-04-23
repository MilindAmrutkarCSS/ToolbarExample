package com.example.toolbarexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomAdapter extends ArrayAdapter<Employees> implements View.OnClickListener {
    Context context;
    private ArrayList<Employees> employeesArrayList;
    private List<Employees> employeesList;

    public CustomAdapter(List<Employees> employees, Context context) {
        super(context, R.layout.row_item, employees);
        this.employeesList = employees;
        this.employeesArrayList = new ArrayList<>();
        this.employeesArrayList.addAll(employees);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Employees employee = getItem(position);
        ViewHolder viewHolder;
        final View result;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.row_item, parent, false);
            viewHolder.employeeName = convertView.findViewById(R.id.name);
            viewHolder.employeeGender = convertView.findViewById(R.id.gender);
            result = convertView;
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        if (employee != null) {
            viewHolder.employeeName.setText(employee.getName());
            viewHolder.employeeGender.setText(employee.getGender());
        } else {
            Toast.makeText(context, "Employee is empty", Toast.LENGTH_SHORT).show();
        }
        return result;
    }

    @Override
    public void onClick(View v) {
    }

    public void filter(String charText) {
        String searchText = charText.toLowerCase(Locale.getDefault());
        employeesList.clear();
        if (searchText.length() == 0) {
            employeesList.addAll(employeesArrayList);
        } else {
            for (Employees emp : employeesArrayList) {
                if (emp.getName().toLowerCase(Locale.getDefault()).contains(searchText)) {
                    employeesList.add(emp);
                }
            }
        }
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        TextView employeeName;
        TextView employeeGender;
    }

}
