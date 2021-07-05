package eis.company.households.repository;

import java.util.List;

import org.springframework.data.repository.NoRepositoryBean;

import eis.company.households.dto.UsersRolesDTO;

@NoRepositoryBean
public interface QueryUsersRepository{

	List<UsersRolesDTO> queryUsersRoles();
}

