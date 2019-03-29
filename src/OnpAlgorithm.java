
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
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filepath)))) {
            while (bufferedReader.readLine() != null) {
                formula.add(bufferedReader.readLine());
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
        while(stringTokenizer.hasMoreTokens()){
            String s=stringTokenizer.nextToken();
            if(isOperator(s)){
                while(!operatorsStack.isEmpty() && precedence(operatorsStack.peek())>=precedence(s)) stringBuilder.append(operatorsStack.pop());
                operatorsStack.push(s);
            }
            else if(s.equals("(")) operatorsStack.push(s);
            else if(s.equals(")")) {
                while(!operatorsStack.peek().equals("(")) stringBuilder.append(operatorsStack.pop());
                operatorsStack.pop();
                }
            else stringBuilder.append(s);
        }
        while(!operatorsStack.isEmpty()) stringBuilder.append(operatorsStack.pop());

    return stringBuilder.toString();
    }
}
