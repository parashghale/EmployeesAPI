package com.parash.employeesapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import Api.EmployeeApi;
import Url.Url;
import model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchEmpActivity extends AppCompatActivity {
private EditText etEmpNo;
private TextView tvData;
private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_emp);

        etEmpNo= findViewById(R.id.etEmpNo);
        tvData= findViewById(R.id.tvData);
        btnSearch=findViewById(R.id.btnSearch);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadData();

            }
        });
    }

    private void loadData(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Url.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EmployeeApi employeeApi = retrofit.create(EmployeeApi.class);

        Call<Employee> listcall= employeeApi.getEmpById(Integer.parseInt(etEmpNo.getText().toString()));

        listcall.enqueue(new Callback<Employee>() {
            @Override
            public void onResponse(Call<Employee> call, Response<Employee> response) {
                Toast.makeText(SearchEmpActivity.this, "Good"+response.body().toString(), Toast.LENGTH_SHORT).show();
                String data="";
                data +="Id "+response.body().getId()+"\n";
                data +="name "+response.body().getEmployee_name()+"\n";
                data +="salary "+response.body().getEmployee_salary()+"\n";
                data +="age "+response.body().getEmployee_age()+"\n";

                tvData.setText(data);
            }

            @Override
            public void onFailure(Call<Employee> call, Throwable t) {
                Toast.makeText(SearchEmpActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
