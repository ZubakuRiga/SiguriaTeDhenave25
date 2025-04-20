package PlayFairCipher;
import java.util.*;

public class PlayfairDecrypt {
    private static final int SIZE =5;
    private char[][] matrica = new char[SIZE][SIZE];
    private Map<Character, int[]> shkronja_pozicion = new HashMap<>();

    public PlayfairDecrypt(String celesi){
        krijoMatricen(celesi.toUpperCase());
    }

    private void krijoMatricen(String celesi){
        //Ruajtja e shkronjave te perdorura, me rend dhe pa perseritje
        Set<Character> used = new LinkedHashSet<>();


        //Shkronja J zevendesohet me I ne Playfair Cipher
        for (char c : celesi.toCharArray()){
            if (c == 'J') c = 'I';
            if (Character.isLetter(c)) used.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++){
            if (c == 'J') continue;
            used.add(c);
        }


        //25 shkronjat unike vendosen ne matrice dhe pozitat e tyre ne Map- shkronja_pozicion
        Iterator<Character> it = used.iterator();
        for(int rreshti =0; rreshti < SIZE; rreshti++){
            for(int kolona=0; kolona <SIZE; kolona++){
                char c = it.next();
                matrica[rreshti][kolona] = c;
                shkronja_pozicion.put(c, new int[]{rreshti, kolona});
            }
        }
    }

    //Logjika e dekriptimit
    public String decrypt(String cipherText){
        cipherText = cipherText.toUpperCase();
        StringBuilder decrypted = new StringBuilder();

        for(int i=0; i<cipherText.length(); i+=2){
            char a = cipherText.charAt(i);
            char b = cipherText.charAt(i+1);
            int[] pozA = shkronja_pozicion.get(a);
            int[] pozB = shkronja_pozicion.get(b);

            if(pozA[0] == pozB[0]){
                decrypted.append(matrica[pozA[0]][(pozA[1]+SIZE - 1) % SIZE]);
                decrypted.append(matrica[pozB[0]][(pozB[1]+SIZE - 1) % SIZE]);
            }else if (pozA[1] == pozB[1]){
                decrypted.append(matrica[(pozA[0]+ SIZE -1)% SIZE][pozA[1]]);
                decrypted.append(matrica[(pozB[0]+ SIZE -1)% SIZE][pozB[1]]);
            }else {
                decrypted.append(matrica[pozA[0]][pozB[1]]);
                decrypted.append(matrica[pozB[0]][pozA[1]]);
            }
        }
        return decrypted.toString();

    }

    //Kontrollimi per X ne mes shkronjave te njejta
    private static String cleanDecryptedText(String text) {
        StringBuilder cleaned = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            char current = text.charAt(i);

            if (i > 0 && i < text.length() - 1 &&
                    current == 'X' &&
                    text.charAt(i - 1) == text.charAt(i + 1)) {
                continue;
            }
            cleaned.append(current);
        }
        //Largojme X nese eshte shtuar per plotesim
        if (cleaned.length() > 0 && cleaned.charAt(cleaned.length() - 1) == 'X') {
            cleaned.setLength(cleaned.length() - 1);
        }

        return cleaned.toString();
    }




            public void printoMatricen(){
                System.out.println("Playfair Matrix:");
                System.out.println("---------------------");
                for (int i = 0; i < SIZE; i++) {
                    System.out.print("| ");
                    for (int j = 0; j < SIZE; j++) {
                        System.out.print(matrica[i][j] + " ");
                    }
                    System.out.println("|");
                }
                System.out.println("---------------------");
            }

    public static void execute(String cipher, String key) {
        PlayfairDecrypt plain = new PlayfairDecrypt(key);
        plain.printoMatricen();

        String decrypted = plain.decrypt(cipher);

        System.out.println("Enkriptuar: " +cipher);
        System.out.println("Dekriptuar: " + cleanDecryptedText(decrypted));

    }

}