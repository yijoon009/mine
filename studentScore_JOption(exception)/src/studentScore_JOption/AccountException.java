package studentScore_JOption;

import java.util.regex.Pattern;

public class AccountException {
	public void idCheck(String id) throws AuthenticException{
		
		boolean check = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]+$", id	);
		//����ǥ���� (�������� ����, ����+�������� ����)
		if(!check) {
			throw new AuthenticException("�������� ����, ����+����(����) ������� �Է��ϼ���.");
		}
	}
	
	public void pwCheck(String pw) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]+$", pw);
		if(!check) {
			throw new AuthenticException("��й�ȣ�� ���ڸ� �����մϴ�.");
		}
	}
}
