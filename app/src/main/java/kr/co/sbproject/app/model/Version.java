/**
 * (주)오픈잇 | http://www.openit.co.kr
 * Copyright (c)2006-2018, openit Inc.
 * All rights reserved.
 */
package kr.co.sbproject.app.model;

import java.util.List;

/**
 * The type Version.
 */
public class Version {
    /**
     * The Service name.
     */
    public String serviceName = "";

    /**
     * Instantiates a new Version.
     *
     * @param serviceName the service name
     */
    public Version(String serviceName) {
        this.serviceName = serviceName;
    }

    /**
     * The type Rq.
     */
//{"appVersion":"1.0","marketCd":"COM05210","serviceSeq":"46"}
    public static class RQ {
        /**
         * The Service seq.
         */
        public String serviceSeq = "";
        /**
         * The Market cd.
         */
        public String marketCd = "";
        /**
         * The App version.
         */
        public String appVersion = "";

        /**
         * Instantiates a new RQ.
         *
         * @param serviceSeq the service seq
         * @param marketCd   the market cd
         * @param appVersion the app version
         */
        public RQ(String serviceSeq, String marketCd, String appVersion) {
            this.serviceSeq = serviceSeq;
            this.marketCd = marketCd;
            this.appVersion = appVersion;
        }
    }

    /**
     * The type RS.
     */
//{"message":"성공","appUpdateInfo":{"popupYn":"Y","autoUpdateYN":"N","storeUrl":"https://play.google.com/store/apps/details?id=kr.co.openit.healthup4","latestAppVersion":"2.0.3","updateMessage":"비밀번호 찾기 버그 개선 및 안정화","updateCd":"COM05030","updateMessageList":[{"nation":"en","nationNm":"영어","nationPopMsg":"Fix bug for finding password, and stabilizing."},{"nation":"ko","nationNm":"한국어","nationPopMsg":"비밀번호 찾기 버그 개선 및 안정화"}]},"isSuccess":true}
    public static class RS {
        /**
         * The Code.
         */
        public String code;
        /**
         * The Message.
         */
        public String msg;
        /**
         * The Data.
         */
        public Data data;

        /**
         * The type Data.
         */
        public class Data {
            /**
             * The App update info.
             */
            public Info appUpdateInfo;

            /**
             * The type Info.
             */
            public class Info {
                /**
                 * The Popup yn.
                 */
                public String popupYn;
                /**
                 * The Auto update yn.
                 */
                public String autoUpdateYN;
                /**
                 * The Store url.
                 */
                public String storeUrl;
                /**
                 * The Latest app version.
                 */
                public String latestAppVersion;
                /**
                 * The Update message list.
                 */
                public List<UpdateMessageList> updateMessageList;

                /**
                 * The type Update message list.
                 */
                public class UpdateMessageList {
                    /**
                     * The Nation.
                     */
                    public String nation;
                    /**
                     * The Nation nm.
                     */
                    public String nationNm;
                    /**
                     * The Nation pop msg.
                     */
                    public String nationPopMsg;
                }
            }
        }
    }
}
