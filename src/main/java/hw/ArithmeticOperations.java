package hw;

public class ArithmeticOperations {

    /**
     * 0) применить несколько арифметических операций ( + , -, * , /) над двумя примитивами типа int
     * 1) применить несколько арифметических операций над int и double в одном выражении
     * 2) применить несколько логических операций ( < , >, >=, <= )
     * 3) прочитать про диапазоны типов данных для вещественных / чисел с плавающей точкой (какие максимальные и минимальные значения есть, как их получить) и переполнение
     * 4) получить переполнение при арифметической операции
     *
     */

    public static void main(String[] args) {
        operationsExampleInt(10, 0);
        operationsExampleIntDouble(10, 2.3);
        comparisonExample(10, 3);
        rangesExample();
        overflowExample();

    }

    public static void operationsExampleInt(int a, int b) {

        int sum = a + b;
        int dif = a - b;
        int multiplication = a * b;
        int division = b != 0 ? a / b : 0;
        int remainder = b != 0 ? a % b : 0;

        System.out.println("\n0) Арифметические операции над двумя int");
        System.out.println("Сумма чисел = " + sum);
        System.out.println("Разность чисел = " + dif);
        System.out.println("Умножение = " + multiplication);
        if (b == 0) {
            System.out.println("Делить на 0 нельзя!");
        } else {
            System.out.println("Деление = " + division);
        }
        System.out.println("Остаток от деления = " + remainder);

    }

    public static void operationsExampleIntDouble(int a, double b) {
        double sum = a + b;
        double dif = a - b;
        double mult = a * b;
        double expression = (a + b) * (a - b) / a;

        System.out.println("\n1) Арифметические операции над int и double в одном выражении");
        System.out.println("Сумма чисел = " + sum);
        System.out.println("Разность чисел = " + dif);
        System.out.println("Умножение = " + mult);

        if (b == 0.0) {
            System.out.println("Делить на 0.0 нельзя!");
        } else {
            System.out.println("Деление = " + (a / b));
        }

        if (a == 0) {
            System.out.println("Делить на a=0 нельзя!");
        } else if (b == 0.0) {
            System.out.println("Выражение: b=0, результат = " + expression);
        } else {
            System.out.println("Выражение ((a+b)*(a-b))/a = " + expression);
        }
    }

    public static void comparisonExample(int a, int b) {
        System.out.println("\n2) Логические операции сравнения");
        System.out.println("a=" + a + ", b=" + b);
        System.out.println("a > b = " + (a > b));
        System.out.println("a >= b = " + (a >= b));
        System.out.println("a < b = " + (a < b));
        System.out.println("a <= b = " + (a <= b));

    }

    public static void rangesExample() {
        System.out.println("\n3) Диапазоны типов данных");
        System.out.println("Double.MIN = " + Double.MIN_VALUE);
        System.out.println("Double.MAX = " + Double.MAX_VALUE);
        System.out.println("Float.MIN = " + Float.MIN_VALUE);
        System.out.println("Float.MAX = " + Float.MAX_VALUE);
    }

    public static void overflowExample() {
        System.out.println("\n4) Переполнение при арифметической операции");
        int max = Integer.MAX_VALUE - 5;
        int overflow = max + 10;
        System.out.println("MAX-5 = " + max);
        System.out.println("+10 = " + overflow + " (получили переполнение)");
    }

}
