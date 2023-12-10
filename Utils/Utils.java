package Utils;

import java.util.Scanner;

public class Utils {
    public int scOptions(int min, int max){
        Scanner teclado = new Scanner(System.in);
        int option = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println("indica una opcion valida: ");
            if (teclado.hasNextInt()) {
                option = teclado.nextInt();
                if (option >= min && option <= max) {
                    valid = true;
                } else {
                    valid = false;
                }

            } else {
                System.out.println("Solo es posible introducir los numero validos");
                teclado.next();
                valid = false;
            }
        }
        return option;
    }
}
