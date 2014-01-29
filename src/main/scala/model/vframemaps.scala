package model
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`vframemaps` (
  `synsetid` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `frameid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`synsetid`, `wordid`, `frameid`),
  INDEX `k_vframemaps_frameid` (`frameid` ASC),
  INDEX `k_vframemaps_synsetid` (`synsetid` ASC),
  INDEX `k_vframemaps_wordid` (`wordid` ASC),
  CONSTRAINT `fk_vframemaps_frameid`
    FOREIGN KEY (`frameid`)
    REFERENCES `wordnet31_snapshot`.`vframes` (`frameid`),
  CONSTRAINT `fk_vframemaps_synsetid`
    FOREIGN KEY (`synsetid`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_vframemaps_wordid`
    FOREIGN KEY (`wordid`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class vframemaps(synsetid:Int, wordid:Int, frameid:Int)
class _vframemaps(tag:Tag) extends Table[vframemaps](tag,"vframemaps"){
  def synsetid = column[Int]("synsetid", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def wordid = column[Int]("wordid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def frameid = column[Int]("frameid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def * = (synsetid, wordid, frameid) <> (vframemaps.tupled, vframemaps.unapply)

  val words = TableQuery[_words]
  def fk_vframemaps_wordid = foreignKey("fk_vframemaps_wordid", wordid, words)(_.wordid)


  val vframes = TableQuery[_vframes]
  def fk_vframemaps_frameid = foreignKey("fk_vframemaps_frameid", frameid, vframes)(_.frameid)

  val synset = TableQuery[_synsets]
  def fk_vframemaps_synsetid = foreignKey("fk_vframemaps_synsetid", synsetid, synset)(_.synsetid)


}
