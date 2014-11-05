package ch1_23;
interface IntStack {
	void push(int item);
	int pop();
}

class FStack implements IntStack {
	private int stck[];
	private int tos;
	
	FStack(int size){
		stck = new int[size];
		tos=-1;
	}
	
	public void push(int item){
		if(tos==stck.length-1)
			System.out.println("Стек заповнено.");
		else
			stck[++tos] = item;
	}
	
	public int pop(){
		if(tos<0){
			System.out.println("Стек порожній.");
			return 0;
		}
		else
			return stck[tos--];
	}
}

class DStack implements IntStack {
	private int stck[];
	private int tos;
	
	DStack(int size){
		stck = new int[size];
		tos=-1;
	}
	
	public void push(int item){
		if(tos==stck.length-1){
			int temp[] = new int[stck.length*2];
			for(int i = 0; i<stck.length;i++)
				temp[i]=stck[i];
			stck = temp;
			stck[++tos] = item;
		}
		else
			stck[++tos] = item;
	}
	
	public int pop(){
		if(tos<0){
			System.out.println("Стек порожній.");
			return 0;
		}
		else
			return stck[tos--];
	}
}

public class IFaceDemo {
	public static void qmain(String args[]){
		IntStack myst;
		FStack my1 = new FStack(5);
		DStack my2 = new DStack(5);
		
		myst = my1;
		for(int i=0;i<10;i++)
			myst.push(i);
		myst = my2;
		for(int i=0;i<10;i++)
			myst.push(i);
		
		myst = my1;
		System.out.println("Стек в my1: ");
		for(int i=0;i<10;i++)
			System.out.println(myst.pop());
		
		myst = my2;
		System.out.println("Стек в my2: ");
		for(int i=0;i<10;i++)
			System.out.println(myst.pop());
	}
}
