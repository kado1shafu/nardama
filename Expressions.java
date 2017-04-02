import java.util.ArrayList;
import java.util.Random;

public class Expressions {

    public String get_expression(int n, int range) {
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Character> a = get_signs(n);
        int x = random.nextInt(range - 20) + 21;
        String s;
        do
            s = new Algebraic(a, x).get_expression();
        while (s.contains("e") == true);
        return s;
    }

    private ArrayList<Character> get_signs(int n) {
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Character> signs = new ArrayList<Character>();
        int sign;
        boolean flag;
        for (int i = 0; i < n; i++) {
            flag = true;
            sign = random.nextInt(4) + 1;
            for (char t : signs)
                if (get_sign(sign) == t)
                    flag = false;
            if (flag)
                signs.add(get_sign(sign));
            else
                i = i - 1;
        }
        return signs;
    }

    private char get_sign(int x) {
        switch (x) {
        case 1:
            return '+';
        case 2:
            return '-';
        case 3:
            return '/';
        case 4:
            return '*';
        default:
            return 0;
        }
    }

}