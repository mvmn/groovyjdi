package x.mvmn.tst;

public class TestApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder strb = new StringBuilder(1024);
		while (true) {
			for (int i = 99; i > 0; i++) {
				strb.append(String.format("%1$s bottles of beer on the wall, %1$s bottles of beer\n", i));
				strb.append(String.format("Take one down, pass it around, %1$s bottles of beer\n", i - 1));
				Thread.yield();
				System.out.print(strb.toString());
				strb.setLength(0);
			}
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
			}
		}
	}

}
