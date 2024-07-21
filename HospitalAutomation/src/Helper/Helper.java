package Helper;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Helper {
	
	public static void optionPaneChangeButtonText() {
		
		UIManager.put("OptionPane.cancelButtonText", "İptal");
		UIManager.put("OptionPane.noButtonText", "Hayır");
		UIManager.put("OptionPane.okButtonText", "Tamam");
		UIManager.put("OptionPane.yesButtonText", "Evet");
		

		
	}
	
	public static void showMsg(String str) {
		String msg;
		optionPaneChangeButtonText();
		
		switch(str) {
		
		case "fill":
			msg="Lütfen tüm alanları eksiksiz doldurunuz!";
			break;
		case "success":
		    msg="İşlem Başarılı Gerçekleşti.";
		    break;
		case "error":
			msg="İşlem Başarısız Gerçekleşti.";
			break;
		default:
			msg=str;
		}
		
		JOptionPane.showMessageDialog(null,msg,"Uyarı",JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static boolean confirm(String str) {
		String msg;
		optionPaneChangeButtonText();
		
		switch (str) {
		case "sure":
			msg="Bu işlemi gerçekleştirmek istiyor musunuz?";
			break;
		default:
	     msg=str;
		}
		
		int res=JOptionPane.showConfirmDialog(null, msg,"Dikkat!",JOptionPane.YES_NO_OPTION);
		
		if(res==0)
			return true;
		else
		 return false;	
		
	}

}
