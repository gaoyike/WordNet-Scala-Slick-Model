package model

/**
 *
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`morphmaps` (
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `pos` ENUM('n','v','a','r','s') NOT NULL DEFAULT 'n',
  `morphid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`morphid`, `pos`, `wordid`),
  INDEX `k_morphmaps_morphid` (`morphid` ASC),
  INDEX `k_morphmaps_wordid` (`wordid` ASC),
  CONSTRAINT `fk_morphmaps_morphid`
    FOREIGN KEY (`morphid`)
    REFERENCES `wordnet31_snapshot`.`morphs` (`morphid`),
  CONSTRAINT `fk_morphmaps_wordid`
    FOREIGN KEY (`wordid`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}
case class morphmaps(wordid:Int,pos:String,morphid:Int)
class _morphmaps(tag:Tag) extends Table[morphmaps](tag,"morphmaps"){
  def wordid = column[Int]("wordid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def pos = column[String]("pos",O.PrimaryKey,O.DBType("ENUM('n','v','a','r','s')"),NotNull, Default("n"))
  def morphid = column[Int]("morphid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def * = ( wordid, pos,morphid) <>(morphmaps.tupled, morphmaps.unapply)

  val words = TableQuery[_words]
  def fk_morphmaps_wordid = foreignKey("fk_morphmaps_wordid", wordid, words)(_.wordid)

  val morphs = TableQuery[_morphs]
  def fk_morphmaps_morphid = foreignKey("fk_morphmaps_morphid", morphid, morphs)(_.morphid)



}
