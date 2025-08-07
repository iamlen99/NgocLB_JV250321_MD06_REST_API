package ra.exercise.model.repository.impl;

import org.springframework.stereotype.Repository;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.repository.MovieRepository;
import ra.exercise.model.util.DBUtil;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class MovieRepositoryImpl implements MovieRepository {

    @Override
    public List<Movie> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM movie";
        try {
            conn = DBUtil.openConnection();
            preStmt = conn.prepareStatement(query);
            ResultSet rs = preStmt.executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseDate(LocalDate.parse(rs.getString("release_date")));
                movie.setGenre(rs.getString("genre"));
                movie.setPoster(rs.getString("poster"));
                movies.add(movie);
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, preStmt);
        }
        return null;
    }

    @Override
    public boolean save(Movie movie) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = DBUtil.openConnection();
            callStmt = conn.prepareCall("call add_movie(?,?,?,?,?)");
            callStmt.setString(1, movie.getTitle());
            callStmt.setString(2, movie.getDirector());
            callStmt.setDate(3, Date.valueOf(movie.getReleaseDate()));
            callStmt.setString(4, movie.getGenre());
            callStmt.setString(5, movie.getPoster());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = DBUtil.openConnection();
            callStmt = conn.prepareCall("call update_movie(?,?,?,?,?,?)");
            callStmt.setInt(1, movie.getId());
            callStmt.setString(2, movie.getTitle());
            callStmt.setString(3, movie.getDirector());
            callStmt.setDate(4, Date.valueOf(movie.getReleaseDate()));
            callStmt.setString(5, movie.getGenre());
            callStmt.setString(6, movie.getPoster());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "DELETE FROM movie where id = ?";
        try {
            conn = DBUtil.openConnection();
            preStmt = conn.prepareStatement(query);
            preStmt.setInt(1, id);
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, preStmt);
        }
        return false;
    }

    @Override
    public Optional<Movie> findById(Integer id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM movie where id = ?";
        try {
            conn = DBUtil.openConnection();
            preStmt = conn.prepareStatement(query);
            preStmt.setInt(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Movie movie = new Movie();
                movie.setId(rs.getInt("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setReleaseDate(LocalDate.parse(rs.getString("release_date")));
                movie.setGenre(rs.getString("genre"));
                movie.setPoster(rs.getString("poster"));
                return Optional.of(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }
}
