package com.fh.entity.vo;

/**
 * 房屋信息类
 * Created by cuizongqiang on 2017/3/10.
 */
public class HouseInfo {

    private Long id; //主键,自增
    private String fwmc;  //房屋名称
    private String syzxm;  //所有人姓名
    private String syzsfzh;  //所有人身份证号
    private String fczh;  //房产证号
    private String xxdz;  //详细地址
    private String sf;  //省份
    private String shi;  //市
    private String jdmc;  //街道名称
    private String xqmc;  //小区名称
    private String lh;  //楼号
    private String mph;  //门牌号
    private String dyh;  //单元号
    private String cs;  //层数
    private String shih;  //室号
    private Integer ktsl;  //客厅数量
    private Integer wssl;  //卧室数量
    private String zbx;  //坐标x
    private String zby;  //坐标y
    private String jfrq;  //交房日期
    private Integer zt; //状态: 1:正常使用 2:闲置


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFwmc() {
        return fwmc;
    }

    public void setFwmc(String fwmc) {
        this.fwmc = fwmc;
    }

    public String getSyzxm() {
        return syzxm;
    }

    public void setSyzxm(String syzxm) {
        this.syzxm = syzxm;
    }

    public String getSyzsfzh() {
        return syzsfzh;
    }

    public void setSyzsfzh(String syzsfzh) {
        this.syzsfzh = syzsfzh;
    }

    public String getFczh() {
        return fczh;
    }

    public void setFczh(String fczh) {
        this.fczh = fczh;
    }

    public String getXxdz() {
        return xxdz;
    }

    public void setXxdz(String xxdz) {
        this.xxdz = xxdz;
    }

    public String getSf() {
        return sf;
    }

    public void setSf(String sf) {
        this.sf = sf;
    }

    public String getShi() {
        return shi;
    }

    public void setShi(String shi) {
        this.shi = shi;
    }

    public String getJdmc() {
        return jdmc;
    }

    public void setJdmc(String jdmc) {
        this.jdmc = jdmc;
    }

    public String getXqmc() {
        return xqmc;
    }

    public void setXqmc(String xqmc) {
        this.xqmc = xqmc;
    }

    public String getLh() {
        return lh;
    }

    public void setLh(String lh) {
        this.lh = lh;
    }

    public String getMph() {
        return mph;
    }

    public void setMph(String mph) {
        this.mph = mph;
    }

    public String getDyh() {
        return dyh;
    }

    public void setDyh(String dyh) {
        this.dyh = dyh;
    }

    public String getCs() {
        return cs;
    }

    public void setCs(String cs) {
        this.cs = cs;
    }

    public String getShih() {
        return shih;
    }

    public void setShih(String shih) {
        this.shih = shih;
    }

    public Integer getKtsl() {
        return ktsl;
    }

    public void setKtsl(Integer ktsl) {
        this.ktsl = ktsl;
    }

    public Integer getWssl() {
        return wssl;
    }

    public void setWssl(Integer wssl) {
        this.wssl = wssl;
    }

    public String getZbx() {
        return zbx;
    }

    public void setZbx(String zbx) {
        this.zbx = zbx;
    }

    public String getZby() {
        return zby;
    }

    public void setZby(String zby) {
        this.zby = zby;
    }

    public String getJfrq() {
        return jfrq;
    }

    public void setJfrq(String jfrq) {
        this.jfrq = jfrq;
    }

    public Integer getZt() {
        return zt;
    }

    public void setZt(Integer zt) {
        this.zt = zt;
    }
}
