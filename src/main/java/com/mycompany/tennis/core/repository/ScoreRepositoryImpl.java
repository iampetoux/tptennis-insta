package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.HibernateUtil;
import com.mycompany.tennis.core.entity.Score;
import org.hibernate.Session;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;

public class ScoreRepositoryImpl {

    private final Session session = HibernateUtil.getSessionFactory().getCurrentSession();

    public void create(Score score) {
        session.persist(score);
        System.out.println("Score crée");
    }

    public void update(Score score) {
        session.update(score);
        System.out.println("Score mis à jour");
    }

    public void delete(long id) {
        Score score = new Score();
        score.setId(id);
        session.delete(score);
        System.out.println("Score supprimé");
    }

    public Score getById(long id) {
        Score score = session.get(Score.class, id);
        System.out.println("Score lu");
        return score;
    }

    public List<Score> list() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Score> criteria = builder.createQuery(Score.class);
        criteria.from(Score.class);
        System.out.println("Scores lus");
        return session.createQuery(criteria).getResultList();
    }
}
