package ch1_23;
class X {int a; float b;}
class Y extends X {double c;}

class SysRuntimeProcessBuilder{
	public static void main(String args[]){
System.out.println("��������� ���'���� �������� Runtime: ");
	    Runtime r = Runtime.getRuntime();
	    long mem1, mem2;
	    
	    Integer someints[] = new Integer[1000];
	    System.out.println("������ ���'��: " + r.totalMemory());
	    
	    mem1 = r.freeMemory();
	    System.out.println("³���� ���'�� �� �������: " + mem1);
	    
	    r.gc();
	    mem1 = r.freeMemory();
	    System.out.println("³���� ���'�� ���� �������� \"�����\": " + mem1);

	    for(int i=0; i<1000; i++)
	    	someints[i] = new Integer(i); // allocate integers
	    mem2 = r.freeMemory();
	    System.out.println("³���� ���'�� ���� �����������: " + mem2);
	    System.out.println("����������� ���'�� ��� �����������: " + (mem1-mem2));

	    // discard Integers
	    for(int i=0; i<1000; i++) someints[i] = null;
	    r.gc();
	    mem2 = r.freeMemory();
	    System.out.println("³���� ���'�� ���� �������� �����" +
	                       " ��������� Integer: " + mem2);
	    System.out.println();
	    
	    long time = System.currentTimeMillis();
System.out.println("��������� �������� �� 0 �� 100 000 000:");
	    for(long i=0; i<100_000_000L;i++);
	    System.out.println((System.currentTimeMillis() - time)/1000. + " ������");
	    System.out.println();
	    
System.out.println("��������� ������ ���������� �����: ");
	    Process p = null;
	    try {
	    	System.out.println("������ ��������.");
	    	p = r.exec("notepad");
	    	System.out.println("�����, ���� ��������� �������.");
	    	//���������� ���������� ������� p
	    	p.waitFor();
	    } catch (Exception e) {
	    	System.out.println("Error executing notepad.");
	    }
	    System.out.println("������� �������� " + 
	    					((p.exitValue()==0) ? ("������"):("� ��������")));
	    System.out.println();
	    
System.out.println("������������ ProcessBuilder: ");
	    try {
	    	System.out.println("������� ���� testSysRuntimeProcessBuilder.txt � ���� �������");
	    	ProcessBuilder proc = new ProcessBuilder("notepad.exe", "testSysRuntimeProcessBuilder.txt");
	    	proc.start();
	    } catch (Exception e) {
	    	System.out.println("Error executing notepad.");
	    }
	    System.out.println();
	    
System.out.println("������������ System.arraycopy()");
	    //������ � ���� ������ ������
	    byte a[] = { 65, 66, 67, 68, 69, 70, 71, 72, 73, 74 };
	    byte b[] = { 77, 77, 77, 77, 77, 77, 77, 77, 77, 77 };
	    System.out.println("a = " + new String(a) + "\nb = " + new String(b) + "\n");
	    System.arraycopy(a, 0, b, 0, a.length);
	    System.out.println("a = " + new String(a) + "\nb = " + new String(b) + "\n");
	    //���� ����������� �� 1 ��������
	    System.arraycopy(a, 0, a, 1, a.length - 1);
	    //���� ������� ������� �� 1 ��������
	    System.arraycopy(b, 1, b, 0, b.length - 1);
	    System.out.println("a = " + new String(a) + "\nb = " + new String(b) + "\n");
	    
System.out.println("������������ System.getProperty(\"user.dir\")");
	    System.out.println(System.getProperty("user.dir"));
	    System.out.println();
	    
System.out.println("������������ Object.clone()");
	    class Test implements Cloneable {
	    	int a; double b;
	    	// ������������� ������� clone()
	    	protected Test cloneTest() {
	    		try {
	    			//��������� ���������� ���� ��� �������� ���������� �������
	    			return (Test) super.clone();
	    		} catch(CloneNotSupportedException e) {
	    			System.out.println("Cloning not allowed.");
	    			return this;
	    		}
	    	}
	    	//�������������� ������ clone()
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
	    //clone() ������� Object - ��������� ���������� ����
	    x2 = (Test) x1.clone();
	    x3 = x1.cloneTest();
	    System.out.println("x1: " + x1.a + " " + x1.b);
	    System.out.println("x2: " + x2.a + " " + x2.b);
	    System.out.println("x3: " + x3.a + " " + x3.b);
	    System.out.println();
	    
System.out.println("������������ Class.getClass() �� Class.getSuperclass()");
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