package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`adjpositiontypes` (
  `position` ENUM('a','p','ip') NOT NULL,
  `positionname` VARCHAR(24) NOT NULL,
  PRIMARY KEY (`position`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class adjpositiontypes(position:String,positionname:String)
class _adjpositiontypes(tag:Tag) extends Table[adjpositiontypes](tag,"adjpositiontypes"){
  def position = column[String]("position", O.PrimaryKey, O.DBType("ENUM('a','p','ip')"), NotNull) // This is the primary key column
  def positionname = column[String]("positionname",NotNull,O.DBType("VARCHAR(24)"))
  def * = (position, positionname) <> (adjpositiontypes.tupled, adjpositiontypes.unapply)
}