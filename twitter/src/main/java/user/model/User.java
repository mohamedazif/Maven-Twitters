package user.model;

import java.util.Objects;

/**
 * Represents a user in the Twitter-like application.
 * Holds personal details and lists of followers and following.
 *
 * @version                 1.0 15 Oct 2025
 * @author                  Mohamed Abdul Azif
 */
public class User {

    private final long id;
    private final String handle;
    private final String email;
    private final String name;
    private final String bio;
    private final String password;
    private final int age;

    /**
     * Constructs a new User with the specified details.
     *
     * @param handle   unique identifier for the user
     * @param email    user's email address
     * @param name     display name of the user
     * @param password user's account password
     * @param age      user's age
     * @param bio      short biography
     */
    public User(final String handle, final String email, final String name,
                final String password, final int age, final String bio) {
        this.id = 0;
        this.name = name;
        this.handle = handle;
        this.email = email;
        this.password = password;
        this.age = age;
        this.bio = bio;
    }

    /**
     * Constructs a new User object with UserBuilder.
     *
     * @param userBuilder   Builder class object of UserBuilder
     */
    public User(final UserBuilder userBuilder) {
        this.name = userBuilder.name;
        this.handle = userBuilder.handle;
        this.id = userBuilder.id;
        this.email = userBuilder.email;
        this.password = userBuilder.password;
        this.age = userBuilder.age;
        this.bio = userBuilder.bio;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getHandle() {
        return handle;
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
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        return Objects.equals(id, ((User) object).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /**
     * Builder class for building User object
     */
    public static class UserBuilder {
        private long id;
        private String handle;
        private String email;
        private String name;
        private String bio;
        private String password;
        private int age;

        public UserBuilder id(final long id) {
            this.id = id;
            return this;
        }

        public UserBuilder handle(final String handle) {
            this.handle = handle;
            return this;
        }

        public UserBuilder email(final String email) {
            this.email = email;
            return this;
        }

        public UserBuilder name(final String name) {
            this.name = name;
            return this;
        }

        public UserBuilder bio(final String bio) {
            this.bio = bio;
            return this;
        }

        public UserBuilder password(final String password) {
            this.password = password;
            return this;
        }

        public UserBuilder age(final int age) {
            this.age = age;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
