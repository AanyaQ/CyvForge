package net.cyvforge.util;

import java.util.*;
import java.util.function.Function;

// code from ChatGPT (I can't be fucked to spend 30 hours on this again)
public class MathEvaluator {
    private static MathEvaluator instance;

    private final Map<String, Integer> PRECEDENCE = new HashMap<>();
    private final Map<String, Function<Double, Double>> FUNCTIONS = new HashMap<>();
    private final Set<String> RIGHT_ASSOCIATIVE = new HashSet<>();

    public static MathEvaluator getInstance() {
        if (instance == null) instance = new MathEvaluator();
        return instance;
    }

    private MathEvaluator() {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
        PRECEDENCE.put("^", 3);
        PRECEDENCE.put("u-", 4); // highest precedence

        // Right-associative operators
        RIGHT_ASSOCIATIVE.add("^");
        RIGHT_ASSOCIATIVE.add("u-"); // right-associative

        // Functions
        // Trig
        FUNCTIONS.put("sin", Math::sin);
        FUNCTIONS.put("cos", Math::cos);
        FUNCTIONS.put("tan", Math::tan);
        FUNCTIONS.put("sec", v -> 1 / Math.cos(v));
        FUNCTIONS.put("csc", v -> 1 / Math.sin(v));
        FUNCTIONS.put("cot", v -> 1 / Math.tan(v));
        // Inverse Trig
        FUNCTIONS.put("arcsin", Math::asin);
        FUNCTIONS.put("arccos", Math::acos);
        FUNCTIONS.put("arctan", Math::atan);
        FUNCTIONS.put("arcsec", v -> Math.acos(1/v));
        FUNCTIONS.put("arccsc", v -> Math.asin(1/v));
        FUNCTIONS.put("arccot", v -> Math.atan(1/v));
        FUNCTIONS.put("asin", Math::asin);
        FUNCTIONS.put("acos", Math::acos);
        FUNCTIONS.put("atan", Math::atan);
        FUNCTIONS.put("asec", v -> Math.acos(1/v));
        FUNCTIONS.put("acsc", v -> Math.asin(1/v));
        FUNCTIONS.put("acot", v -> Math.atan(1/v));
        // Misc
        FUNCTIONS.put("sqrt", Math::sqrt);
        FUNCTIONS.put("cbrt", Math::cbrt);
        FUNCTIONS.put("log", Math::log10);
        FUNCTIONS.put("ln", Math::log);
        FUNCTIONS.put("abs", Math::abs);
    }

    public double eval(String expr) {
        List<String> rpn = toRPN(expr);
        return evalRPN(rpn);
    }

    // convert string into tokens
    private List<String> toRPN(String expr) {
        List<String> output = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        StringTokenizer tokenizer = new StringTokenizer(expr, "+-*/^() ", true);

        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumber(token) || isConstant(token)) {
                output.add(token);
            } else if (FUNCTIONS.containsKey(token.toLowerCase())) {
                stack.push(token);
            } else if (isOperator(token)) {
                // Check for unary minus
                if (token.equals("-")) {
                    if (output.isEmpty() || isOperator(lastToken(stack, output)) || lastToken(stack, output).equals("(")) {
                        token = "u-";
                    }
                }
                while (!stack.isEmpty() && isOperator(stack.peek())) {
                    String top = stack.peek();
                    if ((isLeftAssociative(token) && PRECEDENCE.get(token) <= PRECEDENCE.get(top))
                            || (isRightAssociative(token) && PRECEDENCE.get(token) < PRECEDENCE.get(top))) {
                        output.add(stack.pop());
                    } else break;
                }
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    output.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop(); // discard "("
                }
                if (!stack.isEmpty() && FUNCTIONS.containsKey(stack.peek().toLowerCase())) {
                    output.add(stack.pop());
                }
            } else {
                throw new RuntimeException("Unknown token: " + token);
            }
        }

        while (!stack.isEmpty()) output.add(stack.pop());
        return output;
    }

    // evaluate tokens into a result
    private double evalRPN(List<String> tokens) {
        Stack<Double> stack = new Stack<>();
        for (String token : tokens) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isConstant(token)) {
                stack.push(getConstant(token));
            } else if (isOperator(token)) {
                if (token.equals("u-")) {
                    // Unary minus: only pop one operand
                    double a = stack.pop();
                    stack.push(-a);
                } else {
                    // Binary operators: need two operands
                    double b = stack.pop();
                    double a = stack.pop();
                    switch (token) {
                        case "+": stack.push(a + b); break;
                        case "-": stack.push(a - b); break;
                        case "*": stack.push(a * b); break;
                        case "/": stack.push(a / b); break;
                        case "^": stack.push(Math.pow(a, b)); break;
                        default: throw new RuntimeException("Unknown operator: " + token);
                    }
                }
            } else if (FUNCTIONS.containsKey(token.toLowerCase())) {
                double a = stack.pop();
                stack.push(FUNCTIONS.get(token.toLowerCase()).apply(a));
            } else {
                throw new RuntimeException("Unknown element in RPN: " + token);
            }
        }
        return stack.pop();
    }

    private static String lastToken(Stack<String> stack, List<String> output) {
        if (!output.isEmpty()) return output.get(output.size() - 1);
        if (!stack.isEmpty()) return stack.peek();
        return "("; // pretend start of expression is after "("
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s); return true;
        } catch (Exception e) {
            return false;
        }
    }

    private boolean isOperator(String s) {
        return PRECEDENCE.containsKey(s);
    }

    private boolean isLeftAssociative(String op) {
        return !RIGHT_ASSOCIATIVE.contains(op);
    }

    private boolean isRightAssociative(String op) {
        return RIGHT_ASSOCIATIVE.contains(op);
    }

    private boolean isConstant(String s) {
        return s.equalsIgnoreCase("pi") || s.equalsIgnoreCase("e");
    }

    private double getConstant(String s) {
        if (s.equalsIgnoreCase("pi")) return Math.PI;
        if (s.equalsIgnoreCase("e")) return Math.E;
        throw new RuntimeException("Unknown constant: " + s);
    }

}
