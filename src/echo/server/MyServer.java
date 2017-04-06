/*
 * 자바를 이용하여 서버측 프로그램을 작성한다. 
 * */
package echo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//대화를 나누기전에 접속을 알려주기 위한 객체!! 즉, 아직 대화는 못 나눈다!
	//서버는 클라이언트가 찾아오길 기다리므로 클라이언트와 약속한 포트번호만 보유하면된다.
	//포트번호는 임의대로 지정할 수 있으나
	//1.0~1023은 피하는 것이 좋다 왜? 시스템이 이미 점유하고 있개 때문
	//2.유명한 프로그램은 피하자(Ex- 1521 오라클, 3306 mysql ,80 web)
	ServerSocket server;
	Socket socket;
	int port =8888;
	public MyServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("서버생성");

			//클라이언트의 접속을 기다림..
			//접속이 있을때까지 무한대기.. 즉 지연!!
			//마치 Stream의 read()계열과 같다!
			while(true){
				socket=server.accept(); //한명만 받고 끝나버림..while문으로 죽이지않아야함
				System.out.println("접속발견!");
				
				//소켓을 이용하여 데이터를 받고자 하는 경우는 입력스트림을..
				//데이터를 보내고자하는 경우엔 출력스트림...
				
				InputStream is= socket.getInputStream();
				InputStreamReader reader = null;
				reader = new InputStreamReader(is,"utf-8");
				
				int data;
				
				while(true){
					data =reader.read(); //1byte 읽어들임.. --> 이젠 2바이트씩
					System.out.print((char)data);
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
	public static void main(String[] args) {
		new MyServer();
	}
}
