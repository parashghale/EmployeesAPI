package Url;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Url {

    public final static String base_url ="http://dummy.restapiexample.com/api/v1/";

    public static Retrofit createInstance()
    {
        Retrofit retrofit= new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(base_url)
                .build();

        return retrofit;
    }
}
