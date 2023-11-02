package controllers

import javax.inject._
import play.api.mvc._
//used this code from WPO

@Singleton
class AuthenticatedUserController @Inject()(
    cc: ControllerComponents,
    authenticatedUserAction: AuthenticatedUserAction
) extends AbstractController(cc) {

    def logout: Action[AnyContent] = authenticatedUserAction { implicit request: Request[AnyContent] =>

        Redirect(routes.UserController.showLoginForm)
            .flashing("INFO" -> "You are logged out.")
            .withNewSession
    }
}
