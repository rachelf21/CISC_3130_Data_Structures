public class factorial {

    public static void recursive(final int a, final int b, final int c) {
        if (a > 0) {
            recursive(a - 1, b, c);
            if (b > 0) {
                recursive(a, b - 1, c);
                if (c > 0) {
                    recursive(a, b, c - 1);
                    System.out.println(a + "-" + b + "-" + c);
                }
            }
        }
    }

    public static void basic_recursion(final int[] num) {
        if (num[num.length - 1] == 1) {
            int j = num[0];
            if (num[0] > 1) {
                num[0] = num[0] - 1;
                int[] numbers1= new int[4];
                for (int i = 0; i < 3; i++) {
                    numbers1[i] = num[i];
                }
                numbers1[3]=1;
               // int[] numbers1 = { num[0], num[1], num[2], 1 };
                basic_recursion(numbers1);
            }
            num[0] = j;
            int[] numbers2= new int[4];
            for (int i = 0; i < 3; i++) {
                numbers2[i] = num[i];
            }
            numbers2[3] = 2;
            //numbers2 = { num[0], num[1], num[2], 2 };
            basic_recursion(numbers2);
        }
        if (num[3] == 2) {
            int j = num[1];
            if (num[1] > 1) {
                num[1] = num[1] - 1;
                int[] numbers3 = { num[0], num[1], num[2], 2 };
                basic_recursion(numbers3);
            }
            num[1] = j;
            int[] numbers4 = { num[0], num[1], num[2], 3 };
            basic_recursion(numbers4);

        }
        if (num[3] == 3) {
            int j = num[2];
            if (num[2] > 0) {
                num[2] = num[2] - 1;
                int[] numbers5 = { num[0], num[1], num[2], 3 };
                basic_recursion(numbers5);
                num[2] = j;
                System.out.println(num[0] + "-" + num[1] + "-" + num[2]);
            }
        }
    }

    public static void recursive_a(final int a, final int b, final int c) {
        if (a > 0) {
            recursive_a(a - 1, b, c);
            recursive_b(a, b, c);
        }
    }

    public static void recursive_b(final int a, final int b, final int c) {
        if (b > 0) {
            recursive_b(a, b - 1, c);
            recursive_c(a, b, c);
        }
    }

    public static void recursive_c(final int a, final int b, final int c) {
        if (c > 0) {
            recursive_c(a, b, c - 1);
            System.out.println(a + "-" + b + "-" + c);
        }
    }

    public static void main(final String[] args) {
        // int num = 10;
        // System.out.println("Factorial of " + num + " is " + fact(num));
        // int num2=0;
        // num2 = 7;
        // System.out.println("Fibonacci number of " + num2 + " is " + fib(num2));

        recursive_a(3, 2, 3);
        System.out.println("----");
        final int[] numbers = { 3, 2, 3, 1 };
        basic_recursion(numbers);
        // recursive(2, 2, 2);
    }

    public static int fact(final int n) {
        int x, y;
        if (n == 1)
            return 1;
        x = n - 1;
        y = fact(x);
        return n * y;
    }

    public static int mult(final int x, final int y) {
        if (y == 1) {
            return x;
        }
        final int c = y - 1;
        final int d = mult(x, c);
        return (d + x);
    }

    public static int fib(final int n) {
        int x, y;
        if (n <= 1)
            return n;
        x = fib(n - 1);
        y = fib(n - 2);
        return (x + y);
    }

}