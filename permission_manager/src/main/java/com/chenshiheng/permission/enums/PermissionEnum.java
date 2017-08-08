package com.chenshiheng.permission.enums;

public enum PermissionEnum {
	VISIT(1),ADD(2),DELETE(3),UPDATE(4);
	
	private int type;
	
	private PermissionEnum(int type){
		this.type=type;
	}

	public int getType() {
		return type;
	}
	
}
