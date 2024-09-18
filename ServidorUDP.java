import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket(12345)) {
            byte[] buffer = new byte[1024];
            System.out.println("Servidor UDP escuchando en el puerto 12345...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                socket.receive(packet);

                String mensaje = new String(packet.getData(), 0, packet.getLength());
                String[] partes = mensaje.split(" ");
                double num1 = Double.parseDouble(partes[0]);
                double num2 = Double.parseDouble(partes[1]);
                char operador = partes[2].charAt(0);

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
                            resultado = Double.NaN; // División por cero
                        }
                        break;
                    default:
                        System.out.println("Operador no válido");
                        continue;
                }

                String respuesta = Double.toString(resultado);
                byte[] bufferRespuesta = respuesta.getBytes();
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                DatagramPacket packetRespuesta = new DatagramPacket(bufferRespuesta, bufferRespuesta.length, address,
                        port);
                socket.send(packetRespuesta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
