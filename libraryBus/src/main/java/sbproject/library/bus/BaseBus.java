/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package sbproject.library.bus;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.BehaviorSubject;
import sbproject.library.listener.OnBusListener;

/**
 * The type Bus.
 * Rx를 이용한 이벤트 버스
 */
public class BaseBus implements Bus {
    private static BaseBus mInstance;
    private HashMap<String, Data> maps = new HashMap<>();
    private HashMap<String, Disposable> disposables = new HashMap<>();
    private String mainKey = "";

    private class Data {
        public BehaviorSubject subjects;
        boolean isComplete = false;
    }

    /**
     * Gets instance.생성자
     *
     * @return the instance
     */
    public static BaseBus getInstance() {
        if (mInstance == null) {
            synchronized (BaseBus.class) {
                if (mInstance == null) {
                    mInstance = new BaseBus();
                }
            }
        }
        return mInstance;
    }

    /**
     * Register.등록
     *
     * @param tag the tag
     * @return the boolean
     */
    public void register(String tag) {
        if (mainKey.equals("")) {
            mainKey = tag;
            isRegister(tag);
        }
    }

    /**
     * Register.등록 여부
     *
     * @param tag the tag
     */
    private boolean isRegister(String tag) {
        Data data = maps.get(tag);
        if (data == null) {
            data = new Data();
            data.subjects = BehaviorSubject.create();
            maps.put(tag, data);
            return true;
        }
        return false;
    }

    /**
     * Deregister.해제
     */
    public void deregister() {
        for (Map.Entry<String, Disposable> disposableEntry : disposables.entrySet()) {
            disposableEntry.getValue().dispose();
        }
        disposables.clear();
    }

    /**
     * Subscribe.실행
     *
     * @param tag      the tag
     * @param listener the listener
     */
    @Override
    public void subscribe(final String tag, final OnBusListener listener) {
        final Data data = maps.get(tag);
        if (data != null) {
            synchronized (data.subjects) {
                Disposable disposable = disposables.get(tag);
                if (disposable == null) {
                    disposable = data.subjects.subscribe(new Consumer() {
                        @Override
                        public void accept(Object o) {
                            try {
                                if (!data.isComplete) {
                                    if (listener.onEvent(tag, o)) {
                                        data.isComplete = true;
                                    }
                                }
                            } catch (Exception e) {
                                listener.onError(tag, e);
                            }
                        }
                    });
                    disposables.put(tag, disposable);
                }
            }
        } else {
            if (isRegister(tag)) {
                subscribe(tag, listener);
            }
        }
    }

    /**
     * Subscribe.실행
     *
     * @param listener the consumer
     */
    @Override
    public void subscribe(OnBusListener listener) {
        subscribe(mainKey, listener);
    }

    /**
     * Post.전달
     * Subject이 없을 경우 생성하여 다시 전달
     *
     * @param tag the tag
     * @param o   the o
     */
    @Override
    public void send(String tag, Object o) {
        Data data = maps.get(tag);
        if (data != null) {
            data.isComplete = false;
            data.subjects.onNext(o);
        } else {
            if (isRegister(tag)) {
                send(tag, o);
            }
        }
    }

    /**
     * Post.전달
     * Subject이 없을 경우 생성하여 다시 전달
     *
     * @param o the o
     */
    @Override
    public void send(Object o) {
        send(mainKey, o);
    }
}
