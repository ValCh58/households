package eis.company.households.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import eis.company.households.MyConst;
import eis.company.households.funcinterface.SaveLinkObject;
import eis.company.households.model.House;
import eis.company.households.model.LinkObjectUk;
import eis.company.households.model.ManagCompany;
import eis.company.households.model.PersonAcnt;
import eis.company.households.model.Room;
import eis.company.households.model.Street;
import eis.company.households.model.TypeObject;
import eis.company.households.model.UspdDev;
import eis.company.households.repository.HouseRepository;
import eis.company.households.repository.LinkObjectUkRepository;
import eis.company.households.repository.ManagCompanyRepository;
import eis.company.households.repository.PersonAcntRepository;
import eis.company.households.repository.RoomRepository;
import eis.company.households.repository.StreetRepository;
import eis.company.households.repository.TypeObjectRepository;
import eis.company.households.repository.UspdDevRepository;

@Service
public class ObjectUserService {

	@Autowired private StreetRepository streetRepository;
	@Autowired private ManagCompanyRepository managCompanyRepo;
	@Autowired private HouseRepository houseRepository;
	@Autowired private RoomRepository roomRepository;
	@Autowired private PersonAcntRepository personAcntRepository;
	@Autowired private TypeObjectRepository typeObjRepo;
	@Autowired private LinkObjectUkRepository linkObjectUkRepo;
	@Autowired private UspdDevRepository uspdRepository;

		
	/**
	 * Lambda
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
    /***********************************************************************************/
	
    /******************Manage Company***************************************************/
	/**
	 * Чтение данных УК GET
	 * 
	 * @param id
	 * @return ManagCompany
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public ManagCompany getDataManagCompany(Integer id) {
		Optional<ManagCompany> omc = managCompanyRepo.findById(id);
		return omc.isPresent() ? omc.get() : new ManagCompany();
	}

	/**
	 * Update данных УК POST
	 * 
	 * @param ManagCompany
	 * @return ManagCompany
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public ManagCompany updateDataManagCompany(ManagCompany mc) {
		Optional<ManagCompany> opt = managCompanyRepo.findById(mc.getIdManagCompany());
		ManagCompany managCompany = opt.isPresent() ? opt.get() : null;
		if (managCompany == null) {return null;}
		
		managCompany.setNameCompany(mc.getNameCompany());
		managCompany.setPhone(mc.getPhone());
		managCompany.setAddress1(mc.getAddress1());
		managCompany.setAddress2(mc.getAddress2());
		managCompanyRepo.save(managCompany);
		
		return managCompany;
	}
	
	/***********************************************************************************/
	/******************Street***********************************************************/
    /***********************************************************************************/

	/**
	 * Чтение данных улицы GET
	 * 
	 * @param id
	 * @return Street
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public Street getStreet(Integer id) {
		Optional<Street> opt = streetRepository.findById(id);
		return opt == null ? new Street() : opt.get();
	}

	/**
	 * Update данных улицы POST
	 * 
	 * @param Street
	 * @return Street
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Street updateStreet(Street street) {
		Optional<Street> opt = streetRepository.findById(street.getIdStreet());
		Street str = opt.isPresent() ? opt.get() : null;
		if (str == null) {return null;}
		
		str.setNameStreet(street.getNameStreet());
		str.setDistrict(street.getDistrict());
		str.setIdLinkObject(street.getIdLinkObject());
		
		return streetRepository.save(str);
	}

	/**
	 * Insert into Street, Submit Form #modalFormEditStreet, file
	 * POST
	 * modal_edit_street.html
	 * 
	 * @return Street
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Street insertStreet(Street st) {
		LinkObjectUk linkObjUk = null;
		ManagCompany mc = null;
		TypeObject typeObj = null;

		Optional<LinkObjectUk> optLnOb = linkObjectUkRepo.findById(st.getIdLinkObject());
		if ((linkObjUk = optLnOb.isPresent() ? optLnOb.get() : null) == null) {return null;}
		Optional<ManagCompany> optmc = managCompanyRepo.findById(linkObjUk.getIdObject());
		if ((mc = optmc.isPresent() ? optmc.get() : null) == null) {return null;}
		Optional<TypeObject> optTobj = typeObjRepo.findById(MyConst.TYPE_OBJECT_STREET);
		if ((typeObj = optTobj.isPresent() ? optTobj.get() : null) == null) {return null;}

		Street street = new Street();
		street.setNameStreet(st.getNameStreet());
		street.setDistrict(st.getDistrict());
		street.setIdLinkObject(st.getIdLinkObject());
		street.setManagCompany(mc);
		street.setTypeObject(typeObj);
		street = streetRepository.save(street);
		if (street == null) {return null;}
		
        mc.getStreet().add(street);
        typeObj.getStreet().add(street);
		
		LinkObjectUk retLinkObj = slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(street.getIdStreet()), 
				                                 typeObj, Integer.valueOf(linkObjUk.getIdLinkObject()));
		
		return(linkObjectUkRepo.save(retLinkObj)) != null ? street : null;
	}
	
	/**
	 * Delete Street
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delStreet(Integer idLinkObj) {
		LinkObjectUk linkobject = null;
		Street street = null;
		
		if((linkobject = isDelLinkObjUk(idLinkObj)) == null) {return false;}
		Optional<Street> opStreet = streetRepository.findById(linkobject.getIdObject());
		if((street = opStreet.isPresent() ? opStreet.get() : null) == null) {return false;}
		linkObjectUkRepo.deleteById(idLinkObj);
		street.getManagCompany().removeStreet(street);
		street.getTypeObject().removeStreet(street);
		streetRepository.delete(street);
		
		return true;
	}
	
	/***********************************************************************************/
	/**********************House********************************************************/
	/***********************************************************************************/

