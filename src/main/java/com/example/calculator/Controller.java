package com.example.calculator;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.util.List;
import java.util.Arrays;
import java.util.Stack;


public class Controller {

    @FXML
    private JFXButton ThemeSwitch;
    @FXML
    private Label ModeDisplay;
    @FXML
    private Label SecondDisplay;
    @FXML
    private Label Result;
    @FXML
    private Button Clearbtn;
    @FXML
    private Button BackSpacebtn;
    @FXML
    private Button Pi;
    @FXML
    private Button e;
    @FXML
    private Button LParenthesis;
    @FXML
    private Button RParenthesis;
    @FXML
    private Button Sin;
    @FXML
    private Button Cos;
    @FXML
    private Button Tan;
    @FXML
    private Button Ln;
    @FXML
    private Button Log;
    @FXML
    private Button Square;
    @FXML
    private Button XpowerY;
    @FXML
    private Button SquareRoot;
    @FXML
    private Button Modulo;
    @FXML
    private Button DegMod;
    @FXML
    private Button RadMod;
    @FXML
    private Button OctaMod;
    @FXML
    private Button ModChose;
    @FXML
    private Button Hex;
    @FXML
    private Button Decimal;
    @FXML
    private Button Binary;
    @FXML
    private Button ANS;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private Pane graphPane;
    @FXML
    private Button graph;
    @FXML
    private Button MoveBack;
    private double firstOp = 0.0;
    private String currOp = "";
    private boolean isNum = false;

    private String currentEntry;
    private double resutl = 0.0;
    private double ansVal = 0.0;

    private  String theme="light";
    private String currForam = "DEC";
    private boolean isRadiansMode = true;


    @FXML
    public void initialize() {
        currentEntry = "";
        Clearbtn.setOnAction(this::handleClear);
        BackSpacebtn.setOnAction(this::handelBackspace);
        rootPane.getStyleClass().add(theme);

        isRadiansMode = false;
        ModeDisplay.setText("Deg");
        RadMod.setDisable(false);
        DegMod.setDisable(true);
    }


    @FXML
    private void handelBackspace(ActionEvent event) {
        if (!currentEntry.isEmpty()) {
            currentEntry = currentEntry.substring(0, currentEntry.length() - 1);
            handleSeconddisplay();
        }
    }

    @FXML
    private void handleDigit(ActionEvent e) {
        Button btn = (Button) e.getSource();
        String digit = btn.getText();
        currentEntry += btn.getText();
        handleSeconddisplay();
    }

    @FXML
    private void handleOper(ActionEvent e) {
        Button btn = (Button) e.getSource();
        currentEntry += " " + btn.getText() + " ";
        handleSeconddisplay();
    }

    @FXML
    private void handleParentheses(ActionEvent e) {
        Button btn = (Button) e.getSource();
        currentEntry += " " + btn.getText() + " ";
        handleSeconddisplay();
    }

    @FXML
    private void handleClear(ActionEvent event) {
        currentEntry = ""; // Reset currentEntry
        resutl = 0;
        Result.setText("0");
        SecondDisplay.setText("");
    }

    @FXML
    private void handleSeconddisplay() {
        SecondDisplay.setText(currentEntry);
    }

    @FXML
    private void handleEqual(ActionEvent event) {
        try {
            double result = evaluateExpression(currentEntry);
            Result.setText(Double.toString(result));
            ansVal = result;
        } catch (Exception e) {
            Result.setText("Error");
        }
    }
    @FXML
    private void handleANS(ActionEvent e){
        currentEntry += Double.toString(ansVal);
        handleSeconddisplay();
    }

    @FXML
    private void handlePi(ActionEvent e){
        currentEntry += "3.141592653589793";
        handleSeconddisplay();
    }

    @FXML
    private void handleE(ActionEvent e){
        currentEntry += "2.718281828459045";
        handleSeconddisplay();
    }

    @FXML
    private void handleLn(ActionEvent e){
        currentEntry +="Ln(";
        handleSeconddisplay();
    }

    @FXML
    private void handleSin(ActionEvent e) {
        currentEntry += "Sin(";
        handleSeconddisplay();
    }

