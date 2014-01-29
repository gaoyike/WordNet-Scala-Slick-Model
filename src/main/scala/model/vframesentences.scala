package model

/**
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`vframesentences` (
  `sentenceid` SMALLINT(5) UNSIGNED NOT NULL DEFAULT '0',
  `sentence` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`sentenceid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}

/**
 * Created by chenguanghe on 1/29/14.
 */
case class vframesentences(sentenceid:Int, sentence:String)
class _vframesentences(tag:Tag) extends Table[vframesentences](tag,"vframesentences"){
  def sentenceid = column[Int]("sentenceid", O.PrimaryKey, O.DBType("SMALLINT(5)"), NotNull, Default(0)) // This is the primary key column
  def sentence = column[String]("sentence",O.DBType("MEDIUMTEXT"))
  def * = (sentenceid, sentence) <> (vframesentences.tupled, vframesentences.unapply)
}
