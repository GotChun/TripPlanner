package com.example.ex01.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dto.UserDto;
import com.example.ex01.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PrincipalDetailsServiceImpl implements UserDetailsService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserDto userDto = userMapper.SelectOne(username);
		if(userDto==null)
			throw new UsernameNotFoundException(username+" 은 존재하지 않은 계정입니다.");
		
		return new PrincipalDetails(userDto);
		
	}

}
