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
	
	//제일 첫화면 (회원가입, 로그인)
	public void startView() {
		
		int choice = 0;
		String[] menu = {"회원가입", "로그인"};
		while(true) {
			choice = JOptionPane.showOptionDialog(null, "학생관리 프로그램을 시작합니다.\n 새로운 사용자는 [회원가입]을, 기존 사용자는 [로그인]을 눌러주세요.", "학생관리 프로그램", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, menu, null);
			if(choice== -1) System.exit(0);
			switch(choice) {
			case 0://회원가입
				setUser();
				break;
			case 1://로그인
				login();
				break;
			}//end switch
		}//end while
	}
	
	//회원가입
	public void setUser() {
		boolean id = true, pw = true, data = true, schoolCh = true, gradeCh = true, classCh = true;
		
		//회원가입id
		do {
			String input = JOptionPane.showInputDialog("아이디를 입력해주세요. (영문으로 시작, 12자 이내 (숫자 포함 가능))");
			if(input.length()==0) {
				JOptionPane.showMessageDialog(null, "아이디를 입력해주시길 바랍니다.");
				continue;
			}else if(input.length()>=12) {
				JOptionPane.showMessageDialog(null, "12자 이내로 입력해주시길 바랍니다.");
				continue;
			}
			if(input.matches("^[a-zA-Z]{1}[a-zA-Z0-9]+$")) {
				//첫 회원가입일 경우, 중복검사 필요 없음
				if(acList.size()==0) {
					ac = new Account();
					ac.setId(input);
					id = false;
				}else {
					//id중복검사
					for(Account alid : acList) {
						if(input.equals(alid.getId())){
							JOptionPane.showMessageDialog(null, "이미 존재하는 ID입니다.");
						}else {
							ac = new Account();
							ac.setId(input);
							id = false;
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "아이디는 영문 (숫자 포함 가능)으로만 입력해야 합니다.");
			}
		}while(id);
		
		//회원가입pw
		do {
			String input = JOptionPane.showInputDialog("비밀번호를 입력해주세요. (숫자만 가능)");
			if(input.length()==0) {
				JOptionPane.showMessageDialog(null, "비밀번호를 입력해주시길 바랍니다.");
				continue;
			}
			Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
			m = p.matcher(input);
			if(m.find()) {
				JOptionPane.showMessageDialog(null, "비밀번호는 숫자로만 입력해야 합니다.");
			}else {
				ac.setPw(input);
				pw = false;
			}
		}while(pw);
		
		//첫화면 학교,학년,반 입력
		do {
			do{
				String school = JOptionPane.showInputDialog("학교명을 입력하세요. (한글, 영문만 가능)");
				if(school.length()==0) {
					JOptionPane.showMessageDialog(null, "학교명을 입력하세요.");
				}else if(!school.matches("^[0-9]$")) {
					ac.setSchool(school);
					schoolCh = false;
				}else {
					JOptionPane.showMessageDialog(null, "학교명은 한글, 영문으로만 입력 가능합니다.");
				}
			}while(schoolCh);
			
			do {
				String grade = JOptionPane.showInputDialog("학년을 입력하세요. (숫자만 가능)");
				if(grade.length()==0) {
					JOptionPane.showMessageDialog(null, "학년을 입력하세요.");
				}else if(grade.matches("^[0-9]+$")){
					ac.setGrade(grade);
					gradeCh = false;
				}
			}while(gradeCh);

			do {
				String class_name = JOptionPane.showInputDialog("반을 입력하세요.");
				if(class_name.length() == 0) {
					JOptionPane.showMessageDialog(null, "반을 입력하세요.");
				}else {
					ac.setClass_name(class_name);
					classCh = false;
				}
			}while(classCh);
			data = false;
		}while(data);
		JOptionPane.showMessageDialog(null, "축하합니다!\n회원가입이 완료되었습니다.");
		acList.add(ac);
		

	}//end setUser
	
	//로그인
	public void login() {
		boolean idCh = true, pwCh = false;
		Account user = null;
		//user 전역변수로 넣어봐?
		
		//id check
		while(idCh) {
			String id = JOptionPane.showInputDialog("아이디를 입력하세요.");
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
				JOptionPane.showMessageDialog(null, "존재하지 않거나, 틀린 ID입니다.");
				return;
			}
		}
		while(pwCh) {
			String pw = JOptionPane.showInputDialog("비밀번호를 입력하세요.");
			if(user.getPw().equals(pw)) {
				menuView(user);
				break;
			}else {
				JOptionPane.showMessageDialog(null, "비밀번호가 틀렸습니다. 다시 시도해주세요.");
			}
		}
	}//end login
		
	//로그인 성공시 메뉴 보여주기
	public void menuView(Account user) {
		
		stuList = new ArrayList<>();
		dataBase.put(ac, stuList);
		String[] menu = {"입력","삭제","출력","수정","종료","로그아웃"};
		int act = 0;
		String introMsg = "◐◐◐◐◐◐◐학생 성적 관리 프로그램◑◑◑◑◑◑◑\n"+"학교 : " +user.getSchool()+"     학년 : "+user.getGrade()+"     반 : "+user.getClass_name();
		
		while(true){
			act = JOptionPane.showOptionDialog(null, introMsg + "\n실행할 기능을 선택해주세요.", "학생관리 프로그램",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, menu, null);
			
			if(act==-1) System.exit(0);
			switch(act) {
			case 0://입력
				input(user);
				break;
			case 1://삭제
				delete(user);
				break;
			case 2://출력
				show(user);
				break;
			case 3://수정
				update(user);
				break;
			case 4:
				System.exit(0);
			case 5:
				startView();
			}
		}
	
	}
	
	//학생성적입력
	public void input(Account user) {
		String introMsg = "◐◐◐◐◐◐◐학생 성적 관리 프로그램◑◑◑◑◑◑◑\n"+"학교 : " +user.getSchool()+"     학년 : "+user.getGrade()+"     반 : "+user.getClass_name();
		
		boolean input = true, nameCh=true, s1Ch = true, s2Ch = true;
		do {
			//학생이름 입력
			do{
				String name = JOptionPane.showInputDialog(introMsg + "\n\n학생 이름을 입력하세요");
				if(name.length()==0) {
					JOptionPane.showMessageDialog(null, "이름을 입력해 주시길 바랍니다.");
				}else if(name.matches("^[가-힣]{2,5}$")) {
					st = new Student();
					st.setName(name);
					nameCh = false;
				}else {
					JOptionPane.showMessageDialog(null, "이름은 한글로만 작성해주세요.");
				}
			}while(nameCh);
			
			String pattern = "^[0-9]{1,3}$";
			//중간고사 입력
			do {
				String s1 = JOptionPane.showInputDialog(introMsg + "\n\n중간고사 성적을 입력하세요.");
				boolean regex = Pattern.matches(pattern, s1);
				if(regex) {
					st.setMid(Integer.parseInt(s1));
					s1Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
				}
			}while(s1Ch);
				
			
			//기말고사 입력
			do {
				String s2 = JOptionPane.showInputDialog(introMsg + "\n\n기말고사 성적을 입력하세요.");
				boolean regex = Pattern.matches(pattern, s2);
				if(regex) {
					st.setFin(Integer.parseInt(s2));
					s2Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
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
	
	//삭제
	public void delete(Account user) {
		int choice = 0;
		Student delst = null;
		String introMsg = "◐◐◐◐◐◐◐학생 성적 관리 프로그램◑◑◑◑◑◑◑\n"+"학교 : " +user.getSchool()+"     학년 : "+user.getGrade()+"     반 : "+user.getClass_name()+"\n";
		choice = Integer.parseInt(JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n삭제할 학생의 학번을 입력하세요."));
		
		Iterator<Student> iter = stuList.iterator();
		
		while(iter.hasNext()) {
			Student stud = iter.next();
			if(choice == stud.getNum()) {
				delst = stud;
			}
		}
		if(delst == null) {
			JOptionPane.showMessageDialog(null, "찾으시는 학생이 없습니다.");
		}else {
			stuList.remove(delst);
			JOptionPane.showMessageDialog(null, "해당 학생의 데이터가 삭제되었습니다.");
			show(user);
		}
		
		
	}
	
	public void update(Account user) {
		int choice = 0;
		boolean s1Ch = true, s2Ch = true;
		Student uplst = null;
		String introMsg = "◐◐◐◐◐◐◐학생 성적 관리 프로그램◑◑◑◑◑◑◑\n"+"학교 : " +user.getSchool()+"     학년 : "+user.getGrade()+"     반 : "+user.getClass_name()+"\n";
		choice = Integer.parseInt(JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n수정할 학생의 학번을 입력하세요."));
		
		Iterator<Student> iter = stuList.iterator();
		
		while(iter.hasNext()) {
			Student stud = iter.next();
			if(choice == stud.getNum()) {
				uplst = stud;
			}
		}
		if(uplst == null) {
			JOptionPane.showMessageDialog(null, "찾으시는 학생이 없습니다.");
		}else {
			//해당학생 찾았으면
			while(s1Ch) {
				String s1 = JOptionPane.showInputDialog(introMsg + "\n\n중간고사 성적을 입력하세요.");
				if(!s1.matches("^[0-9]$")) {
					uplst.setMid(Integer.parseInt(s1));
					s1Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
				}
			}
			
			//기말고사 입력
			while(s2Ch){
				String s2 = JOptionPane.showInputDialog(introMsg + "\n\n기말고사 성적을 입력하세요.");
				if(!s2.matches("^[0-9]$")) {
					uplst.setFin(Integer.parseInt(s2));
					s2Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
				}
			}
			uplst.setSum(uplst.getMid()+uplst.getFin());
			uplst.setAvg(uplst.getSum()/2.0);
			show(user);
		}
		
		
		
	}

	//출력(맨처음 
	public void show(Account user) {
		String introMsg = "◐◐◐◐◐◐◐학생 성적 관리 프로그램◑◑◑◑◑◑◑\n"+"학교 : " +user.getSchool()+"     학년 : "+user.getGrade()+"     반 : "+user.getClass_name()+"\n";
		String result = "";

		if(dataBase.get(user).size()==0) {
			JOptionPane.showMessageDialog(null, introMsg+"출력할 데이터가 없습니다.");
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
















