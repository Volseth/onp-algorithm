import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        ArrayList<String> formulasList=new ArrayList<>();
        Scanner scanner=new Scanner(System.in);
        int choice=0;
        System.out.println("Wybierz opcję:\n1.Wczytaj działania z klawiatury\n2.Wczytaj działania z pliku\n3.Wyjście");
        String filepath="f.txt";
        choice=scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Wprowadź działania. Słowo KONIEC kończy wpisywanie działań");
                    while(true){
                        String formula= scanner.nextLine();
                        if(formula.equals("KONIEC")){
                            formulasList.remove(0);
                            for (String f:formulasList) {
                                OnpAlgorithm onpAlgorithm=new OnpAlgorithm();
                                System.out.println(onpAlgorithm.convert(f));
                                System.out.println("Wynik:"+onpAlgorithm.calculate());

                            }
                            break;
                        }
                        else{
                            formulasList.add(formula);
                        }
                    }
                    break;
                case 2:
                    formulasList=OnpAlgorithm.loadFormulas(filepath);
                    for (String f:formulasList) {
                        OnpAlgorithm onpAlgorithm=new OnpAlgorithm();
                        System.out.println(onpAlgorithm.convert(f));
                        System.out.println("Wynik:"+onpAlgorithm.calculate());
                    }
                    break;
                case 0:
                    System.out.println("Wybierz opcję:\n1.Wczytaj działania z klawiatury\n2.Wczytaj działania z pliku\n3.Wyjście");
                    break;
            }
    }
}
