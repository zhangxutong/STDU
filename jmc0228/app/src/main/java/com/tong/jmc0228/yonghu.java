package com.tong.jmc0228;

public class yonghu {
    private String zhanghao;
    private String mima;
    private String xuehao;
    private String shouji;
    private String banji;

    public String getZhanghao() {
        return zhanghao;
    }

    public void setZhanghao(String zhanghao) {
        this.zhanghao = zhanghao;
    }

    public String getMima() {
        return mima;
    }

    public void setMima(String mima) {
        this.mima = mima;
    }

    public String getXuehao() {
        return xuehao;
    }

    public void setXuehao(String xuehao) {
        this.xuehao = xuehao;
    }

    public String getShouji() {
        return shouji;
    }

    public yonghu(String zhanghao, String mima, String xuehao, String shouji, String banji) {
        this.zhanghao = zhanghao;
        this.mima = mima;
        this.xuehao = xuehao;
        this.shouji = shouji;
        this.banji = banji;
    }

    public void setShouji(String shouji) {
        this.shouji = shouji;
    }

    public String getBanji() {
        return banji;
    }

    public void setBanji(String banji) {
        this.banji = banji;
    }
}
