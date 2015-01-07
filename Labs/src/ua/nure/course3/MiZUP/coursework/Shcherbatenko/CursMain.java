package ua.nure.course3.MiZUP.coursework.Shcherbatenko;
import java.io.PrintStream;

public class CursMain {
	public static void main(String args[]) {
		
	    Work Cursa4 = new Work("������� ������ ����������");
	    //PrintStream out = new PrintStream(new File("CourseLog.txt"));
	    PrintStream out = System.out;
	    //Cursa4.setPrintStream(out);
	    
	    Cursa4.addWork("������1", 30)
		    .addRes("�������", 19)
		    .addRes("��������", 10)
		    .addRes("����������", 30);
	    
	    Cursa4.addWork("������3", 30)
		    .addRes("��������", 40)
		    .addRes("����������", 10)
		    .addRes("��������", 20)
		    .addRes("��������", 30);
	    
	    Cursa4.addWork("������2", 30)
	    	.addRes("�������", 30)
	    	.addPred( Cursa4.getWork("������1"));
	    
	    Cursa4.getWork("������2").addWork("�����1", 80)
	    	.addRes("����������", 30);
	    
	    Cursa4.getWork("������2").addWork("�����2", 30)
		    .addRes("����������", 30)
		    .addRes("��������", 20)
		    .addRes("�������", 10);
	    
	    Cursa4.getWork("������2").addWork("�����3", 30)
	    	.addRes("��������", 20);
	    
	    Cursa4.getWork("������2").addWork("�����4", 30)
	    	.addRes("��������", 20);
	    
	    Cursa4.getWork("������2").addWork("�����1", 20);
	    	Cursa4.getWork("������2").getWork("�����1").addRes("����������", 60);
	    
	    Cursa4.getWork("������3").addWork("�����1", 60)
		    .addRes("����������", 30)
		    .addRes("��������", 20)
		    .addRes("�������", 10);
	    
	    Cursa4.getWork("������3").addWork("�����2", 30)
	    	.addRes("��������", 90)
	    	.addPred(Cursa4.getWork("������3").getWork("�����2"));
	    
	    Cursa4.getWork("������3").addWork("�����3", 21)
		    .addRes("��������", 20);
	    
	    Cursa4.getWork("������3")
	    	.addPred(Cursa4.getWork("������1"));

    	Cursa4.getWork("������2").addPred( Cursa4.getWork("������3").getWork("�����3"));
    	
    	Cursa4.getWork("������2").getWork("�����3")
    		.addPred(Cursa4.getWork("������2").getWork("�����2"));
    	
    	// ������ ����
    	Cursa4.getWork("������1").addPred(Cursa4.getWork("������2").getWork("�����3"));
    	
    	// ������ ������ ��������� �������
		Cursa4.getWork("������3").addWork("����", 10, Cursa4.getWork("������3").getWork("�����2"));
		
		// ������ �� ���� �������� ������
		Cursa4.getWork("������3").addWork("����� ��������", 21,Cursa4.getWork("������3").getWork("����") )
			.addRes("��������", 81);
		Cursa4.getWork("������2").getWork("�����1").addPred( Cursa4.getWork("������3").getWork("����� ��������") );
		Cursa4.getWork("������3").getWork("����� ��������").addPred( Cursa4.getWork("������1") );
		//-------------------------------------------------------------------
		
		out.println();
		out.println("--------------------������������ ������� � ������: ");
		Cursa4.showResourcesOverload();
		
		// �������� ���������� ��� ������
		out.println();
		out.println("--------------------��������� ����");
		Cursa4.showPlan();
		
		out.println();
		out.println("--------------------ĳ������ �����");
		Cursa4.showGuntt();
	    
	    // �������� ����������� ����������� �������
	    out.println();
		out.println("--------------------����������� �������");
	    Cursa4.alignResources();
	    
	    // �������� ���������� ��� ������
	    out.println();
		out.println("--------------------��������� ����");
		Cursa4.showPlan();
		
		out.println();
		out.println("--------------------ĳ������ �����");
		Cursa4.showGuntt();
	    
	    // ³���������� ²������ �������
		out.println();
		out.println("--------------------³����� ������ ����");
	    for (Work W : Cursa4.elementaryWorkArray())
	    	out.println(W + " " + W.freeReserve()/3600000);

	    // �������� ���������� ������� �� ����
	    out.println();
		out.println("--------------------�������� ������ �� ����");
	    Cursa4.saveProject("CURS4.dat");
	    
	    // �������� ���������� ������� � �����
	    out.println();
		out.println("--------------------����������� ������ � �����");
		Work newCurs = Work.loadProject("CURS4.dat");
		//newCurs.setPrintStream(out);
	    newCurs.showPlan();
	    newCurs.showGuntt();
	    
	   // out.close();
	}
}