/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package kr.co.sbproject.app;

import android.os.Bundle;

import butterknife.ButterKnife;
import sbproject.library.bus.Bus;

public class SubActivity extends EventSendActivity {
    private Bus bus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);
        ButterKnife.bind(this);
        bus = getBus("test");

        findViewById(R.id.btn_01).setOnClickListener(v -> bus.send("test1", "테스트1"));

        findViewById(R.id.btn_02).setOnClickListener(v -> bus.send("테스트2"));

        findViewById(R.id.btn_03).setOnClickListener(v -> bus.send("테스트3"));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
