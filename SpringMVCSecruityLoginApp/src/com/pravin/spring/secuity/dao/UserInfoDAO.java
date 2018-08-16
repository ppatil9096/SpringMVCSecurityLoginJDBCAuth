package com.pravin.spring.secuity.dao;

import java.util.List;

import com.pravin.spring.secuity.model.UserInfo;

public interface UserInfoDAO {
    public UserInfo findUserInfo(String userName);

    public List<String> getUserRoles(String userName);
}
