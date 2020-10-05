import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ClassNotFoundException;
import java.net.ServerSocket;
import java.net.Socket;
public class SocketServerExample
{
private static ServerSocket server; //static ServerSocket variable
private static int port = 1234; //socket server port on which it will listen
public static void main(String args[]) throws IOException, ClassNotFoundException
{
server = new ServerSocket(port); //create the socket server object
//keep listens indefinitely until receives 'exit' call or program terminates
while(true)
{
System.out.println("Waiting for the crequest");
Socket socket = server.accept(); //creating socket and waiting for client connection
ObjectInputStream ois = new ObjectInputStream(socket.getInputStream()); //read from socket to ObjectInputStream object
String message = (String) ois.readObject(); //convert ObjectInputStream object to String
System.out.println("Message Received: " + message);
ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream()); //create ObjectOutputStream object
oos.writeObject("Hi Client "+message); //write object to Socket
ois.close(); //close resources
oos.close();
socket.close();
if(message.equalsIgnoreCase("exit")) break; //terminate the server if client sends exit request
}
System.out.println("Shutting down Socket server!!");
server.close(); //close the ServerSocket object
}
}