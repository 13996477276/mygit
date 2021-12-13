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
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import java.awt.TextArea;
import javax.swing.JList;

public class Client extends JFrame {
    private JTextField textField_name;
    private JTextField textField_IP;
    private JTextField textField_port;
    private JTextField textField_send;
    private DefaultListModel<String> listModel;//处理在线人数
    private JTextArea textArea;
    private int port;//服务器的端口
    Accept_info accept;
    private String address;//服务器地址
    private String name;//客户姓名
    private OutputStream out ;	//客户端输出流
    private PrintWriter write ;
    private InputStream in = null;//客户端输入流
    private BufferedReader br = null;
    boolean isConnect = false;//判断是否连接服务器
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Client frame = new Client();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    /**
     * Create the frame.
     */
    public Client() {
        init();
    }
    public void init() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                if(isConnect) {
                    //System.out.println("12123");
                    String info_send = "OFFLINE@"+"我下线了";
                    write.println(info_send);////将信息写进字符缓冲区
                    write.flush();//刷新该流的缓冲
                    isConnect = false;

                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 829, 498);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "\u8FDE\u63A5\u4FE1\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel.setBounds(0, 0, 811, 71);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label_1 = new JLabel("\u59D3\u540D\uFF1A");
        label_1.setBounds(14, 29, 54, 21);
        label_1.setHorizontalAlignment(SwingConstants.LEFT);
        label_1.setVerticalAlignment(SwingConstants.TOP);
        panel.add(label_1);

        textField_name = new JTextField();
        //textField_name.setText("30\u4EBA");
        textField_name.setBounds(60, 26, 86, 24);
        panel.add(textField_name);
        textField_name.setColumns(10);

        JLabel lblip = new JLabel("\u670D\u52A1\u5668IP\uFF1A");
        lblip.setBounds(216, 28, 86, 21);
        panel.add(lblip);

        textField_IP = new JTextField();
        textField_IP.setText("127.0.0.1");
        textField_IP.setBounds(295, 26, 86, 24);
        panel.add(textField_IP);
        textField_IP.setColumns(10);

        JLabel label = new JLabel("\u7AEF\u53E3\uFF1A");
        label.setBounds(405, 29, 54, 21);
        panel.add(label);

        textField_port = new JTextField();
        textField_port.setText("8080");
        textField_port.setBounds(455, 26, 86, 24);
        panel.add(textField_port);
        textField_port.setColumns(10);

