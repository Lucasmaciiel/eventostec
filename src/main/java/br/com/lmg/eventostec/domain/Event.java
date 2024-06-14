package br.com.lmg.eventostec.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Table(name = "event")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Event implements Serializable {

    @Serial
    private static final long serialVersionUID = 7763559720200912432L;

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "Description cannot be null")
    private String description;

    private String imageUrl;

    private Boolean remote;

    private Date date;
}
