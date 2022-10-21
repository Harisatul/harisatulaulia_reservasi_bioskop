package org.binar.challenge_4.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.exception.ExceptionResponse;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("cinema/api/v1/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping
    @Operation(
            tags = {"User"},
            operationId = "getAllUser",
            summary = "get all user data",
            description = "to fetch all user data",
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Users.class),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<List<Users>> getAllUser(){
        List<Users> allUsers = userService.getAllUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<Users> getUserByUsername(@RequestBody String username) {
        Users userByUsername = userService.getUserByUsername(username);
        return new ResponseEntity<>(userByUsername,OK);
    }

    @PostMapping
    @Operation(
            tags = {"User"},
            operationId = "addUser",
            summary = "add user data",
            description = "to add user data.",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This is the request body for update User request.",
                    content = @Content(schema = @Schema(implementation = Users.class))),
            parameters = {
                    @Parameter(name = "username", description = "this is username. should be unique",
                            example = "andrew",schema = @Schema(type = "String")),
                    @Parameter(name = "email", description = "this is email. should be unique",
                            example = "andrew@gmail.com", required = true,  schema = @Schema(type = "String")),
                    @Parameter(name = "password", description = "this is password. Strongly combined password suggested",
                            example = "@nDrew!23", required = true, schema = @Schema(type = "String")),
                    @Parameter(name = "isActive", description = "this is boolean. should passing true value",
                            example = "true", required = true, schema = @Schema(type = "String"))},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Users.class, type = "String"),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<Users> addUser(@RequestBody Users user){
        ResponseEntity<Users> userResponseEntity = userService.addUser(user);
        return userResponseEntity;
    }

    @PutMapping("{username}")
    @Operation(
            tags = {"User"},
            operationId = "updateUser",
            summary = "update user data",
            description = "to update user data. username required as path of endpoint",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "This is the request body for update User request.",
                    content = @Content(schema = @Schema(implementation = Users.class))),
            parameters = {
                          @Parameter(name = "username", description = "this is username. should be unique",
                            example = "andrew",schema = @Schema(type = "String")),
                          @Parameter(name = "email", description = "this is email. should be unique",
                                  example = "andrew@gmail.com", required = true,  schema = @Schema(type = "String")),
                          @Parameter(name = "password", description = "this is password. Strongly combined password suggested",
                                  example = "@nDrew!23", required = true, schema = @Schema(type = "String")),
                          @Parameter(name = "isActive", description = "this is boolean. should passing true value",
                                example = "true", required = true, schema = @Schema(type = "String"))},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = Users.class, type = "String"),mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                    content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                            mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )
    public ResponseEntity<Users> updateUser(@RequestBody Users user, @PathVariable(value = "username") String username){
        Users users = userService.updateUser(user, username);
        return new ResponseEntity<>(users, HttpStatus.CREATED);
    }

    @DeleteMapping("{username}")
    @Operation(
            tags = {"User"},
            operationId = "deletelUser",
            summary = "delete user by Usernam ",
            description = "to delete user data",
            parameters = {
                    @Parameter(name = "username", description = "this is username. should be found",
                            example = "andrew",schema = @Schema(type = "String"))},
            responses = {@io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200",
                    content = @Content(
                            schema = @Schema(implementation = ApiResponse.class),mediaType = MediaType.APPLICATION_JSON_VALUE),
                    description = "Success Response."),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Bad Request"),
                    @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "405",
                            content = @Content(schema = @Schema(implementation = ExceptionResponse.class),
                                    mediaType = MediaType.APPLICATION_JSON_VALUE),
                            description = "Method Not Allowed")
            }

    )

    public ResponseEntity<ApiResponse> deleteUser(@PathVariable(value = "username") String username){
        ApiResponse apiResponse = userService.deleteUsers(username);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


}
