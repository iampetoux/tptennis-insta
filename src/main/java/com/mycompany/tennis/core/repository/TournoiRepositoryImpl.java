package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Tournoi;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class TournoiRepositoryImpl {

    private final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void create(Tournoi tournoi) {
        session.persist(tournoi);
        System.out.println("Tournoi crée");
    }

    public void update(Tournoi tournoi) {
        session.update(tournoi);
        System.out.println("Tournoi mis à jour");
    }

    public void delete(long id) {
        Tournoi tournoi = new Tournoi();
        tournoi.setId(id);
        session.delete(tournoi);
        System.out.println("Tournoi supprimé");
    }

    public Tournoi getById(long id) {
        Tournoi tournoi = session.get(Tournoi.class, id);
        System.out.println("Tournoi lu");
        return tournoi;
    }

    public List<Tournoi> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Tournoi> criteria = builder.createQuery(Tournoi.class);
        criteria.from(Tournoi.class);
        System.out.println("Tournois lus");
        return session.createQuery(criteria).getResultList();
    }
}
