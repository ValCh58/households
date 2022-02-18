package eis.company.households.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.MyConst;
import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.Exceptions.SaveResourceErrorException;
import eis.company.households.funcinterface.SaveLinkObject;
import eis.company.households.model.Counts;
import eis.company.households.model.House;
import eis.company.households.model.LinkObjectUk;
import eis.company.households.model.ManagCompany;
import eis.company.households.model.PersonAcnt;
import eis.company.households.model.Room;
import eis.company.households.model.Street;
import eis.company.households.model.TypeObject;
import eis.company.households.model.UspdDev;
import eis.company.households.repository.CountsRepository;
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

	private StreetRepository streetRepository;
	private ManagCompanyRepository managCompanyRepo;
	private HouseRepository houseRepository;
	private RoomRepository roomRepository;
	private PersonAcntRepository personAcntRepository;
	private TypeObjectRepository typeObjRepo;
	private LinkObjectUkRepository linkObjectUkRepo;
	private UspdDevRepository uspdRepository;
    private CountsRepository countsRepository;
    
    @Autowired	
	public ObjectUserService(StreetRepository streetRepository, ManagCompanyRepository managCompanyRepo,
			HouseRepository houseRepository, RoomRepository roomRepository, PersonAcntRepository personAcntRepository,
			TypeObjectRepository typeObjRepo, LinkObjectUkRepository linkObjectUkRepo, UspdDevRepository uspdRepository,
			CountsRepository countsRepository) {
		super();
		this.streetRepository = streetRepository;
		this.managCompanyRepo = managCompanyRepo;
		this.houseRepository = houseRepository;
		this.roomRepository = roomRepository;
		this.personAcntRepository = personAcntRepository;
		this.typeObjRepo = typeObjRepo;
		this.linkObjectUkRepo = linkObjectUkRepo;
		this.uspdRepository = uspdRepository;
		this.countsRepository = countsRepository;
	}

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
		ManagCompany mc = omc.orElseThrow(()->new ResourceNotFoundException("Object ManagCompany:" + id.toString() + " Not found"));
		return mc;
	}

	/**
	 * Update данных УК, POST
	 * 
	 * @param ManagCompany
	 * @return ManagCompany
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public ManagCompany updateDataManagCompany(ManagCompany mc) {
		Optional<ManagCompany> opt = managCompanyRepo.findById(mc.getIdManagCompany());
		ManagCompany managCompany = opt.orElseThrow(()->new ResourceNotFoundException("Object ManagCompany Not found"));
		managCompany.setNameCompany(mc.getNameCompany());
		managCompany.setPhone(mc.getPhone());
		managCompany.setAddress1(mc.getAddress1());
		managCompany.setAddress2(mc.getAddress2());
		managCompany = Optional.ofNullable(managCompanyRepo.save(managCompany))
				               .orElseThrow(()->new SaveResourceErrorException("Save resource error Alarm"));
		
		return managCompany;
	}
	
	/***********************************************************************************/
	/***********************Street******************************************************/
	/***********************************************************************************/
	/**
	 * Чтение данных улицы, GET
	 * 
	 * @param id
	 * @return Street
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public Street getStreet(Integer id) {
		Optional<Street> opt = streetRepository.findById(id);
		Street street = opt.orElseThrow(()->new ResourceNotFoundException("Object Sttreet:" + id.toString() + " Not found"));
		return street;
	}

	/**
	 * Update данных улицы, POST
	 * 
	 * @param Street
	 * @return Street
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Street updateStreet(Street street) {
		Optional<Street> opt = streetRepository.findById(street.getIdStreet());
		Street str = opt.orElseThrow(()->new ResourceNotFoundException("Object Street Not found"));
		str.setNameStreet(street.getNameStreet());
		str.setDistrict(street.getDistrict());
		str.setIdLinkObject(street.getIdLinkObject());
		str = Optional.ofNullable(streetRepository.save(str))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error Street"));
		return str;
	}

	/**
	 * Insert into Street, Submit Form #modalFormEditStreet
	 * POST
	 * file modal_edit_street.html
	 * 
	 * @return Street
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Street insertStreet(Street st) {
		
		Optional<LinkObjectUk> optLnOb = linkObjectUkRepo.findById(st.getIdLinkObject());
		LinkObjectUk linkObjUk = optLnOb
				.orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk Not found")); 
		Optional<ManagCompany> optmc = managCompanyRepo.findById(linkObjUk.getIdObject());
		ManagCompany mc = optmc
				.orElseThrow(()->new ResourceNotFoundException("Object ManagCompany Not found"));
		Optional<TypeObject> optTobj = typeObjRepo.findById(MyConst.TYPE_OBJECT_STREET);
		TypeObject typeObj = optTobj
				.orElseThrow(()->new ResourceNotFoundException("Object TypeObject Not found"));
		
		Street street = new Street();
		street.setNameStreet(st.getNameStreet());
		street.setDistrict(st.getDistrict());
		street.setIdLinkObject(st.getIdLinkObject());
		street.setManagCompany(mc);
		street.setTypeObject(typeObj);
		street = Optional.ofNullable(streetRepository.save(street))
				         .orElseThrow(()->new SaveResourceErrorException("Save resource error Street"));
				
        mc.getStreet().add(street);
        typeObj.getStreet().add(street);
        
		LinkObjectUk retLinkObj = slo
         .saveLinkUk(new LinkObjectUk(), Integer.valueOf(street.getIdStreet()),typeObj, Integer.valueOf(linkObjUk.getIdLinkObject()));
		
		retLinkObj = Optional.ofNullable(linkObjectUkRepo.save(retLinkObj))
				             .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjectUk"));
		return street;
	}
	
	/**
	 * Delete Street
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delStreet(Integer idLinkObj) {
		//Проверка на подчиненные узлы и корневой узел перед удалением
		if (!(isNotRootObj(idLinkObj) == MyConst.RET_OK && isNotChildObj(idLinkObj) == MyConst.RET_OK)) {return false;}
		LinkObjectUk linkobject = getLinkObjectUK(idLinkObj);
		Optional<Street> opStreet = streetRepository.findById(linkobject.getIdObject());
		Street street = opStreet
				.orElseThrow(()->new ResourceNotFoundException("Object Street Not found"));
		linkObjectUkRepo.deleteById(idLinkObj);
		street.getManagCompany().removeStreet(street);
		street.getTypeObject().removeStreet(street);
		streetRepository.delete(street);
		return true;
	}
	
	/***********************************************************************************/
	private LinkObjectUk getLinkObjectUK(Integer idLinkObj) {
		
		return Optional.ofNullable(isDelLinkObjUk(idLinkObj))
                .orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk Not found"));
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
		return opt.orElseThrow(()->new ResourceNotFoundException("Object House Not found"));
	}

	/**
	 * Update House POST
	 * 
	 * @return House
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public House updateHouse(House house) {
		Optional<House> opt = houseRepository.findById(house.getIdHouse());
		House hs = opt.orElseThrow(()->new ResourceNotFoundException("Object House Not found"));
		hs.setNameHouse(house.getNameHouse());
		hs.setAddress(house.getAddress());
		hs = Optional.ofNullable(houseRepository.save(hs))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error House"));
		return hs;
	}

	/**
	 * Insert into House, Submit Form #modalFormEditHome, file modal_edit_home.html
	 * POST
	 * @return House
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public House insertHouse(House house) {
		Optional<LinkObjectUk> optLnOb = linkObjectUkRepo.findById(house.getIdLinkObject());
		LinkObjectUk linkObjUk = optLnOb
				.orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk Not found"));
		Optional<Street> optStreet = streetRepository.findById(linkObjUk.getIdObject());
		Street street = optStreet
				.orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk::Street Not found"));
		Optional<TypeObject> optTypeobj = typeObjRepo.findById(MyConst.TYPE_OBJECT_HOUSE);
		TypeObject typeObj = optTypeobj
				.orElseThrow(()->new ResourceNotFoundException("Object TypeObject Not found"));
		House hs = new House();
		hs.setNameHouse(house.getNameHouse());
		hs.setAddress(house.getAddress());
		hs.setIdLinkObject(house.getIdLinkObject());
		hs.setStreet(street);
		hs.setTypeObject(typeObj);
		hs = Optional.ofNullable(houseRepository.save(hs))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error House"));
		street.getHouse().add(hs);
		typeObj.getHouse().add(hs);
     	LinkObjectUk retLinkObj = Optional.ofNullable(slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(hs.getIdHouse()), typeObj,
		                                   Integer.valueOf(linkObjUk.getIdLinkObject())))
				                          .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjectUk")); 
		retLinkObj = Optional
		.ofNullable(linkObjectUkRepo.save(retLinkObj)).orElseThrow(()->new SaveResourceErrorException("Save resource error House"));
		
		return hs;
	}
	
	/**
	 * Delete House
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delHouse(Integer idLinkObj) {
		//Проверка на подчиненные узлы и корневой узел перед удалением
		if (!(isNotRootObj(idLinkObj) == MyConst.RET_OK && isNotChildObj(idLinkObj) == MyConst.RET_OK)) {return false;}
		LinkObjectUk linkobject = getLinkObjectUK(idLinkObj);
		Optional<House> opHouse = houseRepository.findById(linkobject.getIdObject());
		House house = opHouse
				.orElseThrow(()->new ResourceNotFoundException("Object House Not found"));
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
		return roomRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Object Room Not found"));
	}

	/**
	 * Update Room POST
	 * 
	 * @return Room
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Room updateRoom(Room room) {
		Room rm = roomRepository.findById(room.getIdRoom())
				.orElseThrow(()->new ResourceNotFoundException("Object Room Not found"));
		rm.setNameRoom(room.getNameRoom());
		rm.setNumberRoom(room.getNumberRoom());
		rm.setNumberUspd(room.getNumberUspd());
		UspdDev uspdDev = uspdRepository.findById(room.getId_uspd())
				.orElseThrow(()->new ResourceNotFoundException("Object UspdDev Not found"));
		rm.setUspdDev(uspdDev);
		rm = Optional.ofNullable(roomRepository.save(rm))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error Room"));
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
		LinkObjectUk linkObjUk = linkObjectUkRepo.findById(rm.getIdLinkObject())
				                  .orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk Not found"));
		House house = houseRepository.findById(linkObjUk.getIdObject())
				                  .orElseThrow(()->new ResourceNotFoundException("Object House Not found"));
		TypeObject typeObj = typeObjRepo.findById(MyConst.TYPE_OBJECT_ROOM)
				                  .orElseThrow(()->new ResourceNotFoundException("Object TypeObject Not found"));
		UspdDev uspdDev = uspdRepository.findById(rm.getId_uspd())
				                  .orElseThrow(()->new ResourceNotFoundException("Object UspdDev Not found"));
		Room room = new Room();
		room.setNameRoom(rm.getNameRoom());
		room.setNumberRoom(rm.getNumberRoom());
		room.setIdLinkObject(rm.getIdLinkObject());
		room.setNumberUspd(rm.getNumberUspd());
		room.setHouse(house);
		room.setTypeObject(typeObj);
		room.setUspdDev(uspdDev);
		room = Optional.ofNullable(roomRepository.save(room))
				                   .orElseThrow(()->new SaveResourceErrorException("Save resource error Room"));
		house.getRoom().add(room);
	    typeObj.getRoom().add(room);
	    uspdDev.getRoom().add(room);
	    LinkObjectUk retLinkObj = Optional.ofNullable(slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(room.getIdRoom()), 
	          	                                 typeObj, Integer.valueOf(linkObjUk.getIdLinkObject())))
	    		                  .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjectUk")); 
	    retLinkObj = Optional.ofNullable(linkObjectUkRepo.save(retLinkObj))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjectUk"));
	    return room;
	}
	
	/**
	 * Delete Room
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delRoom(Integer idLinkObj) {
		//Проверка на подчиненные узлы и корневой узел перед удалением
		if (!(isNotRootObj(idLinkObj) == MyConst.RET_OK && isNotChildObj(idLinkObj) == MyConst.RET_OK)) {return false;}
		LinkObjectUk linkobject = getLinkObjectUK(idLinkObj);
		Room room = roomRepository.findById(linkobject.getIdObject())
				                  .orElseThrow(()->new ResourceNotFoundException("Object Room Not found"));
		
		linkObjectUkRepo.deleteById(idLinkObj);
		room.getHouse().removeRoom(room);
		room.getTypeObject().removeRoom(room);
		room.getUspdDev().removeRoom(room);
		roomRepository.delete(room);
		return true;
	}
	
	/**********************************************************************************/
	/*****************************PersonalAccount**************************************/
	/**********************************************************************************/

	/**
	 * Чтение данных л.сч.
	 * 
	 * @param id
	 * @return PersonAcnt
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public PersonAcnt getPersonAcnt(Integer id) {
		return personAcntRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("Object PersonAcnt Not found"));
	}

	/**
	 * Update Account POST
	 * 
	 * @return PersonAcnt
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public PersonAcnt updateAccount(final PersonAcnt pa){
		PersonAcnt personAcc = personAcntRepository.findById(pa.getIdPersonAcnt())
				                        .orElseThrow(()->new ResourceNotFoundException("Object PersonAcnt Not found"));
		personAcc.setNumAcnt(pa.getNumAcnt());
		personAcc =  Optional.ofNullable(personAcntRepository.save(personAcc))
				                        .orElseThrow(()->new SaveResourceErrorException("Save resource error PersonAcnt"));
		linkedAcntToCounts(personAcc, pa);
		
		return personAcc;
	}
	
	/**
	 * Linking an PersonAcnt with a Counts
	 * @throws Throwable 
	 */
	private void linkedAcntToCounts(final PersonAcnt p, final PersonAcnt pa){
		if(pa != null && p != null) {
			List<String> listCnt = pa.getPersoncounts(); 
			for(String idCnt : listCnt) {
			    int id = Integer.valueOf(idCnt);
			    Counts cnt = countsRepository.findById(Math.abs(id))
			    		    .orElseThrow(()->new SaveResourceErrorException("Counter object does not exist"));
			    //Добавление связи Acnt---<Counts 
			    if(id > 0) {//чек установлен
			       p.addCounts(cnt);
			    }//Удаление связи Acnt---<Counts  
			    if(id < 0) {//чек снят
			       p.removeCounts(cnt); 
			    } 
			}
		}
    }
		
	/**
	 * Insert into PersonAcnt, Submit Form #modalFormAct, file modal_edit_act.html
	 * 
	 * @return PersonAcnt
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public PersonAcnt insertPersonAcnt(final PersonAcnt pacnt){
		LinkObjectUk linkObjUk = linkObjectUkRepo.findById(pacnt.getIdLinkObject())
			                	.orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk Not found"));
		Room room = roomRepository.findById(linkObjUk.getIdObject())
				                .orElseThrow(()->new ResourceNotFoundException("Object Room Not found"));
		TypeObject typeObj = typeObjRepo.findById(MyConst.TYPE_OBJECT_PERSON_ACNT)
				                .orElseThrow(()->new ResourceNotFoundException("Object TypeObject Not found"));
		PersonAcnt personAcnt = new PersonAcnt();
		personAcnt.setNumAcnt(pacnt.getNumAcnt());
		personAcnt.setIdLinkObject(pacnt.getIdLinkObject());
		personAcnt.setRoom(room);
		personAcnt.setTypeObject(typeObj);
		personAcnt = Optional.ofNullable(personAcntRepository.save(personAcnt))
				             .orElseThrow(()->new SaveResourceErrorException("Save resource error PersonAcnt"));
		linkedAcntToCounts(personAcnt, pacnt);
		
		LinkObjectUk retLinkObj = Optional.ofNullable(slo.saveLinkUk(new LinkObjectUk(), Integer.valueOf(personAcnt.getIdPersonAcnt()), 
				                                 typeObj, Integer.valueOf(linkObjUk.getIdLinkObject())))
				                          .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjectUk")); 
		retLinkObj = Optional.ofNullable(linkObjectUkRepo.save(retLinkObj))
				             .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjectUk"));
	    return personAcnt;
	}
	
	/**
	 * Delete PersonAcnt
	 * POST
	 * @param idLinkObj ID LinkObjectUk
	 * @return boolean
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delPersonAcnt(Integer idLinkObj) {
		//Проверка на подчиненные узлы и корневой узел перед удалением
		if (!(isNotRootObj(idLinkObj) == MyConst.RET_OK && isNotChildObj(idLinkObj) == MyConst.RET_OK)) {return false;}
		LinkObjectUk linkobject = getLinkObjectUK(idLinkObj);;
		PersonAcnt personAcnt = personAcntRepository.findById(linkobject.getIdObject())
				                                    .orElseThrow(()->new ResourceNotFoundException("Object PersonAcnt Not found"));
		List<Counts> list = null;
		linkObjectUkRepo.deleteById(idLinkObj);
		personAcnt.getRoom().removePersonAcnt(personAcnt);
		personAcnt.getTypeObject().removePersonAcnt(personAcnt);
		if((list = personAcnt.getCounts()) == null) 
		  {throw new ResourceNotFoundException("Object List<Counts> Not found");}
		List<Integer> listCountId = list.stream().map(count->count.getIdCounts()).collect(Collectors.toList());
				
		for(Integer id : listCountId) {
			Counts c = countsRepository.findById(id)
					  .orElseThrow(()->new ResourceNotFoundException("Object PersonAcnt Not found"));
			personAcnt.removeCounts(c);
		}
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
		if (linkobject == null) 
		   {return MyConst.ERROR_OBJECT_NOT_FOUND;}
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
	 * Получим ID ключ объекта
	 * @param idLinkObj ID ключа из LinkObjectUk
	 * @return LinkObjectUk
	 */
	public LinkObjectUk isDelLinkObjUk(Integer idLinkObj) {
		LinkObjectUk retLink = linkObjectUkRepo.findById(idLinkObj)
				.orElseThrow(()->new ResourceNotFoundException("Object LinkObjectUk Not found"));
		return retLink;
	}
	
	
	
	
}
