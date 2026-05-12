import java.util.*;

class KMP {

    public int[] computeLPS(String pattern) {
        int m = pattern.length();
        int[] lps = new int[m];

        int len = 0;
        int i = 1;

        while (i < m) {

            if (pattern.charAt(i) == pattern.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } 
            else {

                if (len != 0) {
                    len = lps[len - 1];
                } 
                else {
                    lps[i] = 0;
                    i++;
                }
            }
        }

        return lps;
    }

    public void KMPsearch(String text, String pattern) {

        int n = text.length();
        int m = pattern.length();

        int[] lps = computeLPS(pattern);

        int i = 0, j = 0;
        boolean found = false;

        while (i < n) {

            if (pattern.charAt(j) == text.charAt(i)) {
                i++;
                j++;
            }

            if (j == m) {
                System.out.println("Pattern found at index: " + (i - j));
                found = true;

                j = lps[j - 1];
            }

            else if (i < n && pattern.charAt(j) != text.charAt(i)) {

                if (j != 0) {
                    j = lps[j - 1];
                } 
                else {
                    i++;
                }
            }
        }

        if (!found) {
            System.out.println("Pattern not found!");
        }
    }
}

public class practice_ass_7 {

    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter pattern: ");
        String pattern = sc.nextLine();

        KMP obj = new KMP();

        obj.KMPsearch(text, pattern);

        sc.close();
    }
}