package com.dnet.myapplication.interfaces;

import com.dnet.myapplication.models.LoginRespone;
import com.dnet.myapplication.models.question.QuestionAnsResult;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiCall {

    @FormUrlEncoded // annotation used in POST type requests
    @POST("api/login")
        // API's endpoints
    Call<LoginRespone> registration(@Field("mobile_no") String user_mobile_no,
                                    @Field("password") String user_password);

    // In registration method @Field used to set the keys and String data type is representing its a string type value and callback is used to get the response from api and it will set it in our POJO class
    @FormUrlEncoded // annotation used in POST type requests
    @POST("api/question-answer")
    // API's endpoints"
    Call<QuestionAnsResult> GetQuestionAnswer(@Header("Authorization") String auth, @Field("last_sync_datetime") String str);
}
