package com.example.ai.swuplant.net.netframe;

import com.example.ai.swuplant.entity.PlantModel;
import com.example.ai.swuplant.entity.PointInfo;
import com.example.ai.swuplant.net.bean.CollectionBackResult;
import com.example.ai.swuplant.net.bean.LoginBackResult;
import com.example.ai.swuplant.net.bean.RegisterBackResult;
import com.example.ai.swuplant.net.bean.UpdatePasswordBackResult;
import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {
    @FormUrlEncoded
    @POST("RegisterServlet")
    Observable<RegisterBackResult> registerUser(@Field("username") String username,@Field("password") String password);

    @GET("UpdateUserPasswordServlet")
    Observable<UpdatePasswordBackResult> updateUserPassword(@Query("username") String username,@Query("newPassword") String newPassword);

    @FormUrlEncoded
    @POST("LoginServlet")
    Observable<LoginBackResult> userLogin(@Field("username") String username, @Field("password") String password);

    /**
     * plantName是植物名的字符串拼接
     * @param isCollection
     * @param username
     * @param plantName
     * @return
     */
    @FormUrlEncoded
    @POST("PlantCollectionServlet")
    Observable<CollectionBackResult> collectionPlant(@Field("isCollection") boolean isCollection, @Field("username") String username, @Field("plantName") String plantName);

    @GET("QueryCollectionServlet")
    Observable<CollectionBackResult> getCollectionList(@Query("username") String username);

    @GET("PlantModelServlet")
    Observable<List<PlantModel>> getPlantModelList();

    @GET("PlantPointServlet")
    Observable<List<PointInfo>> getPointInfoList();

    @GET("PlantBambooServlet")
    Observable<List<String>> getBambooList();

    @GET("PlantFernServlet")
    Observable<List<String>> getFernList();

    @GET("PlantGymnospermServlet")
    Observable<List<String>> getGymnospermList();
 }
