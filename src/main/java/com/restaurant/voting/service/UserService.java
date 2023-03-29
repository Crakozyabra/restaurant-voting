package com.restaurant.voting.service;

import com.restaurant.voting.dto.user.UserDto;
import com.restaurant.voting.repository.UserRepository;
import com.restaurant.voting.util.DtoUtil;
import com.restaurant.voting.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserDto create(UserDto userDto) {
        User user = DtoUtil.userDtoToUser(userDto);
        User saved = userRepository.save(user);
        return DtoUtil.userToUserDto(saved);
    }

    public UserDto get(int id) {
        return userRepository.findById(id).map(DtoUtil::userToUserDto).orElse(new UserDto());
    }

    public UserDto getUserByEmail(String email) {
        return DtoUtil.userToUserDto(userRepository.findUserByEmail(email));
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(DtoUtil.userToUserDto(user)));
        return userDtos;
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User user = DtoUtil.userDtoToUser(userDto);
        User saved = userRepository.save(user);
        return DtoUtil.userToUserDto(saved);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
