package myEdit;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	

}
