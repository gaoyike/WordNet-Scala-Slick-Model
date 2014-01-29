package snippet

import java.util.Properties
import java.io.FileInputStream
import scala.slick.lifted.{AbstractTable, Tag, TableQuery}
import scala.slick.driver.MySQLDriver.simple._
import model._

object MyMain {
  def RunQuerySession={
  val prop = new Properties()
  prop.load(new FileInputStream("./src/main/scala/conf/application.conf"))
  val user = prop.getProperty("db.default.user")
  val pass = prop.getProperty("db.default.password")
  val url = prop.getProperty("db.default.url")
  val driver = prop.getProperty("db.default.driver")
  Database.forURL(url = url, user = user, password = pass, driver = driver) withSession{
    implicit session => {
      val word = TableQuery[_words]
      word foreach println
    }
  }
}

  def main(args: Array[String]) {
    MyMain.RunQuerySession
  }
}

