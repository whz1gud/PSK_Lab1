package com.example.app.psk_lab1.dao.jpa;

import com.example.app.psk_lab1.entity.Conference;

import java.util.List;

public interface ConferenceDao {

    List<Conference> findAll();
    Conference findById(Long id);
    void create(Conference conference);
    Conference update(Conference conference);
    void delete(Long id);

}
