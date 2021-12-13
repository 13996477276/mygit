package cn.chonggong;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Server extends JFrame {

    private JPanel panel;
    private JButton button_on;
    private JButton button_off;
    private JButton button_send;
    private JTextArea textArea;
    private JTextField textField_port = null;
    private JTextField textField_maxpeople;
    private JTextField textField_send;
    private DefaultListModel<String> listModel;
    private boolean isStart = false;
    ServerSocket ss = null;	//服务器
    int port;//服务器端口
    int max_people = 0;//人数上限
    static ArrayList<Client> socket_xc = new ArrayList<Client>();
    private JTextField goout;
    ;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Server frame = new Server();
                    frame.setVisible(true);
                    frame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent arg0) {
                            for(Client client:socket_xc) {//列遍每一个用户，将此用户下线信息广播出去
                                String info_send = "WARNING@"+"服务器已下线";
                                try {
                                    OutputStream out = client.socket.getOutputStream();//返回此套接字的输出流。
                                    PrintWriter write = new PrintWriter(out);
                                    write.println(info_send);////将信息写进字符缓冲区
                                    write.flush();//刷新该流的缓冲
                                } catch (IOException e) {
                                    // TODO 自动生成的 catch 块
                                    e.printStackTrace();
                                }
                            }

                        }
                    });

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public Server() {


        init();
    }
    public void init() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1020, 498);
        getContentPane().setLayout(null);

        panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u8FDE\u63A5\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(0, 0, 811, 71);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label_1 = new JLabel("\u4EBA\u6570\u4E0A\u9650\uFF1A");
        label_1.setBounds(14, 33, 80, 22);
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setVerticalAlignment(SwingConstants.TOP);
        panel.add(label_1);

        textField_maxpeople = new JTextField();
        textField_maxpeople.setText(String.valueOf(max_people));
        textField_maxpeople.setBounds(96, 29, 58, 26);
        panel.add(textField_maxpeople);
        textField_maxpeople.setColumns(10);

        JLabel label = new JLabel("\u7AEF\u53E3\uFF1A");
        label.setBounds(358, 28, 58, 28);
        panel.add(label);
        button_on = new JButton("\u542F\u52A8");
        button_on.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(isStart){//服务器已经启动
                        JOptionPane.showMessageDialog(null,"服务器("+port+"端口)已启动", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    //判断连接信息输入合法性
                    String max_peo = textField_maxpeople.getText();
                    if(max_peo.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null,"请输入有效人数上限", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    max_people = Integer.parseInt(max_peo);//获取输入的人数上限
                    if(textField_port.getText().length() == 0) {//未设置端口
                        JOptionPane.showMessageDialog(null,"请输入有效端口", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    port=Integer.parseInt(textField_port.getText());//获取输入的服务器端口号
                    //启动服务器
                    ss = new ServerSocket(port);
                    isStart = true;
                    textArea.append("服务器：服务器("+port+"端口)已开启\n");
                    textField_port.setEditable(false);//设置端口文本域不可编辑
                    textField_maxpeople.setEditable(false);//设置人数上限文本域不可编辑
                    JOptionPane.showMessageDialog(null,"服务器("+port+"端口)启动成功", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                    Accept accept = new Accept(ss);//初始化接收客户端的线程
                    accept.start();//启动等待客户端线程
                }
                catch (IOException e) {

                    JOptionPane.showMessageDialog(null,"服务器启动失败", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println("服务器启动失败,请检查端口号");
                    e.printStackTrace();
                }
            }
        });

        textField_port = new JTextField();
        textField_port.setText("8080");
        textField_port.setBounds(420, 27, 128, 28);
        panel.add(textField_port);
        textField_port.setColumns(10);
        button_on.setBounds(561, 25, 119, 33);
        panel.add(button_on);

        button_off = new JButton("\u6682\u505C");
        button_off.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(!isStart) {
                        JOptionPane.showMessageDialog(null,"服务器未启动", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    textField_port.setEditable(true);//设置端口文本域可编辑
                    textField_maxpeople.setEditable(true);//设置人数上限文本域可编辑
                    listModel.clear();
                    //accept.interrupt();
                    for(Client client:socket_xc) {//列遍每一个用户线程发送服务器下线通知，并将其线程中断
                        String info_send = "WARNING@"+"服务器已下线";
                        OutputStream out = client.socket.getOutputStream();//返回此套接字的输出流。
                        PrintWriter write = new PrintWriter(out);
                        write.println(info_send);////将信息写进字符缓冲区
                        write.flush();//刷新该流的缓冲
                        client.socket.close();
                        client.interrupt();
                    }
                    ss.close();
                    textArea.append("服务器：("+port+"端口)已关闭\n");
                    isStart = false;
                    //关闭所有相关资源（未实现）（线程，在线用户集合）
                    JOptionPane.showMessageDialog(null,"服务器("+port+"端口)已关闭", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println("服务器("+port+"端口)关闭成功...");
                } catch (IOException e) {

                    JOptionPane.showMessageDialog(null,"服务器关闭失败", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                    //System.out.println("服务器("+port+"端口)启动失败...");
                    e.printStackTrace();
                };//关闭服务器

            }
        });
        button_off.setBounds(684, 25, 112, 34);
        panel.add(button_off);
        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(0, 72, 133, 314);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        listModel =new DefaultListModel<String>();
        JList<String> list = new JList<String>(listModel);
        list.setBounds(1, 1, 89, 172);
        panel_1.add(list);
        JScrollPane gdt_r1 = new JScrollPane(list);//右滚动条
        gdt_r1.setBounds(14, 25, 105, 269);
        panel_1.add(gdt_r1);

        //list监听器
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // 鼠标点击
                if (!e.getValueIsAdjusting()) {
                    // 获取所有被选中的选项索引
                    int index = list.getSelectedIndex();
                    if (index >= 0) {
                        String selectName = listModel.getElementAt(index); // 根据索引获取被踢用户名字
                        goout.setText(selectName);
                    }
                }
            }
        });

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(new TitledBorder(null, "\u6D88\u606F\u663E\u793A\u533A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_2.setBounds(135, 72, 676, 314);
        getContentPane().add(panel_2);
        panel_2.setLayout(null);

        textArea = new JTextArea();
        textArea.setEditable(false);//设置文本域不可编辑
        textArea.setBounds(14, 25, 649, 269);
        panel_2.add(textArea);


        JScrollPane gdt_r2 = new JScrollPane(textArea);//右滚动条
        gdt_r2.setBounds(14, 25, 653, 269);
        panel_2.add(gdt_r2);


        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "\u5199\u6D88\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(0, 387, 811, 64);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        button_send = new JButton("\u53D1\u9001");
        button_send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    if(!isStart) {
                        JOptionPane.showMessageDialog(null,"请先启动服务器", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    /*
                     * 定义发信息的输出流
                     */
                    OutputStream out= null;
                    PrintWriter write = null;
                    //发送信息到各个客户端
                    String info = textField_send.getText();
                    textField_send.setText("");//将消息框置为空
                    if(info.equals("")) {
                        JOptionPane.showMessageDialog(null,"发送的内容为空", "服务器", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    textArea.append("服务器："+info+"\n");
                    String info_send = "MESSAGE@服务器："+info;
                    for(Client client:socket_xc) {//列 遍每一个用户，将接收到的东西发送给他们
                        out = client.socket.getOutputStream();//返回此套接字的输出流。
                        write = new PrintWriter(out);
                        write.println(info_send);////将信息写进字符缓冲区
                        write.flush();//刷新该流的缓冲
                    }
                }catch (IOException e) {
                    JOptionPane.showMessageDialog(null,"发送消息失败", "服务器", JOptionPane.INFORMATION_MESSAGE);
                    e.printStackTrace();
                }

            }
        });
        button_send.setBackground(SystemColor.activeCaptionBorder);
        button_send.setBounds(664, 18, 133, 30);
        panel_3.add(button_send);

        textField_send = new JTextField();
        textField_send.setBounds(14, 18, 648, 30);
        panel_3.add(textField_send);
        textField_send.setColumns(10);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(new TitledBorder(null, "\u670D\u52A1\u5668\u529F\u80FD\u64CD\u4F5C", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_4.setBounds(810, 0, 192, 438);
        getContentPane().add(panel_4);
        panel_4.setLayout(null);

        JLabel label_2 = new JLabel("\u88AB\u8E22\u7528\u6237:");
        label_2.setBounds(2, 29, 75, 26);
        panel_4.add(label_2);

        goout = new JTextField();
        goout.setBounds(66, 30, 86, 25);
        panel_4.add(goout);
        goout.setColumns(10);

        JButton button = new JButton("\u8E22");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String outName = goout.getText();
                if(!outName.equals("")) {
                    listModel.removeElement(outName);//从在线列表中踢出
                    OutputStream out;
                    PrintWriter write;
                    try {
                        for(Client client:socket_xc) {//列遍每一个用户找到被踢用户,提出集合
                            if(client.getName().equals(outName)) {
                                String info_send = "ti"+"@"+client.getName();
                                out = client.socket.getOutputStream();
                                write = new PrintWriter(out);
                                write.println(info_send);////将信息写进字符缓冲区
                                write.flush();//刷新该流的缓冲
                                socket_xc.remove(client);
                                client.interrupt();
                                textArea.append("服务器："+outName+"已被踢出聊天室\n");
                                JOptionPane.showMessageDialog(null,outName+"已被踢出群聊", "服务器", JOptionPane.INFORMATION_MESSAGE);
                            }else {
                                String info_send = "OFFLINE@"+outName;
                                out = client.socket.getOutputStream();
                                write = new PrintWriter(out);
                                write.println(info_send);////将信息写进字符缓冲区
                                write.flush();//刷新该流的缓冲
                            }
                        }
                    } catch (IOException e) {
                        // TODO 自动生成的 catch 块
                        e.printStackTrace();
                    }//返回此套接字的输出流。

                }else {
                    JOptionPane.showMessageDialog(null,"请选择被踢用户", "服务器", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });
        button.setBounds(145, 29, 47, 26);
        panel_4.add(button);

        JLabel label_3 = new JLabel("\u672A\u5B8C\u5F85\u7EED...");
        label_3.setBounds(34, 219, 118, 26);
        panel_4.add(label_3);

    }
    //接收到客户端套接字是给它分配一个线程,处理客户端给服务器发送的信息
    public class Client extends Thread{
        private Socket socket = null;
        private InputStream in = null;
        private BufferedReader br = null;
        private PrintWriter write =null;
        private OutputStream out = null;
        public Client() {

        }
        public Client(String name,Socket socket) {
            super(name);
            this.socket = socket;
        }

        //run方法
        public void run() {
            try {

                in = socket.getInputStream();//返回此套接字的输入流。
                br = new BufferedReader(new InputStreamReader(in));

                //坑：readLine()方法在进行读取一行时，只有遇到回车(\r)或者换行符(\n)才会返回读取结果，这就是“读取一行的意思”，重要的是readLine()返回的读取内容中并不包含换行符或者回车符；
                //解决方法：在客户端那边手动给输入内容加入"\n"或"\r"，再写入服务器；
                while(true) {
                    if(this.isInterrupted()) {
                        //throw new InterruptedException();
                        return;
                    }
                    String info_accept = br.readLine();
                    System.out.println(info_accept);
                    StringTokenizer str = new StringTokenizer(info_accept,"@");//分割接收的信息，提取命令
                    String order = str.nextToken();//命令
                    String info = str.nextToken();
                    switch(order) {//区分消息种类
                        case "MESSAGE":
                            if(info.equals(" ")) {//私聊
                                System.out.println("加入私聊");
                                String selectName = str.nextToken();//私聊用户的名字
                                info = str.nextToken();//私聊内容
                                for(Client client:socket_xc) {//列遍每一个用户找到私聊用户和自己
                                    if(client.getName().equals(selectName)) {//私聊用户
                                        String info_send = " "+"@"+this.getName()+":"+info+"(  对"+selectName+"私聊)";
                                        out = client.socket.getOutputStream();//返回此套接字的输出流。
                                        write = new PrintWriter(out);
                                        write.println(info_send);////将信息写进字符缓冲区
                                        write.flush();//刷新该流的缓冲
                                        textArea.append(this.getName()+"："+info+"(  对"+selectName+"私聊)"+"\n");break;//将接收的消息打印到服务器的消息显示区

                                    }
                                    if(client == this) {//给自己
                                        String info_send = " "+"@"+this.getName()+":"+info+"(  对"+selectName+"私聊)";
                                        out = client.socket.getOutputStream();//返回此套接字的输出流。
                                        write = new PrintWriter(out);
                                        write.println(info_send);////将信息写进字符缓冲区
                                        write.flush();//刷新该流的缓冲
                                    }
                                }

                            }else {
                                for(Client client:socket_xc) {//列遍每一个用户，将接收到的东西发送给他们
                                    String info_send = "MESSAGE@"+this.getName()+":"+info;
                                    out = client.socket.getOutputStream();//返回此套接字的输出流。
                                    write = new PrintWriter(out);
                                    write.println(info_send);////将信息写进字符缓冲区
                                    write.flush();//刷新该流的缓冲
                                }
                                textArea.append(this.getName()+"："+info+"\n");//将接收的消息打印到服务器的消息显示区
                            }

                            break;
                        case "OFFLINE":
                            for(Client client:socket_xc) {//列遍每一个用户，将此用户下线信息广播出去
                                if(client != this) {
                                    String info_send = "OFFLINE@"+this.getName();
                                    out = client.socket.getOutputStream();//返回此套接字的输出流。
                                    write = new PrintWriter(out);
                                    write.println(info_send);////将信息写进字符缓冲区
                                    write.flush();//刷新该流的缓冲

                                }
                            }
                            listModel.removeElement(this.getName());//刷新服务器在线列表
                            textArea.append("服务器："+this.getName()+"已下线！\n");
                            this.interrupt();
                            for(Client client:socket_xc) {//列遍每一个用户，删除下线用户
                                if(client == this) {
                                    socket_xc.remove(this);
                                }
                            }
                            break;//中断此线
                    }
                }

            } catch (IOException e) {

                e.printStackTrace();
            }/*finally {
				try {
					br.close();
					isr.close();
					is.close();
				} catch (IOException e) {

					e.printStackTrace();
				}

			}*/

        }

    }
    //接收客户端线程类，并分配线程
    public class Accept extends Thread {
        private ServerSocket ss;
        private InputStream in = null;
        private BufferedReader br = null;
        private OutputStream out =null;
        private PrintWriter write = null;
        public Accept() {

        }
        public Accept(String name) {
            super(name);
        }
        public Accept(ServerSocket ss) {
            this.ss = ss;
        }
        //run方法
        public void run() {
            if(isStart) {//确保服务器开启

                while(true) {
                    try {
                        Socket socket = ss.accept();//接收客户端的连接
                        if(socket_xc.size()<max_people) {

                            in = socket.getInputStream();//返回此套接字的输入流。
                            br = new BufferedReader(new InputStreamReader(in));
                            String name = br.readLine();//接收用户信息

                            StringTokenizer str = new StringTokenizer(name,"@");//分割接收的信息，提取命令
                            if(str.nextToken().equals("ONLINE")) {//指示登陆

                                name = str.nextToken();//得到客户名字

                                Client client_new = new Client(name,socket);//创建一个客户端线程
                                client_new.start();//启动客户端线程
                                socket_xc.add(client_new);//保存客户端线程
                                textArea.append("服务器：欢迎"+client_new.getName()+"上线！\n");
                                listModel.addElement(client_new.getName());//将指定组件添加到此类表的末尾。
                            }

                            for(Client client_:socket_xc) {//列遍每一个用户，将其它在线用户的名字信息广播到新用户
                                if(client_.socket != socket) {
                                    //if(client_.isInterrupted()) continue;//排除已经中断的客户线程（中断的线程还存在）
                                    out = socket.getOutputStream();//返回此套接字的输出流。
                                    write = new PrintWriter(out);
                                    String info_send ="NEW@"+client_.getName();
                                    System.out.println("在线用户消息："+info_send);
                                    write.println(info_send);////将信息写进字符缓冲区
                                    write.flush();//刷新该流的缓冲

                                }
                            }
                            for(Client client_:socket_xc) {//列遍每一个用户，将新用户上线信息广播到其它用户
                                //if(client_.isInterrupted()) continue;//排除已经中断的客户线程（中断的线程还存在）
                                out = client_.socket.getOutputStream();//返回此套接字的输出流。
                                write = new PrintWriter(out);
                                String info_send ="MESSAGE@服务器：欢迎"+name+"上线";
                                write.println(info_send);////将信息写进字符缓冲区
                                write.flush();//刷新该流的缓冲
                                String info_send_2 ="NEW@"+name;
                                write.println(info_send_2);////将信息写进字符缓冲区
                                write.flush();//刷新该流的缓冲

                            }
                        }else {
                            OutputStream out = socket.getOutputStream();//返回此套接字的输出流。
                            PrintWriter w = new PrintWriter(out);
                            String info_send ="WARNING@"+"登陆服务器失败，服务器登陆人数已达上限";
                            w.println(info_send);////将信息写进字符缓冲区
                            w.flush();//刷新该流的缓冲
                        }



                    } catch (IOException e) {

                        e.printStackTrace();
                    }/*finally {

						try {
							br.close();
							isr.close();
							is.close();
						} catch (IOException e) {

							e.printStackTrace();
						}

					}*/

                }
            }
        }
    }
}