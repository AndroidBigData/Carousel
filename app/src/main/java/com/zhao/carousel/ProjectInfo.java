package com.zhao.carousel;

/**
 * Created by Administrator on 2016/7/13.
 */
public class ProjectInfo {
    private String url;
    private String imgUrl;
    public ProjectInfo(String url, String imgUrl) {
        super();
        this.url = url;
        this.imgUrl = imgUrl;
    }
    public String getImgUrl() {
        return imgUrl;
    }
}

