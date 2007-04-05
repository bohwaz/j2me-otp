/*
 * MIDlet interface to Harry Mantakos <harry@meretrix.com> OTP (s/key)
 * calculator. 
 *
 * Written by Jan-Frode Myklebust <janfrode@tanso.net>.
 *
 *
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */

import javax.microedition.lcdui.*;
import javax.microedition.midlet.*;

public class jotpMIDlet
    extends MIDlet 
    implements CommandListener {
        otp otpwd;
	TextField PassField, SeqField, ChallengeField;
        int hashalg;
        int seq;
  private Form mMainForm;
  
  public jotpMIDlet() {

	mMainForm = new Form("OneTimePass");
	PassField = new TextField("Password", null, 20, 0x10000);
	SeqField = new TextField("Sequence", null, 6, 2);
	ChallengeField = new TextField("Challenge", null, 10, 0);
	mMainForm.append(PassField);
	mMainForm.append(SeqField);
	mMainForm.append(ChallengeField);
	mMainForm.addCommand(new Command("OK", Command.OK, 0));
	mMainForm.addCommand(new Command("Exit", Command.EXIT, 0));
	mMainForm.setCommandListener(this);
  }
  
  public void startApp() {
    Display.getDisplay(this).setCurrent(mMainForm);
  }
  
  public void pauseApp() {}
  
  public void destroyApp(boolean unconditional) {}
  
  public void commandAction(Command c, Displayable s) {
    if ( c.getCommandType() == Command.EXIT ) {
    	notifyDestroyed();
    } 
    if ( c.getCommandType() == Command.OK) {
	seq = new Integer(1).valueOf(SeqField.getString()).intValue();
	otpwd = new otp(seq, ChallengeField.getString(), PassField.getString(), otp.MD5);
	otpwd.calc();
	mMainForm.append(new StringItem(null, otpwd.toString()));
    }
  }


}
