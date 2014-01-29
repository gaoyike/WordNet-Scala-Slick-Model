package model

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`morphs` (
  `morphid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `morph` VARCHAR(70) NOT NULL,
  PRIMARY KEY (`morphid`),
  UNIQUE INDEX `unq_morphs_morph` (`morph` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class morphs(morphid:Int, morph:String)
class _morphs(tag:Tag) extends Table[morphs](tag,"morphs"){
  def morphid = column[Int]("morphid",O.PrimaryKey,O.DBType("MEDIUMINT(8)"),NotNull,Default(0))
  def morph = column[String]("morph",NotNull,O.DBType("VARCHAR(70)"))
  def * = ( morphid, morph) <> (morphs.tupled, morphs.unapply)
}

