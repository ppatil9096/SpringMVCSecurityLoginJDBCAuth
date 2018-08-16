package com.pravin.spring.secuity.auth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pravin.spring.secuity.dao.UserInfoDAO;
import com.pravin.spring.secuity.model.UserInfo;

@Service
public class MyDBAuthenticationService implements UserDetailsService {
    @Autowired
    private UserInfoDAO userInfoDAO;

    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	UserInfo userInfo = userInfoDAO.findUserInfo(userName);
	System.out.println("UserInfo= " + userInfo);
	if (userInfo == null) {
	    throw new UsernameNotFoundException("User " + userName + " was not found in the database");
	}

	List<String> roles = userInfoDAO.getUserRoles(userName);

	List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
	if (roles != null) {
	    for (String role : roles) {
		GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + role);
		grantList.add(authority);
	    }
	}
	UserDetails userDetails = (UserDetails) new User(userInfo.getUserName(), userInfo.getPassword(), grantList);

	return userDetails;
    }

}
