package vn.teamcode.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.teamcode.models.ResponseObject;
import vn.teamcode.models.UserModel;
import vn.teamcode.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/v1/user")
public class UserController {
    // Khai bao repository de controller user nay su dung trong viec xu ly database
    @Autowired
    private UserRepository repository;

    @GetMapping("/getAllUser") // Khai bao url "/getAllUser" cho nguoi dung su dung API Get ALL User
    List<UserModel> getAllUser() {
        return repository.findAll();
    }

    @GetMapping("/getDetailUser/{userID}") // Comment vao day
    ResponseEntity<ResponseObject> getDetailUser(@PathVariable Long userID) {
        Optional<UserModel> foundUser = repository.findById(userID);
        if(foundUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "User detail", foundUser)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Not found user with id = " + userID, foundUser)
            );
        }
    }

    @PostMapping("/insertUser")
    ResponseEntity<ResponseObject> insertUser(@RequestBody UserModel newUser) {
        List<UserModel> foundUser = repository.findByUserName(newUser.getUserName().trim());
        if(foundUser.size() > 0) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("Failed", "Username already taken!", "")
            );
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Insert user successfully!", repository.save(newUser))
            );
        }
    }

    @PutMapping("/updateUser/{userID}")
    ResponseEntity<ResponseObject> updateUser(@PathVariable Long userID, @RequestBody UserModel newEditUser) {
        UserModel updateUser = repository.findById(userID)
                .map(
                        user -> {
                            user.setUserName(newEditUser.getUserName());
                            user.setFullName(newEditUser.getFullName());
                            user.setAddress(newEditUser.getAddress());
                            user.setYear(newEditUser.getYear());
                            return repository.save(user);
                        }
                ).orElseGet(() -> {
                    newEditUser.setId(userID);
                    return repository.save(newEditUser);
                });
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Success", "Update user successfully!", updateUser)
        );
    }

    @DeleteMapping("/deleteUser/{userID}")
    ResponseEntity<ResponseObject> deleteUser(@PathVariable Long userID) {
        boolean exits = repository.existsById(userID);
        if(exits) {
            repository.deleteById(userID);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Delete User successfully!", "")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("Success", "Cannot find User to delete", "")
        );
    }

    @PutMapping("/deleteLocalUser/{userID}")
    ResponseEntity<ResponseObject> deleteLocalUser(@PathVariable Long userID) {
        Optional<UserModel> updateUser = repository.findById(userID)
                .map(
                        user -> {
                            user.setShow(true);
                            return repository.save(user);
                        }
                );
        if(updateUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("Success", "Delete local user successfully!", updateUser)
            );
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ResponseObject("Failed", "Not found user with id = " + userID, null)
            );
        }
    }

    @GetMapping("/getAllNotDeleteUser") // Khai bao url "/getAllUser" cho nguoi dung su dung API Get ALL User
    List<UserModel> getAllNotDeleteUser() {
        return repository.findByIsShow(false);
    }
}