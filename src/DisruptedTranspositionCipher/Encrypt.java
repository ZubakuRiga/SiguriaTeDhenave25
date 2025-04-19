package DisruptedTranspositionCipher;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class Encrypt {
    public static void main(String[] args) {
        DisruptedTransposition.encrypt("Sot kemi mesim ne klasen tjeter","un");
    }
}

//Rreshtat kane gjatsi fikse. Gjatsia e qelsit tregon gjatesin e rreshtave ndersa renditje e shkronjave tqelsit tregon se cila kolone lexohet e para. BCA-qelsi, kolona 3
//lexohet e para pastaj kolona 1 dhe kolona 2 e fundit. Nuk perdoren filler letters

class DisruptedTransposition {
    public static void encrypt(String plain, String key){
        char[] chars = plain.toCharArray();
        int nrCols = key.length();
        int nrRows;
        if(plain.length() % key.length()==0){
            nrRows = plain.length()/key.length();
        }else{
            nrRows = plain.length()/key.length()+1;
        }

        char[][] matrix = new char[nrRows][nrCols];

        for(int i=0; i<nrRows; i++){
            for (int j=0; j<nrCols;j++) {
                int charsPointer = nrCols * i + j ;
                if(charsPointer < chars.length){
                    matrix[i][j] = chars[charsPointer];
                }else{
                    break;
                }
            }
        }

        //Caktimi i pozitave ne alfabet te shkronjave te celsit
        ArrayList<Integer> keyOrderDistorted = new ArrayList<>();
        ArrayList<Integer> keyOrder = new ArrayList<>();
        for(int i=0; i<key.length(); i++){
            char keyChar = key.toUpperCase().charAt(i);
            int alphabetOrder = (int)(keyChar - 'A')%26;

            if(keyOrderDistorted.contains(alphabetOrder)){ //ky "if" mundeson qe qelsi me pas shkronja duplikate. Nese ka renditja n'baze talfabetit prishet
                // sepse i shtohen +26. Shtimi i variables "i" lejon qe nese e njejta shkronje perseritet disa here nje pas nje "uuuuu" te kete
                // mundesin qe seicla te identifikohet veqmas.
                keyOrderDistorted.add(alphabetOrder+26+i);
            }else{
                keyOrderDistorted.add(alphabetOrder); //Permban poziten ne alfabet te shkronjave te celsit
            }

        }

        //Rankimi i pozitave te shkronjave ne alfabet, duke filluar nga 0 dhe duke u rrit per 1
        for (int i=0; i<key.length();i++){
            int counter=0;
            for (int j=0; j<key.length();j++){
                if(keyOrderDistorted.get(j) < keyOrderDistorted.get(i)){
                    counter++;
                }
            }
            keyOrder.add(counter);
        }

        //Leximi kolonave sipas renditjes se shkronjave te qelsit
        StringBuilder cipher = new StringBuilder();
        for(int i=0; i<nrCols;i++){
            int nrKolones = keyOrder.indexOf(i);
            for(int j=0; j<nrRows;j++) {
                char temp = matrix[j][nrKolones];
                if(temp == '\u0000'){ // '\u0000' reprezanton karakter qe eshte null
                    break;
                }else{
                    cipher.append(temp);
                }

            }
        }

        //Printimi i informatave
        System.out.print("--------------------------------------------------------------------------\n");
        System.out.println("Qelsi: "+ key.toUpperCase());
        System.out.println("Pozita e shkronjave ne alfabet: "+ keyOrderDistorted);
        System.out.println("Rankimi i shkronjave: "+keyOrder);
        System.out.print("--------------------------------------------------------------------------\n");

        System.out.println("Tabela: \n");
        for(int i=0; i<nrCols; i++){
            System.out.print(keyOrder.get(i)+1 + "  ");
        }
        System.out.println("\n");
        for(int i=0; i<nrRows; i++){
            for (int j=0; j<nrCols;j++){
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.print("\n");
        }

        System.out.print("--------------------------------------------------------------------------\n");
        System.out.println("\nMesazhi i enkriptuar: "+cipher);
    };
}