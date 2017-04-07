import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Character> domain = new ArrayList<Character>();
        domain.add('+');
        domain.add('-');
        domain.add('/');
        domain.add('*');
        Expressions expressions = new Expressions();
        for(int i = 0; i < 10; i++){
            Pair expr = expressions.get_expression(3, 25, 1500, domain);
            String expression = (String) expr.getx();
            System.out.println(expression + " = " + expr.gety() + " | " + expressions.calculateExpression(expression));
        }

    }
}
