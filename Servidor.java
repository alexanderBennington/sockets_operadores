import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(12345)) {
            System.out.println("Servidor escuchando en el puerto 12345...");
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     DataInputStream in = new DataInputStream(clientSocket.getInputStream());
                     DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream())) {

                    System.out.println("Cliente conectado.");

                    // Leer los numeros y el operador del cliente
                    double num1 = in.readDouble();
                    double num2 = in.readDouble();
                    char operador = in.readChar();

                    // Realizar la operacion
                    double resultado = 0;
                    switch (operador) {
                        case '+':
                            resultado = num1 + num2;
                            break;
                        case '-':
                            resultado = num1 - num2;
                            break;
                        case '*':
                            resultado = num1 * num2;
                            break;
                        case '/':
                            if (num2 != 0) {
                                resultado = num1 / num2;
                            } else {
                                out.writeUTF("Error: Division por cero");
                                continue;
                            }
                            break;
                        default:
                            out.writeUTF("Error: Operador no valido");
                            continue;
                    }

                    // Enviar el resultado al cliente
                    out.writeDouble(resultado);
                } catch (IOException e) {
                    System.out.println("Error en la comunicacion con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error al iniciar el servidor: " + e.getMessage());
        }
    }
}
