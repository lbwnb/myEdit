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
	CardLayout mycard; //声明布局
	JButton button_input_txt,    //按钮的定义
	        button_compiler_text,
	        button_compiler,
	        button_run_prom,
	        button_see_doswin;
	
	JPanel p = new JPanel();
	JTextArea input_text = new JTextArea();   //程序输入区
	JTextArea compiler_text = new JTextArea(); //编译错误显示区
	JTextArea dos_out_text = new JTextArea();  //程序的输出信息
	
	JTextField input_file_name_text = new JTextField();
	JTextField run_file_name_text = new JTextField();
	
	public FileWindow() {
		super("java语言编译器");
		mycard = new CardLayout();
		compiler = new Thread(this);
		run_prom = new Thread(this);
		button_input_txt = new JButton("程序输入区（白色）");
		button_compiler_text = new JButton("编译结果区（粉红色）");
		button_see_doswin = new JButton("程序运行结果（浅蓝色）");
		button_compiler = new JButton("编译程序");
		button_run_prom = new JButton("运行程序");
		
		p.setLayout(mycard);  //设置卡片布局
		p.add("input",input_text);  //定义卡片名称
		p.add("compiler",compiler_text);
		p.add("dos",dos_out_text);
		add(p,"Center");
		
		compiler_text.setBackground(Color.pink);  //设置颜色
		dos_out_text.setBackground(Color.cyan);
		JPanel p1 = new JPanel();
		
		p1.setLayout(new GridLayout(3,3)); //设置表格布局
		//添加组件
		
		p1.add(button_input_txt);
		p1.add(button_compiler_text);
		p1.add(button_see_doswin);
		p1.add(new JLabel("请输入编译文件名（.java）："));
		p1.add(input_file_name_text);
		p1.add(button_compiler);
		p1.add(new JLabel("输入应用程序主类名"));
		p1.add(run_file_name_text);
		p1.add(button_run_prom);
		add(p1,"North");
		
		//定义事件
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
