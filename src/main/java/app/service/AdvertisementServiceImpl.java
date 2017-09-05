package app.service;

import app.dao.AdvertisementDao;
import app.dao.UserDao;
import app.entity.Advertisement;
import app.entity.AdvertisementAction;
import app.entity.Category;
import app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.List;

public class AdvertisementServiceImpl implements AdvertisementService {
    @Autowired
    AdvertisementDao advertisementDao;

    @Autowired
    UserDao userDao;

    @Override
    public List<Category> getAllCategories() {
        return advertisementDao.getAllCategories();
    }

    @Override
    public List<AdvertisementAction> getAdvertisementActions(String authtoken) {
        if(userDao.isLoggedIn(authtoken))
            return advertisementDao.getAdvertisementActions();
        else
            //return empty list
            return new ArrayList<AdvertisementAction>();
    }

    @Override
    public void postAd(Advertisement advertisement, String authtoken) {
        if(userDao.isLoggedIn(authtoken)){
            User user = userDao.getUserByAuthtoken(authtoken);
            advertisement.setPostedBy(user);
            advertisement.setPostedDate(System.currentTimeMillis());
            advertisementDao.postAd(advertisement);
        } else {
            //todo baadme
        }
    }

    @Override
    public List<Advertisement> getAllAdvertismentByUser(String authtoken) {
        User user = userDao.getUserByAuthtoken(authtoken);
        return advertisementDao.getAllAdvertismentByUser(user);
    }
}
