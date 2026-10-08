package ua.kpi.comsys.collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    private static final Pattern REGEX = Pattern.compile("[A-Za-z0-9]+");

    public Map<String, Integer> countWords(List<String> textLines) {
        checkNull(textLines);
        Map<String, Integer> map = new HashMap<>();
        for (String str : textLines) {
            for (String w : getWords(str)) {
                map.merge(w, 1, Integer::sum);
            }
        }
        return map;
    }

    public Set<String> getUniqueWords(List<String> textLines) {
        checkNull(textLines);
        Set<String> set = new HashSet<>();
        for (String str : textLines) {
            set.addAll(getWords(str));
        }
        return set;
    }

    private static void checkNull(List<String> list) {
        if (list == null) {
            throw new IllegalArgumentException("textLines cannot be null");
        }
    }

    private static List<String> getWords(String text) {
        if (text == null) {
            return List.of();
        }
        Matcher m = REGEX.matcher(text);
        List<String> result = new ArrayList<>();
        while (m.find()) {
            result.add(m.group().toLowerCase(Locale.ROOT));
        }
        return result;
    }
}
