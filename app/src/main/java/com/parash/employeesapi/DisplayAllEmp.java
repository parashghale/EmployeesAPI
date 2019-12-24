package com.parash.employeesapi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import Api.EmployeeApi;
import Url.Url;
import model.Employee;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DisplayAllEmp extends AppCompatActivity {
private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_all_emp);

        tv= findViewById(R.id.tv);

//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(Url.base_url)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();

        EmployeeApi  employeeApi =Url.createInstance().create(EmployeeApi.class);

        Call<List<Employee>> listCall = employeeApi.getAllEmployees();

        listCall.enqueue(new Callback<List<Employee>>() {
            @Override
            public void onResponse(Call<List<Employee>> call, Response<List<Employee>> response) {
                if (!response.isSuccessful()){
                    tv.setText("Code :"+ response.code());
                    return;
                }

                List<Employee> employeeList = response.body();
                for (Employee employee : employeeList){

                    String data="";

                    data += "name"+employee.getEmployee_name() +"\n";
                    data += "salary"+employee.getEmployee_salary() +"\n";
                    data += "age"+employee.getEmployee_age() +"\n";
                    data += "___________________________" +"\n";

                    tv.append(data);
                }
            }

            @Override
            public void onFailure(Call<List<Employee>> call, Throwable t) {
                tv.setText("Error" +t.getMessage());
            }
        });

    }
}
