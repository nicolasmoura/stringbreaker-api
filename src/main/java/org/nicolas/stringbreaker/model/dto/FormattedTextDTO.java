package org.nicolas.stringbreaker.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FormattedTextDTO {
    private String message;
    private String text;

    public FormattedTextDTO(String message, String text) {
        this.message = message;
        this.text = text;
    }
}
