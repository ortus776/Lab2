import java.io.PrintStream;
import java.util.Scanner;

public class main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;
    public static void main(String[] args){
        int x, y, z;
        do {
            out.println("Введите корректные размеры массива (x, y, z)");
            x = in.nextInt();
            y = in.nextInt();
            z = in.nextInt();
        }
        while(x < 1 || y < 1 || z < 1);

        double[][][] arr = new double[z][y][x];
        out.println("Введите элементы массива");
        for(int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    arr[i][j][k] = in.nextDouble();
                }
            }
        }
        for(int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                // сортировка
                boolean swapped = false;
                double temp;
                for (int a = 0; a < x * 2; a++){ // x * 2 потому что после сортировки по дробной части x могло не хватать и оставались неотсортированные по целой части числа;
                    for(int b = 0; b < x - 1;b++){
                        // по убыванию дробной части
                        if (arr[i][j][b] % 1 < arr[i][j][b + 1] % 1){
                            temp = arr[i][j][b + 1];
                            arr[i][j][b + 1] = arr[i][j][b];
                            arr[i][j][b] = temp;
                            swapped = true;
                        }
                        // если дробные части равны, то сортируем по целой
                        else{
                            if (arr[i][j][b] % 1 == arr[i][j][b + 1] % 1 && arr[i][j][b] > arr[i][j][b + 1]){
                                temp = arr[i][j][b + 1];
                                arr[i][j][b + 1] = arr[i][j][b];
                                arr[i][j][b] = temp;
                                swapped = true;
                            }
                        }
                    }
                    if (!swapped){
                        break;
                    }
                }
            }
        }
        for (int i = 0; i < z; i++){
            // находим среднее геометрическое каждого слоя
            double geom = 1; // среднее геометрическое
            for (int j = 0; j < y; j++){
                for(int k = 0; k < x; k++){
                    geom *= arr[i][j][k];
                }
            }
            geom = Math.pow(geom, 1/(double)(x*y));
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
        for(int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    arr[i][j][k] = (double)Math.round(arr[i][j][k] * 100d) / 100d; // округление до 2 знаков
                }
            }
        }
        // вывод массива после округления
        for (int i = 0; i < z; i++){
            for (int j = 0; j < y; j++){
                for (int k = 0; k < x; k++){
                    out.printf("%d, %d, %d : %f\n", k, j, i, arr[i][j][k]);
                }
            }
        }
    }
}
