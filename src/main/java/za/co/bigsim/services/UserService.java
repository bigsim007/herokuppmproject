package za.co.bigsim.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import za.co.bigsim.doa.UserRepository;
import za.co.bigsim.domain.ERole;
import za.co.bigsim.domain.Role;
import za.co.bigsim.domain.User;
import za.co.bigsim.exceptions.UsernameAlreadyExistsException;
import za.co.bigsim.security.JwtTokenProvider;
import za.co.bigsim.validator.UserValidator;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public User saveUser (User newUser){

        try{
            newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
            //Username has to be unique (exception)
            newUser.setUsername(newUser.getUsername());
            // Make sure that password and confirmPassword match
            // We don't persist or show the confirmPassword
            newUser.setConfirmPassword("");
            
            Set<Role> roles = new HashSet<Role>();
            roles.add(new Role(ERole.ROLE_USER));
            newUser.setRoles(roles);
            return userRepository.save(newUser);

        }catch (Exception e){
            throw new UsernameAlreadyExistsException("Username '"+newUser.getUsername()+"' already exists");
        }

    }
}
