package pers.zjc.sams.po;

import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.TimeUtils;

import java.util.Date;

public class Course {
    private Integer id;

    private String name;

    private String classroom;

    private Date beginTime;

    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom == null ? null : classroom.trim();
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
}