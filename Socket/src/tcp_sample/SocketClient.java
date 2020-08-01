package tcp_sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class SocketClient {

	private static final int STX_BYTE = 4;
	private static final int FUNCTION_BYTE = 16;
	private static final int CODE_BYTE = 4;
	private static final int DATA_LEN_BYTE = 4;
	private static final int ETX_BYTE = 4;
	private static final int MAX_RECONN_COUNT = 5;
	
	private static final String LOGIN= "1";
	private static final String NOTIFICATION = "2";
	private static final String HEART_BEAT = "3";
	
	private static final String WHOLE_SEND = "o";
	private static final String SEP_SEND = "s";
	
	private static final String LOGIN_FILE_NAME = "reqLogin.json";
	private static final String NOTI_FILE_NAME = "reqNotiEvent.json";
	private static final String HEARTBEAT_FILE_NAME = "reqCheckCommunication.json";
	
	public static void main(String[] args) throws Exception {
		SocketClient.run();
	}
	
	public static void run() throws IOException, InterruptedException {
		System.out.println(">>> CLIENT START");
		SocketClient sc = new SocketClient();
		String option;
		String host = "localhost";
		int port = 21502;
//		Scanner h = new Scanner(System.in);
//		System.out.println("input host");
//		host = h.nextLine();
//		System.out.println("input port");
//		port = h.nextInt();
		
		Scanner s = new Scanner(System.in);
		try {
			SocketChannel socketChannel = connect(host, port, 0);
			while(s.hasNext()) {
				option = s.nextLine();
				if ("exit".equals(option)) {
					System.out.println("Connection termination");
					if (socketChannel.isOpen()) {
						socketChannel.close();
					} else {
						System.out.println("already terminated channel");
					}
					break;
				} else if (
						LOGIN.equals(option)
						|| NOTIFICATION.equals(option)
						|| HEART_BEAT.equals(option)
				) {
					sc.send(socketChannel, option);
				}
			}
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public static SocketChannel connect (String host, int port, int count) throws IOException {
		System.out.println("Require Connection - host: [" + host + "] port: [" + port + "]");
		int i = 0;
		SocketChannel socketChannel = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			socketChannel.connect(new InetSocketAddress(host, port));
			while(!socketChannel.finishConnect()) {
			}
			System.out.println("Connection Pending ["+socketChannel.isConnectionPending()+"], Connection [" + socketChannel.isConnected() + "], Blocking Mode [" + socketChannel.isBlocking() + "]");
		} catch (IOException ioe) {
			if (count > MAX_RECONN_COUNT) {
				throw ioe;
			} else {
				SocketClient.connect(host, port, ++count);
			}
		}
		return socketChannel;
		
	}
	
	public void send(SocketChannel socketChannel, String type) throws IOException, InterruptedException {
//		System.out.println(">>>>> open : " + socketChannel.isOpen() + 
//				", connected : " + socketChannel.isConnected() + 
//				", ConnectionPending : " + socketChannel.isConnectionPending() + 
//				", blocking : " + socketChannel.isBlocking());
		
		Charset charset = Charset.forName("UTF-8");
		List<TestVO> datas = readDataWithJsonSimple(type);
		
		
		List<MessageByteDTO> dataBytes;
		for (TestVO testItem: datas) {
			System.out.println("==========================================================================");
			System.out.println("테스트 이름: " + testItem.getTestName());
			String testName = testItem.getTestName();
			Scanner s;
			String sendType;
			dataBytes = new ArrayList<>();

			if (testItem.getTestData().size() > 1) {
				while(true) {
					System.out.println("send at once : o, separating send: s");
					s = new Scanner(System.in);
					sendType = s.nextLine();
					if (WHOLE_SEND.equals(sendType) || SEP_SEND.equals(sendType)) {
						break;
					}
				}
				/**
				 * 수정필요
				 */
				 for (MessageDTO data: testItem.getTestData()) {
						 dataBytes.add(
							new MessageByteDTO(
									data.getSTX().getBytes(charset),
									data.getFunction().getBytes(charset),
									data.getCode().getBytes(charset),
									data.getData().getBytes(charset),
									data.getETX().getBytes(charset)
									));
				 }
				ByteBuffer buf;
				if (WHOLE_SEND.equals(sendType)) {
					buf = makeWholeSendByteBuffer(dataBytes);
					_send(testName, socketChannel, buf);
				}
				if (SEP_SEND.equals(sendType)) {
					for (MessageByteDTO byteDTO: dataBytes) {
//							 buf = ByteBuffer.allocate(STX_BYTE + FUNCTION_BYTE + CODE_BYTE + DATA_LEN_BYTE + ETX_BYTE + byteDTO.getData_bytes().length);
						buf = ByteBuffer.allocate(20000);
						 setByteBuffer(buf, byteDTO);
						 _send(testName, socketChannel, buf);
					}
				}
				
			} else {
				MessageDTO data = testItem.getTestData().get(0);
				MessageByteDTO byteDTO = new MessageByteDTO(
						data.getSTX() == null ? "".getBytes() : data.getSTX().getBytes(charset),
						data.getFunction().getBytes(charset),
						data.getCode().getBytes(charset),
						data.getData().getBytes(charset),
						data.getETX() == null ? "".getBytes() : data.getETX().getBytes(charset)
				);
//				ByteBuffer buf = ByteBuffer.allocate(STX_BYTE + FUNCTION_BYTE + CODE_BYTE + DATA_LEN_BYTE + ETX_BYTE + byteDTO.getData_bytes().length);
				ByteBuffer buf = ByteBuffer.allocate(20000);
				setByteBuffer(buf, byteDTO);
				_send(testName, socketChannel, buf);
			}
		}
	}
	public void _send(String testName, SocketChannel socketChannel, ByteBuffer buf) throws IOException, InterruptedException {
		Charset charset = Charset.forName("UTF-8");
		buf.flip();
//		fileWriter(charset.decode(buf).toString());
//		buf.flip();
		socketChannel.write(buf);
		
		Thread.sleep(1000);
		buf = ByteBuffer.allocate(4092);
		int respBytes = socketChannel.read(buf);
		buf.flip();
		String response = charset.decode(buf).toString();
		System.out.println("["+testName+"]" + respBytes + "[response] : " + response);
	}
	
	public void fileWriter(String text) {
		File file = new File( "data/log.txt");
		try {
			FileWriter fw = new FileWriter(file, true);
			fw.write(text);
			fw.write(System.getProperty("line.separator"));
			fw.close();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	public ByteBuffer makeWholeSendByteBuffer(List<MessageByteDTO> byteDatas) {
//		int buffSize = (STX_BYTE + FUNCTION_BYTE + CODE_BYTE + DATA_LEN_BYTE + ETX_BYTE) * byteDatas.size();
		int buffSize = 20000;
		for (MessageByteDTO mb: byteDatas) {
			buffSize += mb.getData_bytes().length;
		}
		
		ByteBuffer buf = ByteBuffer.allocate(buffSize);
		buf.order(ByteOrder.LITTLE_ENDIAN);
		for (MessageByteDTO byteData: byteDatas) {
			setByteBuffer(buf, byteData);
		}
		return buf;
	}
	
	public ByteBuffer setByteBuffer(ByteBuffer buf, MessageByteDTO dto) {
		buf.put(dto.getStx_bytes())
			.put(new byte[(STX_BYTE - dto.getStx_bytes().length) < 0 ? 0 : STX_BYTE - dto.getStx_bytes().length])
			.put(dto.getFunc_bytes())
			.put(new byte[FUNCTION_BYTE - dto.getFunc_bytes().length < 0 ? 0 : FUNCTION_BYTE - dto.getFunc_bytes().length])
			.put(dto.getCode_bytes())
			.put(new byte[CODE_BYTE - dto.getCode_bytes().length < 0 ? 0 : CODE_BYTE - dto.getCode_bytes().length])
			.put(dto.getDataLen_bytes())
			.put(new byte[DATA_LEN_BYTE - dto.getDataLen_bytes().length < 0 ? 0 : DATA_LEN_BYTE - dto.getDataLen_bytes().length])
			.put(dto.getData_bytes())
			.put(dto.getEtx_bytes())
			.put(new byte[ETX_BYTE - dto.getEtx_bytes().length < 0 ? 0 : ETX_BYTE - dto.getEtx_bytes().length]);
		
		return buf;
	}
	
	public static void resp(SocketChannel channel) throws IOException {
		ByteBuffer buf = ByteBuffer.allocate(4092);
		channel.read(buf);
		buf.flip();
		
		Charset charset = Charset.forName("UTF-8");
		String response = charset.decode(buf).toString();
		System.out.println(response);
		
	}
	
	public List<TestVO> readDataWithJsonSimple(String type) {
        JSONParser jsonParser = new JSONParser();
        List<TestVO> tvo = new ArrayList<>();
        List<MessageDTO> mdto = null;
        String loadFile = "";
        
        switch(type) {
        case LOGIN:
        	loadFile = LOGIN_FILE_NAME;
        	break;
        case NOTIFICATION:
        	loadFile = NOTI_FILE_NAME;
        	break;
        case HEART_BEAT:
        	loadFile = HEARTBEAT_FILE_NAME;
        	break;
        }
        String filePath_local = "data/" + loadFile;
        String filePath_test = "./test_data/" + loadFile;
        try {
        	FileInputStream input=new FileInputStream(filePath_test);
            InputStreamReader reader=new InputStreamReader(input, "UTF-8");
            BufferedReader br=new BufferedReader(reader);
            int i = 0;
            String line = null;
            StringBuilder sb = new StringBuilder();
            while((line = br.readLine()) != null) {
            	sb.append(line);
            }
            
//        	FileReader reader = new FileReader(filePath_local);
            Object obj = jsonParser.parse(sb.toString());
            JSONArray testItems = (JSONArray) obj;
            JSONObject testItem;
            JSONArray testData; 
            JSONObject data;
            
            for (i=0; i< testItems.size(); i++) {
            	mdto = new ArrayList<>();
            	testItem = (JSONObject) testItems.get(i);
            	testData = (JSONArray) testItem.get("testData");
            	for (int j = 0 ; j < testData.size(); j++) {
            		data = (JSONObject) testData.get(j);
            		MessageDTO dto = new MessageDTO(
            				(String) data.get("stx"),
            				(String) data.get("function"),
            				(String) data.get("code"),
            				String.valueOf(data.get("data").toString().length()),
            				data.get("data").toString(),
            				(String) data.get("etx"));
            		mdto.add(dto);
            	}
            	tvo.add(new TestVO((String) testItem.get("testName"), mdto));
            }
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        
        return tvo;
	}
}
