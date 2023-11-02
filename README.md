# ScalaPlay-StackOverflowClone

Log-In and Registration:

We have set some values for login by default. We can also make a new
account through registration. The credentials are saved on case class User. The login
page looks like this. The log-in view comes from the view “userLogin.scala.html” and
the registration page view comes from the view “userRegister.scala.html”.

<img width="404" alt="Screenshot 2023-11-02 at 10 32 02 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/c25dacb7-fed7-45f5-9261-c85d8cad0f84">

Similarly, the registration page looks like this.

<img width="385" alt="Screenshot 2023-11-02 at 10 32 34 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/dfba076f-ee56-4ed1-b3de-f4104b6acaaf">

Access to site:

When we are logged out, we can still see the posts, votes, answers, comments. We
can also sort the post by date, vote. But we cannot make any vote, answer, or comment.
The below view comes from the View “index.scala.html”.

<img width="469" alt="Screenshot 2023-11-02 at 10 33 45 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/94c26282-5881-4e55-9392-a36c9416bace">

We can see that even being logged out we have access to the posts but not any
functionalities.

Functionalities:

Add Post:

We can add new post by clicking “Add New Post”. There we have three input
fields where we can write the title, problem statement and put our code. This view comes
from the view “addNewPost.scala.html”.

<img width="706" alt="Screenshot 2023-11-02 at 10 34 31 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/48a37c0a-4256-4854-8037-2b46eb2a883b">

Add Answers and Add Comment:

Any logged-in user can answer or comment by clicking on the “Add Answer”
or “Add Comment” button. It will redirect to a page where they can input their comment or
answer. We can see that in the below 3 figures. The “Add Answer” view comes from the
View “addNewComment.scala.html” and the “Add Comment” view comes from the View
“addComment.scala.html”.

<img width="465" alt="Screenshot 2023-11-02 at 10 35 17 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/cf1d3cdf-4936-431b-80a7-832d9121ca38">

<img width="766" alt="Screenshot 2023-11-02 at 10 35 41 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/be4342dd-f958-42a2-a783-31ab51bb88e5">

Sorting:

By clicking on the “Sort By Date” and “Sort By Vote” we can sort all the posts
according to date or votes. It can be done both by a logged-in user and a logged-out user.

<img width="546" alt="Screenshot 2023-11-02 at 10 36 21 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/743d686a-a10a-4c95-a79e-93a5f736e638">

Vote Unvote:

A logged in user can vote a post and unvote a post by clicking on the “Vote”
and “Unvote” button which is situated on the left side of all the posts.

<img width="622" alt="Screenshot 2023-11-02 at 10 36 55 PM" src="https://github.com/Mishkat96/ScalaPlay-StackOverflowClone/assets/47037691/1b338edd-f47d-40a3-9ba1-4b52085be7e3">

Clicking vote will increment vote by 1 and clicking unvote will decrement vote by 1.

Models:

We have 5 models and each of them are described as below:

Global:

We save the session_username_key here. It saves the username for every
session.

Post and PostDao:

Mainly the case class Post is in the Post model and object for the Post Model
is in the PostDao where mainly all the functionalities are saved e.g., posts, answers,
comments, votes, etc.

User and UserDao:

In the User model the case class “User” exists and on the UserDao all the
usernames and password are saved.

Controllers:

We have in total 6 controllers and each of them with their functions are described as
below:

AuthenticatedUserAction:

From this controller we use the function invokeBlock(). This function works to
block all our functionalities if the user is not logged-in.

AuthenticatedUserController:

From this controller we use the function logout() to log out any user and
redirect to the login page.

LandingPageController:

This controller is used to redirect to the login landing page. When a user logs
in then the first page that pops up, it happens due to this controllers with the function
index().

PostController:

This is the most important controller. All the functionalities after loggin in
happens through this controller. Description of each function are given on the code.
UserController:

Through this controller the user login and registration take place.
