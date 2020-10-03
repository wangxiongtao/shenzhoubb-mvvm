package com.dawn.lib_common.bean;

import android.text.TextUtils;

import java.util.List;

/**
 * Created by Dawn on 2018/10/8.
 */

public class HomeInfoBean {
    public String adCategoryCode;//": 1,
    public List<HomeItemBean> adInfoList;//热门

    public String getAdCategoryCode() {
        if(TextUtils.isEmpty(adCategoryCode)){
            return "";
        }
        return adCategoryCode;
    }

    /**
     * 轮播图和社区
     */
    public static class HomeItemBean {
        public String avatar;//":
        public String title;//": "说说IMB小型机的Ipar划分和调整",
        public String link;//": "www.shenzhoubb.com",
        public String serviceCategory;//": "热门"
        public String img;//": "https://static.shenzhoubb.com/smsb.png",//轮播图地址
        public String desc;//":
        public String location;//":
        public String adCode;//":
        public String serviceDate;//": "9.11",
        public String price;//": 500,
        public String style;//": 文字的颜色,
        public String isOpen;//1：打开 0：关闭 //保险使用
        public boolean isOpen(){
            return "1".equals(isOpen);
        }
    }


}
