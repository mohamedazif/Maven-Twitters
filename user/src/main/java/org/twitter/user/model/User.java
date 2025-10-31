package org.twitter.user.model;

import java.util.Objects;

/**
 * Represents a user in the Twitter-like application.
 * Holds personal details and lists of followers and following.
 *
 * @version                 1.0 15 Oct 2025
 * @author                  Mohamed Abdul Azif
 */
public final class User {

    private final String id;
    private final String email;
    private final String userName;
    private final String bio;
    private final String password;
    private final int age;

    /**
     * Constructs a new User with the specified details.
     *
     * @param userId   unique identifier for the user
     * @param email    user's email address
     * @param userName display name of the user
     * @param password user's account password
     * @param age      user's age
     * @param bio      short biography
     */
    public User( final String userId,  final String email, final String userName,
                final String password, final int age, final String bio) {
        this.userName = userName;
        this.id = userId;
        this.email = email;
        this.password = password;
        this.age = age;
        this.bio = bio;
    }

    public String getUserName() {
        return userName;
    }

    public String getId() {
        return id;
    }

    public String getBio() {
        return bio;
    }

    public int getAge() {
        return age;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
