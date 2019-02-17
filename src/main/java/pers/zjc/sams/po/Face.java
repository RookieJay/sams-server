package pers.zjc.sams.po;

import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.TimeUtils;

import java.util.Date;

public class Face {
    private Integer id;

    private String fileName;

    private String path;

    private Integer stuId;

    private Date uploadTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getUploadTime() {
        return TimeUtils.date2String(uploadTime, Const.DateFormat.WITH_HMS);
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUpdateTime() {
        return TimeUtils.date2String(updateTime, Const.DateFormat.WITH_HMS);
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}