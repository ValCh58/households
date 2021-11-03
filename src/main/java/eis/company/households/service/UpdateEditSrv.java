package eis.company.households.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.MyConst;
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

	@Autowired private CountsRepository countsRep;
	@Autowired private ComServerRepository comServerRepository;
	@Autowired private LinkObjectRepository linkObjRep;
	@Autowired private ManagCompanyRepository managCompanyRepository;
	@Autowired private TypeObjectRepository typeObjectRepository;
	@Autowired private UspdDevRepository uspdDevRep;
	@Autowired private TypeUspdRepository typeUspdRepo;

	public UpdateEditSrv() {
	}

	/**
	 * Получение данных для построения дерева
	 * @param id значение поля id_link_object таблицы link_object
	 * @return
	 */
	@Transactional(readOnly = true)
	public LinkObject getDataFromTree(Integer id) {
		Optional<LinkObject> optLinkRep = linkObjRep.findById(id);
		LinkObject linkObject = optLinkRep.isPresent() ? optLinkRep.get() : null;

		return linkObject;
	}

	/**
	 * Сохраним изменения в форме "тест серверов связи"
	 * 
	 * @param esd - объект полей формы
	 * @return
	 */
	@Transactional
	public ComServer saveComServer(EditServerDTO esd) {
		Optional<ComServer> opcomserver = comServerRepository.findById(esd.getId_com_server());
		ComServer comserver = opcomserver.isPresent() ? opcomserver.get() : null;
		if (comserver == null) {
			return null;
		}

		comserver.setIpServer(esd.getIp_server());
		comserver.setNameServer(esd.getName_server());
		comserver.setPortServer(esd.getPort_server());
		comserver = comServerRepository.save(comserver);
		return comserver;
	}

	/**
	 * Сохранение изменений счетчика
	 * 
	 * @param countsDto
	 * @return
	 */
	@Transactional
	public Counts saveCounts(CountsDTO countsDto) {
		Optional<Counts> opCounts = countsRep.findById(countsDto.getIdCounts());
		Counts counts = opCounts.isPresent() ? opCounts.get() : null;
		if (counts == null) {
			return null;
		}
		counts.setAddress(countsDto.getAddress());
		counts.setDateExpire(countsDto.getDateExpire());
		counts.setDatePlug(countsDto.getDatePlug());
		counts.setIdCounts(counts.getIdCounts());
		counts.setNameCount(countsDto.getNameCount());
		counts.setSerialNum(countsDto.getSerialNum());
		counts.setTypeCount(countsDto.getTypeCount());
		counts.setNumCh(countsDto.getNumCh());
		countsRep.save(counts);

		return counts;
	}
	
	/**
	 * Создание объекта счетчик
	 * 
	 * @param countsDto данные из формы
	 * @return
	 */
	@Transactional
	public Counts newCounts(CountsDTO countsDto) {
		Counts counts = new Counts();
		counts.setAddress(countsDto.getAddress());
		counts.setDateExpire(countsDto.getDateExpire());
		counts.setDatePlug(countsDto.getDatePlug());
		counts.setNameCount(countsDto.getNameCount());
		counts.setSerialNum(countsDto.getSerialNum());
		counts.setTypeCount(countsDto.getTypeCount());
		counts.setNumCh(countsDto.getNumCh());

		Optional<LinkObject> opLinkObj = linkObjRep.findById(countsDto.getIdLinkObject());
		if (!opLinkObj.isPresent()) {return null;}

		Optional<UspdDev> opUspdDev = uspdDevRep.findById(opLinkObj.get().getIdObject());
		if (!opUspdDev.isPresent()) {return null;}
		opUspdDev.get().addCounts(counts);

		Optional<TypeObject> opTypeObject = typeObjectRepository.findById(MyConst.TYPE_OBJECT_COUNT);
		if (!opTypeObject.isPresent()) {return null;}
		opTypeObject.get().addCounts(counts);

		countsRep.save(counts);
				
		LinkObject linkObject = new LinkObject();
		linkObject.setIdObject(counts.getIdCounts());
		linkObject.setIdParent(opLinkObj.get().getIdLinkObject());
		linkObject.setTypeObject(opTypeObject.get());
		linkObjRep.save(linkObject);
		
		return counts;
	}

	/**
	 * Сохранение изменений в таблице UspdDev или создание нового объекта
	 * 
	 * @param editUspdDto объект полученый из формы "Редактирование УСПД"
	 * @return
	 */
	public UspdDev saveUspdDev(EditUspdDTO editUspdDto) {

		if (editUspdDto.getIdTypeUspdDev() > 0) {
			return saveAfterEditUspd(editUspdDto); // Update
		} else if (editUspdDto.getIdTypeUspdDev() == 0) {
			return saveNewUspd(editUspdDto); // Insert
		}

		return null;
	}

	/**
	 * Редактирование и создание нового объекта счетчик
	 * 
	 * @param countsDto
	 */
	public void updateCounts(CountsDTO countsDto) {
		if (countsDto.getIdCounts() > 0) {
			saveCounts(countsDto);
		} else {
			newCounts(countsDto);
		}
	}

	/**
	 * Сохранение изменений объекта УСПД
	 * 
	 * @return UspdDev
	 */
	@Transactional
	private UspdDev saveAfterEditUspd(EditUspdDTO editUspdDto) {
		UspdDev uspdDev;
		Optional<UspdDev> opUspdDev = uspdDevRep.getByIdUspdDev(editUspdDto.getIdUspdDev());
		if (!opUspdDev.isPresent()) {
			return null;
		} else {
			uspdDev = opUspdDev.get();
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
			return uspdDevRep.save(uspdDev);
		}
	}

	/**
	 * Сохранение нового объекта УСПД
	 * 
	 * @param editUspdDto
	 * @return UspdDev
	 */
	@Transactional
	private UspdDev saveNewUspd(EditUspdDTO editUspdDto) {

		Optional<LinkObject> opLinkObject = linkObjRep.findById(editUspdDto.getIdLinkObject());
		if (!opLinkObject.isPresent()) {return null;}

		Optional<ComServer> opComServer = comServerRepository.findById(opLinkObject.get().getIdObject());
		TypeObject typeObject = typeObjectRepository.getOne(MyConst.TYPE_OBJECT_USPD);

		UspdDev uspdDev = new UspdDev();
		uspdDev.setNameUspdDev(editUspdDto.getNameUspdDev());
		uspdDev.setNumUspdDev(editUspdDto.getNumUspdDev());
		uspdDev.setAddressLoc(editUspdDto.getAddressLoc());
		uspdDev.setIdCounts(editUspdDto.getIdCounts());
		uspdDev.setIdConfigUspd(editUspdDto.getIdConfigUspd());
		uspdDev.setComServer(opComServer.get());
		uspdDev.setTypeObject(typeObject);
		Integer retIdTypeUspd = editUspdDto.getRetIdTypeUspdDev(); // Значение из списка: Тип УСПД//

		if (retIdTypeUspd != 0 && editUspdDto.getIdTypeUspdDev() == 0) {
			TypeUspd typeUspd = typeUspdRepo.getOne(retIdTypeUspd);
			typeUspd.addUspdDev(uspdDev);
		}
		uspdDevRep.save(uspdDev);

		LinkObject linkObject = new LinkObject();
		linkObject.setIdObject(uspdDev.getIdUspdDev());
		linkObject.setIdParent(opLinkObject.get().getIdLinkObject());
		linkObject.setTypeObject(typeObject);
		linkObjRep.save(linkObject);

		return uspdDev;
	}

	/**
	 * Создаем новый объект Сервера связi
	 * 
	 * @param esd - объект полей формы
	 * @return
	 */
	@Transactional
	public ComServer saveNewComServer(EditServerDTO esd) {
		
		// Найдем корневой элемент дерева//
		Optional<LinkObject> oplink = linkObjRep.findByIdParent(MyConst.TREE_ROOT);
		LinkObject linkobject = oplink.isPresent() ? oplink.get() : null;
		if (linkobject == null) {return null;}

		ComServer comserver = new ComServer();
		comserver.setIpServer(esd.getIp_server());
		comserver.setNameServer(esd.getName_server());
		comserver.setPortServer(esd.getPort_server());

		Optional<ManagCompany> opmcomp = managCompanyRepository.findById(1);// Сделать выбор из списка УК
		ManagCompany manageCompany = opmcomp.isPresent() ? opmcomp.get() : null;
		if (manageCompany == null) {return null;}
		comserver.setManagCompany(manageCompany);

		Optional<TypeObject> optobject = typeObjectRepository.findById(MyConst.TYPE_OBJECT_SERVER);
		TypeObject typeObject = optobject.isPresent() ? optobject.get() : null;
		if (typeObject == null) {return null;}
		comserver.setTypeObject(typeObject);
		comserver = comServerRepository.save(comserver);

		LinkObject linkObject = new LinkObject();
		linkObject.setIdObject(comserver.getIdComServer());
		linkObject.setIdParent(linkobject.getIdLinkObject());
		linkObject.setTypeObject(typeObject);
		linkObjRep.save(linkObject);
		
		return comserver;
	}

	/**
	 * Проверка на корневой узел. Корневой узел не удаляется.
	 * 
	 * @param idLink ID дерева объектов
	 * @return
	 */
	@Transactional(readOnly = true)
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
	@Transactional(readOnly = true)
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
	@Transactional
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
	@Transactional
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
	
	@Transactional
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
