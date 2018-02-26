package programming.set9.parser;
import programming.set9.parser.SimpleTree;

import java.util.*;

/**
 *
 */
public class PNParser {

    private final SimpleTree st;

    private static List<String> operands = new ArrayList<>();
    static {
        operands.add("+");
        operands.add("*");
    }

    /**
     * The constructor expecting the expression in polish notation.
     */
    public PNParser(String pNExpression) {
        StringTokenizer tokenizer = new StringTokenizer(pNExpression, " ");
        st = parse(tokenizer);
        if (tokenizer.hasMoreTokens()) {
            throw new IllegalArgumentException();
        }
    }


    /**
     * @param tokenizer pointer to the tokenizer instance of the string we are parsing
     * @return simpletree representation of the next next part of the string pref-notated string in the tokenizer
     */
    private static SimpleTree parse(StringTokenizer tokenizer) {
        if(!tokenizer.hasMoreTokens()){
            throw new IllegalArgumentException();
        }
        String content = tokenizer.nextToken();
        if(operands.contains(content)) {
            return new SimpleTree(content, parse(tokenizer), parse(tokenizer));
        } else {
            return new SimpleTree(content);
        }
    }

    /**
     * @return the tree in infix notation
     */
    public String toString() {
        return treeToString(st, true).toString();
    }


    /**
     * @param tree tree part to parse
     * @param inAddition whether we (recursively) are in an addition already
     * @return infixstring representation of the tree part
     */
    private static StringBuilder treeToString(SimpleTree tree, boolean inAddition) {
        StringBuilder stringBuilder = new StringBuilder();

        boolean addNext = tree.getContent().equals("+");
        // only add parentheses if we are not already in an addition
        boolean outerAddition = !inAddition && addNext;

        if (outerAddition) {
            stringBuilder.append("(");
        }

        // Instead of creating a string in the order operand left right
        // we'll create a string in the format left operand right
        if (tree.getLeft() != null) {
            stringBuilder.append(treeToString(tree.getLeft(), addNext));
            stringBuilder.append(" ");
        }

        stringBuilder.append(tree.getContent());

        if (tree.getRight() != null) {
            stringBuilder.append(" ");
            stringBuilder.append(treeToString(tree.getRight(), addNext));
        }

        if (outerAddition) {
            stringBuilder.append(")");
        }
        return stringBuilder;
    }

}
