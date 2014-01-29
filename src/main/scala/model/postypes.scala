package model


import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 *  CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`postypes` (
  `pos` ENUM('n','v','a','r','s') NOT NULL,
  `posname` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`pos`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class postypes(pos:String, posname:String)
class _postypes(tag:Tag) extends Table[postypes](tag,"postypes"){
  def pos = column[String]("pos",O.PrimaryKey,O.DBType("ENUM('n','v','a','r','s')"),NotNull)
  def posname = column[String]("posname",NotNull,O.DBType("VARCHAR(20)"))
  def * = (posname, pos) <> (postypes.tupled, postypes.unapply)
}
