package controllers

import models.PostDao

import javax.inject._
import play.api.mvc._

@Singleton
class LandingPageController @Inject()(
    cc: ControllerComponents
) extends AbstractController(cc) {

    def showLandingPage(): Action[AnyContent] = Action {
        implicit request: Request[AnyContent] =>
            Ok(views.html.loginLandingPage())
    }
}
