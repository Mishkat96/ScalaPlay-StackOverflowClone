package controllers

import models.PostDao

import javax.inject._
import play.api.mvc._

@Singleton
class HomeController @Inject()(cc: ControllerComponents) extends AbstractController(cc) {

  def index(): Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val posts = PostDao.findAll
    Ok(views.html.loginLandingPage())
  }

}
