/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package sbproject.library.http;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The type Net retrofit.
 */
public class Api {
    private static Api instance = null;
    private static String mBaseUrl;
    private final Retrofit retrofit;

    /**
     * Create instance.
     *
     * @param baseUrl the base url
     */
    public static synchronized Api createInstance(String baseUrl) {
        if (instance == null || (mBaseUrl != null && !mBaseUrl.equals(baseUrl))) {
            instance = new Api(baseUrl);
            mBaseUrl = baseUrl;
        }
        return instance;
    }

    /**
     * Instantiates a new Net retrofit.
     *
     * @param baseUrl the base url
     */
    private Api(String baseUrl) {
        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()); // 파싱등록
        retrofitBuilder.client(createOkHttpClient());
        retrofit = retrofitBuilder.build();
    }

    /**
     * 통신 로그 확인을 위한 OkHttpClient 생성 및 설정
     *
     * @return Log Level Setting OkHttpClient
     */
    private OkHttpClient createOkHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    public <T> T setApi(Class<T> t) {
        return retrofit.create(t);
    }
}
