package com.xiaogoubaoxigua.service;

import com.xiaogoubaoxigua.po.Users;

public interface UsersService {

    public Users getUsersByUserIdByPass(Users users);
    public Users getUsersById(String userId);
    public int saveUsers(Users users);
}

