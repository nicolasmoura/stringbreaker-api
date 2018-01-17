package org.nicolas.stringbreaker.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Line {
    private int length;
    private List<String> words;
    private int numberOfSpaces;

    public Line() {
        this.length = 0;
        this.words = new ArrayList<>();
    }

    public void addLength(int wordLength) {
        this.length += wordLength;
    }

    public void incrementSpaceCount() {
        this.numberOfSpaces++;
    }

    public String getLineAsString() {
        StringBuilder lineBuilder = new StringBuilder(this.length);
        for (String word : this.words) {
            lineBuilder.append(word);
        }
        return lineBuilder.toString();
    }
}