        JButton button = new JButton("\u8FDE\u63A5");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //连接服务器
                try {
                    if(isConnect) {
                        return;
                    }
                    //获取连接信息
                    String localIp =  InetAddress.getLocalHost().getHostAddress();//获取本机地址
                    if(textField_IP.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null,"请输入有效服务器IP", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    address = textField_IP.getText();//获取输入的服务器端IP
                    if(textField_port.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"请输入有效服务器端口", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    port = Integer.parseInt(textField_port.getText());//获取输入的服务器端口号
                    if(textField_name.getText().equals("")) {
                        JOptionPane.showMessageDialog(null,"请输入有效的用户名", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    name = textField_name.getText();//获取输入的客户姓名
                    String name_ = "ONLINE@"+name+"@"+localIp;

                    //连接服务器
                    Socket socket = new Socket(address,port);
                    textArea.append("连接服务器成功"+"\n");
                    textField_IP.setEditable(false);//设置ip文本域不可编辑
                    textField_port.setEditable(false);//设置端口文本域不可编辑
                    textField_name.setEditable(false);//设置用户名文本域不可编辑
                    isConnect = true;
                    //开启客户端接收服务器信息的线程
                    accept = new Accept_info(socket);
                    accept.start();
                    /*
                     * 把客户姓名发给服务器
                     */
                    out = socket.getOutputStream();//返回此套接字的输出流。
                    write = new PrintWriter(out);
                    write.println(name_);//将信息写进字符缓冲区
                    write.flush();//刷新该流的缓冲。
                    //System.out.println(name);
                } catch (UnknownHostException e) {
                    // TODO 自动生成的 catch 块
                    JOptionPane.showMessageDialog(null,"连接服务器失败，请检查服务器IP和端口号", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                    //e.printStackTrace();
                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    JOptionPane.showMessageDialog(null,"连接服务器失败，请检查服务器IP和端口号", "系统信息", JOptionPane.INFORMATION_MESSAGE);
                    //e.printStackTrace();
                }/*finally {
					try {
						bw.close();
						osw.close();
						out.close();
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}

				}*/

            }
        });
        button.setBounds(567, 25, 113, 27);
        panel.add(button);

        JButton button_stop = new JButton("\u65AD\u5F00");
        button_stop.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                /*
                 * 把客户姓名发给服务器
                 */
                if(!isConnect) {
                    return;
                }
                String info = "OFFLINE@"+name;
                try {
                    write.println(info);//将信息写进字符缓冲区
                    write.flush();//刷新该流的缓冲。
                    //listModel.removeElement(name);//刷新自己的在线人数列表
                    listModel.clear();
                    textArea.append("服务器："+name+"已下线\n");
                    textField_IP.setEditable(true);//设置ip文本域不可编辑
                    textField_port.setEditable(true);//设置端口文本域不可编辑
                    textField_name.setEditable(true);//设置用户名文本域不可编辑
                    isConnect = false;
                    /*
                     * 关闭客户的输出流和输入流
                     */
                    accept.interrupt();//中断客户端接收线程
                    write.close();
                    br.close();
                    out.close();
                    in.close();


                } catch (IOException e) {
                    // TODO 自动生成的 catch 块
                    e.printStackTrace();
                }//返回此套接字的输出流。

            }
        });
        button_stop.setBounds(684, 25, 113, 27);
        panel.add(button_stop);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "\u5728\u7EBF\u7528\u6237", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_1.setBounds(0, 72, 133, 314);
        getContentPane().add(panel_1);
        panel_1.setLayout(null);

        listModel = new DefaultListModel<String>();
        JList<String> list = new JList<String>(listModel);
        list.setBounds(14, 26, 105, 275);
        panel_1.add(list);
        JScrollPane gdt_r1 = new JScrollPane(list);//右滚动条
        gdt_r1.setBounds(14, 26, 105, 275);
        panel_1.add(gdt_r1);
        //list监听器
        list.addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // 鼠标点击
                if (!e.getValueIsAdjusting()) {

                    // 获取所有被选中的选项索引
                    int index = list.getSelectedIndex();
                    if (index >= 0) {
                        String selectName = listModel.getElementAt(index); // 获取选项数据（私聊名字）
                        //System.out.println("私聊用户："+selectName);
                        if(!selectName.equals(name)) {//不能私聊自己
                            textField_send.setText(" "+"@"+selectName+"@");
                        }
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
        textArea.setBounds(14, 24, 648, 277);
        panel_2.add(textArea);
        JScrollPane gdt_r2 = new JScrollPane(textArea);//右滚动条
        gdt_r2.setBounds(14, 24, 648, 277);
        panel_2.add(gdt_r2);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(new TitledBorder(null, "\u5199\u6D88\u606F", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        panel_3.setBounds(0, 387, 811, 64);
        getContentPane().add(panel_3);
        panel_3.setLayout(null);

        JButton button_send = new JButton("\u53D1\u9001");
        button_send.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //发送信息到服务器
                if(!isConnect) {
                    JOptionPane.showMessageDialog(null,"请先连接服务器", name, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String info = textField_send.getText();
                textField_send.setText("");//将消息框置为空
                if(info.equals("")) {
                    JOptionPane.showMessageDialog(null,"发送的内容为空", name, JOptionPane.INFORMATION_MESSAGE);
                    return;
                }
                info = "MESSAGE@"+info;
                //out = socket.getOutputStream();//返回此套接字的输出流。
                write = new PrintWriter(out);
                write.println(info);//将信息写进字符缓冲区
                write.flush();//刷新该流的缓冲。


            }
        });
        button_send.setBackground(SystemColor.activeCaptionBorder);
        button_send.setBounds(664, 18, 133, 30);
        panel_3.add(button_send);

        textField_send = new JTextField();
        textField_send.setBounds(14, 18, 648, 30);
        panel_3.add(textField_send);
        textField_send.setColumns(10);
    }
    public class Accept_info extends Thread{
        private Socket socket;
        public Accept_info( Socket socket) {
            this.socket = socket;
        }
        public void run() {
            try {
                /*
                 * 打开客户端输入流
                 */
                in = socket.getInputStream();
                br = new BufferedReader(new InputStreamReader(in));
                while(true) {
                    if(!isConnect) {
                        return;
                    }
                    String info_accept = br.readLine();
                    StringTokenizer str = new StringTokenizer(info_accept,"@");//分割接收的信息，提取命令
                    String order = str.nextToken();//命令
                    String info = str.nextToken();
                    switch(order) {//区分消息种类
                        case "NEW":listModel.addElement(info);break;//将新上线的用户打印到在线人数区。
                        case "MESSAGE":textArea.append(info+"\n");break;//将接收的消息打印到客户的消息显示区
                        case "WARNING":textArea.append(info+"\n");JOptionPane.showMessageDialog(null,info, name, JOptionPane.INFORMATION_MESSAGE);break;//弹出服务器发送的警告
                        case "OFFLINE":listModel.removeElement(info);textArea.append("服务器："+info+"已下线\n");break;//接收其它用户下线信息,更新在线人数列表
                        case " ":textArea.append(info+"\n");break;//将接收的私聊消息打印到客户的消息显示区
                        case "ti":isConnect = false;textField_IP.setEditable(true);textField_port.setEditable(true);textField_name.setEditable(true);listModel.clear();
                            textArea.append("服务器:您已被踢出群聊\n");write.close();br.close();out.close();in.close();break;//处理被踢消息
                    }
                }
            } catch (IOException e) {

                e.printStackTrace();
            }

        }
    }
}

