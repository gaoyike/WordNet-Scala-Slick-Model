package model
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 *  CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`vframes` (
  `frameid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `frame` VARCHAR(50) NULL DEFAULT NULL,
  PRIMARY KEY (`frameid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class vframes(frameid:Int, frame:String)
class _vframes(tag:Tag) extends Table[vframes](tag,"vframes"){
  def frameid = column[Int]("frameid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def frame = column[String]("frame",O.DBType("VARCHAR(50)"))
  def * = (frameid, frame) <> (vframes.tupled, vframes.unapply)
}
