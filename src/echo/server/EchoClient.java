package echo.server;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class EchoClient extends JFrame {
	JTextArea area;
	JScrollPane scroll;
	JPanel p_south;
	JTextField t_input;
	JButton bt_connect;
	Socket socket; //��ȭ�� ������ �ִ� ����!!
						//���ɶ� - �������κ��� ��½�Ʈ���� �̾Ƴ����~
						//�������� - �������κ��� �Է½�Ʈ���� �̾Ƴ��� ��~
	
	BufferedReader buffr; //û���
	BufferedWriter buffw; //�� �ɱ��
	
	public EchoClient() {
		area = new JTextArea();
		scroll = new JScrollPane(area);
		p_south=new JPanel();
		t_input = new JTextField(10);
		bt_connect = new JButton("����");
		
		p_south.add(t_input);
		p_south.add(bt_connect);
		
		add(scroll, BorderLayout.CENTER);
		add(p_south,BorderLayout.SOUTH);
		
		bt_connect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect();
			}
		});
		
		t_input.addKeyListener(new KeyAdapter() {
			
			public void keyReleased(KeyEvent e) {
				//����ġ��
				int key = e.getKeyCode();
				if(key==KeyEvent.VK_ENTER){
					send();
				}
			
			}
		});
		
		setSize(300,400);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	
	
	//������ �����ϴ� �޼���
	public void connect(){
		try {
			socket= new Socket("localhost", 7777);
			//������ �Ϸ�Ǿ�����, ��Ʈ�� ������(��,���)
			//��??? ��ȭ�� �������ϱ�..����Ǹ��ڷ� ������� �̷�����ϱ�.
			buffr= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			buffw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
				
	}
	
	//������ �� ������
	public void send(){
		//�ؽ�Ʈ������ �޼��� �� ���
		String msg = t_input.getText();
		
		try {
			buffw.write(msg+"\n"); //��Ʈ���� ���� ���!
									//��! ������ ���Ͽ� ������ ����
			buffw.flush(); //���ۿ� ������������ �� �����͸� �� �������� ���.
			t_input.setText("");
			
			//�������� ���ƿ� �޼����� area�� ����غ���!!
			String data = buffr.readLine();
			area.append(data+"\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		new EchoClient();
	}
}
