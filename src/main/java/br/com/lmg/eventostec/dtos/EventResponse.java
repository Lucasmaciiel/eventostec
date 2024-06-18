package br.com.lmg.eventostec.dtos;

import lombok.Builder;

import java.util.Date;
import java.util.UUID;

@Builder
public record EventResponse(UUID id, String title, String description, Date date, String city, String state, Boolean remote, String eventUrl, String imgUrl) {
}
