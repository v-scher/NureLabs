package ch1_23;
class X {int a; float b;}
class Y extends X {double c;}

class SysRuntimeProcessBuilder{
	public static void main(String args[]){
System.out.println("керування пам'яттю методами Runtime: ");
	    Runtime r = Runtime.getRuntime();
	    long mem1, mem2;
	    
	    Integer someints[] = new Integer[1000];
	    System.out.println("Всього пам'яті: " + r.totalMemory());
	    
	    mem1 = r.freeMemory();
	    System.out.println("Вільної пам'яті на початку: " + mem1);
	    
	    r.gc();
	    mem1 = r.freeMemory();
	    System.out.println("Вільної пам'яті після збирання \"сміття\": " + mem1);

	    for(int i=0; i<1000; i++)
	    	someints[i] = new Integer(i); // allocate integers
	    mem2 = r.freeMemory();
	    System.out.println("Вільної пам'яті після розподілення: " + mem2);
	    System.out.println("Використано пам'яті для розподілення: " + (mem1-mem2));

	    // discard Integers
	    for(int i=0; i<1000; i++) someints[i] = null;
	    r.gc();
	    mem2 = r.freeMemory();
	    System.out.println("Вільної пам'яті після збирання сміття" +
	                       " відкинутих Integer: " + mem2);
	    System.out.println();
	    
	    long time = System.currentTimeMillis();
System.out.println("Тривалість перебору від 0 до 100 000 000:");
	    for(long i=0; i<100_000_000L;i++);
	    System.out.println((System.currentTimeMillis() - time)/1000. + " секунд");
	    System.out.println();
	    
System.out.println("виконання команд командного рядка: ");
	    Process p = null;
	    try {
	    	System.out.println("запуск Блокнота.");
	    	p = r.exec("notepad");
	    	System.out.println("чекаю, доки закриється Блокнот.");
	    	//очікування завершення процеса p
	    	p.waitFor();
	    } catch (Exception e) {
	    	System.out.println("Error executing notepad.");
	    }
	    System.out.println("Блокнот закритий " + 
	    					((p.exitValue()==0) ? ("успішно"):("з помилкою")));
	    System.out.println();
	    
System.out.println("використання ProcessBuilder: ");
	    try {
	    	System.out.println("відкрити файл testSysRuntimeProcessBuilder.txt в теці проекту");
	    	ProcessBuilder proc = new ProcessBuilder("notepad.exe", "testSysRuntimeProcessBuilder.txt");
	    	proc.start();
	    } catch (Exception e) {
	    	System.out.println("Error executing notepad.");
	    }
	    System.out.println();
	    
System.out.println("використання System.arraycopy()");
	    //працює з усіма типами масивів
	    byte a[] = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74 };
	    byte b[] = { 77, 77, 77, 77, 77, 77, 77, 77, 77, 77 };
	    System.out.println("a = " + new String(a) + "\nb = " + new String(b) + "\n");
	    System.arraycopy(a, 0, b, 0, a.length);
	    System.out.println("a = " + new String(a) + "\nb = " + new String(b) + "\n");
	    //зсув ПРИЗНАЧЕННЯ на 1 праворуч
	    System.arraycopy(a, 0, a, 1, a.length - 1);
	    //зсув початку ДЖЕРЕЛА на 1 праворуч
	    System.arraycopy(b, 1, b, 0, b.length - 1);
	    System.out.println("a = " + new String(a) + "\nb = " + new String(b) + "\n");
	    
System.out.println("використання System.getProperty(\"user.dir\")");
	    System.out.println(System.getProperty("user.dir"));
	    System.out.println();
	    
System.out.println("використання Object.clone()");
	    class Test implements Cloneable {
	    	int a; double b;
	    	// використовуємо функцію clone()
	    	protected Test cloneTest() {
	    		try {
	    			//необхідне приведення типів для сумісності повернених значень
	    			return (Test) super.clone();
	    		} catch(CloneNotSupportedException e) {
	    			System.out.println("Cloning not allowed.");
	    			return this;
	    		}
	    	}
	    	//перевизначення метода clone()
	    	protected Object clone() {
	    		try {
	    			return super.clone();
	    		} catch(CloneNotSupportedException e) {
	    			System.out.println("Cloning not allowed.");
	    			return this;
	    		}
	    	}
	    }
	    Test x1 = new Test(),x2,x3;
	    x1.a = 10; x1.b = 20.98;
	    //clone() повертає Object - необхідне приведення типів
	    x2 = (Test) x1.clone();
	    x3 = x1.cloneTest();
	    System.out.println("x1: " + x1.a + " " + x1.b);
	    System.out.println("x2: " + x2.a + " " + x2.b);
	    System.out.println("x3: " + x3.a + " " + x3.b);
	    System.out.println();
	    
System.out.println("використання Class.getClass() та Class.getSuperclass()");
	    X x = new X();
	    Y y = new Y();
	    Class<?> clObj;
	    clObj = x.getClass(); // get Class reference
	    System.out.println("x is object of type: " +
	                       clObj.getName());

	    clObj = y.getClass(); // get Class reference
	    System.out.println("y is object of type: " +
	                       clObj.getName());
	    clObj = clObj.getSuperclass();
	    System.out.println("y's superclass is " +
	                       clObj.getName());
	    System.out.println();
	}
}