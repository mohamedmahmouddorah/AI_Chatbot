package com.ai.chatbot;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class Chatbot {
    private Map<String, String> faq = new HashMap<>();
    private Map<String, Double> constants = new HashMap<>();
    private final String FAQ_FILE = "faq.txt";

    public Chatbot() {
        loadFAQ();
        initConstants();
    }

    private void loadFAQ() {
        try (BufferedReader br = new BufferedReader(new FileReader(FAQ_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    faq.put(parts[0].trim().toLowerCase(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("FAQ file not found. Bot will use default responses.");
        }
    }

    private void initConstants() {
        constants.put("pi", Math.PI);
        constants.put("π", Math.PI);
        constants.put("e", Math.E);
        constants.put("phi", 1.6180339887);
    }

    public String getResponse(String input) {
        if (input == null || input.trim().isEmpty()) return "Please enter a message.";

        String lower = input.toLowerCase().trim();

        // Check FAQ exact match
        if (faq.containsKey(lower)) return faq.get(lower);

        // Check FAQ keywords
        for (String key : faq.keySet()) {
            if (lower.contains(key)) return faq.get(key);
        }

        // Check constants
        for (String key : constants.keySet()) {
            if (lower.contains(key)) return key + " = " + constants.get(key);
        }

        // Check math expressions
        String mathResult = evaluateMath(lower);
        if (mathResult != null) return mathResult;

        return "Sorry, I don't understand that.";
    }

    private String evaluateMath(String input) {
        try {
            // Replace constants with values
            for (String key : constants.keySet()) {
                input = input.replaceAll("(?i)" + Pattern.quote(key), String.valueOf(constants.get(key)));
            }

            // Handle simple operators + - * / ^
            Pattern pattern = Pattern.compile("(-?\\d+(\\.\\d+)?)([+\\-*/^])(-?\\d+(\\.\\d+)?)");
            Matcher matcher = pattern.matcher(input.replaceAll("\\s+", ""));
            if (matcher.matches()) {
                double num1 = Double.parseDouble(matcher.group(1));
                String op = matcher.group(3);
                double num2 = Double.parseDouble(matcher.group(4));
                double result = 0;
                switch (op) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) return "Error: Division by zero!";
                        result = num1 / num2; break;
                    case "^": result = Math.pow(num1, num2); break;
                }
                if (result == (int) result) return "Result: " + (int) result;
                return "Result: " + result;
            }

            // sqrt(x)
            pattern = Pattern.compile("sqrt\\((\\d+(\\.\\d+)?)\\)");
            matcher = pattern.matcher(input);
            if (matcher.matches()) {
                double num = Double.parseDouble(matcher.group(1));
                return "Result: " + Math.sqrt(num);
            }

            // log(x)
            pattern = Pattern.compile("log\\((\\d+(\\.\\d+)?)\\)");
            matcher = pattern.matcher(input);
            if (matcher.matches()) {
                double num = Double.parseDouble(matcher.group(1));
                return "Result: " + Math.log10(num);
            }

        } catch (Exception e) {
            return "Error in calculating the expression.";
        }
        return null;
    }
}
