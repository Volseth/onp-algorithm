
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class OnpAlgorithm {
    private Stack<String> operatorsStack = new Stack<>();
    private String postfix = "";
    private StringBuilder stringBuilder = new StringBuilder(postfix);

    public OnpAlgorithm() {
    }

    public static ArrayList<String> loadFormulas(String filepath) {
        ArrayList<String> formula = new ArrayList<>();
        String line;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filepath)))) {
            while ((line=bufferedReader.readLine()) != null) {
                formula.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formula;
    }

    private boolean isOperator(String op) {
        switch (op) {
            case "+":
            case "-":
            case "*":
            case "/":
            case "^":
                return true;
            default:
                return false;
        }
    }

    private int precedence(String op) {
        switch (op) {
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 2;
            case "/":
                return 2;
            case "^":
                return 3;
            default:return 0;
        }
    }


    public String convert(String infix) {
        StringTokenizer stringTokenizer=new StringTokenizer(infix,"+-*/^()",true);
        String previousToken="";
        while(stringTokenizer.hasMoreTokens()){
            String s=stringTokenizer.nextToken();
            if(isOperator(s)){
                while(!operatorsStack.isEmpty() && precedence(operatorsStack.peek())>=precedence(s)) stringBuilder.append(operatorsStack.pop()).append(" ");
                operatorsStack.push(s);
            }
            else if(s.equals("(")) operatorsStack.push(s);
            else if(s.equals(")")) {
                while(!operatorsStack.peek().equals("(")) stringBuilder.append(operatorsStack.pop()).append(" ");
                operatorsStack.pop();
                }
            else stringBuilder.append(s).append(" ");
            if (s.equals("-") && previousToken.equals("(")) stringBuilder.append("0").append(" ");
            previousToken=s;
        }
        while(!operatorsStack.isEmpty()) stringBuilder.append(operatorsStack.pop()).append(" ");
    postfix=stringBuilder.toString();
    return stringBuilder.toString();
    }
    public double calculate(){
        double value=0;
        Stack<Double> tempStack=new Stack<>();
        StringTokenizer stringTokenizer=new StringTokenizer(postfix," ",false);
        while(stringTokenizer.hasMoreTokens() && !postfix.equals("")){
            String s=stringTokenizer.nextToken();
            if(isOperator(s)){
                double b=tempStack.pop();
                double a=tempStack.pop();
                switch(s){
                    case "+":tempStack.push(a+b); break;
                    case "-":tempStack.push(a-b); break;
                    case "*":tempStack.push(a*b); break;
                    case "/": if(b==0) throw new IllegalArgumentException("Dzielenie przez 0"); tempStack.push(a/b); break;
                    case "^":tempStack.push(Math.pow(a,b)); break;
                }
            }
            else{
                tempStack.push(Double.parseDouble(s));
            }
        }
        if(!tempStack.isEmpty()){
            value=tempStack.peek();
        }
        return value;
    }
}
