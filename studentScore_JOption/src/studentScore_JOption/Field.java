package studentScore_JOption;
import java.awt.Dimension;
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
	
	//���� ùȭ�� (ȸ������, �α���)
	public void startView() {
		
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
	public void setUser() {
		boolean id = true, pw = true, data = true, schoolCh = true, gradeCh = true, classCh = true;
		
		//ȸ������id
		do {
			String input = JOptionPane.showInputDialog("���̵� �Է����ּ���. (�������� ����, 8�� �̳� (���� ����  ����))");
			if(input.length()==0) {
				JOptionPane.showMessageDialog(null, "���̵� �Է����ֽñ� �ٶ��ϴ�.");
				continue;
			}else if(input.length()>=12) {
				JOptionPane.showMessageDialog(null, "12�� �̳��� �Է����ֽñ� �ٶ��ϴ�.");
				continue;
			}
			if(input.matches("^[a-zA-Z]{1}[a-zA-Z0-9]+$")) {
				//ù ȸ�������� ���, �ߺ��˻� �ʿ� ����
				if(acList.size()==0) {
					ac = new Account();
					ac.setId(input);
					id = false;
				}else {
					//id�ߺ��˻�
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
			}else {
				JOptionPane.showMessageDialog(null, "���̵�� ���� (���� ���� ����)���θ� �Է��ؾ� �մϴ�.");
			}
		}while(id);
		
		//ȸ������pw
		do {
			String input = JOptionPane.showInputDialog("��й�ȣ�� �Է����ּ���. (���ڸ� ����)");
			if(input.length()==0) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է����ֽñ� �ٶ��ϴ�.");
				continue;
			}
			Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
			m = p.matcher(input);
			if(m.find()) {
				JOptionPane.showMessageDialog(null, "��й�ȣ�� ���ڷθ� �Է��ؾ� �մϴ�.");
			}else {
				ac.setPw(input);
				pw = false;
			}
		}while(pw);
		
		//ùȭ�� �б�,�г�,�� �Է�
		do {
			do{
				String school = JOptionPane.showInputDialog("�б����� �Է��ϼ���. (�ѱ�, ������ ����)");
				if(school.length()==0) {
					JOptionPane.showMessageDialog(null, "�б����� �Է��ϼ���.");
				}else if(!school.matches("^[0-9]$")) {
					ac.setSchool(school);
					schoolCh = false;
				}else {
					JOptionPane.showMessageDialog(null, "�б����� �ѱ�, �������θ� �Է� �����մϴ�.");
				}
			}while(schoolCh);
			
			do {
				String grade = JOptionPane.showInputDialog("�г��� �Է��ϼ���. (���ڸ� ����)");
				if(grade.length()==0) {
					JOptionPane.showMessageDialog(null, "�г��� �Է��ϼ���.");
				}else if(grade.matches("^[0-9]+$")){
					ac.setGrade(grade);
					gradeCh = false;
				}
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
	public void login() {
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
	public void menuView(Account user) {
		
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
				String name = JOptionPane.showInputDialog(introMsg + "\n\n�л� �̸��� �Է��ϼ���");
				if(name.length()==0) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��� �ֽñ� �ٶ��ϴ�.");
				}else if(name.matches("^[��-�R]{2,5}$")) {
					st = new Student();
					st.setName(name);
					nameCh = false;
				}else {
					JOptionPane.showMessageDialog(null, "�̸��� �ѱ۷θ� �ۼ����ּ���.");
				}
			}while(nameCh);
			
			String pattern = "^[0-9]*$";
			//�߰���� �Է�
			do {
				String s1 = JOptionPane.showInputDialog(introMsg + "\n\n�߰���� ������ �Է��ϼ���.");
				boolean regex = Pattern.matches(pattern, s1);
				if(regex) {
					st.setMid(Integer.parseInt(s1));
					s1Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���.");
				}
			}while(s1Ch);
				
			
			//�⸻��� �Է�
			do {
				String s2 = JOptionPane.showInputDialog(introMsg + "\n\n�⸻��� ������ �Է��ϼ���.");
				boolean regex = Pattern.matches(pattern, s2);
				if(regex) {
					st.setFin(Integer.parseInt(s2));
					s2Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���.");
				}
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
		int choice = 0;
		Student delst = null;
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name()+"\n";
		choice = Integer.parseInt(JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n������ �л��� �й��� �Է��ϼ���."));
		
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
		int choice = 0;
		boolean s1Ch = true, s2Ch = true;
		Student uplst = null;
		String introMsg = "�ĢĢĢĢĢĢ��л� ���� ���� ���α׷��ŢŢŢŢŢŢ�\n"+"�б� : " +user.getSchool()+"     �г� : "+user.getGrade()+"     �� : "+user.getClass_name()+"\n";
		choice = Integer.parseInt(JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n������ �л��� �й��� �Է��ϼ���."));
		
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
			while(s1Ch) {
				String s1 = JOptionPane.showInputDialog(introMsg + "\n\n�߰���� ������ �Է��ϼ���.");
				if(!s1.matches("^[0-9]$")) {
					uplst.setMid(Integer.parseInt(s1));
					s1Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���.");
				}
			}
			
			//�⸻��� �Է�
			while(s2Ch){
				String s2 = JOptionPane.showInputDialog(introMsg + "\n\n�⸻��� ������ �Է��ϼ���.");
				if(!s2.matches("^[0-9]$")) {
					uplst.setFin(Integer.parseInt(s2));
					s2Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "���ڸ� �Է��ϼ���.");
				}
			}
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
			//������ϱ� (�θ������ �Ϻ�)
			for(int i=0; i < dataBase.get(user).size();i++) {
				int rank=1;
				for(int j=0; j < dataBase.get(user).size();j++) {
					if(dataBase.get(user).get(i).getSum()<dataBase.get(user).get(j).getSum()) {
						rank++;
					} 
				}
				dataBase.get(user).get(i).setRank(rank);
			}
			
			
			
			
			
			Set<Map.Entry<Account, ArrayList<Student>>> entryS = dataBase.entrySet();
			Iterator<Map.Entry<Account, ArrayList<Student>>> iter = entryS.iterator();
			while(iter.hasNext()) {
				Map.Entry<Account, ArrayList<Student>> itt = iter.next();
				ArrayList<Student> value = itt.getValue();
				result+=value+"\n";
			}
			
			JOptionPane.showMessageDialog(null, introMsg + result);
//			JOptionPane.showMessageDialog(null, introMsg+dataBase.get(user));
		}
		
	}
}
















