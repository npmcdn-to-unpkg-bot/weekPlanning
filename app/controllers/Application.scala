package weekplanning.controllers

import models.Coworker
import play.api.mvc
import play.api.mvc.{Action, Controller}
import service.DAL

import scala.concurrent.{Await, Future}
import scala.concurrent.duration.Duration
import weekplanning._

import scala.util.{Failure, Success}

class Application extends Controller with Secured {

  def test = Action {
/*    DAL.createUserSchema()
    DAL.createProjectSchema()
    DAL.createCollaboratesSchema()
    DAL.createCoworkerSchema() */

    Await.result(DAL.addCoworker(Coworker(0, 32, "zander")), Duration.Inf) match {
      case Success(_) => Ok("ok")
      case Failure(ex) => Ok(ex.getMessage)
    }
/*    Ok("schema created.")*/
  }

  def index = withAuth { username => implicit request =>
    Redirect(routes.Application.projects())
  }

  def projects = withAuth { username => implicit request =>
    Ok(views.html.projects(Global.name, "Projects", Global.menu))
  }

}
