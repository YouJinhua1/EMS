package com.yjh.exception;

public class ParamException extends RuntimeException{

	public ParamException() {
		super();
	}

	public ParamException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

	public ParamException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ParamException(String arg0) {
		super(arg0);
	}

	public ParamException(Throwable arg0) {
		super(arg0);
	}

}
