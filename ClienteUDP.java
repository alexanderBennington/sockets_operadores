import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 12345;

        try (DatagramSocket socket = new DatagramSocket()) {
            String mensaje = "10.5 2.5 +";
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
