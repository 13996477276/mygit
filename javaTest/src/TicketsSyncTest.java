public class TicketsSyncTest {
	public static void main(String[] args) {

		// ����Runnable�ӿڵ�ʵ�������
		TicketsSync m = new TicketsSync();
		Thread t1 = new Thread(m,"a");
		Thread t2 = new Thread(m,"b");
		Thread t3 = new Thread(m,"c");
		
		t1.start();
		t2.start();
		t3.start();

	}
}
