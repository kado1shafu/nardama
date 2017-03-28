import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final Random random = new Random(System.currentTimeMillis());

        Expressions expressions = new Expressions();
        System.out.print(expressions.get_expression(1, 50));

    }

}