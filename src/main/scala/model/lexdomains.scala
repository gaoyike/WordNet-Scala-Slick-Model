package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}
/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`lexdomains` (
  `lexdomainid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `lexdomainname` VARCHAR(32) NULL DEFAULT NULL,
  `pos` ENUM('n','v','a','r','s') NULL DEFAULT NULL,
  PRIMARY KEY (`lexdomainid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class lexdomains(lexdomainid:Int, lexdomainname:String, pos:String)
class _lexdomains(tag:Tag) extends Table[lexdomains](tag,"lexdomains"){
  def lexdomainid = column[Int]("lexdomainid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def lexdomainname = column[String]("lexdomainname",NotNull,O.DBType("VARCHAR(32)"))
  def pos = column[String]("pos",O.DBType("ENUM('n','v','a','r','s')"))
  def * = (lexdomainid, lexdomainname, pos) <> (lexdomains.tupled, lexdomains.unapply)
}
