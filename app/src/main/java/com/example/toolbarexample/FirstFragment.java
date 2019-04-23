package com.example.toolbarexample;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {

    ListView listView;
    ArrayList<Employees> list;
    CustomAdapter adapter;
    Context context;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        listView = view.findViewById(R.id.listView);
        list = new ArrayList<>();
        list.add(new Employees("Milind", "Male"));
        list.add(new Employees("Tushar", "Male"));
        list.add(new Employees("Ajay", "Male"));
        list.add(new Employees("Hardik", "Male"));
        list.add(new Employees("Rahul", "Male"));
        list.add(new Employees("Nikita", "Female"));
        list.add(new Employees("Shruti", "Female"));
        list.add(new Employees("Godwin", "Male"));
        list.add(new Employees("Milind1", "Male"));
        list.add(new Employees("Tushar1", "Male"));
        list.add(new Employees("Ajay1", "Male"));
        list.add(new Employees("Hardik1", "Male"));
        list.add(new Employees("Rahul1", "Male"));
        list.add(new Employees("Nikita1", "Female"));
        list.add(new Employees("Shruti1", "Female"));
        list.add(new Employees("Godwin1", "Male"));

        adapter = new CustomAdapter(list, context);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employees employees = list.get(position);
                Toast.makeText(context, "Name: " + employees.getName() + " Gender: " + employees.getGender(), Toast.LENGTH_SHORT).show();
            }
        });
        listView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        this.context = context;
        super.onAttach(context);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        inflater.inflate(R.menu.main_menu, menu);

        MenuItem mSearch = menu.findItem(R.id.search);

        SearchView mSearchView = (SearchView) mSearch.getActionView();

        EditText searchEditText = mSearchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        searchEditText.setHintTextColor(getResources().getColor(R.color.colorPrimaryDark));

        mSearchView.setQueryHint("Enter fruit name");
        mSearchView.setMaxWidth(Integer.MAX_VALUE);

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                //adapter.getFilter().filter(s);
                adapter.filter(s);
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }


}
