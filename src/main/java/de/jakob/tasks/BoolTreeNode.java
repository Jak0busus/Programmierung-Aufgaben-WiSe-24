package de.jakob.tasks;

import de.jakob.util.Utils;

public class BoolTreeNode {

    private String variable = "";
    private BoolTreeNode child1 = null;
    private BoolTreeNode child2 = null;

    private BoolTreeNode(String variableInput) {
        this.variable = variableInput;
    }

    private BoolTreeNode(BoolTreeNode negated) {
        this.child1 = negated;
    }

    private BoolTreeNode(BoolTreeNode conjunct1, BoolTreeNode conjunct2) {
        this.child1 = conjunct1;
        this.child2 = conjunct2;
    }

    public static BoolTreeNode boolTreeTrueNode() {
        return new BoolTreeNode("true");
    }

    public static BoolTreeNode boolTreeFalseNode() {
        return new BoolTreeNode("false");
    }

    public static BoolTreeNode boolTreeVariableNode(String variableInput) {
        if (variableInput.isEmpty() || variableInput.equals("true") || variableInput.equals("false")) {
            Utils.error("Die Variable darf nicht leer sein!");
            return null;
        }
        return new BoolTreeNode(variableInput);
    }

    public static BoolTreeNode boolTreeNotNode(BoolTreeNode negated) {
        return new BoolTreeNode(negated);
    }

    public static BoolTreeNode boolTreeAndNode(BoolTreeNode conjunct1, BoolTreeNode conjunct2) {
        return new BoolTreeNode(conjunct1, conjunct2);
    }

//    public boolean evaluate(String... trueVars) {
//
//    }

    public int depth() {
        if (child2 == null) {
            if (child1 == null) { //no childs
                return 0;
            }
            return child1.depth() + 1; //only one child
        }
        return Math.max(child1.depth(), child2.depth()) + 1; //two childs - find the deepest one    }
    }

    public boolean isLeaf() {
        return child1 == null && child2 == null;
    }

    public boolean isTrueLeaf() {
        return variable.equals("true");
    }

    public boolean isFalseLeaf() {
        return variable.equals("false");
    }

    public boolean isNegation() {
        return child1 != null && child2 == null;
    }

    public boolean isConjunction() {
        return child1 != null && child2 != null;
    }

    public static void main(String[] args) {
        int x = boolTreeAndNode(boolTreeNotNode(boolTreeFalseNode()), boolTreeNotNode(boolTreeTrueNode())).depth();
        System.out.println(x);
    }

}
