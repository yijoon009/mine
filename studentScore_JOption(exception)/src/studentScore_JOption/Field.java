package studentScore_JOption;
import java.awt.HeadlessException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;


public class Field {
	
	ArrayList<Account> acList = new ArrayList<>();
	ArrayList<Student> stuList;
	Map<Account,ArrayList<Student>> dataBase = new HashMap<>();
	Account ac;
	Student st;
	Matcher m;
	AccountException ae = new AccountException();
	
	//���� ùȭ�� (ȸ������, �α���)
	public void startView() throws AuthenticException  {
		
		int choice = 0;
		String[] menu = {"ȸ������", "�α���"};
		while(true) {
			choice = JOptionPane.showOptionDialog(null, "�л����� ���α׷��� �����մϴ�.\n ���ο� ����ڴ� [ȸ������]��, ���� ����ڴ� [�α���]�� �����ּ���.", "�л����� ���α׷�", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, menu, null);
			if(choice== -1) System.exit(0);
			switch(choice) {
			case 0://ȸ������
				setUser();
				break;
			case 1://�α���
				login();
				break;
			}//end switch
		}//end while
	}
	
	//ȸ������
	public void setUser() throws AuthenticException{
		boolean id = true, pw = true, data = true, schoolCh = true, gradeCh = true, classCh = true;
		AccountException ae = new AccountException();
		
		//ȸ������id
		do {
			String input = null;
			try {
				input = JOptionPane.showInputDialog("���̵� �Է����ּ���. (�������� ����, 12�� �̳� (���� ���� ����))");
				ae.idCheck(input);
				if(acList.size()==0) {
					ac = new Account();
					ac.setId(input);
					id = false;
				}else {
					for(Account alid : acList) {
						if(input.equals(alid.getId())){
							JOptionPane.showMessageDialog(null, "�̹� �����ϴ� ID�Դϴ�.");
						}else {
							ac = new Account();
							ac.setId(input);
							id = false;
						}
					}
				}
			} catch (Exception e) {;}
		}while(id);
		
		//ȸ������pw
		do {
			String input;
			try {
				input = JOptionPane.showInputDialog("��й�ȣ�� �Է����ּ���. (���ڸ� ����)");
				ae.pwCheck(input);
				ac.setPw(input);
				pw = false;
				
			} catch (Exception e) {;}
		}while(pw);
		
		//ùȭ�� �б�,�г�,�� �Է�
		do {
			do{
				String school;
				try {
					school = JOptionPane.showInputDialog("�б����� �Է��ϼ���. (�ѱ�, ������ ����)");
					ae.nonNumCheck(school);
					ac.setSchool(school);
					schoolCh = false;
				} catch (Exception e) {;}
			}while(schoolCh);
			
			do {
				String grade;
				try {
					grade = JOptionPane.showInputDialog("�г��� �Է��ϼ���. (���ڸ� ����)");
					ae.gradeCheck(grade);
					ac.setGrade(grade);
					gradeCh = false;
				} catch (Exception e) {;}
			}while(gradeCh);

			do {
				String class_name = JOptionPane.showInputDialog("���� �Է��ϼ���.");
				if(class_name.length() == 0) {
					JOptionPane.showMessageDialog(null, "���� �Է��ϼ���.");
				}else {
					ac.setClass_name(class_name);
					classCh = false;
				}
			}while(classCh);
			data = false;
		}while(data);
		JOptionPane.showMessageDialog(null, "�����մϴ�!\nȸ�������� �Ϸ�Ǿ����ϴ�.");
		acList.add(ac);
		

	}//end setUser
	
	//�α���
	public void login() throws AuthenticException {
		boolean idCh = true, pwCh = false;
		Account user = null;
		//user ���������� �־��?
		
		//id check
		while(idCh) {
			String id = JOptionPane.showInputDialog("���̵� �Է��ϼ���.");
			Iterator<Account> iter = acList.iterator();
			while(iter.hasNext()) {
				Account acCh = iter.next();
				if(acCh.getId().equals(id)){
					user = acCh;
					idCh = false;
					pwCh = true;
				}
			}
			if(user==null) {
				JOptionPane.showMessageDialog(null, "�������� �ʰų�, Ʋ�� ID�Դϴ�.");
			}
		}
		while(pwCh) {
			String pw = JOptionPane.showInputDialog("��й�ȣ�� �Է��ϼ���.");
			if(user.getPw().equals(pw)) {
				menuView(user);
				break;
			}else {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� Ʋ�Ƚ��ϴ�. �ٽ� �õ����ּ���.");
			}
		}
	}//end login
		
	//�α��� ������ �޴� �����ֱ�
	public void menuView(Account user) throws AuthenticException {
		
		stuList = new ArrayList<>();
		dataBase.put(ac, stuList);
		String[] menu = {"�Է�","����","���","����","����","�α׾ƿ�"};
		int act = 0;
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name();
		
		while(true){
			act = JOptionPane.showOptionDialog(null, introMsg + "\n������ ����� �������ּ���.", "�л����� ���α׷�",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, menu, null);
			
			if(act==-1) System.exit(0);
			switch(act) {
			case 0://�Է�
				input(user);
				break;
			case 1://����
				delete(user);
				break;
			case 2://���
				show(user);
				break;
			case 3://����
				update(user);
				break;
			case 4:
				System.exit(0);
			case 5:
				startView();
			}
		}
	
	}
	
