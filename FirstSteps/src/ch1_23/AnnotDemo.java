package ch1_23;
import java.lang.annotation.*;
import java.lang.reflect.*;

//��������� �������, �� �� �������� �������� �������� ��������� ��������:
@Retention(RetentionPolicy.RUNTIME)
@interface MyAnno{
	String str();
	int val();
}

@Retention(RetentionPolicy.RUNTIME)
@interface What{
	String descr() default "������� ���������";
}

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker{}

@What (descr = "��������� ��������� �����")
@MyAnno(str = "AnnotDemo class annot", val = 99)
class AnnotDemo{
	//���������� �����.
	@What
	@MyAnno(str = "������� ��������� ������", val = 100)
	public static void myMeth1(){
		AnnotDemo ob = new AnnotDemo();
		
		//�������� ��������� � ������
		//�� ���������� �������� �����
		try{
			//��� ������� �������� Class,
			//�� ����������� ����.
			Class<?> c = ob.getClass();//Meta.class;
			
			//����� �������� ��'��� Method,
			//�� ����������� ��� �����
			Method m = c.getMethod("myMeth1");
			
			//��� �������� ��������� �����
			MyAnno anno = m.getAnnotation(MyAnno.class);
			
			//���������� ���������
			System.out.println(anno.str() + " " + anno.val());
			System.out.println();
			
			System.out.println("�� ��������� ��� AnnotDemo");
			Annotation annos[] = ob.getClass().getAnnotations();
			for(Annotation a:annos){
				System.out.println(a);
			}

			System.out.println("�� ��������� ��� myMeth1");
			m = ob.getClass().getMethod("myMeth1");
			annos = m.getAnnotations();
			for(Annotation a:annos){
				System.out.println(a);
			}
			
			System.out.println();
		} catch(NoSuchMethodException ex){
			System.out.println("����� �� ��������");
		}
	}
	
	@MyMarker
	@MyAnno(str = "��� ���������", val = 19)
	public static void myMeth2(String str1, int i){
		AnnotDemo ob = new AnnotDemo();
		
		//�������� ��������� � ������
		//�� ���������� �������� �����
		try{
			Class<?> c = ob.getClass();//AnnotDemo.class;
			
			Method m = c.getMethod("myMeth2",String.class, int.class);

			MyAnno anno = m.getAnnotation(MyAnno.class);
			
			System.out.println(anno.str() + " " + anno.val());
		} catch(NoSuchMethodException ex){
			System.out.println("����� �� ��������");
		}
	}
	
	public static void main(String args[]){
		myMeth2("1",1);
		myMeth1();
		
		try{
			if(new AnnotDemo().getClass().getMethod("myMeth2",String.class, int.class).isAnnotationPresent(MyMarker.class))
				System.out.println("̳��� �� ����!");
		} catch(NoSuchMethodException ex){
			System.out.println("����� �� ��������");
		}
	}
}