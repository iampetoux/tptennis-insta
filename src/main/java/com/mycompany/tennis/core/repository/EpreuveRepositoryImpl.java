package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Epreuve;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpreuveRepositoryImpl {
    private final TournoiRepositoryImpl tournoiRepository = new TournoiRepositoryImpl();

    public void create(Epreuve epreuve) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO epreuves (ANNEE,TOURNOI_ID,TYPE_EPREUVE) VALUES(?,?,?)");

            preparedStatement.setLong(1, epreuve.getAnnee());
            preparedStatement.setLong(2, epreuve.getTournoi().getId());
            preparedStatement.setString(3, epreuve.getTypeEpreuve().toString());

            preparedStatement.executeUpdate();

            System.out.println("Tournoi créé");
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

    public void update(Epreuve epreuve) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE epreuves SET ANNEE=? , TOURNOI_ID=?, TYPE_EPREUVE=? WHERE ID=?");

            preparedStatement.setLong(1, epreuve.getAnnee());
            preparedStatement.setLong(2, epreuve.getTournoi().getId());
            preparedStatement.setString(3, epreuve.getTypeEpreuve().toString());

            preparedStatement.executeUpdate();

            System.out.println("Epreuve modifiée");
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
                    conn.prepareStatement("DELETE FROM epreuves WHERE ID=?");
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();

            System.out.println("Epreuve supprimée");
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

    public Epreuve getById(long id) {
        Connection conn = null;
        Epreuve epreuve = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM epreuves WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                epreuve = new Epreuve();
                epreuve.setId(id);
                epreuve.setAnnee(rs.getInt("ANNEE"));
                epreuve.setTypeEpreuve(rs.getString("TYPE_EPREUVE").charAt(0));
                epreuve.setTournoi(tournoiRepository.getById(rs.getLong("TOURNOI_ID")));
            }

            System.out.println("Epreuve lue");
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
        return epreuve;
    }

    public List<Epreuve> list() {
        Connection conn = null;
        List<Epreuve> epreuves = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT ID,ANNEE,TYPE_EPREUVE,TOURNOI_ID FROM epreuves");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Epreuve epreuve = new Epreuve();
                epreuve.setId(rs.getLong("ID"));
                epreuve.setAnnee(rs.getInt("ANNEE"));
                epreuve.setTypeEpreuve(rs.getString("TYPE_EPREUVE").charAt(0));
                epreuve.setTournoi(tournoiRepository.getById(rs.getLong("TOURNOI_ID")));
                epreuves.add(epreuve);

            }

            System.out.println("Joueurs lus");
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
        return epreuves;
    }
}
