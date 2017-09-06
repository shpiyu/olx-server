package app.service;

import app.entity.Advertisement;
import app.entity.AdvertisementAction;
import app.entity.Category;
import java.util.List;

public interface AdvertisementService {
    List<Category> getAllCategories();
    List<AdvertisementAction> getAdvertisementActions(String authtoken);
    void postAd(Advertisement advertisement, String authtoken);

    List<Advertisement> getAllAdvertismentByUser(String authtoken);

    void updateAdvertisment(Advertisement advertisement, String authtoken);

    Advertisement getSpecificAdOfUser(int id, String authtoken);

    void deleteAdvertisement(int id, String authtoken);
}
