package org.twitter.profile.repository;

/**
 * Interface for Editing/Deleting the user and their details.
 *
 * @version         1.0
 * @author          Mohamed Abdul Azif
 */
public interface EditUserRepository {

    /**
     * To delete a user from database.
     *
     * @param id                ID of the user
     */
    void deleteUser(final long id);

    /**
     * To update the age of the user.
     *
     * @param id                ID of the user
     * @param updatedAge        Age to change
     */
    void updateAge(final long id, final int updatedAge);

    /**
     * To update the biography of the user.
     *
     * @param id                ID of the user
     * @param updatedBio        Bio to change
     */
    void updateBio(final long id, final String updatedBio);

    /**
     * To update the password of the user.
     *
     * @param id                ID of the user
     * @param updatedPassword   Password to change
     */
    void updatePassword(final long id, final String updatedPassword);

    /**
     * To update the name of the user.
     *
     * @param id                ID of the user
     * @param updatedName       Name to change
     */
    void updateUsername(final long id, final String updatedName);
}
