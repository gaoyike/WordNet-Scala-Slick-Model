package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`senses` (
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `casedwordid` MEDIUMINT(8) UNSIGNED NULL DEFAULT NULL,
  `synsetid` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `senseid` MEDIUMINT(8) UNSIGNED NULL DEFAULT NULL,
  `sensenum` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `lexid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  `tagcount` MEDIUMINT(8) UNSIGNED NULL DEFAULT NULL,
  `sensekey` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`wordid`, `synsetid`),
  UNIQUE INDEX `unq_senses_senseid` (`senseid` ASC),
  UNIQUE INDEX `unq_senses_sensekey` (`sensekey` ASC),
  INDEX `k_senses_synsetid` (`synsetid` ASC),
  INDEX `k_senses_wordid` (`wordid` ASC),
  CONSTRAINT `fk_senses_synsetid`
    FOREIGN KEY (`synsetid`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_senses_wordid`
    FOREIGN KEY (`wordid`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class senses(wordid:Int,casedwordid:Int,synsetid:Int,senseid:Int,sensenum:Int,lexid:Int,tagcount:Int,sensekey:String)
class _senses(tag:Tag) extends Table[senses](tag,"senses"){
  def wordid = column[Int]("wordid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def casedwordid = column[Int]("casedwordid",O.DBType("MEDIUMINT(8)")) // This is the primary key column
  def synsetid = column[Int]("synsetid", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def senseid = column[Int]("senseid",O.DBType("MEDIUMINT(8)")) // This is the primary key column
  def sensenum = column[Int]("sensenum", O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def lexid = column[Int]("lexid", O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def tagcount = column[Int]("tagcount",O.DBType("MEDIUMINT(8)")) // This is the primary key column
  def sensekey = column[String]("sensekey",O.DBType("VARCHAR(100)"))

  def * = (wordid, casedwordid, synsetid,senseid,sensenum,lexid,tagcount,sensekey)  <> (senses.tupled, senses.unapply)

  val words = TableQuery[_words]
  def fk_senses_wordid = foreignKey("fk_senses_wordid", wordid, words)(_.wordid)

  val synset = TableQuery[_synsets]
  def fk_senses_synsetid = foreignKey("fk_senses_synsetid", synsetid, synset)(_.synsetid)


}