	//�л������Է�
	public void input(Account user) {
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name();
		boolean input = true, nameCh=true, s1Ch = true, s2Ch = true;
		
		do {
			//�л��̸� �Է�
			do{
				String name;
				try {
					name = JOptionPane.showInputDialog(introMsg + "\n\n�л� �̸��� �Է��ϼ���");
					ae.nameCheck(name);
					st = new Student();
					st.setName(name);
					nameCh = false;
				} catch (Exception e) {;}
			}while(nameCh);
			
			//�߰���� �Է�
			do {
				String s1;
				try {
					s1 = JOptionPane.showInputDialog(introMsg + "\n\n�߰���� ������ �Է��ϼ���.");
					ae.scoreCheck(s1);
					st.setMid(Integer.parseInt(s1));
					s1Ch = false;
				} catch (Exception e) {;}
			}while(s1Ch);
				
			
			//�⸻��� �Է�
			do {
				String s2;
				try {
					s2 = JOptionPane.showInputDialog(introMsg + "\n\n�⸻��� ������ �Է��ϼ���.");
					ae.scoreCheck(s2);
					st.setFin(Integer.parseInt(s2));
					s2Ch = false;
				} catch (Exception e) {;}
			}while(s2Ch);
			
			st.setSum(st.getMid()+st.getFin());
			st.setAvg(st.getSum()/ 2.0);
			
			stuList.add(st);
			show(user);
			dataBase.put(user, stuList);
			input = false;
		}while(input);
	}//end input
	
	//����
	public void delete(Account user) {
		String pCho = null;
		int choice = 0;
		Student delst = null;
		boolean dch = true;
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name()+"\n";
		
		do {
			try {
				pCho = JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n������ �л��� �й��� �Է��ϼ���.");
				ae.numCheck(pCho);
				choice = Integer.parseInt(pCho);
				dch = false;
			} catch (Exception e) {;}
		}while(dch);
		
		Iterator<Student> iter = stuList.iterator();
		while(iter.hasNext()) {
			Student stud = iter.next();
			if(choice == stud.getNum()) {
				delst = stud;
			}
		}
		if(delst == null) {
			JOptionPane.showMessageDialog(null, "ã���ô� �л��� �����ϴ�.");
		}else {
			stuList.remove(delst);
			JOptionPane.showMessageDialog(null, "�ش� �л��� �����Ͱ� �����Ǿ����ϴ�.");
			show(user);
		}
		
		
	}
	
	public void update(Account user) {
		String pCho = null;
		int choice = 0;
		boolean s1Ch = true, s2Ch = true, dch = true;
		Student uplst = null;
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name()+"\n";
		do {
			try {
				pCho = JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n������ �л��� �й��� �Է��ϼ���.");
				ae.numCheck(pCho);
				choice = Integer.parseInt(pCho);
				dch = false;
			} catch (Exception e) {;}
		}while(dch);
		
		Iterator<Student> iter = stuList.iterator();
		
		while(iter.hasNext()) {
			Student stud = iter.next();
			if(choice == stud.getNum()) {
				uplst = stud;
			}
		}
		if(uplst == null) {
			JOptionPane.showMessageDialog(null, "ã���ô� �л��� �����ϴ�.");
		}else {
			//�ش��л� ã������
			
			//�߰���� �Է�
			do {
				String s1;
				try {
					s1 = JOptionPane.showInputDialog(introMsg + "\n\n�߰���� ������ ���Է��ϼ���.");
					ae.scoreCheck(s1);
					uplst.setMid(Integer.parseInt(s1));
					s1Ch = false;
				} catch (Exception e) {;}
			}while(s1Ch);
				
			
			//�⸻��� �Է�
			do {
				String s2;
				try {
					s2 = JOptionPane.showInputDialog(introMsg + "\n\n�⸻��� ������ ���Է��ϼ���.");
					ae.scoreCheck(s2);
					uplst.setFin(Integer.parseInt(s2));
					s2Ch = false;
				} catch (Exception e) {;}
			}while(s2Ch);
			
			uplst.setSum(uplst.getMid()+uplst.getFin());
			uplst.setAvg(uplst.getSum()/2.0);
			show(user);
		}
		
		
		
	}

	//���(��ó�� 
	public void show(Account user) {
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name()+"\n";
		String result = "";

		if(dataBase.get(user).size()==0) {
			JOptionPane.showMessageDialog(null, introMsg+"����� �����Ͱ� �����ϴ�.");
		}else {
			for(int i=0; i < dataBase.get(user).size();i++) {
				int rank=1;
				for(int j=0; j < dataBase.get(user).size();j++) {
					if(dataBase.get(user).get(i).getSum()<dataBase.get(user).get(j).getSum()) {
						rank++;
					} 
				}
				dataBase.get(user).get(i).setRank(rank);
			}
			
			JOptionPane.showMessageDialog(null, introMsg+dataBase.get(user));
		}
		
	}
}
















