package ch1_23;
class ChainExcDemo {
	static void demoproc(){
		NullPointerException e =  new NullPointerException("������ �����");
		//������ ����������-�������
		e.initCause(new ArithmeticException("������ �������"));
		throw e;
	}
	
	public static void main(String args[]){
		try{
			demoproc();
		} catch(NullPointerException e){
			//�������� ����������� ����������
			System.out.println("�����������: " + e);
			
			//�������� ����������-�������
			System.out.println("��������� �������: " + e.getCause());
		}
	}
}