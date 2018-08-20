/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package kr.co.sbproject.app;

import android.app.Activity;

import io.reactivex.functions.Consumer;
import sbproject.library.bus.BaseBus;
import sbproject.library.bus.Bus;
import sbproject.library.listener.OnBusListener;

public class EventReceiveActivity extends Activity {
    /**
     * Event Bus
     */
    private BaseBus bus = BaseBus.getInstance();
    /**
     * Event Bus 객체에서 데이터를 저장 또는 받기 위한 키값
     */
    private String busKey;
    /**
     * 이벤트 발생리 데이터 전달 리스너
     */
    private OnBusListener busListener;

    /**
     * 초기값 세팅
     *
     * @param key      이벤트 버스 키값
     * @param listener 이벤트 리스너
     */
    public Bus getBus(String key, OnBusListener listener) {
        try {
            busKey = key;
            busListener = listener;
            /**
             * 이벤트 버스 등록 & 이벤트 리스너 등록
             */
            bus.register(busKey);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bus;
    }

    public void getEvent() {
        bus.subscribe(busListener);
    }

    public void getEvent(String key) {
        bus.subscribe(key, busListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        /**
         * 이벤트 버스 해제
         */
        bus.deregister();
    }
}
