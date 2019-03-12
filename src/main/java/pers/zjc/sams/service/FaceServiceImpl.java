package pers.zjc.sams.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.zjc.sams.dao.FaceMapper;
import pers.zjc.sams.po.Face;

@Service
@Transactional
public class FaceServiceImpl implements FaceService {

    @Autowired
    private FaceMapper faceMapper;

    @Override
    public boolean addFace(Face face) {
        return faceMapper.insert(face) > 0;
    }

    @Override
    public boolean isExists(Face face) {
        Face faceResult = faceMapper.selectByStuId(face);
        if (faceResult != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean update(Face face) {
        return faceMapper.updateByStuId(face) > 0;
    }
}
