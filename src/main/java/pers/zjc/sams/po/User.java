package pers.zjc.sams.po;

import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.TimeUtils;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
    private Integer id;

    private String account;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Integer role;

    private String deviceId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getCreateTime() {
        return TimeUtils.date2String(createTime, Const.DateFormat.WITH_HMS);
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateTime() {
        return TimeUtils.date2String(updateTime, Const.DateFormat.WITH_HMS);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", role=" + role +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}