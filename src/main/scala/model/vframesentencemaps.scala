package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 *CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`vframesentencemaps` (
  `synsetid` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `sentenceid` SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`synsetid`, `wordid`, `sentenceid`),
  INDEX `k_vframesentencemaps_sentenceid` (`sentenceid` ASC),
  INDEX `k_vframesentencemaps_synsetid` (`synsetid` ASC),
  INDEX `k_vframesentencemaps_wordid` (`wordid` ASC),
  CONSTRAINT `fk_vframesentencemaps_sentenceid`
    FOREIGN KEY (`sentenceid`)
    REFERENCES `wordnet31_snapshot`.`vframesentences` (`sentenceid`),
  CONSTRAINT `fk_vframesentencemaps_synsetid`
    FOREIGN KEY (`synsetid`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_vframesentencemaps_wordid`
    FOREIGN KEY (`wordid`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class vframesentencemaps(synsetid:Int, wordid:Int, sentenceid:Int)
class _vframesentencemaps(tag:Tag) extends Table[vframesentencemaps](tag,"vframesentencemaps"){
  def synsetid = column[Int]("synsetid", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def wordid = column[Int]("wordid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def sentenceid = column[Int]("sentenceid", O.PrimaryKey, O.DBType("SMALLINT(5)"), NotNull, Default(0)) // This is the primary key column
  def * = (synsetid, wordid, sentenceid) <>(vframesentencemaps.tupled, vframesentencemaps.unapply)

  val words = TableQuery[_words]
  def fk_vframesentencemaps_wordid = foreignKey("fk_vframesentencemaps_wordid", wordid, words)(_.wordid)


  val vframesentences = TableQuery[_vframesentences]
  def fk_vframesentencemaps_sentenceid = foreignKey("fk_vframesentencemaps_sentenceid", sentenceid, vframesentences)(_.sentenceid)

  val synset = TableQuery[_synsets]
  def fk_vframesentencemaps_synsetid = foreignKey("fk_vframesentencemaps_synsetid", synsetid, synset)(_.synsetid)


}
