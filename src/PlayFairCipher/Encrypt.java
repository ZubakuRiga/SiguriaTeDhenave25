package PlayFairCipher;

import java.util.*;

public class Encrypt {
    private static final int SIZE = 5;
    private char[][] matrix = new char[SIZE][SIZE];
    private Map<Character, int[]> letterPos = new HashMap<>();

    public Encrypt(String keyword) {
        buildMatrix(keyword.toUpperCase());
    }

    private void buildMatrix(String keyword) {
        Set<Character> used = new LinkedHashSet<>();
        for (char c : keyword.toCharArray()) {
            if (c == 'J') c = 'I';
            if (Character.isLetter(c)) used.add(c);
        }

        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            used.add(c);
        }

        Iterator<Character> it = used.iterator();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char c = it.next();
                matrix[row][col] = c;
                letterPos.put(c, new int[]{row, col});
            }
        }
    }

    public String encrypt(String text) {
        text = preprocess(text.toUpperCase());
        StringBuilder encrypted = new StringBuilder();

        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            int[] posA = letterPos.get(a);
            int[] posB = letterPos.get(b);

            if (posA[0] == posB[0]) {
                encrypted.append(matrix[posA[0]][(posA[1] + 1) % SIZE]);
                encrypted.append(matrix[posB[0]][(posB[1] + 1) % SIZE]);
            } else if (posA[1] == posB[1]) {
                encrypted.append(matrix[(posA[0] + 1) % SIZE][posA[1]]);
                encrypted.append(matrix[(posB[0] + 1) % SIZE][posB[1]]);
            } else {
                encrypted.append(matrix[posA[0]][posB[1]]);
                encrypted.append(matrix[posB[0]][posA[1]]);
            }
        }

        return encrypted.toString();
    }

    private String preprocess(String text) {
        StringBuilder cleaned = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                cleaned.append(c == 'J' ? 'I' : c);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < cleaned.length(); i++) {
            char a = cleaned.charAt(i);
            char b = (i + 1 < cleaned.length()) ? cleaned.charAt(i + 1) : 'X';

            if (a == b) {
                result.append(a).append('X');
            } else {
                result.append(a).append(b);
                i++;
            }
        }

        if (result.length() % 2 != 0) result.append('X');
        return result.toString();
    }

    public static void main(String[] args) {
        Encrypt cipher = new Encrypt("playfair");
        String plaintext = "riga";
        String encrypted = cipher.encrypt(plaintext);
        System.out.println("Encrypted: " + encrypted);
    }
}
