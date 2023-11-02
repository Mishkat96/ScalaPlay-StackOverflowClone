package models

//used this code from wpo
import javax.inject.Inject

@javax.inject.Singleton
class UserDao @Inject()() {

    var users: Seq[User] = Seq(
        User("Alex", "Alex"),
        User("Bob", "Bob"),
        User("user2", "user2"),
        User("admin", "admin"),
        User("user","user")
    )


    def lookupUser(u: User): Boolean = {
        users.contains(u)
    }
}
