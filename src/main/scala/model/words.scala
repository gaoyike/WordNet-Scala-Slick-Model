package model
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 *　  CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`words` (
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `lemma` VARCHAR(80) NOT NULL,
  PRIMARY KEY (`wordid`),
  UNIQUE INDEX `unq_words_lemma` (`lemma` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class words(wordid:Int, lemma:String)
class _words(tag:Tag) extends Table[words](tag,"WORDS"){
  def wordid = column[Int]("WORDID", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull,Default(0)) // This is the primary key column
  def lemma = column[String]("LEMMA",NotNull,O.DBType("VARCHAR(80)"))
  def * = (wordid, lemma) <> (words.tupled, words.unapply)
}

