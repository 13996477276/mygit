/**
 * ��Ʊ������:ʹ��ͬ�������
 * 
 * @author kenny
 *
 */
public class TicketsSync implements Runnable {
	private int ticketNum = 10;
	// ����������
	Object obj = new Object();
	@Override
	public void run() {

		while (true) {
			synchronized (obj) {
				if (ticketNum <= 0) {
					break;
				}				
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + 
						"" + ticketNum--);
			}
		}

	}
}
