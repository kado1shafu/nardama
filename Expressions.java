import java.util.ArrayList;
import java.util.Random;

public class Expressions {

    private Random random = new Random();

    public String get_expression(int numb_of_signs, int from, int to, ArrayList<Character> domain) {
        // корректность
        numb_of_signs = Math.abs(numb_of_signs);
        if(numb_of_signs > 3) numb_of_signs = 3;
        if(domain == null) domain = new ArrayList<>();
        if(domain.size() == 0) { domain.add('+');domain.add('-');domain.add('/');domain.add('*'); }
        if(from >= to)
            to = from * 2;
        // функция
        ArrayList<Character> signs = get_signs(numb_of_signs, domain);
        String s;
        int x;
        do{
            x = random.nextInt(to - from) + from + 1;
            s = new Algebraic(signs, x).build_expression();
        }
        while (s.contains("e") == true);
        return s + " = " + x;
    }

    private ArrayList<Character> get_signs(int n, ArrayList<Character> old_signs) {
        ArrayList<Character> result = new ArrayList<Character>(old_signs);
        for(int i = 4; i != n; i--){
            int pos = random.nextInt(result.size());
            result.remove(pos);
        }
        return result;
    }

}