package ua.nure.shcherbatenko.course3.MiZUP.coursework;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;

public class CursMain {
	public static void main(String args[]) {
		
	    Work Cursa4 = new Work("Курсова робота Щербатенко");
	    //PrintStream out = new PrintStream(new File("CourseLog.txt"));
	    PrintStream out = System.out;
	    //Cursa4.setPrintStream(out);
	    
	    Cursa4.addWork("Верхня1", 30)
		    .addRes("Інженер", 19)
		    .addRes("Менеджер", 10)
		    .addRes("Будівельник", 30);
	    
	    Cursa4.addWork("Верхня3", 30)
		    .addRes("Менеджер", 40)
		    .addRes("Будівельник", 10)
		    .addRes("Менеджер", 20)
		    .addRes("Дизайнер", 30);
	    
	    Cursa4.addWork("Верхня2", 30)
	    	.addRes("Інженер", 30)
	    	.addPred( Cursa4.getWork("Верхня1"));
	    
	    Cursa4.getWork("Верхня2").addWork("НижДр1", 80)
	    	.addRes("Будівельник", 30);
	    
	    Cursa4.getWork("Верхня2").addWork("НижДр2", 30)
		    .addRes("Будівельник", 30)
		    .addRes("Дизайнер", 20)
		    .addRes("Інженер", 10);
	    
	    Cursa4.getWork("Верхня2").addWork("НижДр3", 30)
	    	.addRes("Дизайнер", 20);
	    
	    Cursa4.getWork("Верхня2").addWork("НижДр4", 30)
	    	.addRes("Дизайнер", 20);
	    
	    Cursa4.getWork("Верхня2").addWork("НижДр1", 20);
	    	Cursa4.getWork("Верхня2").getWork("НижДр1").addRes("Будівельник", 60);
	    
	    Cursa4.getWork("Верхня3").addWork("НижТр1", 60)
		    .addRes("Будівельник", 30)
		    .addRes("Дизайнер", 20)
		    .addRes("Інженер", 10);
	    
	    Cursa4.getWork("Верхня3").addWork("НижТр2", 30)
	    	.addRes("Дизайнер", 90)
	    	.addPred(Cursa4.getWork("Верхня3").getWork("НижТр2"));
	    
	    Cursa4.getWork("Верхня3").addWork("НижТр3", 21)
		    .addRes("Дизайнер", 20);
	    
	    Cursa4.getWork("Верхня3")
	    	.addPred(Cursa4.getWork("Верхня1"));

    	Cursa4.getWork("Верхня2").addPred( Cursa4.getWork("Верхня3").getWork("НижТр3"));
    	
    	Cursa4.getWork("Верхня2").getWork("НижДр3")
    		.addPred(Cursa4.getWork("Верхня2").getWork("НижДр2"));
    	
    	// Додаємо цикл
    	Cursa4.getWork("Верхня1").addPred(Cursa4.getWork("Верхня2").getWork("НижДр3"));
    	
    	// Додаємо роботу всередину графіку
		Cursa4.getWork("Верхня3").addWork("ТЕСТ", 10, Cursa4.getWork("Верхня3").getWork("НижТр2"));
		
		// Додаємо ще одну критичну роботу
		Cursa4.getWork("Верхня3").addWork("Друга критична", 21,Cursa4.getWork("Верхня3").getWork("ТЕСТ") )
			.addRes("Дизайнер", 81);
		Cursa4.getWork("Верхня2").getWork("НижДр1").addPred( Cursa4.getWork("Верхня3").getWork("Друга критична") );
		Cursa4.getWork("Верхня3").getWork("Друга критична").addPred( Cursa4.getWork("Верхня1") );
		//-------------------------------------------------------------------
		
		out.println();
		out.println("--------------------Перевантажені ресурси і роботи: ");
		Cursa4.showResourcesOverload();
		
		// Виводимо інформацію про проект
		out.println();
		out.println("--------------------Структура робіт");
		Cursa4.showPlan();
		
		out.println();
		out.println("--------------------Діаграма Ганта");
		Cursa4.showGuntt();
	    
	    // Виконуємо автоматичне вирівнювання ресурсів
	    out.println();
		out.println("--------------------Вирівнювання ресурсів");
	    Cursa4.alignResources();
	    
	    // Виводимо інформацію про проект
	    out.println();
		out.println("--------------------Структура робіт");
		Cursa4.showPlan();
		
		out.println();
		out.println("--------------------Діаграма Ганта");
		Cursa4.showGuntt();
	    
	    // Відображення ВІЛЬНОГО РЕЗЕРВУ
		out.println();
		out.println("--------------------Вільний резерв часу");
	    for (Work W : Cursa4.elementaryWorkArray())
	    	out.println(W + " " + W.freeReserve()/3600000);

	    // Виконуємо збереження проекту на диск
	    out.println();
		out.println("--------------------Зберегли проект на диск");
	    Cursa4.saveProject("CURS4.dat");
	    
	    // Виконуємо зчитування проекту з диску
	    out.println();
		out.println("--------------------Завантажили проект з диска");
		Work newCurs = Work.loadProject("CURS4.dat");
		//newCurs.setPrintStream(out);
	    newCurs.showPlan();
	    newCurs.showGuntt();
	    
	   // out.close();
	}
}