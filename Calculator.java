import java.util.Scanner;

public class Calculator
{
    public static void main(String[] args)
    {
        String[] str_Restart = null; // ----- Para no final dar para dar restart à calculadora.

        Scanner scanner = new Scanner(System.in);

        System.out.print( "\n»»»»»» CALCULADORA «««««« "
                        + "\nEscolha uma opção: "
                        + "\nPara iniciar marque 1 "
                        + "\nPara sair marque -1 \n");

        int opcao = scanner.nextInt();

        if(opcao == 1)
        {
            System.out.print("Introduza o primeiro número: ");
            int a = scanner.nextInt();

            System.out.print("Introduza a operação pretendida (+, -, / ou *): ");
            String operator = scanner.next();

            System.out.print("Introduza o segundo número: ");
            int b = scanner.nextInt();    
                        
            CalculatorMethods calculatorMethods = new CalculatorMethods();

            int intResult = 0;

            if(operator.equals("+"))
                intResult = calculatorMethods.sum(a, b);
            else if(operator.equals("-"))
                intResult = calculatorMethods.sub(a, b);
            else if(operator.equals("/"))
                intResult = calculatorMethods.div(a, b);
            else if(operator.equals("*"))
                intResult = calculatorMethods.mult(a, b); 
            else
            {
                System.out.print("Operação inválida!");
                return;
            }

            System.out.print( "\nA sua conta é: "
                            + a
                            + " "
                            + operator
                            + " "
                            + b
                            + " = "
                            + intResult);

            System.out.print("\nDeseja continuar? (Sim ou Nao): ");
            String askToContinue = scanner.next();

            if(askToContinue.equals("Sim"))
                main(str_Restart);
            else
                SayAdeus();
        }
        else
            SayAdeus();
    }

    public static void SayAdeus()
    {
        System.out.print("\n»»»»»» ADEUS! ««««««\n");
    }
}