package pers.zjc.sams.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import pers.zjc.sams.po.enums.CourseEnum;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.TimeUtils;

import java.util.Date;

public class AttenceRecord {
    private String attenceId;

    private Integer status;

    private Integer stuId;

    private Integer courseId;

    private String courseName;

    private String operator;

    private Date createTime;

    private Date updateTime;

    public String getAttenceId() {
        return attenceId;
    }

    public void setAttenceId(String attenceId) {
        this.attenceId = attenceId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
//        courseName = CourseEnum.parse(courseId).toString();
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator == null ? null : operator.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}