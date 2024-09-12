import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (Socket socket = new Socket(host, puerto);
             DataOutputStream out = new DataOutputStream(socket.getOutputStream());
             DataInputStream in = new DataInputStream(socket.getInputStream())) {

            // Enviar los numeros y el operador al servidor
            double num1 = 10.5;
            double num2 = 2.5;
            char operador = '+';

            out.writeDouble(num1);
            out.writeDouble(num2);
            out.writeChar(operador);

            // Leer el resultado del servidor
            double resultado = in.readDouble();
            System.out.println("Resultado: " + resultado);

        } catch (IOException e) {
            System.out.println("Error en la comunicacion con el servidor: " + e.getMessage());
        }
    }
}
