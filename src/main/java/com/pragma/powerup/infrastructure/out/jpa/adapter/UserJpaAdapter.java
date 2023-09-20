package com.pragma.powerup.infrastructure.out.jpa.adapter;

import com.pragma.powerup.domain.model.UserModel;
import com.pragma.powerup.domain.spi.IUserPersistencePort;
import com.pragma.powerup.infrastructure.exception.DocumentNumberAlreadyExistException;
import com.pragma.powerup.infrastructure.exception.EmailAlreadyExistException;
import com.pragma.powerup.infrastructure.exception.PhoneAlreadyExistException;
import com.pragma.powerup.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.powerup.infrastructure.out.jpa.repository.IUserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserJpaAdapter implements IUserPersistencePort {
    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;

    @Override
    public void saveOwner(UserModel owner) {
        if(userRepository.findByEmail(owner.getEmail()).isPresent()){
            throw new EmailAlreadyExistException();
        }
        if(userRepository.findByDocumentNumber(owner.getDocumentNumber()).isPresent()){
            throw new DocumentNumberAlreadyExistException();
        }
        if(userRepository.findByPhone(owner.getPhone()).isPresent()){
            throw new PhoneAlreadyExistException();
        }
        userRepository.save(userEntityMapper.toUserEntity(owner));
    }
}
