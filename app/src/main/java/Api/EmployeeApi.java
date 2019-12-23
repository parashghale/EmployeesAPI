package Api;

import java.util.List;

import model.Employee;
import retrofit2.Call;
import retrofit2.http.GET;

public interface EmployeeApi {
    @GET("employees")
    Call<List<Employee>> getAllEmployees();


}
