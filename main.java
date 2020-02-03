/**
 * Generated from main.treo by Reo 1.0.
 */

import nl.cwi.reo.runtime.*;

public class main {

	public static void main(String[] args) {

		Port<String> $5 = new PortWaitNotify<String>();
		Port<String> $5_3 = new PortWaitNotify<String>();
		Port<String> $3 = new PortWaitNotify<String>();
		Port<String> $4 = new PortWaitNotify<String>();

		green1 green1 = new green1();
		$3.setProducer(green1); 
		green1.$3 = $3;
		Thread thread_green1 = new Thread(green1);
		red2 red2 = new red2();
		$4.setProducer(red2); 
		red2.$4 = $4;
		Thread thread_red2 = new Thread(red2);
		blue3 blue3 = new blue3();
		$5.setConsumer(blue3); 
		blue3.$5 = $5;
		Thread thread_blue3 = new Thread(blue3);
		PortWindow4 PortWindow4 = new PortWindow4();
		$3.setConsumer(PortWindow4); 
		PortWindow4.$3 = $3;
		Thread thread_PortWindow4 = new Thread(PortWindow4);
		PortWindow5 PortWindow5 = new PortWindow5();
		$4.setConsumer(PortWindow5); 
		PortWindow5.$4 = $4;
		Thread thread_PortWindow5 = new Thread(PortWindow5);
		PortWindow6 PortWindow6 = new PortWindow6();
		$5_3.setProducer(PortWindow6); 
		PortWindow6.$5_3 = $5_3;
		Thread thread_PortWindow6 = new Thread(PortWindow6);
		Protocol1 Protocol1 = new Protocol1();
		$5.setProducer(Protocol1); 
		$5_3.setConsumer(Protocol1); 
		$3.setConsumer(Protocol1); 
		$4.setConsumer(Protocol1); 
		Protocol1.$5 = $5;
		Protocol1.$5_3 = $5_3;
		Protocol1.$3 = $3;
		Protocol1.$4 = $4;
		Thread thread_Protocol1 = new Thread(Protocol1); 

		thread_green1.start();
		thread_red2.start();
		thread_blue3.start();
		thread_PortWindow4.start();
		thread_PortWindow5.start();
		thread_PortWindow6.start();
		thread_Protocol1.start();

		try {
			thread_green1.join();
			thread_red2.join();
			thread_blue3.join();
			thread_PortWindow4.join();
			thread_PortWindow5.join();
			thread_PortWindow6.join();
			thread_Protocol1.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static class green1 implements Component {

		public volatile Port<String> $3;
		 

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void run() {
			Processes.Green($3);
		}
	}

	private static class red2 implements Component {

		public volatile Port<String> $4;
		 

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void run() {
			Processes.Red($4);
		}
	}

	private static class blue3 implements Component {

		public volatile Port<String> $5;
		 

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void run() {
			Processes.Blue($5);
		}
	}

	private static class PortWindow4 implements Component {

		public volatile Port<String> $3;
		 

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void run() {
			Windows.consumer("a", $3);
		}
	}

	private static class PortWindow5 implements Component {

		public volatile Port<String> $4;
		 

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void run() {
			Windows.consumer("b", $4);
		}
	}

	private static class PortWindow6 implements Component {

		public volatile Port<String> $5_3;
		 

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void run() {
			Windows.producer("c", $5_3);
		}
	}

	private static class Protocol1 implements Component {

		public volatile Port<String> $5;
		public volatile Port<String> $5_3;
		public volatile Port<String> $3;
		public volatile Port<String> $4;
		private String m1  = null ;  

		public void activate() {
			synchronized (this) {
				notify();
			}
		}

		public void fire(Integer i){
			if(guards[i].guard())commands[i].update();;
		};

		interface Guard{
			Boolean guard();
		}

		interface Command{
			void update();
		}

		private Guard[] guards = new Guard[]{
			new Guard(){
				public Boolean guard(){
					return ($5.hasGet() && (!(m1 == null)));
				}
			},
			new Guard(){
				public Boolean guard(){
					return ($5.hasGet() && (!($5_3.peek() == null)));
				}
			},
			new Guard(){
				public Boolean guard(){
					return ($5.hasGet() && (m1 == null && 
			           !($3.peek() == null) && 
			           !($4.peek() == null) && 
			           null == null));
				}
			},		
		};

		private Command[] commands = new Command[]{
			new Command(){
				public void update(){
					$5.put(m1); 
					 
					m1 = null; 

				}
			},
			new Command(){
				public void update(){
					$5.put($5_3.peek()); 
					 
					 
					$5_3.get();
				}
			},
			new Command(){
				public void update(){
					$5.put($3.peek()); 
					m1 = $4.peek(); 
					 
					$3.get();
					$4.get();
				}
			},		
		};

		public void run() {
			int i = 0;
			int j = 0;
			int s = guards.length;

			while (true) {
				fire((i+j) % s);			

				i = (i+1) % s;
				if (i == 0)
					j = (j+1) % s;

				synchronized (this) {
					while(true) {
						if ($5.hasGet() && (!(m1 == null))) break;
						if ($5.hasGet() && (!($5_3.peek() == null))) break;
						if ($5.hasGet() && (m1 == null && 
						     !($3.peek() == null) && 
						     !($4.peek() == null) && 
						     null == null)) break;
						try { 
							wait(); 
						} catch (InterruptedException e) { }
					}	
				}
			}
		}
	}
}