package com.example.app.psk_lab1.dao.mybatis;

import com.example.app.psk_lab1.entity.Speaker;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

@ApplicationScoped
public class SpeakerDaoMyBatis {

    @Inject
    private SqlSession sqlSession;

    public List<Speaker> findAll() {
        return sqlSession.getMapper(SpeakerMapper.class).selectAll();
    }

    public Speaker findById(Long id) {
        return sqlSession.getMapper(SpeakerMapper.class).selectById(id);
    }

    public void insert(Speaker s) {
        sqlSession.getMapper(SpeakerMapper.class).insertSpeaker(s);
        // Possibly commit or rely on container transaction
    }

    public void update(Speaker s) {
        sqlSession.getMapper(SpeakerMapper.class).updateSpeaker(s);
    }

    public void delete(Long id) {
        sqlSession.getMapper(SpeakerMapper.class).deleteSpeaker(id);
    }
}