package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Joueur;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class JoueurRepositoryImpl {

    private final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void create(Joueur joueur) {
        session.persist(joueur);
        System.out.println("Joueur crée");
    }

    public void update(Joueur joueur) {
        session.update(joueur);
        System.out.println("Joueur mis à jour");
    }

    public void delete(long id) {
        Joueur joueur = new Joueur();
        joueur.setId(id);
        session.delete(joueur);
        System.out.println("Joueur supprimé");
    }

    public Joueur getById(long id) {
        Joueur joueur = session.get(Joueur.class, id);
        System.out.println("Joueur lu");
        return joueur;
    }

    public List<Joueur> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Joueur> criteria = builder.createQuery(Joueur.class);
        criteria.from(Joueur.class);
        System.out.println("Joueurs lus");
        return session.createQuery(criteria).getResultList();
    }
}
