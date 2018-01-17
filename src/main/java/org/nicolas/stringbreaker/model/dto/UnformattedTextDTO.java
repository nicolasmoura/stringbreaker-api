package org.nicolas.stringbreaker.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnformattedTextDTO {
    private String text;
    private Integer lineLength;
}