	/**
	 * Чтение данных House GET
	 * 
	 * @param id
	 * @return House
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public House getHouse(Integer id) {
		Optional<House> opt = houseRepository.findById(id);
		return opt == null ? new House() : opt.get();
	}

	/**
	 * Update House POST
	 * 
	 * @return House
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public House updateHouse(House house) {
		Optional<House> opt = houseRepository.findById(house.getIdHouse());
		House hs = opt.isPresent() ? opt.get() : null;
		if (hs == null) {
			return null;
		}
		hs.setNameHouse(house.getNameHouse());
		hs.setAddress(house.getAddress());
		houseRepository.save(hs);
		return hs;
	}

	/**
	 * Insert into House, Submit Form #modalFormEditHome, file modal_edit_home.html
	 * POST
	 * @return House
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public House insertHouse(House house) {
		LinkObjectUk linkObjUk = null;
		Street street = null;
		TypeObject typeObj = null;

		Optional<LinkObjectUk> optLnOb = linkObjectUkRepo.findById(house.getIdLinkObject());
		if ((linkObjUk = optLnOb.isPresent() ? optLnOb.get() : null) == null) {return null;}
		Optional<Street> optStreet = streetRepository.findById(linkObjUk.getIdObject());
		if ((street = optStreet.isPresent() ? optStreet.get() : null) == null) {return null;}
		Optional<TypeObject> optTypeobj = typeObjRepo.findById(MyConst.TYPE_OBJECT_HOUSE);
		if ((typeObj = optTypeobj.isPresent() ? optTypeobj.get() : null) == null) {return null;}

		House hs = new House();
		hs.setNameHouse(house.getNameHouse());
		hs.setAddress(house.getAddress());
		hs.setIdLinkObject(house.getIdLinkObject());
		hs.setStreet(street);
		hs.setTypeObject(typeObj);
		hs = houseRepository.save(hs);
		if (hs == null) {return null;}
		
        street.getHouse().add(hs);
		typeObj.getHouse().add(hs);
        
		LinkObjectUk retLinkObj = slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(hs.getIdHouse()), typeObj,
		                                         Integer.valueOf(linkObjUk.getIdLinkObject())); 
	    
		return  linkObjectUkRepo.save(retLinkObj) != null ? hs : null;
	}
	
	/**
	 * Delete House
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delHouse(Integer idLinkObj) {
		LinkObjectUk linkobject = null;
		House house = null;
		
		if((linkobject = isDelLinkObjUk(idLinkObj)) == null) {return false;}
		Optional<House> opHouse = houseRepository.findById(linkobject.getIdObject());
		if((house = opHouse.isPresent() ? opHouse.get() : null) == null) {return false;}
		linkObjectUkRepo.deleteById(idLinkObj);
		house.getStreet().removeHouse(house);
		house.getTypeObject().removeHouse(house);
		houseRepository.delete(house);
		
		return true;
	}
	
	/**********************************************************************************/
	/**************************Room****************************************************/
	/**********************************************************************************/

