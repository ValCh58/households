package eis.company.households.enums;

public enum ObjectUk {

	UK_OBJ(1), 
	STREET_OBJ(9), 
	HOME_OBJ(8), 
	FLAT_OBJ(10), 
	ACC_OBJ(11), 
	SRV_OBJ(2), 
	USPD_OBJ(3), 
	COUNT_OBJ(4);
	
	@SuppressWarnings("unused")
	private final int obj;
	
	private ObjectUk(int iObj) {
		this.obj = iObj;
		
	}
	
}

	
	

