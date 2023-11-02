package controllers

import akka.http.scaladsl.model.DateTime
import models.{Post, PostDao}
import play.api.mvc._

import javax.inject._


@Singleton
class PostController @Inject()(cc: ControllerComponents, authenticatedUserAction: AuthenticatedUserAction) extends AbstractController(cc) {

//This function returns the postView View which shows all the posts
  def listPosts: Action[AnyContent] = authenticatedUserAction {
    implicit request: Request[AnyContent] =>
      val title = request.session.get("title")
    val posts = PostDao.findAll
    Ok(views.html.postView(posts))
  }

  //After adding a new post this function helps to save the post in the model PostDao and return the postView View
  //with the new post in it
  def addNewPost(number: String, a: String, b: String): Action[AnyContent] = Action { implicit request =>

    //val user = Post("", number, "")
    val username = request.session.get("username")
    val currentDate = DateTime.now
    var UnitId = PostDao.findAll.length + 1
    PostDao.posts = PostDao.posts :+ Post(UnitId,currentDate, username.get, number, a, b, List(),List(), 0)
    val posts = PostDao.findAll
    Ok(views.html.postView(posts))

  }
//This function redirects to the page where we can add our title, text and code for a new post
  def addNewPostPost: Action[AnyContent] = Action { implicit request =>
    val username = request.session.get("username").toString
    val id = request.session.get("id").toString
    Ok(views.html.addNewPost(username))
  }

    //This function returns the page where we can add our new answer for any post
  def addNewCommentPost(id: Int): Action[AnyContent] = Action { implicit request =>
    val username = request.session.get("username").toString
    var idd = 0
    val posts1 = PostDao.findAll
    posts1.foreach { temp =>
      if (temp.id == id) {
        idd = temp.id
        Ok(views.html.addNewComment(username, id))
      }
    }
    Ok(views.html.addNewComment(username, id))
  }

  //This function helps to increase a vote when clicked the vote button
  def voteIncreament(id: Int) = Action { implicit request =>
    val a = PostDao.findAll
    var suppose = true
    a.foreach(temp =>
      if (temp.id == id) {
        if (suppose == true) {
          temp.vote = temp.vote + 1
          suppose = false
        }
      }
    )
    val posts = PostDao.findAll
    Ok(views.html.postView(posts))

  }

  //This function helps to decrease a vote when clicked the vote button
  def voteDecreament(id: Int) = Action { implicit request =>
    val a = PostDao.findAll
    var suppose = true
    a.foreach(temp =>
      if (temp.id == id) {
        if (suppose == true) {
          temp.vote = temp.vote - 1
          suppose = false
        }
      }
    )
    val posts = PostDao.findAll
    Ok(views.html.postView(posts))

  }

//After adding answer, this function helps to store the answer on the model and then show the view with the new answer
  def addAnswers(answer: String, answerCode: String, id: Int) = Action {
    implicit request =>
      val postsTemp = PostDao.findAll
      postsTemp.foreach { temp =>
        if (temp.id == id) {
          temp.answers = temp.answers :+ answer :+ answerCode
        }
      }

      val posts = PostDao.findAll
      Ok(views.html.postView(posts))

  }

  //This function helps the comment to store on the model and show the view with this new answer dislayed on it
  def addComment(comment: String ,id: Int) = Action {
    implicit request =>
      val postsTemp = PostDao.findAll
      postsTemp.foreach { temp =>
        if (temp.id == id) {
          temp.comment = temp.comment :+ comment
        }
      }
      val posts = PostDao.findAll
      Ok(views.html.postView(posts))
  }

  //This function displays the page where we can input our comment
  def addNewComment(id: Int): Action[AnyContent] = Action { implicit request =>
    val username = request.session.get("username").toString
    var idd = 0
    val posts1 = PostDao.findAll
    posts1.foreach { temp =>
      if (temp.id == id) {
        idd = temp.id
        Ok(views.html.addComment(username, id))
      }
    }
    Ok(views.html.addComment(username, id))
  }

  //This function helps to sort all the posts by date when clicked the "Sort By Date" button
  def sortByDate: Action[AnyContent] = Action {
    implicit request =>
    val sortbyDate = PostDao.findAll.sortBy(_.date).reverse
      Ok(views.html.postView(sortbyDate))
  }
  //This function helps to sort all the posts by vote when clicked the "Sort By Vote" button
  def sortByVote: Action[AnyContent] = Action {
    implicit request =>
      val sortbyvote = PostDao.findAll.sortBy(_.vote).reverse
      Ok(views.html.postView(sortbyvote))
  }

  //sorting all posts by date when the user is not logged in
  def sortByDateLogout: Action[AnyContent] = Action {
    implicit request =>
      val sortbyDate = PostDao.findAll.sortBy(_.date).reverse
      Ok(views.html.index(sortbyDate))
  }
  //sorting all posts by vote when the user is not logged in
  def sortByVoteLogout: Action[AnyContent] = Action {
    implicit request =>
      val sortbyvote = PostDao.findAll.sortBy(_.vote).reverse
      Ok(views.html.index(sortbyvote))
  }
//This function helps to display all the posts when the user is logged out.
  def showPostNotFunction: Action[AnyContent] = Action { implicit request: Request[AnyContent] =>
    val posts = PostDao.findAll
    Ok(views.html.index(posts))
  }


}
