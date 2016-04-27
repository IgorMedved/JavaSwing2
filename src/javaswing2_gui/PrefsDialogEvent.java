package javaswing2_gui;

import java.util.EventObject;

public class PrefsDialogEvent extends EventObject {
	
	private String mUserName;
	private String mPassword;
	private int mConnectionPort;
	
	
	public PrefsDialogEvent(Object source, String userName, String password, int connectionPort) {
		super(source);
		// TODO Auto-generated constructor stub
		mUserName = userName;
		mPassword = password;
		mConnectionPort = connectionPort;
	}


	public String getUserName() {
		return mUserName;
	}


	public void setUserName(String mUserName) {
		this.mUserName = mUserName;
	}


	public String getPassword() {
		return mPassword;
	}


	public void setPassword(String mPassword) {
		this.mPassword = mPassword;
	}


	public int getConnectionPort() {
		return mConnectionPort;
	}


	public void setConnectionPort(int mConnectionPort) {
		this.mConnectionPort = mConnectionPort;
	}


	
	

}
