package com.dawn.lib_common.bean;

public class CheckBean {
    public String title;
    public String url;

    public CheckBean(String title, boolean check,String url) {
        this.title = title;
        this.check = check;
        this.url=url;
    }

    public boolean check;
}
