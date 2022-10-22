package org.binar.challenge_4.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import org.binar.challenge_4.dto.UserDTO;
import org.binar.challenge_4.entities.Role;
import org.binar.challenge_4.entities.Users;
import org.binar.challenge_4.exception.ExceptionResponse;
import org.binar.challenge_4.payload.ApiResponse;
import org.binar.challenge_4.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/getuser")
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

    @GetMapping("{username}")
    public ResponseEntity<Users> getUserByUsername(@PathVariable String username) {
        Users userByUsername = userService.getUserByUsername(username);
        return new ResponseEntity<>(userByUsername,OK);
    }

    @PostMapping("/signup")
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
    public ResponseEntity<Users> addUser(@RequestBody UserDTO users){
        ResponseEntity<Users> userResponseEntity = userService.addUser(users);
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

    @GetMapping("refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authHeader = request.getHeader(AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                String refresh_token = authHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT decodedJWT = verifier.verify(refresh_token);
                String username = decodedJWT.getSubject();
                Users userByUsername = userService.getUserByUsername(username);

                String access_token = JWT.create()
                        .withSubject(userByUsername.getUsername())
                        .withExpiresAt(new Date(System.currentTimeMillis() + 1 * 60 * 1000))
                        .withIssuer(request.getRequestURI().toString())
                        .withClaim("roles", userByUsername.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
                        .sign(algorithm);
                HashMap<String, String> tokens = new HashMap<>();
                tokens.put("access_token", access_token);
                tokens.put("refresh_token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Successfully created new access token",tokens);
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }catch (Exception exception){
                ExceptionResponse exceptionResponse = new ExceptionResponse(
                        List.of(exception.getMessage()),FORBIDDEN.toString() ,FORBIDDEN.value());
                response.setContentType(APPLICATION_JSON_VALUE);
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                response.setStatus(FORBIDDEN.value());
                objectMapper.writeValue(response.getOutputStream(),exceptionResponse);
            }
        }else {
            throw new RuntimeException("Refresh token is missing");
        }
    }


}
