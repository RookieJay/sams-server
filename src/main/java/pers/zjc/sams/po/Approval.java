package pers.zjc.sams.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import pers.zjc.sams.utils.Const;
import pers.zjc.sams.utils.TimeUtils;

import java.util.Date;

/**
 * 审批表
 */
public class Approval {
    private String id;

    private Integer tId;

    private Integer signId;

    private Integer status;
    private Date time;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer gettId() {
        return tId;
    }

    public void settId(Integer tId) {
        this.tId = tId;
    }

    public Integer getsignId() {
        return signId;
    }

    public void setsId(Integer sId) {
        this.signId = sId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Approval{" +
                "id='" + id + '\'' +
                ", tId=" + tId +
                ", signId=" + signId +
                ", status=" + status +
                ", time=" + time +
                '}';
    }
}