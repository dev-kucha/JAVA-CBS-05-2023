public class Main {

    public static void main(String[] args) {

        float number = 3;
        int numIterations = 180;
        float result = 1;

        System.out.println("1 варіант рішення");
        for (int i=1; i < numIterations; i++) {
            result = result * number;
//            System.out.println(result + " result");
            if (result >= 1000) {
                System.out.println(i + " ітерацій");
                break;
            }
        }
        System.out.println("***********************");
        result = 1;
        int i=0;

        System.out.println("2 варіант рішення");
        while (result < 1000) {
            result = result * number;
//            System.out.println(result + " result");
            i++;
            }
        System.out.println(i + " ітерацій");
        }




}