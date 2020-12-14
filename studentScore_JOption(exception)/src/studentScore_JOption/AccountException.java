package studentScore_JOption;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class AccountException {
	
	//����ǥ���� (�������� ����, ����+�������� ����)
	public void idCheck(String id) throws AuthenticException{
//		boolean check = Pattern.matches(" ^[a-zA-Z0-9]*$ ", id	);
		boolean check = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]*$", id	);
		if(id.length()==0) {
			JOptionPane.showMessageDialog(null, "���̵� �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("���̵� �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(id.length()>=12) {
			JOptionPane.showMessageDialog(null, "12�� �̳��� �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("12�� �̳��� �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "�������� ����, ����+����(����) ������� �Է��ϼ���.");
			throw new AuthenticException("�������� ����, ����+����(����) ������� �Է��ϼ���.");
		}
	}
	
	//��й�ȣ - ���ڸ� ����
	public void pwCheck(String pw) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", pw);
		if(pw.length()==0) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("��й�ȣ�� �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "��й�ȣ�� ���ڸ� �����մϴ�.");
			throw new AuthenticException("��й�ȣ�� ���ڸ� �����մϴ�.");
		}
	}
	
	//�ѱ�, ������ ����
	public void nonNumCheck(String input) throws AuthenticException{
		boolean check = Pattern.matches("^[��-�Ra-zA-Z]+$", input);
		if(input.length()==0) {
			JOptionPane.showMessageDialog(null, "�б��� �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("�б����� �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "�б��� �ѱ�, ������ �����մϴ�.");
			throw new AuthenticException("�б��� �ѱ�, ������ �����մϴ�.");
		}
	}
	
	//�г� - ���ڸ� ����
	public void gradeCheck(String grade) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", grade);
		if(grade.length()==0) {
			JOptionPane.showMessageDialog(null, "�г��� �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("�г��� �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "�г��� ���ڸ� �����մϴ�.");
			throw new AuthenticException("�г��� ���ڸ� �����մϴ�.");
		}
	}
	
	//���ڸ� ����
	public void numCheck(String input) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", input);
		if(input.length()==0) {
			JOptionPane.showMessageDialog(null, "�Է°��� �����ϴ�.");
			throw new AuthenticException("�Է°��� �����ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���.");
			throw new AuthenticException("���ڸ� �Է��ϼ���.");
		}
	}
	
	//�л��̸� - �ѱ�(2~5����)
	public void nameCheck(String name) throws AuthenticException{
		boolean check = Pattern.matches("^[��-�R]{2,5}$", name);
		if(name.length()==0) {
			JOptionPane.showMessageDialog(null, "�̸��� �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("�̸��� �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "�̸��� �ѱ۷θ� �ۼ����ּ���.");
			throw new AuthenticException("�̸��� �ѱ۷θ� �ۼ����ּ���.");
		}
	}
	
	//�����Է�
	public void scoreCheck(String s) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]{1,3}$", s);
		if(s.length()==0) {
			JOptionPane.showMessageDialog(null, "������ �Է����ֽñ� �ٶ��ϴ�.");
			throw new AuthenticException("������ �Է����ֽñ� �ٶ��ϴ�.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "������ 0~100�� ������ ���� �Է����ּ���.");
			throw new AuthenticException("������ 0~100�� ������ ���� �Է����ּ���.");
		}
	}
}








