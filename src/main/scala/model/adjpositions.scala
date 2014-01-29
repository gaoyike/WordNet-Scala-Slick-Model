package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`adjpositions` (
  `synsetid` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `position` ENUM('a','p','ip') NOT NULL DEFAULT 'a',
  PRIMARY KEY (`synsetid`, `wordid`),
  INDEX `k_adjpositions_synsetid` (`synsetid` ASC),
  INDEX `k_adjpositions_wordid` (`wordid` ASC),
  CONSTRAINT `fk_adjpositions_synsetid`
    FOREIGN KEY (`synsetid`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_adjpositions_wordid`
    FOREIGN KEY (`wordid`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class adjpositions(wordid:Int,synsetid:Int,position:String)
class _adjpositions(tag:Tag) extends Table[adjpositions](tag,"adjpositions"){
  def wordid = column[Int]("wordid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def synsetid = column[Int]("synsetid", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def position = column[String]("position", O.PrimaryKey, O.DBType("ENUM('a','p','ip')"), NotNull,Default("a")) // This is the primary key column
  def * = (wordid,synsetid,position) <> (adjpositions.tupled,adjpositions.unapply)

  val words = TableQuery[_words]
  def fk_adjpositions_wordid = foreignKey("fk_adjpositions_wordid", wordid, words)(_.wordid)

  val synset = TableQuery[_synsets]
  def fk_adjpositions_synsetid = foreignKey("fk_adjpositions_synsetid", synsetid, synset)(_.synsetid)


}