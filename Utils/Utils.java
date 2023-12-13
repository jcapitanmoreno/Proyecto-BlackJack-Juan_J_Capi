package Utils;

import java.util.Scanner;


/**
 * Método para solicitar y validar una opción de menú desde la entrada estándar.
 *
 * min: El valor mínimo permitido.
 * max: El valor máximo permitido.
 *
 * Devuelve La opción válida ingresada por el usuario.
 */
public class Utils {
    public int scOptions(int min, int max){
        Scanner teclado = new Scanner(System.in);
        int option = 0;
        boolean valid = false;

        while (!valid) {
            System.out.println("indica una opcion valida: ");
            //hasNextInt devuelve true si lo siguiente introducido es un número
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
