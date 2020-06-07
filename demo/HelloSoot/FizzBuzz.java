public class FizzBuzz {

    public void printFizzBuzz(int k) {
//        if (k%15==0)
//            System.out.println("FizzBuzz");
//        else if (k%5==0)
//            System.out.println("Buzz");
//        else if (k%3==0)
//            System.out.println("Fizz");
//        else
//            System.out.println(k);
//        k = 6;

//        for (int i = 0; i < k; i++) {
//            for (int j = 0; j < k; j++) {
//                for (int m = 0; j < k; m++)
//                    if (i + j == 6) return;
//            }
//        }
        int m = 0;
        switch (k) {
            case 3:
                m = 3;
                break;
            case 4:
                m = 2;
                break;
            case 5:
                m++;
                break;
        }
    }

    public void fizzBuzz(int n) {
        for (int i = 1; i <= n; i++)
            printFizzBuzz(i);
    }
}

