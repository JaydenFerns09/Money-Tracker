package com.example.moneytracker;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {

    @Query("select * from Users")
    List<UserDB> getALlUsers();

    @Insert
    void addUsr(UserDB userDB);

    @Query("Select id from Users where username = :name")
    Integer getId(String name);
    
    @Query("select total from Users where username = :uname")
    Integer getAmountOfUser(String uname);

    @Query("update Users set total=:t where username=:name")
    void updateTotal(Integer t,String name);
}
