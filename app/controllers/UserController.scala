package controllers

import javax.inject.Inject
import models.{Global, User, UserDao}
import play.api.data.Forms._
import play.api.data._
import play.api.mvc._

//used this code from wpo

class UserController @Inject()(
    cc: MessagesControllerComponents,
    userDao: UserDao
) extends MessagesAbstractController(cc) {

    private val logger = play.api.Logger(this.getClass)

    val form: Form[User] = Form (
        mapping(
            "username" -> nonEmptyText
                .verifying("Too few characters",  s => lengthIsGreaterThanNCharacters(s, 2))
                .verifying("Too many characters", s => lengthIsLessThanNCharacters(s, 20)),
            "password" -> nonEmptyText
                .verifying("Too few characters",  s => lengthIsGreaterThanNCharacters(s, 2))
                .verifying("Too few characters", s => lengthIsLessThanNCharacters(s, 30)),
        )(User.apply)(User.unapply)
    )

    private val formSubmitUrl = routes.UserController.processLoginAttempt

    def showLoginForm: Action[AnyContent] = Action {
        implicit request: MessagesRequest[AnyContent] =>
        Ok(views.html.userLogin(form, formSubmitUrl))
    }

    def processLoginAttempt: Action[AnyContent] = Action {
        implicit request: MessagesRequest[AnyContent] =>
        val errorFunction = { formWithErrors: Form[User] =>
            // form validation/binding failed...
            BadRequest(views.html.userLogin(formWithErrors, formSubmitUrl))
        }
        val successFunction = { user: User =>
            // form validation/binding succeeded ...
            val foundUser: Boolean = userDao.lookupUser(user)
            if (foundUser) {
                Redirect(routes.LandingPageController.showLandingPage)
                    .flashing("INFO" -> "You are logged in.")
                    .withSession(Global.SESSION_USERNAME_KEY -> user.username)
            } else {
                Redirect(routes.UserController.showLoginForm)
                    .flashing("ERROR" -> "Invalid username/password.")
            }
        }
        val formValidationResult: Form[User] = form.bindFromRequest
        formValidationResult.fold(
            errorFunction,
            successFunction
        )
    }

    private val formSubmitUrlRegister = routes.UserController.processRegisterAttempt

    def showRegisterForm: Action[AnyContent] = Action {
        implicit request: MessagesRequest[AnyContent] =>
            Ok(views.html.userRegister(form, formSubmitUrlRegister))
    }

    def processRegisterAttempt: Action[AnyContent] = Action {
        implicit request: MessagesRequest[AnyContent] =>
            val errorFunction = { formWithErrors: Form[User] =>
                // form validation/binding failed...
                BadRequest(views.html.userRegister(formWithErrors, formSubmitUrl))
            }
            val successFunction = { user: User =>
                // form validation/binding succeeded ...
                val foundUser: Boolean = userDao.lookupUser(user)
                if (foundUser) {
                    Redirect(routes.UserController.showRegisterForm)
                      .flashing("ERROR" -> "User already exist.")
                } else {
                    userDao.users = Seq(
                        User(user.username, user.password))
                    Redirect(routes.UserController.showLoginForm)
                      .flashing("INFO" -> "You are successfully registered! Please log in to continue.")
                }
            }
            val formValidationResult: Form[User] = form.bindFromRequest
            formValidationResult.fold(
                errorFunction,
                successFunction
            )
    }

    private def lengthIsGreaterThanNCharacters(s: String, n: Int): Boolean = {
        if (s.length > n) true else false
    }

    private def lengthIsLessThanNCharacters(s: String, n: Int): Boolean = {
        if (s.length < n) true else false
    }

}
