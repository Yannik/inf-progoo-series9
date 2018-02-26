package programming.set9.morse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class MorseCoder {

    private static Map<Character, String> morseCode = new HashMap<Character, String>(64);

    static {

        morseCode.put('a', ".-");
        morseCode.put('b', "-...");
        morseCode.put('c', "-.-.");
        morseCode.put('d', "-..");
        morseCode.put('e', ".");
        morseCode.put('f', "..-.");
        morseCode.put('g', "--.");
        morseCode.put('h', "....");
        morseCode.put('i', "..");
        morseCode.put('j', ".---");
        morseCode.put('k', "-.-");
        morseCode.put('l', ".-..");
        morseCode.put('m', "--");
        morseCode.put('n', "-.");
        morseCode.put('o', "---");
        morseCode.put('p', ".--.");
        morseCode.put('q', "--.-");
        morseCode.put('r', ".-.");
        morseCode.put('s', "...");
        morseCode.put('t', "-");
        morseCode.put('u', "..-");
        morseCode.put('v', "...-");
        morseCode.put('w', ".--");
        morseCode.put('x', "-..-");
        morseCode.put('y', "-.--");
        morseCode.put('z', "--..");

        morseCode.put('0', "-----");
        morseCode.put('1', ".----");
        morseCode.put('2', "..---");
        morseCode.put('3', "...--");
        morseCode.put('4', "....-");
        morseCode.put('5', ".....");
        morseCode.put('6', "-....");
        morseCode.put('7', "--...");
        morseCode.put('8', "---..");
        morseCode.put('9', "----.");

        morseCode.put('.', ".-.-.-");
        morseCode.put(',', "--..--");
        morseCode.put('?', "..--..");
        morseCode.put('@', ".--.-.");
    }


    /**
     * Returns a new string which is the morse code version of the input string.
     * Each word is on a separate line. The morse representation of each
     * character of the input string is separated from the next by a space
     * character in the output string.
     *
     * @param input the input string.
     * @return the morse code version of the input string, ignoring all
     * characters in the input string that cannot be represented in
     * international morse code.
     */
    public static String encode(String input) {
        String output = "";
        for (char c : input.toLowerCase().toCharArray()) {
            if (c == ' ') {
                output += "\n";
            }
            if (morseCode.containsKey(c)) {
                output += morseCode.get(c) + " ";
            }
        }
        return output;
    }

    /**
     * Returns a new string which is the natural-language version of the input
     * string, which is assumed to be in morse code format as produced by the
     * encode method.
     *
     * @param input morse code input string.
     * @return natural language version or {@code null} if the input string
     * could not be properly parsed.
     */
    public static String decode(String input) {
        String output = "";
        for (String morseword : input.split("\\n")) {
            for (String morsechar : morseword.split(" +")) {
                Optional<Character> c = morseCode
                        .entrySet()
                        .stream()
                        .filter(entry -> Objects.equals(entry.getValue(), morsechar))
                        .map(Map.Entry::getKey)
                        .findFirst();
                if (c.isPresent()) {
                    output += c.get();
                } else {
                    return null;
                }
            }
            output += " ";
        }
        return output.trim();
    }
}
