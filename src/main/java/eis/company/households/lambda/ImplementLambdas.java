package eis.company.households.lambda;

import eis.company.households.funcinterface.SaveLinkObject;
import eis.company.households.model.LinkObjectUk;
import eis.company.households.model.TypeObject;

public final class ImplementLambdas<T> {

	/**
	 * Lamda
	 * Вставка записи в link_object_uk/LinkObjectUk
	 * @return LinkObjectUk
	 */
	public SaveLinkObject<LinkObjectUk, Integer, TypeObject, Integer> slo =
	 (linkObjkUk, IdObj, typeObj, IdLinkObject)->{
		   linkObjkUk.setIdParent(IdLinkObject);
		   linkObjkUk.setIdObject(IdObj); 
	       linkObjkUk.setTypeObject(typeObj);
		   return linkObjkUk;
    };
    /**********************************************************/
    
   
}

//LinkObjectUk linkObjectUk = new LinkObjectUk();
		//linkObjectUk.setIdObject(street.getIdStreet());
		//linkObjectUk.setIdParent(street.getIdLinkObject());
		//linkObjectUk.setTypeObject(typeObj);