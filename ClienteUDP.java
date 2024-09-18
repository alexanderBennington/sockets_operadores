import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            Scanner scanner = new Scanner(System.in);

            // Pedir los números y el operador al usuario
            System.out.print("Introduce el primer número: ");
            double num1 = scanner.nextDouble();
            System.out.print("Introduce el segundo número: ");
            double num2 = scanner.nextDouble();
            System.out.print("Introduce el operador (+, -, *, /): ");
            char operador = scanner.next().charAt(0);

            // Crear el mensaje a enviar
            String mensaje = num1 + " " + num2 + " " + operador;
            byte[] buffer = mensaje.getBytes();
            InetAddress address = InetAddress.getByName(host);

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, address, puerto);
            socket.send(packet);

            byte[] bufferRespuesta = new byte[1024];
            DatagramPacket packetRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length);
            socket.receive(packetRespuesta);

            String respuesta = new String(packetRespuesta.getData(), 0, packetRespuesta.getLength());
            System.out.println("Resultado: " + respuesta);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
