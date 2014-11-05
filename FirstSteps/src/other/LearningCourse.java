package other;

class Lecture {
	private String day;
	private String theme;
	private int students;

	Lecture (String day, String theme, int students) {
		this.setDay(day);
		this.setTheme(theme);
		this.setStudents(students);
	}

	public int getStudents() {return students;}

	public void setStudents(int students) {this.students = students;}

	public String getTheme() {return theme;}

	public void setTheme(String theme) {this.theme = theme;}

	public String getDay() {return day;}

	public void setDay(String day) {this.day = day;}
}

class LearningCourse {
	private String courseName;
	private String teacherName;
	private Lecture lectures[];
	
	LearningCourse(String courseName, String teacherName) {
		this.courseName = courseName;
		this.teacherName = teacherName;
		this.lectures = null;
	}
	
	public String getCourseName() {return courseName;}
	
	public void setCourseName(String courseName) {this.courseName = courseName;}
	
	public String getTeacherName() {return teacherName;}
	
	public void setTeacherName(String teacherName) {this.teacherName = teacherName;}

	public Lecture[] getLectures() {return lectures;}

	public void setLectures(Lecture lectures[]) {this.lectures = lectures;}
	
	public Lecture getMinStudentsLecture() {
		Lecture min = this.lectures[0];
		
		for (int i = 1; i < this.lectures.length; i++) {
			if (this.lectures[i].getStudents() < min.getStudents())
				min = this.lectures[i];
		}
		
		return min;
	}
	
	public String[] getThemes(String Word) {
		String[] tmp = new String[this.lectures.length];
		
		int q = 0;
		for (int i = 1; i < this.lectures.length; i++) {
			if (this.lectures[i].getTheme().contains(Word))
				tmp[q++] = this.lectures[i].getTheme();
		}
		
		String[] themes = new String[q];
		for (int i = 0; i < q; i++)
			themes[i] = tmp[i];
		
		return themes;
	}

	public char getLastLetter() {
		return this.teacherName.charAt(this.teacherName.indexOf(' ')-1);
	}

	public static void main(String args[]) {
		LearningCourse Course = new LearningCourse("Програмування", "Петров Петро Петрович");
		Course.setCourseName("Інформаційні технології");
		
		Lecture lectures[] = new Lecture[5];
		lectures[0] = new Lecture("13/12/12", "програмування", 15);
		lectures[0].setStudents(12);
		lectures[1] = new Lecture("12/12/12", "інформатика", 15);
		lectures[2] = new Lecture("3/11/12", "програмування Java", 14);
		lectures[3] = new Lecture("13/12/11", "інформаційні технології", 15);
		lectures[4] = new Lecture("13/5/12", "алгоритми", 9);
		Course.setLectures(lectures);
		
		System.out.println("Назва курсу: " + Course.getCourseName());
		System.out.println("Ім'я викладача: " + Course.getTeacherName());
		System.out.println("Остання літера: " + Course.getLastLetter());
		
		System.out.println("Теми зі словом \"інформ\"");
		String[] themes = Course.getThemes("інформ");
		for (int i = 0; i < themes.length; i++)
			System.out.println(themes[i]);
		
	}
}
