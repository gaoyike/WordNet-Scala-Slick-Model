package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}
/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`linktypes` (
  `linkid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `link` VARCHAR(50) NULL DEFAULT NULL,
  `recurses` TINYINT(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`linkid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class linktypes(linkid:Int, link:String, recurses:Int)
class _linktypes(tag:Tag) extends Table[linktypes](tag,"linktypes"){
  def linkid = column[Int]("linkid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def link = column[String]("link",O.DBType("VARCHAR(50)"))
  def recurses = column[Int]("pos", O.DBType("TINYINT(1)"), NotNull, Default(0))
  def * = (linkid, link, recurses) <> (linktypes.tupled, linktypes.unapply)
}

