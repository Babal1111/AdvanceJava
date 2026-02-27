package org.example.repository;

import org.example.model.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
public class UserRepositoryImplementation implements UserRepository {

    private List<User> users = new ArrayList<>();

    public UserRepositoryImplementation() {
        users.add(new User(1L, "John Doe", "john@example.com"));
        users.add(new User(2L, "Jane Smith", "jane@example.com"));
    }

    @Override
    public List<User> findAll() {
        return users;
    }

    @Override
    public User findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(User user) {
        users.add(user);
    }
}
