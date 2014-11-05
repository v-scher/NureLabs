package ch1_23;
enum Apple{
	Jon(10), Gol(9), Red(12), Win(15), Cor(8);
	
	private int price;
	
	Apple(int p){ price = p; }
	
	Apple(){ price = -1; }
	
	int getPrice() { return price; }
}

/* Перелік можна порівнювати за допомогою ==
 * його можна використовувати в операторі switch()
 * 
 * перелік - це повноцінний клас
 * 
 * 
 */
class EnumDemo{
	public static void main(String args[]){
		Apple ap;
		
		//виймання ціни
		System.out.println("Win коштує " + Apple.Win.getPrice() + "\n");
		System.out.println("Константи Apple:");
		
		//виймання усіх значень та цін з переліку
		Apple allaps[] = Apple.values();
		for(Apple a: allaps)
			System.out.println(a + ", ціна: " + a.getPrice());
		System.out.println();
		
		//виймання одного значення
		ap = Apple.valueOf("Red");
		System.out.println("ap містить " + ap + "\n\nІнший приклад:\n");
		
		//отримаємо всі порядкові номери
		System.out.println("Це всі константи та їх порядкові номери: ");
		for(Apple a:Apple.values())
			System.out.println(a + " " + a.ordinal());
		
		//compareTo(), equals() та ==
		Apple ap1,ap2,ap3;
		ap1 = Apple.Red;
		ap2 = Apple.Gol;
		ap3 = Apple.Red;
		System.out.println();
		
		if(ap1.compareTo(ap2) < 0)
			System.out.println(ap1 + " йде перед " + ap2);
		
		if(ap1.compareTo(ap2) > 0)
			System.out.println(ap2 + " йде перед " + ap1);
		
		if(ap1.compareTo(ap3) == 0)
			System.out.println(ap + " дорівнює " + ap3);
		
		System.out.println();
		
		if(ap1.equals(ap2))
			System.out.println("Error!");
			
		if(ap1.equals(ap3))
			System.out.println(ap1 + " equals " + ap3);
			
		if(ap1 == ap3)
			System.out.println(ap1 + " == " + ap3);
	}
}