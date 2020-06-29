package com.example.hanapitest;

import android.app.Application;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.SPCookieStore;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;

import java.net.Proxy;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;

import okhttp3.OkHttpClient;
import okhttp3.internal.tls.OkHostnameVerifier;

public class MyApplication extends Application {

    private static MyApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        MyApplication.sInstance = this;
        initNet();
    }

    public static MyApplication getInstance() {
        return MyApplication.sInstance;
    }

    /**
     * 初始化网络库,进行全局配置
     */
    private void initNet() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);
        loggingInterceptor.setColorLevel(Level.INFO);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)         // 全局的读取超时时间
                .writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS)        // 全局的写入超时时间
                .connectTimeout(10000, TimeUnit.MILLISECONDS)                  // 全局的连接超时时间--10s
                .cookieJar(new CookieJarImpl(new SPCookieStore(this)));

        builder.addInterceptor(loggingInterceptor);

        HostnameVerifier verifier = OkHostnameVerifier.INSTANCE;
        builder.hostnameVerifier((hostname, session) -> {
            return verifier.verify(hostname, session) || verifier.verify("csp.han-networks.com", session);
        });


        OkGo.getInstance().init(this)
                .setOkHttpClient(builder.build())               // 建议设置OkHttpClient，不设置会使用默认的;
                .setCacheMode(CacheMode.NO_CACHE)               // 全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   // 全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0);                              // 全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0

    }

}
