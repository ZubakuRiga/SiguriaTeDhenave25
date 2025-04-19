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

}