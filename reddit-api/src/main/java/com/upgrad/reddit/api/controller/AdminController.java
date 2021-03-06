package com.upgrad.reddit.api.controller;

import com.upgrad.reddit.api.model.UserDeleteResponse;
import com.upgrad.reddit.service.business.AdminBusinessService;
import com.upgrad.reddit.service.entity.UserEntity;
import com.upgrad.reddit.service.exception.AuthorizationFailedException;
import com.upgrad.reddit.service.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminBusinessService adminBusinessService;

    /**
     * A controller method to delete a user in the database.
     *
     * @param userId        - The uuid of the user to be deleted from the database.
     * @param authorization - A field in the request header which contains the JWT token.
     * @return - ResponseEntity<UserDeleteResponse> type object along with Http status OK.
     * @throws AuthorizationFailedException
     * @throws UserNotFoundException
     */

    @DeleteMapping(value = "/user/{userId}")
    public ResponseEntity<UserDeleteResponse> deleteUser(@PathVariable String userId, @RequestHeader("authorization") String authorization)throws UserNotFoundException, AuthorizationFailedException{
        UserEntity userEntity = adminBusinessService.deleteUser(authorization,userId);
        UserDeleteResponse userDeleteResponse = new UserDeleteResponse();
        userDeleteResponse.setId(userEntity.getUuid());
        userDeleteResponse.setStatus("User Successfully deleted");
        return new ResponseEntity<>(userDeleteResponse, HttpStatus.OK);
    }
}
