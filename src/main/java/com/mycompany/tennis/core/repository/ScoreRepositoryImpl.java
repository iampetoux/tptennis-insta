package com.mycompany.tennis.core.repository;

import com.mycompany.tennis.core.DataSourceProvider;
import com.mycompany.tennis.core.entity.Match;
import com.mycompany.tennis.core.entity.Score;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ScoreRepositoryImpl {
    private final MatchRepositoryImpl matchRepository = new MatchRepositoryImpl();

    public void create(Score score) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("INSERT INTO scores (MATCH_ID, SET_1, SET_2, SET_3, SET_4, SET_5) VALUES(?, ?, ?, ?, ?, ?)");

            preparedStatement.setLong(1, score.getMatch().getId());
            preparedStatement.setByte(2, score.getSet1());
            preparedStatement.setByte(3, score.getSet2());
            preparedStatement.setByte(4, score.getSet3());
            preparedStatement.setByte(5, score.getSet4());
            preparedStatement.setByte(6, score.getSet5());

            preparedStatement.executeUpdate();

            System.out.println("Score créé");
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

    public void update(Score score) {
        Connection conn = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("UPDATE scores SET MATCH_ID=? , SET_1=?, SET_2=?, SET_3=?, SET_4=?, SET_5=? WHERE ID=?");

            preparedStatement.setLong(1, score.getMatch().getId());
            preparedStatement.setLong(2, score.getSet1());
            preparedStatement.setLong(3, score.getSet2());
            preparedStatement.setLong(4, score.getSet3());
            preparedStatement.setLong(5, score.getSet4());
            preparedStatement.setLong(6, score.getSet5());

            preparedStatement.executeUpdate();

            System.out.println("Score modifié");
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
                    conn.prepareStatement("DELETE FROM scores WHERE ID=?");
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

    public Score getById(long id) {
        Connection conn = null;
        Score score = null;
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT * FROM scores WHERE id = ?");
            preparedStatement.setLong(1, id);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                score = new Score();
                score.setId(id);
                score.setMatch(matchRepository.getById(rs.getLong("MATCH_ID")));
                score.setSet1(rs.getByte("SET_1"));
                score.setSet2(rs.getByte("SET_2"));
                score.setSet3(rs.getByte("SET_3"));
                score.setSet4(rs.getByte("SET_4"));
                score.setSet5(rs.getByte("SET_5"));
            }

            System.out.println("Score lu");
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
        return score;
    }

    public List<Score> list() {
        Connection conn = null;
        List<Score> scores = new ArrayList<>();
        try {
            DataSource dataSource = DataSourceProvider.getSingleDataSourceInstance();

            conn = dataSource.getConnection();

            //conn.setAutoCommit(false);

            PreparedStatement preparedStatement =
                    conn.prepareStatement("SELECT ID,EPREUVE_ID,VAINQUEUR_ID,FINALISTE_ID FROM matchs");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Score score = new Score();
                score.setId(rs.getLong("ID"));
                score.setMatch(matchRepository.getById(rs.getLong("MATCH_ID")));
                score.setSet1(rs.getByte("SET_1"));
                score.setSet2(rs.getByte("SET_2"));
                score.setSet3(rs.getByte("SET_3"));
                score.setSet4(rs.getByte("SET_4"));
                score.setSet5(rs.getByte("SET_5"));
                scores.add(score);

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
        return scores;
    }
}
