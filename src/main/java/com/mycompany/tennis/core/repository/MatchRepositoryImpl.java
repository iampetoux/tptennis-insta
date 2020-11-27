package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Match;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class MatchRepositoryImpl {

    private final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void create(Match match) {
        session.persist(match);
        System.out.println("Match crée");
    }

    public void update(Match match) {
        session.update(match);
        System.out.println("Match mis à jour");
    }

    public void delete(long id) {
        Match match = new Match();
        match.setId(id);
        session.delete(match);
        System.out.println("Match supprimé");
    }

    public Match getById(long id) {
        Match match = session.get(Match.class, id);
        System.out.println("Match lu");
        return match;
    }

    public List<Match> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Match> criteria = builder.createQuery(Match.class);
        criteria.from(Match.class);
        System.out.println("Matchs lus");
        return session.createQuery(criteria).getResultList();
    }
}
