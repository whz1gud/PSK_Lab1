package com.example.app.psk_lab1.entity.dtos.response;

import com.example.app.psk_lab1.entity.Presentation;

public class PresentationResponseDTO {
    public Long id;
    public String title;
    public String description;
    public Long conferenceId;

    // Constructor to map entity to DTO
    public PresentationResponseDTO(Presentation presentation) {
        this.id = presentation.getId();
        this.title = presentation.getTitle();
        this.description = presentation.getDescription();
        this.conferenceId = presentation.getConference().getId();
    }
}