package ch1_23;
//����� 17
import java.util.*;
import java.io.*; 

public class CollectionDemo {
	public static void main(String args[]){
System.out.println("������������� ArrayList:");
		ArrayList<String> al = new ArrayList<String>();
	System.out.println("���������� ����� ������: " + al.size());
		al.add("C");
		al.add("A");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");
		al.add(1,"A2");
	System.out.println("����� alStr ���� �������: " + al.size());
	System.out.println("���� alStr: " + al);
		al.remove("F");
		al.remove(2);
	System.out.println("����� alStr ���� ���������: " + al.size());
	System.out.println("���� alStr: " + al);
		System.out.println();
		
System.out.println("������������� ArrayList + toArray()");
	    ArrayList<Integer> al1 = new ArrayList<Integer>();    
	    // Add elements to the array list. 
	    al1.add(1);  
	    al1.add(2);  
	    al1.add(3);  
	    al1.add(4);  
	System.out.println("���� alInt: " + al1);  
	    // Get the array. 
	    Integer ia[] = new Integer[al1.size()];  
	    ia = al1.toArray(ia);  
	    int sum = 0;  
	    // Sum the array. 
	    for(int i : ia) sum += i;  
	System.out.println("���� �������� ������ alInt: " + sum);
	    System.out.println();
	    
System.out.println("������������� LinkedList: ");
	    LinkedList<String> ll= new LinkedList<>();
	    ll.add("F");
	    ll.add("B");
	    ll.add("D");
	    ll.add("E");
	    ll.add("C");
	    ll.addFirst("A");
	    ll.addLast("Z");
	    ll.add(1,"A2");
	System.out.println("���������� ���� ll: " + ll);
	    ll.remove("F");
	    ll.remove(2);
	System.out.println("���� ll ���� ���������: " + ll);
	    ll.removeFirst();
	    ll.removeLast();
	System.out.println("ll ���� ��������� ������� �� ���������� ��������: " + ll);
	    String val = ll.get(2);
	    ll.set(2, val + " �������");
	System.out.println("ll ���� ����: " + ll);
	    System.out.println();
	     
System.out.println("������������� HashSet: ");
		HashSet<String> hs = new HashSet<>(); 
	    hs.add("B"); 
	    hs.add("A"); 
	    hs.add("D"); 
	    hs.add("E"); 
	    hs.add("C"); 
	    hs.add("F"); 
	    System.out.println(hs); 
System.out.println("������������� LinkedHashSet: ");
	    LinkedHashSet<String> lhs = new LinkedHashSet<>(); 
	    lhs.add("B"); 
	    lhs.add("A"); 
	    lhs.add("D"); 
	    lhs.add("E"); 
	    lhs.add("C"); 
	    lhs.add("F"); 
	    System.out.println(lhs);
System.out.println("������������� TreeSet: ");
	    TreeSet<String> ts = new TreeSet<String>(); 
	    ts.add("C"); 
	    ts.add("A"); 
	    ts.add("B"); 
	    ts.add("E"); 
	    ts.add("F"); 
	    ts.add("D"); 
	    System.out.println(ts); 
	System.out.println("��������� ts.subSet(\"C\", \"F\"): ");
	    System.out.println(ts.subSet("C", "F"));
	    System.out.println();
	    
System.out.println("������������� PriorityQueue: ");
	    PriorityQueue<Character> qwe = new PriorityQueue<>();
	    qwe.offer('e');
	    qwe.offer('w');
	    qwe.offer('t');
	System.out.println("���� �����: " + qwe);
	System.out.println("������� ���� �������: " + qwe.poll());
	System.out.println("���� �����: " + qwe);
	System.out.println(qwe.peek().toString() + qwe.poll().toString() + qwe.peek().toString());
	    System.out.println();
	     
System.out.println("������������� ArrayDeque: ");
	    ArrayDeque<String> adq = new ArrayDeque<String>(); 
	    adq.push("A"); 
	    adq.push("B");  
	    adq.push("D"); 
	    adq.push("E"); 
	    adq.push("F"); 
	System.out.println("���� ������: " + adq);
	System.out.print("�������� �� � �����: ");
	    while(adq.peek() != null) 
	    	System.out.print(adq.pop() + " ");
	    System.out.println();
	    System.out.println();
	    
System.out.println("������������� ���������� iterator �� listIterator: ");
	    ArrayList<String> alStr = new ArrayList<String>(); 
	    alStr.add("C"); 
	    alStr.add("A"); 
	    alStr.add("E"); 
	    alStr.add("B"); 
	    alStr.add("D"); 
	    alStr.add("F"); 
	 
	    //����������� ��������� ��� ����������� ����� alStr. 
	System.out.print("�������� ���� alStr �� ���. iterator(): "); 
	    //�������� �������� ������ alStr:
	    Iterator<String> itr = alStr.iterator(); 
	    while(itr.hasNext()) { 
	    	//��������� ��������� �� ��������� ������� ����� ��������
	    	System.out.print(itr.next() + " "); 
	    } 
	    System.out.println(); 
	 
	System.out.println("������� ���� �� ���. listIterator()");
	    //������ ���� ������
	    ListIterator<String> litr = alStr.listIterator();  
	    while(litr.hasNext()) { 
	    	//set() ������ ������� �������, ���� ������� next()
	    	//String element = litr.next(); 
	    	//litr.set(element); 
	    	litr.set(litr.next() + "+"); //������ ���� �� ������� ��������
	    } 
	 
	System.out.print("�������� ������� ���� alStr � ���. iterator(): "); 
	    itr = alStr.iterator();  
	    while(itr.hasNext()) { 
	    	System.out.print(itr.next() + " "); 
	    } 
	    System.out.println(); 
	  
	System.out.print("�������� ���� alStr � ����������� ������� �� ���. listIterator(): "); 
	    while(litr.hasPrevious()) { 
	    	System.out.print(litr.previous() + " "); 
	    } 
	    System.out.println(); 
	    
	System.out.print("�������� ���� alStr ����� for-each: ");
	    for(String v: alStr)
	    	System.out.print(v + " ");
	    System.out.println(); 
	    System.out.println(); 
	    
System.out.println("������������ �������������� ����� � ���������");
	    class Address {
	    	private String name;  
	    	private String street;  
	    	private String city;  
	    	private String state;  
	    	private String code;  
	    	  
	    	Address(String n, String s, String c, String st, String cd) {  
	    		name = n;  
	    		street = s;  
	    		city = c;  
	    		state = st;  
	    		code = cd;  
	    	}  
	    	  
	    	public String toString() {  
	    		return name + "\n" + street + "\n" + city + " " + state + " " + code;  
	    	}  
	    }  
	    
	    LinkedList<Address> ml = new LinkedList<>();  
	    // Add elements to the linked list.
	    ml.add(new Address("J.W. West", "11 Oak Ave", "Urbana", "IL", "61801"));  
	    ml.add(new Address("Ralph Baker", "1142 Maple Lane", "Mahome", "IL", "61853"));  
	    ml.add(new Address("Tom Carlton", "867 Elm St", "Champaign", "IL", "61820"));  
	    // Display the mailing list. 
	    for(Address element : ml) 
	    	System.out.println(element + "\n");  
	    System.out.println(); 
	    
System.out.println("������������ HashMap (������. ������� ��������� ��������� get() �� put())");
	    HashMap<String, Double> hm = new HashMap<>(); 
	    //���������� ����
	    hm.put("John Doe", new Double(3434.34));  
	    hm.put("Tom Smith", new Double(123.22));  
	    hm.put("Jane Baker", new Double(1378.00));  
	    hm.put("Tod Hall", new Double(99.22));  
	    hm.put("Ralph Smith", new Double(-19.08));  
	    //�������� "������"(Set) �������� ����
	    Set<Map.Entry<String, Double>> sethm = hm.entrySet();  
	    //����������� ��������
	    for(Map.Entry<String, Double> me : sethm) { 
	    	System.out.println(me.getKey() + ": " + me.getValue());  
	    }
	    //������ ������ ����� ���
	    //���� ����� ���� ��� �, ������ ���� ������� ���� �������
	    hm.put("John Doe", hm.get("John Doe") + 1000);  
	    System.out.println("John Doe's new balance: " +  hm.get("John Doe")); 
	    System.out.println(); 
	    
System.out.println("������������ Hashtable: ������ ��������� Map, �����\n" +
		"���� ����� ���� HashMap ������� ���� ������\n");
	    Hashtable<String, Double> balance = new Hashtable<String, Double>(); 
	    	 
	    String str; 
	    double bal; 
	    	 
	    balance.put("John Doe", 3434.34); 
	    balance.put("Tom Smith", 123.22); 
	    balance.put("Jane Baker", 1378.00); 
	    balance.put("Tod Hall", 99.22); 
	    balance.put("Ralph Smith", -19.08); 
	    	 
	    //Hashtable �� ������� ���������, ���� ������������� ������ (Enumeration)
	System.out.println("Hashtable ������������ ������� (Enumeration) ��� ����������� �����: ");
	    Enumeration<String> names = balance.keys(); 
	    
	    while(names.hasMoreElements()) { 
	    	str = names.nextElement(); 
	    	System.out.println(str + ": " + balance.get(str)); 
	    } 
	    System.out.println();
	    
	    //��� ����� �������� ���� (Set), ��� ������������ ���������:
	    //������� HashTable, � ����� Set, � ����� iterator
	System.out.println("Hashtable ������������ ����������� ��������� (Set.iterator) ��� ����������� �����: ");
	    Iterator<String> itrht = balance.keySet().iterator(); 
	    
	    while(itrht.hasNext()) { 
	      str = itrht.next(); 
	      System.out.println(str + ": " + balance.get(str)); 
	    }
	    System.out.println(); 

	    // Deposit 1,000 into John Doe's account. 
	    balance.put("John Doe", balance.get("John Doe")+1000); 
	    System.out.println("John Doe's new balance: " + balance.get("John Doe")); 
	    System.out.println();
	    
System.out.println("������������ TreeMap " +
		"(SortedMap ������ �� ����! ������ CharsetTest) " +
		"(������ ��� ������������� �� �������)");
	    TreeMap<String, Double> tm = new TreeMap<>();  
	    //���������� ���� 
	    tm.put("John Doe", new Double(3434.34));  
	    tm.put("Tom Smith", new Double(123.22));  
	    tm.put("Jane Baker", new Double(1378.00));  
	    tm.put("Tod Hall", new Double(99.22));  
	    tm.put("Ralph Smith", new Double(-19.08));  
	    //�������� "������"(Set) �������� ����
	    Set<Map.Entry<String, Double>> settm = tm.entrySet(); 
	    //����������� ��������
	    for(Map.Entry<String, Double> me : settm) { 
	    	System.out.println(me.getKey() + ": " + me.getValue());    
	    }  
	    //������ ������ ����� ��� 
	    tm.put("John Doe", tm.get("John Doe") + 1000);  
	    System.out.println("John Doe's new balance: " + tm.get("John Doe"));  
	    System.out.println(); 
	    
System.out.println("�������������� ������������");
	System.out.println("�������� �������:");
		//��������� ����, �� ������ ��������� Comparator
	    class DescComp implements Comparator<String>{
	    	//�������������� ������� ���������
	    	public int compare(String a, String b){
	    		return b.compareTo(a);
	    	}
	    }
	    TreeSet<String> ts1 = new TreeSet<>(new DescComp());  
	    ts1.add("C");
	    ts1.add("A");  
	    ts1.add("B");  
	    ts1.add("E");  
	    ts1.add("F");  
	    ts1.add("D");  
	    for(String element : ts1) 
	    	System.out.print(element + " ");  
	    System.out.println(); 
	    System.out.println();
	    
	System.out.println("���������� �� �������� (���� �� ��'��):");
		//��������� ����, �� ������ ��������� Comparator
	    class LastNameComp implements Comparator<String>{
	    	//�������������� ������� ���������
	    	public int compare(String aStr, String bStr){
	    		//��������� ������� ������ (����������)
	    		int Aspace = aStr.lastIndexOf(" ");
	    		int Bspace = bStr.lastIndexOf(" ");
	    		//��������� �������
	    		int k = aStr.substring(Aspace + 1).compareTo(bStr.substring(Bspace + 1));
	    		//���� ������� ������� (k==0), �� �������� ��������� �� �������
	    		return (k == 0) ? aStr.compareTo(bStr) : k ;
	    	}
	    }
	    TreeMap<String, Double> tm1 = new TreeMap<>(new LastNameComp());  
	    tm1.put("John Doe", new Double(3434.34));  
	    tm1.put("Tom Smith", new Double(123.22));  
	    tm1.put("Jane Baker", new Double(1378.00));  
	    tm1.put("Tod Hall", new Double(99.22));  
	    tm1.put("Ralph Smith", new Double(-19.08));   
	    Set<Map.Entry<String, Double>> settm1 = tm1.entrySet();  
	    for(Map.Entry<String, Double> me : settm1) { 
	    	System.out.println(me.getKey() + ": " + me.getValue());   
	    }  
	    System.out.println(); 
	    
System.out.println("������������ ��������� ��������"); 
		// Create and initialize linked list. 
	    LinkedList<Integer> ll1 = new LinkedList<Integer>();  
	    ll1.add(-8);  
	    ll1.add(20);  
	    ll1.add(-20);  
	    ll1.add(8);  
	    
	System.out.print("������ ������������ � ����������� �������: ");  
	    // Create a reverse order comparator. 
	    Comparator<Integer> r = Collections.reverseOrder();  
	    // Sort list by using the comparator. 
	    Collections.sort(ll1, r);  
	    //Collections.sort(ll1, Collections.reverseOrder());     
	    for(int i : ll1) 
	    	System.out.print(i+ " ");  
	    System.out.println(); 
	    
	System.out.print("������������� ������: "); 
	    Collections.shuffle(ll1);        
	    for(int i : ll1) 
	    	System.out.print(i + " ");  
	    System.out.println();  
	  
	    System.out.println("̳������� ��������: " + Collections.min(ll1));      
	    System.out.println("����������� ��������: " + Collections.max(ll1));
	    System.out.println(); 
	    
System.out.print("������������ ������ ����� Array, �� ���������� ����� �� ");
System.out.println("���������� �� ��������"); 
			
		// Allocate and initialize array. 
	    int array[] = new int[10];  
	    for(int i = 0; i < 10; i++)  
	    	array[i] = -3 * i;  
	  
	System.out.print("���������� ���� ������: ");  
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println();
		
	System.out.print("³�����������: "); 
	    Arrays.sort(array);
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println();  
	  
	System.out.print("��������� ����� � 2(�������) �� 6 (���������) ���������� -1: ");  
	    Arrays.fill(array, 2, 6, -1);   
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println(); 
	  
	System.out.print("�������� ����������: ");  
	    Arrays.sort(array);
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println(); 
	  
	System.out.print("�������� -9 ����������� � �������: ");  
	    System.out.println(Arrays.binarySearch(array, -9));
	    System.out.println();
	    
System.out.println("������������ BitSet");
		BitSet bits1 = new BitSet(16), 
				bits2 = new BitSet(16);
	// set some bits
		for(int i=0; i<16; i++) {
			if((i%2) == 0) bits1.set(i);
			if((i%5) != 0) bits2.set(i);
		}
		System.out.println("���������� ������ � bits1: " + bits1);
		System.out.println("���������� ������ � bits2: " + bits2);
	System.out.println("//��������� ����������� � ��'��� ���� �������� �������");
	// AND bits
		bits2.and(bits1);
		System.out.println("bits2 AND bits1: " + bits2);		
	// OR bits
		bits2.or(bits1);
		System.out.println("bits2 OR bits1: " + bits2);
		
		// XOR bits
		bits2.xor(bits1);
		System.out.println("bits2 XOR bits1: " + bits2);
		System.out.println();
	    
System.out.println("������������ Properties (������ Hashtable) -" + 
		" ���� ������� ��� ��� �����\n������� ������� ���������" + 
		" �� ���� �������� load(), store()");
		//�������� ������� ����� �� �������������
		//(���� -- ��������)
		Properties defList = new Properties();  
		defList.put("Washington", "Olympia");  
		defList.put("Wisconsin", "Madison");  
		Properties capitals = new Properties(defList);
		
		capitals.put("Illinois", "Springfield");  
		capitals.put("Missouri", "Jefferson City");  
		capitals.put("California", "Sacramento");  
		capitals.put("Indiana", "Indianapolis");  
		
		// Get a set-view of the keys. 
		Set<?> states = capitals.keySet();
//??states.add("Florida");
		// Show all of the states and capitals. 
		for(Object name : states)  
			System.out.println("The capital of " + name + " is " +  
		                     capitals.getProperty((String)name) + ".");  
		
		// Look for state not in list -- specify default. 
		System.out.println("The capital of Florida is "  
		                   + capitals.getProperty("Florida", "Not Found") + ".");  
		
	System.out.println("������������ Properties � ����� ��");
		Properties ht = new Properties(); 
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    String name=null, number = null; 
	    FileInputStream fin = null; 
	    boolean changed = false; 
	 
//������ ������� �������� ���� phonebook.dat 
	    try {
	    	//���� ����������� � ���� � ��������
	    	fin = new FileInputStream("phonebook.dat"); 
	 
//���� ������� ���� - ����������� ����

	    	if(fin != null) { 
	    		ht.load(fin); 
	    		fin.close(); 
	    	} 
	    } catch(IOException e) { 
	    	System.out.println("������� ������� �����."); 
	    } 
	 
//��������� ����������� ������� ��� ����� �� ������ ��������
	    do { 
	    	System.out.println("������ ��'� ('�����' ��� �������): "); 
	    	try {
	    		name = br.readLine();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	
	    	if(name.equals("�����")) continue; 
	    	System.out.println("������ �����: "); 
	    	try {
	    		number = br.readLine();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} 
	 
	    	ht.put(name, number); 
	    	changed = true; 
	    } while(!name.equals("�����")); 
	 
//���� ������� ������ ���, �������� ����
	    if(changed) { 
	    	FileOutputStream fout = null;
			try {
				fout = new FileOutputStream("phonebook.dat");
				ht.store(fout, "��������� �����");
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    } 
	 
// ������ ����� �� ��'��
	    do {
	    	System.out.println("������ ��'� ��� ������ ('�����' ��� �������): "); 
	    	try {
	    		name = br.readLine();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} 
	    	if(name.equals("�����")) continue; 
	    	number = (String) ht.get(name); 
	    	System.out.println(number); 
	    } while(!name.equals("�����")); 
	}
}