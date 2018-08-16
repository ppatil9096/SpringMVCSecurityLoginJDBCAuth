package com.pravin.spring.secuity.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pravin.spring.secuity.dao.UserInfoDAO;
import com.pravin.spring.secuity.mapper.UserInfoMapper;
import com.pravin.spring.secuity.model.UserInfo;

@Service
@Transactional
public class UserInfoDAOImpl extends JdbcDaoSupport implements UserInfoDAO {

    @Autowired
    public UserInfoDAOImpl(DataSource dataSource) {
	this.setDataSource(dataSource);
    }

    public UserInfo findUserInfo(String userName) {
	String sql = "Select u.Username,u.Password " + " from User_s u where u.Username = ? ";

	Object[] params = new Object[] { userName };
	UserInfoMapper mapper = new UserInfoMapper();
	try {
	    UserInfo userInfo = this.getJdbcTemplate().queryForObject(sql, params, mapper);
	    return userInfo;
	} catch (EmptyResultDataAccessException e) {
	    return null;
	}
    }

    public List<String> getUserRoles(String userName) {
	String sql = "Select r.User_Role " + " from User_Roles r where r.Username = ? ";

	Object[] params = new Object[] { userName };

	List<String> roles = this.getJdbcTemplate().queryForList(sql, params, String.class);

	return roles;
    }

}
