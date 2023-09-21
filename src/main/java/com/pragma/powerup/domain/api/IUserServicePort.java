package com.pragma.powerup.domain.api;

import com.pragma.powerup.domain.model.UserModel;

public interface IUserServicePort {

    void saveOwner(UserModel owner);

    UserModel getUserById(Long id);


}
