import java.util.ArrayList;
import java.util.Random;

public class Algebraic {

    Character sign;
    int number;
    ArrayList<Character> signs;
    boolean debug = false;
    final Random random = new Random(System.currentTimeMillis());
    Algebraic(ArrayList<Character> new_signs, int new_num) {
        signs = new_signs;
        number = new_num;
    }

    public String build_expression() {
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
                return s1.build_expression() + " " + sign + " " + s2.build_expression();
            }
        for (int i = signs.size() - 1; i != -1; i--)
            if (signs.get(i) == '/' || signs.get(i) == '*') {
                Pair p = get_pair(signs.get(i), number);
                if (p == null)
                    return "e";
                s1 = new Algebraic(new ArrayList<Character>(signs.subList(0, i)), p.x);
                s2 = new Algebraic(new ArrayList<Character>(signs.subList(i + 1, signs.size())), p.y);
                sign = signs.get(i);
                return s1.build_expression() + " " + sign + " " + s2.build_expression();
            }
        return "e";
    }

    private Pair decompos_into_mult(int number) {
        int save = 0;
        if (debug)
            System.out.print("Начало разложения " + number);

        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            for (int i = 2; i < Math.floor(Math.sqrt(number)) + 1; i++)
                if (number % i == 0)
                    mArrayList.add(new Pair(i, number / i));
        }
        if (save == 500)
            return null;
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        if (debug)
            System.out.print(" в виде " + p.x + " * " + p.y + " \n");
        return save == 500 ? null : p;
    }

    private Pair decompos_into_division(int number) {
        int save = 0;
        if (debug)
            System.out.print("\nНачало разложения " + number);
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            for (int i = 2; i < number - 1; i++) {
                mArrayList.add(new Pair(i * number, i));
            }

        }
        if (save == 500)
            return null;
        Pair p = mArrayList.get(random.nextInt(mArrayList.size()));
        if (debug)
            System.out.print(" в виде " + p.x + " / " + p.y + " \n");
        return p;
    }

    private Pair decompos_into_sum(int number) {
        int save = 0;
        if (debug)
            System.out.print("Начало разложения " + number);
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y = 0;
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            x = random.nextInt(number) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = number - x;
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

    private Pair decompos_into_difference(int number) {
        int save = 0;
        if (debug)
            System.out.print("Начало разложения " + number);
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y;
        while (mArrayList.size() == 0 && save != 500) {
            save++;
            x = random.nextInt(2 * number) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = x - number;
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
            return decompos_into_sum(x);
        case '-':
            return decompos_into_difference(x);
        case '/':
            return decompos_into_division(x);
        case '*':
            return decompos_into_mult(x);
        default:
            return null;
        }
    }

}