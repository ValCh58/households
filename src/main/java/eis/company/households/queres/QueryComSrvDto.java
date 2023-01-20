package eis.company.households.queres;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;

import org.springframework.stereotype.Component;

import eis.company.households.dto.ServerObjDTO;
import minaclient.MyPing;


@Component
public class QueryComSrvDto {
	List<ServerObjDTO> retList = new ArrayList<ServerObjDTO>();
	@PersistenceContext(name="housingEntityManager")
	private EntityManager em;
	
	public List<ServerObjDTO> retComSrvDto(){
		retList.clear();
		Query q = em.createNativeQuery("SELECT "
				+ " cm.id_com_server as idComServer, \r\n"
				+ "	cm.name_server as nameServer,\r\n"
				+ " cm.ip_server as ipServer,\r\n"
				+ " cm.port_server as portServer,\r\n"
				+ " mc.name_company as nameCompany\r\n"
				+ " FROM com_server cm, manag_company mc"
				+ " where cm.id_manag_company = mc.id_manag_company", Tuple.class);
		@SuppressWarnings("unchecked")
		List<Tuple> list = q.getResultList();
		if(list.isEmpty()) return null;
		int detected = 0;
		
		for(Tuple t : list) {
			ServerObjDTO sod = new ServerObjDTO((int) t.get("idComServer"),
					                             (String) t.get("nameServer"),
					                             (String) t.get("ipServer"),
					                             (String) t.get("portServer"),
					                             (String) t.get("nameCompany"),
					                             0);
			//Проверка работы сервера связи/////////////////////////////////////////////
			try {
				detected = MyPing.getPing(sod.getIpServer(), Integer.parseInt(sod.getPortServer()))?1:0;
				
			}catch(Exception e) {
				detected = 0;
			}
			
			//int detected = MyPing.getPing(sod.getIpServer(), Integer.parseInt(sod.getPortServer())) ? 1 : 0;//Проверка работы сервера связи
			sod.setStatusSrv(detected);
			detected = 0;
			retList.add(sod);
		}
		
		return retList;
	}
	

}