    @FXML
    private void handleCos(ActionEvent e) {
        currentEntry += "Cos(";
        handleSeconddisplay();
    }

    @FXML
    private void handleTan(ActionEvent e) {
        currentEntry += "Tan(";
        handleSeconddisplay();
    }

    @FXML
    private void handlLog(ActionEvent e){
        currentEntry+="Log(";
        handleSeconddisplay();
    }
    @FXML
    private void handleSquareRoot(ActionEvent event) {
        currentEntry += "√(";
        handleSeconddisplay();
    }
    @FXML
    private void handleXpowerY(ActionEvent e) {
        currentEntry += " ^ ";
        handleSeconddisplay();
    }

    private List<String> themes = Arrays.asList("light","dark","pastel");
    private int currentTheme = 0;
    @FXML
    private  void switchTheme(){
        currentTheme++;
        if(currentTheme>=themes.size()){
            currentTheme=0;
        }

        String nextTheme=themes.get(currentTheme);
        rootPane.getStyleClass().clear();
        rootPane.getStyleClass().add(nextTheme);
    }

    @FXML
    private void handleHex(ActionEvent e){
        currForam = "HEX";
        Result.setText(converToHex(currentEntry));
    }
    @FXML
    private void handleDec(ActionEvent e){
        currForam = "DEC";
        Result.setText(currentEntry);
    }
    @FXML
    private void handleOct(ActionEvent e){
        currForam = "OCT";
        Result.setText(converToOct(currentEntry));
    }
    @FXML
    private void handleBin(ActionEvent e){
        currForam = "BIN";
        Result.setText(converToBin(currentEntry));
    }
    @FXML
    private void handlePowerTwo(ActionEvent e) {
        if (!currentEntry.isEmpty()) {
            double number = Double.parseDouble(currentEntry);
            double result = CalcSquare(number);
            currentEntry = Double.toString(result);
            handleSeconddisplay();
            Result.setText(currentEntry);
        }
    }
    @FXML
    private void handleRad (ActionEvent e){
        isRadiansMode = true;
        ModeDisplay.setText("Rad");
        RadMod.setDisable(true);
        DegMod.setDisable(false);
    }
    @FXML
    private void handleDegMod(ActionEvent e) {
        isRadiansMode = false;
        ModeDisplay.setText("Deg");
        RadMod.setDisable(false);
        DegMod.setDisable(true);
    }

    /*@FXML
    private void handlePane(ActionEvent e){
        double currentX = graphPane.getLayoutX();
        if (currentX == -350) {
            transformation(currentX, 8);
        }
    }*/
    /*@FXML
    private void handlePaneBack(ActionEvent e){
        double currentX = graphPane.getLayoutX();
        if (currentX == -350) {
            transformation(currentX, -350);
        }
    }*/




    private double evaluateExpression(String expression) {
        if (expression.contains("Sin(")) {
            return evaluateSinCosTan(expression, "Sin");
        } else if (expression.contains("Cos(")) {
            return evaluateSinCosTan(expression, "Cos");
        } else if (expression.contains("Tan(")) {
            return evaluateSinCosTan(expression, "Tan");
        } else if (expression.contains("Ln(")) {
            return evaluateLnFun(expression);
        }  else if (expression.contains("Log(")) {
            return evaluateLog(expression);
        } else if (expression.contains("√(")) {
            return evaluateSquareRoot(expression);
        } else if (expression.contains("^")) {
            return evaluateXpowerY(expression);
        }else {
            return evaluateInfixExpression(expression);
        }
    }


    private double evaluateInfixExpression(String expression) {
        Stack<Double> values = new Stack<>();
        Stack<Character> operators = new Stack<>();
        String[] tokens = expression.split(" ");

        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            char c = token.charAt(0);

            if (Character.isDigit(c) || (c == '.' && token.length() > 1)) {
                values.push(Double.parseDouble(token));
            } else if (isOperator(c)) {
                while (!operators.isEmpty() && hasHigherPrecedence(operators.peek(), c)) {
                    applyOperator(values, operators);
                }
                operators.push(c);
            } else if (c == '(') {
                operators.push(c);
            } else if (c == ')') {
                while (!operators.isEmpty() && operators.peek() != '(') {
                    applyOperator(values, operators);
                }
                if (!operators.isEmpty() && operators.peek() == '(') {
                    operators.pop();
                } else {
                    throw new IllegalArgumentException("Mismatched parentheses.");
                }
            }
        }

