package cn.itcast.travel.domain;

import java.util.Date;

public class FavoriteId {

    private Integer rid;
    private Date date;
    private Integer uid;

    public Integer getRid() {
        return rid;
    }

    public void setRid(Integer rid) {
        this.rid = rid;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "FavoriteId{" +
                "rid=" + rid +
                ", date=" + date +
                ", uid=" + uid +
                '}';
    }
}
