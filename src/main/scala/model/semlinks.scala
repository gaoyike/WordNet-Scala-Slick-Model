package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`semlinks` (
  `synset1id` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `synset2id` INT(10) UNSIGNED NOT NULL DEFAULT '0',
  `linkid` TINYINT(3) UNSIGNED NOT NULL DEFAULT '0',
  PRIMARY KEY (`synset1id`, `synset2id`, `linkid`),
  INDEX `k_semlinks_linkid` (`linkid` ASC),
  INDEX `k_semlinks_synset1id` (`synset1id` ASC),
  INDEX `k_semlinks_synset2id` (`synset2id` ASC),
  CONSTRAINT `fk_semlinks_linkid`
    FOREIGN KEY (`linkid`)
    REFERENCES `wordnet31_snapshot`.`linktypes` (`linkid`),
  CONSTRAINT `fk_semlinks_synset1id`
    FOREIGN KEY (`synset1id`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`),
  CONSTRAINT `fk_semlinks_synset2id`
    FOREIGN KEY (`synset2id`)
    REFERENCES `wordnet31_snapshot`.`synsets` (`synsetid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class semlinks(synset1id:Int, synset2id:Int, linkid:Int)
class _semlinks(tag:Tag) extends Table[semlinks](tag,"semlinks"){
  def synset1id = column[Int]("synset1id", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def synset2id = column[Int]("synset2id", O.PrimaryKey, O.DBType("INT(10)"), NotNull, Default(0)) // This is the primary key column
  def linkid = column[Int]("linkid", O.PrimaryKey, O.DBType("TINYINT(3)"), NotNull, Default(0)) // This is the primary key column
  def * = (synset1id, synset2id,linkid) <> (semlinks.tupled, semlinks.unapply)

  val links = TableQuery[_linktypes]
  def fk_semlinks_linkid = foreignKey("fk_semlinks_linkid", linkid, links)(_.linkid)

  val synset1 = TableQuery[_synsets]
  def fk_semlinks_synset1id = foreignKey("fk_semlinks_synset1id", synset1id, synset1)(_.synsetid)

  val synset2 = TableQuery[_synsets]
  def fk_semlinks_synset2id = foreignKey("fk_semlinks_synset2id", synset2id, synset2)(_.synsetid)


}
