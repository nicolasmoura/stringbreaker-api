package org.nicolas.stringbreaker.util;

import org.nicolas.stringbreaker.model.Line;

public class WordUtil {
    public static String getWordWithAddedSpace(Line linha, int wordIndex) {
        return linha.getWords().get(wordIndex) + " ";
    }
}
