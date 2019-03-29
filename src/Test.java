import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> formulasList=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        int choice=0;
        System.out.println("Wybierz opcję:\n1.Wczytaj działania z klawiatury\n2.Wczytaj działania z pliku\n3.Wyjście");
        choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Wprowadź działania. Słowo KONIEC kończy wpisywanie działań");
                    boolean flag=true;
                    while(flag){
                        String formula= scanner.nextLine();
                        if(formula.equals("KONIEC")){
                            flag=false;
                            choice=0;
                            for (String f:formulasList) {
                                OnpAlgorithm onpAlgorithm=new OnpAlgorithm();
                                System.out.println(onpAlgorithm.convert(f));

                            }
                            break;
                        }
                        else{
                            formulasList.add(formula);
                        }
                    }
                    break;
                case 2:
                    System.out.println("Podaj nazwę pliku tekstowego:");
                    String filepath=scanner.nextLine();
                    formulasList=OnpAlgorithm.loadFormulas(filepath);
                    choice=0;
                    break;
                case 0:
                    System.out.println("Wybierz opcję:\n1.Wczytaj działania z klawiatury\n2.Wczytaj działania z pliku\n3.Wyjście");
                    break;
            }
    }
}
