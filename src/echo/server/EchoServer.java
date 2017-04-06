/*
 * 에코프로그램이란?
 * 클라이언트의 메세지를 그대로 다시 전달하는 방식의 서버!
 * 채팅 기초 1단계!
 * 
 * */
package echo.server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	ServerSocket server;
	int port=7777;
	
	
	public EchoServer() {
		try {
			server = new ServerSocket(port); //서버생성
			System.out.println("서버생성");
			
			Socket socket =server.accept(); //접속자가있을때 까지 무한 대기에 빠짐
			InetAddress inet  =socket.getInetAddress();
			String ip = inet.getHostAddress();
			
			System.out.println(ip + " 님접속자발견");
			
			//클라이언트의 데이터를 받기 위해 입력스트림 얻기!
			//바이트->문자기반 ->버퍼기반
			//듣기용
			BufferedReader buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//말하기용
			BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//클라이언트의 말 듣기
			String msg;
			while(true){ //대화는 계속 가능하지만 실행부가 아래의 와일문안에 갇혀있으므로
								//더이상 추가 접속자에 대한 접속 허용은 불가하다.
								//결국 : 최초 가장 빨리 들어온 사람용 서버
				msg=buffr.readLine();
				buffw.write(msg+"\n"); //한 줄보내기
				buffw.flush();	//버퍼비우기
				System.out.println("클라이언트가 보낸 말 = "+msg);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
