package com.kevin.live.bean;

import java.util.List;

/**
 * Created by Kevin on 2018/8/14<br/>
 * Blog:http://student9128.top/<br/>
 * Describe:<br/>
 */
public class CookBookMethodBean {

    private List<MethodBean> method;

    public List<MethodBean> getMethod() {
        return method;
    }

    public void setMethod(List<MethodBean> method) {
        this.method = method;
    }

    public static class MethodBean {
        /**
         * img : http://f2.mob.com/null/2015/08/20/1440066974701.jpg
         * step : 1.木瓜洗净，去皮和籽，切成5大块，姜切片备用，鸭洗净血水，切大块备用。
         */

        private String img;
        private String step;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getStep() {
            return step;
        }

        public void setStep(String step) {
            this.step = step;
        }
    }
}
