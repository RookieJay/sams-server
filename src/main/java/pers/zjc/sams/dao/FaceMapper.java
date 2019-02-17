package pers.zjc.sams.dao;

import pers.zjc.sams.po.Face;

public interface FaceMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Face record);

    int insertSelective(Face record);

    Face selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Face record);

    int updateByPrimaryKey(Face record);
}