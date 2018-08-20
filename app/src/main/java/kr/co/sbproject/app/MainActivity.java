package kr.co.sbproject.app;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import kr.co.sbproject.app.api.ApiInterface;
import kr.co.sbproject.app.model.Version;
import sbproject.library.bus.Bus;
import sbproject.library.http.Api;
import sbproject.library.listener.OnBusListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends EventReceiveActivity implements OnBusListener {
    @BindView(R.id.btn_01)
    Button button01;
    @BindView(R.id.btn_02)
    Button button02;
    @BindView(R.id.btn_03)
    Button button03;
    /**
     * 서버 통신
     */
    public ApiInterface api;
    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bus = getBus("test", this);

        ButterKnife.bind(this);
        //서버 통신
        api = Api.createInstance("http://192.168.6.142:8083/").setApi(ApiInterface.class);

        button01.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SubActivity.class);
            startActivity(intent);
        });

        button02.setOnClickListener(v -> bus.send("테스트2"));

        button03.setOnClickListener(v -> getEvent("test1"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        getEvent();
    }

    private void setVersionCheck() {
        String serviceSeq = "1";
        String marketCd = "141002";
        String appVersion = "";
        try {
            PackageInfo pakageInfo = getApplicationContext().getPackageManager().getPackageInfo(getApplicationContext().getPackageName(), 0);
            appVersion = pakageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            finish();
        }
        api.version(new Version.RQ(serviceSeq, marketCd, appVersion)).enqueue(new Callback<Version.RS>() {
            @Override
            public void onResponse(Call<Version.RS> call, Response<Version.RS> response) {
                try {
                    if (200 == response.code()) {
                        if (response.body().data != null) {
                            String storeUrl = response.body().data.appUpdateInfo.storeUrl;
                            String popupYn = response.body().data.appUpdateInfo.popupYn;
                            String autoUpdateYN = response.body().data.appUpdateInfo.autoUpdateYN;
                            if ("Y".equals(popupYn)) {
                                //다이얼로그 이벤트
                                //확인
                                if ("Y".equals(autoUpdateYN)) {
                                    //마켓이동 storeUrl
                                } else {
                                    //메인이동
                                }
                                //취소
                                if ("Y".equals(autoUpdateYN)) {
                                    //종료
                                } else {
                                    //메인이동
                                }
                            } else {
                                if ("Y".equals(autoUpdateYN)) {
                                    //마켓이동 storeUrl
                                } else {
                                    //메인이동
                                }
                            }
                        } else {
                            Toast.makeText(MainActivity.this, response.body().code + " : " + response.body().msg, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        //메인이동
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Version.RS> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onEvent(String tag, Object o) {
        Log.i("sgim", "onEvent[ " + tag + " ] " + o.toString());
//            if (o.toString().equals("테스트3")) {
//                setVersionCheck();
//            }
        if (o.toString().equals("테스트2")) {
            return false;
        }
        return true;
    }

    @Override
    public void onError(String tag, Exception e) {
        Log.i("sgim", "onError[ " + tag + " ] " + e.getMessage());
    }
}
