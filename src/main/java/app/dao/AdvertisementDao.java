package app.dao;

import app.entity.Advertisement;
import app.entity.AdvertisementAction;
import app.entity.Category;
import app.entity.User;

import java.util.Calendar;
import java.util.List;

public interface AdvertisementDao {
    List<Category> getAllCategories();
    List<AdvertisementAction> getAdvertisementActions();
    void postAd(Advertisement advertisement);

    List<Advertisement> getAllAdvertismentByUser(User user);

    void updateAdvertisment(Advertisement advertisement);

    Advertisement getSpecificAdOfUser(int id);

    void deleteAdvertisement(int id);
}
