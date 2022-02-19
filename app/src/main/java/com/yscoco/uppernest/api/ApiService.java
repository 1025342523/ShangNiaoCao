package com.yscoco.uppernest.api;

import com.yscoco.uppernest.model.LoginBean;
import com.yscoco.uppernest.model.RegisterResultBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by ZhangZeZhi on 2018\11\19 0019.
 */

public interface ApiService {

    /**
     * 登录
     *
     * @param account
     * @param password
     * @param loginDevice
     * @return
     */
    @POST("api/member/login.v1")
    @FormUrlEncoded
    Observable<BaseResp<LoginBean>> login(@Field("account") String account, @Field("password") String password
            , @Field("loginDevice") String loginDevice);

    /**
     * 注册
     *
     * @param userName
     * @param mobileOrEmail
     * @param password
     * @param nickName
     * @param loginDevice
     * @param smsCode
     * @param referrer
     * @param referrerStatus
     * @return
     */
    @POST("api/member/register.v1")
    @FormUrlEncoded
    Observable<BaseResp<RegisterResultBean>> register(@Field("userName") String userName, @Field("mobileOrEmail") String mobileOrEmail, @Field("password") String password
            , @Field("nickName") String nickName, @Field("loginDevice") String loginDevice, @Field("smsCode") String smsCode
            , @Field("referrer") String referrer, @Field("referrerStatus") String referrerStatus);

    /**
     * 发送验证码
     *
     * @param mobileOrEmail
     * @param type
     * @return
     */
    @POST("api/member/sendSmsCode.v1")
    @FormUrlEncoded
    Observable<BaseResp<String>> sendSmsCode(@Field("mobileOrEmail") String mobileOrEmail, @Field("type") String type);

    @POST("api/member/updatePasswordBySmsCode.v1")
    @FormUrlEncoded
    Observable<BaseResp<String>> updatePassword(@Field("smsCode") String smsCode, @Field("mobileOrEmail") String mobileOrEmail, @Field("password") String password);
    

}