	/**
	 * Чтение данных помещения GET
	 * 
	 * @param id
	 * @return Room
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public Room getRoom(Integer id) {
		Optional<Room> opt = roomRepository.findById(id);
		return opt == null ? new Room() : opt.get();
	}

	/**
	 * Update Room POST
	 * 
	 * @return Room
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Room updateRoom(Room room) {
		Optional<Room> opt = roomRepository.findById(room.getIdRoom());
		Room rm = opt.isPresent() ? opt.get() : null;
		if (rm == null) {return null;}
		rm.setNameRoom(room.getNameRoom());
		rm.setNumberRoom(room.getNumberRoom());
		rm.setNumberUspd(room.getNumberUspd());
		UspdDev uspdDev = null;
		Optional<UspdDev> opUspdDev = uspdRepository.findById(room.getId_uspd());
		if((uspdDev = opUspdDev.isPresent() ? opUspdDev.get() : null) == null) {return null;}
		rm.setUspdDev(uspdDev);
		roomRepository.save(rm);
		rm.getUspdDev().getRoom().add(rm);
		
		return rm;
	}
	
	/**
	 * Insert into Room, Submit Form #modalFormEditRomm, file modal_edit_room.html
	 * 
	 * @return Room
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Room insertRoom(Room rm) {
		LinkObjectUk linkObjUk = null;
		House house = null;
		TypeObject typeObj = null;
		UspdDev uspdDev = null;

		Optional<LinkObjectUk> optLnOb = linkObjectUkRepo.findById(rm.getIdLinkObject());
		if ((linkObjUk = optLnOb.isPresent() ? optLnOb.get() : null) == null) {return null;}
		Optional<House> optHouse = houseRepository.findById(linkObjUk.getIdObject());//Parent object
		if ((house = optHouse.isPresent() ? optHouse.get() : null) == null) {return null;}
		Optional<TypeObject> optTypeobj = typeObjRepo.findById(MyConst.TYPE_OBJECT_ROOM);
		if ((typeObj = optTypeobj.isPresent() ? optTypeobj.get() : null) == null) {return null;}
		Optional<UspdDev> opUspdDev = uspdRepository.findById(rm.getId_uspd());
		if((uspdDev = opUspdDev.isPresent() ? opUspdDev.get() : null) == null) {return null;}
				
		Room room = new Room();
		room.setNameRoom(rm.getNameRoom());
		room.setNumberRoom(rm.getNumberRoom());
		room.setIdLinkObject(rm.getIdLinkObject());
		room.setNumberUspd(rm.getNumberUspd());
		room.setHouse(house);
		room.setTypeObject(typeObj);
		room.setUspdDev(uspdDev);
		room = roomRepository.save(room);
		house.getRoom().add(room);
	    typeObj.getRoom().add(room);
	    uspdDev.getRoom().add(room);
		
	    LinkObjectUk retLinkObj = slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(room.getIdRoom()), 
	          	                                 typeObj, Integer.valueOf(linkObjUk.getIdLinkObject())); 
	    
		return  linkObjectUkRepo.save(retLinkObj) != null ? room : null;
	}
	
	/**
	 * Delete Room
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delRoom(Integer idLinkObj) {
		LinkObjectUk linkobject = null;
		Room room = null;
		
		if((linkobject = isDelLinkObjUk(idLinkObj)) == null) {return false;}
		Optional<Room> opRoom = roomRepository.findById(linkobject.getIdObject());
		if((room = opRoom.isPresent() ? opRoom.get() : null) == null) {return false;}
		linkObjectUkRepo.deleteById(idLinkObj);
		room.getHouse().removeRoom(room);
		room.getTypeObject().removeRoom(room);
		room.getUspdDev().removeRoom(room);
		roomRepository.delete(room);
		
		return true;
	}
	
	/**********************************************************************************/
	/*****************************PersonalAccaunt**************************************/
	/**********************************************************************************/

