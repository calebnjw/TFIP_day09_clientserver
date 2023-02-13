package app;

import java.util.Random;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
  private App() {
  }

  public static void main(String[] args) {
    Random random = new Random();
    Integer randomNum = random.nextInt(100);
    Integer guess = 0;

    Scanner scanner = new Scanner(System.in);
    while (guess != randomNum) {
      guess = scanner.nextInt();

      if (guess < randomNum) {
        System.out.println("Guess a bigger number.");
      } else if (guess > randomNum) {
        System.out.println("Guess a smaller number.");
      } else {
        System.out.println("You got it!");
        scanner.close();
      }
    }
  }
}
