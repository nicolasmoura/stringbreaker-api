package org.nicolas.stringbreaker.service;

import org.nicolas.stringbreaker.model.Line;
import org.nicolas.stringbreaker.model.dto.UnformattedTextDTO;
import org.nicolas.stringbreaker.util.WordUtil;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TextService {

    public void validateUnformattedTextRequest(UnformattedTextDTO unformattedTextDTO) {
        if (unformattedTextDTO == null) throw new IllegalArgumentException("Body não pode ser nulo.");
        if (unformattedTextDTO.getText() == null || unformattedTextDTO.getText().isEmpty()) {
            throw new IllegalArgumentException("O texto não pode estar vazio.");
        }
        if (unformattedTextDTO.getLineLength() == null || unformattedTextDTO.getLineLength().equals(0)) {
            throw new IllegalArgumentException("O tamanho da linha tem que ser maior do que zero.");
        }
    }

    public String format(String text, int lineLength) throws IllegalArgumentException {
        List<String> words = Arrays.asList(text.split("\\s"));
        Line line = new Line();
        StringBuilder output = new StringBuilder();
        for (String word : words) {
            if (word.length() > lineLength) {
                throw new IllegalArgumentException("Seu texto contém palavras maiores do que o tamanho máximo da linha.");
            }
            if (representsDoubleLinefeedCharacter(word)) {
                if (line.getLength() > 0) {
                    word = "\n\n";
                    line = justify(line, lineLength);
                } else {
                    word = "\n";
                }
                output.append(line.getLineAsString().trim()).append(word);
                line = new Line();
            } else if (line.getLength() + word.length() <= lineLength) {
                line.getWords().add(word);
                line.addLength(word.length() + 1);
                line.incrementSpaceCount();
            } else {
                line = justify(line, lineLength);
                output.append(line.getLineAsString().trim()).append("\n");
                line = new Line();
                line.getWords().add(word);
                line.addLength(word.length() + 1);
                line.setNumberOfSpaces(1);
            }
        }
        line = justify(line, lineLength);
        output.append(line.getLineAsString().trim());
        return output.toString().trim();
    }

    private Line justify(Line line, int maxLineLength) {
        Line justifiedLine = new Line();
        justifiedLine.setLength(line.getLength());
        justifiedLine.setWords(line.getWords());
        justifiedLine.setNumberOfSpaces(line.getNumberOfSpaces());

        int numberOfSpacesToAdd = maxLineLength - justifiedLine.getLength() + justifiedLine.getNumberOfSpaces();
        int wordIndex = 0;
        while (numberOfSpacesToAdd > 0) {
            justifiedLine.getWords().set(wordIndex, WordUtil.getWordWithAddedSpace(justifiedLine, wordIndex));
            wordIndex = getNextWordIndex(justifiedLine, wordIndex);
            numberOfSpacesToAdd--;
        }

        return justifiedLine;
    }

    private int getNextWordIndex(Line justifiedLine, int wordIndex) {
        if (justifiedLine.getWords().size() == 1) {
            return 0;
        }
        return wordIndex == justifiedLine.getWords().size() - 2 ? 0 : ++wordIndex;
    }

    private boolean representsDoubleLinefeedCharacter(String value) {
        return value.length() == 0;
    }
}
