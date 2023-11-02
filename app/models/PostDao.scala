package models
import akka.http.scaladsl.model.DateTime


object PostDao {
  var posts: Seq[Post] = Seq(
    Post(1, DateTime(1970-1-1), "Alex", s"Refernce: https://stackoverflow.com/questions/74997732/ \n\n\n\n\n      \n An expression attribute name used in the document path is not defined; attribute name", "I am using reserve DynamoDB keywords live value,users, name. I have create entry in DynamoDB with\n", "{\n   \"id\":1\n  \"poc_name\": \"ABC\"\n}", List("Reference: https://stackoverflow.com/questions/74997753/how-to-unnest-a-dataframe-resulting-from-a-test-in-r \n It is a list output which we may convert to tidy dataset","library(broom)\nlibrary(dplyr)\nlibrary(tidyr)\ndf %>% \n  group_by(test) %>%\n  summarise(out = tidy(shapiro.test(difference))) %>% \n  unnest(out)"),List("How to do this?"),2),
    Post(2, DateTime.now, "Bob", "array", "How to run an array", "a = [1,2,3,4]", List("not like this","a++"),List("Can you explain in details?"),3))

  def findAll: List[Post] = posts.toList

  def lookupPost(name: String): Seq[Post] = {
    posts.filter(_.username == name)
  }

}
