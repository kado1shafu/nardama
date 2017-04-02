import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    public static void main(String[] args) {
        final Random random = new Random(System.currentTimeMillis());
        ArrayList<Character> domain = new ArrayList<Character>();
        //domain.add('+');
        domain.add('-');
        domain.add('/');
        domain.add('*');
        ArrayList<Character> a;
        Expressions expressions = new Expressions();
        for(int i = 0; i < 10; i++)
            System.out.print(expressions.get_expression(3, 25, 100, domain) + "\n");
           
    }
}