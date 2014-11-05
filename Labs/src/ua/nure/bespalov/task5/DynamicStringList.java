package ua.nure.bespalov.task5;

public class DynamicStringList implements SimpleList {
	private static final int sizeStep = 3;
	private String[] strings;
	private int size = 0;
	
	public static void main(String args[])
	{
		DynamicStringList list = new DynamicStringList();
		list.add("first");
		list.add("second");
		System.out.println("First, seconds added: " + list);
		list.add("third");
		System.out.println("Third added: " + list);
		list.add("fourth");
		System.out.println("Fourth added: " + list);

		System.out.println("2 element removed: " + list.remove(2));
		System.out.println("Content: " + list);
		System.out.println("Last element removed: " + list.remove());
		System.out.println("Content: " + list);
		System.out.println("Delete result: " + list.delete());
		System.out.println("Content: " + list);
	}
	
	public DynamicStringList(){
		strings = new String[sizeStep];
	}

	public DynamicStringList(int startSize){
		strings = new String[startSize];
	}
	
	@Override
	public void add(String s) {
		if (strings.length == size)
		{
			String[] newStrings = new String[strings.length + sizeStep];
			for (int i = 0; i < size; i++)
				newStrings[i] = strings[i];
			strings = newStrings;
		}
		
		strings[size] = s;
		size++;
	}

	@Override
	public String get() {
		if (size < 1)
			return null;
		
		return strings[size - 1];
	}

	@Override
	public String get(int id) {
		if (id < 0 || id >= size)
			return null;
		
		return strings[id];
	}

	@Override
	public String remove() {
		size--;
		return strings[size];
	}

	@Override
	public String remove(int id) {
		if (id < 0 || id >= size)
			return null;
		
		String value = strings[id];

		size--;
		for (int i = id; i < size; i++)
			strings[i] = strings[i + 1];
		
		return value;
	}

	@Override
	public boolean delete() {
		if (size == 0)
			return false;
		
		for (int i = 0; i < strings.length; i++)
			strings[i] = null;
		
		strings = new String[sizeStep];
		size = 0;
		return true;
	}
	
	@Override
	public String toString() {
		if (size == 0)
			return null;
		
		StringBuilder content = new StringBuilder(strings[0]);
		for (int i = 1; i < size; i++)
			content.append(", " + strings[i]);
		return content.toString();
	}
}
