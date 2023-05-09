public class Main {

    public static void main(String[] args) {
        int[] array = new int[10];

        for (int i=0; i< array.length; i++){
            array[i] = i+1+(i+1)*10+(i+1)*100;
        }

        for (int i=0; i< array.length; i++){
            System.out.println("чсло " + array[i] + ((array[i]%2 > 0) ? " непарне" : " парне" ) );
        }

    }
}