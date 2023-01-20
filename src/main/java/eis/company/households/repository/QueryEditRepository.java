package eis.company.households.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;
/**
 * Тип для фабрики запросов при редактировании объектов 
 * @author chVal
 *
 */
@NoRepositoryBean
public interface QueryEditRepository<T, ID> {

	List<T> queryEditModalFormRepository(Integer ID);
}
