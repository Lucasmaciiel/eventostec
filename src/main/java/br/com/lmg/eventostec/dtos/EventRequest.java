package br.com.lmg.eventostec.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Builder
public record EventRequest(
        @NotBlank(message = "Title cannot be empty") String title,
        @NotBlank(message = "Description cannot be empty") String description,
        @NotNull(message = "Date cannot be null") LocalDateTime date,
        String city,
        String uf,
        Boolean remote,
        String eventUrl,
        MultipartFile image) {

}
