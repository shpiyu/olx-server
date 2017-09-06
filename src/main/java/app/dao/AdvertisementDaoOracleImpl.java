package app.dao;

import app.entity.Advertisement;
import app.entity.AdvertisementAction;
import app.entity.Category;
import app.entity.User;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class AdvertisementDaoOracleImpl extends HibernateDaoSupport implements AdvertisementDao {

    @Override
    @Transactional
    public List<Category> getAllCategories() {
        Category cloths = new Category("cloths");
        Category furniture = new Category("furniture");
        getHibernateTemplate().save(cloths);
        getHibernateTemplate().save(furniture);
        return (List<Category>) getHibernateTemplate().find("from Category");
    }

    @Override
    @Transactional
    public List<AdvertisementAction> getAdvertisementActions() {
        return (List<AdvertisementAction>) getHibernateTemplate().find("from AdvertisementAction");
    }

    @Override
    @Transactional
    public void postAd(Advertisement advertisement) {
        String categoryName = advertisement.getCategory().getCategoryName();
        List<Category> categories = (List<Category>) getHibernateTemplate().find("from Category where categoryName = ?",categoryName);
        advertisement.setCategory(categories.get(0));


        getHibernateTemplate().save(advertisement);
    }

    @Override
    public List<Advertisement> getAllAdvertismentByUser(User user) {
        return (List<Advertisement>) getHibernateTemplate().find("from Advertisement where postedBy = ?", user);
    }

    @Override
    @Transactional
    public void updateAdvertisment(Advertisement advertisement) {
        Advertisement storedAd = getHibernateTemplate().load(Advertisement.class,advertisement.getId());
        storedAd.setName(advertisement.getName());
        Category storedCategory = getHibernateTemplate().get(Category.class, advertisement.getCategory().getCategoryName());
        storedAd.setCategory(storedCategory);
        storedAd.setDescription(advertisement.getDescription());
        storedAd.setTitle(advertisement.getTitle());
        getHibernateTemplate().update(storedAd);
    }
}
