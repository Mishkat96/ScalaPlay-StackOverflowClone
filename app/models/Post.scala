package models
import akka.http.scaladsl.model.DateTime


case class Post(id: Int,
                date: DateTime,
                username: String,
                title: String,
                text: String,
                code: String,
                var answers: List[String],
                var comment: List[String],
                var vote: Int)

