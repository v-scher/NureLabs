package other;

class Men {
	String name;
	String surname;
	String birthday;
	String telefon;

	Men(String name, String surname, String birthday, String telefon) {
		this.name = name;
		this.surname = surname;
		this.birthday = birthday;
		this.telefon = telefon;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

}

class Notebook {
	Men characters[];

	public Men[] getCharacters() {
		return characters;
	}

	public void setCharacters(Men[] characters) {
		this.characters = characters;
	}

	public Notebook(Men characters[]) {
		this.characters = characters;
	}

	public boolean compare(String fam) {
		for (int i = 0; i < characters.length; i++) {

			if (characters[i].surname.compareTo(fam) == 0) {
				return true;
			}
		}
		return false;
	}

	public void print() {
		for (int i = 0; i < characters.length; i++) {
			System.out.println(characters[i].name + " " + characters[i].surname
					+ " " + characters[i].birthday + " "
					+ characters[i].telefon + " ");
		}
	}

	public void delate(int idx) {
		if (idx > characters.length-1)
			return;

		Men newcharacters[] = new Men[characters.length - 1];
		int q = 0;
		for (int i = 0; i < characters.length; i++) {
			if (idx != i) {
				newcharacters[q] = characters[i];
				q++;
			}
		}
		characters = newcharacters;
	}

	public void add(Men ob1) {
		Men newcharacters[] = new Men[characters.length + 1];
		for (int i = 0; i < characters.length; i++) {
			newcharacters[i] = characters[i];
		}
		newcharacters[characters.length] = ob1;
		characters = newcharacters;
	}

	public static void main(String[] args) {
		Men[] ob = { new Men("Karl", "ivanov", "03.04.1992", "56577"),
				new Men("John", "sidorov", "11.12.1990", "343536"),
				new Men("Kate", "Pirse", "31.07.1991", "9857464"),
				new Men("Inna", "Olifenko", "29.10.1994", "123456"),
				new Men("Jakson", "Colby", "12.09.1989", "164376068") };
		Notebook notebook = new Notebook(ob);
		if (notebook.compare("sidorov"))
			System.out.println("«апись содержит фамилию sidorov");
		else
			System.out.println("«апись не содержит фамилию sidorov");

		notebook.print();
		System.out.println("--------------------");
		notebook.delate(3);
		notebook.print();
		Men ob1 = new Men("Tanya", "ivanenko", "15.07.1998", "82637489");// создали
																			// новую
																			// запись
		notebook.add(ob1);
	}
}