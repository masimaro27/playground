package tcp_sample;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class SocketServer {
	
	private static final int STX_BYTE = 4;
	private static final int FUNCTION_BYTE = 16;
	private static final int CODE_BYTE = 4;
	private static final int DATA_LEN_BYTE = 4;
	private static final int ETX_BYTE = 4;
	
	public static void main(String[] args) throws Exception {
		SocketServer.run();
	}
	
	public static void run() throws IOException {
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		serverSocketChannel.configureBlocking(true);
		serverSocketChannel.bind(new InetSocketAddress(21502));
		
		SocketChannel socketChannel = serverSocketChannel.accept();
		while (true) {
			InetSocketAddress inetSocketAddress = (InetSocketAddress) socketChannel.getRemoteAddress();
			Charset charset = Charset.forName("UTF-8");
			
			ByteBuffer byteBuffer = ByteBuffer.allocate(2048);
			socketChannel.read(byteBuffer);
			
			byteBuffer.flip();
			System.out.println("Received Data : " + charset.decode(byteBuffer).toString());
			
			byteBuffer.flip();
			socketChannel.write(byteBuffer);
			System.out.println("Sending Success");
//			System.out.println("open : " + socketChannel.isOpen() + ", connected : " + socketChannel.isConnected() + ", ConnectionPending : " + socketChannel.isConnectionPending() + ", socketChannel.isBlocking() : " + socketChannel.isBlocking());
		}
	}
}