	/**
	 * Чтение данных л.сч.
	 * 
	 * @param id
	 * @return PersonAcnt
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public PersonAcnt getPersonAcnt(Integer id) {
		Optional<PersonAcnt> opt = personAcntRepository.findById(id);
		return opt == null ? new PersonAcnt() : opt.get();
	}

	/**
	 * Update Account POST
	 * 
	 * @return PersonAcnt
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public PersonAcnt updateAccount(PersonAcnt pa) {
		Optional<PersonAcnt> opt = personAcntRepository.findById(pa.getIdPersonAcnt());
		PersonAcnt personAcc = opt.isPresent() ? opt.get() : null;
		if (personAcc == null) {
			return null;
		}
		personAcc.setNumAcnt(pa.getNumAcnt());
		personAcntRepository.save(personAcc);
		return personAcc;
	}
		
	/**
	 * Insert into PersonAcnt, Submit Form #modalFormAct, file modal_edit_act.html
	 * 
	 * @return PersonAcnt
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public PersonAcnt insertPersonAcnt(PersonAcnt pacnt) {
		LinkObjectUk linkObjUk = null;
		Room room = null;
		TypeObject typeObj = null;
		
		Optional<LinkObjectUk> optLnOb = linkObjectUkRepo.findById(pacnt.getIdLinkObject());
		if ((linkObjUk = optLnOb.isPresent() ? optLnOb.get() : null) == null) {return null;}
		Optional<Room> optRoom = roomRepository.findById(linkObjUk.getIdObject());
		if ((room = optRoom.isPresent() ? optRoom.get() : null) == null) {return null;}
		Optional<TypeObject> optTypeobj = typeObjRepo.findById(MyConst.TYPE_OBJECT_PERSON_ACNT);
		if ((typeObj = optTypeobj.isPresent() ? optTypeobj.get() : null) == null) {return null;}
		
		PersonAcnt personAcnt = new PersonAcnt();
		personAcnt.setNumAcnt(pacnt.getNumAcnt());
		personAcnt.setIdLinkObject(pacnt.getIdLinkObject());
		personAcnt.setRoom(room);
		personAcnt.setTypeObject(typeObj);
		personAcntRepository.save(personAcnt);
		
		LinkObjectUk retLinkObj = slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(personAcnt.getIdPersonAcnt()), 
				                                 typeObj, Integer.valueOf(linkObjUk.getIdLinkObject())); 
	    
		return  linkObjectUkRepo.save(retLinkObj) != null ? personAcnt : null;
	}
	
	/**
	 * Delete PersonAcnt
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delPersonAcnt(Integer idLinkObj) {
		LinkObjectUk linkobject = null;
		PersonAcnt personAcnt = null;
		
		if((linkobject = isDelLinkObjUk(idLinkObj)) == null) {return false;}
		Optional<PersonAcnt> opPersonAcnt = personAcntRepository.findById(linkobject.getIdObject());
		if((personAcnt = opPersonAcnt.isPresent() ? opPersonAcnt.get() : null) == null) {return false;}
		linkObjectUkRepo.deleteById(idLinkObj);
		personAcnt.getRoom().removePersonAcnt(personAcnt);
		personAcnt.getTypeObject().removePersonAcnt(personAcnt);
		personAcntRepository.delete(personAcnt);
		
		return true;
	}
	
	/**************************************************************************************/
	/*****************Helper functions for delete******************************************/
	/**************************************************************************************/
	
	/**
	 * Проверка на корневой узел. Корневой узел не удаляется.
	 * 
	 * @param idLink ID дерева объектов
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public Integer isNotRootObj(Integer idLink) {

		Optional<LinkObjectUk> oplink = linkObjectUkRepo.findById(idLink);
		LinkObjectUk linkobject = oplink.isPresent() ? oplink.get() : null;
		if (linkobject == null) {return MyConst.ERROR_OBJECT_NOT_FOUND;}

		return (linkobject.getIdParent() > 0) ? MyConst.RET_OK : MyConst.ERROR_TREE_ROOT;
	}
	
	/**
	 * Проверка на дочерние узлы, если есть - узел не удаляется.
	 * 
	 * @param idLink ID дерева объектов
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public Integer isNotChildObj(Integer idLink) {
		Optional<List<LinkObjectUk>> oplinkparent = linkObjectUkRepo.findByIdParent(idLink);
		List<LinkObjectUk> linkobject = oplinkparent.isPresent() ? oplinkparent.get() : null;
		return (linkobject == null) ? MyConst.RET_OK : MyConst.ERROR_PARENT_IS;
	}
	
	/**
	 * Проверка на подчиненные узлы и корневой узел перед удалением
	 * @param idLinkObj ID ключа из LinkObjectUk
	 * @return LinkObjectUk
	 */
	public LinkObjectUk isDelLinkObjUk(Integer idLinkObj) {
		LinkObjectUk retLink = null;
		
		if (isNotRootObj(idLinkObj) == MyConst.RET_OK && isNotChildObj(idLinkObj) == MyConst.RET_OK) {
			Optional<LinkObjectUk> oplink = linkObjectUkRepo.findById(idLinkObj);
			retLink = oplink.isPresent() ? oplink.get() : null;
		}
		return retLink;
	}
	
	
	
	
}
