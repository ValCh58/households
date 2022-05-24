package eis.company.households.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.MyConst;
import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.Exceptions.SaveResourceErrorException;
import eis.company.households.dto.CountsDTO;
import eis.company.households.dto.EditServerDTO;
import eis.company.households.dto.EditUspdDTO;
import eis.company.households.model.ComServer;
import eis.company.households.model.Counts;
import eis.company.households.model.LinkObject;
import eis.company.households.model.ManagCompany;
import eis.company.households.model.TypeObject;
import eis.company.households.model.TypeUspd;
import eis.company.households.model.UspdDev;
import eis.company.households.repository.ComServerRepository;
import eis.company.households.repository.CountsRepository;
import eis.company.households.repository.LinkObjectRepository;
import eis.company.households.repository.ManagCompanyRepository;
import eis.company.households.repository.TypeObjectRepository;
import eis.company.households.repository.TypeUspdRepository;
import eis.company.households.repository.UspdDevRepository;

@Service
public class UpdateEditSrv {

	private CountsRepository countsRep;
	private ComServerRepository comServerRepository;
	private LinkObjectRepository linkObjRep;
	private ManagCompanyRepository managCompanyRepository;
	private TypeObjectRepository typeObjectRepository;
	private UspdDevRepository uspdDevRep;
	private TypeUspdRepository typeUspdRepo;

	@Autowired
	public UpdateEditSrv(CountsRepository countsRep, ComServerRepository comServerRepository,
			LinkObjectRepository linkObjRep, ManagCompanyRepository managCompanyRepository,
			TypeObjectRepository typeObjectRepository, UspdDevRepository uspdDevRep, TypeUspdRepository typeUspdRepo) {
		super();
		this.countsRep = countsRep;
		this.comServerRepository = comServerRepository;
		this.linkObjRep = linkObjRep;
		this.managCompanyRepository = managCompanyRepository;
		this.typeObjectRepository = typeObjectRepository;
		this.uspdDevRep = uspdDevRep;
		this.typeUspdRepo = typeUspdRepo;
	}
	
	/***********************************************************************************/

