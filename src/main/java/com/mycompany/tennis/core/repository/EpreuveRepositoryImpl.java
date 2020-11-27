package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Epreuve;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class EpreuveRepositoryImpl {

    private final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void create(Epreuve epreuve) {
        session.persist(epreuve);
        System.out.println("Epreuve créée");
    }

    public void update(Epreuve epreuve) {
        session.update(epreuve);
        System.out.println("Epreuve mise à jour");
    }

    public void delete(long id) {
        Epreuve epreuve = new Epreuve();
        epreuve.setId(id);
        session.delete(epreuve);
        System.out.println("Epreuve supprimée");
    }

    public Epreuve getById(long id) {
        Epreuve epreuve = session.get(Epreuve.class, id);
        System.out.println("Epreuve lue");
        return epreuve;
    }

    public List<Epreuve> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Epreuve> criteria = builder.createQuery(Epreuve.class);
        criteria.from(Epreuve.class);
        System.out.println("Epreuves lues");
        return session.createQuery(criteria).getResultList();
    }
}
