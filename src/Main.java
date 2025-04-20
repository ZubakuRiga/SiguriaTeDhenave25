import DisruptedTranspositionCipher.DisruptedDecrypt;
import DisruptedTranspositionCipher.DisruptedEncrypt;
import PlayFairCipher.PlayfairDecrypt;
import PlayFairCipher.PlayfairEncrypt;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        zgjedhAlgoritmin();
    }

    public static void zgjedhAlgoritmin() {
        System.out.println("-------------------------PLAYFAIR & DISRUPTED TRANSPOSITION CIPHERS-------------------------");
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("Zgjedhni cilin algoritem deshironi te perdorni: \n[1] Playfair\n[2] Disrupted Transposition\n");
            if(scanner.hasNextInt()){
                int zgjedhjaAlgoritmit = scanner.nextInt();
                if (zgjedhjaAlgoritmit == 1) {
                    playfair();
                    break;
                } else if (zgjedhjaAlgoritmit == 2) {
                    disruptedTrasnposition();
                    break;
                } else {
                    System.out.println("Inputi duhet te jete 1 ose 2!\n");
                }
            }else{
                System.out.println("Inputi nuk eshte numer!\n");
                scanner.next();
            }
        }
    }

    public static int zgjedhOperacionin(){
        Scanner scanner = new Scanner(System.in);
        int operacioni = 0;

        while (true) {
            System.out.println("Zgjedhni operacionin:\n[1] Enkriptim\n[2] Dekriptim");

            if (scanner.hasNextInt()) {
                operacioni = scanner.nextInt();
                if (operacioni == 1 || operacioni == 2) {
                    return operacioni;
                } else {
                    System.out.println("Inputi duhet te jete 1 ose 2!\n");
                }
            } else {
                System.out.println("Inputi nuk eshte numer!\n");
                scanner.next(); // clear the invalid input
            }
        }
    }

    public static void playfair(){
        int operacioni = zgjedhOperacionin();
        if(operacioni == 1){
            PlayfairEncrypt.execute(setText(), setCelsin());
        } else if (operacioni == 2) {
            PlayfairDecrypt.execute(setText(), setCelsin());
        }

        replay();
    }

    public static void disruptedTrasnposition(){
        int operacioni = zgjedhOperacionin();
        if(operacioni == 1){
            DisruptedEncrypt.encrypt(setText(), setCelsin());
        } else if (operacioni == 2) {
         DisruptedDecrypt.decrypt(setText(), setCelsin());
        }
        replay();
    }

    public static String setText(){
        System.out.println("Jepni Text: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static String setCelsin(){
        System.out.println("Jepni Celsin: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static void replay(){
        Scanner scanner = new Scanner(System.in);

        while(true){
            System.out.println("\nDeshironi me rifillu programin?\n[P] Po\n[J] Jo");
            char temp = scanner.next().toUpperCase().charAt(0);
            if(temp == 'P'){
                zgjedhAlgoritmin();
                break;
            } else if (temp == 'J') {
                System.exit(0);
            }else{
                System.out.println("Inputi nuk eshte P ose J!\n");
            }
        }
    }


}