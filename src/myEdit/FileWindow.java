package myEdit;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class FileWindow extends JFrame implements ActionListener,Runnable {

	
	Thread compiler = null;
	Thread run_prom = null;
	boolean bn = true;
	CardLayout mycard; //��������
	JButton button_input_txt,    //��ť�Ķ���
	        button_compiler_text,
	        button_compiler,
	        button_run_prom,
	        button_see_doswin;
	
	JPanel p = new JPanel();
	JTextArea input_text = new JTextArea();   //����������
	JTextArea compiler_text = new JTextArea(); //���������ʾ��
	JTextArea dos_out_text = new JTextArea();  //����������Ϣ
	
	JTextField input_file_name_text = new JTextField();
	JTextField run_file_name_text = new JTextField();
	
	public FileWindow() {
		super("java���Ա�����");
		mycard = new CardLayout();
		compiler = new Thread(this);
		run_prom = new Thread(this);
		button_input_txt = new JButton("��������������ɫ��");
		button_compiler_text = new JButton("�����������ۺ�ɫ��");
		button_see_doswin = new JButton("�������н����ǳ��ɫ��");
		button_compiler = new JButton("�������");
		button_run_prom = new JButton("���г���");
		
		p.setLayout(mycard);  //���ÿ�Ƭ����
		p.add("input",input_text);  //���忨Ƭ����
		p.add("compiler",compiler_text);
		p.add("dos",dos_out_text);
		add(p,"Center");
		
		compiler_text.setBackground(Color.pink);  //������ɫ
		dos_out_text.setBackground(Color.cyan);
		JPanel p1 = new JPanel();
		
		p1.setLayout(new GridLayout(3,3)); //���ñ�񲼾�
		//������
		
		p1.add(button_input_txt);
		p1.add(button_compiler_text);
		p1.add(button_see_doswin);
		p1.add(new JLabel("����������ļ�����.java����"));
		p1.add(input_file_name_text);
		p1.add(button_compiler);
		p1.add(new JLabel("����Ӧ�ó���������"));
		p1.add(run_file_name_text);
		p1.add(button_run_prom);
		add(p1,"North");
		
		//�����¼�
		button_input_txt.addActionListener(this);
		button_compiler.addActionListener(this);
		button_input_txt.addActionListener(this);
		button_run_prom.addActionListener(this);
		button_see_doswin.addActionListener(this);
		
		
		
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		if(Thread.currentThread()==compiler) {
			compiler_text.setText(null);
			String temp = input_text.getText().trim();
			byte[] buffer = temp.getBytes();
			int b = buffer.length;
			String file_name = null;
			file_name=input_file_name_text.getText().trim();
			try {
				File file_saved = new File(file_name);
				FileOutputStream writefile = null;
				writefile = new FileOutputStream(file_saved);
				writefile.write(buffer,0,b);
				writefile.close();
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("ERROR");
			}
			try {
				//��øý��̵Ĵ��������ſ���֪�����н��������ʧ���˻��ǳɹ���
				Runtime rt = Runtime.getRuntime();
				//ͨ��Runtime����javac����
				InputStream in=rt.exec("javac "+file_name).getErrorStream();
				
				BufferedInputStream bufIn = new BufferedInputStream(in);
				
				byte[] shuzu = new byte[100];
				int n = 0;
				boolean flag = true;
				
				//���������Ϣ
				while((n=bufIn.read(shuzu,0,shuzu.length))!=-1) {
					String s = null;
					s = new String(shuzu,0,n);
					compiler_text.append(s);
					if(s!=null) {
						flag=false;
					}
				}
				//�ж��Ƿ����ɹ�
				if(flag) {
					compiler_text.append("Compile Succeed!");
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}else if(Thread.currentThread()==run_prom) {
			//�����ļ���������������dos_out_text
			dos_out_text.setText(null);
			try {
				Runtime rt  = Runtime.getRuntime();
				String path =run_file_name_text.getText().trim();
				Process stream = rt.exec("java "+path); //����java����
				
				InputStream in = stream.getInputStream();
				BufferedInputStream bisErr = new BufferedInputStream(stream.getErrorStream());
				BufferedInputStream bisIn = new BufferedInputStream(in);
				
				byte[] buf = new byte[150];
				byte[] err_buf = new byte[150];
				
				@SuppressWarnings("unused")
				int m = 0;
				@SuppressWarnings("unused")
				int i = 0;
				String s =null;
				String err = null;
				
				//��ӡ������Ϣ��������Ϣ
				while((m=bisIn.read(buf,0,150))!=-1) {
					s = new String(buf,0,150);
					dos_out_text.append(s);
				}while((i=bisErr.read(err_buf))!=-1) {
					err=new String(err_buf,0,150);
					dos_out_text.append(err);
				}
				
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==button_input_txt) {
			//��ʾ����������
			mycard.show(p, "input");
		}else if(e.getSource()==button_compiler_text) {
			//��ʾ��������
			mycard.show(p, "compiler");
		}else if(e.getSource()==button_see_doswin) {
			//��ʾ�������н����
			mycard.show(p, "dos");
		}else if(e.getSource()==button_compiler) {
			//����Ǳ��밴ť��ִ�б����ļ��ķ���
			if(!(compiler.isAlive())) {
				compiler = new Thread(this);
			}
			try {
				compiler.start();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			mycard.show(p, "compiler");
		}else if(e.getSource()==button_run_prom) {
			//��������а�ť��ִ�������ļ��ķ���
			if(!(run_prom.isAlive())) {
				run_prom = new Thread(this);
			}
			try {
				run_prom = new Thread(this);
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			mycard.show(p,"dos");
		}
		
	}
	

}
