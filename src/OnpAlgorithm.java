import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Stack;

public class OnpAlgorithm {
    ArrayList<String> formula;

    public OnpAlgorithm(ArrayList<String> formula){
        this.formula=formula;

    }
    public static ArrayList<String> loadFormulas(String filepath) {
        ArrayList<String> formula=new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(filepath)))) {
        while(bufferedReader.readLine()!=null){
            formula.add(bufferedReader.readLine());
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return formula;
    }

    public void converseFormula(){
        ArrayDeque<String> numbersDeque=new ArrayDeque<>();
        Stack<String> operatorsStack=new Stack<>();
        ArrayList<String> onpFormulas=new ArrayList<>();
        for (String s:formula ) {
            String [] splitedString= s.split("");
            for (int i=0;i<splitedString.length;i++){
                if(splitedString[i].matches("/d")){
                    numbersDeque.add(splitedString[i]);
                }
                if(splitedString[i].equals("(")){
                    operatorsStack.push(splitedString[i]);
                }

            }
        }
    }
}
