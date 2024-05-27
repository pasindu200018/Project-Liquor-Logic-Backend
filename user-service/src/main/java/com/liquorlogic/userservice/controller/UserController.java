package com.liquorlogic.userservice.controller;

import com.liquorlogic.userservice.dto.ResponseUserData;
import com.liquorlogic.userservice.entity.User;
import com.liquorlogic.userservice.enums.Role;
import com.liquorlogic.userservice.enums.Status;
import com.liquorlogic.userservice.service.EmailService;
import com.liquorlogic.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private final UserService service;
    private final EmailService emailService;
    private static final org.apache.logging.log4j.Logger loggerLog4J = LogManager.getLogger(UserController.class);

    private static int generateResetToken() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<ResponseUserData>> getAllUsers() {
        loggerLog4J.info("Start getAllUsers");
        try {
            List<User> allUsers = service.getAllUser();
            List<ResponseUserData> responseUsers = convertToResponseUserList(allUsers);

            loggerLog4J.info("End getAllUsers");
            return ResponseEntity.ok(responseUsers);
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/findUserBy")
    public ResponseEntity<ResponseUserData> getUser(@RequestParam(required = false) Status status,
                                                    @RequestParam(required = false) Role type,
                                                    @RequestParam(required = false) UUID userId,
                                                    @RequestParam(required = false) String contact) {
        loggerLog4J.info("Start getUser");

        try {
            List<User> userList;
            if (status != null) {
                userList = service.findAllByStatus(status);
            } else if (type != null) {
                userList = service.findAllByType(type);
            } else if (userId != null) {
                Optional<User> userById = service.findUserById(userId);
                userList = userById.map(Collections::singletonList).orElseGet(Collections::emptyList);
            }  else if (contact != null) {
                userList = service.findByContact(contact);
            } else {
                return ResponseEntity.badRequest().build(); // No valid query parameter provided
            }

            if (!userList.isEmpty()) {
                ResponseUserData responseUser = convertToResponseUserData(userList.get(0));
                loggerLog4J.info("End getUser");
                return ResponseEntity.ok(responseUser);
            } else {
                loggerLog4J.info("End getUser");
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/singup")
    public ResponseEntity<ResponseUserData> saveUser(@RequestBody Map<String, String> credentials){
        loggerLog4J.info("Start singup");
        try {
            String[] requiredFields = {"firstName","lastName","email","contact","status","type","username","password"};
            validateMap(credentials,requiredFields);
            String firstName = credentials.get("firstName");
            String lastName = credentials.get("lastName");
            String email = credentials.get("email");
            String heading = credentials.get("heading");
            String about = credentials.get("about");
            String contact = credentials.get("contact");
            Status status = Status.valueOf(credentials.get("status"));
            Role type = Role.valueOf(credentials.get("type"));
            String username = credentials.get("username");
            String password = credentials.get("password");
            Date date = new Date();

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setHeading(heading);
            user.setAbout(about);
            user.setContact(contact);
            user.setCreate(date);
            user.setUpdate(date);
            user.setStatus(status);
            user.setType(type);
            user.setUsername(username);
            user.setPassword(password);

            service.saveUser(user);
            ResponseUserData responseUser =convertToResponseUserData(user);
            loggerLog4J.info("End singup");
            return ResponseEntity.ok(responseUser);

        }catch (Exception e){
            handleException(e);
            loggerLog4J.info("End singup");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseUserData> userLogin(@RequestBody Map<String, String> credentials){
        loggerLog4J.info("Start login");
        try{
            String[] requiredFields = {"username","password"};
            validateMap(credentials,requiredFields);
            String username = credentials.get("username");
            String password = credentials.get("password");

            List<User> userList = service.findByUsername(username);
            if (!userList.isEmpty()){
                User user = userList.get(0);
                if (password.equals(user.getPassword())){
                    ResponseUserData responseUser = convertToResponseUserData(user);
                    loggerLog4J.info("End login");
                    return ResponseEntity.ok(responseUser);
                }else {
                    loggerLog4J.info("End login");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
                }
            }else {
                loggerLog4J.info("End login - No users found with the given username");
                return ResponseEntity.noContent().build();
            }

        }catch (Exception e){
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<String> forgotPassword(@RequestBody Map<String, String> credentials) {
        loggerLog4J.info("Start forgot-password");
        try{
            String[] requiredFields = {"email"};
            validateMap(credentials,requiredFields);
            String email = credentials.get("email");
            List<User> userOptional  = service.findByEmail(email);

            if (!userOptional.isEmpty()){
                User user = userOptional.get(0);
                int resetToken = generateResetToken();
                emailService.sendResetPasswordEmail(user.getEmail(), resetToken);
                loggerLog4J.info("End forgot-password");
                return ResponseEntity.ok("Password reset email sent successfully");
            }else {
                loggerLog4J.info("End forgot-password");
                return ResponseEntity.noContent().build();
            }
        }catch (Exception e){
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping("/reset-password")
    public String showResetPasswordForm(@RequestParam int token, Model model) {
        loggerLog4J.info("Start reset-password");
        Optional<User> userOptional = service.findUserByResetToken(token);
        if (userOptional.isPresent()){
            model.addAttribute("token",token);
            return "reset-password-form";
        }else {
            return "invalid-token";
        }
    }
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestParam int token, @RequestParam String newPassword) {
        loggerLog4J.info("Start reset-password");
        try{
            Optional<User> userOptional = service.findUserByResetToken(token);
            if (userOptional.isPresent()){
                User user = userOptional.get();
                user.setPassword(newPassword);
                service.saveUser(user);

                service.updateResetToken(user.getId(), 0);
                loggerLog4J.info("End reset-password");
                return ResponseEntity.ok("Password reset successful");
            } else {
                loggerLog4J.info("End reset-password");
                return ResponseEntity.noContent().build();
            }
        } catch (Exception e) {
            handleException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error resetting password");
        }
    }
    private List<ResponseUserData> convertToResponseUserList(List<User> allUsers) {
        if (allUsers != null && !allUsers.isEmpty()) {
            return allUsers.stream()
                    .map(this::convertToResponseUserData)
                    .toList();
        } else {
            return Collections.emptyList();
        }
    }

    private void handleException(Exception e) {
        loggerLog4J.error("Error ", e);
        e.printStackTrace();
    }

    private ResponseUserData convertToResponseUserData(User user) {
        return new ResponseUserData(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getHeading(),
                user.getAbout(),
                user.getContact(),
                user.getCreate(),
                user.getUpdate(),
                user.getStatus(),
                user.getType(),
                user.getUsername(),
                user.getPassword()
        );
    }
    private void validateMap(Map<String, String> assetCategoryMap,String[] requiredFields) {
        for (String field : requiredFields) {
            if (assetCategoryMap.get(field) == null || assetCategoryMap.get(field).isEmpty()) {
                throw new IllegalArgumentException("Not found " + field);
            }
        }
    }
}
