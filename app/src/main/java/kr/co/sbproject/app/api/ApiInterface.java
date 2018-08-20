package kr.co.sbproject.app.api;

import kr.co.sbproject.app.model.Version;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Content-Type: application/json; charset=UTF-8", "Accept: application/json"})
    @POST("/mob/app/searchVersion")
    Call<Version.RS> version(@Body Version.RQ rq);
}
