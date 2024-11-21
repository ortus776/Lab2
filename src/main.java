import java.io.PrintStream;
import java.util.Scanner;

public class main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args){
        int x, y, z;
        // пользователь вводит элементы пока они не станут корректными
        do {
            out.println("Введите корректные размеры массива (x, y, z)");
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
        }
        while(x < 1 || y < 1 || z < 1);

        double[][][] arr = new double[z][y][x];
        out.println("Введите элементы массива");
        // вводим элементы массива
        for(int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    arr[i][j][k] = in.nextDouble();
                }
            }
        }
        // перебираем элементы массива, фиксируем arr[i][j] и сортируем его элементы
        for(int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                // сортировка
                double temp;
                for (int a = 0; a < x * 2; a++){ // x * 2 потому что после сортировки по дробной части x могло не хватать и оставались неотсортированные по целой части числа;
                    boolean swapped = false; // поменялись ли 2 элемента местами за проход по массиву?
                    for(int b = 0; b < x - 1;b++){
                        // если дробная часть arr[i][j][b] больше arr[i][[j][b] или дробные части равны и arr[i][j][b] больше arr[i][j][b + 1]
                        if (arr[i][j][b] % 1 < arr[i][j][b + 1] % 1 || arr[i][j][b] % 1 == arr[i][j][b + 1] % 1 && arr[i][j][b] > arr[i][j][b + 1]){
                            // меняем их местами
                            temp = arr[i][j][b + 1];
                            arr[i][j][b + 1] = arr[i][j][b];
                            arr[i][j][b] = temp;
                            swapped = true; //  пара элементов поменялась местами
                        }
                    }
                    if (!swapped){ // если ни одна пара элементов не поменялась местами, то завершаем цикл
                        break;
                    }
                }
            }
        }
        // находим среднее геометрическое каждого слоя
        for (int i = 0; i < z; i++){  // фиксируем массив по z
            double geom = 1; // среднее геометрическое
            for (int j = 0; j < y; j++){
                for(int k = 0; k < x; k++){
                    geom *= arr[i][j][k];
                }
            }
            geom = Math.pow(geom, 1/(double)(x*y)); // берем от geom корень степни x*y (количество элементов в слое)
            out.println(geom);
        }
        // вывод массива
        for (int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    out.printf("%d, %d, %d : %f\n", k, j, i, arr[i][j][k]);
                }
            }
        }
        // проходимся по всем элементам массива
        for(int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    arr[i][j][k] = (double)Math.round(arr[i][j][k] * 100d) / 100d; // округляем до 2 знаков после запятой
                }
            }
        }
        out.println(); // разделение между выводами массивов
        // вывод массива после округления
        for (int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    out.print(arr[i][j][k] + " ");
                }
                out.println();
            }
            out.println();
        }
    }
}