	/**
	 * Получение данных для построения дерева
	 * @param id значение поля id_link_object таблицы link_object
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public LinkObject getDataFromTree(Integer id) {
		
		LinkObject linkObject = linkObjRep.findById(id).
				orElseThrow(()->new ResourceNotFoundException("Object LinkObject Not found"));
		return linkObject;
	}

	/**
	 * Сохраним изменения в форме "тест серверов связи"
	 * 
	 * @param esd - объект полей формы
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public ComServer saveComServer(EditServerDTO esd) {
				
		ComServer comserver = comServerRepository.findById(esd.getId_com_server())
				.orElseThrow(()->new ResourceNotFoundException("Object ComServer Not found"));
		comserver.setIpServer(esd.getIp_server());
		comserver.setNameServer(esd.getName_server());
		comserver.setPortServer(esd.getPort_server());
		
		comserver = Optional.ofNullable(comServerRepository.save(comserver))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error ComServer"));
		return comserver;
	}

	/**
	 * Сохранение изменений счетчика
	 * 
	 * @param countsDto
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Counts saveCounts(CountsDTO countsDto) {
				
		Counts counts = countsRep.findById(countsDto.getIdCounts())
				.orElseThrow(()->new ResourceNotFoundException("Object Counts Not found"));
		counts.setAddress(countsDto.getAddress());
		counts.setDateExpire(countsDto.getDateExpire());
		counts.setDatePlug(countsDto.getDatePlug());
		counts.setIdCounts(counts.getIdCounts());
		counts.setNameCount(countsDto.getNameCount());
		counts.setSerialNum(countsDto.getSerialNum());
		counts.setTypeCount(countsDto.getTypeCount());
		counts.setNumCh(countsDto.getNumCh());
		counts.setNumRat(countsDto.getNumRat());
		counts = Optional.ofNullable(countsRep.save(counts))
				.orElseThrow(()->new SaveResourceErrorException("Save resource error ComServer"));
		return counts;
	}
	
	/**
	 * Создание объекта счетчик
	 * 
	 * @param countsDto данные из формы
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Counts newCounts(CountsDTO countsDto) {
		Counts counts = new Counts();
		counts.setAddress(countsDto.getAddress());
		counts.setDateExpire(countsDto.getDateExpire());
		counts.setDatePlug(countsDto.getDatePlug());
		counts.setNameCount(countsDto.getNameCount());
		counts.setSerialNum(countsDto.getSerialNum());
		counts.setTypeCount(countsDto.getTypeCount());
		counts.setNumCh(countsDto.getNumCh());
		counts.setNumRat(countsDto.getNumRat());
		
		LinkObject opLinkObj = linkObjRep.findById(countsDto.getIdLinkObject())
		                     .orElseThrow(()->new ResourceNotFoundException("Object LinkObject Not found"));
		UspdDev opUspdDev = uspdDevRep.findById(opLinkObj.getIdObject())
				          .orElseThrow(()->new ResourceNotFoundException("Object UspdDev Not found"));
		opUspdDev.addCounts(counts);
		TypeObject opTypeObject = typeObjectRepository.findById(MyConst.TYPE_OBJECT_COUNT)
				                 .orElseThrow(()->new ResourceNotFoundException("Object UspdDev Not found"));
		opTypeObject.addCounts(counts);

		counts = Optional.ofNullable(countsRep.save(counts))
			   .orElseThrow(()->new SaveResourceErrorException("Save resource error Counts"));
				
		LinkObject linkObject = new LinkObject();
		linkObject.setIdObject(counts.getIdCounts());
		linkObject.setIdParent(opLinkObj.getIdLinkObject());
		linkObject.setTypeObject(opTypeObject);
		linkObject = Optional.ofNullable(linkObjRep.save(linkObject))
				    .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObject"));
		
		return counts;
	}

	/**
	 * Сохранение изменений в таблице UspdDev или создание нового объекта
	 * 
	 * @param editUspdDto объект полученый из формы "Редактирование УСПД"
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public UspdDev saveUspdDev(EditUspdDTO editUspdDto) {

		if (editUspdDto.getIdTypeUspdDev() > 0) {
			return Optional.ofNullable(saveAfterEditUspd(editUspdDto))
					       .orElseThrow(()->new SaveResourceErrorException("Update resource error UspdDev")); // Update
		} else if (editUspdDto.getIdTypeUspdDev() == 0) {
			return Optional.ofNullable(saveNewUspd(editUspdDto))
					       .orElseThrow(()->new SaveResourceErrorException("Insert resource error UspdDev")); // Insert
		}
		throw new SaveResourceErrorException("Save resource error UspdDev");
	}

	/**
	 * Редактирование и создание нового объекта счетчик
	 * 
	 * @param countsDto
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public Counts updateCounts(CountsDTO countsDto) {
		if (countsDto.getIdCounts() > 0) {
		   return	Optional.ofNullable(saveCounts(countsDto))
				            .orElseThrow(()->new SaveResourceErrorException("Update resource error Counts"));
		} 
	    return Optional.ofNullable(newCounts(countsDto))
	    		       .orElseThrow(()->new SaveResourceErrorException("Insert resource error Counts"));
	}

	/**
	 * Сохранение изменений объекта УСПД
	 * 
	 * @return UspdDev
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	private UspdDev saveAfterEditUspd(EditUspdDTO editUspdDto) {
		UspdDev uspdDev = uspdDevRep.getByIdUspdDev(editUspdDto.getIdUspdDev())
				        .orElseThrow(()->new ResourceNotFoundException("Object UspdDev Not found"));
		
			uspdDev.setIdUspdDev(editUspdDto.getIdUspdDev());
			uspdDev.setNameUspdDev(editUspdDto.getNameUspdDev());
			uspdDev.setNumUspdDev(editUspdDto.getNumUspdDev());
			uspdDev.setAddressLoc(editUspdDto.getAddressLoc());
			uspdDev.setIdCounts(editUspdDto.getIdCounts());
			uspdDev.setIdConfigUspd(editUspdDto.getIdConfigUspd());
			Integer retIdTypeUspd = editUspdDto.getRetIdTypeUspdDev(); // Значение из списка: Тип УСПД//

			// Если есть смена типа перепривяжем UspdDev
			// /////////////////////////////////////////////
			if (retIdTypeUspd != editUspdDto.getIdTypeUspdDev() && editUspdDto.getIdTypeUspdDev() > 0) {
				uspdDev.getTypeUspd().removeUspdDev(uspdDev);
				TypeUspd typeUspd = typeUspdRepo.getOne(retIdTypeUspd);
				typeUspd.addUspdDev(uspdDev);
			}
			return Optional.ofNullable(uspdDevRep.save(uspdDev))
					       .orElseThrow(()->new SaveResourceErrorException("Save resource error UspdDev"));
	}

	/**
	 * Сохранение нового объекта УСПД
	 * 
	 * @param editUspdDto
	 * @return UspdDev
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	private UspdDev saveNewUspd(EditUspdDTO editUspdDto) {

		LinkObject opLinkObject = linkObjRep.findById(editUspdDto.getIdLinkObject())
				                          .orElseThrow(()->new ResourceNotFoundException("Object LinkObject Not found"));
		ComServer opComServer = comServerRepository.findById(opLinkObject.getIdObject())
				                                   .orElseThrow(()->new ResourceNotFoundException("Object ComServer Not found"));
		TypeObject typeObject = Optional.ofNullable(typeObjectRepository.getOne(MyConst.TYPE_OBJECT_USPD))
				              .orElseThrow(()->new ResourceNotFoundException("Object TypeObject Not found"));

		UspdDev uspdDev = new UspdDev();
		uspdDev.setNameUspdDev(editUspdDto.getNameUspdDev());
		uspdDev.setNumUspdDev(editUspdDto.getNumUspdDev());
		uspdDev.setAddressLoc(editUspdDto.getAddressLoc());
		uspdDev.setIdCounts(editUspdDto.getIdCounts());
		uspdDev.setIdConfigUspd(editUspdDto.getIdConfigUspd());
		uspdDev.setComServer(opComServer);
		uspdDev.setTypeObject(typeObject);
		Integer retIdTypeUspd = editUspdDto.getRetIdTypeUspdDev(); // Значение из списка: Тип УСПД//

		if (retIdTypeUspd != 0 && editUspdDto.getIdTypeUspdDev() == 0) {
			TypeUspd typeUspd =  Optional.ofNullable(typeUspdRepo.getOne(retIdTypeUspd))
					                     .orElseThrow(()->new ResourceNotFoundException("Object TypeUspd Not found"));
			typeUspd.addUspdDev(uspdDev);
		}
		uspdDev = Optional.ofNullable(uspdDevRep.save(uspdDev))
				          .orElseThrow(()->new SaveResourceErrorException("Save resource error UspdDev"));

		LinkObject linkObject = new LinkObject();
		linkObject.setIdObject(uspdDev.getIdUspdDev());
		linkObject.setIdParent(opLinkObject.getIdLinkObject());
		linkObject.setTypeObject(typeObject);
		
		linkObject = Optional.ofNullable(linkObjRep.save(linkObject))
				             .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObject"));
		return uspdDev;
	}

	/** 
	 * Создаем новый объект Сервера связi
	 * 
	 * @param esd - объект полей формы
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public ComServer saveNewComServer(EditServerDTO esd) {
		
		// Найдем корневой элемент дерева//
		LinkObject linkobject = (linkObjRep.findByIdParent(MyConst.TREE_ROOT))
				                       .orElseThrow(()->new ResourceNotFoundException("Object LinkObject Not found"));
		ComServer comserver = new ComServer();
		comserver.setIpServer(esd.getIp_server());
		comserver.setNameServer(esd.getName_server());
		comserver.setPortServer(esd.getPort_server());

		ManagCompany manageCompany = (managCompanyRepository.findById(1))// Сделать выбор из списка УК
				                                     .orElseThrow(()->new ResourceNotFoundException("Object ManagCompany Not found"));
		comserver.setManagCompany(manageCompany);
		TypeObject typeObject = (typeObjectRepository.findById(MyConst.TYPE_OBJECT_SERVER))
				                        .orElseThrow(()->new ResourceNotFoundException("Object TypeObject Not found"));
		comserver.setTypeObject(typeObject);
		comserver = Optional.ofNullable(comServerRepository.save(comserver))
				            .orElseThrow(()->new SaveResourceErrorException("Save resource error ComServer"));
		LinkObject linkObject = new LinkObject();
		linkObject.setIdObject(comserver.getIdComServer());
		linkObject.setIdParent(linkobject.getIdLinkObject());
		linkObject.setTypeObject(typeObject);
		linkObject = Optional.ofNullable(linkObjRep.save(linkObject))
		        .orElseThrow(()->new SaveResourceErrorException("Save resource error LinkObjec"));
		return comserver;
	}

	/**
	 * Проверка на корневой узел. Корневой узел не удаляется.
	 * 
	 * @param idLink ID дерева объектов
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager", readOnly = true)
	public Integer isNotRootObj(Integer idLink) {

		Optional<LinkObject> oplink = linkObjRep.findById(idLink);
		LinkObject linkobject = oplink.isPresent() ? oplink.get() : null;
		if (linkobject == null) {
			return MyConst.ERROR_OBJECT_NOT_FOUND;
		}
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
		Optional<LinkObject> oplinkparent = linkObjRep.findByIdParent(idLink);
		LinkObject linkobject = oplinkparent.isPresent() ? oplinkparent.get() : null;
		return (linkobject == null) ? MyConst.RET_OK : MyConst.ERROR_PARENT_IS;
	}

	/**
	 * Удаление Comm Server
	 * 
	 * @param idLinkObj ID дерева объектов
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delServerObj(Integer idLinkObj) {
		if (isNotRootObj(idLinkObj) == MyConst.RET_OK && isNotChildObj(idLinkObj) == MyConst.RET_OK) {
			Optional<LinkObject> oplink = linkObjRep.findById(idLinkObj);
			LinkObject linkobject = oplink.isPresent() ? oplink.get() : null;
			if (linkobject == null) {
				return false;
			} else {
				Optional<ComServer> opcomserver = comServerRepository.findById(linkobject.getIdObject());
				ComServer comserver = opcomserver.isPresent() ? opcomserver.get() : null;
				if (comserver == null) {
					return false;
				} else {
					linkObjRep.deleteById(idLinkObj);
					comServerRepository.delete(comserver);
				}
			}
		} else {
			return false;
		}

		return true;
	}

	/**
	 * Удаление УСПД
	 * 
	 * @param idLinkObj
	 * @return
	 */
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delUspdObj(Integer idLinkObj) {
		/* Проверим -есть ли подчиненные объекты счетчики */
		if (isNotChildObj(idLinkObj) == MyConst.RET_OK) {
			Optional<LinkObject> oplink = linkObjRep.findById(idLinkObj);
			LinkObject linkobject = oplink.isPresent() ? oplink.get() : null;
			if (linkobject == null) {return false;}
			
			Optional<UspdDev> opUspdDev = uspdDevRep.findById(linkobject.getIdObject());
			UspdDev uspdDev = opUspdDev.isPresent() ? opUspdDev.get() : null;
			if (uspdDev == null) {
				return false;
			} 
			else {
				linkObjRep.deleteById(idLinkObj);
				uspdDev.getTypeUspd().removeUspdDev(uspdDev);
				uspdDevRep.delete(uspdDev);
			}
		} else {
			return false;
		}

		return true;
	}
	
	/**
	 * Удаление счетчика
	 * @param idLinkObj
	 * @return
	 */
	
	@Transactional(transactionManager = "housingTransactionManager")
	public boolean delCounts(Integer idLinkObj) {
		/* Проверим -есть ли подчиненные объекты счетчики */
		if (isNotChildObj(idLinkObj) == MyConst.RET_OK) {
			Optional<LinkObject> oplink = linkObjRep.findById(idLinkObj);
			LinkObject linkobject = oplink.isPresent() ? oplink.get() : null;
			if (linkobject == null) {return false;}
			
			Optional<Counts> opCounts = countsRep.findById(linkobject.getIdObject());
			Counts count = opCounts.isPresent() ? opCounts.get() : null;
			if (count == null) {
				return false;
			} 
			else {
				linkObjRep.deleteById(idLinkObj);
				count.getUspdDev().removeCounts(count);
				countsRep.delete(count);
			}
		} else {
			return false;
		}	
		return true;
	}
}
