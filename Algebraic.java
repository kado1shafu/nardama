import java.util.ArrayList;
import java.util.Random;

public class Algebraic {

    Character sign;
    int number;
    ArrayList<Character> signs;
    boolean debug = false;

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
                if (p == null)
                    return "e";
                s1 = new Algebraic(new ArrayList<Character>(signs.subList(0, i)), p.x);
                s2 = new Algebraic(new ArrayList<Character>(signs.subList(i + 1, signs.size())), p.y);
                sign = signs.get(i);
                return s1.get_expression() + " " + sign + " " + s2.get_expression();
            }
        for (int i = 0; i < signs.size(); i++)
            if (signs.get(i) == '/' || signs.get(i) == '*') {
                Pair p = get_pair(signs.get(i), number);
                if (p == null)
                    return "e";
                s1 = new Algebraic(new ArrayList<Character>(signs.subList(0, i)), p.x);
                s2 = new Algebraic(new ArrayList<Character>(signs.subList(i + 1, signs.size())), p.y);
                sign = signs.get(i);
                return s1.get_expression() + " " + sign + " " + s2.get_expression();
            }

        return "null";
    }

    private Pair get_pair_mult(int range) {
        int save = 0;
        if (debug)
            System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            for (int i = 2; i < Math.floor(Math.sqrt(range)) + 1; i++)
                if (range % i == 0)
                    mArrayList.add(new Pair(i, range / i));
        }
        if (save == 500)
            return null;
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        if (debug)
            System.out.print(" в виде " + p.x + " * " + p.y + " \n");
        return save == 500 ? null : p;
    }

    private Pair get_pair_unmult(int range) {
        int save = 0;
        if (debug)
            System.out.print("\nНачало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            for (int i = 2; i < range - 1; i++) {
                mArrayList.add(new Pair(i * range, i));
            }

        }
        if (save == 500)
            return null;
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        if (debug)
            System.out.print(" в виде " + p.x + " / " + p.y + " \n");
        return p;
    }

    private Pair get_pair_sum(int range) {
        int save = 0;
        if (debug)
            System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y = 0;
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            x = random.nextInt(range) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = range - x;
                if (Math.abs(x - 3 * y) < x / 2 && y != x - y)
                    mArrayList.add(new Pair(y, x));
            }

        }
        if (save == 500)
            return null;
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        if (debug)
            System.out.print(" в виде " + p.x + " + " + p.y + " \n");
        return p;
    }

    private Pair get_pair_unsum(int range) {
        int save = 0;
        if (debug)
            System.out.print("Начало разложения " + range);
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y;
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            x = random.nextInt(2 * range) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = x - range;
                if (y > 0 && Math.abs(x - 3 * y) < x / 2)
                    mArrayList.add(new Pair(x, y));
            }

        }
        if (save == 500)
            return null;
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        if (debug)
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