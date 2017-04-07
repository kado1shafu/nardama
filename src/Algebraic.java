import java.util.ArrayList;
import java.util.Random;

public class Algebraic {

    Character sign;
    int number;
    int def_x;
    ArrayList<Character> signs;
    static int range = 300;
    final static Random random = new Random(System.currentTimeMillis());

    Algebraic(ArrayList<Character> new_signs, int new_num, int answ) {
        def_x = answ;
        signs = new_signs;
        number = new_num;
    }

    // Рекурсивное построение случайного выражения
    public String build_expression() {
        if (signs.size() == 0) return number + "";

        Algebraic s1, s2;
        for (int i = 0; i < signs.size(); i++)
            if (signs.get(i) == '+' || signs.get(i) == '-') {
                Pair p = get_pair(signs.get(i), number, def_x);
                if (p == null) return "e";
                s1 = new Algebraic(new ArrayList<>(signs.subList(0, i)), (int)p.getx(), def_x);
                s2 = new Algebraic(new ArrayList<>(signs.subList(i + 1, signs.size())), (int)p.gety(), def_x);
                sign = signs.get(i);
                return s1.build_expression() + " " + sign + " " + s2.build_expression();
            }
        for (int i = signs.size() - 1; i != -1; i--)
            if (signs.get(i) == '/' || signs.get(i) == '*') {
                Pair p = get_pair(signs.get(i), number, def_x);
                if (p == null) return "e";
                s1 = new Algebraic(new ArrayList<>(signs.subList(0, i)), (int)p.getx(), def_x);
                s2 = new Algebraic(new ArrayList<>(signs.subList(i + 1, signs.size())), (int)p.gety(), def_x);
                sign = signs.get(i);
                return s1.build_expression() + " " + sign + " " + s2.build_expression();
            }
        return "e";
    }

    // Представляет число в виде x = a sign b
    private Pair get_pair(char sign, int x, int df) {
        switch (sign) {
            case '+':
                return decompos_into_sum(x);
            case '-':
                return decompos_into_difference(x);
            case '/':
                return decompos_into_division(x, df);
            case '*':
                Pair t = decompos_into_mult(x);
                return t == null ? null : random.nextInt(2) == 0 ? t : t.x_change();
            default:
                return null;
        }
    }

    // Представление числа в виде произведения
    private Pair decompos_into_mult(int number) {
        int save = 0;
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0 && save != range) {
            save++;
            for (int i = 2; i < Math.floor(Math.sqrt(number)) + 1; i++)
                if (number % i == 0)
                    mArrayList.add(new Pair(i, number / i));
        }
        return (save == range) ?  null : mArrayList.get(random.nextInt(mArrayList.size()));
    }

    // Представление числа в виде деления
    private Pair decompos_into_division(int number, int df) {
        int save = 0;
        ArrayList<Pair> mArrayList = new ArrayList<>();
        while (mArrayList.size() == 0 && save != range) {
            save++;
            for (int i = 2; i < Math.round(Math.log10(df)) * 9; i++) {
                mArrayList.add(new Pair(i * number, i));
            }

        }
        return (save == range) ?  null : mArrayList.get(random.nextInt(mArrayList.size()));
    }

    // Представление числа в виде суммы
    private Pair decompos_into_sum(int number) {
        int save = 0;
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y = 0;
        while (mArrayList.size() == 0 && save != range) {
            save++;
            x = random.nextInt(number) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = number - x;
                if (Math.abs(x - 3 * y) < x / 2 && y != x - y)
                    mArrayList.add(new Pair(y, x));
            }

        }
        return (save == range) ?  null : mArrayList.get(random.nextInt(mArrayList.size()));
    }

    // Представление числа в виде разности
    private Pair decompos_into_difference(int number) {
        int save = 0;
        ArrayList<Pair> mArrayList = new ArrayList<>();
        int x, y;
        while (mArrayList.size() == 0 && save != range) {
            save++;
            x = random.nextInt(2 * number) + 1;
            for (int i = 1; i < x + 1; i++) {
                y = x - number;
                if (y > 0 && Math.abs(x - 3 * y) < x / 2)
                    mArrayList.add(new Pair(x, y));
            }

        }
        return (save == range) ?  null : mArrayList.get(random.nextInt(mArrayList.size()));
    }

}