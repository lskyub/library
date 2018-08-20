/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package sbproject.library.bus;

import sbproject.library.listener.OnBusListener;

/**
 * The interface Bus.
 */
public interface Bus {
    /**
     * Subscribe.실행
     *
     * @param tag      the tag
     * @param listener the listener
     */
    void subscribe(String tag, OnBusListener listener);

    /**
     * Subscribe.실행
     *
     * @param listener the listener
     */
    void subscribe(OnBusListener listener);

    /**
     * Post.전달
     * Subject이 없을 경우 생성하여 다시 전달
     *
     * @param tag the tag
     * @param o   the o
     */
    void send(String tag, Object o);

    /**
     * Post.전달
     * Subject이 없을 경우 생성하여 다시 전달
     *
     * @param o the o
     */
    void send(Object o);
}
