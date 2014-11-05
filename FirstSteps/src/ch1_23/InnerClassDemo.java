package ch1_23;
import java.applet.*;
import java.awt.event.*;
/*
  <applet code="MousePressedDemo" width=200 height=100>
  </applet>
 
*/
 
public class InnerClassDemo extends Applet {
	public void init() {
		addMouseListener(new MyMouseAdapter1(this));
	}
}

class MyMouseAdapter1 extends MouseAdapter {
	// ����� ���� �������� �������� �� ���� ������
	InnerClassDemo mousePressedDemo;
	
	// ����������� ������ ��������, ��� ��������� showStatus ����� �����
	public MyMouseAdapter1(InnerClassDemo mousePressedDemo) {
		this.mousePressedDemo = mousePressedDemo;
	}
	
	public void mousePressed(MouseEvent me) {
		mousePressedDemo.showStatus("Mouse Pressed.");
	}
}

/*
public class InnerClassDemo extends Applet {
	public void init() {
		addMouseListener(new MyMouseAdapter());
	}
	
	// ������� �� � ������ �������� ��������� �����, 
	// �� �� ������ �� ������ showStatus �������������,
	// � ���� �� ������� �������� �������� �� InnerClassDemo

	class MyMouseAdapter extends MouseAdapter {
		public void mousePressed(MouseEvent me) {
			showStatus("Mouse Pressed");
		}
	}
}
*/

/*
public class InnerClassDemo extends Applet {
	public void init() {
		addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me) {
				showStatus("Mouse Pressed");
			}
		});
	}
}
*/