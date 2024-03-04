package gigachads.noenemies.diploma.api.controller;

import gigachads.noenemies.diploma.api.dto.UserResponse;
import gigachads.noenemies.diploma.api.dto.VoteResponse;
import gigachads.noenemies.diploma.domain.mapper.UserMapper;
import gigachads.noenemies.diploma.domain.mapper.VoteMapper;
import gigachads.noenemies.diploma.domain.model.User;
import gigachads.noenemies.diploma.domain.model.UserId;
import gigachads.noenemies.diploma.domain.model.UserRole;
import gigachads.noenemies.diploma.domain.model.UserSortField;
import gigachads.noenemies.diploma.domain.service.UserService;
import gigachads.noenemies.diploma.domain.service.VoteService;
import gigachads.noenemies.diploma.exception.InvalidRoleException;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import static gigachads.noenemies.diploma.HelperClass.getUserIdByOauth2Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final VoteService voteService;
    private final UserMapper userMapper;
    private final VoteMapper voteMapper;

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
        return userMapper.toResponse(userService.getUserById(getUserIdByOauth2Principal(principal)));
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
    public UserResponse getUserById(@PathVariable("userId") UserId userId) {
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

    @Operation(summary = "Get user votes user id",
            operationId = "getUserVotesByUserId",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Found user votes",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = VoteResponse.class))})
                    })
    @GetMapping("/{userId}/votes")
    @ResponseStatus(HttpStatus.OK)
    public List<VoteResponse> getUserVotesByUserId(@PathVariable UserId userId,
                                                   @RequestParam(value = "limit", defaultValue = "5", required = false) Integer limit) {
        return voteMapper.toResponse(voteService.findUserVotesByUserId(userId, limit));
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
            @RequestParam(name = "page", defaultValue = "0", required = false) Integer page,
            @RequestParam(name = "size", defaultValue = "10", required = false) Integer sizePerPage,
            @RequestParam(defaultValue = "LASTNAME", required = false) UserSortField sortField,
            @RequestParam(defaultValue = "ASC", required = false) Sort.Direction sortDirection,
            @RequestParam(name = "role", defaultValue = "", required = false) String userRole
            ) {
        Pageable pageable = PageRequest.of(page, sizePerPage, sortDirection, sortField.getDatabaseFieldName());
        if (userRole.isEmpty()) {
            return userService.findAllByPage(pageable).map(userMapper::toResponse);
        } else if (!UserRole.isValidRole(userRole)) {
            throw new InvalidRoleException("Invalid user role: " + userRole);
        } else {
            return userService.findAllByRoleAndPage(pageable, UserRole.valueOf(userRole)).map(userMapper::toResponse);
        }
    }
//
//    @Operation(summary = "Get all candidates",
//            operationId = "getAllCandidates",
//            tags = {"User"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "List of all candidates",
//                            content = {@Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = UserResponse.class))})
//                    })
//    @GetMapping("/candidates")
//    @ResponseStatus(HttpStatus.OK)
//    public List<UserResponse> getAllCandidates() {
//        return userMapper.toResponse(userService.getAllCandidates());
//    }

//    @Operation(summary = "Get all active candidates",
//            operationId = "getAllActiveCandidates",
//            tags = {"User"},
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "List of all current candidates",
//                            content = {@Content(mediaType = "application/json",
//                                    schema = @Schema(implementation = UserResponse.class))})
//            })
//    @GetMapping("/candidates/active")
//    @ResponseStatus(HttpStatus.OK)
//    public List<UserResponse> getAllActiveCandidates() {
//        return userMapper.toResponse(userService.getAllActiveCandidates());
//    }

    @Operation(summary = "Upload user photo",
            operationId = "uploadCandidatureImage",
            tags = {"User"},
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully uploaded photo",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = String.class))})
            })
    @PostMapping("/photo")
    @ResponseStatus(HttpStatus.CREATED)
    public User uploadUserImage(
            Principal principal,
            @RequestParam("photo") MultipartFile photo
    ) {
        return userService.saveProfilePhoto(getUserIdByOauth2Principal(principal), photo);
    }
}