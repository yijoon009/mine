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
	
	//Á¦ÀÏ Ã¹È­¸é (È¸¿ø°¡ÀÔ, ·Î±×ÀÎ)
	public void startView() {
		
		int choice = 0;
		String[] menu = {"È¸¿ø°¡ÀÔ", "·Î±×ÀÎ"};
		while(true) {
			choice = JOptionPane.showOptionDialog(null, "ÇÐ»ý°ü¸® ÇÁ·Î±×·¥À» ½ÃÀÛÇÕ´Ï´Ù.\n »õ·Î¿î »ç¿ëÀÚ´Â [È¸¿ø°¡ÀÔ]À», ±âÁ¸ »ç¿ëÀÚ´Â [·Î±×ÀÎ]À» ´­·¯ÁÖ¼¼¿ä.", "ÇÐ»ý°ü¸® ÇÁ·Î±×·¥", JOptionPane.DEFAULT_OPTION, 
					JOptionPane.PLAIN_MESSAGE, null, menu, null);
			if(choice== -1) System.exit(0);
			switch(choice) {
			case 0://È¸¿ø°¡ÀÔ
				setUser();
				break;
			case 1://·Î±×ÀÎ
				login();
				break;
			}//end switch
		}//end while
	}
	
	//È¸¿ø°¡ÀÔ
	public void setUser() {
		boolean id = true, pw = true, data = true, schoolCh = true, gradeCh = true, classCh = true;
		
		//È¸¿ø°¡ÀÔid
		do {
			String input = JOptionPane.showInputDialog("¾ÆÀÌµð¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä. (¿µ¹®À¸·Î ½ÃÀÛ, 8ÀÚ ÀÌ³» (¼ýÀÚ Æ÷ÇÔ  °¡´É))");
			if(input.length()==0) {
				JOptionPane.showMessageDialog(null, "¾ÆÀÌµð¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
				continue;
			}else if(input.length()>=12) {
				JOptionPane.showMessageDialog(null, "12ÀÚ ÀÌ³»·Î ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
				continue;
			}
			if(input.matches("^[a-zA-Z]{1}[a-zA-Z0-9]+$")) {
				//Ã¹ È¸¿ø°¡ÀÔÀÏ °æ¿ì, Áßº¹°Ë»ç ÇÊ¿ä ¾øÀ½
				if(acList.size()==0) {
					ac = new Account();
					ac.setId(input);
					id = false;
				}else {
					//idÁßº¹°Ë»ç
					for(Account alid : acList) {
						if(input.equals(alid.getId())){
							JOptionPane.showMessageDialog(null, "ÀÌ¹Ì Á¸ÀçÇÏ´Â IDÀÔ´Ï´Ù.");
						}else {
							ac = new Account();
							ac.setId(input);
							id = false;
						}
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "¾ÆÀÌµð´Â ¿µ¹® (¼ýÀÚ Æ÷ÇÔ °¡´É)À¸·Î¸¸ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
			}
		}while(id);
		
		//È¸¿ø°¡ÀÔpw
		do {
			String input = JOptionPane.showInputDialog("ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ¼¼¿ä. (¼ýÀÚ¸¸ °¡´É)");
			if(input.length()==0) {
				JOptionPane.showMessageDialog(null, "ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
				continue;
			}
			Pattern p = Pattern.compile("[A-Z,a-z,&%$#@!()*^]");
			m = p.matcher(input);
			if(m.find()) {
				JOptionPane.showMessageDialog(null, "ºñ¹Ð¹øÈ£´Â ¼ýÀÚ·Î¸¸ ÀÔ·ÂÇØ¾ß ÇÕ´Ï´Ù.");
			}else {
				ac.setPw(input);
				pw = false;
			}
		}while(pw);
		
		//Ã¹È­¸é ÇÐ±³,ÇÐ³â,¹Ý ÀÔ·Â
		do {
			do{
				String school = JOptionPane.showInputDialog("ÇÐ±³¸íÀ» ÀÔ·ÂÇÏ¼¼¿ä. (ÇÑ±Û, ¿µ¹®¸¸ °¡´É)");
				if(school.length()==0) {
					JOptionPane.showMessageDialog(null, "ÇÐ±³¸íÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				}else if(!school.matches("^[0-9]$")) {
					ac.setSchool(school);
					schoolCh = false;
				}else {
					JOptionPane.showMessageDialog(null, "ÇÐ±³¸íÀº ÇÑ±Û, ¿µ¹®À¸·Î¸¸ ÀÔ·Â °¡´ÉÇÕ´Ï´Ù.");
				}
			}while(schoolCh);
			
			do {
				String grade = JOptionPane.showInputDialog("ÇÐ³âÀ» ÀÔ·ÂÇÏ¼¼¿ä. (¼ýÀÚ¸¸ °¡´É)");
				if(grade.length()==0) {
					JOptionPane.showMessageDialog(null, "ÇÐ³âÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				}else if(grade.matches("^[0-9]+$")){
					ac.setGrade(grade);
					gradeCh = false;
				}
			}while(gradeCh);

			do {
				String class_name = JOptionPane.showInputDialog("¹ÝÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				if(class_name.length() == 0) {
					JOptionPane.showMessageDialog(null, "¹ÝÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				}else {
					ac.setClass_name(class_name);
					classCh = false;
				}
			}while(classCh);
			data = false;
		}while(data);
		JOptionPane.showMessageDialog(null, "ÃàÇÏÇÕ´Ï´Ù!\nÈ¸¿ø°¡ÀÔÀÌ ¿Ï·áµÇ¾ú½À´Ï´Ù.");
		acList.add(ac);
		

	}//end setUser
	
	//·Î±×ÀÎ
	public void login() {
		boolean idCh = true, pwCh = false;
		Account user = null;
		//user Àü¿ªº¯¼ö·Î ³Ö¾îºÁ?
		
		//id check
		while(idCh) {
			String id = JOptionPane.showInputDialog("¾ÆÀÌµð¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
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
				JOptionPane.showMessageDialog(null, "Á¸ÀçÇÏÁö ¾Ê°Å³ª, Æ²¸° IDÀÔ´Ï´Ù.");
			}
		}
		while(pwCh) {
			String pw = JOptionPane.showInputDialog("ºñ¹Ð¹øÈ£¸¦ ÀÔ·ÂÇÏ¼¼¿ä.");
			if(user.getPw().equals(pw)) {
				menuView(user);
				break;
			}else {
				JOptionPane.showMessageDialog(null, "ºñ¹Ð¹øÈ£°¡ Æ²·È½À´Ï´Ù. ´Ù½Ã ½ÃµµÇØÁÖ¼¼¿ä.");
			}
		}
	}//end login
		
	//·Î±×ÀÎ ¼º°ø½Ã ¸Þ´º º¸¿©ÁÖ±â
	public void menuView(Account user) {
		
		stuList = new ArrayList<>();
		dataBase.put(ac, stuList);
		String[] menu = {"ÀÔ·Â","»èÁ¦","Ãâ·Â","¼öÁ¤","Á¾·á","·Î±×¾Æ¿ô"};
		int act = 0;
		String introMsg = "¢Ä¢Ä¢Ä¢Ä¢Ä¢Ä¢ÄÇÐ»ý ¼ºÀû °ü¸® ÇÁ·Î±×·¥¢Å¢Å¢Å¢Å¢Å¢Å¢Å\n"+"ÇÐ±³ : " +user.getSchool()+"     ÇÐ³â : "+user.getGrade()+"     ¹Ý : "+user.getClass_name();
		
		while(true){
			act = JOptionPane.showOptionDialog(null, introMsg + "\n½ÇÇàÇÒ ±â´ÉÀ» ¼±ÅÃÇØÁÖ¼¼¿ä.", "ÇÐ»ý°ü¸® ÇÁ·Î±×·¥",
					JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, menu, null);
			
			if(act==-1) System.exit(0);
			switch(act) {
			case 0://ÀÔ·Â
				input(user);
				break;
			case 1://»èÁ¦
				delete(user);
				break;
			case 2://Ãâ·Â
				show(user);
				break;
			case 3://¼öÁ¤
				update(user);
				break;
			case 4:
				System.exit(0);
			case 5:
				startView();
			}
		}
	
	}
	
	//ÇÐ»ý¼ºÀûÀÔ·Â
	public void input(Account user) {
		String introMsg = "¢Ä¢Ä¢Ä¢Ä¢Ä¢Ä¢ÄÇÐ»ý ¼ºÀû °ü¸® ÇÁ·Î±×·¥¢Å¢Å¢Å¢Å¢Å¢Å¢Å\n"+"ÇÐ±³ : " +user.getSchool()+"     ÇÐ³â : "+user.getGrade()+"     ¹Ý : "+user.getClass_name();
		
		boolean input = true, nameCh=true, s1Ch = true, s2Ch = true;
		do {
			//ÇÐ»ýÀÌ¸§ ÀÔ·Â
			do{
				String name = JOptionPane.showInputDialog(introMsg + "\n\nÇÐ»ý ÀÌ¸§À» ÀÔ·ÂÇÏ¼¼¿ä");
				if(name.length()==0) {
					JOptionPane.showMessageDialog(null, "ÀÌ¸§À» ÀÔ·ÂÇØ ÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
				}else if(name.matches("^[°¡-ÆR]{2,5}$")) {
					st = new Student();
					st.setName(name);
					nameCh = false;
				}else {
					JOptionPane.showMessageDialog(null, "ÀÌ¸§Àº ÇÑ±Û·Î¸¸ ÀÛ¼ºÇØÁÖ¼¼¿ä.");
				}
			}while(nameCh);
			
			String pattern = "^[0-9]*$";
			//Áß°£°í»ç ÀÔ·Â
			do {
				String s1 = JOptionPane.showInputDialog(introMsg + "\n\nÁß°£°í»ç ¼ºÀûÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				boolean regex = Pattern.matches(pattern, s1);
				if(regex) {
					st.setMid(Integer.parseInt(s1));
					s1Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "¼ýÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}while(s1Ch);
				
			
			//±â¸»°í»ç ÀÔ·Â
			do {
				String s2 = JOptionPane.showInputDialog(introMsg + "\n\n±â¸»°í»ç ¼ºÀûÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				boolean regex = Pattern.matches(pattern, s2);
				if(regex) {
					st.setFin(Integer.parseInt(s2));
					s2Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "¼ýÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
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
	
	//»èÁ¦
	public void delete(Account user) {
		int choice = 0;
		Student delst = null;
		String introMsg = "¢Ä¢Ä¢Ä¢Ä¢Ä¢Ä¢ÄÇÐ»ý ¼ºÀû °ü¸® ÇÁ·Î±×·¥¢Å¢Å¢Å¢Å¢Å¢Å¢Å\n"+"ÇÐ±³ : " +user.getSchool()+"     ÇÐ³â : "+user.getGrade()+"     ¹Ý : "+user.getClass_name()+"\n";
		choice = Integer.parseInt(JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n»èÁ¦ÇÒ ÇÐ»ýÀÇ ÇÐ¹øÀ» ÀÔ·ÂÇÏ¼¼¿ä."));
		
		Iterator<Student> iter = stuList.iterator();
		
		while(iter.hasNext()) {
			Student stud = iter.next();
			if(choice == stud.getNum()) {
				delst = stud;
			}
		}
		if(delst == null) {
			JOptionPane.showMessageDialog(null, "Ã£À¸½Ã´Â ÇÐ»ýÀÌ ¾ø½À´Ï´Ù.");
		}else {
			stuList.remove(delst);
			JOptionPane.showMessageDialog(null, "ÇØ´ç ÇÐ»ýÀÇ µ¥ÀÌÅÍ°¡ »èÁ¦µÇ¾ú½À´Ï´Ù.");
			show(user);
		}
		
		
	}
	
	public void update(Account user) {
		int choice = 0;
		boolean s1Ch = true, s2Ch = true;
		Student uplst = null;
		String introMsg = "¢Ä¢Ä¢Ä¢Ä¢Ä¢Ä¢ÄÇÐ»ý ¼ºÀû °ü¸® ÇÁ·Î±×·¥¢Å¢Å¢Å¢Å¢Å¢Å¢Å\n"+"ÇÐ±³ : " +user.getSchool()+"     ÇÐ³â : "+user.getGrade()+"     ¹Ý : "+user.getClass_name()+"\n";
		choice = Integer.parseInt(JOptionPane.showInputDialog(introMsg+"\n"+stuList.toString()+"\n¼öÁ¤ÇÒ ÇÐ»ýÀÇ ÇÐ¹øÀ» ÀÔ·ÂÇÏ¼¼¿ä."));
		
		Iterator<Student> iter = stuList.iterator();
		
		while(iter.hasNext()) {
			Student stud = iter.next();
			if(choice == stud.getNum()) {
				uplst = stud;
			}
		}
		if(uplst == null) {
			JOptionPane.showMessageDialog(null, "Ã£À¸½Ã´Â ÇÐ»ýÀÌ ¾ø½À´Ï´Ù.");
		}else {
			//ÇØ´çÇÐ»ý Ã£¾ÒÀ¸¸é
			while(s1Ch) {
				String s1 = JOptionPane.showInputDialog(introMsg + "\n\nÁß°£°í»ç ¼ºÀûÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				if(!s1.matches("^[0-9]$")) {
					uplst.setMid(Integer.parseInt(s1));
					s1Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "¼ýÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
			
			//±â¸»°í»ç ÀÔ·Â
			while(s2Ch){
				String s2 = JOptionPane.showInputDialog(introMsg + "\n\n±â¸»°í»ç ¼ºÀûÀ» ÀÔ·ÂÇÏ¼¼¿ä.");
				if(!s2.matches("^[0-9]$")) {
					uplst.setFin(Integer.parseInt(s2));
					s2Ch = false;
				}else {
					JOptionPane.showMessageDialog(null, "¼ýÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
				}
			}
			uplst.setSum(uplst.getMid()+uplst.getFin());
			uplst.setAvg(uplst.getSum()/2.0);
			show(user);
		}
		
		
		
	}

	//Ãâ·Â(¸ÇÃ³À½ 
	public void show(Account user) {
		String introMsg = "¢Ä¢Ä¢Ä¢Ä¢Ä¢Ä¢ÄÇÐ»ý ¼ºÀû °ü¸® ÇÁ·Î±×·¥¢Å¢Å¢Å¢Å¢Å¢Å¢Å\n"+"ÇÐ±³ : " +user.getSchool()+"     ÇÐ³â : "+user.getGrade()+"     ¹Ý : "+user.getClass_name()+"\n";
		String result = "";

		if(dataBase.get(user).size()==0) {
			JOptionPane.showMessageDialog(null, introMsg+"Ãâ·ÂÇÒ µ¥ÀÌÅÍ°¡ ¾ø½À´Ï´Ù.");
		}else {
			//µî¼ö±¸ÇÏ±â (µÎ¸í±îÁö¸¸ ¿Ïº®)
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
















