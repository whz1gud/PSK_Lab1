package com.example.app.psk_lab1.dao.mybatis;

import com.example.app.psk_lab1.entity.Speaker;

import java.util.List;

public interface SpeakerMapper {

    List<Speaker> selectAll();

    Speaker selectById(Long id);

    void insertSpeaker(Speaker speaker);

    void updateSpeaker(Speaker speaker);

    void deleteSpeaker(Long id);
}