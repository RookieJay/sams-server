package pers.zjc.sams.service;

import pers.zjc.sams.po.Face;

public interface FaceService {

    boolean addFace(Face face);

    boolean isExists(Face face);

    boolean update(Face face);
}
