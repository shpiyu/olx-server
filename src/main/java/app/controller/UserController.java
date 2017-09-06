package app.controller;

import app.entity.User;
import app.service.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    //todo : put it somewhere globally

    public JSONObject buildResponse(boolean isError, String message){
        JSONObject response = new JSONObject();
        try{
            if (isError){
                response.put("error","true");
            } else {
                response.put("error","false");
            }
            response.put("message",message);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return response;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String registerUser(@RequestBody User user){
        JSONObject response = new JSONObject();
        boolean registrationError = false;
        String errorMessage = "";

        // todo remove cross cutting concerns
        try {
            userService.register(user);
        } catch (DataIntegrityViolationException e){
            registrationError = true;
            errorMessage = "user already exists";
        }

            if (registrationError){
                response = buildResponse(true, errorMessage);
            } else {
                response = buildResponse(false, "user registered successfully");
            }

        return response.toString();
    }


    @RequestMapping(value = "/login", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String login(@RequestBody User user){
        String authtoken = userService.login(user);
        JSONObject response = new JSONObject();
        if(authtoken!=null){
            try {
                response.put("auth-token",authtoken);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response.toString();
        } else {
            try {
                response.put("error","login failed");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return response.toString();
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void logout(@RequestHeader(name = "auth-token")String authtoken){
        userService.logout(authtoken);
    }


    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public @ResponseBody User getUserInfo(@RequestParam(name = "userId")int userId, @RequestHeader(name = "auth-token")String authtoken){
        return userService.getUserInfo(userId, authtoken);
    }
}
