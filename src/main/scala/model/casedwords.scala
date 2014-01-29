package model


import scala.slick.driver.MySQLDriver.simple._
import scala.slick.ast.ColumnOption.{Default, NotNull}
import scala.slick.direct.AnnotationMapper.column

/**
 *
 * CREATE TABLE IF NOT EXISTS `wordnet31_snapshot`.`casedwords` (
  `casedwordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `wordid` MEDIUMINT(8) UNSIGNED NOT NULL DEFAULT '0',
  `cased` VARCHAR(80) CHARACTER SET 'utf8' COLLATE 'utf8_bin' NOT NULL,
  PRIMARY KEY (`casedwordid`),
  UNIQUE INDEX `unq_casedwords_cased` (`cased` ASC),
  INDEX `k_casedwords_wordid` (`wordid` ASC),
  CONSTRAINT `fk_casedwords_wordid`
    FOREIGN KEY (`wordid`)
    REFERENCES `wordnet31_snapshot`.`words` (`wordid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
 */
case class casedwords(casedwordid:Int,wordid:Int,cased:String)
class _casedwords(tag:Tag) extends Table[casedwords](tag,"casedwords"){

  def casedwordid = column[Int]("casedwordid", O.PrimaryKey, O.DBType("MEDIUMINT(8)"), NotNull, Default(0)) // This is the primary key column
  def wordid = column[Int]("wordid", O.DBType("MEDIUMINT(8)"), Default(0)) // This is the primary key column
  def cased = column[String]("cased", O.DBType("VARCHAR(80)"),NotNull)

  def * = (casedwordid, wordid, cased) <> (casedwords.tupled, casedwords.unapply)

  val words = TableQuery[_words]
  def fk_casedwords_wordid = foreignKey("fk_casedwords_wordid", wordid, words)(_.wordid)

}
