package com.exrate.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.exrate.dto.UserFormDTO;
import com.exrate.entity.User;
import com.exrate.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User registerNewUser(UserFormDTO userFormDTO) 
	{
			User user = new User();
			user.setAddress(userFormDTO.getAddress());
			user.setCity(userFormDTO.getCity());
			user.setCountry(userFormDTO.getCountry());
			user.setName(userFormDTO.getName());
			user.setEmail(userFormDTO.getEmail());
			user.setZip(userFormDTO.getZip());
			user.setEncryptedPassword(passwordEncoder.encode(userFormDTO.getPassword()));
			user.setDateOfBirth(userFormDTO.getDateOfBirth());
			return userRepository.save(user);

	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.findUserByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getEncryptedPassword(), getAuthorities());
	}
	
	private Set<GrantedAuthority> getAuthorities(){
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("USER");
        authorities.add(grantedAuthority);
        return authorities;
    }
	
	public User authenticateUser(String email, String password)
	{
		User user = userRepository.findUserByEmail(email);
		if(null == user) return null;
		if (!passwordEncoder.matches(password, user.getEncryptedPassword())) {
            return null;
        }
		return user;
	}
	
	
	
	

}
