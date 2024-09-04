package com.silver.crudapp.service;

import com.silver.crudapp.exception.NoSuchUserExistsException;
import com.silver.crudapp.exception.UserAlreadyExistsException;
import com.silver.crudapp.model.User;
import com.silver.crudapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService implements CrudService<User> {

    private final UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User getById(Long uid) {
        return repository.findById(uid)
                .orElseThrow(
                        ()-> new NoSuchUserExistsException("No Such user Exist with the uid = "+ uid));
    }

    @Override
    public User save(User user) {
        User userObj = repository.findById(user.getUid()).orElse(null);
        if(userObj==null){
            return repository.save(user);
        }
        throw new UserAlreadyExistsException("User Already exist with the uid = " + user.getUid());
    }

    @Override
    public User update(User user) {
        User userObj = repository.findById(user.getUid())
                .orElseThrow(
                        () -> new NoSuchUserExistsException("No Such user Exist with the uid = " + user.getUid()));
        userObj.setUsername(user.getUsername());
        userObj.setPassword(user.getPassword());
        return repository.save(userObj);
    }

    @Override
    public boolean delete(Long uid) {
        Optional<User> userObj = repository.findById(uid);
        if(userObj.isPresent()){
            repository.deleteById(uid);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteAll() {
        List<User> userList = repository.findAll();
        if(!userList.isEmpty()){
            repository.deleteAll();
            return true;
        }
        return false;
    }
}
