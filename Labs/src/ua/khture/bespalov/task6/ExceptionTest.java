package ua.khture.bespalov.task6;

public class ExceptionTest {
	static int k = 0;
	
	public static void main(String[] args) {
		try {
			int[] array = new int[2];
			int a = array[3];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println(e);
		}


		try {
			Thread.currentThread().setPriority(Thread.MIN_PRIORITY - 1);
		} catch (IllegalArgumentException e) {
			System.out.println(e);
		}


		try {
			Object str = new String();
			((Integer)str).intValue();
		} catch (ClassCastException e) {
			System.out.println(e);
		}


		try {
			String str = "123456789";
			str.charAt(20);
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println(e);
		}


		try {
			String str = null;
			str.isEmpty();
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		try {
			int[] array = null;
			int a = array[1];
		} catch (NullPointerException e) {
			System.out.println(e);
		}
		
		try {
			throw null;
		} catch (NullPointerException e) {
			System.out.println(e);
		}

		try {
			new Thread(new Runnable() {
				@Override
				public void run() {
					run();
				}
			}).start();
		} catch (StackOverflowError e) {
			System.out.println(e);
		}
	
	
		try {
			new Integer("1s");
		} catch (NumberFormatException e) {
			System.out.println(e);
		}
		
		for (int i=1; i <= 100; i++) {
	        try {
	            byte[] bytes = new byte[1024*1024*500];
	        } catch (Exception e) {
	            e.printStackTrace();
	        } catch (OutOfMemoryError e) {
	            System.out.println(e);
	        }
	    }
	}
}
