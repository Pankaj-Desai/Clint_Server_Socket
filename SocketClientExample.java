import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
public class SocketClientExample
{
public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException
{
//get the local host IP address, if server is running on some other IP, you need to use that
InetAddress host = InetAddress.getLocalHost();
Socket socket = null;
ObjectOutputStream oos = null;
ObjectInputStream ois = null;
for(int i=0; i<5;i++){
socket = new Socket(host.getHostName(), 1234); //establish socket connection to server
oos = new ObjectOutputStream(socket.getOutputStream()); //write to socket using ObjectOutputStream
System.out.println("Sending request to Socket Server");
if(i==4)oos.writeObject("exit");
else oos.writeObject(""+i);
ois = new ObjectInputStream(socket.getInputStream()); //read the server response message
String message = (String) ois.readObject();
System.out.println("Message: " + message);
ois.close(); //close resources
oos.close();
Thread.sleep(100);
}
}
}