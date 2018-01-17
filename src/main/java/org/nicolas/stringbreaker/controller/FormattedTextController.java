package org.nicolas.stringbreaker.controller;

import org.nicolas.stringbreaker.model.dto.FormattedTextDTO;
import org.nicolas.stringbreaker.model.dto.UnformattedTextDTO;
import org.nicolas.stringbreaker.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController("/formatted-text")
public class FormattedTextController {

    @Autowired
    private TextService textService;

    @PostMapping
    public ResponseEntity<FormattedTextDTO> breakLinesAndJustify(@RequestBody UnformattedTextDTO unformattedTextDTO) {
        String formattedText;
        try {
            textService.validateUnformattedTextRequest(unformattedTextDTO);
            formattedText = textService.format(unformattedTextDTO.getText(), unformattedTextDTO.getLineLength());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.unprocessableEntity().body(new FormattedTextDTO(e.getMessage(), null));
        }
        return ResponseEntity.ok().body(new FormattedTextDTO("Sucesso", formattedText));
    }
}
