package hu.unideb.inf.DAO;

import hu.unideb.inf.Modell.Patient;
import hu.unideb.inf.Modell.User;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUserDAO implements UserDAO {

    private List<User> users;

    public FileUserDAO(){
        try(FileInputStream fis = new FileInputStream("users.ser");
            ObjectInputStream ois = new ObjectInputStream(fis);) {
            users = (List<User>)ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            users = new ArrayList<>();
        }

    }

    public void serialize(){
        try (FileOutputStream fos = new FileOutputStream("user.ser");
             ObjectOutputStream oos = new ObjectOutputStream(fos);) {
            oos.writeObject(users);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(User user){
        if(!users.contains(user)){
            users.add(user);
            serialize();
        }
    }

    @Override
    public void deleteUser(User user){
        if (!users.contains(user)){
            users.add(user);
            serialize();
        }
    }

    @Override
    public void updateUser(User user){
        if (users.contains(user)) {
            users.remove(user);
            users.add(user);
            serialize();
        }else{
            users.add(user);
            serialize();
        }
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public void close() throws Exception { }

    }
