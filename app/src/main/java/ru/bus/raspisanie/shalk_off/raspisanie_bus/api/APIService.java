package ru.bus.raspisanie.shalk_off.raspisanie_bus.api;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.request.RequestNewUser;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseSchedule;
import ru.bus.raspisanie.shalk_off.raspisanie_bus.model.response.ResponseVersion;
import rx.Observable;

/**
 * Created by pkorl on 25.03.2017.
 */

public interface APIService {

    @FormUrlEncoded
    @POST("android/getRaspisanie.php")
    Observable<ResponseSchedule> getSchedule(@Field("info") String param);

    @FormUrlEncoded
    @POST("android/getRaspisanie.php")
    Observable<ResponseVersion> getVersion(@Field("info") String param);

    @FormUrlEncoded
    @POST("android/testToken.php")
    Observable<RequestNewUser> setNewUser(@Field("id") String param);

    @FormUrlEncoded
    @POST("android/testUser.php")
    Observable<String> setUser(@Field("nameDivece") String nameDivece,
                         @Field("tokenKlient") String tokenKlient,
                         @Field("androidId") String androidId,
                         @Field("md5TokenKlient") String md5TokenKlient);
}
