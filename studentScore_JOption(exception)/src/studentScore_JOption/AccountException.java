package studentScore_JOption;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class AccountException {
	
	//Á¤±ÔÇ¥Çö½Ä (¿µ¹®À¸·Î ½ÃÀÛ, ¿µ¾î+¼ıÀÚÁ¶ÇÕ °¡´É)
	public void idCheck(String id) throws AuthenticException{
//		boolean check = Pattern.matches(" ^[a-zA-Z0-9]*$ ", id	);
		boolean check = Pattern.matches("^[a-zA-Z]{1}[a-zA-Z0-9]*$", id	);
		if(id.length()==0) {
			JOptionPane.showMessageDialog(null, "¾ÆÀÌµğ¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("¾ÆÀÌµğ¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(id.length()>=12) {
			JOptionPane.showMessageDialog(null, "12ÀÚ ÀÌ³»·Î ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("12ÀÚ ÀÌ³»·Î ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "¿µ¹®À¸·Î ½ÃÀÛ, ¿µ¾î+¼ıÀÚ(¼±ÅÃ) ¾ç½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
			throw new AuthenticException("¿µ¹®À¸·Î ½ÃÀÛ, ¿µ¾î+¼ıÀÚ(¼±ÅÃ) ¾ç½ÄÀ¸·Î ÀÔ·ÂÇÏ¼¼¿ä.");
		}
	}
	
	//ºñ¹Ğ¹øÈ£ - ¼ıÀÚ¸¸ °¡´É
	public void pwCheck(String pw) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", pw);
		if(pw.length()==0) {
			JOptionPane.showMessageDialog(null, "ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("ºñ¹Ğ¹øÈ£¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "ºñ¹Ğ¹øÈ£´Â ¼ıÀÚ¸¸ °¡´ÉÇÕ´Ï´Ù.");
			throw new AuthenticException("ºñ¹Ğ¹øÈ£´Â ¼ıÀÚ¸¸ °¡´ÉÇÕ´Ï´Ù.");
		}
	}
	
	//ÇÑ±Û, ¿µ¹®¸¸ °¡´É
	public void nonNumCheck(String input) throws AuthenticException{
		boolean check = Pattern.matches("^[°¡-ÆRa-zA-Z]+$", input);
		if(input.length()==0) {
			JOptionPane.showMessageDialog(null, "ÇĞ±³¸¦ ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("ÇĞ±³¸íÀ» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "ÇĞ±³´Â ÇÑ±Û, ¿µ¹®¸¸ °¡´ÉÇÕ´Ï´Ù.");
			throw new AuthenticException("ÇĞ±³´Â ÇÑ±Û, ¿µ¹®¸¸ °¡´ÉÇÕ´Ï´Ù.");
		}
	}
	
	//ÇĞ³â - ¼ıÀÚ¸¸ °¡´É
	public void gradeCheck(String grade) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", grade);
		if(grade.length()==0) {
			JOptionPane.showMessageDialog(null, "ÇĞ³âÀ» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("ÇĞ³âÀ» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "ÇĞ³âÀº ¼ıÀÚ¸¸ °¡´ÉÇÕ´Ï´Ù.");
			throw new AuthenticException("ÇĞ³âÀº ¼ıÀÚ¸¸ °¡´ÉÇÕ´Ï´Ù.");
		}
	}
	
	//¼ıÀÚ¸¸ °¡´É
	public void numCheck(String input) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]*$", input);
		if(input.length()==0) {
			JOptionPane.showMessageDialog(null, "ÀÔ·Â°ªÀÌ ¾ø½À´Ï´Ù.");
			throw new AuthenticException("ÀÔ·Â°ªÀÌ ¾ø½À´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "¼ıÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
			throw new AuthenticException("¼ıÀÚ¸¸ ÀÔ·ÂÇÏ¼¼¿ä.");
		}
	}
	
	//ÇĞ»ıÀÌ¸§ - ÇÑ±Û(2~5±ÛÀÚ)
	public void nameCheck(String name) throws AuthenticException{
		boolean check = Pattern.matches("^[°¡-ÆR]{2,5}$", name);
		if(name.length()==0) {
			JOptionPane.showMessageDialog(null, "ÀÌ¸§À» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("ÀÌ¸§À» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "ÀÌ¸§Àº ÇÑ±Û·Î¸¸ ÀÛ¼ºÇØÁÖ¼¼¿ä.");
			throw new AuthenticException("ÀÌ¸§Àº ÇÑ±Û·Î¸¸ ÀÛ¼ºÇØÁÖ¼¼¿ä.");
		}
	}
	
	//¼ºÀûÀÔ·Â
	public void scoreCheck(String s) throws AuthenticException{
		boolean check = Pattern.matches("^[0-9]{1,3}$", s);
		if(s.length()==0) {
			JOptionPane.showMessageDialog(null, "¼ºÀûÀ» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
			throw new AuthenticException("¼ºÀûÀ» ÀÔ·ÂÇØÁÖ½Ã±æ ¹Ù¶ø´Ï´Ù.");
		}
		if(!check) {
			JOptionPane.showMessageDialog(null, "¼ºÀûÀº 0~100Á¡ »çÀÌÀÇ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
			throw new AuthenticException("¼ºÀûÀº 0~100Á¡ »çÀÌÀÇ °ªÀ» ÀÔ·ÂÇØÁÖ¼¼¿ä.");
		}
	}
}








