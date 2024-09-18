import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto);
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                DataInputStream in = new DataInputStream(socket.getInputStream())) {

            Scanner scanner = new Scanner(System.in);

            // Pedir los números y el operador al usuario
            System.out.print("Introduce el primer número: ");
            double num1 = scanner.nextDouble();
            System.out.print("Introduce el segundo número: ");
            double num2 = scanner.nextDouble();
            System.out.print("Introduce el operador (+, -, *, /): ");
            char operador = scanner.next().charAt(0);

            // Enviar los números y el operador al servidor
            out.writeDouble(num1);
            out.writeDouble(num2);
            out.writeChar(operador);

            // Leer el resultado del servidor
            double resultado = in.readDouble();
            System.out.println("Resultado: " + resultado);

        } catch (IOException e) {
            System.out.println("Error en la comunicación con el servidor: " + e.getMessage());
        }
    }
}
