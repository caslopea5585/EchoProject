/*
 * �������α׷��̶�?
 * Ŭ���̾�Ʈ�� �޼����� �״�� �ٽ� �����ϴ� ����� ����!
 * ä�� ���� 1�ܰ�!
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
			server = new ServerSocket(port); //��������
			System.out.println("��������");
			
			Socket socket =server.accept(); //�����ڰ������� ���� ���� ��⿡ ����
			InetAddress inet  =socket.getInetAddress();
			String ip = inet.getHostAddress();
			
			System.out.println(ip + " �������ڹ߰�");
			
			//Ŭ���̾�Ʈ�� �����͸� �ޱ� ���� �Է½�Ʈ�� ���!
			//����Ʈ->���ڱ�� ->���۱��
			//����
			BufferedReader buffr=new BufferedReader(new InputStreamReader(socket.getInputStream()));
			//���ϱ��
			BufferedWriter buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			//Ŭ���̾�Ʈ�� �� ���
			String msg;
			while(true){ //��ȭ�� ��� ���������� ����ΰ� �Ʒ��� ���Ϲ��ȿ� ���������Ƿ�
								//���̻� �߰� �����ڿ� ���� ���� ����� �Ұ��ϴ�.
								//�ᱹ : ���� ���� ���� ���� ����� ����
				msg=buffr.readLine();
				buffw.write(msg+"\n"); //�� �ٺ�����
				buffw.flush();	//���ۺ���
				System.out.println("Ŭ���̾�Ʈ�� ���� �� = "+msg);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new EchoServer();
	}
}
