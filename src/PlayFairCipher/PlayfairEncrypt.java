package PlayFairCipher;

import java.util.*;

public class PlayfairEncrypt {
    private static final int SIZE = 5;
    // Matrica 5x5 që ruan çelësin e përpunuar për PlayfairCipher
    private final char[][] matrix = new char[SIZE][SIZE];
    private final Map<Character, int[]> letterPos = new HashMap<>();

    public PlayfairEncrypt(String keyword) {
        buildMatrix(keyword.toUpperCase());
    }

    // Ndërton matricën 5x5 bazuar në çelës dhe pjesën tjetër të alfabetit
    private void buildMatrix(String keyword) {
        Set<Character> used = new LinkedHashSet<>();
        // Hapi 1: Shton shkronjat e çelësit, zëvendëson J me I dhe shmang përsëritjet
        for (char c : keyword.toCharArray()) {
            if (c == 'J') c = 'I';
            if (Character.isLetter(c)) used.add(c);
        }

        // Hapi 2: Shto shkronjat e tjera të alfabetit që nuk janë përdorur (përveç J)
        for (char c = 'A'; c <= 'Z'; c++) {
            if (c == 'J') continue;
            used.add(c);
        }
        // Hapi 3: Plotëson matricën rresht për rresht dhe regjistron pozicionet e shkronjave
        Iterator<Character> it = used.iterator();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                char c = it.next();
                matrix[row][col] = c;
                letterPos.put(c, new int[]{row, col});
            }
        }
    }

    // Metoda që kryen enkriptimin me PlayfairCipher për tekstin e dhënë
    public String encrypt(String text) {
        text = preprocess(text.toUpperCase());
        StringBuilder encrypted = new StringBuilder();

        // Përpunon tekstin në grupe prej dy shkronjash (digrafë)
        for (int i = 0; i < text.length(); i += 2) {
            char a = text.charAt(i);
            char b = text.charAt(i + 1);
            int[] posA = letterPos.get(a);
            int[] posB = letterPos.get(b);

            // Rregulli 1: Në të njëjtin rresht → zhvendos djathtas
            if (posA[0] == posB[0]) {
                encrypted.append(matrix[posA[0]][(posA[1] + 1) % SIZE]);
                encrypted.append(matrix[posB[0]][(posB[1] + 1) % SIZE]);

            // Rregulli 2: Në të njëjtën kolone → zhvendos poshtë
            } else if (posA[1] == posB[1]) {
                encrypted.append(matrix[(posA[0] + 1) % SIZE][posA[1]]);
                encrypted.append(matrix[(posB[0] + 1) % SIZE][posB[1]]);

            // Rregulli 3: Formojnë drejtkëndësh → ndërro kolonat
            } else {
                encrypted.append(matrix[posA[0]][posB[1]]);
                encrypted.append(matrix[posB[0]][posA[1]]);
            }
        }

        return encrypted.toString();
    }

    // Metoda për të përgatitur tekstin për enkriptim
    private String preprocess(String text) {
        StringBuilder cleaned = new StringBuilder();

        // Hapi 1: Largon karakteret që nuk janë shkronja dhe zëvendëso J me I
        for (char c : text.toCharArray()) {
            if (Character.isLetter(c)) {
                cleaned.append(c == 'J' ? 'I' : c);
            }
        }
        // Hapi 2: Krijo digrafë dhe fut 'X' nëse ka shkronja të njëjta radhazi
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
        // Hapi 3: Shto 'X' në fund nëse numri i shkronjave është tek
        if (result.length() % 2 != 0) result.append('X');
        return result.toString();
    }

    // Shfaq matricën Playfair
    public void printMatrix() {
        System.out.println("Playfair Matrix:");
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                System.out.print(matrix[row][col] + " ");
            }
            System.out.println();
        }
    }

    // Metodë ndihmëse për të ekzekutuar PlayFairCipher dhe shfaqjen e matrices
    public static void execute(String plaintext, String key) {
        PlayfairEncrypt cipher = new PlayfairEncrypt(key);
        cipher.printMatrix();
        String encrypted = cipher.encrypt(plaintext);
        System.out.println("Encrypted: " + encrypted);
    }
}
