package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Epreuve;
import com.mycompany.tennis.core.entity.Tournoi;
import com.mycompany.tennis.core.entity.Joueur;
import com.mycompany.tennis.core.service.TournoiService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MatchRepositoryImpl {
    private final JoueurRepositoryImpl joueurRepository = new JoueurRepositoryImpl();
    private final EpreuveRepositoryImpl epreuveRepository = new EpreuveRepositoryImpl();

    public void create(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO matchs (EPREUVE_ID,VAINQUEUR_ID,FINALISTE_ID) VALUES(?,?,?)");

            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            System.out.println("Match créé");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    public void update(Match match) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE matchs SET EPREUVE_ID=? , VAINQUEUR_ID=?, FINALISTE_ID=? WHERE ID=?");

            preparedStatement.setLong(1, match.getEpreuve().getId());
            preparedStatement.setLong(2, match.getVainqueur().getId());
            preparedStatement.setLong(3, match.getFinaliste().getId());

            preparedStatement.executeUpdate();

            System.out.println("Match modifié");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    public void delete(long id) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("DELETE FROM matchs WHERE ID=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Match supprimée");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    public Match getById(long id) {
        Connection conn = null;
        Match match = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM matchs WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                match = new Match();
                match.setId(id);
                match.setEpreuve(epreuveRepository.getById(rs.getLong("EPREUVE_ID")));
                match.setVainqueur(joueurRepository.getById(rs.getLong("VAINQUEUR_ID")));
                match.setVainqueur(joueurRepository.getById(rs.getLong("FINALISTE_ID")));
            }

            System.out.println("Match lu");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        return match;
    }

    public List<Match> list() {
        Connection conn = null;
        List<Match> matchs = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT ID,EPREUVE_ID,VAINQUEUR_ID,FINALISTE_ID FROM matchs");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Match match = new Match();
                match.setId(rs.getLong("ID"));
                match.setEpreuve(epreuveRepository.getById(rs.getLong("EPREUVE_ID")));
                match.setVainqueur(joueurRepository.getById(rs.getLong("VAINQUEUR_ID")));
                match.setVainqueur(joueurRepository.getById(rs.getLong("FINALISTE_ID")));
                matchs.add(match);

            }

            System.out.println("Matchs lus");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

                try {
                    conn.rollback();
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
        return matchs;
    }
}
