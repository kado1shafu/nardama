import java.util.ArrayList;
import java.util.Random;

public class Algebraic {

    Character sign;
    int number;
    ArrayList<Character> signs;

    Algebraic(ArrayList<Character> new_signs, int new_num) {
        signs = new_signs;
        number = new_num;
    }

    public String get_expression() {
        if (signs.size() == 0)
            return number + "";
        Algebraic s1, s2;
        for (int i = 0; i < signs.size(); i++)
            if (signs.get(i) == '+' || signs.get(i) == '-') {
                Pair p = get_pair(signs.get(i), number);
                s1 = new Algebraic(new ArrayList<Character>(signs.subList(0, i)), p.x);
                s2 = new Algebraic(new ArrayList<Character>(signs.subList(i + 1, signs.size())), p.y);
                sign = signs.get(i);
                return s1.get_expression() + " " + sign + " " + s2.get_expression();
            }
        for (int i = 0; i < signs.size(); i++)
            if (signs.get(i) == '/' || signs.get(i) == '*') {
                Pair p = get_pair(signs.get(i), number);
                s1 = new Algebraic(new ArrayList<Character>(signs.subList(0, i)), p.x);
                s2 = new Algebraic(new ArrayList<Character>(signs.subList(i + 1, signs.size())), p.y);
                sign = signs.get(i);
                return s1.get_expression() + " " + sign + " " + s2.get_expression();
            }

        return "null";
    }

    private Pair get_pair_mult(int range) {
        System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0) {
            for (int i = 1; i < Math.floor(Math.sqrt(range)) + 1; i++)
                if (range % i == 0)
                    mArrayList.add(new Pair(i, range / i));
        }

        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        System.out.print(" в виде " + p.x + " + " + p.y + " \n");
        return p;
    }

    private Pair get_pair_unmult(int range) {
        System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0) {
            for (int i = 1; i < Math.floor(Math.sqrt(range)) + 1; i++) {
                int x = random.nextInt(range) + 1;
                if (x * range <= 2 * x * x)
                    mArrayList.add(new Pair(x * range, x));
            }

        }
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        System.out.print(" в виде " + p.x + " / " + p.y + " \n");
        return p;
    }

    private Pair get_pair_sum(int range) {

        System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y = 0;
        while (mArrayList.size() == 0) {
            x = random.nextInt(range) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = range - x;
                if (Math.abs(x - 3 * y) < x / 2 && y != x - y)
                    mArrayList.add(new Pair(y, x));
            }

        }

        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        System.out.print(" в виде " + p.x + " + " + p.y + " \n");
        return p;
    }

    private Pair get_pair_unsum(int range) {
        System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y;
        while (mArrayList.size() == 0) {
            x = random.nextInt(2 * range) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = x - range;
                if (y > 0 && Math.abs(x - 3 * y) < x / 2)
                    mArrayList.add(new Pair(x, y));
            }

        }

        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        System.out.print(" в виде " + p.x + " - " + p.y + " \n");
        return p;
    }

    private Pair get_pair(char sign, int x) {
        switch (sign) {
        case '+':
            return get_pair_sum(x);
        case '-':
            return get_pair_unsum(x);
        case '/':
            return get_pair_unmult(x);
        case '*':
            return get_pair_mult(x);
        default:
            return null;
        }
    }

}