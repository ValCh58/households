package eis.company.households.repository;

import org.springframework.data.repository.NoRepositoryBean;

import eis.company.households.dto.EditServerDTO;

/**
 * Тип для запроса данных сервера связи
 * @author chVal
 *
 */

@NoRepositoryBean
public interface QueryEditSrvRepository extends QueryEditRepository<EditServerDTO, Integer>{
	
}
