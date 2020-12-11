package studentScore_JOption;

import java.util.regex.Pattern;

public class AccountException {
	public void idCheck(String id) throws AuthenticException{
		
		boolean check = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]+$", id	);
		//정규표현식 (영문으로 시작, 영어+숫자조합 가능)
		if(!check) {
			throw new AuthenticException("영문으로 시작, 영어+숫자(선택) 양식으로 입력하세요.");
		}
	}
	
	public void pwCheck(String pw) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]+$", pw);
		if(!check) {
			throw new AuthenticException("비밀번호는 숫자만 가능합니다.");
		}
	}
}
