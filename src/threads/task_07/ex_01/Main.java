package threads.task_07.ex_01;

import java.util.Arrays;

/**
 *
 * @author Yuzo Description: One-Dimensional Stencil - implementação sequencial.
 */
public class Main {

    public static void main(String[] args) {
        double[] list = {1, 2, 2, 2, 2, 2, 2, 2, 2, 1};
        System.out.println(Arrays.toString(sequential(1000, list)));
    }

    public static double[] sequential(int iter, double[] list) {
        double[] newList = list.clone();
        double[] valList = list.clone();
        for (int i = 1; i < iter; i++) {
            for (int j = 1; j < list.length - 1; j++) {
                newList[j] = (valList[j - 1] + valList[j + 1]) / 2.0;
            }
            double[] temp = newList;
            newList = valList;
            valList = temp;
        }
        return newList;
    }

}
