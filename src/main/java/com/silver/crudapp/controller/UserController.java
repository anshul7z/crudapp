package com.silver.crudapp.controller;

import com.silver.crudapp.model.User;
import com.silver.crudapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService service;

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> userList = service.getAll();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{uid}")
    public ResponseEntity<?> getById(@PathVariable Long uid) {
        //throw new Exception("test exception");
        User userObj = service.getById(uid);
        return ResponseEntity.ok(userObj);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user){
        User userObj = service.save(user);
        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody User user){
        User userObj = service.update(user);
        return ResponseEntity.ok(userObj);
    }

    @DeleteMapping("/{uid}")
    public ResponseEntity<?> deleteById(@PathVariable Long uid){
        boolean response = service.delete(uid);
        if(response){
            return ResponseEntity.ok("User Deleted");
        }
        return new ResponseEntity<>("User Not Found",HttpStatus.NOT_FOUND);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll(){
        boolean response = service.deleteAll();
        if(response){
            return ResponseEntity.ok("All Users Deleted");
        }
        return new ResponseEntity<>("Database is Empty",HttpStatus.OK);
    }

}
