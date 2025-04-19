package PlayFairCipher;
import java.util.*;

public class Decrypt {
    private static final int SIZE =5;
    private char[][] matrica = new char[SIZE][SIZE];
    private Map<Character, int[]> shkronja_pozicion = new HashMap<>();

    public Decrypt(String celesi){
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
            int[] pozA = shkronja_pozicion(a);
            int[] pozB = shkronja_pozicion(b);

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

}