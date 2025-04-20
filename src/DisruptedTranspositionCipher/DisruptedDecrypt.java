package DisruptedTranspositionCipher;

import java.util.ArrayList;

public class DisruptedDecrypt {
    public static void decrypt(String cipher, String key) {
        char[] chars = cipher.toCharArray();
        int nrCols = key.length();
        int nrRows;
        if (cipher.length() % key.length() == 0) {
            nrRows = cipher.length() / key.length();
        } else {
            nrRows = cipher.length() / key.length() + 1;
        }

        char[][] matrix = new char[nrRows][nrCols];

        ArrayList<Integer> keyOrderDistorted = new ArrayList<>();
        ArrayList<Integer> keyOrder = new ArrayList<>();

        for (int i = 0; i < key.length(); i++) {
            char keyChar = key.toUpperCase().charAt(i);
            int alphabetOrder = (int) (keyChar - 'A') % 26;

            if (keyOrderDistorted.contains(alphabetOrder)) {
                keyOrderDistorted.add(alphabetOrder + 26 + i);
            } else {
                keyOrderDistorted.add(alphabetOrder);
            }
        }

        for (int i = 0; i < key.length(); i++) {
            int counter = 0;
            for (int j = 0; j < key.length(); j++) {
                if (keyOrderDistorted.get(j) < keyOrderDistorted.get(i)) {
                    counter++;
                }
            }
            keyOrder.add(counter);
        }

        int totalChars = chars.length;
        int fullColumns = totalChars % nrCols;

        int cipherLength = chars.length;
        int columnsWithExtraChar = totalChars % nrCols;

        int pointer = 0;
        for (int i = 0; i < nrCols; i++) {
            int col = keyOrder.indexOf(i);
            int rowsInThisCol = nrRows;
            if (fullColumns != 0 && col >= fullColumns) {
                rowsInThisCol = nrRows - 1;
            }

            for (int j = 0; j < rowsInThisCol; j++) {
                if (pointer < chars.length) {
                    matrix[j][col] = chars[pointer];
                    pointer++;
                }
            }
        }


        StringBuilder plain = new StringBuilder();
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                char temp = matrix[i][j];
                if (temp == '\u0000') {
                    continue;
                } else {
                    plain.append(temp);
                }
            }
        }

        System.out.print("--------------------------------------------------------------------------\n");
        System.out.println("Qelsi: " + key.toUpperCase());
        System.out.println("Pozita e shkronjave ne alfabet: " + keyOrderDistorted);
        System.out.println("Rankimi i shkronjave: " + keyOrder);
        System.out.print("--------------------------------------------------------------------------\n");

        System.out.println("Tabela: \n");
        for (int i = 0; i < nrCols; i++) {
            System.out.print(keyOrder.get(i) + 1 + "  ");
        }
        System.out.println("\n");
        for (int i = 0; i < nrRows; i++) {
            for (int j = 0; j < nrCols; j++) {
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.print("\n");
        }

        System.out.print("--------------------------------------------------------------------------\n");
        System.out.println("\nMesazhi i dekriptuar: " + plain);
    }
}