        while (!operators.isEmpty()) {
            applyOperator(values, operators);
        }

        if (values.size() != 1 || !operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        return values.pop();
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/'|| c == '%';
    }

    private boolean hasHigherPrecedence(char op1, char op2) {
        int precedence1 = getPrecedence(op1);
        int precedence2 = getPrecedence(op2);
        return precedence1 >= precedence2;
    }

    private int getPrecedence(char operator) {
        switch (operator) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return 0;
        }
    }

    private void applyOperator(Stack<Double> values, Stack<Character> operators) {
        if (values.size() < 2 || operators.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression.");
        }
        char operator = operators.pop();
        double b = values.pop();
        double a = values.pop();
        double result = performOperation(a, b, operator);
        values.push(result);
    }


    private double performOperation(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0) {
                    throw new ArithmeticException("Division by zero.");
                }
                return a / b;
            case '%':
                return a % b;
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    private double evaluateSinCosTan(String expression,String funName ){
        String[] parts = expression.split(funName + "\\(");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid " + funName + " function.");
        }
        String arg = parts[1].replaceAll("[^0-9+\\-*/.]", "").trim();
        double value = evaluateExpression(arg);
        if (!isRadiansMode) {
            value = Math.toRadians(value);
        }
        switch (funName) {
            case "Sin":
                return Math.sin(Math.toRadians(value));
            case "Cos":
                return Math.cos(Math.toRadians(value));
            case "Tan":
                return Math.tan(Math.toRadians(value));
            default:
                throw new IllegalArgumentException("Invalid function: " + funName);
        }
    }

    private  double evaluateLnFun(String expression){
        String[] parts = expression.split("Ln\\(");
        if (parts.length==2){
            String arg = parts[1].replaceAll("[^0-9+\\-*/.]", "").trim();
            double value = evaluateExpression(arg);
            if(value<=0){
                throw new IllegalArgumentException("Invalid Ln");
            }
            return Math.log(value);
        }else {
            throw new IllegalArgumentException("Invalid Ln function.");
        }
    }

    private double evaluateLog(String expression){
        String[] parts = expression.split("Log\\(");
        if (parts.length == 2) {
            String arg = parts[1].replaceAll("[^0-9+\\-*/.]", "").trim();
            double value = evaluateExpression(arg);
            if (value <= 0) {
                throw new IllegalArgumentException("Invalid Log");
            }
            return Math.log10(value);
        } else {
            throw new IllegalArgumentException("Invalid Log function.");
        }
    }

    private double evaluateSquareRoot(String expression) {
        String[] parts = expression.split("√\\(");
        if (parts.length == 2) {
            String arg = parts[1].replaceAll("[^0-9+\\-*/.]", "").trim();
            double value = evaluateExpression(arg);
            if (value < 0) {
                throw new IllegalArgumentException("Invalid Square Root");
            }
            return Math.sqrt(value);
        } else {
            throw new IllegalArgumentException("Invalid Square Root function.");
        }
    }

    private double evaluateXpowerY(String expression) {
        String[] parts = expression.split("\\^");
        if (parts.length != 2) {
            throw new IllegalArgumentException("Invalid X^Y operation.");
        }
        double base = evaluateExpression(parts[0]);
        double exponent = evaluateExpression(parts[1]);
        return Math.pow(base, exponent);
    }

    private String converToHex(String num){
        return Long.toHexString(Long.parseLong(num));
    }
    private String converToBin(String num){
        return Long.toBinaryString(Long.parseLong(num));
    }
    private String converToOct(String num){
        return  Long.toOctalString(Long.parseLong(num));
    }
    private double CalcSquare(double number) {
        return number * number;
    }
    /*public void transformation (double fromX,double toX){
        TranslateTransition transition = new TranslateTransition(Duration.seconds(1), graphPane);
        transition.setToX(toX - fromX);
        transition.play();
    }*/
}