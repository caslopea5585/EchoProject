/*
 * �ڹٸ� �̿��Ͽ� ������ ���α׷��� �ۼ��Ѵ�. 
 * */
package echo.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer {
	//��ȭ�� ���������� ������ �˷��ֱ� ���� ��ü!! ��, ���� ��ȭ�� �� ������!
	//������ Ŭ���̾�Ʈ�� ã�ƿ��� ��ٸ��Ƿ� Ŭ���̾�Ʈ�� ����� ��Ʈ��ȣ�� �����ϸ�ȴ�.
	//��Ʈ��ȣ�� ���Ǵ�� ������ �� ������
	//1.0~1023�� ���ϴ� ���� ���� ��? �ý����� �̹� �����ϰ� �ְ� ����
	//2.������ ���α׷��� ������(Ex- 1521 ����Ŭ, 3306 mysql ,80 web)
	ServerSocket server;
	Socket socket;
	int port =8888;
	public MyServer() {
		try {
			server = new ServerSocket(port);
			System.out.println("��������");

			//Ŭ���̾�Ʈ�� ������ ��ٸ�..
			//������ ���������� ���Ѵ��.. �� ����!!
			//��ġ Stream�� read()�迭�� ����!
			while(true){
				socket=server.accept(); //�Ѹ� �ް� ��������..while������ �������ʾƾ���
				System.out.println("���ӹ߰�!");
				
				//������ �̿��Ͽ� �����͸� �ް��� �ϴ� ���� �Է½�Ʈ����..
				//�����͸� ���������ϴ� ��쿣 ��½�Ʈ��...
				
				InputStream is= socket.getInputStream();
				InputStreamReader reader = null;
				reader = new InputStreamReader(is,"utf-8");
				
				int data;
				
				while(true){
					data =reader.read(); //1byte �о����.. --> ���� 2����Ʈ��
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
