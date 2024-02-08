package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserSortField;
import gigachads.noenemies.diploma.domain.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Operation(summary = "Get current user by session",
            operationId = "getCurrentUser",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found user",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content)})
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getCurrentUser(Principal principal) {
        var token = (OAuth2AuthenticationToken) principal;
        token.getPrincipal().getAttributes();

        System.out.println(
                token.getPrincipal().getAttributes()
        );
        return null;
//        return userMapper.toResponse(userService.getUserById(UserId.of()));
    }

    @Operation(summary = "Get user by Id",
            operationId = "getUserById",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found user",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "User not found",
                            content = @Content)})
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getUserById(@PathVariable UserId userId) {
        return userMapper.toResponse(userService.getUserById(userId));
    }

    @Operation(summary = "Get student by barcode",
            operationId = "getStudentByBarcode",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found student",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))}),
                    @ApiResponse(responseCode = "400", description = "Invalid barcode supplied",
                            content = @Content),
                    @ApiResponse(responseCode = "404", description = "Student not found",
                            content = @Content)})
    @GetMapping("/barcode/{barcode}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponse getStudentByBarcode(@PathVariable String barcode) {
        return userMapper.toResponse(userService.getUserByBarcode(barcode));
    }

    @Operation(summary = "Get users with pagination",
            operationId = "getUsers",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Page of users",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))})
            })
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public Page<UserResponse> getUsers(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "10") Integer sizePerPage,
            @RequestParam(defaultValue = "LASTNAME") UserSortField sortField,
            @RequestParam(defaultValue = "ASC") Sort.Direction sortDirection
    ) {
        Pageable pageable = PageRequest.of(page, sizePerPage, sortDirection, sortField.getDatabaseFieldName());
        return userService.findAllByPage(pageable).map(userMapper::toResponse);
    }

    @Operation(summary = "Get all candidates",
            operationId = "getAllCandidates",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all candidates",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))})
                    })
    @GetMapping("/candidates")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllCandidates() {
        return userMapper.toResponse(userService.getAllCandidates());
    }

    @Operation(summary = "Get all active candidates",
            operationId = "getAllActiveCandidates",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "List of all current candidates",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserResponse.class))})
            })
    @GetMapping("/candidates/active")
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAllActiveCandidates() {
        return userMapper.toResponse(userService.getAllActiveCandidates());
    }

    @Operation(summary = "Upload user photo",
            operationId = "uploadCandidatureImage",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully uploaded photo",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))})
            })
    @PostMapping("/{userId}/plan/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public User uploadUserImage(
            Principal principal,
            @PathVariable UserId userId,
            @RequestParam("photo") MultipartFile photo
    ) {
        return userService.saveProfilePhoto(userId, photo);
    }


}