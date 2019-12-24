package Api;

import java.util.List;

import model.Employee;
import model.EmployeeCUD;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EmployeeApi {
    @GET("employees")
    Call<List<Employee>> getAllEmployees();

    @GET("employee/{empId}")
    Call<Employee> getEmpById(@Path("empId") int empId);

    @POST("create")
    Call<Void> registerEmployee(@Body EmployeeCUD emp);


}
