package eis.company.households.controller;

import eis.company.households.dto.UsersRolesDTO;
import eis.company.households.model.Role;
import eis.company.households.model.User;
import eis.company.households.queres.QueryUsersRepositoryImpl;
import eis.company.households.repository.RoleRepository;
import eis.company.households.repository.UserRepository;
import eis.company.households.service.UserService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
//import org.hibernate.validator;

@Controller
public class LoginController {

    private UserService userService;
    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private QueryUsersRepositoryImpl usersRepositoryImpl;
    
    @Autowired
    public LoginController(UserService userService, RoleRepository roleRepository, UserRepository userRepository,
			QueryUsersRepositoryImpl usersRepositoryImpl) {
		super();
		this.userService = userService;
		this.roleRepository = roleRepository;
		this.userRepository = userRepository;
		this.usersRepositoryImpl = usersRepositoryImpl;
	}

	@GetMapping(value={"/", "/login"})
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }
    
    @GetMapping(value="/admin/edituser")
    public ModelAndView editUser() {
    	ModelAndView modelAndView = new ModelAndView();
       	List<UsersRolesDTO> listUsers = usersRepositoryImpl.queryUsersRoles();
    	modelAndView.addObject("listUsers", listUsers);
    	modelAndView.setViewName("admin/edituser");
    	return modelAndView;
    }
    
    @GetMapping("/admin/formupdateusers/{id}")
    public ModelAndView showFormForUpdate(@PathVariable(value = "id") Long id) {
    	ModelAndView modelAndView = new ModelAndView();
        Optional<User> userOpt =  userRepository.findById(id);
        User user = userOpt.get();
        Object[] nmRole =  user.getRoles().toArray();
        Role rol = (Role) nmRole[0];
        user.setNameRole(rol.getRole());
        List<Role> role = roleRepository.findAll();
    	modelAndView.addObject("role", role);
       	modelAndView.addObject("user", user);
       	modelAndView.setViewName("admin/formupdateusers");
        return modelAndView;
    }
    
    @GetMapping("/admin/deleteUsers/{id}")
    public ModelAndView deleteUser(@PathVariable(value = "id") Long id) {
    	ModelAndView modelAndView = new ModelAndView();
    	userService.deleteUser(id);
    	List<UsersRolesDTO> listUsers = usersRepositoryImpl.queryUsersRoles();
    	modelAndView.addObject("listUsers", listUsers);
    	modelAndView.setViewName("admin/edituser");
    	return modelAndView;
    }
 
    @GetMapping(value="/admin/registration")
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        List<Role> role = roleRepository.findAll();
        modelAndView.addObject("user", user);
        modelAndView.addObject("role", role);
        modelAndView.setViewName("admin/registration");
        return modelAndView;
    }

    @PostMapping(value = "/admin/formupdateusers")
    public ModelAndView saveLoginDataUser(@Valid User user, BindingResult bindingResult) {
    	ModelAndView modelAndView = new ModelAndView();
    	userService.saveUser(user);
    	List<UsersRolesDTO> listUsers = usersRepositoryImpl.queryUsersRoles();
    	modelAndView.addObject("listUsers", listUsers);
    	modelAndView.setViewName("admin/edituser");
    	return modelAndView;
    }
    
    @PostMapping(value = "/admin/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        /*Проверка на "Пользователь уже существует"*/
        User userExists = userService.findUserByUserName(user.getUserName());
        if (userExists != null) {
            bindingResult.rejectValue("userName", "error.user", "Пользователь с таким логином уже существует");
        }
        if (bindingResult.hasErrors()) {
        	List<Role> role = roleRepository.findAll();
        	modelAndView.addObject("role", role);
            modelAndView.setViewName("admin/registration");
        } else {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Пользователь сохранен");
            modelAndView.addObject("user", new User());
            List<Role> role = roleRepository.findAll();
        	modelAndView.addObject("role", role);
            modelAndView.setViewName("admin/registration");
        }
        return modelAndView;
    }

    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Пользователь " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Контент администратора");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    
       
    @GetMapping(value="/user/home_user")
    public ModelAndView home_user(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(auth.getName());
        modelAndView.addObject("userName", "Пользователь " + user.getUserName() + "/" + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("userMessage","Контент пользователя УК");
        modelAndView.setViewName("user/home_user");
        return modelAndView;
    }


}
