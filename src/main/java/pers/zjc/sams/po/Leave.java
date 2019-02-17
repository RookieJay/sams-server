package pers.zjc.sams.po;

import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.TimeUtils;

import java.util.Date;

public class Leave {
    private Integer id;

    private Integer stuId;

    private String reason;

    private Date beginTime;

    private Date endTime;

    private Integer courseId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason == null ? null : reason.trim();
    }

    public String getBeginTime() {
        return TimeUtils.date2String(beginTime, Const.DateFormat.WITH_HMS);
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return TimeUtils.date2String(endTime, Const.DateFormat.WITH_HMS);
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
}