package studentScore_JOption;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class AccountException {
	
	//정규표현식 (영문으로 시작, 영어+숫자조합 가능)
	public void idCheck(String id) throws AuthenticException{
//		boolean check = Pattern.matches(" ^[a-zA-Z0-9]*$ ", id	);
		boolean check = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]*$", id	);
		if(id.length()==0) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해주시길 바랍니다.");
			throw new AuthenticException("아이디를 입력해주시길 바랍니다.");
		}
		if(id.length()>=12) {
			JOptionPane.showMessageDialog(null, "12자 이내로 입력해주시길 바랍니다.");
			throw new AuthenticException("12자 이내로 입력해주시길 바랍니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "영문으로 시작, 영어+숫자(선택) 양식으로 입력하세요.");
			throw new AuthenticException("영문으로 시작, 영어+숫자(선택) 양식으로 입력하세요.");
		}
	}
	
	//비밀번호 - 숫자만 가능
	public void pwCheck(String pw) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", pw);
		if(pw.length()==0) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해주시길 바랍니다.");
			throw new AuthenticException("비밀번호를 입력해주시길 바랍니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "비밀번호는 숫자만 가능합니다.");
			throw new AuthenticException("비밀번호는 숫자만 가능합니다.");
		}
	}
	
	//한글, 영문만 가능
	public void nonNumCheck(String input) throws AuthenticException{
		boolean check = Pattern.matches("^[가-힣a-zA-Z]+$", input);
		if(input.length()==0) {
			JOptionPane.showMessageDialog(null, "학교를 입력해주시길 바랍니다.");
			throw new AuthenticException("학교명을 입력해주시길 바랍니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "학교는 한글, 영문만 가능합니다.");
			throw new AuthenticException("학교는 한글, 영문만 가능합니다.");
		}
	}
	
	//학년 - 숫자만 가능
	public void gradeCheck(String grade) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", grade);
		if(grade.length()==0) {
			JOptionPane.showMessageDialog(null, "학년을 입력해주시길 바랍니다.");
			throw new AuthenticException("학년을 입력해주시길 바랍니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "학년은 숫자만 가능합니다.");
			throw new AuthenticException("학년은 숫자만 가능합니다.");
		}
	}
	
	//숫자만 가능
	public void numCheck(String input) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", input);
		if(input.length()==0) {
			JOptionPane.showMessageDialog(null, "입력값이 없습니다.");
			throw new AuthenticException("입력값이 없습니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "숫자만 입력하세요.");
			throw new AuthenticException("숫자만 입력하세요.");
		}
	}
	
	//학생이름 - 한글(2~5글자)
	public void nameCheck(String name) throws AuthenticException{
		boolean check = Pattern.matches("^[가-힣]{2,5}$", name);
		if(name.length()==0) {
			JOptionPane.showMessageDialog(null, "이름을 입력해주시길 바랍니다.");
			throw new AuthenticException("이름을 입력해주시길 바랍니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "이름은 한글로만 작성해주세요.");
			throw new AuthenticException("이름은 한글로만 작성해주세요.");
		}
	}
	
	//성적입력
	public void scoreCheck(String s) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]{1,3}$", s);
		if(s.length()==0) {
			JOptionPane.showMessageDialog(null, "성적을 입력해주시길 바랍니다.");
			throw new AuthenticException("성적을 입력해주시길 바랍니다.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "성적은 0~100점 사이의 값을 입력해주세요.");
			throw new AuthenticException("성적은 0~100점 사이의 값을 입력해주세요.");
		}
	}
}








