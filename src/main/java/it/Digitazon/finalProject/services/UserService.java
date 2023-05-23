package it.Digitazon.finalProject.services;

import it.Digitazon.finalProject.persistence.entities.User;
import it.Digitazon.finalProject.persistence.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAll() { // tradforma oggetti di tipo entità in tipo di oggetti dto
        return userRepository.findAll();
    }


    public User getById(long id) {
        Optional<User> optional = userRepository.findById(id);
        if (optional.isEmpty()) {
            throw new IllegalStateException("User not found");
        }
        return optional.get();
    }


    public User create(User newUser) {
        newUser = userRepository.save(newUser);
        return newUser;
    }

    public User update(long id, User updateUser) {
        Optional<User> optionalUser = userRepository.findById(id); // cerco entità da aaggiornare tramite id
        if (optionalUser.isEmpty()) { // se non esiste restituisco vuoto
            throw new IllegalStateException("User non found");
        }
        User entityToUpdate = optionalUser.get();
        updateUser.setId(entityToUpdate.getId());
        updateUser=userRepository.save(updateUser);
        return updateUser;
    }

    public User delete(long id) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) { // se non esiste restituisco vuoto
            throw new IllegalStateException("User non found");
        }
        User entityToDelete = optionalUser.get();
        userRepository.delete(entityToDelete);

        return entityToDelete;
    }
}

