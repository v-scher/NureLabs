package ch1_23;
import java.lang.annotation.*;
import java.lang.reflect.*;

//необхідно вказати, шо ця анотація доступна протягом виконання програми:
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno{
	String str();
	int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What{
	String descr() default "Порожня аннотація";
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker{}

@What (descr = "Аннотація тестового класу")
@MyAnno(str = "AnnotDemo class annot", val = 99)
class AnnotDemo{
	//Аннотувати метод.
	@What
	@MyAnno(str = "Приклад аннотації методу", val = 100)
	public static void myMeth1(){
		AnnotDemo ob = new AnnotDemo();
		
		//отримати аннотацію з метода
		//та відобразити значення членів
		try{
			//Для початку отримаємо Class,
			//що представляє клас.
			Class<?> c = ob.getClass();//Meta.class;
			
			//Тепер отримаємо об'єкт Method,
			//що представляє цей метод
			Method m = c.getMethod("myMeth1");
			
			//Далі отримаємо аннотацію класа
			MyAnno anno = m.getAnnotation(MyAnno.class);
			
			//відобразити аннотацію
			System.out.println(anno.str() + " " + anno.val());
			System.out.println();
			
			System.out.println("Всі аннотації для AnnotDemo");
			Annotation annos[] = ob.getClass().getAnnotations();
			for(Annotation a:annos){
				System.out.println(a);
			}

			System.out.println("Всі аннотації для myMeth1");
			m = ob.getClass().getMethod("myMeth1");
			annos = m.getAnnotations();
			for(Annotation a:annos){
				System.out.println(a);
			}
			
			System.out.println();
		} catch(NoSuchMethodException ex){
			System.out.println("Метод не знайдено");
		}
	}
	
	@MyMarker
	@MyAnno(str = "Два параметри", val = 19)
	public static void myMeth2(String str1, int i){
		AnnotDemo ob = new AnnotDemo();
		
		//отримати аннотацію з метода
		//та відобразити значення членів
		try{
			Class<?> c = ob.getClass();//AnnotDemo.class;
			
			Method m = c.getMethod("myMeth2",String.class, int.class);

			MyAnno anno = m.getAnnotation(MyAnno.class);
			
			System.out.println(anno.str() + " " + anno.val());
		} catch(NoSuchMethodException ex){
			System.out.println("Метод не знайдено");
		}
	}
	
	public static void main(String args[]){
		myMeth2("1",1);
		myMeth1();
		
		try{
			if(new AnnotDemo().getClass().getMethod("myMeth2",String.class, int.class).isAnnotationPresent(MyMarker.class))
				System.out.println("Мітка на місці!");
		} catch(NoSuchMethodException ex){
			System.out.println("Метод не знайдено");
		}
	}
}