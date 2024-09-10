package com.example.demo;




import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;



@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "user"})
public class Job {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.ALL})
    @JoinTable(
        name = "savejob", // Join table name
        joinColumns = @JoinColumn(name = "job_id"), // Column for Student
        inverseJoinColumns = @JoinColumn(name = "user_id") // Column for Course
    )
    @JsonBackReference

    List<User> user;

    public Job (){

    }
    public Job(String title) {

        this.title = title;
       
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public List<User> getUser() {
        return this.user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

   public void addUser(User userT){
      if(this.user == null){
        user = new ArrayList<>();
      }
      user.add(userT);
   }
   
   public void removeUser(User userT){
        this.user.remove(userT);
        //user.remove
   }

}
