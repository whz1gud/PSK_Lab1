package com.example.app.psk_lab1.dao.jpa;

import com.example.app.psk_lab1.entity.Presentation;

import java.util.List;

public interface PresentationDao {

    List<Presentation> findAll();
    Presentation findById(Long id);
    void create(Presentation presentation);
    Presentation update (Presentation presentation);
    void delete(Long id);
    Presentation findByConferenceId(Long conferenceId);
}
