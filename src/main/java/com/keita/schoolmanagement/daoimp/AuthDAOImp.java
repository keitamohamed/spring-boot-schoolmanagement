package com.keita.schoolmanagement.daoimp;

import com.keita.schoolmanagement.dao.AuthDAO;
import com.keita.schoolmanagement.model.Authenticate;
import com.keita.schoolmanagement.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class AuthDAOImp implements AuthDAO {
    @Autowired
    private AuthRepository authRepository;
//    private Connection connection = null;
//    private PreparedStatement statement = null;
//
//    private static class Singleton {
//        private static final AuthDAOImp INSTANCE = new AuthDAOImp();
//    }
//
//    public static AuthDAOImp getInstance() {return Singleton.INSTANCE;}

//    @Override
    public Authenticate auth(String username, String password) throws SQLException {
        return authRepository.auth(username, password);

//        String query = "SELECT * FROM Authenticate WHERE username = ? AND password = ?";
//        connection = SingletonConnection.getInstance().getConnection();
//        statement = connection.prepareStatement(query);
//        statement.setString(1, user.getUsername());
//        statement.setString(2, user.getPassword());
//
//        ResultSet resultSet = statement.executeQuery();
//        if (resultSet.next()) {
//            Authenticate userData = new Authenticate(resultSet.getString("username"),
//                    resultSet.getString("password"));
//            return Optional.of(userData);
//        }
//
//        return Optional.empty();
    }

    @Override
    public Optional<Authenticate> find(Long id) throws SQLException {
        return Optional.empty();
    }

    @Override
    public List<Authenticate> findAll() throws SQLException {
        return null;
    }

    @Override
    public boolean sava(Authenticate o) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Authenticate o) throws SQLException {
        return false;
    }

    @Override
    public boolean deleteData(Authenticate o) throws SQLException {
        return false;
    }
}
