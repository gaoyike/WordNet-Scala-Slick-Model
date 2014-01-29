package model


import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}
/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`synsets` (
  `synsetid` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `pos` ENUM('n','v','a','r','s') NULL DEFAULT NULL,
  `lexdomainid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `definition` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`synsetid`),
  INDEX `k_synsets_lexdomainid` (`lexdomainid` ASC),
  CONSTRAINT `fk_synsets_lexdomainid`
    FOREIGN KEY (`lexdomainid`)
    REFERENCES `wordnet31_snapshot`.`lexdomains` (`lexdomainid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class synsets(synsetid:Int, lexdomainid:Int, pos:String, definition:String)
class _synsets(tag:Tag) extends Table[synsets](tag,"synsets"){

  def synsetid = column[Int]("synsetid", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def lexdomainid = column[Int]("lexdomainid",NotNull,O.DBType("TINYINT(3)"),Default(0))
  def pos = column[String]("pos",O.DBType("ENUM('n','v','a','r','s')"))
  def definition = column[String]("definition",O.DBType("MEDIUMTEXT"))
  def * = (synsetid, lexdomainid, pos,definition)  <> (synsets.tupled, synsets.unapply)

  val lexdomains = TableQuery[_lexdomains]
  def fk_synsets_lexdomainid = foreignKey("fk_synsets_lexdomainid", lexdomainid, lexdomains)(_.lexdomainid)
}

