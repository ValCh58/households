SELECT users.last_name, users.name, user_name, users.active, users.email, roles.role  
FROM users, user_role, roles 
where users.user_id = user_role.user_id and user_role.role_id = roles.role_id; 