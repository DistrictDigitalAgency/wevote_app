package districtdigital.agency.utilities;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import java.util.List;


public interface wevoAPI {


//mazeltt chnzid fiha el grab user data
    @FormUrlEncoded
    @POST("login")
    Call<userModel> authenticate(@Field("mailAddress") String mailAddress, @Field("password") String password);


    @FormUrlEncoded
    @POST("register")
    Call<userModel> register(
            @Field("fullName") String fullName
            ,@Field("mailAddress") String mailAddress
            ,@Field("password") String password
            ,@Field("age") String age
            ,@Field("address") String address
            ,@Field("city") String city
            ,@Field("phoneNumber") String phoneNumber
            ,@Field("sex") String sex
    );


}
