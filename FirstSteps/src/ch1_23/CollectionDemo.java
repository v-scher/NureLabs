package ch1_23;
//глава 17
import java.util.*;
import java.io.*; 

public class CollectionDemo {
	public static void main(String args[]){
System.out.println("Використовуємо ArrayList:");
		ArrayList<String> al = new ArrayList<String>();
	System.out.println("Початковий розмір масива: " + al.size());
		al.add("C");
		al.add("A");
		al.add("E");
		al.add("B");
		al.add("D");
		al.add("F");
		al.add(1,"A2");
	System.out.println("Розмір alStr після вставки: " + al.size());
	System.out.println("Вміст alStr: " + al);
		al.remove("F");
		al.remove(2);
	System.out.println("Розмір alStr після видалення: " + al.size());
	System.out.println("Вміст alStr: " + al);
		System.out.println();
		
System.out.println("Використовуємо ArrayList + toArray()");
	    ArrayList<Integer> al1 = new ArrayList<Integer>();    
	    // Add elements to the array list. 
	    al1.add(1);  
	    al1.add(2);  
	    al1.add(3);  
	    al1.add(4);  
	System.out.println("Вміст alInt: " + al1);  
	    // Get the array. 
	    Integer ia[] = new Integer[al1.size()];  
	    ia = al1.toArray(ia);  
	    int sum = 0;  
	    // Sum the array. 
	    for(int i : ia) sum += i;  
	System.out.println("Сума елементів масиву alInt: " + sum);
	    System.out.println();
	    
System.out.println("Використовуємо LinkedList: ");
	    LinkedList<String> ll= new LinkedList<>();
	    ll.add("F");
	    ll.add("B");
	    ll.add("D");
	    ll.add("E");
	    ll.add("C");
	    ll.addFirst("A");
	    ll.addLast("Z");
	    ll.add(1,"A2");
	System.out.println("Початковий вміст ll: " + ll);
	    ll.remove("F");
	    ll.remove(2);
	System.out.println("Вміст ll після видалення: " + ll);
	    ll.removeFirst();
	    ll.removeLast();
	System.out.println("ll після видалення першого та останнього елементів: " + ll);
	    String val = ll.get(2);
	    ll.set(2, val + " змінений");
	System.out.println("ll після зміни: " + ll);
	    System.out.println();
	     
System.out.println("Використовуємо HashSet: ");
		HashSet<String> hs = new HashSet<>(); 
	    hs.add("B"); 
	    hs.add("A"); 
	    hs.add("D"); 
	    hs.add("E"); 
	    hs.add("C"); 
	    hs.add("F"); 
	    System.out.println(hs); 
System.out.println("Використовуємо LinkedHashSet: ");
	    LinkedHashSet<String> lhs = new LinkedHashSet<>(); 
	    lhs.add("B"); 
	    lhs.add("A"); 
	    lhs.add("D"); 
	    lhs.add("E"); 
	    lhs.add("C"); 
	    lhs.add("F"); 
	    System.out.println(lhs);
System.out.println("Використовуємо TreeSet: ");
	    TreeSet<String> ts = new TreeSet<String>(); 
	    ts.add("C"); 
	    ts.add("A"); 
	    ts.add("B"); 
	    ts.add("E"); 
	    ts.add("F"); 
	    ts.add("D"); 
	    System.out.println(ts); 
	System.out.println("Виконання ts.subSet(\"C\", \"F\"): ");
	    System.out.println(ts.subSet("C", "F"));
	    System.out.println();
	    
System.out.println("Використовуємо PriorityQueue: ");
	    PriorityQueue<Character> qwe = new PriorityQueue<>();
	    qwe.offer('e');
	    qwe.offer('w');
	    qwe.offer('t');
	System.out.println("Вміст черги: " + qwe);
	System.out.println("Витягли один елемент: " + qwe.poll());
	System.out.println("Вміст черги: " + qwe);
	System.out.println(qwe.peek().toString() + qwe.poll().toString() + qwe.peek().toString());
	    System.out.println();
	     
System.out.println("Використовуємо ArrayDeque: ");
	    ArrayDeque<String> adq = new ArrayDeque<String>(); 
	    adq.push("A"); 
	    adq.push("B");  
	    adq.push("D"); 
	    adq.push("E"); 
	    adq.push("F"); 
	System.out.println("Вміст масиву: " + adq);
	System.out.print("Витягуємо як зі стеку: ");
	    while(adq.peek() != null) 
	    	System.out.print(adq.pop() + " ");
	    System.out.println();
	    System.out.println();
	    
System.out.println("Використовуємо інтерфейси iterator та listIterator: ");
	    ArrayList<String> alStr = new ArrayList<String>(); 
	    alStr.add("C"); 
	    alStr.add("A"); 
	    alStr.add("E"); 
	    alStr.add("B"); 
	    alStr.add("D"); 
	    alStr.add("F"); 
	 
	    //Використати ітератори для відображення вмісту alStr. 
	System.out.print("Виводимо вміст alStr за доп. iterator(): "); 
	    //отримуємо ітератор масиву alStr:
	    Iterator<String> itr = alStr.iterator(); 
	    while(itr.hasNext()) { 
	    	//отримання посилання на наступний елемент через ітератор
	    	System.out.print(itr.next() + " "); 
	    } 
	    System.out.println(); 
	 
	System.out.println("Змінюємо вміст за доп. listIterator()");
	    //Змінити вміст масива
	    ListIterator<String> litr = alStr.listIterator();  
	    while(litr.hasNext()) { 
	    	//set() замінює останній елемент, який повертає next()
	    	//String element = litr.next(); 
	    	//litr.set(element); 
	    	litr.set(litr.next() + "+"); //додаємо плюс до кожного елемента
	    } 
	 
	System.out.print("Виводимо змінений вміст alStr з доп. iterator(): "); 
	    itr = alStr.iterator();  
	    while(itr.hasNext()) { 
	    	System.out.print(itr.next() + " "); 
	    } 
	    System.out.println(); 
	  
	System.out.print("Виводимо вміст alStr в зворотньому порядку за доп. listIterator(): "); 
	    while(litr.hasPrevious()) { 
	    	System.out.print(litr.previous() + " "); 
	    } 
	    System.out.println(); 
	    
	System.out.print("Виводимо вміст alStr через for-each: ");
	    for(String v: alStr)
	    	System.out.print(v + " ");
	    System.out.println(); 
	    System.out.println(); 
	    
System.out.println("Використання користувацьких класів в колекціях");
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
	    
System.out.println("Використання HashMap (забезп. постійну тривалість виконання get() та put())");
	    HashMap<String, Double> hm = new HashMap<>(); 
	    //заповнюємо мапу
	    hm.put("John Doe", new Double(3434.34));  
	    hm.put("Tom Smith", new Double(123.22));  
	    hm.put("Jane Baker", new Double(1378.00));  
	    hm.put("Tod Hall", new Double(99.22));  
	    hm.put("Ralph Smith", new Double(-19.08));  
	    //отримуємо "перелік"(Set) елементів мапи
	    Set<Map.Entry<String, Double>> sethm = hm.entrySet();  
	    //відомбражамо елементи
	    for(Map.Entry<String, Double> me : sethm) { 
	    	System.out.println(me.getKey() + ": " + me.getValue());  
	    }
	    //змінимо баланс Джона Доу
	    //якщо такий ключ вже є, баланс його рахунку буде заміщено
	    hm.put("John Doe", hm.get("John Doe") + 1000);  
	    System.out.println("John Doe's new balance: " +  hm.get("John Doe")); 
	    System.out.println(); 
	    
System.out.println("Використання Hashtable: реалізує інтерфейс Map, проте\n" +
		"більш свіжий клас HashMap повністю його замінює\n");
	    Hashtable<String, Double> balance = new Hashtable<String, Double>(); 
	    	 
	    String str; 
	    double bal; 
	    	 
	    balance.put("John Doe", 3434.34); 
	    balance.put("Tom Smith", 123.22); 
	    balance.put("Jane Baker", 1378.00); 
	    balance.put("Tod Hall", 99.22); 
	    balance.put("Ralph Smith", -19.08); 
	    	 
	    //Hashtable не підтримує ітератори, тому використовуємо перелік (Enumeration)
	System.out.println("Hashtable використання переліків (Enumeration) для відображення даних: ");
	    Enumeration<String> names = balance.keys(); 
	    
	    while(names.hasMoreElements()) { 
	    	str = names.nextElement(); 
	    	System.out.println(str + ": " + balance.get(str)); 
	    } 
	    System.out.println();
	    
	    //Але можна отримати набір (Set), для використання ітераторів:
	    //повертає HashTable, з нього Set, з нього iterator
	System.out.println("Hashtable використання традиційних ітераторів (Set.iterator) для відображення даних: ");
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
	    
System.out.println("Використання TreeMap " +
		"(SortedMap робить те саме! Дивись CharsetTest) " +
		"(зберігає дані відсортованими за ключами)");
	    TreeMap<String, Double> tm = new TreeMap<>();  
	    //заповнюємо мапу 
	    tm.put("John Doe", new Double(3434.34));  
	    tm.put("Tom Smith", new Double(123.22));  
	    tm.put("Jane Baker", new Double(1378.00));  
	    tm.put("Tod Hall", new Double(99.22));  
	    tm.put("Ralph Smith", new Double(-19.08));  
	    //отримуємо "перелік"(Set) елементів мапи
	    Set<Map.Entry<String, Double>> settm = tm.entrySet(); 
	    //відомбражамо елементи
	    for(Map.Entry<String, Double> me : settm) { 
	    	System.out.println(me.getKey() + ": " + me.getValue());    
	    }  
	    //змінимо баланс Джона Доу 
	    tm.put("John Doe", tm.get("John Doe") + 1000);  
	    System.out.println("John Doe's new balance: " + tm.get("John Doe"));  
	    System.out.println(); 
	    
System.out.println("Перевизначення порівняльника");
	System.out.println("Зворотній порядок:");
		//створюємо клас, що реалізує інтерфейс Comparator
	    class DescComp implements Comparator<String>{
	    	//перевизначення функції порівняння
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
	    
	System.out.println("Сортування за прізвищем (потім за ім'ям):");
		//створюємо клас, що реалізує інтерфейс Comparator
	    class LastNameComp implements Comparator<String>{
	    	//перевизначення функції порівняння
	    	public int compare(String aStr, String bStr){
	    		//знаходимо позицію пробіла (роздільника)
	    		int Aspace = aStr.lastIndexOf(" ");
	    		int Bspace = bStr.lastIndexOf(" ");
	    		//порівнюємо прізвища
	    		int k = aStr.substring(Aspace + 1).compareTo(bStr.substring(Bspace + 1));
	    		//якщо прізвища однакові (k==0), то виконуємо порівняння за іменами
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
	    
System.out.println("Використання алгоритмів колекцій"); 
		// Create and initialize linked list. 
	    LinkedList<Integer> ll1 = new LinkedList<Integer>();  
	    ll1.add(-8);  
	    ll1.add(20);  
	    ll1.add(-20);  
	    ll1.add(8);  
	    
	System.out.print("Список відсортований в зворотньому порядку: ");  
	    // Create a reverse order comparator. 
	    Comparator<Integer> r = Collections.reverseOrder();  
	    // Sort list by using the comparator. 
	    Collections.sort(ll1, r);  
	    //Collections.sort(ll1, Collections.reverseOrder());     
	    for(int i : ll1) 
	    	System.out.print(i+ " ");  
	    System.out.println(); 
	    
	System.out.print("Перетасований список: "); 
	    Collections.shuffle(ll1);        
	    for(int i : ll1) 
	    	System.out.print(i + " ");  
	    System.out.println();  
	  
	    System.out.println("Мінімальне значення: " + Collections.min(ll1));      
	    System.out.println("Максимальне значення: " + Collections.max(ll1));
	    System.out.println(); 
	    
System.out.print("Використання методів класу Array, які заповнюють пробіл між ");
System.out.println("колекціями та масивами"); 
			
		// Allocate and initialize array. 
	    int array[] = new int[10];  
	    for(int i = 0; i < 10; i++)  
	    	array[i] = -3 * i;  
	  
	System.out.print("Початковий вміст масиву: ");  
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println();
		
	System.out.print("Відсортований: "); 
	    Arrays.sort(array);
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println();  
	  
	System.out.print("заповнити масив з 2(включно) до 6 (невключно) значеннями -1: ");  
	    Arrays.fill(array, 2, 6, -1);   
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println(); 
	  
	System.out.print("Повторне сортування: ");  
	    Arrays.sort(array);
	    for(int i: array) 
			System.out.print(i + " ");  
		System.out.println(); 
	  
	System.out.print("Значення -9 знаходиться в позиції: ");  
	    System.out.println(Arrays.binarySearch(array, -9));
	    System.out.println();
	    
System.out.println("Використання BitSet");
		BitSet bits1 = new BitSet(16), 
				bits2 = new BitSet(16);
	// set some bits
		for(int i=0; i<16; i++) {
			if((i%2) == 0) bits1.set(i);
			if((i%5) != 0) bits2.set(i);
		}
		System.out.println("Початковий шаблон в bits1: " + bits1);
		System.out.println("Початковий шаблон в bits2: " + bits2);
	System.out.println("//Результат повертається в об'єкт який викликав функцію");
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
	    
System.out.println("Використання Properties (підклас Hashtable) -" + 
		" дуже зручний для баз даних\nзавдяки легкому зберіганню" + 
		" на диск методами load(), store()");
		//зберігаємо столиці штатів за замовчуванням
		//(ключ -- значення)
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
		
	System.out.println("Використання Properties в якості БД");
		Properties ht = new Properties(); 
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
	    String name=null, number = null; 
	    FileInputStream fin = null; 
	    boolean changed = false; 
	 
//Спроба відкрити існуючий файл phonebook.dat 
	    try {
	    	//файл знаходиться у теці з проектом
	    	fin = new FileInputStream("phonebook.dat"); 
	 
//Якщо довідник існує - завантажити його

	    	if(fin != null) { 
	    		ht.load(fin); 
	    		fin.close(); 
	    	} 
	    } catch(IOException e) { 
	    	System.out.println("Помилка читання файлу."); 
	    } 
	 
//Дозволити користувачу вносити нові імена та номери телефонів
	    do { 
	    	System.out.println("Введіть ім'я ('вихід' для зупинки): "); 
	    	try {
	    		name = br.readLine();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	
	    	if(name.equals("вихід")) continue; 
	    	System.out.println("Введіть номер: "); 
	    	try {
	    		number = br.readLine();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} 
	 
	    	ht.put(name, number); 
	    	changed = true; 
	    } while(!name.equals("вихід")); 
	 
//Якщо довідник зазнав змін, зберегти його
	    if(changed) { 
	    	FileOutputStream fout = null;
			try {
				fout = new FileOutputStream("phonebook.dat");
				ht.store(fout, "Телефонна книга");
				fout.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	    } 
	 
// Шукати номер за ім'ям
	    do {
	    	System.out.println("Введіть ім'я для пошуку ('вихід' для зупинки): "); 
	    	try {
	    		name = br.readLine();
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	} 
	    	if(name.equals("вихід")) continue; 
	    	number = (String) ht.get(name); 
	    	System.out.println(number); 
	    } while(!name.equals("вихід")); 
	}
}