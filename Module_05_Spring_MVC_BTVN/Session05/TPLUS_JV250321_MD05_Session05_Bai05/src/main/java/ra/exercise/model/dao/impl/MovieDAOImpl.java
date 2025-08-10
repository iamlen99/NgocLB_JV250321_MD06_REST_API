package ra.exercise.model.dao.impl;

import ra.exercise.model.dao.MovieDAO;
import ra.exercise.model.entity.Movie;
import ra.exercise.model.ulti.ConnectionDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class MovieDAOImpl implements MovieDAO {
    @Override
    public List<Movie> findAll() {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM Movie";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(query);
            ResultSet rs = preStmt.executeQuery();
            List<Movie> movies = new ArrayList<>();
            while (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setDuration(rs.getInt("duration"));
                movie.setLanguage(rs.getString("language"));
                movies.add(movie);
            }
            return movies;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return null;
    }

    @Override
    public boolean add(Movie movie) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("call add_movie(?,?,?,?,?,?)");
            callStmt.setString(1, movie.getTitle());
            callStmt.setString(2, movie.getDirector());
            callStmt.setString(3, movie.getGenre());
            callStmt.setString(4, movie.getDescription());
            callStmt.setInt(5, movie.getDuration());
            callStmt.setString(6, movie.getLanguage());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean update(Movie movie) {
        Connection conn = null;
        CallableStatement callStmt = null;
        try {
            conn = ConnectionDB.openConnection();
            callStmt = conn.prepareCall("call update_movie(?,?,?,?,?,?,?)");
            callStmt.setLong(1, movie.getMovieId());
            callStmt.setString(2, movie.getTitle());
            callStmt.setString(3, movie.getDirector());
            callStmt.setString(4, movie.getGenre());
            callStmt.setString(5, movie.getDescription());
            callStmt.setInt(6, movie.getDuration());
            callStmt.setString(7, movie.getLanguage());
            callStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, callStmt);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "DELETE FROM Movie where id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(query);
            preStmt.setLong(1, id);
            preStmt.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return false;
    }

    @Override
    public Optional<Movie> findById(Long id) {
        Connection conn = null;
        PreparedStatement preStmt = null;
        String query = "SELECT * FROM Movie where id = ?";
        try {
            conn = ConnectionDB.openConnection();
            preStmt = conn.prepareStatement(query);
            preStmt.setLong(1, id);
            ResultSet rs = preStmt.executeQuery();
            if (rs.next()) {
                Movie movie = new Movie();
                movie.setMovieId(rs.getLong("id"));
                movie.setTitle(rs.getString("title"));
                movie.setDirector(rs.getString("director"));
                movie.setGenre(rs.getString("genre"));
                movie.setDescription(rs.getString("description"));
                movie.setDuration(rs.getInt("duration"));
                movie.setLanguage(rs.getString("language"));
                return Optional.of(movie);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, preStmt);
        }
        return Optional.empty();
    }
}
