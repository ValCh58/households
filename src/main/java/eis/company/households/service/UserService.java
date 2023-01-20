package eis.company.households.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eis.company.households.Exceptions.ResourceNotFoundException;
import eis.company.households.Exceptions.SaveResourceErrorException;
import eis.company.households.model.Role;
import eis.company.households.model.User;
import eis.company.households.repository.RoleRepository;
import eis.company.households.repository.UserRepository;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Autowired
    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Transactional(transactionManager = "housingTransactionManager", readOnly = true)
    public User findUserByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email))
        		       .orElseThrow(()->new ResourceNotFoundException("User.email not found"));
    }

    @Transactional(transactionManager = "housingTransactionManager", readOnly = true)
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
    
    @Transactional(transactionManager = "housingTransactionManager")
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        Role userRole = Optional.ofNullable(roleRepository.findByRole(user.getNameRole()))
        		                .orElseThrow(()->new ResourceNotFoundException("Role not found"));
        user.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
        return Optional.ofNullable(userRepository.save(user))
        		       .orElseThrow(()->new SaveResourceErrorException("Save resource error User"));
    }
    
    public void deleteUser(Long id) {
       	this.userRepository.deleteById(id);                            
    }

}