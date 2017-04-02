import java.util.Random;

public class Main {
    public static void main(String[] args) {
        final Random random = new Random(System.currentTimeMillis());

        Expressions expressions = new Expressions();
        for(int i = 0; i < 10; i++){
            int x = random.nextInt(100 - 25) + 25;
             System.out.print(expressions.get_expression(3, x) + " = " + x + "\n");
        }
           
    }
}