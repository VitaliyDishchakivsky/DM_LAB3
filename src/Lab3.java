import java.util.Arrays;
import java.io.*;
import java.util.Scanner;

public class Lab3 {

	// Шукає найменший цикл Гамільтона за допомогою методу бектрекінгу
    private static int tsp(int[][] graph, boolean[] visited, int current, int n, int count, int cost, int ans, int[] path) {
    	    // Якщо всі вузли відвідані, і є зв'язок назад до початкового вузла, оновлюємо мінімальну вагу
    	    if (count == n && graph[current][0] > 0) {
    	        ans = Math.min(ans, cost + graph[current][0]);
    	        // Друкуємо відвідані вузли в порядку циклу Гамільтона
    	        System.out.print("1");
    	        for (int i = 1; i < n; i++) {
    	            System.out.print(" -> " + (path[i]+1));
    	        }
    	        System.out.println(" -> 1");
    	        return ans;
    	    }

    	    // Проходимо по всіх суміжних вузлах і рекурсивно перевіряємо, чи вони ведуть до кращого розв'язку
    	    for (int i = 0; i < n; i++) {
    	        if (!visited[i] && graph[current][i] > 0) {
    	            visited[i] = true;
    	            path[count] = i;
    	            ans = tsp(graph, visited, i, n, count + 1, cost + graph[current][i], ans, path);
    	            visited[i] = false;
    	            path[count] = -1;
    	        }
    	    }

    	    return ans;
    	}

    	public static void main(String[] args) throws IOException {

    	    // Визначаємо граф і кількість вузлів
    		//n - розмірність читаємої матриці
            int n;
            //Оголошення матриці ваг
            int graph[][];
            //Читання матриці з файлу
            File f = new File("c:\\matrix.txt");
            Scanner sc = new Scanner(f);
            n = sc.nextInt();
            graph  = new int[n][n];
            
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                	 graph [i][j] = sc.nextInt();
            //Закриття файлу
            sc.close();

            System.out.println("Матриця ваг:");
            for (int i = 0; i <  graph .length; i++) {
                for (int j = 0; j <  graph [i].length; j++) {
                    System.out.printf("%4s\t",  graph [i][j]);
                }
                System.out.println();
            }
    	

    	    // Позначаємо початковий вузол відвіданим і ініціалізуємо відповідь до максимального значення цілочисельного типу
    	    boolean[] visited = new boolean[n];
    	    Arrays.fill(visited, false);
    	    visited[0] = true;
    	    int ans = Integer.MAX_VALUE;
    	    int[] path = new int[n];
    	    Arrays.fill(path, -1);
    	    path[0] = 0;

    	    // Шукаємо найменший цикл Гамільтона, використовуючи функцію tsp
    	    System.out.println("\nМінімальний цикл Гамільтона: ");
    	    ans = tsp(graph, visited, 0, n, 1, 0, ans, path);

    	    // Виводимо результат
    	    System.out.println("\nСумарна вага пройденого маршруту: " + ans);
    	}

}
