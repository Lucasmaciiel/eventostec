package br.com.lmg.eventostec.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Table(name = "event")
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = 7763559720200912432L;

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Title cannot be null")
    private String title;

    @NotBlank(message = "Description cannot be null")
    private String description;

    private String eventUrl;

    @Column(name = "img_url")
    private String imageUrl;

    private Boolean remote;

    private LocalDateTime date;
}
