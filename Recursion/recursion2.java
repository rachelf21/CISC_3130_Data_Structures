public class recursion2 {

    // //This does not work
    // public static void recursive(final int a, final int b, final int c) {
    //     if (a > 0) {
    //         recursive(a - 1, b, c);
    //         if (b > 0) {
    //             recursive(a, b - 1, c);
    //             if (c > 0) {
    //                 recursive(a, b, c - 1);
    //                 System.out.println(a + "-" + b + "-" + c);
    //             }
    //         }
    //     }
    // }

    public static void basic_recursion(final int[] num) {
        int len = num.length;
        int last_pos=len-1;
        int count = num[last_pos];
        int j = num[num[last_pos]-1];

        if (j > 1) {
            num[num[last_pos]-1] = j - 1;
            int[] numbers1 = new int[len];
            for (int i = 0; i < last_pos; i++) {
                numbers1[i] = num[i];
            }
            numbers1[last_pos] = count;
            basic_recursion(numbers1);
        }
        num[num[last_pos]-1] = j;

        if(num[last_pos] != last_pos) {
            int[] numbers2 = new int[len];
            for (int i = 0; i < last_pos; i++) {
                numbers2[i] = num[i];
            }
            numbers2[last_pos] = ++count;
            basic_recursion(numbers2);
        }
        else{
            num[last_pos-1] = j;
            int[] numbers_final=new int[len-1];
            for (int i = 0; i < last_pos; i++) {
                numbers_final[i] = num[i];
                System.out.print(numbers_final[i]);
            }
            System.out.println();
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
        recursive_a(2, 1, 3);  //Using 3 different recursive functions.
        System.out.println("-----");
        final int[] numbers = {2, 1, 3, 1};  //Using 1 recursive function, which automatically calculates the appropriate depth, 
        //based on the last value in the array that is passed. In other words, this example is a 3 dimensional array (2x1x3)that is 
        //passed, where the last digit (1) is not a part of the actual array itself.
        basic_recursion(numbers);
    }
}