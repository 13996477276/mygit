package cn.chonggong;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

public class ClientIFrame extends JFrame implements Runnable {
    public Socket s;
    public JTextField tf;
    public JTextArea ta;
    JList<String> list;
    public ClientIFrame(){
        //设置关闭时，程序停止
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        //设置是否可以最大
        setResizable(false);
        //设置聊天框大小
        setBounds(400, 400, 400, 400);
        //设置标题
        setTitle("客户端");
        //设置文本大小不可调，自定义
        setLayout(null);
        //设置按钮
        JButton btn = new JButton("send");
        btn.setBounds(300, 300, 80, 50);
        //设置绑定事件
        //设置enter绑定事件
        btn.registerKeyboardAction(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                ////发送信息的方法与enter键绑定，将socket对象与输入的文本传送到发送方法中，准备发送
                sendMsg(s,tf.getText());
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), JComponent.WHEN_IN_FOCUSED_WINDOW);
        btn.addActionListener(new ActionListener() {
            //绑定点击事件
            @Override
            public void actionPerformed(ActionEvent e) {
//				// TODO Auto-generated method stub
//				//输出到控制台console中
//				System.out.println(tf.getText());
//				//输出到文本域中(文本域存在的内容的后面连接从文本框中新获取的值）
//				//ta.setText(ta.getText()+"\r\n"+tf.getText());
//				ta.setText(ta.getText()+"\r\n"+Client.msg);
                //发送信息的方法与按钮绑定，将socket对象与输入的文本传送到发送方法中，准备发送
                sendMsg(s,tf.getText());
//清空文本输入框
                tf.setText("");
            }
        });
        add(btn);
        //设置输入文本
        tf = new JTextField();
        tf.setBounds(50, 300, 200, 50);
        //绑定客户端输出信息
        //tf.setText();
        add(tf);
        //设置文本域
        ta = new JTextArea();
        ta.setBounds(10,10,250,250);
        //文本域的添加必须在滚动条之前
        add(ta);
        //设置滚动条，大小位置应与文本域一致
        JScrollPane sp = new JScrollPane(ta);
        sp.setBounds(10,10,250,250);
        //设置滚动条可见
        sp.setViewportView(ta);
        add(sp);
        //设置成员对象
        String[]data ={"张无忌","周芷若","赵敏","张三丰","李雷","韩梅梅","lucy","lily"};
        list = new JList<String>(data);
        list.setBounds(280, 50, 50, 200);
        //添加聊天成员选择事件
        list.addListSelectionListener(new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                // TODO Auto-generated method stub
                tf.setText("@"+list.getSelectedValue()+":");
            }
        });
        add(list);
        //通过ip与端口连接服务器
        try {
            s = new Socket("127.0.0.1",8899);
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ClientIFrame cf = new ClientIFrame();
        cf.setVisible(true);
        //利用多线程对象，启用run方法
        Thread th = new Thread(cf);
        th.start();
    }
    //发送方法，在上面绑定到按钮里面
    public void sendMsg(Socket s,String msg){
        try {
            DataOutputStream dos = new DataOutputStream(s.getOutputStream());
            dos.writeUTF(msg);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    //接收方法
    public String receiveMsg(Socket s){
        DataInputStream dis = null;
        String msg ="";
        try {
            dis = new DataInputStream(s.getInputStream());
            msg = dis.readUTF();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return msg;
    }
    //采用多线程，一直接收信息放到文本域中
    @Override
    public void run() {
        // TODO Auto-generated method stub
        boolean flag = true;
        while(flag){
            ta.setText(ta.getText()+"\r\n服务端对话："+new Date()+"\r\n"+receiveMsg(s));
        }
    }
}
