package com.restaurant.voting.service;

import com.restaurant.voting.to.user.UserDto;
import com.restaurant.voting.repository.UserRepository;
import com.restaurant.voting.util.ToUtil;
import com.restaurant.voting.model.User;
import com.restaurant.voting.util.ValidationUtil;
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
        User user = ToUtil.userDtoToUser(userDto);
        User saved = userRepository.save(user);
        return ToUtil.userToUserDto(saved);
    }

    public UserDto get(int id) {
        return ValidationUtil.checkNotFoundWithId(
                userRepository.findById(id).map(ToUtil::userToUserDto).orElse(null), id
        );
    }

    public List<UserDto> getAll() {
        List<User> users = userRepository.getAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(user -> userDtos.add(ToUtil.userToUserDto(user)));
        return userDtos;
    }

    @Transactional
    public UserDto update(UserDto userDto) {
        User user = ToUtil.userDtoToUser(userDto);
        User saved = userRepository.save(user);
        return ToUtil.userToUserDto(saved);
    }

    @Transactional
    public void delete(int id) {
        userRepository.deleteById(id);
    }
}
