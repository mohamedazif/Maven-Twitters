package org.twitter.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Represents a user in the Twitter-like application.
 * Holds personal details and lists of followers and following.
 *
 * @version                 1.0 15 Oct 2025
 * @author                  Mohamed Abdul Azif
 */
public final class User {

    private final Collection<String> followers = new HashSet<>();
    private final Collection<String> following = new HashSet<>();
    private final Collection<Retweet> retweets = new ArrayList<>();
    private final String userId;
    private final String email;

    private String userName;
    private String password;
    private String bio;
    private int age;

    /**
     * Constructs a new User with the specified details.
     *
     * @param userName display name of the user
     * @param userId   unique identifier for the user
     * @param password user's account password
     * @param email    user's email address
     * @param age      user's age
     * @param bio      short biography
     */
    public User(final String userName, final String userId, final String email, final String password, final int age, final String bio) {
        this.userName = userName;
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.age = age;
        this.bio = bio;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(final String bio) {
        this.bio = bio;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public Collection<String> getFollowers() {
        return followers;
    }

    public Collection<String> getFollowing() {
        return following;
    }

    public Collection<Retweet> getRetweets() {
        return retweets;
    }
}
