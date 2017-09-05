package app.controller;

import app.entity.Advertisement;
import app.entity.AdvertisementAction;
import app.entity.Category;
import app.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdvertisementController {
    @Autowired
    AdvertisementService advertisementService;

    @RequestMapping(value = "/categories", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<Category> getAllCategories(){
        return advertisementService.getAllCategories();
    }

    @RequestMapping(value = "/actions", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AdvertisementAction> getAdvertisementActions(@RequestHeader(name = "auth-token")String authtoken){
        return advertisementService.getAdvertisementActions(authtoken);
    }


    @RequestMapping(value = "/postAd", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void postAd(@RequestBody Advertisement advertisement, @RequestHeader(name = "auth-token")String authtoken){
        advertisementService.postAd(advertisement,authtoken);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    public @ResponseBody List<Advertisement> getAllAdvertismentByUser(@RequestHeader(name = "auth-token")String authtoken){
        return advertisementService.getAllAdvertismentByUser(authtoken);
    }
}
